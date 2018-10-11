/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Pelicula;
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
public class PeliculaFunciones {

    private AMB ventana;
    private PeliculaCRUD peliculaCRUD = new PeliculaCRUD();
    //columnas de la tabla
    private final String columnas[] = new String[]{"ID_PELICULA", "TITULO", "AÑO ESTRENO", "DIRECTOR", "ACTOR PRINCIPAL", "ACTOR SECUNDARIO", "DURACIÓN", "TRAILER", "DISPONIBLE"};

    ////--Funciones para controlar la ventana--\\\\
    public void cambiarVentanaPelis() {
        //campos visibles para filtrar por si hacemos cambios
        labelTituloVentana.setText("PELÍCULAS");
        labelID.setText("ID Pélicula:");
        labelTitulo.setText("Título:");
        labelAnyo.setText("Año: ");
        labelDirector.setText("Director:");
        //campos que no estan visible por si hacemos cambios
        labelAP.setVisible(false);
        labelAS.setVisible(false);
        labelDuracion.setVisible(false);
        lableTrailer.setVisible(false);
        textoTrailer.setVisible(false);
        textoAcPr.setVisible(false);
        textoAcSe.setVisible(false);
        textoDuracion.setVisible(false);
    }

    public void abrirVentanaPelis(AMB ventana) {
        this.ventana = ventana;
        cambiarVentanaPelis();
        this.ventana.ponerColumnasTabla(columnas);
        this.ventana.setVisible(true);
    }

    public void abrirVentanaAñadir(Añadir ventanaAñadir) {
        Añadir.labelID.setText("ID Pelicula");
        Añadir.labelTitulo.setText("Titulo:");
        Añadir.labelAnyo.setText("Año de estreno");
        Añadir.labelDirector.setText("Director");
        Añadir.labelAP.setText("Actor principal");
        Añadir.labelAS.setText("Actor secundario");
        Añadir.labelDuracion.setText("Duración");
        Añadir.labelTrailer.setText("Trailer");
        Añadir.labelDisponible.setText("Disponible");
        Añadir.labelUltimo.setVisible(false);
        Añadir.textoUltimo.setVisible(false);

        ventanaAñadir.peliculaFunciones = this;
        ventanaAñadir.ventanaAnterior = ventana;
        ventanaAñadir.setVisible(true);
        ventana.setEnabled(false);

    }

    public void botonañadir() throws SQLException, ClassNotFoundException, IOException {
        //pille la info de los text de ventana añadir RECUERDA: ID NO SE PONE (solo en selects)
        Pelicula pelicula = new Pelicula();

        pelicula.setTITULO(Añadir.textoTitulo.getText());
        pelicula.setANYO_STRENO(Añadir.textoAnyo.getText());
        pelicula.setDIRECTOR(Añadir.textoDirector.getText());
        pelicula.setACTOR_PRINCI(Añadir.textoAcPr.getText());
        pelicula.setACTOR_SECUN(Añadir.textoAcSe.getText());
        pelicula.setDURACION(Añadir.textoDuracion.getText());
        pelicula.setTRAILER(Añadir.textoTrailer.getText());
        // pelicula.setCARGO(Añadir.textoTrailer.getText());
        pelicula.setDISPONIBLE(Añadir.disponibleCheckBox.isSelected());

        //cree el objeto
        //llame al CRUD añadir
        boolean action = peliculaCRUD.insertPelicula(pelicula, ventana.getTipoConexion());
        // empleadoCRUD.addEmpleado(empleado, empleadoCRUD.sqlitecon)
        //como devuelve TRUE o FALSE el añadir...
        //jOptionPane ("Se ha insertado bien" o " se ha insertado mal")
        if (action) {
            JOptionPane.showMessageDialog(null, "Recurso añadido satisfactoriamente");
        }
    }

    public List<Object> botonFiltrar() throws IOException, SQLException, ClassNotFoundException {
        Pelicula pelicula = new Pelicula();
        //crear la pelicula de filtrado
        int id = -1;
        if (AMB.textoID.getText().isEmpty()) {//evitar error de int null
            id = -1;
            pelicula.setID_PELICULA(id);
        } else {
            pelicula.setID_PELICULA(Integer.parseInt(AMB.textoID.getText()));
        }
        pelicula.setTITULO(AMB.textoTitulo.getText());
        pelicula.setANYO_STRENO(AMB.textoAnyo.getText());
        pelicula.setDIRECTOR(AMB.textoDirector.getText());

        //consulta a base de datos con su respuesta en forma de lista       
        ArrayList<Pelicula> peliculas = new ArrayList<>(peliculaCRUD.filtrarPeliculas(pelicula, ventana.getTipoConexion()));
        if (peliculas.size() > 0) {
            ponerEnTabla(peliculas);
        } else {
            ventana.rellenarErrores("No parece haber coincidencias");
            ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        }
        return (List<Object>) (Object) peliculas;

    }

    public void ponerEnTabla(List<Pelicula> peliculas) {
        ventana.modeloTabla.setRowCount(0);//vaciar las filas que pudiera haber
        Object datosPelicula[] = new Object[10]; //posiciones = atributos de la clase "getClass().getDeclaredFields().length" no va :(

        for (Pelicula pelicula : peliculas) {
            datosPelicula[0] = pelicula.getID_PELICULA();
            datosPelicula[1] = pelicula.getTITULO();
            datosPelicula[2] = pelicula.getANYO_STRENO();
            datosPelicula[3] = pelicula.getDIRECTOR();
            datosPelicula[4] = pelicula.getACTOR_PRINCI();
            datosPelicula[5] = pelicula.getACTOR_SECUN();
            datosPelicula[6] = pelicula.getDURACION();
            datosPelicula[7] = pelicula.getTRAILER();
            datosPelicula[8] = pelicula.isDISPONIBLE();
            //insertar la fila
            ventana.modeloTabla.addRow(datosPelicula);
        }

    }

    public PeliculaFunciones() {
    }

    public PeliculaFunciones(AMB ventana, PeliculaCRUD peliculaCRUD) {
        this.ventana = ventana;
        this.peliculaCRUD = peliculaCRUD;
    }

    public AMB getVentana() {
        return ventana;
    }

    public void setVentana(AMB ventana) {
        this.ventana = ventana;
    }

    public PeliculaCRUD getPeliculaCRUD() {
        return peliculaCRUD;
    }

    public void setPeliculaCRUD(PeliculaCRUD peliculaCRUD) {
        this.peliculaCRUD = peliculaCRUD;
    }

}
