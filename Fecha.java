package org.mendez.utilidades;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.time.LocalDate;
/**
*Esta clase es el beans herramienta del que creamos objetos
*@author Daniel Mendez
*/
public class Fecha{
	private int dia;
	private int mes;
	private int anyo;
	private int hora;
	private int minutos;
	private static int[] array = new int[3];
	private static int[] arrayAComparar = new int[3];
	/**
	*Este metodo obtiene la fecha actual
	*@return fecha tipo:String
	*/
	public String getFechaActual(){
		Calendar ahoraCal = Calendar.getInstance();
		String fecha= Integer.toString(ahoraCal.get(Calendar.DATE))+"/"+Integer.toString((ahoraCal.get(Calendar.MONTH)+1))+"/"+Integer.toString(ahoraCal.get(Calendar.YEAR));
		return fecha;
	}	
	/**
	*Este metodo hace un split a la fecha
	*@param fecha tipo:String
	*@param estado tipo:boolean
	*@param booleanII tipo:boolean
	*@return fechita tipo.String
	*/
	public String hacerSplitFecha(String fecha, boolean estado, boolean booleanII){
		String[] fech=fecha.split("-");
		String[] cambioFech=fecha.split("/");
		String fechita="";
		if(fech.length==3 || cambioFech.length==3){
			for(int i=0; i<3; i++){
				if(estado==true){
					if(i==0){
						this.setAnyo(Integer.parseInt(fech[i]));
					}
					if(i==1){
						this.setMes(Integer.parseInt(fech[i]));
					}
					if(i==2){
						this.setDia(Integer.parseInt(fech[i]));
					}
				}else{
					if(booleanII==true){
						if(i==0){
							array[0]=Integer.parseInt(cambioFech[i]);
						}
						if(i==1){
							array[1]=Integer.parseInt(cambioFech[i]);
						}
						if(i==2){
							array[2]=Integer.parseInt(cambioFech[i]);
						}
					}else{
						if(i==0){
							arrayAComparar[0]=Integer.parseInt(cambioFech[i]);
						}
						if(i==1){
							arrayAComparar[1]=Integer.parseInt(cambioFech[i]);
						}
						if(i==2){
							arrayAComparar[2]=Integer.parseInt(cambioFech[i]);
						}
					}
				}
			}
			fechita=Integer.toString(getDia())+"/"+Integer.toString(getMes())+"/"+Integer.toString(getAnyo());
			return fechita;
		}
		return "noValido";
	}
	/**
	*Este metodo verifica el estado de las fechas
	*@param fecha tipo:String
	*@return true or false
	*/
	public boolean estadoDeFechas(String fecha){
		String fechaActual=this.hacerSplitFecha(this.getFechaActual(), false,false);
		String fechaAEvaluar=(hacerSplitFecha(fecha, false,true));
		boolean variable=false;
		if(array[0]>arrayAComparar[0] && array[1]==arrayAComparar[1] && array[2]==arrayAComparar[2]){
			return true;
		}
		if(array[0]==arrayAComparar[0] && array[1]>arrayAComparar[1] && array[2]==arrayAComparar[2]){
			return true;
		}
		if(array[0]<arrayAComparar[0] && array[1]<arrayAComparar[1] && array[2]>arrayAComparar[2]){
			return true;	
		}
		if(array[0]>arrayAComparar[0] && array[1]>arrayAComparar[1] && array[2]==arrayAComparar[2]){
			return true;	
		}
		if(array[0]<arrayAComparar[0] && array[1]>arrayAComparar[1] && array[2]==arrayAComparar[2]){
			return true;	
		}
		return false;
	}
	/**
	*Este metodo obtiene la hora del sistema
	*@return horaActual tipo:String
	*/
	public String getHoraActual(){
		Calendar calendario = Calendar.getInstance();
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		String horaActual=Integer.toString(hora)+":"+Integer.toString(minutos);
		return horaActual;
	}
	/**
	*Este metodo envia un dia
	*@param dia tipo:int
	*/
	public void setDia(int dia){
		this.dia=dia;
	}
	/**
	*Este metodo envia un mes
	*@param mes tipo:int
	*/
	public void setMes(int mes){
		this.mes=mes;
	}
	/**
	*Este metodo envia un anyo
	*@param anyo tipo:int
	*/
	public void setAnyo(int anyo){
		this.anyo=anyo;
	}
	/**
	*Este metodo obtiene un dia
	*@return dia tipo:int
	*/
	public int getDia(){
		return dia;
	}
	/**
	*Este metodo obtiene un mes
	*@return mes tipo:int
	*/
	public int getMes(){
		return mes;
	}
	/**
	*Este metodo obtiene un anyo
	*@return anyo tipo:int
	*/
	public int getAnyo(){
		return anyo;
	}
}