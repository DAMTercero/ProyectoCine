package Clases;

public class Historico {
	
	private String fechaEmision;
	private String horario;
	private Sala sala;
	private Empleado empleado;
	private Pelicula pelicula;
	
	public Historico() {
		
	}

	public Historico(String fechaEmision, String horario, Sala sala, Empleado empleado, Pelicula pelicula) {
		
		this.fechaEmision = fechaEmision;
		this.horario = horario;
		this.sala = sala;
		this.empleado = empleado;
		this.pelicula = pelicula;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}
	 

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	

	
	
}
