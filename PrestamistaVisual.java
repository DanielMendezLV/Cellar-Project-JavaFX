package org.mendez.visual;
import org.mendez.ui.App;
import org.mendez.beans.Prestamo;
import org.mendez.beans.DetallePrestamo;
import org.mendez.manejadores.ManejadorDetallePrestamo;
import org.mendez.manejadores.ManejadorPrestamo;
import org.mendez.utilidadesVisuales.AutoCompletar;
import org.mendez.db.Conexion;
import org.mendez.ui.App;
import org.mendez.beans.Profesor;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane;
import javafx.scene.Scene;
import javafx.util.Callback;
import java.text.DateFormat;
import java.time.LocalDate;
import javafx.scene.layout.GridPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.mendez.beans.Herramienta;
import org.mendez.beans.Profesor;
import org.mendez.manejadores.ManejadorTipoHerramienta;
import org.mendez.manejadores.ManejadorHerramienta;
import org.mendez.manejadores.ManejadorUsuario;
import org.mendez.manejadores.ManejadorProfesor;
import org.mendez.utilidades.Fecha;
import org.mendez.beans.TipoHerramienta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
*Esta clase maneja la parte visual del modulo prestamista
*@author Daniel Mendez
*/
public class PrestamistaVisual extends Application implements EventHandler<Event>{
	private Tab tbModuloPrestamista;
	private VBox vBoxPrestamista,vBoxAgregarPrestamo;
	private HBox hBoxPrestamista, hBoxTablayBotonAgregarPrestamo;
	private Button btnVerPrestamos, btnAgregarPrestamo,btnActualizarPrestamo, btnLogoutPrestamista;
	private Button btnCerrar, btnBuscarProfesorPrestamo, btnAceptarAgregarPrestamo, btnAgregarHerramientaPrestamo;
	private TableView<Prestamo> tvPrestamo;
	private Scene sceneAgregarPrestamo;
	private DatePicker pick;
	private LocalDate fechai;
	private Label lblRadioButtonMatutina;
	private Label lblJornada;
	private Label lblIngreso;
	private Label lblFechaDevolucion;
	private Label lblRadioButtonVespertina;
	private Label lblIdProfesor;
	private BorderPane bpAgregarPrestamo;
	private GridPane gpAgregarPrestamo;
	private RadioButton rbMatutina;
	private RadioButton rbVespertina;
	private ToggleGroup grupoRB;
	private TextField tfIdProfesor;
	private ManejadorDetallePrestamo mDetallePrestamo;
	private ManejadorPrestamo mPrestamo;
	private App app;
	private Conexion cnx;
	private String fecha;
	private TabPane tbPrincipal;
	private TableView<DetallePrestamo> tvDetallePrestamo;
	private ObservableList<Herramienta> herramientaAgregarLista;
	private Scene sceneMostrarHerramientasPrestadas;
	private ManejadorTipoHerramienta mTipoHerramienta;
	private ManejadorHerramienta mHerramienta;
	private TableView<Herramienta> tvHerramienta;
	private Boolean estadoVespertino=false;
	private Boolean estadoMatutino=false;
	private Boolean estadoTfId=false;
	private ManejadorUsuario mUsuario;
	private Stage ventanaAgregar;
	private Fecha fechaVerificacion;
	private Boolean estadoVerificacion, boleanoCambioPicker;
	private ManejadorProfesor mProfesor;
	private AutoCompletar autoCompletar;
	/**
	*Este es el constructor de la clase PrestamistaVisual y permite que todo funcione correctamente
	*@param cnx tipo:Conexion
	*@param mDetallePrestamo tipo:ManejadorDetallePrestamo
	*@param mPrestamo tipo:ManejadorPrestamo
	*@param tbPrincipal tipo:TabPane
	*@param app tipo:App
	*@param mTipoHerramienta tipo:ManejadorTipoHerramienta
	*@param mHerramienta tipo:ManejadorHerramienta
	*@param tvHerramienta tipo:TableView
	*@param mUsuario tipo:ManejadorUsuario
	*@param mProfesor tipo:ManejadorProfesor
	*/
	public PrestamistaVisual(Conexion cnx, ManejadorDetallePrestamo mDetallePrestamo, ManejadorPrestamo mPrestamo, TabPane tbPrincipal, App app, 
		ManejadorTipoHerramienta mTipoHerramienta, ManejadorHerramienta mHerramienta, TableView<Herramienta> tvHerramienta
		, ManejadorUsuario mUsuario, ManejadorProfesor mProfesor){
		this.cnx=cnx;
		this.mDetallePrestamo=mDetallePrestamo;
		this.mPrestamo=mPrestamo;
		this.tbPrincipal=tbPrincipal;
		this.app=app;
		this.iniciarObjetos();
		this.asignarEventos();
		this.insertarValoresAlaTabla();
		this.insertarHbox();
		this.insertarVboxYTab();
		this.mTipoHerramienta=mTipoHerramienta;
		this.mHerramienta=mHerramienta;
		this.tvHerramienta=tvHerramienta;
		this.mUsuario=mUsuario;
		this.mProfesor=mProfesor;
	}
	/**
	*Este metodo inicializa los objetos
	*/
	public void iniciarObjetos(){
		tbModuloPrestamista = new Tab("Modulo Prestamista");
		fechaVerificacion = new Fecha();
		vBoxPrestamista = new VBox();
		hBoxPrestamista = new HBox();
		btnVerPrestamos = new Button("Herramientas Prestadas");
		btnAgregarPrestamo = new Button("Agregar");
		btnActualizarPrestamo = new Button("Actualizar");
		btnLogoutPrestamista = new Button("Logout");
		tvPrestamo = new TableView<Prestamo>(mPrestamo.getListaDePrestamos());
		tvPrestamo.setMinSize(800,570);
	}
	/**
	*Este metodo inicializa los objetos al clickear agregar prestamo
	*/
	public void inicializarObjetosAgregarPrestamo(){
		herramientaAgregarLista=FXCollections.observableArrayList();
		lblFechaDevolucion = new Label("Fecha Devolucion");
		lblIdProfesor = new Label("Id Profesor");
		btnBuscarProfesorPrestamo = new Button("Buscar");
		btnAceptarAgregarPrestamo = new Button("Agregar");
		btnAgregarHerramientaPrestamo = new Button("Add");
		lblIngreso = new Label("Ingrese los siguientes datos: ");
		tfIdProfesor = new TextField();//tfIdProfesor.setEditable(false);
		gpAgregarPrestamo = new GridPane();
		pick = new DatePicker(LocalDate.now());
		grupoRB = new ToggleGroup();
		rbMatutina = new RadioButton();rbMatutina.setToggleGroup(grupoRB);
		rbVespertina = new RadioButton();rbVespertina.setToggleGroup(grupoRB);
		lblJornada = new Label("Jornada:");
		lblRadioButtonMatutina = new Label("Matutina");
		lblRadioButtonVespertina = new Label("Vespertina");
		btnCerrar = new Button("Cerrar");
		hBoxTablayBotonAgregarPrestamo = new HBox();
		vBoxAgregarPrestamo = new VBox();
		vBoxAgregarPrestamo.getChildren().add(btnCerrar); vBoxAgregarPrestamo.setMargin(btnCerrar, new Insets(3,3,3,20));
	}
	/**
	*Metodo start esta vacio y sin uso
	*@param primary tipo:Stage
	*/
	public void start(Stage primary){
	}
	/**
	*Este metodo asigna eventos a los botones o datepicker al estar en el stage agregar
	*/
	public void asignarEventosAgregar(){
		btnBuscarProfesorPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregarHerramientaPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAceptarAgregarPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED, this); 
		pick.setOnAction(event -> {
            fechai = pick.getValue();
          	String fech = fechai+"";
          	mPrestamo.setFecha(fech);
          	String fechaArreglada=mPrestamo.getFecha();
          	estadoVerificacion=fechaVerificacion.estadoDeFechas(fechaArreglada);
          	boleanoCambioPicker=true;
        });
        rbVespertina.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		rbMatutina.addEventHandler(MouseEvent.MOUSE_CLICKED, this);	
		btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);	
	}	
	/**
	*Este evento asigna los eventos a los principales botones del modulo
	*/
	public void asignarEventos(){
		btnVerPrestamos.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnActualizarPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnLogoutPrestamista.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregarPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}
	/**
	*Este evento inserta al Hbox principal los botones del modulo
	*/
	public void insertarHbox(){
		hBoxPrestamista.getChildren().addAll(btnAgregarPrestamo, btnActualizarPrestamo, btnVerPrestamos, btnLogoutPrestamista);
		hBoxPrestamista.setMargin(btnVerPrestamos, new Insets(5,5,5,5));
		hBoxPrestamista.setMargin(btnAgregarPrestamo, new Insets(5,5,5,5));
		hBoxPrestamista.setMargin(btnActualizarPrestamo, new Insets(5,5,5,5));
		hBoxPrestamista.setMargin(btnLogoutPrestamista, new Insets(5,5,5,5));
	}
	/**
	*Este metodo inserta valores a la tabla prestamo
	*/		
	public void insertarValoresAlaTabla(){		
		TableColumn<Prestamo, Integer> columnaIdPrestamo=new TableColumn<Prestamo,Integer>("Id Prestamo");
			columnaIdPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, Integer>("idPrestamo"));
			columnaIdPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaFechaPrestamo=new TableColumn<Prestamo,String>("Fecha");
			columnaFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("fecha"));
			columnaFechaPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaHoraPrestamo=new TableColumn<Prestamo,String>("Hora");
			columnaHoraPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("hora"));
			columnaHoraPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaJornadaPrestamo=new TableColumn<Prestamo,String>("Jornada");
			columnaJornadaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("jornada"));
			columnaJornadaPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaUsuarioPrestamo=new TableColumn<Prestamo,String>("Creador");
			columnaUsuarioPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("usuario"));
			columnaUsuarioPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaProfesorPrestamo=new TableColumn<Prestamo,String>("Profesor");
			columnaProfesorPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("profesor"));
			columnaProfesorPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaFechaDevolucionPrestamo=new TableColumn<Prestamo,String>("F. Devolucion");
			columnaFechaDevolucionPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fechaDevolucion"));
			columnaFechaDevolucionPrestamo.setMinWidth(170);
		TableColumn<Prestamo, String> columnaEstadoPrestamo=new TableColumn<Prestamo,String>("Estado");
			columnaEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
			columnaEstadoPrestamo.setMinWidth(170);
		tvPrestamo.getColumns().setAll(columnaIdPrestamo, columnaFechaPrestamo, columnaFechaDevolucionPrestamo, columnaHoraPrestamo,columnaJornadaPrestamo,columnaProfesorPrestamo,columnaUsuarioPrestamo, columnaEstadoPrestamo);
	}
	/**
	*Este metodo inserta el hbox principal y el table view a la tab
	*/
	public void insertarVboxYTab(){
		vBoxPrestamista.getChildren().add(hBoxPrestamista);
		vBoxPrestamista.getChildren().add(tvPrestamo);
		tbModuloPrestamista.setContent(vBoxPrestamista);
	}	
	/**
	*Este metodo inserta los objetos visibles al gridpane de agregar
	*/
	public void agregarPrestamoGridPane(){
		gpAgregarPrestamo.add(lblIngreso, 0,0); gpAgregarPrestamo.setMargin(lblIngreso, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(lblIdProfesor, 0,1); gpAgregarPrestamo.setMargin(lblIdProfesor, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(tfIdProfesor, 1,1); gpAgregarPrestamo.setMargin(tfIdProfesor, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(lblFechaDevolucion, 0,2); gpAgregarPrestamo.setMargin(lblFechaDevolucion, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(pick, 1,2); gpAgregarPrestamo.setMargin(pick, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(lblJornada,0,3); gpAgregarPrestamo.setMargin(lblJornada, new Insets(3,3,3,70));
		gpAgregarPrestamo.add(rbMatutina,1,4); gpAgregarPrestamo.setMargin(rbMatutina, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(lblRadioButtonMatutina,0,4); gpAgregarPrestamo.setMargin(lblRadioButtonMatutina, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(rbVespertina,1,5); gpAgregarPrestamo.setMargin(rbVespertina, new Insets(3,3,3,5));
		gpAgregarPrestamo.add(lblRadioButtonVespertina,0,5); gpAgregarPrestamo.setMargin(lblRadioButtonVespertina, new Insets(3,3,3,5));
		bpAgregarPrestamo = new BorderPane();
		bpAgregarPrestamo.setLeft(gpAgregarPrestamo);
		sceneAgregarPrestamo = new Scene(bpAgregarPrestamo,1300,900);
	}
	/**
	*Este metodo retorna la tab prestamista
	*@return tbModuloPrestamita tipo:Tab
	*/
	public Tab getTabPrestamista(){
		return tbModuloPrestamista;
	}
	/**
	*Metodo handle que crea el evento 
	*@param event tipo:Event
	*/
	public void handle(Event event){
		if(event.getSource().equals(btnActualizarPrestamo)){
			mPrestamo.actualizarListaDePrestamos();
		}
		if(event.getSource().equals(btnVerPrestamos)){
			Prestamo prestamoSelect =(Prestamo)tvPrestamo.getSelectionModel().getSelectedItem();
			if(prestamoSelect!=null){
				mDetallePrestamo.actualizarListaDeDetallePrestamo(prestamoSelect.getIdPrestamo());
				tvDetallePrestamo= new TableView<DetallePrestamo>(mDetallePrestamo.getListaDetallePrestamo(prestamoSelect.getIdPrestamo()));
				TableColumn<DetallePrestamo, Integer> columnaIdPrestamonDetalle=new TableColumn<DetallePrestamo,Integer>("no. Prestamo");
					columnaIdPrestamonDetalle.setCellValueFactory(new PropertyValueFactory<DetallePrestamo, Integer>("idPrestamo"));
					columnaIdPrestamonDetalle.setMinWidth(100);
						
				TableColumn<DetallePrestamo, Integer> columnaIdHerramientaDetalle=new TableColumn<DetallePrestamo,Integer>("id. Herramienta");
					columnaIdHerramientaDetalle.setCellValueFactory(new PropertyValueFactory<DetallePrestamo, Integer>("idHerramienta"));
					columnaIdHerramientaDetalle.setMinWidth(100);
						
				TableColumn<DetallePrestamo, String> columnaSerialPrestamoDetalle=new TableColumn<DetallePrestamo,String>("Serial");
					columnaSerialPrestamoDetalle.setCellValueFactory(new PropertyValueFactory<DetallePrestamo, String>("serial"));
					columnaSerialPrestamoDetalle.setMinWidth(100);
						
				TableColumn<DetallePrestamo, String> columnaNombrePrestamoDetalle=new TableColumn<DetallePrestamo,String>("Nombre");
					columnaNombrePrestamoDetalle.setCellValueFactory(new PropertyValueFactory<DetallePrestamo, String>("tipo"));
					columnaNombrePrestamoDetalle.setMinWidth(100);

				tvDetallePrestamo.getColumns().setAll(columnaIdPrestamonDetalle,columnaIdHerramientaDetalle, columnaSerialPrestamoDetalle, columnaNombrePrestamoDetalle);

				sceneMostrarHerramientasPrestadas = new Scene(tvDetallePrestamo, 400,400);
				Stage prestamoSeleccionado = new Stage();
				prestamoSeleccionado.setScene(sceneMostrarHerramientasPrestadas);
				prestamoSeleccionado.show(); 
			}
		}
		if(event.getSource().equals(btnLogoutPrestamista)){
			app.logout();
		}	
		if(event.getSource().equals(btnAgregarPrestamo)){
			boleanoCambioPicker=false;
			this.inicializarObjetosAgregarPrestamo();
			this.agregarPrestamoGridPane();
			this.asignarEventosAgregar();
			herramientaAgregarLista.clear();
			mTipoHerramienta.actualizarListaDeTipos();
			mHerramienta.actualizarListaDeHerramientas();
			bpAgregarPrestamo.setBottom(hBoxTablayBotonAgregarPrestamo);
			autoCompletar= new AutoCompletar(tfIdProfesor, mProfesor);
			tfIdProfesor.setText(null);
			app.volverEditable();
			estadoTfId=false;
			ventanaAgregar = new Stage();
			this.quitar();
			this.poner();
			hBoxTablayBotonAgregarPrestamo.setMargin(tvHerramienta,new Insets(20,30,120,10));
			hBoxTablayBotonAgregarPrestamo.setMargin(btnAceptarAgregarPrestamo, new Insets(20,3,3,0));
			hBoxTablayBotonAgregarPrestamo.setMargin(btnAgregarHerramientaPrestamo, new Insets(20,3,3,0));
			ventanaAgregar.setScene(sceneAgregarPrestamo);
			ventanaAgregar.show();
			ventanaAgregar.setWidth(900);
			ventanaAgregar.setHeight(660);
		}
		if(event.getSource().equals(rbVespertina)){
			estadoVespertino=true;
			estadoMatutino=false;	
		}
		if(event.getSource().equals(rbMatutina)){
			estadoVespertino=false;
			estadoMatutino=true;
		}
		if(event.getSource().equals(btnAgregarHerramientaPrestamo)){
			Herramienta herramientaSelect = (Herramienta)tvHerramienta.getSelectionModel().getSelectedItem();
			if(herramientaSelect!=null){
				if(herramientaSelect.getEstado()==false){
					int contador=0;
					if(herramientaAgregarLista.size()!=0){
						for(int i=0; i<herramientaAgregarLista.size();i++){
							if(herramientaAgregarLista.get(i).getSerial().equals(herramientaSelect.getSerial())){
									this.crearStages("Herramienta Repetida");
							}else{
								contador++;
								if(contador==herramientaAgregarLista.size()){
									herramientaAgregarLista.add(herramientaSelect);
									mHerramienta.actualizarListaDeHerramientas();
									mTipoHerramienta.actualizarListaDeTipos();
									break;
								}
							}
						}
					}else{
						herramientaAgregarLista.add(herramientaSelect);
					}
				}else{
					this.crearStages("Esta herramienta esta prestada");
				}
			}
		}
		if(event.getSource().equals(btnAceptarAgregarPrestamo)){
			String jornada="vacio";
			if(herramientaAgregarLista.size()!=0){
				if(tfIdProfesor!=null){
					if(estadoVespertino | estadoMatutino){
						if(boleanoCambioPicker==true){
							if(estadoVerificacion==true){
								if(this.dameElId(tfIdProfesor.getText())!=0){
									if(estadoVespertino){
										jornada="Vespertina";
											mPrestamo.agregarPrestamo(jornada, this.dameElId(tfIdProfesor.getText()), mUsuario.getUsuarioAutenticado().getIdUsuario(),herramientaAgregarLista);
											herramientaAgregarLista.clear();
											mHerramienta.actualizarListaDeHerramientas();
											mTipoHerramienta.actualizarListaDeTipos();
											this.crearStages("Prestamo agregado");
											ventanaAgregar.close();
											app.volverEditable();

									}
									if(estadoMatutino){
										jornada="Matutina";
										mPrestamo.agregarPrestamo(jornada,this.dameElId(tfIdProfesor.getText()), mUsuario.getUsuarioAutenticado().getIdUsuario(),herramientaAgregarLista);
										herramientaAgregarLista.clear();
										mHerramienta.actualizarListaDeHerramientas();
										mTipoHerramienta.actualizarListaDeTipos();
										this.crearStages("Prestamo agregado");
										ventanaAgregar.close();
										app.volverEditable();
									}
								}else{
									this.crearStages("El profesor no es valido");
								}
							}else{
								this.crearStages("La esta fecha esta muy atrasada");
							}
						}else{
							this.crearStages("La fecha de hoy no es valida");
						}
					}else{
						this.crearStages("La jornada es invalida");
					}
				}else{
					this.crearStages("El profesor esta vacio");
				}
			}else{
				this.crearStages("No ha agregado Herramientas");
			}
		}
	}
	/**
	*Este evento quita algunos objetos del vbox al agregar prestamo para evitar el error del duplicado
	*/
	public void quitar(){
		hBoxTablayBotonAgregarPrestamo.getChildren().remove(tvHerramienta);
		hBoxTablayBotonAgregarPrestamo.getChildren().remove(btnAceptarAgregarPrestamo);
		hBoxTablayBotonAgregarPrestamo.getChildren().remove(btnAgregarHerramientaPrestamo);
		bpAgregarPrestamo.getChildren().remove(vBoxAgregarPrestamo);
	}
	/**
	*Este evento pone los objetos quitados del vbox al agregar prestamo para evitar el error del duplicado
	*/
	public void poner(){
		hBoxTablayBotonAgregarPrestamo.getChildren().add(tvHerramienta);
		hBoxTablayBotonAgregarPrestamo.getChildren().add(btnAgregarHerramientaPrestamo);
		hBoxTablayBotonAgregarPrestamo.getChildren().add(btnAceptarAgregarPrestamo);
	}
	/**
	*Este metodo crea las advertencias en diferentes stage pasandole un mensaje como parametro
	*@param mensaje tipo:String
	*/
	public void crearStages(String mensaje){
		Stage dialogo = new Stage();
		dialogo.setScene(new Scene(new Label(mensaje)));
		dialogo.setWidth(100);
		dialogo.setHeight(100);
		dialogo.show();
	}
	/**
	*Este metodo remueve la pestaña del modulo prestamista
	*/
	public void remove(){
		tbPrincipal.getTabs().remove(tbModuloPrestamista);
	}
	/**
	*Este metodo manda el nombre del profesor del tfIdProfesor para compararlo en la base de datos
	*@param nombre tipo:String
	*@return id tipo:int
	*/
	public int dameElId(String nombre){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idProfesor FROM Profesor WHERE nombre="+"'"+nombre+"'");
		int id=0;
		try{
			while(resultado.next()){
				id=resultado.getInt("idProfesor");
			}
		}catch(SQLException sql){
			return id;
		}
		return id;
	}
}