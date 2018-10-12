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
    private String FECHA_APERTURA;
    private String PANTALLA;
    private boolean DISPONIBLE;

    public Sala() {
    }

    public Sala(int ID_SALA, int CAPACIDAD, String FEC_APERTURA, String PANTALLA, boolean DISPONIBLE) {
        this.ID_SALA = ID_SALA;
        this.CAPACIDAD = CAPACIDAD;
        this.FECHA_APERTURA = FEC_APERTURA;
        this.PANTALLA = PANTALLA;
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

    public String getPANTALLA() {
        return PANTALLA;
    }

    public void setPANTALLA(String PANTALLA) {
        this.PANTALLA = PANTALLA;
    }

    public String getFECHA_APERTURA() {
        return FECHA_APERTURA;
    }

    public void setFECHA_APERTURA(String FECHA_APERTURA) {
        this.FECHA_APERTURA = FECHA_APERTURA;
    }

    public boolean getDISPONIBLE() {
        return DISPONIBLE;
    }

    public void setDISPONIBLE(boolean DISPONIBLE) {
        this.DISPONIBLE = DISPONIBLE;
    }

}
