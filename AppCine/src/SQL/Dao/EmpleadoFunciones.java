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
public class EmpleadoFunciones {

    private AMB ventana;
    private EmpleadoCRUD empleadoCRUD;

    ////--Funciones para controlar la ventana--\\\\
    public void cambiarVentanaEmpleados() {
        //campos visibles para filtrar
        labelTituloVentana.setText("EMPLEADOS");
        labelID.setText("ID Empleado:");
        labelTitulo.setText("Nombre:");
        labelAnyo.setText("Primer apellido: ");
        labelDirector.setText("Segundo apellido:");
        //campos que no estan visible por si hacemos cambios
        labelAP.setVisible(false);
        labelAS.setVisible(false);
        labelDuracion.setVisible(false);
        lableTrailer.setVisible(false);
        textoAcPr.setVisible(false);
        textoAcSe.setVisible(false);
        textoDuracion.setVisible(false);
        textoTrailer.setVisible(false);
    }

    public void abrirVentanaEmpleados(AMB ventana) {
        this.ventana = ventana;
        cambiarVentanaEmpleados();
        this.ventana.setVisible(true);
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
