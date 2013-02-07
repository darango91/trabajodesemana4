package modelo;

import java.util.ArrayList;

public class MatrizSudoku {

	private char[][] tableroInicial = new char[9][9];
	private char[][] tableroPequeño = new char[3][3];
	private char[] fc_temp = new char[9];
	private boolean esCorrecto;
	private ArrayList<char[][]> matricesInternas = new ArrayList<char[][]>();
	private ArrayList<char[]> filas = new ArrayList<char[]>();
	private ArrayList<char[]> columnas = new ArrayList<char[]>();
	
	public MatrizSudoku(char[][] tableroInicial) {
		super();
		this.tableroInicial = tableroInicial;
		if(this.estaCompleto()){
			obtenerMatricesInternas();
			sacarEnArreglo(0);
			sacarEnArreglo(1);
		}
	}
	
	public boolean estaCompleto(){
		
		for (int i = 0; i < tableroInicial.length; i++) {
			for (int j = 0; j < tableroInicial[i].length; j++) {
				if(tableroInicial[i][j] == '0' || tableroInicial[i][j] == ' '){
					return false;
				}
			}
		}
		
		return true;
	}	
	
	public boolean estaCorrecto(){
		esCorrecto =  true;
		
		if(tieneRepetidosCoF(0) || tieneRepetidosCoF(1) || tieneRepetidosEnSub_Matrices()) esCorrecto = false;
			
		return esCorrecto;
	}
	
	public boolean tieneRepetidosCoF(int op){//0 para filas, 1 para columnas
		
		if(op == 0){
			char[] temp = new char[9];
			for (int i = 0; i < filas.size(); i++) {
				temp = filas.get(i);
				boolean tiene = tieneRepetidos(temp);
				if(tiene == true){
					System.out.println("Valor del boolean: "+tiene);
					return true;
				}
			}
		}else{
			char[] temp = new char[9];
			for (int i = 0; i < columnas.size(); i++) {
				temp = columnas.get(i);
				boolean tiene = tieneRepetidos(temp);
				if(tiene){
					System.out.println("Valor del boolean: "+tiene);
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	public boolean tieneRepetidosEnSub_Matrices(){
		
		char[][] temp = new char[3][3];
		for (int i = 0; i < matricesInternas.size(); i++) {
			temp = matricesInternas.get(i);
			boolean tiene = tieneRepetidosMatrizIterna(temp);
			if(tiene){
				System.out.println("Valor del boolean: "+tiene);
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * Este metodo sirve para sacar en arreglos cada una de las filas o columnas (depende de
	 * la opcion dada) y se almacenan en las listas que le corresponde.
	 */
	public void sacarEnArreglo(int opcion){ //0 por filas, 1 por columnas
		
		if(opcion == 0){//por filas
			
			for (int i = 0; i < tableroInicial.length; i++) {
				char[] arreglo = new char[9];
				for (int j = 0; j < tableroInicial.length; j++) {
					arreglo[j] = tableroInicial[i][j];
				}
				filas.add(arreglo);
			}
		}else{
			for (int i = 0; i < tableroInicial.length; i++) {
				char[] arreglo = new char[9];
				
				for (int j = 0; j < tableroInicial.length; j++) {
					char var = tableroInicial[j][i];
					arreglo[j] = var;
				}
				columnas.add(arreglo);
			}
		}
	}
	
	/*
	 * Este método sirve para revisar si un arreglo tiene o no valores repetidos dentro
	 * de este.
	 */
	public boolean tieneRepetidos(char[] arreglo){

		for(int i=0;i<arreglo.length-1;i++){
			for(int j=i+1;j<arreglo.length;j++){
				if(arreglo[i]==arreglo[j])
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Este método valida que una matriz interna (la matriz de 3x3) no tenga valores repetidos
	 */
	public boolean tieneRepetidosMatrizIterna(char[][] matrizInterna){
		char[] arreglo = new char[9];
		int indice = 0;
		for (int i = 0; i < matrizInterna.length; i++) {
			for (int j = 0; j < matrizInterna.length; j++) {
				arreglo[indice] = matrizInterna[i][j];
				indice++;
			}
		}
		
		boolean tieneRepetidos = tieneRepetidos(arreglo);
		
		return tieneRepetidos;
	}
	
	/*
	 * Con este metodo se hallan las sub-matrices que conforman el sudoku y se almacenan en un
	 * ArrayList llamado matricesInternas
	 */
	public void obtenerMatricesInternas(){
		char[][] matriz = new char[3][3];
		
		int cIni = 0, cFin = 2;
		int fIni = 0, fFin = 2;
		int cantMatrices = 0;
		
		do {
			
			switch (cantMatrices) {
			case 0:
				cIni = 0; cFin = 2;
				fIni = 0; fFin = 2;
			break;

			case 1:
				cIni = 0; cFin = 2;
				fIni = 3; fFin = 5;
			break;
			
			case 2:
				cIni = 0; cFin = 2;
				fIni = 6; fFin = 8;
			break;
			
			case 3:
				cIni = 3; cFin = 5;
				fIni = 0; fFin = 2;
			break;
				
			case 4:
				cIni = 3; cFin = 5;
				fIni = 3; fFin = 5;
			break;
			
			case 5:
				cIni = 3; cFin = 5;
				fIni = 6; fFin = 8;
			break;
			
			case 6:
				cIni = 6; cFin = 8;
				fIni = 0; fFin = 2;
			break;
			
			case 7:
				cIni = 6; cFin = 8;
				fIni = 3; fFin = 5;
			break;
			
			case 8:
				cIni = 6; cFin = 8;
				fIni = 6; fFin = 8;
			break;
			
			default:
				break;
			}
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i+fIni<9 && j+cIni<9){
						matriz[i][j] =  tableroInicial[i+fIni][j+cIni];
					}
				}
			}
			
			matricesInternas.add(matriz);
			
			cantMatrices = cantMatrices+1;
			
		} while (cantMatrices < 9);
	}

	public void imprimirTablero(){
		String matriz = "";
		for (int i = 0; i < tableroInicial.length; i++) {
			for (int j = 0; j < tableroInicial.length; j++) {
				if(j==0) matriz += "| ";
				if(j != 8){
					if(j==2||j==5){
						matriz += tableroInicial[i][j]+" || ";
					}else{
						matriz += tableroInicial[i][j]+" | ";
					}
				}else{
					if(i==2||i==5){
					  //matriz += "| 1 | 2 | 3 || 4 | 5 | 6 || 7 | 8 | 9 |"
						matriz += tableroInicial[i][j]+" |\n";
						matriz += "---------------------------------------\n";
					}else{
						matriz += tableroInicial[i][j]+" |\n";
					}
				}
			}
		}
		System.out.println(matriz);
	}
	
	public void imprimirFilasOColumnas(int op){ //0 para filas, 1 para columnas
		if(op == 0){
			System.out.println("Contenido por filas");
			char[] temp = new char[9];
			for (int i = 0; i < filas.size(); i++) {
				temp = filas.get(i);
				System.out.println("Fila numero: "+i);
				for (int j = 0; j < temp.length; j++) {
					System.out.print(temp[j]+" ");
				}
				System.out.println("\n");
			}
		}else{
			System.out.println("Contenido por columnas");
			char[] temp = new char[9];
			for (int i = 0; i < columnas.size(); i++) {
				temp = columnas.get(i);
				System.out.println("Columna numero: "+i);
				for (int j = 0; j < temp.length; j++) {
					System.out.print(temp[j]+" ");
				}
				System.out.println("\n");
			}
		}
	}

}