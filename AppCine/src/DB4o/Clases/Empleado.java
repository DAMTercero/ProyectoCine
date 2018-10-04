package DB4o.Clases;

public class Empleado {
	
	private int idEmpleado;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNac;
	private String fechaContrato;
	private String fechaFin;
	private String nacionalidad;
	private String cargo;
	private Boolean activo=true;
	
	
	public Empleado() {
		
	}


	public Empleado(int idEmpleado, String nombre, String apellido1, String apellido2, String fechaNac,
			String fechaContrato, String fechaFin, String nacionalidad, String cargo) {
		
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNac = fechaNac;
		this.fechaContrato = fechaContrato;
		this.fechaFin = fechaFin;
		this.nacionalidad = nacionalidad;
		this.cargo = cargo;
		
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getFechaContrato() {
		return fechaContrato;
	}


	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}


	public String getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	

}
