package org.mendez.manejadores;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.beans.Categoria;
import org.mendez.db.Conexion;
/**
*Esta clase maneja la categoria
*@author Daniel Mendez
*/
public class ManejadorCategoria{
	private Conexion cnx;
	private ObservableList<Categoria> listaDeCategorias;
	/**
	*Este es el constructor de ManejadorCategoria
	*@param conexion tipo:Conexion
	*/
	public ManejadorCategoria(Conexion conexion){
		this.cnx = conexion;
		this.listaDeCategorias=FXCollections.observableArrayList();
	}
	/**
	*Este metodo actualiza la lista de Categorias
	*/
	public void actualizarListaDeCategorias(){
		listaDeCategorias.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idCategoria, nombre FROM Categoria");
		try{
			while(resultado.next()){
				Categoria categoria = new Categoria(resultado.getInt("idCategoria"), resultado.getString("nombre"));
				//System.out.println("pasword"+usuario.getPasword());
				listaDeCategorias.add(categoria);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo retorna una lista observable tipo categoria
	*@return listaDeCategorias
	*/
	public ObservableList<Categoria> getListaDeCategorias(){
		actualizarListaDeCategorias();
		return listaDeCategorias;
	}
	/**
	*Este metodo modifica un nombre de una categoria
	*@param idUsuario tipo:int
	*@param nombre tipo:String
	*/
	public void modificarNombre(int idUsuario, String nombre){
		cnx.ejecutarSentencia("EXEC ModificarNombreCategoria '"+nombre+"',"+idUsuario);
		actualizarListaDeCategorias();
	}
	/**
	*Este metodo agrega una categoria
	*@param nombre tipo:String
	*/
	public void agregarCategoria(String nombre){
		cnx.ejecutarSentencia("EXEC InsertarCategoria '"+nombre+"'");
		actualizarListaDeCategorias();
	}
	/**
	*Este metodo elimina una categoria por medio de un id
	*@param id tipo:int
	*/
	public void eliminarCategoria(int id){
		cnx.ejecutarSentencia("EXEC eliminarCategoria "+id);
		actualizarListaDeCategorias();
	}
}