package org.example.Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//hara la funcion del apliccattion properties, donde se dan los detalles para la conexion de la dataBase
public class Conexion {
    public static Connection getConexion(){
        Connection connection = null;

        var dataBase = "estudiantes_db"; //nombre de la base de datos
        var url = "jdbc:mysql://localhost:3306/" + dataBase;
        var usuario = "root";
        var password = "root";

        //Se carga la clase del driver de mysql en memoria
        //Manejo de excepciones porque la conexion se puede caer
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar la base de datos" + e.getMessage());
        }
        return connection;
    }

}

