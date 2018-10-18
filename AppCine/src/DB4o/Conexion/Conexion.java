package DB4o.Conexion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import DB4o.Clases.Empleado;
import DB4o.Clases.Historico;
import DB4o.Clases.Pelicula;
import DB4o.Clases.Sala;

import DB4o.Ventanas.AMB;
import DB4o.Ventanas.HistoricoVentana;
import java.util.List;
import java.util.ArrayList;

public class Conexion {
    
    final String rutaABaseDeDatos="DBEmbebidas/BD6.yap";
    final String rutaABaseDeDatosHistoricos="DBEmbebidas/BDH1.yap";
    

    public void guardarPelicula(Pelicula p1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);

        final ObjectSet result = db.queryByExample(p1);
        if (result.size() == 0) {

            db.store(p1);
            db.close();

        } else {
            AMB.textoErrores.setText("Duplicado");
        }

    }

    public void guardarSala(Sala s1) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);

        final ObjectSet result = db.queryByExample(s1);
        if (result.size() == 0) {

            db.store(s1);
            db.close();

        } else {
            AMB.textoErrores.setText("Duplicado");
        }

    }

    public void guardarEmpleado(Empleado e1) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);

        final ObjectSet result = db.queryByExample(e1);
        if (result.size() == 0) {

            db.store(e1);
            db.close();

        } else {
            AMB.textoErrores.setText("Duplicado");
        }

    }
     public void guardarHistorico(Historico h1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatosHistoricos);

        final ObjectSet result = db.queryByExample(h1);
        if (result.size() == 0) {

            db.store(h1);
            db.close();

        } else {
           
        }

    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void modificarPelicula(Pelicula peliculaOriginal, Pelicula peliculaMOdificada) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Pelicula> result = db.queryByExample(peliculaOriginal);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa peli o no hay pelis en la BD");

        } else {

            while (result.hasNext()) {
                Pelicula peliAMostrarParaMOdificar = result.next();
                db.delete(peliAMostrarParaMOdificar);
                db.store(peliculaMOdificada);

            }
        }

        db.close();

    }

    public void modificarSala(Sala salaOriginal, Sala salaMOdificada) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Sala> result = db.queryByExample(salaOriginal);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa sala o no hay salas en la BD");

        } else {

            while (result.hasNext()) {
                Sala salaParaMOdificar = result.next();
                db.delete(salaParaMOdificar);
                db.store(salaMOdificada);

            }
        }

        db.close();
    }

    public void modificarEmpleado(Empleado empleOriginal, Empleado empleMOdificada) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Empleado> result = db.queryByExample(empleOriginal);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe ese empleado o no hay empleados en la BD");

        } else {

            while (result.hasNext()) {
                Empleado empleParaMOdificar = result.next();
                db.delete(empleParaMOdificar);
                db.store(empleMOdificada);

            }
        }

        db.close();
    }
    
    public void modificarHistorico(Historico histoOriginal, Historico histoMOdificada) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatosHistoricos);
        ObjectSet<Historico> result = db.queryByExample(histoOriginal);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa registro o no hay registros en la BD");

        } else {

            while (result.hasNext()) {
                Historico histoParaMOdificar = result.next();
                db.delete(histoParaMOdificar);
                db.store(histoMOdificada);

            }
        }

        db.close();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void buscarPelicula(Pelicula p1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Pelicula> result = db.queryByExample(p1);
        List<Pelicula> pelis = new ArrayList<>();

        if (result.size() == 0) {
            AMB.rellenarErrores("No existe esa peli o no hay pelis en la BD");
        } else {
            while (result.hasNext()) {

                Pelicula peliAMostrar = result.next();
                if (peliAMostrar.getEliminada()) {
                    System.out.println("Esa peli esta eliminada");
                } else {
                    pelis.add(peliAMostrar);
                }
            }
            AMB.rellenarPelicula(pelis);
        }

        db.close();
    }

    public void buscarSala(Sala s1) {
        
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Sala> result = db.queryByExample(s1);
        List<Sala> salas = new ArrayList<>();

        if (result.size() == 0) {
            AMB.rellenarErrores("No existe esa sala o no hay salas en la BD");
        } else {
            while (result.hasNext()) {

                Sala salaAMostrar = result.next();
                if (salaAMostrar.getDisponible()==false) {
                    System.out.println("Esa sala esta eliminada");
                } else {
                    salas.add(salaAMostrar);
                }
            }
            AMB.rellenarSala(salas);
        }

        db.close();
    }

    public void buscarEmpleado(Empleado e1) {
         ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Empleado> result = db.queryByExample(e1);
        List<Empleado> emples = new ArrayList<>();

        if (result.size() == 0) {
            AMB.rellenarErrores("No existe ese empleado o no hay empleados en la BD");
        } else {
            while (result.hasNext()) {

                Empleado empleAMostrar = result.next();
                if (empleAMostrar.getActivo()) {
                    emples.add(empleAMostrar);
                } else {
                  System.out.println("Ese empleado esta eliminado");
                }
            }
            AMB.rellenarEmpleado(emples);
        }

        db.close();
    }
    
    public void buscarHistorico(Historico h1) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatosHistoricos);
        ObjectSet<Historico> result = db.queryByExample(h1);
        List<Historico> histos = new ArrayList<>();

        if (result.size() == 0) {
          
        } else {
            while (result.hasNext()) {
                Historico histoAMostrar = result.next();
                histos.add(histoAMostrar);
            }

        }
        HistoricoVentana.rellenarHistorico(histos);
        db.close();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void borrarPelicula(Pelicula p1) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Pelicula> result = db.queryByExample(p1);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa peli o no hay pelis en la BD");

        } else {

            while (result.hasNext()) {
                Pelicula peliAMostrarParaBorrar = result.next();
                Pelicula peliAMostrar = peliAMostrarParaBorrar;
                db.delete(peliAMostrarParaBorrar);
                peliAMostrar.setEliminada(true);
                db.store(peliAMostrar);
            }
        }

        db.close();

    }

    public void borrarSala(Sala s1) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Sala> result = db.queryByExample(s1);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe esa sala o no hay salas en la BD");

        } else {

            while (result.hasNext()) {
                Sala salaAMostrarParaBorrar = result.next();
                Sala salaAMostrar = salaAMostrarParaBorrar;
                db.delete(salaAMostrarParaBorrar);
                salaAMostrar.setDisponible(false);
                db.store(salaAMostrar);
            }
        }

        db.close();
    }

    public void borrarEmpleado(Empleado e1) {
         ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Empleado> result = db.queryByExample(e1);

        if (result.size() == 0) {

            AMB.rellenarErrores("No existe ese empleado o no hay empleados en la BD");

        } else {

            while (result.hasNext()) {
                Empleado empleAMostrarParaBorrar = result.next();
                Empleado empleAMostrar = empleAMostrarParaBorrar;
                db.delete(empleAMostrarParaBorrar);
                empleAMostrar.setActivo(false);
                db.store(empleAMostrar);
            }
        }

        db.close();
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<Pelicula> mostrarTodasPeliculas(){
        Pelicula p = new Pelicula (0,null,null,null,null,null,null,null);
        List<Pelicula> peliculas = new ArrayList();
        Pelicula pTempo;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Pelicula> result = db.queryByExample(p);

            while (result.hasNext()) {
                pTempo= result.next();
                peliculas.add(pTempo);
            }

        db.close();
        return peliculas;
    }
    public List<Empleado> mostrarTodosEmpleados(){
        Empleado e = new Empleado (0,null,null,null,null,null,null,null,null);
        List<Empleado> empleados = new ArrayList();
        Empleado eTempo;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Empleado> result = db.queryByExample(e);

            while (result.hasNext()) {
                eTempo = result.next();
                empleados.add(eTempo);
            }

        db.close();
        return empleados;
    }
    public List<Sala> mostrarTodasSalas(){
        Sala s = new Sala (0,0,0,null,null);
        List<Sala> salas = new ArrayList();
        Sala sTempo;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Sala> result = db.queryByExample(s);

            while (result.hasNext()) {
                sTempo = result.next();
                salas.add(sTempo);
            }

        db.close();
        return salas;
    }
    
    public Empleado buscarEseEmpleado(Empleado e){
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Empleado> result = db.queryByExample(e);
        Empleado emples= new Empleado();

        if (result.size() == 0) {
           
        } else {
            while (result.hasNext()) {

                emples = result.next();
            }
        }
        db.close();
    return emples;
    }
    
    public Pelicula buscarEsaPelicula(Pelicula pe){
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Pelicula> result = db.queryByExample(pe);
        Pelicula pelis= new Pelicula();

        if (result.size() == 0) {
        
        } else {
            while (result.hasNext()) {

                pelis = result.next();
            }
        }
        db.close();
    return pelis;
    }
     public Sala buscarEsaSala(Sala sa){
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaABaseDeDatos);
        ObjectSet<Sala> result = db.queryByExample(sa);
        Sala salas= new Sala();

        if (result.size() == 0) {
         
        } else {
            while (result.hasNext()) {

                salas = result.next();
            }
        }
        db.close();
    return salas;
    }
    
}
