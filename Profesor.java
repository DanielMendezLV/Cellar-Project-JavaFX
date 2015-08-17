package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
*Esta es la clase del bean profesor encargada de hacer objetos tipo:Profesor
*@author Daniel Mendez
*/
public class Profesor{
	private StringProperty nombre,clase;
	private IntegerProperty idProfesor;
	/**
	*Este es el constructor en el cual se inicializan las variables
	*/
	public Profesor(){
		this.init();
	}
	/**
	*Este es el metodo donde se crean los objetos tipo profesor con sus atributos
	*@param idProfesor tipo:int
	*@param nombre tipo:String
	*@param clase tipo:String
	*/
	public Profesor(int idProfesor, String nombre, String clase){
		this.init();
		this.setNombre(nombre);	
		this.setClase(clase);
		this.setIdProfesor(idProfesor);
	}
	/**
	*Este metodo inicializa las variables
	*/
	private void init(){
		this.nombre = new SimpleStringProperty();
		this.clase = new SimpleStringProperty();
		this.idProfesor = new SimpleIntegerProperty();
	}
	/**
	*Este metodo retorna un idProfesor
	*@return idProfesor.get() tipo:int
	*/
	public int getIdProfesor(){
		return this.idProfesor.get();
	}
	/**
	*Este metodo envia un idProfesor
	*@param id tipo:int
	*/
	public void setIdProfesor(int id){
		this.idProfesor.set(id);
	}
	/**
	*Este metodo retorna un IntegerProperty
	*@return idProfesor tipo:integerProperty
	*/
	public IntegerProperty idProfesorProperty(){
		return this.idProfesor;
	}
	/**
	*Este metodo retorna un nombre
	*@return nombre tipo:String
	*/
	public String getNombre(){
		return this.nombre.get();
	}
	/**
	*Este metodo envia un nombre
	*@param nombre tipo:String
	*/
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	/**
	*Este metodo retorna un nombreProperty
	*@return nombre
	*/
	public StringProperty nombreProperty(){
		return this.nombre;
	}
	/**
	*Este metodo me devuelve la clase
	*@return clase.get() tipo:String
	*/
	public String getClase(){
		return this.clase.get();
	}
	/**
	*Este metodo envia una clase
	*@param clase tipo:String
	*/
	public void setClase(String clase){
		this.clase.set(clase);
	}
	/**
	*Este metodo retorna un SringProperty 
	*@return clase;
	*/
	public StringProperty claseProperty(){
		return this.clase;
	}
}