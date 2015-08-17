package org.mendez.db;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
/**
*Esta clase establece la conexion con sql
*@author Daniel Mendez
*/
public class Conexion{
	private static Conexion instancia;
	private Connection cnx;
	private Statement stn;
	private File archivo;
	private FileReader lector;
	private BufferedReader bufer;
	private String[] datos = new String[5];
	/**
	*Este metodo es el constructor conexion el cual pide y tiene los datos del servidor
	*/
	public Conexion(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.leerDatosConexion();
			cnx = DriverManager.getConnection("jdbc:sqlserver://"+datos[0]+"\\"+datos[1]+";databaseName="+datos[2]+"", datos[3], datos[4]);
			stn = cnx.createStatement();
			leerDatosConexion();
		}catch(SQLException sql){
			sql.printStackTrace();
		}catch(ClassNotFoundException cl){
			System.out.println("Driver no encontrado");
		}
	}
	/**
	*Este metodo ejecuta una sentencia
	*@param sentencia tipo:String
	*/
	public void ejecutarSentencia(String sentencia){
		try{
			stn.execute(sentencia);
		}catch(SQLException sq){
			sq.printStackTrace();
		}
	}
	/**
	*Este metodo ejecuta una consulta
	*@param consulta tipo:String
	*@return resultado tipo:ResulSet
	*/
	public ResultSet ejecutarConsulta(String consulta){
		ResultSet resultado = null;
		try{
			resultado = stn.executeQuery(consulta);
		}catch(SQLException sq){
			sq.printStackTrace();
		}
		return resultado;
	}
	/**
	*Este metodo lee los datos de configuracon del .conf
	*/
	public void leerDatosConexion(){
		try{
			archivo = new File("ConnectedData.conf");
			lector = new FileReader(archivo);
			bufer = new BufferedReader(lector);
			int contador=0;
			for(int i=0; i<5; i++){
				String[] valores=bufer.readLine().split("=");
				datos[i]=valores[1];
			}
		}catch(IOException errorLeerDatos ){
			System.out.println("Error al leer datos");
		}
	}
}
