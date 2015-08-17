package org.mendez.manejadores;
import org.mendez.beans.Profesor;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
/**
*Esta clase maneja el profesor
*@author Daniel Mendez
*/
public class ManejadorProfesor{
	private Conexion cnx;
	private ObservableList<Profesor> listaDeProfesores;
	/**
	*Este es el constructor de manejadorProfesor
	*@param conexion tipo:Conexion
	*/
	public ManejadorProfesor(Conexion conexion){
		this.cnx = conexion;
		this.listaDeProfesores=FXCollections.observableArrayList();
	}
	/**
	*Este emtodo actualiza la lista de profesores
	*/
	public void actualizarListaDeProfesores(){
		listaDeProfesores.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idProfesor, nombre, clase FROM Profesor");
		try{
			while(resultado.next()){
				Profesor profesor = new Profesor(resultado.getInt("idProfesor"), resultado.getString("nombre"),resultado.getString("clase"));				//System.out.println("pasword"+usuario.getPasword());
				listaDeProfesores.add(profesor);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo retorna una lista observable de Profesores
	*@return listaDeProfesores tipo:ObservableList
	*/
	public ObservableList<Profesor> getListaDeProfesores(){
		actualizarListaDeProfesores();
		return listaDeProfesores;
	}
	/**
	*Este metodo modifica un usuario en la base de datos
	*@param idProfesor tipo:int
	*@param nombre tipo:String
	*/
	public void modificarNombre(int idProfesor, String nombre){
		cnx.ejecutarSentencia("EXEC ModificarNombreProfesor '"+nombre+"',"+idProfesor);
		actualizarListaDeProfesores();
	}
	/**
	*Este metodo modifica la clase del profesor
	*@param idProfesor tipo:int
	*@param clase tipo:String
	*/
	public void modificarClase(int idProfesor, String clase){
		cnx.ejecutarSentencia("EXEC ModificarClaseProfesor '"+clase+"',"+idProfesor);
		actualizarListaDeProfesores();
	}
	/**
	*Este metodo agrega un profesor
	*@param nombre tipo:String
	*@param clase tipo:String
	*/
	public void agregarProfesor(String nombre, String clase){
		cnx.ejecutarSentencia("EXEC InsertarProfesor '"+nombre+"','"+clase+"'");
		actualizarListaDeProfesores();
	}
	/**
	*Este metodo elimina un profesor
	*@param idProfesor tipo:int
	*/
	public void eliminarProfesor(int idProfesor){
		cnx.ejecutarSentencia("EXEC eliminarProfesor "+ idProfesor);
		actualizarListaDeProfesores();
	}
}