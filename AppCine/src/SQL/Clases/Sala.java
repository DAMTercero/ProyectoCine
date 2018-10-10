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
public class Sala {

     private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private String conector;

    private int ID_SALA;
    private int CAPACIDAD;
    private int PANTALLA;
    private String APERTURA;
    private boolean DISPONIBLE;

    public Sala() {
    }

    public Sala(int ID_SALA, int CAPACIDAD, int PANTALLA, String APERTURA, boolean DISPONIBLE) {
        this.ID_SALA = ID_SALA;
        this.CAPACIDAD = CAPACIDAD;
        this.PANTALLA = PANTALLA;
        this.APERTURA = APERTURA;
        this.DISPONIBLE = DISPONIBLE;

    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public int getID_SALA() {
        return ID_SALA;
    }

    public void setID_SALA(int ID_SALA) {
        this.ID_SALA = ID_SALA;
    }

    public int getCAPACIDAD() {
        return CAPACIDAD;
    }

    public void setCAPACIDAD(int CAPACIDAD) {
        this.CAPACIDAD = CAPACIDAD;
    }

    public int getPANTALLA() {
        return PANTALLA;
    }

    public void setPANTALLA(int PANTALLA) {
        this.PANTALLA = PANTALLA;
    }

    public String getAPERTURA() {
        return APERTURA;
    }

    public void setAPERTURA(String APERTURA) {
        this.APERTURA = APERTURA;
    }

    public boolean getDISPONIBLE() {
        return DISPONIBLE;
    }

    public void setDISPONIBLE(boolean DISPONIBLE) {
        this.DISPONIBLE = DISPONIBLE;
    }

}
