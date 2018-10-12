/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Ventanas.AMB;
import SQL.Clases.*;
import SQL.Ventanas.Añadir;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ikitess
 */
public class EmpleadoFunciones {

    private AMB ventana;
    private EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
    //columnas de la tabla
    private final String columnas[] = new String[]{"ID_EMPLEADO", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "FECHA NACIMIENTO", "FECHA CONTRATO", "FECHA FIN CONTRATO", "NACIONALIDAD", "CARGO", "DISPONIBLE"};

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
        this.ventana.ponerColumnasTabla(columnas);
        this.ventana.setVisible(true);
    }

    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        //labels de Añadir
        Añadir.labelID.setText("ID Empleado");
        Añadir.labelTitulo.setText("Nombre:");
        Añadir.labelAnyo.setText("Apellido 1");
        Añadir.labelDirector.setText("Apellido 2");
        Añadir.labelAP.setText("Fecha contratación");
        Añadir.labelAS.setText("Fecha nacimiento");
        Añadir.labelDuracion.setText("Fecha fin contrato");
        Añadir.labelTrailer.setText("Nacionalidad");
        Añadir.labelUltimo.setText("Cargo");
        Añadir.labelDisponible.setText("Disponible");

        ventanaAñadir.empleadoFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.isAnyadir = 1;//descirle que es añadir
        ventanaAñadir.boton_add_modify.setText("Añadir");
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public void abrirVentanaModificar(Añadir ventanaModificar, Empleado empleado) {
        Añadir.labelID.setText("ID Empleado");
        Añadir.textoID.setText(String.valueOf(empleado.getID_EMPLEADO()));
        Añadir.labelTitulo.setText("Nombre:");
        Añadir.textoTitulo.setText(empleado.getNOMBRE());
        Añadir.labelAnyo.setText("Apellido 1");
        Añadir.textoAnyo.setText(empleado.getAPELLIDO1());
        Añadir.labelDirector.setText("Apellido 2");
        Añadir.textoDirector.setText(empleado.getAPELLIDO2());
        Añadir.labelAP.setText("Fecha nacimiento");
        Añadir.textoAcPr.setText(empleado.getFECHA_NACIMIENTO());
        Añadir.labelAS.setText("Fecha contratación");
        Añadir.textoAcSe.setText(empleado.getFECHA_CONTRATO());
        Añadir.labelDuracion.setText("Fecha fin contrato");
        Añadir.textoDuracion.setText(empleado.getFECHA_FIN());
        Añadir.labelTrailer.setText("Nacionalidad");
        Añadir.textoTrailer.setText(empleado.getNACIONALIDAD());
        Añadir.labelUltimo.setText("Cargo");
        Añadir.textoUltimo.setText(empleado.getCARGO());
        Añadir.labelDisponible.setText("Disponible");
        Añadir.disponibleCheckBox.setSelected(empleado.isDISPONIBLE());

        ventanaModificar.empleadoFunciones = this;
        ventanaModificar.ventanaAnterior = ventana;
        ventanaModificar.isAnyadir = 0;//descirle que es modificar
        ventanaModificar.boton_add_modify.setText("Modificar");
        ventanaModificar.setVisible(true);
        ventana.setEnabled(false);

    }

    public void botonAnyadir_Modificar(int isAnyadir) throws SQLException, ClassNotFoundException, IOException {
        Empleado empleado = new Empleado();
        switch (isAnyadir) {
            case 0://es modificar
                empleado.setID_EMPLEADO(Integer.parseInt(Añadir.textoID.getText()));
                empleado.setNOMBRE(Añadir.textoTitulo.getText());
                empleado.setAPELLIDO1(Añadir.textoAnyo.getText());
                empleado.setAPELLIDO2(Añadir.textoDirector.getText());
                empleado.setFECHA_NACIMIENTO(Añadir.textoAcPr.getText());
                empleado.setFECHA_CONTRATO(Añadir.textoAcSe.getText());
                empleado.setFECHA_FIN(Añadir.textoDuracion.getText());
                empleado.setNACIONALIDAD(Añadir.textoTrailer.getText());
                empleado.setCARGO(Añadir.textoUltimo.getText());
                empleado.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

                boolean action1 = empleadoCRUD.updateEmpleado(empleado, ventana.getTipoConexion());

                if (action1) {
                    JOptionPane.showMessageDialog(null, "Recurso Actualizado satisfactoriamente");
                }
                break;
            case 1: //añadir
                empleado.setNOMBRE(Añadir.textoTitulo.getText());
                empleado.setAPELLIDO1(Añadir.textoAnyo.getText());
                empleado.setAPELLIDO2(Añadir.textoDirector.getText());
                empleado.setFECHA_NACIMIENTO(Añadir.textoAcPr.getText());
                empleado.setFECHA_CONTRATO(Añadir.textoAcSe.getText());
                empleado.setFECHA_FIN(Añadir.textoDuracion.getText());
                empleado.setNACIONALIDAD(Añadir.textoTrailer.getText());
                empleado.setCARGO(Añadir.textoUltimo.getText());
                empleado.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

                boolean action2 = empleadoCRUD.insertEmpleado(empleado, ventana.getTipoConexion());

                if (action2) {
                    JOptionPane.showMessageDialog(null, "Recurso añadido satisfactoriamente");
                }
                break;
            default:
                break;
        }

    }

    public List<Object> botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        //desactivar botones modificar y baja
        ventana.botonModificar.setEnabled(false);
        ventana.botonBaja.setEnabled(false);
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
        ArrayList<Empleado> empleados = new ArrayList<>(empleadoCRUD.filtrarEmpleados(empleado, ventana.getTipoConexion()));
        if (empleados.size() > 0) {
            ponerEnTabla(empleados);
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
            ventana.rellenarErrores("No parece haber coincidencias");
            ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber            
        }
        return (List<Object>) (Object) empleados;
    }

    public void botonBaja(Empleado empleado) throws SQLException, ClassNotFoundException, IOException {
        empleado.setDISPONIBLE(false);
        boolean action = empleadoCRUD.updateEmpleado(empleado, ventana.getTipoConexion());
        if (action) {
            JOptionPane.showMessageDialog(null, "Recurso Actualizado satisfactoriamente");
        }
    }

    public void ponerEnTabla(List<Empleado> empleados) {
        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosEmpleado[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Empleado empleado : empleados) {
            datosEmpleado[0] = empleado.getID_EMPLEADO();
            datosEmpleado[1] = empleado.getNOMBRE();
            datosEmpleado[2] = empleado.getAPELLIDO1();
            datosEmpleado[3] = empleado.getAPELLIDO2();
            datosEmpleado[4] = empleado.getFECHA_NACIMIENTO();
            datosEmpleado[5] = empleado.getFECHA_CONTRATO();
            datosEmpleado[6] = empleado.getFECHA_FIN();
            datosEmpleado[7] = empleado.getNACIONALIDAD();
            datosEmpleado[8] = empleado.getCARGO();
            datosEmpleado[9] = empleado.isDISPONIBLE();
            ventana.modeloTabla.addRow(datosEmpleado);

        }
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
