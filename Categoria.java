package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
* Descripción de la clase Categoria
* @author Daniel Mendez
*/
public class Categoria{
	private StringProperty nombre;
	private IntegerProperty idCategoria;
	/**
	*Este es un constructor manda a iniziquilizar las variables
	*/
	public Categoria(){
		this.init();
	}
	/**
	*Este es un constructor envia los valores para crear el objeto
	*@param idCategoria tipo:int
	*@param nombre tipo:String
	*/
	public Categoria(int idCategoria, String nombre){
		this.init();
		this.setIdCategoria(idCategoria);	
		this.setNombre(nombre);
	}
	/**
	*Este metodo inicializa las variables
	*/
	private void init(){
		this.nombre = new SimpleStringProperty();
		this.idCategoria = new SimpleIntegerProperty();
	}
	/**
	*Este metodo retorna el nombre
	*@return nombre tipo:String 
	*/
	public String getNombre(){
		return this.nombre.get();
	}
	/**
	*Este metodo envia el nombre
	*@param nombre tipo:String 
	*/
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	/**
	*Este metodo retorna el nombreProperty
	*@return nombre tipo:StringProperty 
	*/
	public StringProperty nombreProperty(){
		return this.nombre;
	}
	/**
	*Este metodo retorna la categoria
	*@return idCategoria.get()  
	*/
	public int getIdCategoria(){
		return this.idCategoria.get();
	}
	/**
	*Este metodo envia el idCategoria
	*@param  udcategoria tipo:int 
	*/
	public void setIdCategoria(int udcategoria){
		this.idCategoria.set(udcategoria);
	}
	/**
	*Este metodo retorna el idProperty
	*@return idCategoria tipo:integerProperty 
	*/
	public IntegerProperty idProperty(){
		return this.idCategoria;
	}
}