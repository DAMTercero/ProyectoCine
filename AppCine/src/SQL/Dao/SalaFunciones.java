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
import javax.swing.JOptionPane;

/**
 *
 * @author ikitess
 */
public class SalaFunciones {

    private AMB ventana;
    private SalaCRUD salaCRUD = new SalaCRUD();
    //columnas de la tabla
    private final String columnas[] = new String[]{"ID_SALA", "CAPACIDAD", "PANTALLA", "FECH_APERTURA", "DISPONIBLE"};

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
        this.ventana.ponerColumnasTabla(columnas);
        this.ventana.setVisible(true);
    }

    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        //labels de Añadir
        Añadir.labelID.setText("ID Sala");
        Añadir.labelTitulo.setText("Capacidad:");
        Añadir.labelAnyo.setText("Pantalla");
        Añadir.labelDirector.setText("Fecha apertura");
        Añadir.labelAP.setVisible(false);
        Añadir.labelAS.setVisible(false);
        Añadir.labelDuracion.setVisible(false);
        Añadir.labelTrailer.setVisible(false);
        Añadir.labelUltimo.setVisible(false);
        Añadir.textoAcPr.setVisible(false);
        Añadir.textoAcSe.setVisible(false);
        Añadir.textoDuracion.setVisible(false);
        Añadir.textoTrailer.setVisible(false);
        Añadir.textoUltimo.setVisible(false);
        Añadir.labelDisponible.setText("Disponible");

        ventanaAñadir.salaFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.isAnyadir = 1;//descirle que es añadir
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public void abrirVentanaModificar(Añadir ventanaModificar, Sala sala) {
        Añadir.labelID.setText("ID Sala");
        Añadir.textoID.setText(String.valueOf(sala.getID_SALA()));
        Añadir.labelTitulo.setText("Capacidad:");
        Añadir.textoTitulo.setText(String.valueOf(sala.getCAPACIDAD()));
        Añadir.labelAnyo.setText("Pantalla");
        Añadir.textoAnyo.setText(sala.getFECHA_APERTURA());
        Añadir.labelDirector.setText("Fecha apertura");
        Añadir.textoDirector.setText(sala.getPANTALLA());
        Añadir.labelDisponible.setText("Disponible");
        Añadir.disponibleCheckBox.setSelected(sala.getDISPONIBLE());

        Añadir.labelAP.setVisible(false);
        Añadir.textoAcPr.setVisible(false);
        Añadir.labelAS.setVisible(false);
        Añadir.textoAcSe.setVisible(false);
        Añadir.labelDuracion.setVisible(false);
        Añadir.textoDuracion.setVisible(false);
        Añadir.labelTrailer.setVisible(false);
        Añadir.textoTrailer.setVisible(false);
        Añadir.labelUltimo.setVisible(false);
        Añadir.textoUltimo.setVisible(false);

        ventanaModificar.salaFunciones = this;
        ventanaModificar.ventanaAnterior = ventana;
        ventanaModificar.isAnyadir = 0;//descirle que es modificar
        ventanaModificar.boton_add_modify.setText("Modificar");
        ventanaModificar.setVisible(true);
        ventana.setEnabled(false);

    }

    public void botonAnyadir_Modificar(int isAnyadir) throws SQLException, ClassNotFoundException, IOException {
        Sala sala = new Sala();
        switch (isAnyadir) {
            case 0://es modificar
                sala.setID_SALA(Integer.parseInt(Añadir.textoID.getText()));
                sala.setCAPACIDAD(Integer.parseInt(Añadir.textoTitulo.getText()));
                sala.setPANTALLA(Añadir.textoDirector.getText());
                sala.setFECHA_APERTURA(Añadir.textoAnyo.getText());
                sala.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

                boolean action1 = salaCRUD.updateSala(sala, ventana.getTipoConexion());

                if (action1) {
                    JOptionPane.showMessageDialog(null, "Recurso Actualizado satisfactoriamente");
                }
                break;
            case 1: //añadir
                sala.setCAPACIDAD(Integer.parseInt(Añadir.textoTitulo.getText()));
                sala.setPANTALLA(Añadir.textoDirector.getText());
                sala.setFECHA_APERTURA(Añadir.textoAnyo.getText());
                sala.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

                boolean action2 = salaCRUD.insertSala(sala, ventana.getTipoConexion());

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
        ArrayList<Sala> salas = new ArrayList<>(salaCRUD.filtrarSalas(sala, ventana.getTipoConexion()));//objeto y tipo conexion
        if (salas.size() > 0) {
            ponerEnTabla(salas);
        } else {
            //sacar un mensaje de que no existen coincidendias Ó usando el label de ERROR o poniendo en la tabla que no hay coincidencias
            ventana.rellenarErrores("No parece haber coincidencias");
            ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber

        }
        return (List<Object>) (Object) salas;
    }

    public void botonBaja(Sala sala) throws SQLException, ClassNotFoundException, IOException {
        sala.setDISPONIBLE(false);
        boolean action = salaCRUD.updateSala(sala, ventana.getTipoConexion());
        if (action) {
            JOptionPane.showMessageDialog(null, "Recurso Actualizado satisfactoriamente");
        }
    }

    public void ponerEnTabla(List<Sala> salas) {
        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosSala[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Sala sala : salas) {
            datosSala[0] = sala.getID_SALA();
            datosSala[1] = sala.getCAPACIDAD();
            datosSala[3] = sala.getPANTALLA();//asi sale bien y nose porque
            datosSala[2] = sala.getFECHA_APERTURA();
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
