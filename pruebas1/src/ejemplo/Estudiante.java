package ejemplo;

public class Estudiante {
	
	private String nombre;
	private String codigo;
	private int edad;
	private Estudiante siguiente;
	
	
	public Estudiante getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Estudiante siguiente) {
		this.siguiente = siguiente;
	}
	
	public Estudiante() {
		edad = (int)(Math.random()*20)+1;
	}	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Estudiante(String nombre, String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
