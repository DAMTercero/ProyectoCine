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
public class Pelicula {
    private int ID_PELICULA;
    private String TITULO;
    private String ANYO_STRENO;
    private String DIECTOR;
    private String ACTOR_PRINCI;
    private String ACTOR_SECUN;
    private String DURACION;
    private String TRAILER;
    private boolean EMITIENDOSE;

    public int getID_PELICULA() {
        return ID_PELICULA;
    }

    public void setID_PELICULA(int ID_PELICULA) {
        this.ID_PELICULA = ID_PELICULA;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getANYO_STRENO() {
        return ANYO_STRENO;
    }

    public void setANYO_STRENO(String ANYO_STRENO) {
        this.ANYO_STRENO = ANYO_STRENO;
    }

    public String getDIECTOR() {
        return DIECTOR;
    }

    public void setDIECTOR(String DIECTOR) {
        this.DIECTOR = DIECTOR;
    }

    public String getACTOR_PRINCI() {
        return ACTOR_PRINCI;
    }

    public void setACTOR_PRINCI(String ACTOR_PRINCI) {
        this.ACTOR_PRINCI = ACTOR_PRINCI;
    }

    public String getACTOR_SECUN() {
        return ACTOR_SECUN;
    }

    public void setACTOR_SECUN(String ACTOR_SECUN) {
        this.ACTOR_SECUN = ACTOR_SECUN;
    }

    public String getDURACION() {
        return DURACION;
    }

    public void setDURACION(String DURACION) {
        this.DURACION = DURACION;
    }

    public String getTRAILER() {
        return TRAILER;
    }

    public void setTRAILER(String TRAILER) {
        this.TRAILER = TRAILER;
    }

    public boolean isEMITIENDOSE() {
        return EMITIENDOSE;
    }

    public void setEMITIENDOSE(boolean EMITIENDOSE) {
        this.EMITIENDOSE = EMITIENDOSE;
    }
    
    
}
