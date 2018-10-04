package DB4o.Clases;

public class Pelicula {
	
	private int idPelicula;
	private String titulo;
	private String anyoEstreno;
	private String director;
	private String actorPrinci;
	private String actorSecun;
	private String duracion;
	private String trailer;
	private Boolean eliminada=false;
	
	
	public Pelicula() {
		
	}

	


	public Pelicula(int idPelicula) {
		
		this.idPelicula = idPelicula;
	}




	public Pelicula(int idPelicula, String titulo, String anyoEstreno, String director,
			String actorPrinci, String actorSecun, String duracion, String trailer) {
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.anyoEstreno = anyoEstreno;
		this.director = director;
		this.actorPrinci = actorPrinci;
		this.actorSecun = actorSecun;
		this.duracion = duracion;
		this.trailer = trailer;
		
	}


	public int getIdPelicula() {
		return idPelicula;
	}


	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}



	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAnyoEstreno() {
		return anyoEstreno;
	}


	public void setAnyoEstreno(String anyoEstreno) {
		this.anyoEstreno = anyoEstreno;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getActorPrinci() {
		return actorPrinci;
	}


	public void setActorPrinci(String actorPrinci) {
		this.actorPrinci = actorPrinci;
	}


	public String getActorSecun() {
		return actorSecun;
	}


	public void setActorSecun(String actorSecun) {
		this.actorSecun = actorSecun;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	public String getTrailer() {
		return trailer;
	}


	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}




	public Boolean getEliminada() {
		return eliminada;
	}


	public void setEliminada(Boolean eliminada) {
		this.eliminada = eliminada;
	}
	
	
	
	
	

}
