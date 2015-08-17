package org.mendez.manejadores;
import org.mendez.beans.Prestamo;
import org.mendez.manejadores.ManejadorDetallePrestamo;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
import org.mendez.utilidades.Fecha;
import org.mendez.beans.Herramienta;
/**
*Esta clase maneja el profesor
*@author Daniel Mendez
*/
public class ManejadorPrestamo{
	private Conexion cnx;
	private ManejadorDetallePrestamo mManejadorDetalle;
	private ObservableList<Prestamo> listaDePrestamos;
	private ObservableList<Prestamo> listaDePrestamosPendientes;
	private Fecha fech;
	private String fecha;
	/**
	*Este es el constructor de manejadorPrestamo
	*@param conexion tipo:Conexion
	*@param mManejadorDetalle tipo:ManejadorDetallePrestamo
	*/
	public ManejadorPrestamo(Conexion conexion, ManejadorDetallePrestamo mManejadorDetalle){
		this.mManejadorDetalle=mManejadorDetalle;
		this.cnx = conexion;
		this.listaDePrestamos=FXCollections.observableArrayList();
		this.listaDePrestamosPendientes=FXCollections.observableArrayList();
		fech = new Fecha();
	}
	/**
	*Este emtodo actualiza la lista de prestamos
	*/
	public void actualizarListaDePrestamos(){
		listaDePrestamos.clear();
		listaDePrestamosPendientes.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT Prestamo.idPrestamo,Prestamo.fecha, Prestamo.fechaDevolucion as Devolucion, Prestamo.hora, Prestamo.jornada,Prestamo.estadoPrestamo,Profesor.nombre as Profesor, Usuario.nombre as Usuario FROM Prestamo INNER JOIN Profesor ON Prestamo.idProfesor=Profesor.idProfesor INNER JOIN Usuario ON Prestamo.idUsuario=Usuario.idUsuario");
		try{
			while(resultado.next()){
				boolean estado=fech.estadoDeFechas(resultado.getString("Devolucion"));
				String variable;
				Prestamo prestamo;
				if(resultado.getBoolean("estadoPrestamo")){
					variable="Entregado";
					prestamo= new Prestamo(resultado.getInt("idPrestamo"), resultado.getString("fecha"),resultado.getString("hora"), resultado.getString("profesor"), resultado.getString("usuario"), resultado.getString("jornada"), resultado.getString("Devolucion"),variable);				//System.out.println("pasword"+usuario.getPasword());
				}else{
					if(estado){
						variable="Pendiente";
						prestamo= new Prestamo(resultado.getInt("idPrestamo"), resultado.getString("fecha"),resultado.getString("hora"), resultado.getString("profesor"), resultado.getString("usuario"), resultado.getString("jornada"), resultado.getString("Devolucion"),variable);				//System.out.println("pasword"+usuario.getPasword());
						listaDePrestamosPendientes.add(prestamo);
					}else{
						variable="Fuera de Fecha";
						prestamo = new Prestamo(resultado.getInt("idPrestamo"), resultado.getString("fecha"),resultado.getString("hora"), resultado.getString("profesor"), resultado.getString("usuario"), resultado.getString("jornada"), resultado.getString("Devolucion"),variable);				//System.out.println("pasword"+usuario.getPasword());
						listaDePrestamosPendientes.add(prestamo);
					}
				}	
				listaDePrestamos.add(prestamo);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo retorna una lista observable de Prestamos
	*@return listaDePrestamos tipo:ObservableList
	*/
	public ObservableList<Prestamo> getListaDePrestamos(){
		actualizarListaDePrestamos();
		return listaDePrestamos;
	}
	/**
	*Este metodo envia una fecha 
	*@param fecha tipo.String
	*/
	public void setFecha(String fecha){
		String fechaApropiada=fech.hacerSplitFecha(fecha,true,true);
		System.out.println("Fecha apropiada "+fechaApropiada);
		this.fecha=fechaApropiada;
	}
	/**
	*Este metodo retorna una fecha
	*@return fecha tipo:String
	*/
	public String getFecha(){
		return fecha;
	}
	/**
	*Este método agrega un prestamo
	*@param jornada tipo:String
	*@param idProfesor tipo:int
	*@param idUsuaro tipo:int
	*@param arrayHerramientas tipo:ObservableList
	*/
	public void agregarPrestamo(String jornada, int idProfesor, int idUsuaro, ObservableList<Herramienta> arrayHerramientas){
		if(this.getFecha()!=null){
			cnx.ejecutarSentencia("EXEC insertarPrestamo '"+fech.getFechaActual()+"','"+this.getFecha()+"','"+fech.getHoraActual()+"','"+jornada+"',"+idProfesor+","+idUsuaro);
			actualizarListaDePrestamos();
		}else{
			cnx.ejecutarSentencia("EXEC insertarPrestamo '"+fech.getFechaActual()+"','"+fech.getFechaActual()+"','"+fech.getHoraActual()+"','"+jornada+"',"+idProfesor+","+idUsuaro);
  			actualizarListaDePrestamos();
		}
		mManejadorDetalle.agregarHerramientaPrestamo(this.getUltimoId(),arrayHerramientas);
	}
	/**
	*Este metodo obtiene el ultimo id ingresado de prestamo
	*@return -1
	*/
	public int getUltimoId(){
		ResultSet resultado=cnx.ejecutarConsulta("SELECT MAX(idPrestamo) AS idPrestamo FROM Prestamo");
		try{
			if(resultado.next()){
				return resultado.getInt("idPrestamo");
			}
			return -1;
		}catch(SQLException sql){
			sql.printStackTrace();
			return -1;
		}	
	}
	/**
	*Este metodo envia un Id de un prestamo y cambia su estado
	*@param idPrestamo tipo:int 
	*/
	public void cambiarEstadoEntregado(int idPrestamo){
		cnx.ejecutarSentencia("EXEC cambiarEstadoPrestamo "+idPrestamo);
		this.actualizarListaDePrestamos();
	}
	/**
	*Este metodo solo devuelve aquellos que estan pendientas
	*@return listaDePrestamosPendientes tipo:ObservableList
	*/
	public ObservableList<Prestamo> getListaDePrestamosPendientes(){
		this.actualizarListaDePrestamos();
		return listaDePrestamosPendientes;
	}
}