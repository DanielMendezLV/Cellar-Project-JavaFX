package org.mendez.visual;
import org.mendez.ui.App;
import org.mendez.beans.TipoHerramienta;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import org.mendez.beans.Herramienta;
import javafx.scene.input.MouseEvent;
import org.mendez.manejadores.ManejadorHerramienta;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
/**
*Esta clase contiene lo visual de herramienta
*@author Daniel Mendez
*/
public class VisualHerramienta extends Application implements EventHandler<Event>{
	private Button btnAgregarHerramienta;
	private Button btnActualizarHerramienta;
	private Button btnEliminarHerramienta;
	private Button btnEditarTipo;
	private HBox hBoxHerramienta;
	private VBox vBoxHerramienta;
	private Tab tbHerramientaCRUD;
	private TableView<Herramienta> tvHerramienta;
	private ManejadorHerramienta mHerramienta;
	private TabPane tpPrincipal;
	private App app;
	private Scene sceneAgregarHerramienta;
	private TableView<TipoHerramienta> tvTipo;
	private Button btnBuscarTipo;
	private Button btnAceptarHerramienta;
	private GridPane gpAgregarHerramienta;
	private Label lblHerramienta;
	private Label lblTipo;
	private TextField tfIdTipo;
	private BorderPane bPbusquedaTipo;
	private TextField tfNombreHerramienta;
	private Label lblIngreso, lblIngreso1;
	private TextField tfEditarTipo;
	private Stage ventanaAgregar;
	private Tab tbEditarTipo;
	private Label lblEditarTipo;
	private Button btnAceptarEdicionTipo, btnBuscarTipoEdicion;
	private GridPane gpEditarTipo;
	private BorderPane bpBusquedaTip;
	private int id;
	/**
	*Este metodo inicaliza los objetos de la tab herramienta
	*/
	public void inicializarObjetosTabHerramienta(){
		tfEditarTipo = new TextField();
		tfIdTipo = new TextField();
		btnAgregarHerramienta = new Button("Agregar");
		btnActualizarHerramienta = new Button("Actualizar");
		btnEliminarHerramienta = new Button("Eliminar");
		btnEditarTipo = new Button("Editar Tipo");
		hBoxHerramienta = new HBox();
		vBoxHerramienta = new VBox();
		hBoxHerramienta.getChildren().addAll(btnAgregarHerramienta, btnActualizarHerramienta, btnEliminarHerramienta, btnEditarTipo);
		tbHerramientaCRUD= new Tab("Herramienta");
	}

	/**Metodo constructor
	*@param mHerramienta tipo:ManejadorHerramienta
	*@param tpPrincipal tipo:TabPane
	*@param app tipo:App
	*@param tvTipo tipo:TableView
	*/
	public VisualHerramienta(ManejadorHerramienta mHerramienta, TabPane tpPrincipal, App app, TableView<TipoHerramienta>tvTipo){
		this.mHerramienta=mHerramienta;
		this.tpPrincipal=tpPrincipal;
		this.tvTipo=tvTipo;
		this.app=app;
		this.iniciar();
	}

	/**Metodo iniciar: inicia las operaciones*/
	public void iniciar(){
		this.inicializarObjetosTabHerramienta();
		this.asignarEventos();
		this.asignarMargenesYMeterAlVBox();
		this.meterValoresTableView();
		this.agregarTablaVbox();
		this.inicializarObjetosEditarTipo();
		this.insertarAlGridPaneEditar();
		this.asignarEventosEdicionTipo();
	}
	/**
	*Este metodo asigna eventos a los botones principales
	*/
	public void asignarEventos(){
		btnAgregarHerramienta.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnActualizarHerramienta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnEliminarHerramienta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnEditarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		tvTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}

	/**Este metodo asigna margenes y mete al vbox los nodos del hbox*/
	public void asignarMargenesYMeterAlVBox(){
		hBoxHerramienta.setMargin(btnEliminarHerramienta, new Insets(5,5,10,5));
		hBoxHerramienta.setMargin(btnActualizarHerramienta, new Insets(5,5,10,5));
		hBoxHerramienta.setMargin(btnAgregarHerramienta, new Insets(5,5,10,5));
		hBoxHerramienta.setMargin(btnEditarTipo, new Insets(5,5,10,5));
		vBoxHerramienta.getChildren().add(hBoxHerramienta);
	}
	/**
	*Este metodo inserta los valores al tableview
	*/
	public void meterValoresTableView(){
		tvHerramienta = new TableView<Herramienta>(mHerramienta.getListaDeHerramienta());
		tvHerramienta.setEditable(true);		
		TableColumn<Herramienta, Integer> columnaIdHerramienta=new TableColumn<Herramienta,Integer>("Id Herramienta");
		columnaIdHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta, Integer>("idHerramienta"));
		columnaIdHerramienta.setMinWidth(170);
		TableColumn<Herramienta, String> columnaSerial=new TableColumn<Herramienta,String>("Serial");
		columnaSerial.setCellValueFactory(new PropertyValueFactory<Herramienta, String>("serial"));
		columnaSerial.setMinWidth(100);
		TableColumn<Herramienta, String> columnaNTipo=new TableColumn<Herramienta,String>("Nombre General");
		columnaNTipo.setCellValueFactory(new PropertyValueFactory<Herramienta, String>("tipo"));
		columnaNTipo.setMinWidth(200);
		TableColumn<Herramienta, String> columnaEstado=new TableColumn<Herramienta,String>("Estado Especifico");
		columnaEstado.setCellValueFactory(new PropertyValueFactory<Herramienta, String>("estadoVisible"));
		columnaEstado.setMinWidth(200);
		TableColumn<Herramienta, String> columnaCantidadConjunto=new TableColumn<Herramienta,String>("Cantidad General");
		columnaCantidadConjunto.setCellValueFactory(new PropertyValueFactory<Herramienta, String>("cantidadGeneral"));
		columnaCantidadConjunto.setMinWidth(200);
		columnaSerial.setCellFactory(TextFieldTableCell.forTableColumn());
		tvHerramienta.getColumns().setAll(columnaIdHerramienta,columnaSerial, columnaNTipo, columnaEstado, columnaCantidadConjunto);		
	  /**
      * Edita el valor de la columnaSerial tipo Herramienta
      * @param EventHandler<CellEditEvent<Herramienta,String>>
      */
		columnaSerial.setOnEditCommit(new EventHandler<CellEditEvent<Herramienta,String>>(){
			@Override
				public void handle(CellEditEvent<Herramienta, String> eventoE){
					if(eventoE.getNewValue()!=(eventoE.getOldValue())){
						Herramienta herra = (Herramienta)tvHerramienta.getSelectionModel().getSelectedItem();
						mHerramienta.modificarSerial(herra.getIdHerramientaEspecifico(),eventoE.getNewValue());
						//((Contacto)eventoEditar.getTableView().getItems().get(eventoEditar.getTablePosition().getRow())).setNombre(t.getNewValue());
					}	
				}
			}
		);
	}
	/**
	*Agrega al vbox el tableview y tambien a la tab el contenido
	*/
	public void agregarTablaVbox(){
		vBoxHerramienta.getChildren().add(tvHerramienta);
		tbHerramientaCRUD.setContent(vBoxHerramienta);
	}
	/**Aqui se inicalizan los objetos al abregar una herramienta*/
	public void inicializarObjetosAgregar(){
		btnBuscarTipo = new Button("Buscar");
		btnAceptarHerramienta = new Button("Aceptar");
		gpAgregarHerramienta = new GridPane();
		lblIngreso = new Label("Ingrese los siguientes datos");
		lblIngreso1 = new Label("Ingrese los siguientes datos");
		lblHerramienta = new Label("Herramienta: ");
		lblHerramienta.setId("LblHerra");
		tfNombreHerramienta = new TextField();
		lblTipo = new Label("Nombre G.:");
		lblTipo.setId("LblTipo");
		tfIdTipo.setEditable(false);
		bPbusquedaTipo=new BorderPane();
	}

	/**Aca le asigno los eventos al agregar una herramienta*/
	public void asignarEventosAgregar(){
		btnBuscarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAceptarHerramienta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}

	/**Aca se mete al grid pane y se muestra el menu principal del crud herramienta*/
	public void meterAlGridPaneyMostrar(){
		gpAgregarHerramienta.add(lblIngreso,0,1);
		gpAgregarHerramienta.add(lblHerramienta,0,2);
		gpAgregarHerramienta.add(tfNombreHerramienta,1,2);
		gpAgregarHerramienta.add(lblTipo,0,3);
		gpAgregarHerramienta.add(tfIdTipo,1,3);
		gpAgregarHerramienta.add(btnBuscarTipo,2,3);
		gpAgregarHerramienta.add(btnAceptarHerramienta, 0,5);
		gpAgregarHerramienta.setMargin(lblIngreso, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(lblHerramienta, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(tfNombreHerramienta, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(lblTipo, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(tfIdTipo, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(btnBuscarTipo, new Insets(3,3,3,5));
		gpAgregarHerramienta.setMargin(btnAceptarHerramienta, new Insets(3,3,3,5));
		bPbusquedaTipo.setCenter(gpAgregarHerramienta);
		sceneAgregarHerramienta= new Scene(bPbusquedaTipo,400,400);
	}

	/**Aca se inicalizan los objetos al editar*/
	public void inicializarObjetosEditarTipo(){
		tbEditarTipo = new Tab("Editar Tipo");
		lblEditarTipo = new Label("Tipo:");
		btnAceptarEdicionTipo = new Button("Aceptar");
		btnBuscarTipoEdicion = new Button("Buscar...");
		tfEditarTipo.setPromptText("Nuevo Tipo");
		tfEditarTipo.setEditable(false);
		bpBusquedaTip = new BorderPane();
		gpEditarTipo = new GridPane();
		System.out.println(".......");
	}

	/**Aca se insertan los nodos al gridpane*/
	public void insertarAlGridPaneEditar(){
		//gpEditarTipo.add(lblIngreso1, 0,1);
		gpEditarTipo.add(lblEditarTipo, 0,2);
		gpEditarTipo.add(tfEditarTipo, 1,2);
		gpEditarTipo.add(btnBuscarTipoEdicion, 0,3);
		gpEditarTipo.add(btnAceptarEdicionTipo, 1,4);
		bpBusquedaTip.setLeft(gpEditarTipo);
		tbEditarTipo.setContent(bpBusquedaTip);
		lblEditarTipo.setId("lblTipo");
		//lblIngreso1.setId("lblIngreso");
		//gpEditarTipo.setMargin(lblIngreso, new Insets(3,3,3,5));
		gpEditarTipo.setMargin(lblEditarTipo, new Insets(3,3,3,5));
		gpEditarTipo.setMargin(tfEditarTipo, new Insets(3,3,3,5));
		gpEditarTipo.setMargin(btnAceptarEdicionTipo, new Insets(3,3,3,5));
		gpEditarTipo.setMargin(btnBuscarTipoEdicion, new Insets(3,3,3,5));
	}
	/**
	*Este metodo asigna los eventos al editar el tipo
	*/
	public void asignarEventosEdicionTipo(){
		btnAceptarEdicionTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);	
		btnBuscarTipoEdicion.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
	}

	/**Este metodo retorna la tab tbHerramientaCrud
	*@return tbHerramientaCRUD tipo:Tab
	*/
	public Tab getTab(){
		return  tbHerramientaCRUD;
	}
	/**
	*Metodo start esta vacio y sin uso
	*@param primary tipo:Stage
	*/
	public void start(Stage primary){
	}
	/**
	*Metodo handle
	*@param event tipo:Event
	*/
	public void handle(Event event){
		if(event.getSource().equals(btnActualizarHerramienta)){
			app.setId(0);
			mHerramienta.actualizarListaDeHerramientas();
		}
		if(event.getSource().equals(btnEliminarHerramienta)){
			Herramienta herramientaEliminar = (Herramienta)tvHerramienta.getSelectionModel().getSelectedItem();
			mHerramienta.eliminarHerramienta(herramientaEliminar.getIdHerramientaEspecifico());
		}
		if(event.getSource().equals(btnAgregarHerramienta)){
			app.volverEditable();
			this.inicializarObjetosAgregar();
			this.asignarEventosAgregar();
			this.meterAlGridPaneyMostrar();
			bPbusquedaTipo.getChildren().remove(tvTipo);
			tfIdTipo.setText(null);
			tfEditarTipo.setText(null);
			tfNombreHerramienta.setText(null);
			ventanaAgregar = new Stage();
			ventanaAgregar.setScene(sceneAgregarHerramienta);
			ventanaAgregar.show();
			ventanaAgregar.setWidth(650);
			ventanaAgregar.setHeight(250);	
		}
		if(event.getSource().equals(btnBuscarTipo)){
			bPbusquedaTipo.setRight(tvTipo);
			tvTipo.setEditable(false);
		}
		if(event.getSource().equals(tvTipo)){
			TipoHerramienta tipoI = (TipoHerramienta)tvTipo.getSelectionModel().getSelectedItem();
			tfEditarTipo.setText(Integer.toString(tipoI.getIdHerramienta()));
			tfIdTipo.setText(Integer.toString(tipoI.getIdHerramienta()));
		}

		if(event.getSource().equals(btnAceptarHerramienta)){
			Stage dialogo = new Stage();
			if(tfNombreHerramienta.getText()!=null && tfIdTipo.getText()!=null){
				mHerramienta.agregarHerramienta(Integer.parseInt(tfIdTipo.getText()), tfNombreHerramienta.getText());
				dialogo.setScene(new Scene(new Label("Herramienta Agregada")));
			}else{
				dialogo.setScene(new Scene(new Label("Campo vacio Herramienta")));
			}
			dialogo.show(); 
			tfNombreHerramienta.setText(null);
			tfIdTipo.setText(null);
			ventanaAgregar.close();
			app.volverEditable();
		}
		if(event.getSource().equals(btnEditarTipo)){
			bpBusquedaTip.getChildren().remove(tvTipo);
			tfEditarTipo.setText(null);
			//tfIdTipo.setText(null);
			Herramienta herriSeleccionada = (Herramienta)tvHerramienta.getSelectionModel().getSelectedItem();
			if(herriSeleccionada!=null){
				tpPrincipal.getTabs().add(tbEditarTipo);
				this.setId(herriSeleccionada.getIdHerramientaEspecifico());
			}	
		}
		if(event.getSource().equals(btnBuscarTipoEdicion)){
			tvTipo.setEditable(false);
			bpBusquedaTip.setRight(tvTipo);
		}
		if(event.getSource().equals(btnAceptarEdicionTipo)){
			Stage dialogo = new Stage();
			if(tfEditarTipo.getText()!=null){
				mHerramienta.modificarTipoHerramienta(Integer.parseInt(tfEditarTipo.getText()), this.getId());
				dialogo.setScene(new Scene(new Label("Campo Modificado")));
			}else{
				dialogo.setScene(new Scene(new Label("Campo vacio")));
			}
			dialogo.show(); 
			tfEditarTipo.setText(null);
			tpPrincipal.getTabs().remove(tbEditarTipo);
			app.volverEditable();
			app.alertaDeCambio();
		}
	}
	/**
	*Metodo quita el table view
	*/
	public void quitar(){
		vBoxHerramienta.getChildren().remove(tvHerramienta);
	}
	/**
	*Metodo pone el table view
	*/
	public void poner(){
		vBoxHerramienta.getChildren().add(tvHerramienta);
	}
	/**
	*Metodo selecciona la tab
	*/
	public void seleccionar(){
		tpPrincipal.getSelectionModel().select(tbHerramientaCRUD);
	}
	/**
	*Metodo quita la tab
	*/
	public void remover(){
		tpPrincipal.getTabs().remove(tbHerramientaCRUD);
	}
	/**
      * Este metodo me retorna el id
      * @return id tipoInt
    */
	public int getId(){
		return id;
	}
	/**
      * Este metodo envia el id
      * @param id TipoId Este envia el id
    */
	public void setId(int id){
		this.id=id;
	}
	/**
	*Este metodo retorna la tabla herramienta
	*@return tvHerramienta tipo:TableView
	*/
	public TableView<Herramienta> getTabla(){
		return tvHerramienta;
	}
}




		
		
		
		
		
		
