package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
*Esta clase es el prestamo beans
*@author Daniel Mendez
*/
public class Prestamo{
	private StringProperty fecha, fechaDevolucion, hora, jornada, profesor,usuario, estado;
	private IntegerProperty idPrestamo;
	private int id;
	/**Este es un constructor que manda a llamar el metodo inicializar*/
	public Prestamo(){
		this.init();
	}
	/**
	*Este es un metodo constructor que forma el objeto con los siguientes parametros:
	*@param idPrestamo tipo:int
	*@param fecha tipo:String
	*@param hora tipo:String
	*@param profesor tipo:String 
	*@param usuario tipo:String
	*@param fechaDevolucion tipo:String
	*@param jornada tipo:String
	*@param estado tipo:String
	*/
	public Prestamo(int idPrestamo, String fecha, String hora, String profesor, String usuario, String jornada, String fechaDevolucion, String estado){
		this.init();
		this.setIdPrestamo(idPrestamo);	
		this.setFecha(fecha);
		this.setHora(hora);
		this.setJornada(jornada);
		this.setProfesor(profesor);
		this.setUsuario(usuario);
		this.setFechaDevolucion(fechaDevolucion);
		this.setEstado(estado);
	}
	/**
	*Este metodo inicia las variables
	*/
	private void init(){
		this.idPrestamo= new SimpleIntegerProperty();
		this.hora = new SimpleStringProperty();
		this.fecha = new SimpleStringProperty();
		this.profesor = new SimpleStringProperty();
		this.usuario = new SimpleStringProperty();
		this.jornada = new SimpleStringProperty();
		this.fechaDevolucion = new SimpleStringProperty();
		this.estado=new SimpleStringProperty();
	}
	/**
	*Esta metodo me da un id de un prestamo
	*@return  idPrestamo.get()
	*/
	public int getIdPrestamo(){
		return this.idPrestamo.get();
	}
	/**
	*Este metodo envia un id Especifico
	*@param id tipo:int
	*/
	public void setIdPrestamo(int id){
		this.idPrestamo.set(id);
	}
	/**
	*Este metodo devuelve un IntegerProperty
	*@return idPrestamo tipo:IntegerProperty
	*/
	public IntegerProperty idPrestamoProperty(){
		return this.idPrestamo;
	}
	/**
	*Este metodo obtiene una fecha
	*@return fecha.get()
	*/
	public String getFecha(){
		return this.fecha.get();
	}
	/**
	*Este metodo envia una fecha
	*@param fecha tipo:String
	*/
	public void setFecha(String fecha){
		this.fecha.set(fecha);
	}
	/**
	*Este metodo envia un StringProperty a fecha 
	*@return fecha tipo:StringProperty
	*/
	public StringProperty fechaProperty(){
		return this.fecha;
	}
	/**
	*Este metodo obtiene una hora
	*@return hora.get()
	*/
	public String getHora(){
		return this.hora.get();
	}
	/**
	*Este metodo envia una hora
	*@param hora tipo:String
	*/
	public void setHora(String hora){
		this.hora.set(hora);
	}
	/**
	*Este metodo envia un StringProperty a hora
	*@return hora tipo:StringProperty
	*/
	public StringProperty horaProperty(){
		return this.hora;
	}
	/**
	*Este metodo obtiene un nombre de un Profesor
	*@return profesor.get()
	*/
	public String getProfesor(){
		return this.profesor.get();
	}
	/**
	*Este metodo envia un profesor
	*@param profesorNombre tipo:String
	*/
	public void setProfesor(String profesorNombre){
		this.profesor.set(profesorNombre);
	}
	/**
	*Este metodo envia un StringProperty a profesor
	*@return profesor tipo:StringProperty
	*/
	public StringProperty profesorProperty(){
		return this.profesor;
	}
	/**
	*Este metodo obtiene un nombre de un Profesor
	*@return usuario.get()
	*/
	public String getUsuario(){
		return this.usuario.get();
	}
	/**
	*Este metodo envia un usuario
	*@param usuarioNombre tipo:Usuario
	*/
	public void setUsuario(String usuarioNombre){
		this.usuario.set(usuarioNombre);
	}
	/**
	*Este metodo envia un StringProperty a usuario
	*@return usuario tipo:StringProperty
	*/
	public StringProperty usuarioProperty(){
		return this.usuario;
	}
	/**
	*Este metodo obtiene una jornada en la que se hace un prestamo
	*@return jornada.get()
	*/
	public String getJornada(){
		return this.jornada.get();
	}
	/**
	*Este metodo envia una jornada
	*@param jornada tipo:String
	*/
	public void setJornada(String jornada){
		this.jornada.set(jornada);
	}
	/**
	*Este metodo envia un StringProperty a a jornada
	*@return jornada tipo:StringProperty
	*/
	public StringProperty jornadaProperty(){
		return this.jornada;
	}
	/**
	*Este metodo obtiene una fechaDevolucion
	*@return fecha.get()
	*/
	public String getFechaDevolucion(){
		return this.fechaDevolucion.get();
	}
	/**
	*Este metodo envia una fecha
	*@param fechaDevolucion tipo:String
	*/
	public void setFechaDevolucion(String fechaDevolucion){
		this.fechaDevolucion.set(fechaDevolucion);
	}
	/**
	*Este metodo envia un StringProperty a fechaDevolucion
	*@return fechaDevolucion tipo:StringProperty
	*/
	public StringProperty fechaDevolucionProperty(){
		return this.fechaDevolucion;
	}


	/**
	*Este metodo obtiene un estado
	*@return estado.get()
	*/
	public String getEstado(){
		return this.estado.get();
	}
	/**
	*Este metodo envia una estado
	*@param estado tipo:String
	*/
	public void setEstado(String estado){
		this.estado.set(estado);
	}
	/**
	*Este metodo envia un StringProperty a estado
	*@return estado tipo:StringProperty
	*/
	public StringProperty estadoDevolucionProperty(){
		return this.estado;
	}	
}