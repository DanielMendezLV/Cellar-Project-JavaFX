package org.mendez.beans;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
*Esta clase es el beans Usuario, encargada de moldear objetos y crearlos
*@author Daniel Mendez
*/
public class Usuario{
	private StringProperty nombre, apellido, pasword, nickname, permiso;
	private IntegerProperty idUsuario;
	/**Este es un constructor tipo usuario que manda a llamar al metodo inicializar*/
	public Usuario(){
		this.init();
	}
	/**
	*Este es un metodo constructor con las variables que formaran parte de el
	*@param idUsuario tipo:Int
	*@param nombre tipo:String
	*@param apellido tipo:String
	*@param pasword tipo:String
	*@param nickname tipo:String
	*@param permiso tipo:String
	*/
	public Usuario(int idUsuario, String nombre, String apellido, String pasword, String nickname, String permiso){
		this.init();
		this.setNombre(nombre);	
		this.setApellido(apellido);
		this.setPasword(pasword);
		this.setIdUsuario(idUsuario);
		this.setNickname(nickname);
		this.setPermiso(permiso);
	}
	/**
	*Este metodo inicializa todas las variables
	*/
	private void init(){
		this.nombre = new SimpleStringProperty();
		this.apellido = new SimpleStringProperty();
		this.pasword = new SimpleStringProperty();
		this.idUsuario = new SimpleIntegerProperty();
		this.nickname= new SimpleStringProperty();
		this.permiso= new SimpleStringProperty();
	}
	/**
	*Este metodo retorna el nombre
	*@return nombre.get() tipo:String
	*/
	public String getNombre(){
		return this.nombre.get();
	}
	/**
	*Este metodo envia un nombre
	*@param nombre tipo:String
	*/
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	/**
	*Este metodo retorna un nombreProperty
	*@return nombre;
	*/
	public StringProperty nombreProperty(){
		return this.nombre;
	}
	/**
	*Este metodo retorna un apellido
	*@return apellido.get(); tipo:String
	*/
	public String getApellido(){
		return this.apellido.get();
	}
	/**
	*Este metodo envia un apellido
	*@param apellido tipo:String
	*/
	public void setApellido(String apellido){
		this.apellido.set(apellido);
	}
	/**
	*Este metodo envia un apellidoProperty
	*@return apellido;
	*/
	public StringProperty apellidoProperty(){
		return this.apellido;
	}
	/**
	*Este metodo retorna un pasword
	*@return pasword.get() tipo:String;
	*/
	public String getPasword(){
		return this.pasword.get();
	}
	/**
	*Este metodo envia un pasword
	*@param contra tipo:String
	*/
	public void setPasword(String contra){
		this.pasword.set(contra);
	}
	/**
	*Ese metodo retorna un StringProperty
	*@return pasword
	*/
	public StringProperty paswordProperty(){
		return this.pasword;
	}
	/**
	*Este metodo obtiene un idUsuario
	*@return idUsuario.get();
	*/
	public int getIdUsuario(){
		return this.idUsuario.get();
	}
	/**
	*Este metodo envia un idUsuario
	*@param id tipo:int
	*/
	public void setIdUsuario(int id){
		this.idUsuario.set(id);
	}
	/**
	*Este metodo retorna un IntegerProperty 
	*@return idUsuario tipo:IntegerProperty
	*/
	public IntegerProperty idUsuarioProperty(){
		return this.idUsuario;
	}
	/**
	*Este metodo retorna un nickname
	*@return nickname.get()
	*/
	public String getNickname(){
		return this.nickname.get();
	}
	/**
	*Este metodo envia un nickname
	*@param nickname tipo:String
	*/
	public void setNickname(String nickname){
		this.nickname.set(nickname);
	}
	/**
	*Este metodo retorna un nickname StringProperty
	*@return nickname tipo:StringProperty
	*/
	public StringProperty nicknameProperty(){
		return this.nickname;
	}
	/**
	*Este metodo retorna el permiso
	*@return permiso.get() tipo:String
	*/
	public String getPermiso(){
		return this.permiso.get();
	}
	/**
	*Este metodo envia un permiso
	*@param permiso tipo:String
	*/
	public void setPermiso(String permiso){
		this.permiso.set(permiso);
	}
	/**
	*Este metodo retorna un permisoProperty
	*@return permiso;
	*/
	public StringProperty permisoProperty(){
		return this.permiso;
	}
}
