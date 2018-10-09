/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Clases;

/**
 *
 * @author 9fdam07
 */
public class Historico {

    private int id_sala;
    private int id_pelicula;
    private int id_empleado;
    private String fechaEmision;//p.e: "01/01/2018"
    private String sesion; //p.e.: "12:00 - 16:00"

    public Historico() {
    }

    public Historico(int id_sala, int id_pelicula, int id_empleado, String fechaEmision, String sesion) {
        this.id_sala = id_sala;
        this.id_pelicula = id_pelicula;
        this.id_empleado = id_empleado;
        this.fechaEmision = fechaEmision;
        this.sesion = sesion;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

}
