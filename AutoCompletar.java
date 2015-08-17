package org.mendez.utilidadesVisuales;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import org.mendez.manejadores.ManejadorProfesor;
import org.mendez.beans.Profesor;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.geometry.Side;

/**
*Esta clase incia un autocompletar
*@author Daniel Mendez
*/
public class AutoCompletar extends Application implements EventHandler<Event>{
	private ContextMenu sugerencias;
	private TextField tf_AutocompletarConstructor;
	private ManejadorProfesor mProfesor;

	/**
	*Este metodo inicializa los objetos
	*/
	public void inicializarObjetos(){
		this.sugerencias = new ContextMenu();
	}

	/**
	*Este metodo asigna los evento
	*/
	public void asignarEventos(){
		this.tf_AutocompletarConstructor.addEventHandler(KeyEvent.KEY_PRESSED, this);
	}

	/**
	*Este metodo constructor manda los parametros necesarios para la ejecucion
	*@param tf_AutocompletarConstructor tipo:TextField
	*@param mProfesor tipo:ManejadorProfesor
	*/
	public AutoCompletar(TextField tf_AutocompletarConstructor, ManejadorProfesor mProfesor){
		this.inicializarObjetos();
		this.tf_AutocompletarConstructor=tf_AutocompletarConstructor;
		this.asignarEventos();
		this.mProfesor=mProfesor;
	}

	/**
	*Este metodo busca un cambo en especifico del metodo 
	*@param campoBuscado tipo:String
	*/
	public void busquemos(String campoBuscado){
		this.sugerencias.getItems().clear();
		mProfesor.actualizarListaDeProfesores();
		ObservableList<Profesor> lista=mProfesor.getListaDeProfesores();
		for(Profesor profe:lista){
			try{
				if(campoBuscado.trim().equals("")){
					sugerencias.getItems().add(new MenuItem(profe.getNombre()));
				}else{
					if(profe.getNombre().contains(campoBuscado)==true){
						sugerencias.getItems().add(new MenuItem(profe.getNombre()));
					}
				}
			}catch(NullPointerException nuli){

			}
		}
		for(MenuItem item:sugerencias.getItems()){
			item.setOnAction(new EventHandler<ActionEvent>(){
				/**
				*Este metodo sobreescribe al handrel y envia al textield el valor del context menu
				*@param eve tipo:ActionEvent
				*/
				@Override public void handle(ActionEvent eve) {
					tf_AutocompletarConstructor.setText(item.getText());
					tf_AutocompletarConstructor.nextWord();
					tf_AutocompletarConstructor.requestFocus();
					sugerencias.hide();
				}
			});
		}
	}

	/**
	*Este metodo es el start de aplication
	*@param primary tipo:Stage
	*/
	public void start(Stage primary){
	}

	/**
	*Este metodo handle suministra los eventos key
	*/
	public void handle(Event event){
		KeyEvent keyevent = (KeyEvent)event;
		if(keyevent.getEventType() == KeyEvent.KEY_PRESSED){
			if(event.getSource().equals(tf_AutocompletarConstructor)){
				this.busquemos(tf_AutocompletarConstructor.getText());
				sugerencias.show(tf_AutocompletarConstructor, Side.BOTTOM, 0, 0);
				tf_AutocompletarConstructor.requestFocus();
			}
		}
	}
}