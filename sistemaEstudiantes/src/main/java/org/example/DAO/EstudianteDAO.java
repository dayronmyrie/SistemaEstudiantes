package org.example.DAO;

import org.example.Conection.Conexion;
import org.example.Entity.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DATA ACCESS OBJECT
public class EstudianteDAO {

    public List<Estudiante> listar()  {
        List<Estudiante> estudiantes = new ArrayList<>();

        PreparedStatement ps;  //prepara la sentencia que vamos a solicitar a la base de datos
        ResultSet rs;  //almacena el resultado obtenido de la base de datos

        Connection con = Conexion.getConexion();  // inicializa la conexion

        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante"; //sentencia sql que queremos solicitar, los devuelve ordenados por id

        try {
            ps = con.prepareStatement(sql);  //preparamos la sentencia  a la conexion

            rs = ps.executeQuery(); // ejecuta la sentencia, lee informacion de la base de datos

            // iniciamos la extraccion de registros de la base de datos
            while (rs.next()) {  //mientras haya registros que iterar
                var estudiante = new Estudiante();

                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));  // seteamos la variables con las columnas de la base de datos
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                estudiantes.add(estudiante); // se agrega a la lista
            }
        } catch(Exception e){
            System.out.println("Ha ocurrido un error en traer los estudiantes: " + e.getMessage());
            }



            finally{
            try {
            con.close();  // cerrar la conexion una vez terminado el proceso del metodo

            } catch (Exception e){
                System.out.println("No se ha podido cerrar la conexion: " + e.getMessage());
        }

        }

        return estudiantes;
    }

    public boolean findById(Estudiante estudiante)  {



        PreparedStatement ps;
        ResultSet rs;



        Connection cn = Conexion.getConexion();

        String sql = "select * from estudiante where id_estudiante = ? ";

        try {
            ps = cn.prepareStatement(sql);  //preparamos la sentencia  a la conexion
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery(); // ejecuta la sentencia


            if (rs.next()) {  //si hay registros que iterar



                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                return true;


            }
        } catch (Exception e) {
            System.out.println("Error al buscar estudiante por id: " + e.getMessage());
        } finally {
            try {
                cn.close();  // cerrar la conexion una vez terminado el proceso del metodo

            } catch (Exception e) {
                System.out.println("No se ha podido cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }
    public boolean AgregarEstudiante(Estudiante estudiante){

       // Estudiante estudiante1 = new Estudiante();
        //Declaracion de conexion, se declara la preparacion de declaracion, el result set, la declaracion de conexion y la sentencia sql

        PreparedStatement ps;
        ResultSet rs;

        Connection cn = Conexion.getConexion();

        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)" + " values(?,?,?,?)";

        //Bloque try donde iniciamos el proceso de realizar la consulta query

        try {

            ps = cn.prepareStatement(sql);

            // se setean las ?
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();  // modifica, no lee

          return true;


        } catch (Exception e){
            System.out.println("Error al agregar el estudiante " + e.getMessage());
        }  finally {
            try {
                cn.close();
            } catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;  // no se usa porque no se almacenara nada en la base de datos

        Connection cn = Conexion.getConexion();

        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setInt(1, estudiante.getIdEstudiante());

            ps.execute();

            return true;
        } catch (Exception e){
            System.out.println("Error al eliminar estudiante " + e.getMessage());
        }  finally {
            try {
                cn.close();
            } catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }
        }
        return false;
    }

    public boolean updateEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection cn = Conexion.getConexion();

        String sql = "UPDATE estudiante SET nombre = ? , apellido = ? , telefono = ? , email = ? WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());

            ps.execute();

            return true;
        } catch (Exception e){
            System.out.println("Error al actualizar estudiante " + e.getMessage());
        }
        return false;
    }
    public boolean updateEmail(Estudiante estudiante){
        PreparedStatement ps;
        Connection cn = Conexion.getConexion();

        String sql = "UPDATE estudiante SET email = ? WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, estudiante.getEmail());
            ps.setInt(2, estudiante.getIdEstudiante());

            ps.execute();

            return true;
        } catch (Exception e){
            System.out.println("No se pudo actualizar el correo del estudiante " + e.getMessage());
        }  finally {
            try {
                cn.close();
            }  catch (Exception e){
                System.out.println("No se pudo cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }
    public boolean updateName(Estudiante estudiante){
        PreparedStatement ps;
        Connection cn = Conexion.getConexion();

        String sql = "UPDATE estudiante SET nombre = ? WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setString(1, estudiante.getNombre());
            ps.setInt(2, estudiante.getIdEstudiante());

            ps.execute();

            return true;


        } catch (Exception e){
            System.out.println("No se pudo actualizar el nombre " + e.getMessage());
        }  finally {
            try {
                cn.close();
            } catch (Exception e){
                System.out.println("No se pudo cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }
    public boolean updateApellido(Estudiante estudiante){
        PreparedStatement ps;
        Connection cn = Conexion.getConexion();

        String sql = "UPDATE estudiante SET apellido = ? WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setString(1, estudiante.getApellido());
            ps.setInt(2, estudiante.getIdEstudiante());

            ps.execute();

            return true;
        }  catch (Exception e){

            System.out.println("No se actualizo el apellido " + e.getMessage());
        } finally {
            try{
                cn.close();
            } catch (Exception e){
                System.out.println("No se pudo cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateTelefono(Estudiante estudiante){
        PreparedStatement ps;
        Connection cn = Conexion.getConexion();

        String sql = "UPDATE estudiante SET telefono = ? WHERE id_estudiante = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setString(1, estudiante.getTelefono());
            ps.setInt(2, estudiante.getIdEstudiante());

            ps.execute();

            return true;
        }  catch (Exception e){
            System.out.println("No se pudo actualizar el telefono " + e.getMessage());
        }  finally {
            try {
                cn.close();
            } catch (Exception e){
                System.out.println("No se pudo cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    void main(){

    }


}
