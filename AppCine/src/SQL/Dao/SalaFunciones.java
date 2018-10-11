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
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public void botonañadir() throws SQLException, ClassNotFoundException, IOException {
        //pille la info de los text de ventana añadir RECUERDA: ID NO SE PONE (solo en selects)
        Sala sala = new Sala();

        sala.setCAPACIDAD(Integer.parseInt(Añadir.textoTitulo.getText()));
        sala.setPANTALLA(Añadir.textoDirector.getText());
        sala.setFEC_APERTURA(Añadir.textoAnyo.getText());
        sala.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

        //cree el objeto
        //llame al CRUD añadir
        boolean action = salaCRUD.insertSala(sala, ventana.getTipoConexion());
        // empleadoCRUD.addEmpleado(empleado, empleadoCRUD.sqlitecon)
        //como devuelve TRUE o FALSE el añadir...
        //jOptionPane ("Se ha insertado bien" o " se ha insertado mal")
        if (action) {
            JOptionPane.showMessageDialog(null, "Recurso añadido satisfactoriamente");
        }
    }

    public List<Object> botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
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

    public void ponerEnTabla(List<Sala> salas) {
        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosSala[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Sala sala : salas) {
            datosSala[0] = sala.getID_SALA();
            datosSala[1] = sala.getCAPACIDAD();
            datosSala[3] = sala.getPANTALLA();//asi sale bien y nose porque
            datosSala[2] = sala.getFEC_APERTURA();
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
