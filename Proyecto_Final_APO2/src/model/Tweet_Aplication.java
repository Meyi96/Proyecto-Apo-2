package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.tools.FileObject;

import Hilos.Hilo_ProcesarDatos;

public class Tweet_Aplication {
	private Usuario usuarioRaiz;
	private PalabraRelevante raizRelevante;
	private Hashtag tendencias;
	private Link tendenciasLinks;
	private Hilo_ProcesarDatos Datos;
	private ArrayList<String> NombreArchivos;
	
	public Tweet_Aplication() throws IOException {
		tendencias = new Hashtag("", null, 0);
		tendenciasLinks = new Link("", null);
		NombreArchivos = new ArrayList<>();
		
		File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
		FileInputStream wow = new FileInputStream(wr);
		ObjectInputStream entrada = new ObjectInputStream(wow);
		Datos = new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,"./Persistencia/Usuarios/Elon Musk");
		Thread a = new Thread(Datos);
		a.start();
		usuarioRaiz = Datos.getCreado();
		System.out.println(usuarioRaiz);
	}

	public void RegistrarUsuario(String data) throws IOException {
		System.out.println(data);
		String info[] = data.split("\n");
		String nombre_archivo = "./Persistencia/Usuarios/"+info[16];
		File newF = new File(nombre_archivo);
		NombreArchivos.add(nombre_archivo);
		FileWriter write = new FileWriter(newF);
		BufferedWriter writer = new BufferedWriter(write);
		for (int i = 0; i < info.length; i++) {
			System.out.println("Escribiendo");
			writer.write(info[i]);
			writer.write("\n");
		}
		writer.close();
		Datos = new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,nombre_archivo);
		Thread a = new Thread(Datos);
		a.start();
		guardarProgreso();
	}

	private void guardarProgreso() throws IOException {
		File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
		FileOutputStream wr2 = new FileOutputStream(wr);
		ObjectOutputStream write = new ObjectOutputStream(wr2);
		write.writeObject(this);
		write.close();
	}
}
