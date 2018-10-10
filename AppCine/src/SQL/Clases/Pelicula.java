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

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private String conector;

    private int ID_PELICULA;
    private String TITULO;
    private String ANYO_STRENO;
    private String DIRECTOR;
    private String ACTOR_PRINCI;
    private String ACTOR_SECUN;
    private String DURACION;
    private String TRAILER;
    private boolean DISPONIBLE;

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public Pelicula() {
    }

    public Pelicula(int ID_PELICULA, String TITULO, String ANYO_STRENO, String DIRECTOR, String ACTOR_PRINCI, String ACTOR_SECUN, String DURACION, String TRAILER, boolean DISPONIBLE) {
        this.ID_PELICULA = ID_PELICULA;
        this.TITULO = TITULO;
        this.ANYO_STRENO = ANYO_STRENO;
        this.DIRECTOR = DIRECTOR;
        this.ACTOR_PRINCI = ACTOR_PRINCI;
        this.ACTOR_SECUN = ACTOR_SECUN;
        this.DURACION = DURACION;
        this.TRAILER = TRAILER;
        this.DISPONIBLE = DISPONIBLE;
    }

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

    public String getDIRECTOR() {
        return DIRECTOR;
    }

    public void setDIRECTOR(String DIRECTOR) {
        this.DIRECTOR = DIRECTOR;
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

    public boolean isDISPONIBLE() {
        return DISPONIBLE;
    }

    public void setDISPONIBLE(boolean DISPONIBLE) {
        this.DISPONIBLE = DISPONIBLE;
    }

}
