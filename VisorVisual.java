package org.mendez.visual;
import org.mendez.beans.Prestamo;
import org.mendez.beans.DetallePrestamo;
import org.mendez.manejadores.ManejadorDetallePrestamo;
import org.mendez.manejadores.ManejadorPrestamo;
import org.mendez.db.Conexion;
import javafx.application.Application;
import org.mendez.ui.App;
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
/**
*Esta clase tiene el modulo visor visual 
*@author Daniel Mendez
*/
public class VisorVisual extends Application implements EventHandler<Event>{
	private Tab tbModuloVisor;
	private VBox vBoxVisor;
	private Conexion cnx;
	private HBox hboxPrestamoVisor;
	private Button btnEntregado;
	private Button btnActualizarVisorPrestamo;
	private Button btnLogoutVisor;
	private Button btnVerHerramientasPrestadasVisor;
	private TableView<DetallePrestamo> tvDetallePrestamo;
	private TableView<Prestamo> tvPrestamoClon;
	private ManejadorDetallePrestamo mDetallePrestamo;
	private ManejadorPrestamo mPrestamo;
	private Scene sceneMostrarHerramientasPrestadas;
	private TabPane tbPrincipal;
	private App app;
	/**
	*Este es el metodo constructor que manda la instruccion de inicializar y dar la copia por referencia de los objetos
	*@param cnx tipo:cnx
	*@param mDetallePrestamo tipo:ManejadorDetallePrestamo
	*@param mPrestamo tipo:ManejadorPrestamo
	*@param tbPrincipal tipo:TabPane
	*@param app tipo:App
	*/
	public VisorVisual(Conexion cnx, ManejadorDetallePrestamo mDetallePrestamo, ManejadorPrestamo mPrestamo, TabPane tbPrincipal, App app){
		this.cnx=cnx;
		this.mDetallePrestamo=mDetallePrestamo;
		this.mPrestamo=mPrestamo;
		this.tbPrincipal=tbPrincipal;
		this.app=app;
		this.iniciarVariables();
		this.asignarValoresTableView();
		this.insertarAlHbox();
		this.insertarAlVbox();
	}
	/**
	*Este metodo es el start de javafx
	*@param primary tipo:Stage
	*/
	public void start(Stage primary){
	}
	/**
	*Este metodo inicializa las variables
	*/
	public void iniciarVariables(){
		tbModuloVisor = new Tab("Visor");
		tbModuloVisor.setClosable(false);
		vBoxVisor = new VBox();
		hboxPrestamoVisor = new HBox();
		btnEntregado = new Button("Entregado");
		btnActualizarVisorPrestamo = new Button("Actualizar");
		btnVerHerramientasPrestadasVisor = new Button("Ver herramientas");
		btnLogoutVisor = new Button("Logout");
		tvPrestamoClon = new TableView<Prestamo>(mPrestamo.getListaDePrestamosPendientes());
		tvPrestamoClon.setMinSize(800,570);
		btnVerHerramientasPrestadasVisor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnActualizarVisorPrestamo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnEntregado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnLogoutVisor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}
	/**
	*Este metodo inserta los datos al hbox
	*/
	public void insertarAlHbox(){
		hboxPrestamoVisor.getChildren().add(btnEntregado);
		hboxPrestamoVisor.getChildren().add(btnActualizarVisorPrestamo);
		hboxPrestamoVisor.getChildren().add(btnVerHerramientasPrestadasVisor);
		hboxPrestamoVisor.getChildren().add(btnLogoutVisor);
		hboxPrestamoVisor.setMargin(btnEntregado, new Insets(5,5,10,5));
		hboxPrestamoVisor.setMargin(btnLogoutVisor, new Insets(5,5,10,5));
		hboxPrestamoVisor.setMargin(btnVerHerramientasPrestadasVisor, new Insets(5,5,10,5));
		hboxPrestamoVisor.setMargin(btnActualizarVisorPrestamo, new Insets(5,5,10,5));
	}	
	/**
	*Este metodo inserta los datos al vbox
	*/
	public void insertarAlVbox(){
		vBoxVisor.getChildren().add(hboxPrestamoVisor);
		vBoxVisor.getChildren().add(tvPrestamoClon);
		vBoxVisor.setMargin(tvPrestamoClon, new Insets(5,5,10,5));
		tbModuloVisor.setContent(vBoxVisor);
	}
	/**Este metodo asigna los valores al tableview de PrestamoClon*/
	public void asignarValoresTableView(){
		TableColumn<Prestamo, Integer> columnaIdPrestamoClon=new TableColumn<Prestamo,Integer>("Id Prestamo");
			columnaIdPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, Integer>("idPrestamo"));
			columnaIdPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaFechaPrestamoClon=new TableColumn<Prestamo,String>("Fecha");
			columnaFechaPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("fecha"));
			columnaFechaPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaHoraPrestamoClon=new TableColumn<Prestamo,String>("Hora");
			columnaHoraPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("hora"));
			columnaHoraPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaJornadaPrestamoClon=new TableColumn<Prestamo,String>("Jornada");
			columnaJornadaPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("jornada"));
			columnaJornadaPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaUsuarioPrestamoClon=new TableColumn<Prestamo,String>("Creador");
			columnaUsuarioPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("usuario"));
			columnaUsuarioPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaProfesorPrestamoClon=new TableColumn<Prestamo,String>("Profesor");
			columnaProfesorPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("profesor"));
			columnaProfesorPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaFechaDevolucionPrestamoClon=new TableColumn<Prestamo,String>("F. Devolucion");
			columnaFechaDevolucionPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fechaDevolucion"));
			columnaFechaDevolucionPrestamoClon.setMinWidth(170);
		TableColumn<Prestamo, String> columnaEstadoPrestamoClon=new TableColumn<Prestamo,String>("Estado");
			columnaEstadoPrestamoClon.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
			columnaEstadoPrestamoClon.setMinWidth(170);	
		tvPrestamoClon.getColumns().setAll(columnaIdPrestamoClon, columnaFechaPrestamoClon, columnaFechaDevolucionPrestamoClon, columnaHoraPrestamoClon,columnaJornadaPrestamoClon,columnaProfesorPrestamoClon,columnaUsuarioPrestamoClon, columnaEstadoPrestamoClon);
		columnaEstadoPrestamoClon.setCellFactory(new Callback<TableColumn<Prestamo, String>, TableCell<Prestamo, String>>(){
				@Override public TableCell<Prestamo,String> call(TableColumn<Prestamo,String>columnaEstadoPrestamo){
					return new TableCell<Prestamo,String>(){
						@Override public void updateItem(String item, boolean empty){
						super.updateItem(item, empty);
						if(item!=null){
							setText(item);
							if(item.equals("Fuera de Fecha")){
								if(getTableRow() !=null){
									this.getTableRow().setStyle("-fx-background-color:#FF4A4A");
								}
							}
							if(item.equals("Pendiente")){
								if(getTableRow() !=null){
									this.getTableRow().setStyle("-fx-background-color:#0095D9");
								}
							}
							if(item.equals("Entregado")){
								if(getTableRow() !=null){
									this.getTableRow().setStyle("-fx-background-color:#88C863");
								}
							}
						}
					}
				};
			}
		});

	}
	/**
	*Este metodo envia los eventos
	*@param event tipo:Event
	*/
	public void handle(Event event){
		if(event.getSource().equals(btnActualizarVisorPrestamo)){
					mPrestamo.actualizarListaDePrestamos();
		}
		if(event.getSource().equals(btnEntregado)){
			Prestamo prestamoSelectClon =(Prestamo)tvPrestamoClon.getSelectionModel().getSelectedItem();
			if(prestamoSelectClon!=null){
				mPrestamo.cambiarEstadoEntregado(prestamoSelectClon.getIdPrestamo());
			}
		}
		if(event.getSource().equals(btnVerHerramientasPrestadasVisor)){
			Prestamo prestamoSelectClon =(Prestamo)tvPrestamoClon.getSelectionModel().getSelectedItem();
			if(prestamoSelectClon!=null){
						
				mDetallePrestamo.actualizarListaDeDetallePrestamo(prestamoSelectClon.getIdPrestamo());
				tvDetallePrestamo= new TableView<DetallePrestamo>(mDetallePrestamo.getListaDetallePrestamo(prestamoSelectClon.getIdPrestamo()));	
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
		if(event.getSource().equals(btnLogoutVisor)){
			app.logout();
		}
	}	
	/**Este metodo manda a remover a app el tbVisor*/
	public void remove(){
		tbPrincipal.getTabs().remove(tbModuloVisor);
	}
	/**Este metodo devuelve el table view
	*@return tbModuloVisor tipo:Tab
	*/
	public Tab getTabVisor(){
		return tbModuloVisor;
	}
}