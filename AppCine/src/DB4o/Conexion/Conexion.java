package DB4o.Conexion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import DB4o.Clases.Empleado;
import DB4o.Clases.Pelicula;
import DB4o.Clases.Sala;

import DB4o.Ventanas.AMB;
import java.util.List;
import java.util.ArrayList;

public class Conexion {

    public void guardarPelicula(Pelicula p1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BD1.yap");

        final ObjectSet result = db.queryByExample(p1);
        if (result.size() == 0) {

            db.store(p1);
            db.close();

        } else {
            AMB.textoErrores.setText("Duplicado");
        }

    }

    public void guardarSala(Sala s1) {

    }

    public void guardarEmpleado(Empleado e1) {

    }

    public void buscarPelicula(Pelicula p1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BD1.yap");
        ObjectSet<Pelicula> result = db.queryByExample(p1);
        List<Pelicula> pelis= new ArrayList<>();
       
        if (result.size() == 0) {
            
            AMB.rellenarErrores("No existe esa sala o no hay salas en la BD");

        } else {

            while (result.hasNext()) {

                Pelicula peliAMostrar = result.next();
                pelis.add(peliAMostrar);
                
            }
            AMB.rellenarPelicula(pelis);
        }

        db.close();
    }

    public void buscarSala(Sala s1) {
    }

    public void buscarEmpleado(Empleado e1) {
    }

    public void borrarPelicula(Pelicula p1) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BD1.yap");
        ObjectSet<Pelicula> result = db.queryByExample(p1);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa sala o no hay salas en la BD");

        } else {

            while (result.hasNext()) {
                Pelicula peliAMostrar = result.next();
                peliAMostrar.setEliminada(true);
                db.store(peliAMostrar);
            }
        }

        db.close();

    }

    public void borrarSala(Sala s1) {

    }

    public void borrarEmpleado(Empleado e1) {

    }
}
