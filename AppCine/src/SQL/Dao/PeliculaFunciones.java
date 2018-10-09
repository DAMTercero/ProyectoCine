/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Ventanas.AMB;
import static SQL.Ventanas.AMB.*;
import SQL.Ventanas.Añadir;

/**
 *
 * @author ikitess
 */
public class PeliculaFunciones {

    private AMB ventana;
    private PeliculaCRUD peliculaCRUD;

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
