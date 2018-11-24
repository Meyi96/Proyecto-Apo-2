package model;

import java.io.File;
import java.util.ArrayList;

import Hilos.Hilo_ProcesarDatos;

public class Tweet_Aplication {
	private Usuario usuarioRaiz;
	private PalabraRelevante raizRelevante;
	private Hashtag tendencias;
	private Link tendenciasLinks;
	private Hilo_ProcesarDatos Datos;
	private ArrayList<String> NombreArchivos;
	
	public Tweet_Aplication() {
		Datos = new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,"Nombre del archivo");
		Thread a = new Thread(Datos);
		a.start();
		while(usuarioRaiz == null) {
			usuarioRaiz = Datos.getCreado();
		}
		System.out.println(usuarioRaiz);
	}
	
	public static void main(String args[]) {
		Tweet_Aplication lel = new Tweet_Aplication();
	}
}
