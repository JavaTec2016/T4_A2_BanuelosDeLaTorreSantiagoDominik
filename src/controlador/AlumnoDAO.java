package controlador;

import conexionBD.ConexionBD;
import modelo.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO {
    //metodos para las acciones ABCC (CRUD)
    ConexionBD conexion = new ConexionBD();
    //=====METODOS ABCC===//
    //Metodo de ALTAS---
    public boolean agregarAlumno(Alumno a){
        boolean res = false;

        String alta = "INSERT INTO alumnos VALUES('"+a.getNumControl()+"', '"
                +a.getNombre()+"', '"
                +a.getPrimerAp()+"', '"
                +a.getSegundoAp()+"', "
                +a.getEdad()+", "
                +a.getSemestre()+", '"
                +a.getCarrera()+"')";

        res = conexion.ejecutarInstruccionDML(alta);
        return res;
    }
    //Metodo de BAJAS
    public boolean eliminarAlumno(String numControl){
        String sql = "DELETE FROM alumnos WHERE Num_Control='"+numControl+"'";
        return conexion.ejecutarInstruccionDML(sql);
    }
    public boolean actualizarAlumno(Alumno a){
        String sql = "UPDATE alumnos SET " +
                "Nombre='"+a.getNombre()+"', " +
                "Primer_Ap='"+a.getPrimerAp()+"', " +
                "Segundo_Ap='"+a.getSegundoAp()+"', " +
                "Edad="+a.getEdad()+", " +
                "SemestreActual="+a.getSemestre()+", " +
                "Carrera='"+a.getCarrera()+"' WHERE Num_Control='"+a.getNumControl()+"'";

        return conexion.ejecutarInstruccionDML(sql);
    }
    //Consultas
    public ArrayList mostrarAlumnos(String filtro){
        ArrayList listaAlumnos = new ArrayList();

        String sql = "SELECT * FROM alumnos";
        if(!filtro.isBlank()) sql += " WHERE " + filtro;
        System.out.println(sql);
        ResultSet rs = conexion.ejecutarConsultaSQL(sql);

        try {
            rs.next();
            do {
               String nc = rs.getString(1),
                       nom = rs.getString(2),
                       pa = rs.getString("Primer_Ap"),
                       sa = rs.getString("Segundo_Ap"),
                       car = rs.getString("Carrera");

               byte ed = rs.getByte("Edad"),
                    sem = rs.getByte("SemestreActual");
               Alumno a = new Alumno(nc, nom, pa, sa, ed, sem, car);
               listaAlumnos.add(a);

            } while (rs.next());
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listaAlumnos;
    }

}//clase
