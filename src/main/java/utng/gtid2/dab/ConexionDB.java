package utng.gtid2.dab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de establecer la conexión con la base de datos SQL Server.
 * Proporciona un método para obtener una conexión mediante JDBC.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ConexionDB {

    /**
     * URL de conexión a la base de datos SQL Server.
     */
    private static final String URL =
        "jdbc:sqlserver://localhost;databaseName=tienda_utng;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    /**
     * Establece y devuelve una conexión con la base de datos.
     *
     * @return Una conexión activa a la base de datos o {@code null} si ocurre un error.
     */
    public static Connection obtenerConexion() {
        try {
            Connection conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a SQL Server.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar:");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método principal utilizado para comprobar que la conexión
     * con la base de datos se establece correctamente.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        obtenerConexion();
    }
}