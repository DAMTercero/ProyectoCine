/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Clases;

/**
 *
 * @author josep
 */
public class Empleado {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private String conector;

    private int ID_EMPLEADO;
    private String NOMBRE;
    private String APELLIDO1;
    private String APELLIDO2;
    private String FECHA_NAC;
    private String FECHA_CONTRATO;
    private String FECHA_FIN;
    private String NACIONALIDAD;
    private String CARGO;
    private boolean DISPONIBLE;

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public Empleado() {
    }

    public Empleado(int ID_EMPLEADO, String NOMBRE, String APELLIDO1, String APELLIDO2, String FECHA_NAC, String FECHA_CONTRATO, String FECHA_FIN, String NACIONALIDAD, String CARGO, boolean DISPONIBLE) {
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.NOMBRE = NOMBRE;
        this.APELLIDO1 = APELLIDO1;
        this.APELLIDO2 = APELLIDO2;
        this.FECHA_NAC = FECHA_NAC;
        this.FECHA_CONTRATO = FECHA_CONTRATO;
        this.FECHA_FIN = FECHA_FIN;
        this.NACIONALIDAD = NACIONALIDAD;
        this.CARGO = CARGO;
        this.DISPONIBLE = DISPONIBLE;
    }

    public int getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(int ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO1() {
        return APELLIDO1;
    }

    public void setAPELLIDO1(String APELLIDO1) {
        this.APELLIDO1 = APELLIDO1;
    }

    public String getAPELLIDO2() {
        return APELLIDO2;
    }

    public void setAPELLIDO2(String APELLIDO2) {
        this.APELLIDO2 = APELLIDO2;
    }

    public String getFECHA_NAC() {
        return FECHA_NAC;
    }

    public void setFECHA_NAC(String FECHA_NAC) {
        this.FECHA_NAC = FECHA_NAC;
    }

    public String getFECHA_CONTRATO() {
        return FECHA_CONTRATO;
    }

    public void setFECHA_CONTRATO(String FECHA_CONTRATO) {
        this.FECHA_CONTRATO = FECHA_CONTRATO;
    }

    public String getFECHA_FIN() {
        return FECHA_FIN;
    }

    public void setFECHA_FIN(String FECHA_FIN) {
        this.FECHA_FIN = FECHA_FIN;
    }

    public String getNACIONALIDAD() {
        return NACIONALIDAD;
    }

    public void setNACIONALIDAD(String NACIONALIDAD) {
        this.NACIONALIDAD = NACIONALIDAD;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }

    public boolean isDISPONIBLE() {
        return DISPONIBLE;
    }

    public void setDISPONIBLE(boolean DISPONIBLE) {
        this.DISPONIBLE = DISPONIBLE;
    }

}
