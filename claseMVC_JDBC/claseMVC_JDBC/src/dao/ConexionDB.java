package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  ConexionDB.java — la puerta de entrada a la base de datos   ║
 * ║  Es la ÚNICA clase que sabe cómo conectarse a MySQL.         ║
 * ║  Los datos de conexión (url, usuario, clave) NO están        ║
 * ║  escritos acá: se leen de config.properties, para poder      ║
 * ║  cambiarlos sin tocar el código.                             ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  config.properties (en la carpeta del proyecto) tiene este formato:
 *
 *       url=jdbc:mysql://localhost:3306/reserva
 *       usuario=root
 *       clave=
 *
 *  Para que Java "hable" con MySQL hace falta el driver (un .jar):
 *  se agrega en Libraries del proyecto (ver cheatsheet).
 */
public class ConexionDB {

    private static final String ARCHIVO_CONFIG = "config.properties";

    // Abre una conexión nueva cada vez que se la piden.
    // Quien la pide es responsable de cerrarla (con try-with-resources se cierra sola).
    public static Connection getConexion() throws SQLException {
        Properties config = new Properties();
        try (FileReader lector = new FileReader(ARCHIVO_CONFIG)) {
            config.load(lector);   // lee las líneas clave=valor del archivo
        } catch (IOException e) {
            throw new SQLException("No se pudo leer " + ARCHIVO_CONFIG, e);
        }

        return DriverManager.getConnection(        // → abre la conexión con MySQL
                config.getProperty("url"),
                config.getProperty("usuario"),
                config.getProperty("clave"));      // ← devuelve la conexión a quien la pidió
    }
}
