package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

	/**
	 * @param args
	 */
	public static MatrizSudoku tablero;
	public static char[][] sudoku = new char[9][9];
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File f = new File( "sudk" );
		BufferedReader entrada;
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			String linea;
			int filas = 0;
			while(entrada.ready()){
				linea = entrada.readLine();
				leerSudoku(linea,filas);
				filas = filas+1;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		tablero = new MatrizSudoku(sudoku);
		tablero.imprimirTablero(tablero.getTableroInicial());
		
		String resultado = "";
		
		if(tablero.estaCompleto(tablero.getTableroInicial())){
			resultado += "El tablero esta completo y";
		}else{
			resultado += "El tablero no esta completo y";
		}
		if(tablero.estaCorrecto()){
			resultado += " el tablero esta correcto";
		}else{
			resultado += " el tablero no esta correcto";
		}
		System.out.println(resultado);
		
		tablero.resolver();
		//tablero.llenarTablero();
	}

	public static void leerSudoku(String tableroSudoku, int indiceFila){
		String[] filas =  tableroSudoku.split(",");
		char[] caracteres = new char[9];
		for (int i = 0; i < filas.length; i++) {
			char[] caracter = filas[i].toCharArray();
			caracteres[i] = caracter[0];
		}		
		for (int i = 0; i < caracteres.length; i++) {
			sudoku[indiceFila][i] = caracteres[i];
		}
	}

}