package DB4o.Clases;

public class Sala {

	private int idSala;
	private int capacidad;
	private int pantalla;
	private String apertura;//17:00
	private String horario;//17:00-19:00/
	private Boolean disponible=true;
	
	
	public Sala() {
		
	}


	public Sala(int idSala, int capacidad, int pantalla, String apertura, String horario) {
		
		this.idSala = idSala;
		this.capacidad = capacidad;
		this.pantalla = pantalla;
		this.apertura = apertura;
		this.horario = horario;
		
	}


	public int getIdSala() {
		return idSala;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public int getPantalla() {
		return pantalla;
	}


	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}


	public String getApertura() {
		return apertura;
	}


	public void setApertura(String apertura) {
		this.apertura = apertura;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public Boolean getDisponible() {
		return disponible;
	}


	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	
	
	
}
