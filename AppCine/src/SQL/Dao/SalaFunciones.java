/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Ventanas.AMB;
import static SQL.Ventanas.AMB.*;

/**
 *
 * @author ikitess
 */
public class SalaFunciones {

    private AMB ventana;
    private SalaCRUD salaCRUD;

    ////--Funciones para controlar la ventana--\\\\
    public void cambiarVentanaSalas() {
        //campos visibles para filtrar
        labelTituloVentana.setText("SALAS");
        labelID.setText("ID Sala:");
        textoID.setVisible(true);
        //campos que no estan visible por si hacemos cambios
        labelTitulo.setText(" ");//asi no se rompe la estructura de labels y textfields
        labelAnyo.setVisible(false);
        labelDirector.setVisible(false);
        labelAP.setVisible(false);
        labelAS.setVisible(false);
        labelDuracion.setVisible(false);
        lableTrailer.setVisible(false);
        //campos que no estan visible por si hacemos cambios
        textoTitulo.setVisible(false);
        textoDirector.setVisible(false);
        textoAnyo.setVisible(false);
        textoAcPr.setVisible(false);
        textoAcSe.setVisible(false);
        textoDuracion.setVisible(false);
        textoTrailer.setVisible(false);
    }

    public void abrirVentanaSalas(AMB ventana) {
        this.ventana = ventana;
        cambiarVentanaSalas();
        this.ventana.setVisible(true);
    }

    public SalaFunciones() {
    }

    public SalaFunciones(AMB ventana, SalaCRUD salaCRUD) {
        this.ventana = ventana;
        this.salaCRUD = salaCRUD;
    }

    public AMB getVentana() {
        return ventana;
    }

    public void setVentana(AMB ventana) {
        this.ventana = ventana;
    }

    public SalaCRUD getSalaCRUD() {
        return salaCRUD;
    }

    public void setSalaCRUD(SalaCRUD salaCRUD) {
        this.salaCRUD = salaCRUD;
    }

}
