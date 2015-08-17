package org.mendez.manejadores;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
import org.mendez.beans.Herramienta;

public class ManejadorHerramienta{
	private Conexion cnx;
	private ObservableList<Herramienta> listaDeHerramientas;
	/**
	*Este metodo es el manejadorherramienta
	*@param conexion tipo:Conexion
	*/
	public ManejadorHerramienta(Conexion conexion){
		this.cnx = conexion;
		this.listaDeHerramientas=FXCollections.observableArrayList();
	}
	/**
	*Este metodo actualiza la lista de herramientas
	*/
	public void actualizarListaDeHerramientas(){
		listaDeHerramientas.clear();
		ResultSet resultado = cnx.ejecutarConsulta("Select Herramienta.idHerramienta, Herramienta.serial, Tipo.nombre as tipo, Tipo.cantidadDisponible, Herramienta.estado FROM Herramienta INNER JOIN Tipo ON Herramienta.idTipo=Tipo.idTipo");
		try{
			while(resultado.next()){
				if(resultado.getBoolean("estado")==false){
					Herramienta herramienta = new Herramienta(resultado.getInt("idHerramienta"), resultado.getString("serial"), resultado.getString("tipo"), resultado.getBoolean("estado"), "Disponible",resultado.getInt("cantidadDisponible"));			
					listaDeHerramientas.add(herramienta);
				}else if(resultado.getBoolean("estado")==true){
					Herramienta herramienta = new Herramienta(resultado.getInt("idHerramienta"), resultado.getString("serial"), resultado.getString("tipo"), resultado.getBoolean("estado"), "Prestado",resultado.getInt("cantidadDisponible"));			
					listaDeHerramientas.add(herramienta);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo obtiene la lista de herramientas
	*@return ObservableList tipo:ObservableList
	*/
	public ObservableList<Herramienta> getListaDeHerramienta(){
		actualizarListaDeHerramientas();
		return listaDeHerramientas;
	}
	/**
	*Este metodo modifica el serial
	*@param id tipo:Int
	*@param serialN tipo:String
	*/
	public void modificarSerial(int id, String serialN){
		cnx.ejecutarSentencia("EXEC modificarSerialHerramienta '"+serialN+"',"+id);
		actualizarListaDeHerramientas();
	}
	/**
	*Este metodo agrega herramienta
	*@param id tipo:Int
	*@param serialN tipo:String
	*/
	public void agregarHerramienta(int id, String serialN){
		cnx.ejecutarSentencia("EXEC agregarHerramienta '"+serialN+"',"+id);
		actualizarListaDeHerramientas();
	}
	/**
	*Este metodo modifica el tipo de la herramienta
	*@param tipo tipo:Int
	*@param id tipo:Int
	*/
	public void modificarTipoHerramienta(int tipo, int id){
		cnx.ejecutarSentencia("EXEC modificarTipoHerramienta "+tipo+","+id);
		actualizarListaDeHerramientas();
	}
	/**
	*Este metodo elimina una herramienta
	*@param id tipo:int
	*/
	public void eliminarHerramienta(int id){
		cnx.ejecutarSentencia("EXEC eliminarHerramienta "+id);
		actualizarListaDeHerramientas();
	}
}