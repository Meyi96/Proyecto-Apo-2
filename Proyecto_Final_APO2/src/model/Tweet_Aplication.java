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
import java.io.Serializable;
import java.util.ArrayList;

import javax.tools.FileObject;

import Hilos.Hilo_ProcesarDatos;

public class Tweet_Aplication implements Serializable{
	private Usuario usuarioRaiz;
	private PalabraRelevante raizRelevante;
	private Hashtag tendencias;
	private Link tendenciasLinks;
	private Hilo_ProcesarDatos Datos;
	private ArrayList<String> NombreArchivos;
	
	public Tweet_Aplication() throws IOException, ClassNotFoundException {
		try {
			File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
			FileInputStream wow = new FileInputStream(wr);
			ObjectInputStream entrada = new ObjectInputStream(wow);
			Tweet_Aplication temp = (Tweet_Aplication)entrada.readObject();
			usuarioRaiz = temp.usuarioRaiz;
			raizRelevante = temp.raizRelevante;
			tendencias = temp.tendencias;
			tendenciasLinks = temp.tendenciasLinks;
			NombreArchivos = temp.NombreArchivos;
			entrada.close();
			Datos = new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,"./Persistencia/Usuarios/Elon Musk");
			Thread a = new Thread(Datos);
			a.start();
			usuarioRaiz = Datos.getCreado();
			System.out.println(usuarioRaiz);
		} catch (Exception e) {
			tendencias = new Hashtag("", null, 0);
			tendenciasLinks = new Link("", null);
			NombreArchivos = new ArrayList<>();
			Datos = new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,"./Persistencia/Usuarios/Elon Musk");
			Thread a = new Thread(Datos);
			a.start();
			usuarioRaiz = Datos.getCreado();
			System.out.println(usuarioRaiz);
			guardarProgreso();
		}
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
