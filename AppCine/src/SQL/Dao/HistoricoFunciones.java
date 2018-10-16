/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
import SQL.Clases.Pelicula;
import SQL.Clases.Sala;
import SQL.Ventanas.Historico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ikitess
 */
public class HistoricoFunciones {

    private Historico ventanaHistorico;
    private HistoricoCRUD historicoCRUD = new HistoricoCRUD();
    //columnas de la tabla
    private final String columnas[] = new String[]{"ID_SALA", "ID_PELICULA", "FECHA EMISION", "SESION", "ID_EMPLEADO"};

    public void abrirVentanaHistorico(Historico ventanaHistorico) throws IOException, SQLException, ClassNotFoundException {
        this.ventanaHistorico = ventanaHistorico;
        this.ventanaHistorico.ponerColumnasTabla(columnas);

        //quiero poner aqui que rellene los comboBox del filtrado.
        rellenarComboBoxes();

        this.ventanaHistorico.setVisible(true);
    }

    private void rellenarComboBoxes() throws IOException, SQLException, ClassNotFoundException {

        //obtener todo para las consultas
        ArrayList<Empleado> empleados = new ArrayList<>(historicoCRUD.selectEmpleados(ventanaHistorico.getTipoConexion()));
        ArrayList<Sala> salas = new ArrayList<>(historicoCRUD.selectSalas(ventanaHistorico.getTipoConexion()));
        ArrayList<Pelicula> peliculas = new ArrayList<>(historicoCRUD.selectPeliculas(ventanaHistorico.getTipoConexion()));
        ArrayList<SQL.Clases.Historico> historicosFechas = new ArrayList<>(historicoCRUD.selectFecha_Emision(ventanaHistorico.getTipoConexion()));

        //pasar listas a la ventana
        ventanaHistorico.empleados = empleados;
        ventanaHistorico.salas = salas;
        ventanaHistorico.peliculas = peliculas;
        ventanaHistorico.historicosFechas = historicosFechas;

        //poner en los combobox
        for (Pelicula pelicula : peliculas) {
            Historico.comboPeli.addItem(pelicula.getTITULO());
        }
        for (Sala sala : salas) {
            Historico.comboSala.addItem(String.valueOf(sala.getID_SALA()));
        }
        for (Empleado empleado : empleados) {
            Historico.comboEmpleado.addItem(empleado.getNOMBRE());
        }
        for (SQL.Clases.Historico historico : historicosFechas) {
            Historico.comboFechaEmision.addItem(historico.getFECHA_EMISION());
        }
    }

    public List<Object> botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        //desactivar botones modificar y baja
        ventanaHistorico.botonModificar.setEnabled(false);
        SQL.Clases.Historico historico = new SQL.Clases.Historico();
        //crear el historico de filtrado

        //leer el empleado
        Empleado emple = null;
        if (ventanaHistorico.comboEmpleado.getSelectedIndex() == 0) {
            //no se ha selecciona un empleado
        } else {
            //se ha seleccionado
            emple = ventanaHistorico.empleados.get(ventanaHistorico.comboEmpleado.getSelectedIndex() - 1);
        }
        //leer la peli
        Pelicula pelicula = null;
        if (ventanaHistorico.comboPeli.getSelectedIndex() == 0) {
            //no se ha selecciona un empleado
        } else {
            //se ha seleccionado
            pelicula = ventanaHistorico.peliculas.get(ventanaHistorico.comboPeli.getSelectedIndex() - 1);
        }
        //leer la sala
        Sala sala = null;
        if (ventanaHistorico.comboSala.getSelectedIndex() == 0) {
            //no se ha selecciona un empleado
        } else {
            //se ha seleccionado
            sala = ventanaHistorico.salas.get(ventanaHistorico.comboSala.getSelectedIndex() - 1);
        }
        //leer el fechaEmision
        SQL.Clases.Historico historicoFechas = null;
        if (ventanaHistorico.comboFechaEmision.getSelectedIndex() == 0) {
            //no se ha selecciona un empleado
        } else {
            //se ha seleccionado
            historicoFechas = ventanaHistorico.historicosFechas.get(ventanaHistorico.comboFechaEmision.getSelectedIndex() -1);
        }
        historico = new SQL.Clases.Historico(-1, -1, "", "", -1);//por defecto para el filtro
        //crear el objeto para las busquedas
        if (sala != null) {
            historico.setID_SALA(sala.getID_SALA());
        }
        if (pelicula != null) {
            historico.setID_PELICULA(pelicula.getID_PELICULA());
        }
        if (historicoFechas != null) {
            historico.setFECHA_EMISION(historicoFechas.getFECHA_EMISION());
        }
        if (emple != null) {
            historico.setID_EMPLEADO(emple.getID_EMPLEADO());
        }

        //consulta a base de datos con su respuestaen forma de lista
        ArrayList<SQL.Clases.Historico> hricos = new ArrayList<>(historicoCRUD.filtrarHistoricos(historico, ventanaHistorico.getTipoConexion()));
        if (hricos.size() > 0) {
            ponerEnTabla(hricos);
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
            //ventanaHistorico.rellenarErrores("No parece haber coincidencias");
            ventanaHistorico.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber            
        }
        return (List<Object>) (Object) hricos;
    }

    public void ponerEnTabla(List<SQL.Clases.Historico> historicos) {
        ventanaHistorico.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosHistorico[] = new Object[5]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (SQL.Clases.Historico h : historicos) {
            datosHistorico[0] = h.getID_SALA();
            datosHistorico[1] = h.getID_PELICULA();
            datosHistorico[2] = h.getFECHA_EMISION();
            datosHistorico[3] = h.getSESION();
            datosHistorico[4] = h.getID_EMPLEADO();

            ventanaHistorico.modeloTabla.addRow(datosHistorico);

        }
    }
}
