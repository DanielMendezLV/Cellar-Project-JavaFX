package org.mendez.manejadores;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
import org.mendez.beans.TipoHerramienta;
/**
*Esta clase maneja el tipo de Herramienta
*@author Daniel Mendez
*/
public class ManejadorTipoHerramienta{
	private Conexion cnx;
	private ObservableList<TipoHerramienta> listaDeTipos;
	/**
	*Este es el constructor tipoHerramienta
	*@param conexion tipo:Conexion 
	*/
	public ManejadorTipoHerramienta(Conexion conexion){
		this.cnx = conexion;
		this.listaDeTipos=FXCollections.observableArrayList();
	}
	/**
	*Este metodo actualiza la lista de tipos
	*/
	public void actualizarListaDeTipos(){
		listaDeTipos.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idTipo, nombre,idCategoria,cantidad,cantidadDisponible FROM Tipo");
		try{
			while(resultado.next()){
				TipoHerramienta tipos = new TipoHerramienta(resultado.getInt("idTipo"), resultado.getString("nombre"),resultado.getInt("idCategoria"),resultado.getInt("cantidad"),resultado.getInt("cantidadDisponible"));				//System.out.println("pasword"+usuario.getPasword());
				//System.out.println(resultado.getInt("idCategoria"));
				listaDeTipos.add(tipos);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo retorna un observableList tipo:TipoHerramienta
	*@return listaDeTipos tipo:ObservableList
	*/
	public ObservableList<TipoHerramienta> getListaDeTipos(){
		actualizarListaDeTipos();
		return listaDeTipos;
	}
	/**
	*Este metodo modifica nombre de tipo
	*@param idTipo tipo:int
	*@param nombre tipo:String
	*/
	public void modificarNombreTipo(int idTipo, String nombre){
		cnx.ejecutarSentencia("EXEC ModificarNombreTipo '"+nombre+"',"+idTipo);
		actualizarListaDeTipos();
	}
	/**
	*Este metodo modifica la categoria
	*@param idCategoria tipo:int
	*@param idTipo tipo:int
	*/
	public void modificarCategoriaTipo(int idCategoria, int idTipo){
		cnx.ejecutarSentencia("EXEC modificarCategoriaTipo "+idCategoria+","+idTipo);
		actualizarListaDeTipos();
	}
	/**
	*Este metodo agrega un tipo
	*@param nombre tipo:String
	*@param idCategoria tipo:String
	*/
	public void agregarTipo(String nombre, String idCategoria){
		cnx.ejecutarSentencia("EXEC insertarTipo '"+nombre+"',"+Integer.parseInt(idCategoria));
		actualizarListaDeTipos();
	}
	/**
	*Este metodo elimina un tipo
	*@param codigo tipo:int
	*/
	public void eliminarTipo(int codigo){
		cnx.ejecutarSentencia("EXEC eliminarTipo "+codigo);
		actualizarListaDeTipos();
	}
}

