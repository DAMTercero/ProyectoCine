/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import DB4o.Conexion.Conexion;
import SQL.Ventanas.AMB;
import static SQL.Ventanas.AMB.*;
import static SQL.Ventanas.Añadir.*;
import SQL.Clases.*;
import SQL.Ventanas.Añadir;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ikitess
 */
public class EmpleadoFunciones {

    private AMB ventana;
    private EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();

    ////--Funciones para controlar la ventana--\\\\
    public void cambiarVentanaEmpleados() {
        //campos visibles para filtrar
        AMB.labelTituloVentana.setText("EMPLEADOS");
        AMB.labelID.setText("ID Empleado:");
        AMB.labelTitulo.setText("Nombre:");
        AMB.labelAnyo.setText("Primer apellido: ");
        AMB.labelDirector.setText("Segundo apellido:");
        //campos que no estan visible por si hacemos cambios
        AMB.labelAP.setVisible(false);
        AMB.labelAS.setVisible(false);
        AMB.labelDuracion.setVisible(false);
        AMB.lableTrailer.setVisible(false);
        AMB.textoAcPr.setVisible(false);
        AMB.textoAcSe.setVisible(false);
        AMB.textoDuracion.setVisible(false);
        AMB.textoTrailer.setVisible(false);
    }

    public void abrirVentanaEmpleados(AMB ventana) {
        this.ventana = ventana;
        cambiarVentanaEmpleados();
        this.ventana.setVisible(true);
    }

    public void botonañadir() {
        //pille la info de los text de ventana añadir RECUERDA: ID NO SE PONE (solo en selects)
        Empleado empleado = new Empleado();

        /* empleado.setNOMBRE();
        empleado.setAPELLIDO1();
        empleado.setAPELLIDO2();
        empleado.setFECHA_NAC();*/
        //cree el objeto
        //llame al CRUD añadir
        // empleadoCRUD.addEmpleado(empleado, empleadoCRUD.sqlitecon)
        //como devuelve TRUE o FALSE el añadir...
        //jOptionPane ("Se ha insertado bien" o " se ha insertado mal")
    }

    public void botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        Empleado empleado = new Empleado();
        //crear el empleado de filtrado
        int id = -1;
        if (AMB.textoID.getText().isEmpty()) {//evitar error de int null
            id = -1;
            empleado.setID_EMPLEADO(id);
        } else {
            empleado.setID_EMPLEADO(Integer.parseInt(AMB.textoID.getText()));
        }
        empleado.setNOMBRE(AMB.textoTitulo.getText());
        empleado.setAPELLIDO1(AMB.textoAnyo.getText());
        empleado.setAPELLIDO2(AMB.textoDirector.getText());
        //consulta a base de datos con su respuestaen forma de lista
        iniciarTabla();
        ArrayList<Empleado> empleados = new ArrayList<>(empleadoCRUD.filtrarEmpleados(empleado, "sqlite"));
        if (empleados.size() > 0) {
            ponerEnTabla(empleados);
            //iniciarTabla();
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
        }
    }

    //poner COLUMNAS
    public void iniciarTabla() {
        ventana.modeloTabla.setColumnCount(0);
        // String columna[] = new String[]{"ID_EMPLEADO", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "FECHA_NAC", "FECHA_FIN", "NACIONALIDAD", "CARGO", "DISPONIBLE"}; PORQUE NO ME DEJA PONER UNA COLUMAN ASI!
        ventana.modeloTabla.addColumn("ID_EMPLEADO");
        ventana.modeloTabla.addColumn("NOMBRE");
        ventana.modeloTabla.addColumn("APELLIDO 1");
        ventana.modeloTabla.addColumn("APELLIDO 2");
        ventana.modeloTabla.addColumn("FECHA_NAC");
        ventana.modeloTabla.addColumn("FECHA_FIN");
        ventana.modeloTabla.addColumn("NACIONALIDAD");
        ventana.modeloTabla.addColumn("CARGO");
        ventana.modeloTabla.addColumn("DISPONIBLE");
        //t = new DefaultTableModel(null, new String[]{"ID_EMPLEADO", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "FECHA_NAC", "FECHA_FIN", "NACIONALIDAD", "CARGO", "DISPONIBLE"});
        /* try {

            //empleadoCRUD.insertEmpleado(new Empleado(99, "d ", "d ", "d ", "d ", "d ", "d ", "d ", "d ", true), "sqlite");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoFunciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoFunciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoFunciones.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void ponerEnTabla(List<Empleado> empleados) {

        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosEmpleado[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Empleado empleado : empleados) {
            datosEmpleado[0] = empleado.getID_EMPLEADO();
            datosEmpleado[1] = empleado.getNOMBRE();
            datosEmpleado[2] = empleado.getAPELLIDO1();
            datosEmpleado[3] = empleado.getAPELLIDO2();
            datosEmpleado[4] = empleado.getFECHA_NAC();
            datosEmpleado[5] = empleado.getFECHA_CONTRATO();
            datosEmpleado[6] = empleado.getFECHA_FIN();
            datosEmpleado[7] = empleado.getNACIONALIDAD();
            datosEmpleado[8] = empleado.getCARGO();
            datosEmpleado[9] = empleado.isDISPONIBLE();
            //insertar la fila
            ventana.modeloTabla.addRow(datosEmpleado);
        }

    }

    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        Añadir.labelID.setText("ID Empleado");
        Añadir.labelTitulo.setText("Nombre:");
        Añadir.labelAnyo.setText("Apellido 1");
        Añadir.labelDirector.setText("Apellido 2");
        Añadir.labelAP.setText("Fecha nacimiento");
        Añadir.labelAS.setText("Fecha fin contrato");
        Añadir.labelDuracion.setText("Nacionalidad");
        Añadir.labelTrailer.setText("Cargo");
        Añadir.labelDisponible.setText("Disponible");

        ventanaAñadir.empleadoFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public EmpleadoFunciones() {
    }

    public EmpleadoFunciones(AMB ventana, EmpleadoCRUD empleadoCRUD) {
        this.ventana = ventana;
        this.empleadoCRUD = empleadoCRUD;
    }

    public AMB getVentana() {
        return ventana;
    }

    public void setVentana(AMB ventana) {
        this.ventana = ventana;
    }

    public EmpleadoCRUD getEmpleadoCRUD() {
        return empleadoCRUD;
    }

    public void setEmpleadoCRUD(EmpleadoCRUD empleadoCRUD) {
        this.empleadoCRUD = empleadoCRUD;
    }

}
