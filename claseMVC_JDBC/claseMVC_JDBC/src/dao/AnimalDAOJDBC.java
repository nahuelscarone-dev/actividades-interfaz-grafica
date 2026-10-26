package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Animal;
import modelo.EstadoSalud;
import modelo.FabricaAnimales;

/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  AnimalDAOJDBC.java — DAO que guarda en una base MySQL       ║
 * ║  Hace lo MISMO que AnimalDAOArchivo, pero con una tabla:     ║
 * ║    archivo: Animal ⇄ línea de texto   (split / aTexto)       ║
 * ║    base:    Animal ⇄ fila de la tabla (rs.getXxx / ps.setXxx)║
 * ║                                                              ║
 * ║  Conoce al Modelo, pero NO a la Vista ni al Controlador.     ║
 * ║  No importa javax.swing.                                     ║
 * ║                                                              ║
 * ║  // →  "voy a otra capa"       // ←  "me vuelve una respuesta"║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  Tabla (se crea con reserva.sql):
 *
 *     animales ( id, especie, nombre, edad, energia, estado )
 */
public class AnimalDAOJDBC implements AnimalDAO {

    // ═════════════════════════════════════════════════════════
    //  LEER: tabla animales → ArrayList<Animal>
    // ═════════════════════════════════════════════════════════
    // El Controlador llama acá UNA vez, al arrancar la aplicación.
    @Override
    public ArrayList<Animal> cargarAnimales() {
        ArrayList<Animal> lista = new ArrayList<>();

        String sql = "SELECT especie, nombre, edad, energia, estado FROM animales ORDER BY id";

        // try-with-resources: la conexión, la consulta y el resultado se cierran solos.
        try (Connection cn = ConexionDB.getConexion();          // → abre la conexión con MySQL
             PreparedStatement ps = cn.prepareStatement(sql);    // → prepara la consulta
             ResultSet rs = ps.executeQuery()) {                 // → ejecuta el SELECT
                                                                 // ← MySQL devuelve las filas
            while (rs.next()) {                                  // next() pasa a la fila siguiente
                try {
                    lista.add(deFila(rs));                       // fila → objeto Animal
                } catch (IllegalArgumentException e) {
                    // Una fila con datos inválidos no tira abajo todo el programa: se ignora.
                    System.out.println("Fila ignorada: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la base de datos: " + e.getMessage());
        }

        return lista;   // ← vuelve al Controlador con la lista completa
    }

    // ═════════════════════════════════════════════════════════
    //  ESCRIBIR: ArrayList<Animal> → tabla animales
    // ═════════════════════════════════════════════════════════
    // El Controlador llama acá después de cada cambio en el modelo.
    // Igual que con el archivo: se reemplaza TODO por el estado actual de la lista.
    @Override
    public boolean guardarAnimales(ArrayList<Animal> lista) {
        String borrar   = "DELETE FROM animales";
        String insertar = "INSERT INTO animales (especie, nombre, edad, energia, estado) "
                        + "VALUES (?, ?, ?, ?, ?)";     // cada ? se completa después con ps.setXxx

        try (Connection cn = ConexionDB.getConexion()) {     // → abre la conexión con MySQL
            cn.setAutoCommit(false);   // "todo o nada": nada se guarda hasta llamar a commit()
            try (PreparedStatement psBorrar   = cn.prepareStatement(borrar);
                 PreparedStatement psInsertar = cn.prepareStatement(insertar)) {

                psBorrar.executeUpdate();                    // → vacía la tabla

                for (Animal a : lista) {
                    aFila(a, psInsertar);                    // objeto Animal → parámetros del INSERT
                    psInsertar.executeUpdate();              // → agrega una fila
                }
                cn.commit();                                 // ahora sí: se guarda todo junto
                return true;    // ← le avisa al Controlador que salió bien

            } catch (SQLException e) {
                cn.rollback();  // si algo falló, se deshace todo: la tabla queda como estaba
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar en la base de datos: " + e.getMessage());
            return false;       // ← le avisa al Controlador que falló (él decide qué hacer)
        }
    }

    // ═════════════════════════════════════════════════════════
    //  Conversión objeto ⇄ fila (privados: nadie más las usa)
    // ═════════════════════════════════════════════════════════

    // Animal → parámetros del INSERT (el número es la posición del ?)
    private void aFila(Animal a, PreparedStatement ps) throws SQLException {
        ps.setString(1, a.getClass().getSimpleName());   // el nombre de la clase = la especie
        ps.setString(2, a.getNombre());
        ps.setInt(3, a.getEdad());
        ps.setInt(4, a.getEnergia());
        ps.setString(5, a.getEstadoSalud().name());      // el enum se guarda como texto
    }

    // fila actual del ResultSet → Animal
    private Animal deFila(ResultSet rs) throws SQLException {
        String      especie = rs.getString("especie");
        String      nombre  = rs.getString("nombre");
        int         edad    = rs.getInt("edad");
        int         energia = rs.getInt("energia");
        EstadoSalud estado  = EstadoSalud.valueOf(rs.getString("estado"));   // texto → enum

        Animal a = FabricaAnimales.crear(especie, nombre, edad);   // → Modelo: crea la subclase correcta
        if (a == null) {
            throw new IllegalArgumentException("Especie desconocida: " + especie);
        }
        a.setEstadoSalud(estado);   // primero el estado (define el tope de energía)...
        a.setEnergia(energia);      // ...y después la energía que tenía guardada
        return a;
    }
}
