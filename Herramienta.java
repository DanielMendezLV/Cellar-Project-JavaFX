package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
*Esta clase es el beans herramienta del que creamos objetos
*@author Daniel Mendez
*/
public class Herramienta{
	private StringProperty serial,tipo,estadoVisible;
	private IntegerProperty idHerramientaEspecifico,cantidadGeneral;
	private BooleanProperty estado;
	/**Este es un constructor que manda a llamar el metodo inicializar*/
	public Herramienta(){
		this.init();
	}
	/**
	*Este es un metodo constructor que forma el objeto
	*@param idHerramientaEspecifico tipo:int
	*@param serial tipo:String
	*@param tipo:String
	*@param estado: String
	*@param estadoVisible tipo:String
	*@param cantidadGeneral tipo:int
	*/
	public Herramienta(int idHerramientaEspecifico, String serial, String tipo, boolean estado, String estadoVisible, int cantidadGeneral){
		this.init();
		this.setIdHerramientaEspecifico(idHerramientaEspecifico);	
		this.setSerial(serial);
		this.setTipo(tipo);
		this.setEstado(estado);
		this.setEstadoVisible(estadoVisible);
		this.setCantidadGeneral(cantidadGeneral);
	}
	/**
	*Este metodo inicia las variables
	*/
	private void init(){
		this.idHerramientaEspecifico = new SimpleIntegerProperty();
		this.tipo = new SimpleStringProperty();
		this.serial = new SimpleStringProperty();
		this.estado = new SimpleBooleanProperty();
		this.estadoVisible = new SimpleStringProperty();
		this.cantidadGeneral = new SimpleIntegerProperty();
	}
	/**
	*Esta metodo me da un id de herramienta
	*@return idHerramientaEspecifico.get() 
	*/
	public int getIdHerramientaEspecifico(){
		return this.idHerramientaEspecifico.get();
	}
	/**
	*Este metodo envia un id Especifico
	*@param id tipo:int
	*/
	public void setIdHerramientaEspecifico(int id){
		this.idHerramientaEspecifico.set(id);
	}
	/**
	*Este metodo devuelve un IntegerProperty
	*@return idHerramientaEspecifico tipo:IntegerProperty
	*/
	public IntegerProperty idHerramientaProperty(){
		return this.idHerramientaEspecifico;
	}
	/**
	*Este metodo obtiene un serial
	*@return serial.get() tipo:String
	*/
	public String getSerial(){
		return this.serial.get();
	}
	/**
	*Este metodo envia un serial
	*@param serial tipo:String
	*/
	public void setSerial(String serial){
		this.serial.set(serial);
	}
	/**
	*Este metodo envia un nombreProperty
	*@return serial tipo:StringProperty
	*/
	public StringProperty nombreProperty(){
		return this.serial;
	}
	/**
	*Este metodo retorna un tipo
	*@return tipo.get() tipo:String
	*/
	public String getTipo(){
		return this.tipo.get();
	}
	/**
	*Este metodo envia un tipo
	*@param tipo tipo:String
	*/
	public void setTipo(String tipo){
		this.tipo.set(tipo);
	}
	/**
	*Este metodo retorna un tipoProperty
	*@return tipo tipo:StringProperty
	*/
	public StringProperty tipoProperty(){
		return this.tipo;
	}
	/**
	*Esta metodo me da el estado de una herramienta
	*@return estado.get() 
	*/
	public boolean getEstado(){
		return this.estado.get();
	}
	/**
	*Este metodo envia un estado
	*@param estado tipo:boolean
	*/
	public void setEstado(boolean estado){
		this.estado.set(estado);
	}
	/**
	*Este metodo devuelve un BooleanProperty
	*@return estado tipo:BooleanProperty
	*/
	public BooleanProperty estado(){
		return this.estado;
	}
	/**
	*Este metodo retorna un estadoVisible
	*@return estadoVisible.get() tipo:String
	*/
	public String getEstadoVisible(){
		return this.estadoVisible.get();
	}
	/**
	*Este metodo envia un estadoVisible
	*@param estado tipo:String
	*/
	public void setEstadoVisible(String estado){
		this.estadoVisible.set(estado);
	}
	/**
	*Este metodo retorna un estadoProperty
	*@return estadoVisible tipo:StringProperty
	*/
	public StringProperty estadoVisibleProperty(){
		return this.estadoVisible;
	}
	/**
	*Este metodo retorna una cantidad general
	*@return cantidadGeneral.get() tipo:String
	*/
	public int getCantidadGeneral(){
		return this.cantidadGeneral.get();
	}
	/**
	*Este metodo envia una cantidad general
	*@param cantidadGeneral tipo:String
	*/
	public void setCantidadGeneral(int cantidadGeneral){
		this.cantidadGeneral.set(cantidadGeneral);
	}
	/**
	*Este metodo retorna una cantidadGeneralProperty
	*@return cantidadGeneral tipo:IntegerProperty
	*/
	public IntegerProperty cantidadGeneralProperty(){
		return this.cantidadGeneral;
	}
}