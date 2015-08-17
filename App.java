package org.mendez.ui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.time.LocalDate;
import org.mendez.db.Conexion;
import org.mendez.beans.Usuario;
import org.mendez.beans.Categoria;
import org.mendez.beans.Profesor;
import org.mendez.beans.TipoHerramienta;
import org.mendez.beans.Prestamo;
import org.mendez.beans.Herramienta;
import org.mendez.manejadores.ManejadorCategoria;
import org.mendez.manejadores.ManejadorProfesor;
import org.mendez.manejadores.ManejadorUsuario;
import org.mendez.manejadores.ManejadorTipoHerramienta;
import org.mendez.manejadores.ManejadorHerramienta;
import org.mendez.manejadores.ManejadorPrestamo;
import org.mendez.manejadores.ManejadorDetallePrestamo;
import org.mendez.beans.DetallePrestamo;
import org.mendez.visual.VisualHerramienta;
import org.mendez.visual.VisorVisual;
import java.text.DateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.stage.StageStyle;
import org.mendez.visual.PrestamistaVisual;
/**
* La clase app se encarga de la parte grafica
* @author Daniel Mendez
*/
public class App extends Application implements EventHandler<Event>{
	private Scene primaryScene, sceneAgregarUsuario, sceneAgregarCategoria, sceneAgregarProfesor, sceneAgregarTipo, sceneAgregarHerramienta, sceneAgregarPrestamo, sceneMostrarHerramientasPrestadas;
	private TabPane tpPrincipal;
	private BorderPane bPbusquedaCategoria, bPEditarCategoria, bPbusquedaTipo, bpBusquedaTip, bpAgregarPrestamo;
	private Tab tbLogin, tbCRUD, tbCrudUsuario;//tbContactos;
	private Tab tbModuloPrestamista;
	private Button btnUsuario,btnEliminar, btnActualizar, btnUsuarioX, btnAgregar, btnAceptar, btnLogout,btnCategoria, btnProfesor, btnHerramienta;
	private Button btnEliminarCat, btnActualizarCat, btnAgregarCat, btnAceptarCategoria;
	private Button btnEliminarProfesor, btnActualizarProfesor, btnAgregarProfesor, btnAceptarProfesor;
	private Button btnBuscarC,btnAceptarEdicion;
	private Button btnEliminarTipo, btnActualizarTipo, btnAgregarTipo, btnAceptarTipo, btnTipos, btnBuscarCategoria, btnEditarCategoria;
	private Button btnEliminarHerramienta, btnActualizarHerramienta, btnAgregarHerramienta, btnBuscarTipo, btnAceptarHerramienta, btnEditarTipo, btnAceptarEdicionTipo, btnBuscarTipoEdicion;
	private TextField tfNombre, tfNombreUsuario, tfApellidoUsuario, tfNicknameUsuario, tfPasswordUsuario, tfNombreCategoria;
	private TextField tfClaseProfesor, tfNombreProfesor;
	private TextField tfNombreTipo, tfIdCategoria, tfNuevaCategoria, tfNombreHerramienta, tfIdTipo, tfEditarTipo;
	private PasswordField pfContra;
	private Label lblNombre, lblPasword,lblProfesor, lblNickname, lblContra, lblApellido, lblPresentacion, lblIngreso, lblN, lblCrud, lblVisor, lblPrestamista, lblPermisos;
	private Label lblNombreUsuarioA, lblClase, lblCategoria, lblNuevaCategoria, lblHerramienta, lblTipo, lblEditarTipo, lblCat, lblIdProfesor;
	private Conexion cnx;
	private Conexion conect;
	private CheckBox chkCrud, chkVisor, chkPrestamista;
	private ManejadorHerramienta mHerramienta;
	private HBox hBoxModificaciones, hbxModulos, hBoxCategoria, hBoxProfesor, hBoxTipos, hBoxHerramienta,hBoxPrestamista;
	private VBox vBoxTablayHbox, vBoxTablaCategoria, vBoxTablaProfesor, vBoxTablaTipos, vBoxHerramienta,vBoxAgregarPrestamo,vBoxPrestamista;
	private TableView<Usuario> tvUsuario;
	private GridPane gpLogin, gpUsuario, gpAgregarUsuario, gpAgregarCategoria, gpAgregarProfesor, gpAgregarTipo, gpEditarCategoria, gpAgregarHerramienta, gpEditarTipo, gpAgregarPrestamo;
	private ManejadorUsuario mUsuario;
	private ManejadorCategoria mCategoria;
	private ManejadorTipoHerramienta mTipoHerramienta;
	private TableView<Categoria> tvCategoria;
	private TableView<Profesor> tvProfesor;
	private TableView<TipoHerramienta> tvTipo;
	private TableView<Herramienta> tvHerramienta;
	private Tab tbCategoriaCRUD, tbProfesorCRUD, tbTiposCRUD, tbEditarCategoria, tbHerramientaCRUD, tbEditarTipo;
	private ManejadorProfesor mProfesor;
	private ManejadorPrestamo mPrestamo;
	boolean estado;
	boolean estadoTipo, estadoEditarTipoHerramienta, estadoProf, estadoVespertino, estadoMatutino;
	private int id;
	private TextField tfIdProfesor;
	private ToggleGroup grupoRB;
	private HBox hBoxTablayBotonAgregarPrestamo;
	private ManejadorDetallePrestamo mDetallePrestamo;
	private VisorVisual visorVisual;
	private PrestamistaVisual prestamistaVisual;
	private VisualHerramienta visualHerramienta;
	/**
      * start este metodo inicia la interfaz grafica y los respectivos manejadores y conexion
      * @param primaryStage Stage: Este es la stage primaria
    */
	public void start(Stage primaryStage){
		cnx = new Conexion();
		mCategoria = new ManejadorCategoria(cnx);
		mUsuario = new ManejadorUsuario(cnx);
		mProfesor = new ManejadorProfesor(cnx);
		mTipoHerramienta = new ManejadorTipoHerramienta(cnx);
		mDetallePrestamo = new ManejadorDetallePrestamo(cnx);
		mPrestamo = new ManejadorPrestamo(cnx, mDetallePrestamo);
		tpPrincipal = new TabPane();
		tbLogin = new Tab("Login");
		gpLogin = new GridPane();
		tfNombre = new TextField();
		tfNombre.setPromptText("nickname");
		tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
		pfContra = new PasswordField();
		pfContra.setPromptText("clave de usuario");
		pfContra.addEventHandler(KeyEvent.KEY_RELEASED, this);
		lblN = new Label("Nickname :");
		lblNickname = new Label("Nickname: ");
		lblN.setId("lblNickname");
		lblContra = new Label("Clave: ");
		lblContra.setId("lblContra");
		gpLogin.add(lblN, 0,0);
		gpLogin.add(tfNombre, 1, 0);
		gpLogin.add(lblContra, 0,1);
		gpLogin.add(pfContra, 1, 1);
		gpLogin.setMargin(lblN, new Insets (80,5,5,150));
		gpLogin.setMargin(lblContra, new Insets (10,10,5,150));
		gpLogin.setMargin(tfNombre, new Insets (80,5,5,20));
		gpLogin.setMargin(pfContra, new Insets (10,10,5,20));
		tbLogin.setContent(gpLogin);

		//Modulo CRUD
		tbCRUD = new Tab("CRUD");
		btnLogout = new Button("Logout");
		lblPresentacion= new Label("A que menu desea acceder:");
		lblPresentacion.setId("lblPresentacion");
		btnUsuarioX = new Button("Usuario");
		btnCategoria = new Button("Categoria");
		btnProfesor = new Button("Profesor");
		btnTipos = new Button("Nombre General Herramienta");
		btnHerramienta = new Button("Herramienta Especifica");
		btnHerramienta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnCategoria.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnUsuarioX.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnProfesor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnTipos.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		gpUsuario= new GridPane();
		gpUsuario.setMargin(lblPresentacion, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnUsuarioX, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnProfesor, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnTipos, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnLogout, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnCategoria, new Insets(10,0,10,10));
		gpUsuario.setMargin(btnHerramienta, new Insets(10,0,10,10));
		gpUsuario.add(lblPresentacion, 0,0);
		gpUsuario.add(btnUsuarioX, 0,2);
		gpUsuario.add(btnCategoria, 0,3);
		gpUsuario.add(btnProfesor, 0,4);
		gpUsuario.add(btnTipos, 0,5);
		gpUsuario.add(btnLogout, 1,2);
		gpUsuario.add(btnHerramienta, 0,6);
		tbCRUD.setContent(gpUsuario);
		//CRUD Usuario
		tvUsuario = new TableView<Usuario>(mUsuario.getListaDeUsuarios());
		tvUsuario.setEditable(true);
		TableColumn<Usuario, String> columnaNombre=new TableColumn<Usuario, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
		columnaNombre.setMinWidth(120);
		TableColumn<Usuario, String> columnaApellido=new TableColumn<Usuario, String>("Apellido");
		columnaApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
		columnaApellido.setMinWidth(120);
		TableColumn<Usuario, String> columnaNickname=new TableColumn<Usuario, String>("Nickname");
		columnaNickname.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nickname"));
		columnaNickname.setMinWidth(150);
		TableColumn<Usuario, String> columnaPassword=new TableColumn<Usuario, String>("Password");
		columnaPassword.setCellValueFactory(new PropertyValueFactory<Usuario, String>("pasword"));
		columnaPassword.setMinWidth(150);
		tvUsuario.getColumns().setAll(columnaNombre, columnaApellido, columnaNickname, columnaPassword);
		tbCrudUsuario = new Tab("Usuarios");
		tbCrudUsuario.setContent(tvUsuario);
		columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaApellido.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNickname.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaPassword.setCellFactory(TextFieldTableCell.forTableColumn());
	  /**
      * Edita el valor de la columnaNombre o permite su edicion
      * @param EventHandler<CellEditEvent<Usuario,String>>
      */
		columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Usuario, String>>(){
		@Override
			public void handle(CellEditEvent<Usuario, String> eventoEditar) {
					if(eventoEditar.getNewValue()!=(eventoEditar.getOldValue())){
						Usuario usuario = (Usuario)tvUsuario.getSelectionModel().getSelectedItem();
						mUsuario.modificarNombre(usuario.getIdUsuario(),eventoEditar.getNewValue());
						//((Contacto)eventoEditar.getTableView().getItems().get(eventoEditar.getTablePosition().getRow())).setNombre(t.getNewValue());
					}	
				}
			}
		);

	  /**
      * Edita el valor de la columnaApellido o permite su edicion
      * @param EventHandler<CellEditEvent<Usuario,String>>
      */
		columnaApellido.setOnEditCommit(new EventHandler<CellEditEvent<Usuario,String>>(){
		@Override
			public void handle(CellEditEvent<Usuario,String> eventoEditar){
				if(eventoEditar.getNewValue()!=(eventoEditar.getOldValue())){
					Usuario usuario = (Usuario)tvUsuario.getSelectionModel().getSelectedItem();
					mUsuario.modificarApellido(usuario.getIdUsuario(), eventoEditar.getNewValue());
				}
			}
		}
		);
	  /**
      * Edita el valor de la columnaNickname o permite su edicion
      * @param EventHandler<CellEditEvent<Usuario,String>>
      */
		columnaNickname.setOnEditCommit(new EventHandler<CellEditEvent<Usuario,String>>(){
		@Override
			public void handle(CellEditEvent<Usuario,String> eventoEditar){
				if(eventoEditar.getNewValue()!=(eventoEditar.getOldValue())){
					Usuario usuario = (Usuario)tvUsuario.getSelectionModel().getSelectedItem();
					mUsuario.modificarNickname(usuario.getIdUsuario(), eventoEditar.getNewValue());
				}
			}
		}
		);
    	/**
      * Edita el valor de la columnaPasword o permite su edicion
      * @param EventHandler<CellEditEvent<Usuario,String>>
      */
		columnaPassword.setOnEditCommit(new EventHandler<CellEditEvent<Usuario,String>>(){
		@Override
			public void handle(CellEditEvent<Usuario,String> eventoEditar){
				if(eventoEditar.getNewValue()!=(eventoEditar.getOldValue())){
					Usuario usuario = (Usuario)tvUsuario.getSelectionModel().getSelectedItem();
					mUsuario.modificarPassword(usuario.getIdUsuario(), eventoEditar.getNewValue());
				}
			}
		}
		);
		tpPrincipal.getTabs().add(tbLogin);
		vBoxTablayHbox= new VBox();
		hBoxModificaciones= new HBox();
		btnEliminar= new Button("Delete");
		btnActualizar = new Button("Actualizar");
		btnAgregar = new Button("Agregar");
		btnEliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnActualizar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnLogout.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		//Agreo el eliminar y actualizar al hboxmodificar
		hBoxModificaciones.getChildren().add(btnEliminar);
		hBoxModificaciones.getChildren().add(btnActualizar);
		hBoxModificaciones.getChildren().add(btnAgregar);
		//margenes de los botones
		hBoxModificaciones.setMargin(btnEliminar, new Insets(0,5,0,0));
		hBoxModificaciones.setMargin(btnActualizar, new Insets(0,5,0,0));
		vBoxTablayHbox.getChildren().add(hBoxModificaciones);
		vBoxTablayHbox.getChildren().add(tvUsuario);
		vBoxTablayHbox.setMargin(hBoxModificaciones, new Insets(10,10,20,10));
		vBoxTablayHbox.setMargin(tvUsuario,new Insets(10,10,20,10));
		tbCrudUsuario.setContent(vBoxTablayHbox);
		primaryScene = new Scene(tpPrincipal,400,300);
		//------------------------------------------------------
		//Aqui es lo de ingreso de usuario
		tfPasswordUsuario = new TextField();
		tfNicknameUsuario = new TextField();
		tfApellidoUsuario= new TextField();
		tfNombreUsuario = new TextField();
		//Aqui es el prompt
		tfPasswordUsuario.setPromptText("Pasword");
		tfNicknameUsuario.setPromptText("Nickname");
		tfApellidoUsuario.setPromptText("Apellido");
		tfNombreUsuario.setPromptText("Nombre");
		//Labels
		lblApellido = new Label("Apellido:");
		lblPasword = new Label("Pasword:");
		lblNombre = new Label("Nombre: ");
		//Aqui van los checkbox para la eleccion de modulos con sus labels
		chkCrud = new CheckBox();
		chkPrestamista = new CheckBox();
		chkVisor = new CheckBox();
		lblCrud = new Label("CRUD");
		lblVisor = new Label("Visor");
		lblPrestamista = new Label("Prestamista");
		lblPermisos = new Label("Permisos:");
		hbxModulos = new HBox();
		hbxModulos.getChildren().addAll(lblCrud, chkCrud, lblVisor, chkVisor, lblPrestamista, chkPrestamista);
		//Aqui el gridpane para los textos
		gpAgregarUsuario = new GridPane();
		lblNombreUsuarioA = new Label("Nombre:");
		btnAceptar = new Button("Aceptar");
		btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		lblIngreso = new Label("Ingrese los siguientes datos:");
		gpAgregarUsuario.add(lblIngreso,0,4);
		gpAgregarUsuario.add(lblNombreUsuarioA, 0,6);
		gpAgregarUsuario.add(tfNombreUsuario, 1,6);
		gpAgregarUsuario.add(lblApellido, 0,8);
		gpAgregarUsuario.add(tfApellidoUsuario, 1,8);
		gpAgregarUsuario.add(lblPasword, 0,12);
		gpAgregarUsuario.add(tfPasswordUsuario, 1,12);
		gpAgregarUsuario.add(lblNickname,0,10);
		gpAgregarUsuario.add(tfNicknameUsuario, 1,10);
		gpAgregarUsuario.add(hbxModulos, 1,14);
		gpAgregarUsuario.add(lblPermisos, 0,14);
		gpAgregarUsuario.add(btnAceptar, 1,16);
		gpAgregarUsuario.setMargin(lblIngreso, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(tfNombreUsuario, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(tfNicknameUsuario, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(tfPasswordUsuario, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(tfApellidoUsuario, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(btnAceptar, new Insets(10,10,20,10));
		gpAgregarUsuario.setMargin(lblNombre, new Insets(3,3,3,5));
		gpAgregarUsuario.setMargin(lblNombreUsuarioA, new Insets(10,10,10,7));
		gpAgregarUsuario.setMargin(lblPasword, new Insets(3,3,3,5));
		gpAgregarUsuario.setMargin(lblNickname, new Insets(3,3,3,5));
		gpAgregarUsuario.setMargin(lblApellido, new Insets(3,3,3,5));
		lblPermisos.setPadding(new Insets(3,3,3,5));
		//Aqui el crud categoria
		lblCategoria = new Label("Categoria: ");
		mCategoria.actualizarListaDeCategorias();
		tvCategoria= new TableView<Categoria>(mCategoria.getListaDeCategorias());
		tvCategoria.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		tvCategoria.setEditable(true);
		TableColumn<Categoria, Integer> columnaId=new TableColumn<Categoria,Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("idCategoria"));
		columnaId.setMinWidth(100);
		TableColumn<Categoria, String> columnaCategoria=new TableColumn<Categoria, String>("Categoria");
		columnaCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombre"));
		columnaCategoria.setMinWidth(150);
		tvCategoria.getColumns().setAll(columnaId,columnaCategoria);
		columnaId.setId("columaId");
		columnaCategoria.setId("columnaCategoria");	
		columnaCategoria.setCellFactory(TextFieldTableCell.forTableColumn());
		/**
      * Edita el valor de la columnaCategoria tipo categoria
      * @param EventHandler<CellEditEvent<Categoria,String>>
      */
		columnaCategoria.setOnEditCommit(new EventHandler<CellEditEvent<Categoria, String>>(){
			@Override
				public void handle(CellEditEvent<Categoria, String> eventoEdi) {
					if(eventoEdi.getNewValue()!=(eventoEdi.getOldValue())){
						Categoria cater = (Categoria)tvCategoria.getSelectionModel().getSelectedItem();
						mCategoria.modificarNombre(cater.getIdCategoria(),eventoEdi.getNewValue());
						//((Contacto)eventoEditar.getTableView().getItems().get(eventoEditar.getTablePosition().getRow())).setNombre(t.getNewValue());
					}	
				}
			}
		);
		tbCategoriaCRUD = new Tab("Categoria");
		vBoxTablaCategoria = new VBox();
		hBoxCategoria = new HBox();
		btnEliminarCat= new Button("Delete");
		btnActualizarCat = new Button("Actualizar");
		btnAgregarCat = new Button("Agregar");
		btnEliminarCat.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnActualizarCat.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregarCat.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		//Agreo el eliminar y actualizar al hboxmodificar
		hBoxCategoria.getChildren().add(btnEliminarCat);
		hBoxCategoria.getChildren().add(btnActualizarCat);
		hBoxCategoria.getChildren().add(btnAgregarCat);
		vBoxTablaCategoria.getChildren().add(hBoxCategoria);
		lblCat = new Label("Categoria");
		gpAgregarCategoria = new GridPane();
		btnAceptarCategoria = new Button("Aceptar");
		btnAceptarCategoria.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		gpAgregarCategoria.add(lblIngreso,0,1);
		gpAgregarCategoria.add(lblCat, 0,3);
		tfNombreCategoria = new TextField();
		gpAgregarCategoria.add(tfNombreCategoria, 1,3);
		gpAgregarCategoria.add(btnAceptarCategoria, 1,4);
		gpAgregarCategoria.setMargin(lblIngreso, new Insets(10,10,10,10));
		gpAgregarCategoria.setMargin(tfNombreCategoria, new Insets(10,10,10,10));
		gpAgregarCategoria.setMargin(lblCat, new Insets(10,10,10,10));
		gpAgregarCategoria.setMargin(btnAceptarCategoria, new Insets(10,10,10,10));
		gpAgregarUsuario.setMargin(btnAceptar, new Insets(10,10,20,10));
		gpAgregarUsuario.setMargin(lblNombre, new Insets(3,3,3,5));
		hBoxCategoria.setMargin(btnEliminarCat, new Insets(5,5,10,5));
		hBoxCategoria.setMargin(btnActualizarCat, new Insets(5,5,10,5));
		hBoxCategoria.setMargin(btnAgregarCat, new Insets(5,5,10,5));
		tbCategoriaCRUD.setContent(vBoxTablaCategoria);
		sceneAgregarUsuario = new Scene(gpAgregarUsuario, 400,300);
		sceneAgregarCategoria = new Scene(gpAgregarCategoria, 400,200);
		//Aca el crud profesor
		tbProfesorCRUD = new Tab("Profesor");
		mProfesor.actualizarListaDeProfesores();
		tvProfesor= new TableView<Profesor>(mProfesor.getListaDeProfesores());
		tvProfesor.setEditable(true);
		TableColumn<Profesor, Integer> columnaIdProfesor=new TableColumn<Profesor,Integer>("Id");
		columnaIdProfesor.setCellValueFactory(new PropertyValueFactory<Profesor, Integer>("idProfesor"));
		columnaId.setMinWidth(100);
		TableColumn<Profesor, String> columnaNombreProfesor=new TableColumn<Profesor, String>("Nombre");
		columnaNombreProfesor.setCellValueFactory(new PropertyValueFactory<Profesor, String>("nombre"));
		columnaNombreProfesor.setMinWidth(100);
		TableColumn<Profesor, String> columnaClaseProfesor=new TableColumn<Profesor, String>("Clase");
		columnaClaseProfesor.setCellValueFactory(new PropertyValueFactory<Profesor, String>("clase"));
		columnaClaseProfesor.setMinWidth(100);
		columnaNombreProfesor.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaClaseProfesor.setCellFactory(TextFieldTableCell.forTableColumn());
		/**
      * Edita el valor de la columnaNombreProfesor tipo profesor
      * @param EventHandler<CellEditEvent<Profesor,String>>
      */
		columnaNombreProfesor.setOnEditCommit(new EventHandler<CellEditEvent<Profesor,String>>(){
			@Override
				public void handle(CellEditEvent<Profesor, String> eventoE){
					if(eventoE.getNewValue()!=(eventoE.getOldValue())){
						Profesor prof = (Profesor)tvProfesor.getSelectionModel().getSelectedItem();
						mProfesor.modificarNombre(prof.getIdProfesor(),eventoE.getNewValue());
						//((Contacto)eventoEditar.getTableView().getItems().get(eventoEditar.getTablePosition().getRow())).setNombre(t.getNewValue());
					}	
				}
			}
		);
		/**
      * Edita el valor de la columnaClaseProfesor tipo profesor
      * @param EventHandler<CellEditEvent<Profesor,String>>
      */
		columnaClaseProfesor.setOnEditCommit(new EventHandler<CellEditEvent<Profesor, String>>(){
			@Override
				public void handle(CellEditEvent<Profesor, String> eventoE){
					if(eventoE.getNewValue()!=(eventoE.getOldValue())){
						Profesor prof = (Profesor)tvProfesor.getSelectionModel().getSelectedItem();
						mProfesor.modificarClase(prof.getIdProfesor(),eventoE.getNewValue());
					}	
				}
			}
		);
		tvProfesor.getColumns().setAll(columnaIdProfesor,columnaNombreProfesor, columnaClaseProfesor);			
		vBoxTablaProfesor = new VBox();
		hBoxProfesor = new HBox();
		btnEliminarProfesor= new Button("Delete");
		btnActualizarProfesor = new Button("Actualizar");
		btnAgregarProfesor = new Button("Agregar");
		btnEliminarProfesor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnActualizarProfesor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregarProfesor.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		//Agreo el eliminar y actualizar al hboxmodificar
		hBoxProfesor.getChildren().add(btnEliminarProfesor);
		hBoxProfesor.getChildren().add(btnActualizarProfesor);
		hBoxProfesor.getChildren().add(btnAgregarProfesor);
		vBoxTablaProfesor.getChildren().add(hBoxProfesor);
		vBoxTablaProfesor.getChildren().add(tvProfesor);
		tbProfesorCRUD.setContent(vBoxTablaProfesor);
		hBoxProfesor.setMargin(btnEliminarProfesor, new Insets(5,5,10,5));
		hBoxProfesor.setMargin(btnActualizarProfesor, new Insets(5,5,10,5));
		hBoxCategoria.setMargin(btnAgregarProfesor, new Insets(5,5,10,5));
		//---------Al agregar---------------------
		gpAgregarProfesor = new GridPane();
		lblClase = new Label("Clase:");
		lblProfesor = new Label("Profesor:");
		btnAceptarProfesor = new Button("Aceptar");
		btnAceptarProfesor.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		gpAgregarProfesor.add(lblIngreso,0,1);
		gpAgregarProfesor.add(lblProfesor, 0,3);
		gpAgregarProfesor.add(lblClase,0,6);
		tfNombreProfesor = new TextField();
		tfClaseProfesor = new TextField();
		gpAgregarProfesor.add(tfNombreProfesor, 1,3);
		gpAgregarProfesor.add(tfClaseProfesor, 1,6);
		gpAgregarProfesor.add(btnAceptarProfesor, 0,8);
		sceneAgregarProfesor = new Scene(gpAgregarProfesor,330,220);
		tfNombreProfesor.setPromptText("Nombre Profesor");
		tfClaseProfesor.setPromptText("Clase");
		gpAgregarProfesor.setMargin(lblClase, new Insets(3,3,3,5));
		gpAgregarProfesor.setMargin(tfNombreProfesor, new Insets(3,3,3,5));
		gpAgregarProfesor.setMargin(tfClaseProfesor, new Insets(3,3,3,5));
		gpAgregarProfesor.setMargin(btnAceptarProfesor, new Insets(3,3,3,5));
		gpAgregarProfesor.setMargin(lblProfesor, new Insets(3,3,3,5));
		//-------------CRUD TIPOS-------------------------
		hBoxTipos = new HBox();
		vBoxTablaTipos = new VBox();
		btnAceptarTipo = new Button ("Aceptar");
		btnAgregarTipo = new Button("Agregar");
		btnEliminarTipo = new Button("Eliminar");
		btnActualizarTipo = new Button("Actualizar");
		btnBuscarCategoria = new Button("Buscar...");
		btnEditarCategoria = new Button("Editar Cat");
		btnEditarCategoria.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnBuscarCategoria.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		btnAceptarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnAgregarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnActualizarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnEliminarTipo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		tbTiposCRUD = new Tab("N. General");
 		hBoxTipos.getChildren().addAll(btnAgregarTipo, btnEliminarTipo, btnActualizarTipo, btnEditarCategoria);
		hBoxTipos.setMargin(btnEliminarTipo, new Insets(5,5,10,5));
		hBoxTipos.setMargin(btnActualizarTipo, new Insets(5,5,10,5));
		hBoxTipos.setMargin(btnAgregarTipo, new Insets(5,5,10,5));
		hBoxTipos.setMargin(btnEditarCategoria, new Insets(5,5,10,5));
		vBoxTablaTipos.getChildren().add(hBoxTipos);
		tvTipo = new TableView<TipoHerramienta>(mTipoHerramienta.getListaDeTipos());
		tvTipo.setEditable(true);		
		TableColumn<TipoHerramienta, Integer> columnaIdTipo=new TableColumn<TipoHerramienta,Integer>("IdTipo");
		columnaIdTipo.setCellValueFactory(new PropertyValueFactory<TipoHerramienta, Integer>("idHerramienta"));
		columnaIdTipo.setMinWidth(100);
		TableColumn<TipoHerramienta, String> columnaNombreTipo=new TableColumn<TipoHerramienta,String>("Nombre");
		columnaNombreTipo.setCellValueFactory(new PropertyValueFactory<TipoHerramienta, String>("nombre"));
		columnaNombreTipo.setMinWidth(170);
		TableColumn<TipoHerramienta, Integer> columnaIdTipoCategoria=new TableColumn<TipoHerramienta,Integer>("Id Categoria");
		columnaIdTipoCategoria.setCellValueFactory(new PropertyValueFactory<TipoHerramienta, Integer>("idCategoria"));
		columnaIdTipoCategoria.setMinWidth(160);
		TableColumn<TipoHerramienta, Integer> columnaCantidad=new TableColumn<TipoHerramienta,Integer>("Cantidad");
		columnaCantidad.setCellValueFactory(new PropertyValueFactory<TipoHerramienta, Integer>("cantidad"));
		columnaCantidad.setMinWidth(160);
		TableColumn<TipoHerramienta, Integer> columnaCantidadDisponible=new TableColumn<TipoHerramienta,Integer>("Disponible");
		columnaCantidadDisponible.setCellValueFactory(new PropertyValueFactory<TipoHerramienta, Integer>("cantidadDisponible"));
		columnaCantidadDisponible.setMinWidth(160);

		columnaNombreTipo.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNombreTipo.setOnEditCommit(new EventHandler<CellEditEvent<TipoHerramienta,String>>(){
			@Override
				public void handle(CellEditEvent<TipoHerramienta, String> eventoE){
					if(eventoE.getNewValue()!=(eventoE.getOldValue())){
						TipoHerramienta tipo = (TipoHerramienta)tvTipo.getSelectionModel().getSelectedItem();
						mTipoHerramienta.modificarNombreTipo(tipo.getIdHerramienta(),eventoE.getNewValue());
						//((Contacto)eventoEditar.getTableView().getItems().get(eventoEditar.getTablePosition().getRow())).setNombre(t.getNewValue());
					}	
				}
			}
		);
		tvTipo.getColumns().setAll(columnaIdTipo,columnaNombreTipo,columnaCantidad,columnaCantidadDisponible,columnaIdTipoCategoria);	
		vBoxTablaTipos.getChildren().add(tvTipo);
		tbTiposCRUD.setContent(vBoxTablaTipos);
		//Agregar un Tipo
		gpAgregarTipo = new GridPane();
		tfNombreTipo = new TextField();
		tfIdCategoria = new TextField();
		tfIdCategoria.setEditable(false);
		gpAgregarTipo.add(lblIngreso,0,1);
		gpAgregarTipo.add(lblNombre,0,2);
		lblNombre.setId("lblNombre");
		gpAgregarTipo.add(tfNombreTipo,1,2);
		gpAgregarTipo.add(lblCategoria,0,3);
		lblCategoria.setId("lblCategoria");
		gpAgregarTipo.add(tfIdCategoria,1,3);
		gpAgregarTipo.add(btnBuscarCategoria,2,3);
		gpAgregarTipo.add(btnAceptarTipo, 0,5);
		tfNombreTipo.setPromptText("Nombre Tipo");
		tfIdCategoria.setPromptText("Id Categoria");
		gpAgregarTipo.setMargin(lblIngreso, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(lblNombre, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(lblCategoria, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(tfNombreTipo, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(tfIdCategoria, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(btnAceptarTipo, new Insets(3,3,3,5));
		gpAgregarTipo.setMargin(btnBuscarCategoria, new Insets(3,3,3,5));
		bPbusquedaCategoria = new BorderPane();
		tfNuevaCategoria = new TextField();
		lblNuevaCategoria = new Label("Categoria:");
		tfNuevaCategoria.setPromptText("Nueva Categoria");
		bPbusquedaCategoria.setCenter(gpAgregarTipo);
		sceneAgregarTipo= new Scene(bPbusquedaCategoria,600,400);
		//-------Editar Categoria 
		tbEditarCategoria = new Tab("Editar Categoria");
		btnAceptarEdicion = new Button("Aceptar");
		btnBuscarC = new Button("Buscar...");
		btnAceptarEdicion.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		btnBuscarC.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
		bPEditarCategoria = new BorderPane();
		gpEditarCategoria = new GridPane();
		gpEditarCategoria.add(lblIngreso, 0,1);
		gpEditarCategoria.add(lblNuevaCategoria, 0,2);
		lblNuevaCategoria.setId("lblCaty");
		gpEditarCategoria.add(tfNuevaCategoria, 1,2);
		gpEditarCategoria.add(btnBuscarC,0,3);
		gpEditarCategoria.setMargin(btnBuscarC,new Insets(10,10,10,10));
		gpEditarCategoria.setMargin(lblNuevaCategoria, new Insets(10,10,10,10));
		tfNuevaCategoria.setEditable(false);
		gpEditarCategoria.add(btnAceptarEdicion,1,4);
		bPEditarCategoria.setCenter(gpEditarCategoria);
		tbEditarCategoria.setContent(bPEditarCategoria);
		//Crud Herramienta :) ya casii 

		mHerramienta = new ManejadorHerramienta(cnx);
		visualHerramienta = new VisualHerramienta(mHerramienta,tpPrincipal,this,tvTipo);

		primaryStage.setScene(primaryScene);
		primaryStage.setWidth(1000);
		primaryStage.setHeight(600);
		tbCRUD.setClosable(false);
		tbLogin.setClosable(false);
		//------------------Aqui el estilo en css 
		primaryScene.getStylesheets().add("css/Estilo.css");
		sceneAgregarUsuario.getStylesheets().add("css/EstiloUsuario.css");
		sceneAgregarCategoria.getStylesheets().add("css/EstiloCategoria.css");
		sceneAgregarProfesor.getStylesheets().add("css/EstiloProfesor.css");
		sceneAgregarTipo.getStylesheets().add("css/EstiloTipo.css");
		//sceneAgregarHerramienta.getStylesheets().add("css/EstiloHerramienta.css");
		primaryStage.show();
	}
	/**
      * Este metodo verifica los datos de tfNombre y pfContra
      * @return !tfNombre.getText().trim().equals("") & !pfContra.getText().trim().equals("")
      */
	private boolean verificarDatos(){
		return !tfNombre.getText().trim().equals("") & !pfContra.getText().trim().equals("");
	}
	/**
      * Este metodo ver los eventos de las ventanas
      * @param event Envia el evento
    */
	public void handle(Event event){
		MouseEvent mouse;
		if(event instanceof KeyEvent){
			if(((KeyEvent)event).getCode() == KeyCode.ENTER){
				if(verificarDatos()){
					Stage dialogo = new Stage();
					mUsuario.desautenticar();
					if(mUsuario.autenticar(tfNombre.getText(), pfContra.getText())){
						dialogo.setScene(new Scene(new Label(" Bienvenido "+tfNombre.getText())));
						String[] permisos=mUsuario.getUsuarioAutenticado().getPermiso().split("-");
						for(int contador=0;contador<permisos.length;contador++){
							if(permisos[contador].equals("crud")){
								tpPrincipal.getTabs().add(tbCRUD);
							}
							if(permisos[contador].equals("visor")){
								visorVisual = new VisorVisual(cnx, mDetallePrestamo, mPrestamo, tpPrincipal,this);
								tpPrincipal.getTabs().add(visorVisual.getTabVisor());
							}
							if(permisos[contador].equals("prestamista")){
								prestamistaVisual =  new PrestamistaVisual(cnx, mDetallePrestamo, mPrestamo, tpPrincipal,this,mTipoHerramienta, mHerramienta, visualHerramienta.getTabla(), mUsuario, mProfesor);
								tpPrincipal.getTabs().add(prestamistaVisual.getTabPrestamista());
							}
							tpPrincipal.getTabs().remove(tbLogin);
						}
					}else{
						dialogo.setScene(new Scene(new Label("Verifique sus credenciales :(")));
					}
					dialogo.show();
				}
			}
		}			
		if(event instanceof MouseEvent){
			mouse = (MouseEvent)event;
			if(event.getEventType()==MouseEvent.MOUSE_CLICKED){
				if(event.getSource().equals(btnUsuarioX)){
					tpPrincipal.getTabs().add(tbCrudUsuario); 
					tpPrincipal.getSelectionModel().select(tbCrudUsuario);
				}
				if(event.getSource().equals(btnEliminar)){
					if(tvUsuario.getSelectionModel().getSelectedItem()!=null){
						Usuario usuario = (Usuario)tvUsuario.getSelectionModel().getSelectedItem();
						mUsuario.eliminarUsuario(usuario.getIdUsuario());
					}
				}
				if(event.getSource().equals(btnActualizar)){
					mUsuario.actualizarListaDeUsuarios();
				}
				if(event.getSource().equals(btnAgregar)){
					tfNombreUsuario.setText(null);
					tfApellidoUsuario.setText(null);
					tfNicknameUsuario.setText(null);
					tfPasswordUsuario.setText(null);
					Stage ventanaAgregar = new Stage();
					ventanaAgregar.setScene(sceneAgregarUsuario);
					ventanaAgregar.setWidth(650);
					ventanaAgregar.setHeight(500);
					ventanaAgregar.show();
				}
				if(event.getSource().equals(btnAceptar)){
					Stage dialogo = new Stage();
					if(tfNombreUsuario.getText()!=null && tfApellidoUsuario.getText()!=null){
						if(tfNombreUsuario.getText()!=null && tfNicknameUsuario.getText()!=null){
							if(chkVisor.isSelected()==false && chkCrud.isSelected()==false && chkPrestamista.isSelected()==false){
								dialogo.setScene(new Scene(new Label("Seleccione Privilegios")));
							}else{
								String permiso="";
								if(chkVisor.isSelected()){
								 	if(permiso.equals("")){
								 		permiso="visor";
								 	}else{
								 		permiso=permiso+"-visor";
								 	}
								}
								if(chkPrestamista.isSelected()){
									if(permiso.equals("")){
								 		permiso="prestamista";
								 	}else{
								 		permiso=permiso+"-prestamista";
								 	}
								}
								if(chkCrud.isSelected()){
									if(permiso.equals("")){
								 		permiso="crud";
								 	}else{
								 		permiso=permiso+"-crud";
								 	}
								}
								mUsuario.agregarUsuario(tfNombreUsuario.getText(),tfApellidoUsuario.getText(), tfNicknameUsuario.getText(),
								tfPasswordUsuario.getText(),permiso);
								dialogo.setScene(new Scene(new Label("Usuario Agregado")));
							}
						}else{
							dialogo.setScene(new Scene(new Label("Campo vacio")));
						}
					}else{
						//dialogo.setScene(new Scene(new Label("Campo vacio")));
					}
					dialogo.show();
					tfNombreUsuario.setText(null);
					tfApellidoUsuario.setText(null);
					tfNicknameUsuario.setText(null);
					tfPasswordUsuario.setText(null);
				}
				if(event.getSource().equals(btnLogout)){
					this.logout();
				}
				if(event.getSource().equals(btnCategoria)){
					vBoxTablaCategoria.getChildren().remove(tvCategoria);
					vBoxTablaCategoria.getChildren().add(tvCategoria);
					estado=false;
					tvCategoria.setEditable(true);
					tpPrincipal.getTabs().add(tbCategoriaCRUD);
					tpPrincipal.getSelectionModel().select(tbCategoriaCRUD);
				}	
				if(event.getSource().equals(btnActualizarCat)){
					mCategoria.actualizarListaDeCategorias();
				}
				if(event.getSource().equals(btnAgregarCat)){
					Stage ventanaAgregar = new Stage();
					tfNombreCategoria.setText(null);
					ventanaAgregar.setScene(sceneAgregarCategoria);
					ventanaAgregar.show();
				}
				if(event.getSource().equals(btnAceptarCategoria)){
					Stage dialogo = new Stage();
					if(tfNombreCategoria.getText()!=null){
						mCategoria.agregarCategoria(tfNombreCategoria.getText());
						dialogo.setScene(new Scene(new Label("Categoria Agregada")));
					}else{
						dialogo.setScene(new Scene(new Label("Campo vacio")));
					}
					dialogo.show();
					tfNombreCategoria.setText(null);
				}
				if(event.getSource().equals(btnProfesor)){
					vBoxTablaProfesor.getChildren().remove(tvProfesor);
					vBoxTablaProfesor.getChildren().add(tvProfesor);
					tpPrincipal.getTabs().add(tbProfesorCRUD);
					tpPrincipal.getSelectionModel().select(tbProfesorCRUD);
					tvProfesor.setEditable(true);
				}
				if(event.getSource().equals(btnActualizarProfesor)){
					mProfesor.actualizarListaDeProfesores();
				}
				if(event.getSource().equals(btnAgregarProfesor)){
					tfNombreProfesor.setText(null);
					tfClaseProfesor.setText(null);
					Stage ventanaAgregar = new Stage();
					ventanaAgregar.setWidth(450);
					ventanaAgregar.setScene(sceneAgregarProfesor);
					ventanaAgregar.show();
				}
				if(event.getSource().equals(btnAceptarProfesor)){
					Stage dialogo = new Stage();
					if(tfNombreProfesor.getText()!=null){
						mProfesor.agregarProfesor(tfNombreProfesor.getText(),tfClaseProfesor.getText());
						dialogo.setScene(new Scene(new Label("Profesor Agregado")));
					}else{
						dialogo.setScene(new Scene(new Label("Campo vacio")));
					}
					dialogo.show(); 
					tfNombreProfesor.setText(null);
					tfClaseProfesor.setText(null);
				}	
				if(event.getSource().equals(btnTipos)){
					this.volverEditable();
					vBoxTablaTipos.getChildren().remove(tvTipo);
					vBoxTablaTipos.getChildren().add(tvTipo);
					tvTipo.setEditable(true);
					tpPrincipal.getTabs().add(tbTiposCRUD);
					tpPrincipal.getSelectionModel().select(tbTiposCRUD);
				}
				if(event.getSource().equals(btnActualizarTipo)){
					mTipoHerramienta.actualizarListaDeTipos();
				}
				if(event.getSource().equals(btnAgregarTipo)){
					tfNombreTipo.setText(null);
					tfIdCategoria.setText(null);
					Stage ventanaAgregar = new Stage();
					ventanaAgregar.setScene(sceneAgregarTipo);
					ventanaAgregar.show();
					ventanaAgregar.setWidth(750);
					ventanaAgregar.setHeight(250);
				}
				if(event.getSource().equals(btnBuscarCategoria)){
					bPbusquedaCategoria.setRight(tvCategoria);
					estado=true;
				}
				if(event.getSource().equals(tvCategoria)){
					if(estado==true){
						tvCategoria.setEditable(false);
						Categoria tipoCategoria = (Categoria)tvCategoria.getSelectionModel().getSelectedItem();
						tfIdCategoria.setText(Integer.toString(tipoCategoria.getIdCategoria()));
						tfNuevaCategoria.setText(Integer.toString(tipoCategoria.getIdCategoria()));
					}else{
						tvCategoria.setEditable(true);
					}	
				}
				if(event.getSource().equals(btnAceptarTipo)){
					Stage dialogo = new Stage();
					if(tfNombreTipo.getText()!=null && tfIdCategoria.getText()!=null){
						mTipoHerramienta.agregarTipo(tfNombreTipo.getText(),tfIdCategoria.getText());
						dialogo.setScene(new Scene(new Label("Tipo Agregado")));
					}else{
						dialogo.setScene(new Scene(new Label("Campo vacio")));
					}
					dialogo.show(); 
					tfNombreTipo.setText(null);
					tfIdCategoria.setText(null);
					tfNuevaCategoria.setText(null);
				}
				if(event.getSource().equals(btnEditarCategoria)){
					tvCategoria.setEditable(false);
					tfNuevaCategoria.setText(null);
					TipoHerramienta tipon = (TipoHerramienta)tvTipo.getSelectionModel().getSelectedItem();
					if(tipon!=null){
						tpPrincipal.getTabs().add(tbEditarCategoria);
						this.setId(tipon.getIdHerramienta());
						estado=true;
					}
				}
				if(event.getSource().equals(btnAceptarEdicion)){
					Stage dialogo = new Stage();
					if(tfNuevaCategoria.getText()!=null){
						mTipoHerramienta.modificarCategoriaTipo(Integer.parseInt(tfNuevaCategoria.getText()), this.getId());
						dialogo.setScene(new Scene(new Label("Campo Modificado")));
					}else{
						dialogo.setScene(new Scene(new Label("Campo vacio")));
					}
					dialogo.show(); 
					tfNombreTipo.setText(null);
					tfIdCategoria.setText(null);
				}
				if(event.getSource().equals(btnBuscarC)){
					bPEditarCategoria.setRight(tvCategoria);
				}
				if(event.getSource().equals(btnHerramienta)){
					visualHerramienta.remover();
					visualHerramienta.quitar();
					visualHerramienta.poner();
					tpPrincipal.getTabs().add(visualHerramienta.getTab());
					visualHerramienta.seleccionar();
				}
				if(event.getSource().equals(btnEliminarCat)){
					Categoria categoriaEliminar = (Categoria)tvCategoria.getSelectionModel().getSelectedItem();
					if(categoriaEliminar!=null){
						mCategoria.eliminarCategoria(categoriaEliminar.getIdCategoria());
					}
				}
				if(event.getSource().equals(btnEliminarProfesor)){
					Profesor profesorEliminar = (Profesor)tvProfesor.getSelectionModel().getSelectedItem();
					if(profesorEliminar!=null){
						mProfesor.eliminarProfesor(profesorEliminar.getIdProfesor());
					}
				}
				if(event.getSource().equals(btnEliminarTipo)){
					TipoHerramienta tipoEliminar = (TipoHerramienta)tvTipo.getSelectionModel().getSelectedItem();
					if(tipoEliminar!=null){
						mTipoHerramienta.eliminarTipo(tipoEliminar.getIdHerramienta());
					}
				}
			}
		}
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
      * Este metodo envia una manejadorUsuario
      * @param mUsuario tipo:ManejadorUsuario 
    */
	public void setMUsuario(ManejadorUsuario mUsuario){
		this.mUsuario = mUsuario;
	}
	/**
      * Este metodo retorna la tabPrincipal
      * @return tbPrincipal tipo:TabPane
    */
	public TabPane returnTab(){
		return this.tpPrincipal;
	}
	/**
	*Este metodo permite salir a las tabs
	*/
	public void logout(){
		tfNombre.setText(null);
		pfContra.setText(null);
		tpPrincipal.getTabs().add(tbLogin);
		tpPrincipal.getTabs().remove(tbCrudUsuario);
		tpPrincipal.getTabs().remove(tbCRUD);
		tpPrincipal.getTabs().remove(tbCategoriaCRUD);
		tpPrincipal.getTabs().remove(tbProfesorCRUD);
		tpPrincipal.getTabs().remove(tbTiposCRUD);
		visorVisual.remove();
		prestamistaVisual.remove();
	}
	/**
	*Este metodo vuelve editable un tableview
	*/
	public void volverEditable(){
		tvProfesor.setEditable(true);
		tvTipo.setEditable(true);
	}
	/**
	*Altera los valores de las cantidades al efectuarse el cambio de tipo
	*/
	public void alertaDeCambio(){
		cnx.ejecutarSentencia("EXEC volverTodasCero");
		cnx.ejecutarSentencia("EXEC actualizarCantidadesFlotantes");
	}
}
