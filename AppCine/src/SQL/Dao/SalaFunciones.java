/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Sala;
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
public class SalaFunciones {

    private AMB ventana;
    private SalaCRUD salaCRUD = new SalaCRUD();

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

    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        Añadir.labelID.setText("ID Sala");
        Añadir.labelTitulo.setText("Capacidad:");
        Añadir.labelAnyo.setText("Pantalla");
        Añadir.labelDirector.setText("Fecha apertura");
        Añadir.labelAP.setVisible(false);
        Añadir.labelAS.setVisible(false);
        Añadir.labelDuracion.setVisible(false);
        Añadir.labelTrailer.setVisible(false);
        Añadir.textoAcPr.setVisible(false);
        Añadir.textoAcSe.setVisible(false);
        Añadir.textoDuracion.setVisible(false);
        Añadir.textoTrailer.setVisible(false);
        Añadir.labelDisponible.setText("Disponible");

        ventanaAñadir.salaFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public void botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        Sala sala = new Sala();
        //crear sala de filtrado
        int id = -1;
        if (AMB.textoID.getText().isEmpty()) {//evitar error de int null
            id = -1;
            sala.setID_SALA(id);
        } else {
            sala.setID_SALA(Integer.parseInt(AMB.textoID.getText()));
        }

        //consulta a base de datos con su respuesta en forma de lista
        iniciarTabla();// TODO poner columnas tabla (mejorar lugar)
        ArrayList<Sala> salas = new ArrayList<>(salaCRUD.filtrarSalas(sala, "sqlite"));
        if (salas.size() > 0) {
            ponerEnTabla(salas);
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
        }
    }

    //poner COLUMNAS
    public void iniciarTabla() {
        ventana.modeloTabla.setColumnCount(0);
        // String columna[] = new String[]{"ID_EMPLEADO", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "FECHA_NAC", "FECHA_FIN", "NACIONALIDAD", "CARGO", "DISPONIBLE"}; PORQUE NO ME DEJA PONER UNA COLUMAN ASI!
        ventana.modeloTabla.addColumn("ID_SALA");
        ventana.modeloTabla.addColumn("CAPACIDAD");
        ventana.modeloTabla.addColumn("FECHA APERTURA");
        ventana.modeloTabla.addColumn("PANTALLA");
        ventana.modeloTabla.addColumn("DISPONIBLE");
    }

    public void ponerEnTabla(List<Sala> salas) {
        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosSala[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Sala sala : salas) {
            datosSala[0] = sala.getID_SALA();
            datosSala[1] = sala.getCAPACIDAD();
            datosSala[2] = sala.getFEC_APERTURA();
            datosSala[3] = sala.getPANTALLA();
            datosSala[4] = sala.getDISPONIBLE();
            //insertar la fila
            ventana.modeloTabla.addRow(datosSala);
        }

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
