/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();

    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }

    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }

    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles {
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");
        int i, j, filasA, columnasA;
        filasA = a.getDimension().height;
        columnasA = a.getDimension().width;
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) {
            for (i = 0; i < columnasA; i++) {
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j];
            }
        }
        return matrizResultante;
    }

    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {
                ret += String.format("%3d", datos[i][j]);
                if (j != getDimension().height - 1) ret += ", ";
            }
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        }
        ret += "]\n";
        return ret;
    }


    //matriz inversa
	    public static Matriz inversa(Matriz d) throws CloneNotSupportedException {
	        int n=d.getDimension().height;  //dimensión de la matriz
	        Matriz a=(Matriz)d.clone();
	        Matriz b=new Matriz(n,n,false);
	        Matriz c=new Matriz(n,n,false);

	        for(int i=0; i<n; i++){
	            b.datos[i][i]=1;
	        }

	        for(int k=0; k<n-1; k++){
	            for(int i=k+1; i<n; i++){
	                for(int s=0; s<n; s++){
	                    b.datos[i][s]-=a.datos[i][k]*b.datos[k][s]/a.datos[k][k];
	                }
	                for(int j=k+1; j<n; j++){
	                    a.datos[i][j]-=a.datos[i][k]*a.datos[k][j]/a.datos[k][k];
	                }
	            }
	        }
	        return a;
	    }


         //producto de dos matrices
		    public static Matriz producto(Matriz a, Matriz b){
		        Matriz resultado= new Matriz(a.getDimension().height,a.getDimension().height,false);
		        for(int i=0; i<a.getDimension().height; i++){
		            for(int j=0; j<a.getDimension().height; j++){
		                for(int k=0; k<a.getDimension().height; k++){
		                    resultado.datos[i][j]+=a.datos[i][k]*b.datos[k][j];
		                }
		            }
		        }
		        return resultado;
    }

}
