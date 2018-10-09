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

    private int ID_SALA;
    private int ID_PELICULA;
    private int ID_EMPLEADO;
    private String FECHAEMISION;//p.e: "01/01/2018"
    private String SESION; //p.e.: "12:00 - 16:00"

    public Historico() {
    }

    public Historico(int ID_SALA, int ID_PELICULA, int ID_EMPLEADO, String FECHAEMISION, String SESION) {
        this.ID_SALA = ID_SALA;
        this.ID_PELICULA = ID_PELICULA;
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.FECHAEMISION = FECHAEMISION;
        this.SESION = SESION;
    }

    public int getID_SALA() {
        return ID_SALA;
    }

    public void setID_SALA(int ID_SALA) {
        this.ID_SALA = ID_SALA;
    }

    public int getID_PELICULA() {
        return ID_PELICULA;
    }

    public void setID_PELICULA(int ID_PELICULA) {
        this.ID_PELICULA = ID_PELICULA;
    }

    public int getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(int ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getFECHAEMISION() {
        return FECHAEMISION;
    }

    public void setFECHAEMISION(String FECHAEMISION) {
        this.FECHAEMISION = FECHAEMISION;
    }

    public String getSESION() {
        return SESION;
    }

    public void setSESION(String SESION) {
        this.SESION = SESION;
    }

}
