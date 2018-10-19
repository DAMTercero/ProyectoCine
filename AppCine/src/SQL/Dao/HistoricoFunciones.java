/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
import SQL.Clases.Pelicula;
import SQL.Clases.Sala;
import SQL.Ventanas.Añadir;
import SQL.Ventanas.Añadir_Modificar;
import SQL.Ventanas.Historico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

    public void abrirVentanaAñadir(Añadir_Modificar ventanaAñadir) {
        //llenar los combos       
        for (Pelicula pelicula : ventanaHistorico.peliculas) {
            ventanaAñadir.comboPeli.addItem(pelicula.getTITULO());
        }
        for (Sala sala : ventanaHistorico.salas) {
            ventanaAñadir.comboSala.addItem(String.valueOf(sala.getID_SALA()));
        }
        for (Empleado empleado : ventanaHistorico.empleados) {
            ventanaAñadir.comboEmpleado.addItem(empleado.getNOMBRE());
        }

        ventanaAñadir.historicoFunciones = this;
        ventanaAñadir.ventanaAnterior = ventanaHistorico;
        ventanaAñadir.isAnyadir = 1;//descirle que es añadir
        ventanaAñadir.boton_add_modify.setText("Añadir");
        ventanaAñadir.setVisible(true);
        ventanaHistorico.setEnabled(false);

    }

    public void abrirVentanaModificar(Añadir_Modificar ventanaModificar, SQL.Clases.Historico hitorico) {
        //llenar los combos con el objeto que vino
        Sala sala = null;
        Pelicula pelicula = null;
        Empleado empleado = null;
        SQL.Clases.Historico historicoModificado = null;

        //poner en los combos ya la seleccion de los combos. . ....
        //pa luego
        ventanaModificar.historicoFunciones = this;
        ventanaModificar.ventanaAnterior = ventanaHistorico;
        ventanaModificar.isAnyadir = 0;//descirle que es modificar
        ventanaModificar.boton_add_modify.setText("Modificar");
        ventanaModificar.setVisible(true);
        ventanaHistorico.setEnabled(false);

    }

    public void botonAnyadir_Modificar(int isAnyadir) throws SQLException, ClassNotFoundException, IOException {
        SQL.Clases.Historico historico = new SQL.Clases.Historico();
        switch (isAnyadir) {
            case 0://es modificar               
                break;
            case 1: //añadir
                //tengo el indice seleccionado, coger el objeto de los arrays en esa misma posicion
                historico.setID_SALA(ventanaHistorico.salas.get(Añadir_Modificar.comboSala.getSelectedIndex()).getID_SALA());
                historico.setID_PELICULA(ventanaHistorico.peliculas.get(Añadir_Modificar.comboPeli.getSelectedIndex()).getID_PELICULA());
                historico.setID_EMPLEADO(ventanaHistorico.empleados.get(Añadir_Modificar.comboEmpleado.getSelectedIndex()).getID_EMPLEADO());
                historico.setFECHA_EMISION(Añadir_Modificar.textFechaEmision.getText());
                historico.setSESION(Añadir_Modificar.textSesion.getText());

                boolean action2 = historicoCRUD.insertHistorico(historico, ventanaHistorico.getTipoConexion());

                if (action2) {
                    JOptionPane.showMessageDialog(null, "Recurso añadido satisfactoriamente");
                }
                break;
            default:
                break;
        }

    }

    public void rellenarComboBoxes() throws IOException, SQLException, ClassNotFoundException {
        Historico.comboPeli.removeAllItems();
        Historico.comboSala.removeAllItems();
        Historico.comboEmpleado.removeAllItems();
        Historico.comboFechaEmision.removeAllItems();

        Historico.comboPeli.addItem("---");
        Historico.comboSala.addItem("---");
        Historico.comboEmpleado.addItem("---");
        Historico.comboFechaEmision.addItem("---");

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
            historicoFechas = ventanaHistorico.historicosFechas.get(ventanaHistorico.comboFechaEmision.getSelectedIndex() - 1);
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
