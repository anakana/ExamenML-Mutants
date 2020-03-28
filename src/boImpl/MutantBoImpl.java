package boImpl;

import java.util.Arrays;

import bo.MutantBo;
import entities.DNA;

public class MutantBoImpl implements MutantBo{
	
	private int countDNAMutant =0;

	@Override
	public boolean isMutant(DNA dna) {

		//Obtener datos de la BD
		
		String[] arrayDna = dna.getDna();
		int n =  arrayDna.length;
		int i=0, j=0, iDiagSup=0, iDiagInf=0;
		int diagSup = 0, diagInf =0, diagSup2 = n-1, diagInf2 = 2;
		boolean isValid = true, isMutant = false;
		String strFila = "", strColumn = "", strDiagIzqDerSup = "", strDiagIzqDerInf = "", strDiagDerIzqSup = "", strDiagDerIzqInf = "";
		char[][] m = toMatriz(arrayDna);
		
		while((i<n && iDiagInf<n && iDiagSup<n) && !isMutant && isValid) {
		
			//#################### FILAS Y COLUMNAS ####################
			//Se recorre la matriz en fila
			strFila += m[i][j];
			//Se recorre la matriz en columnas
			strColumn += m[i][j];
			if(j== n-1){
				i++;
				j=0;
				System.out.println("FILA ---> "+ strFila);
				System.out.println("Column ---> "+ strColumn);
				//analizar cadena, aumentar o no addmutante	
				if(strFila.length()>=4) {
					AnalizarMutante(strFila);
				}else if (strColumn.length()>=4) {
					AnalizarMutante(strColumn);
				}
				
				if(this.countDNAMutant>=2) {
					isMutant = true;
				}
				
				strFila = "";
				strColumn = "";
			}else {
				j++;				
			}
			
			//#################### OBLICUA IZQ A DER / ####################
			//Diagonal Superior
			strDiagIzqDerSup += m[iDiagSup-diagSup][diagSup];
			if(diagSup>iDiagSup){
				iDiagSup++;
				diagSup=0;
				System.out.println("Diagonal superior ---> "+ strDiagIzqDerSup);
				//analizar cadena, aumentar o no addmutante
				if(strDiagIzqDerSup.length()>=4) {
					AnalizarMutante(strDiagIzqDerSup);
					if(this.countDNAMutant>=2) {
						isMutant = true;
					}
				}
				
				strDiagIzqDerSup = "";
			}else {
				diagSup++;				
			}
			
			//Diagonal Inferior
			strDiagIzqDerInf += m[iDiagInf-diagSup][diagSup];
			if(diagInf>(n-iDiagInf-1)){
				iDiagInf++;
				diagInf=0;
				System.out.println("Diagonal inferior ---> "+ strDiagIzqDerInf);
				//analizar cadena, aumentar o no addmutante
				if(strDiagIzqDerInf.length()>=4) {
					AnalizarMutante(strDiagIzqDerInf);
					if(this.countDNAMutant>=2) {
						isMutant = true;
					}
				}
				strDiagIzqDerInf = "";
			}else {
				diagInf++;				
			}

		}
		
		System.out.println("Es Mutante?? ---> "+isMutant);
		
		
	/*	int matriz[][]
                = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
                };
 
        for (int i = 0, j = 0; i &lt; matriz.length;) {
 
            System.out.println(matriz[i][j]);
 
            if (j == matriz[0].length - 1) {
                i++;
                j = 0;
            } else {
                j++;
            }
 
        }*/
		
	/*
	 *var m=[
		[1, 3, 6, 10],
		[2, 5, 9, 13],
		[4, 8, 12, 15],
		[7, 11, 14, 16]
		];
		
		//primera parte diagonal superior
		for (var i=0;i<m.length;i++) {
		    for (var j=0;j<=i;j++) { 
		       alert (m[i-j][j]);
		    }
		}
		
		//segunda parte diagonal inferior
		for (var i=0;i<m.length;i++) {
		    for (var j=0;j<m.length-i-1;j++) { 
		        alert (m[m.length-j-1][j+i+1]);
		    }
		}*/
		
		System.out.println(this.toMatriz(dna.getDna()));
 
		return false;
	}
	
	//Complejidad N
	private char[][] toMatriz(String[] array){
		int n = array.length;
		int i=0,j = 0;
		char[][] matriz = new char[n][n];
		
		while (i<n) {
			//Caso: String[][] matriz --- Devuelve (, a, b, c)... el 1ero vacio
			//matriz[i] = array[j].split(""); 
			matriz[i] = array[j].toCharArray();
			//Arrays.asList(array[j]).forEach(System.out::println);
		}
		System.out.println("MATRIZ ----> "+matriz);
		return matriz;
	}

	private int AnalizarMutante(String cadena) {
		if(cadena.contains("AAAA")) {
			this.countDNAMutant++;
		}else if(cadena.contains("CCCC")) {
			this.countDNAMutant++;
		}else if(cadena.contains("GGGG")) {
			this.countDNAMutant++;
		}else if(cadena.contains("TTTT")) {
			this.countDNAMutant++;
		}
		
		System.out.println("CONTADOR ----->  "+this.countDNAMutant);
		return this.countDNAMutant;
	}
}
