package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
*Esta clase es el tipo de herramienta o el tipo
*/
public class TipoHerramienta{
	private StringProperty nombre;
	private IntegerProperty idHerramienta,idCategoria, cantidad, cantidadDisponible;
	/**Este es el metodo constructor que manda a llamar al inicializador de variables*/
	public TipoHerramienta(){
		this.init();
	}
	/**
	*Este metodo es el constructor que lleva los atributos de los objetos creador
	*@param idHerramienta tipo:int
	*@param nombre tipo:String
	*@param idCategoria tipo:int
	*@param cantidad tipo:int
	*@param cantidadDisponible tipo:int;
	*/
	public TipoHerramienta(int idHerramienta, String nombre,int idCategoria, int cantidad, int cantidadDisponible){
		this.init();
		this.setNombre(nombre);	
		this.setIdHerramienta(idHerramienta);
		this.setIdCategoria(idCategoria);
		this.setCantidad(cantidad);
		this.setCantidadDisponible(cantidadDisponible);
	}
	/**Este metodo inicializa las variables*/
	private void init(){
		this.nombre = new SimpleStringProperty();
		this.idHerramienta = new SimpleIntegerProperty();
		this.idCategoria = new SimpleIntegerProperty();
		this.cantidad = new SimpleIntegerProperty();
		this.cantidadDisponible = new SimpleIntegerProperty();
	}
	/**
	*Este metodo retorna un id Herramienta
	*@return idHerramienta.get() tipo:int
	*/
	public int getIdHerramienta(){
		return this.idHerramienta.get();
	}
	/**
	*Este metodo envia un idHerramienta
	*@param id tipo:int
	*/
	public void setIdHerramienta(int id){
		this.idHerramienta.set(id);
	}
	/**
	*Este metodo retorna un idHerramientaProperty
	*@return idHerramienta tipo:IntegerProperty
	*/
	public IntegerProperty idHerramientaProperty(){
		return this.idHerramienta;
	}
	/**
	*Este metodo obtiene un nombre
	*@return nombre.get() tipo:String
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
	*@return nombre tipo:StringProperty
	*/
	public StringProperty nombreProperty(){
		return this.nombre;
	}
	/**
	*Este metodo retorna un idCategoria
	*@return idCategoria.get() tipo:int
	*/
	public int getIdCategoria(){
		return this.idCategoria.get();
	}
	/** 
	*Este metodo envia un idCategoria
	*@param idCat tipo:int
	*/
	public void setIdCategoria(int idCat){
		this.idCategoria.set(idCat);
	}
	/**
	*Este metodo obtiene un integerProperty
	*@return idCategoria tipo:IntegerProperty
	*/
	public IntegerProperty idCategoriaProperty(){
		return this.idCategoria;
	}
	/**
	*Este metodo retorna una cantidad
	*@return cantidad.get() tipo:int
	*/
	public int getCantidad(){
		return this.cantidad.get();
	}
	/**
	*Este metodo envia una cantidad
	*@param cantidad tipo:int
	*/
	public void setCantidad(int cantidad){
		this.cantidad.set(cantidad);
	}
	/**
	*Este metodo retorna una cantidadHerramientaProperty
	*@return cantidad tipo:IntegerProperty
	*/
	public IntegerProperty getCantidadProperty(){
		return this.cantidad;
	}
	/**
	*Este metodo retorna una cantidad Disponible
	*@return cantidadDisponible.get() tipo:int
	*/
	public int getCantidadDisponible(){
		return this.cantidadDisponible.get();
	}
	/**
	*Este metodo envia una cantidad disponible
	*@param cantidad tipo:int
	*/
	public void setCantidadDisponible(int cantidad){
		this.cantidadDisponible.set(cantidad);
	}
	/**
	*Este metodo retorna una cantidadDisponibleHerramientaProperty
	*@return cantidadDisponible tipo:IntegerProperty
	*/
	public IntegerProperty getCantidadDisponibleProperty(){
		return this.cantidadDisponible;
	}
}