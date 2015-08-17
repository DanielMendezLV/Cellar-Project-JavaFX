package org.mendez.manejadores;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;
import org.mendez.db.Conexion;
import org.mendez.beans.Usuario;
/**
*Esta clase maneja al usuario
*@author Daniel Mendez 
*/
public class ManejadorUsuario{
	//private SimpleListProperty<Usuario> listaDeUsuarios;
	private Usuario usuarioConectado;
	private Conexion conexion;
	private ObservableList<Usuario> listaDeUsuarios;
	/**Este metodo es el constructor de manejador usuario
	*@param conexion tipo:Conexion
	*/
	public ManejadorUsuario(Conexion conexion){
		//listaDeUsuarios = new SimpleListProperty<Usuario>();
		this.conexion = conexion;
		this.listaDeUsuarios=FXCollections.observableArrayList();
	}
	/**
	*Este metodo autentica el usuario
	*@param nickname tipo:String
	*@param contra tipo:String
	*@return boolean tipo:boolean
	*/
	public boolean autenticar(String nickname, String contra){
		if(usuarioConectado!=null)
			return true;
		ResultSet resultado = conexion.ejecutarConsulta("EXEC VerificarUsuario '"+nickname+"','"+contra+"'");
		try{
			if(resultado!=null){
				if(resultado.next()){
					System.out.println("Resultado "+resultado.getString("permiso"));
					usuarioConectado=new Usuario(resultado.getInt("idUsuario"),resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("pasword"), resultado.getString("nickname"), resultado.getString("permiso"));
					//System.out.println("UsuarioConectado"+ usuarioConectado.getNombre());
					//return true;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		//return false;
		return usuarioConectado!=null;
	}
	/**
	*Este metodo actualiza la listra de usuario
	*/
	public void actualizarListaDeUsuarios(){
		listaDeUsuarios.clear();
		ResultSet resultado = conexion.ejecutarConsulta("SELECT idUsuario, pasword, nickname, nombre, apellido, permiso FROM Usuario");
		try{
			while(resultado.next()){
				Usuario usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("pasword"), resultado.getString("nickname"),resultado.getString("permiso"));
				//System.out.println("pasword"+usuario.getPasword());
				listaDeUsuarios.add(usuario);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	*Este metodo me devuelve un observablelist
	*@return listaDeUsuarios tipo:ObservableList
	*/
	public ObservableList<Usuario> getListaDeUsuarios(){
		actualizarListaDeUsuarios();
		return listaDeUsuarios;
	}
	/**
	*Este metodo desautentica un usuario
	*/
	public void desautenticar(){
		usuarioConectado = null;
	}
	/**
	*Este metodo retorna un susuario
	*@return usuarioConectado tipo:Usuario
	*/
	public Usuario getUsuarioAutenticado(){
		return this.usuarioConectado;
	}
	/**
	*Este metodo eliminar el usuario
	*@param idUsuario tipo:Int
	*/
	public void eliminarUsuario(int idUsuario){	
		conexion.ejecutarSentencia("EXEC eliminarUsuario "+idUsuario);
		actualizarListaDeUsuarios();
	}
	/**
	*Este metodo modifica el nombre
	*@param idUsuario tipo:Int
	*@param nombre tipo:String
	*/
	public void modificarNombre(int idUsuario, String nombre){
		conexion.ejecutarSentencia("EXEC ModificarNombreUsuario '"+nombre+"',"+idUsuario);
		actualizarListaDeUsuarios();
	}
	/**
	*Este metodo modifica el apellido
	*@param idUsuario tipo:int
	*@param apellido tipo:String
	*/
	public void modificarApellido(int idUsuario, String apellido){
		conexion.ejecutarSentencia("EXEC ModificarApellidoUsuario '"+apellido+"',"+idUsuario);
		actualizarListaDeUsuarios();
	}
	/**
	*Este metido modifica el nickname
	*@param idUsuario tipo:int
	*@param nickname tipo:String
	*/
	public void modificarNickname(int idUsuario, String nickname){
		conexion.ejecutarSentencia("EXEC ModificarNicknameUsuario '"+nickname+"',"+idUsuario);
		actualizarListaDeUsuarios();
	}
	/**
	*Este metodo modifica el password
	*@param idUsuario tipo:Int
	*@param pasword tipo:String
	*/
	public void modificarPassword(int idUsuario, String pasword){
		conexion.ejecutarSentencia("EXEC ModificarPaswordUsuario '"+pasword+"',"+idUsuario);
		actualizarListaDeUsuarios();
	}
	/**
	*Este metodo agrega un usuario
	*@param nombre tipo:String
	*@param apellido tipo:String
	*@param nickname tipo:String
	*@param pasword tipo:String
	*@param permiso tipo:String
	*/
	public void agregarUsuario(String nombre, String apellido, String nickname, String pasword, String permiso){
		conexion.ejecutarSentencia("EXEC InsertarUsuario '"+nombre+"','"+apellido+"','"+nickname+"','"+pasword+"','"+permiso+"'");
		actualizarListaDeUsuarios();
	}
}
