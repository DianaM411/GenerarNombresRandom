package com.diana;
/*Ejercicio B4 - Nombre y apellidos
Implementa un programa que genere aleatoriamente nombres de persona (combinando nombres
y apellidos de ‘usa_nombres.txt’ y ‘usa_apellidos.txt’). Se le pedirá al usuario cuántos nombres de
persona desea generar y a qué archivo añadirlos (por ejemplo ‘usa_personas.txt’).*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner lector = new Scanner(System.in);
        FileWriter escribir = null;
        try {
            //Ponemos las rutas relativas (que se pueden abrir de Windows y Linux) de los ficheros a leer
            String rutaRelativaNombres = "usa_nombres.txt";
            String rutaRelativaApellidos = "usa_apellidos.txt";
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsolutaNombres = pwd + File.separator + rutaRelativaNombres;
            String rutaAbsolutaApellidos = pwd + File.separator + rutaRelativaApellidos;

            //Manera sencilla de cargar todas las líneas de un archivo en un ArrayList
            ArrayList<String> listaNombres = new ArrayList<>(Files.readAllLines(Paths.get(rutaAbsolutaNombres)));
            ArrayList<String> listaApellidos = new ArrayList<>(Files.readAllLines(Paths.get(rutaAbsolutaApellidos)));

            //Mezclamos los elementos del Arraylist
            Collections.shuffle(listaNombres);
            Collections.shuffle(listaApellidos);

            //Preguntamos al usuario cuantos nombres quiere generar
            System.out.println("Cuantos nombres quieres generar?");
            int cuantos = lector.nextInt();

            //Pedimos el archivo a escribir
            System.out.println("Introduzca el nombre de archivo");
            String limpiarScanner = lector.nextLine();
            String rutaRelativaEscribir = lector.nextLine() + ".txt";
            String rutaAbsolutaEscribir = pwd + File.separator + rutaRelativaEscribir;
            File nombreRandoms = new File(rutaAbsolutaEscribir);
            escribir = new FileWriter(nombreRandoms);
            BufferedWriter escribirBuffer = new BufferedWriter(escribir);

            //Escribimos los nombres y apellidos de los arraylists random en el fichero que nos indica el usuario
            for (int i = 0; i < cuantos; i++) {
                escribirBuffer.write(listaNombres.get(i));
                escribirBuffer.write(" ");
                escribirBuffer.write(listaApellidos.get(i));
                escribirBuffer.newLine();
            }

            //cerramos buffer
            escribirBuffer.close();

        } catch (Exception e) { //manejamos excepciones
            e.printStackTrace();
        } finally {
            lector.close();  //cerramos el scanner
            escribir.close();
        }
    }

}

