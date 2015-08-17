package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class DetallePrestamo{
	private IntegerProperty idPrestado, idHerramienta;
	private StringProperty herramientaSerial, herramientaTipo; 
	/**
	*Este es un constructor que manda a llamar el metodo inicializar
	*/
	public DetallePrestamo(){
		this.init();
	}
	/**
	*Este es un metodo constructor que forma el objeto con los siguientes parametros:
	*@param idPrestado tipo:int
	*@param idHerramienta tipo:int
	*@param herramientaTipo tipo:String
	*@param herramientaSerial tipo:String
	*/
	public DetallePrestamo(int idPrestado, int idHerramienta, String herramientaTipo, String herramientaSerial ){
		this.init();
		this.setIdPrestado(idPrestado);	
		this.setIdHerramienta(idHerramienta);
		this.setSerialHerramienta(herramientaSerial);
		this.setTipo(herramientaTipo);
	}
	/**
	*Este metodo inicia las variables
	*/
	private void init(){
		this.idPrestado= new SimpleIntegerProperty();
		this.idHerramienta= new SimpleIntegerProperty();
		this.herramientaSerial = new SimpleStringProperty();
		this.herramientaTipo = new SimpleStringProperty();
	}
	/**
	*Esta metodo me da un id de un prestamo 
	*@return  idPrestamo.get()
	*/
	public int getIdPrestamo(){
		return this.idPrestado.get();
	}
	/**
	*Este metodo envia un id Especifico
	*@param id tipo:int
	*/
	public void setIdPrestado(int id){
		//System.out.println("Hola"+ id);
		this.idPrestado.set(id);
	}
	/**
	*Este metodo devuelve un IntegerProperty
	*@return idPrestado tipo:IntegerProperty
	*/
	public IntegerProperty idPrestamoProperty(){
		return this.idPrestado;
	}
	
	/**
	*Esta metodo me da un id de un prestamo 
	*@return  idPrestamo.get()
	*/
	public int getIdHerramienta(){
		return this.idHerramienta.get();
	}
	/**
	*Este metodo envia un id Especifico
	*@param id tipo:int
	*/
	public void setIdHerramienta(int id){
		//System.out.println("vv" + id);
		this.idHerramienta.set(id);
	}
	/**
	*Este metodo devuelve un IntegerProperty
	*@return idHerramienta tipo:IntegerProperty
	*/
	public IntegerProperty idHerramientaProperty(){
		return this.idHerramienta;
	}
	/**
	*Este metodo obtiene el serial de una herramienta
	*@return herramientaSerial.get()
	*/
	public String getSerialHerramienta(){
		return this.herramientaSerial.get();
	}
	/**
	*Este metodo envia un serial
	*@param serial tipo:String
	*/
	public void setSerialHerramienta(String serial){
		//System.out.println("nnn" + serial);
		this.herramientaSerial.set(serial);
	}
	/**
	*Este metodo returna un StringProperty de serial 
	*@return herramientaSerial tipo:StringProperty
	*/
	public StringProperty serialProperty(){
		return this.herramientaSerial;
	}
		
	/**
	*Este metodo obtiene un tipo
	*@return herramientaTipo.get()
	*/
	public String getTipo(){
		return this.herramientaTipo.get();
	}
	/**
	*Este metodo envia un tipo
	*@param tipo tipo:String
	*/
	public void setTipo(String tipo){
		//System.out.println("ipo");
		this.herramientaTipo.set(tipo);
	}
	/**
	*Este metodo envia un StringProperty a tipo
	*@return herramientaTipo tipo:StringProperty
	*/
	public StringProperty tipoProperty(){
		return this.herramientaTipo;
	}
	
}