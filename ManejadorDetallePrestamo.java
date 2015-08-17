package org.mendez.manejadores;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
import org.mendez.beans.DetallePrestamo;
import org.mendez.beans.Herramienta;
/**
*Esta clase maneja el tipo de detallPrestamo
*@author Daniel Mendez
*/
public class ManejadorDetallePrestamo{
	private Conexion cnx;
	private ObservableList<DetallePrestamo> listaDeDetallePrestamo;
	/**
	*Este es el constructor tipoHerramienta
	*@param conexion tipo:Conexion 
	*/
	public ManejadorDetallePrestamo(Conexion conexion){
		this.cnx = conexion;
		this.listaDeDetallePrestamo=FXCollections.observableArrayList();
	}
	/**
	*Este metodo actualiza la lista del detallePrestamo
	*@param id tipo:int
	*/
	public void actualizarListaDeDetallePrestamo(int id){
		listaDeDetallePrestamo.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT DetallePrestamo.idPrestamo, DetallePrestamo.idHerramienta, Herramienta.serial as serial, Tipo.nombre as nombre FROM DetallePrestamo INNER JOIN Herramienta ON DetallePrestamo.idHerramienta=Herramienta.idHerramienta INNER JOIN Tipo ON Herramienta.idTipo=Tipo.idTipo WHERE idPrestamo="+id);
		try{
			while(resultado.next()){
				DetallePrestamo det = new DetallePrestamo(resultado.getInt("idPrestamo"), resultado.getInt("idHerramienta"),resultado.getString("nombre"),resultado.getString("serial"));				//System.out.println("pasword"+usuario.getPasword());
				listaDeDetallePrestamo.add(det);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	
	/**
	*Este metodo agrega una herramienta en el prestamo
	*@param idPrestamo tipo:int
	*@param herramien tipo:ObservableListHerramienta
	*/
	public void agregarHerramientaPrestamo(int idPrestamo, ObservableList<Herramienta> herramien){
		for(int i=0; i<herramien.size();i++){
			cnx.ejecutarSentencia("EXEC insertarDetallePrestamo "+idPrestamo+","+herramien.get(i).getIdHerramientaEspecifico());
		}
	}
	/**
	*Este metodo obtiene la lista del detallePrestamo
	*@param id tipo:int
	*@return listaDeDetallePrestamo tipo:ObservableList DetallePrestamo
	*/
	public ObservableList<DetallePrestamo> getListaDetallePrestamo(int id){
		this.actualizarListaDeDetallePrestamo(id);
		return listaDeDetallePrestamo;
	}
}