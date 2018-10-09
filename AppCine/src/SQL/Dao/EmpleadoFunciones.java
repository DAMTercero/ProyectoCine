/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Ventanas.AMB;
import static SQL.Ventanas.AMB.*;
import static SQL.Ventanas.Añadir.*;
import SQL.Clases.*;
import SQL.Ventanas.Añadir;
import java.util.List;

/**
 *
 * @author ikitess
 */
public class EmpleadoFunciones {

    private AMB ventana;
    private EmpleadoCRUD empleadoCRUD;

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

    public void botonFiltrar() {
        Empleado empleado = new Empleado();
        //crear el empleado de filtrado
        empleado.setID_EMPLEADO(Integer.parseInt(AMB.textoID.getText()));
        empleado.setNOMBRE(AMB.textoTitulo.getText());
        empleado.setAPELLIDO1(AMB.textoAnyo.getText());
        empleado.setAPELLIDO2(AMB.textoDirector.getText());

        List<Empleado> empleados = empleadoCRUD.filtrarEmpleados(empleado);
        if (empleados.size() > 0) {
            ponerEnTabla(empleados);
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
        }
    }

    public void ponerEnTabla(List<Empleado> empleados) {
        //lo de poner columnas
        //lo de poner filas por cada empleado en la lista
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
