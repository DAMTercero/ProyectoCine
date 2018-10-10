/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Pelicula;
import SQL.Ventanas.AMB;
import static SQL.Ventanas.AMB.*;
import SQL.Ventanas.Añadir;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikitess
 */
public class PeliculaFunciones {

    private AMB ventana;
    private PeliculaCRUD peliculaCRUD = new PeliculaCRUD();

    ////--Funciones para controlar la ventana--\\\\
    public void cambiarVentanaPelis() {
         //campos visibles para filtrar por si hacemos cambios
        labelTituloVentana.setText("PELÍCULAS");
        labelID.setText("ID Pélicula:");
        labelTitulo.setText("Título:");
        labelAnyo.setText("Año: ");
        labelDirector.setText("Director:");
        //campos que no estan visible por si hacemos cambios
        labelAP.setVisible(false);
        labelAS.setVisible(false);
        labelDuracion.setVisible(false);
        lableTrailer.setVisible(false);
        textoTrailer.setVisible(false);
        textoAcPr.setVisible(false);
        textoAcSe.setVisible(false);
        textoDuracion.setVisible(false);
    }

    public void abrirVentanaPelis(AMB ventana) {
        this.ventana = ventana;
        cambiarVentanaPelis();
        this.ventana.setVisible(true);
    }
    
    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        Añadir.labelID.setText("ID Pelicula");
        Añadir.labelTitulo.setText("Titulo:");
        Añadir.labelAnyo.setText("Año de estreno");
        Añadir.labelDirector.setText("Director");
        Añadir.labelAP.setText("Actor principal");
        Añadir.labelAS.setText("Actor secundario");
        Añadir.labelDuracion.setText("Duración");
        Añadir.labelTrailer.setText("Trailer");
        Añadir.labelDisponible.setText("Disponible");

        ventanaAñadir.peliculaFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }
    
     public void botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        Pelicula  pelicula = new Pelicula();
        //crear el empleado de filtrado
        int id = -1;
        if (AMB.textoID.getText().isEmpty()) {//evitar error de int null
            id = -1;
            pelicula.setID_PELICULA(id);
        } else {
            pelicula.setID_PELICULA(Integer.parseInt(AMB.textoID.getText()));
        }
        pelicula.setTITULO(AMB.textoTitulo.getText());
        pelicula.setANYO_STRENO(AMB.textoAnyo.getText());
        pelicula.setDIRECTOR(AMB.textoDirector.getText());
        //consulta a base de datos con su respuestaen forma de lista
        iniciarTabla();
         ArrayList<Pelicula> peliculas = new ArrayList<>(peliculaCRUD.filtrarPeliculas(pelicula, "sqlite"));
        if (peliculas.size() > 0) {
            ponerEnTabla(peliculas);
            //iniciarTabla();
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
        }
    }

    //poner COLUMNAS
    public void iniciarTabla() {
        ventana.modeloTabla.setColumnCount(0);
        // String columna[] = new String[]{"ID_EMPLEADO", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "FECHA_NAC", "FECHA_FIN", "NACIONALIDAD", "CARGO", "DISPONIBLE"}; PORQUE NO ME DEJA PONER UNA COLUMAN ASI!
        ventana.modeloTabla.addColumn("ID_PELICULA");
        ventana.modeloTabla.addColumn("TITULO");
        ventana.modeloTabla.addColumn("AÑO ESTRENO");
        ventana.modeloTabla.addColumn("DIRECTOR");
        ventana.modeloTabla.addColumn("ACTOR PRINCIPAL");
        ventana.modeloTabla.addColumn("ACTOR SECUNDARIO");
        ventana.modeloTabla.addColumn("DURACION");
        ventana.modeloTabla.addColumn("TRAILER");
        ventana.modeloTabla.addColumn("DISPONIBLE");       
    }

    public void ponerEnTabla(List<Pelicula> peliculas) {

        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosPelicula[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Pelicula pelicula : peliculas) {
            datosPelicula[0] = pelicula.getID_PELICULA();
            datosPelicula[1] = pelicula.getTITULO();
            datosPelicula[2] = pelicula.getANYO_STRENO();
            datosPelicula[3] = pelicula.getDIRECTOR();
            datosPelicula[4] = pelicula.getACTOR_PRINCI();
            datosPelicula[5] = pelicula.getACTOR_SECUN();
            datosPelicula[6] = pelicula.getDURACION();
            datosPelicula[7] = pelicula.getTRAILER();
            datosPelicula[8] = pelicula.isDISPONIBLE();            
            //insertar la fila
            ventana.modeloTabla.addRow(datosPelicula);
        }

    }

    public PeliculaFunciones() {
    }

    public PeliculaFunciones(AMB ventana, PeliculaCRUD peliculaCRUD) {
        this.ventana = ventana;
        this.peliculaCRUD = peliculaCRUD;
    }

    public AMB getVentana() {
        return ventana;
    }

    public void setVentana(AMB ventana) {
        this.ventana = ventana;
    }

    public PeliculaCRUD getPeliculaCRUD() {
        return peliculaCRUD;
    }

    public void setPeliculaCRUD(PeliculaCRUD peliculaCRUD) {
        this.peliculaCRUD = peliculaCRUD;
    }

}
