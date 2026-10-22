package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Animal;
import modelo.EstadoSalud;
import modelo.FabricaAnimales;

/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  AnimalDAO.java — DAO (Data Access Object)                   ║
 * ║  La ÚNICA clase que toca el archivo animales.txt.            ║
 * ║  Conoce al Modelo (Animal), pero NO a la Vista ni al         ║
 * ║  Controlador. No importa javax.swing.                        ║
 * ║                                                              ║
 * ║  // →  "voy a otra capa"       // ←  "me vuelve una respuesta"║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  Formato del archivo: una línea por animal, campos separados por ";"
 *
 *       Especie;Nombre;Edad;Energía;Estado
 *       Lobo;Fenrir;3;100;SALUDABLE
 *
 *  Si mañana los datos van a una base de datos, se escribe OTRO DAO
 *  con los mismos métodos y el resto del programa no se entera.
 */
public class AnimalDAO {

    private static final String ARCHIVO   = "animales.txt";   // queda en la carpeta del proyecto
    private static final String SEPARADOR = "|";

    // ═════════════════════════════════════════════════════════
    //  LEER: archivo → ArrayList<Animal>
    // ═════════════════════════════════════════════════════════
    // El Controlador llama acá UNA vez, al arrancar la aplicación.
    public ArrayList<Animal> cargarAnimales() {
        ArrayList<Animal> lista = new ArrayList<>();

        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return lista;   // ← primera vez: no hay archivo, se devuelve una lista vacía
        }

        // try-with-resources: el archivo se cierra solo al terminar el bloque.
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {   // readLine() devuelve null al final
                if (linea.trim().isEmpty()) {
                    continue;   // se saltean las líneas vacías
                }
                try {
                    lista.add(deTexto(linea));   // texto → objeto Animal
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    // Una línea mal escrita no tira abajo todo el programa: se ignora.
                    System.out.println("Línea ignorada (formato inválido): " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;   // ← vuelve al Controlador con la lista completa
    }

    // ═════════════════════════════════════════════════════════
    //  ESCRIBIR: ArrayList<Animal> → archivo
    // ═════════════════════════════════════════════════════════
    // El Controlador llama acá después de cada cambio en el modelo.
    // Sobrescribe el archivo completo con el estado actual de la lista.
    public boolean guardarAnimales(ArrayList<Animal> lista) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Animal a : lista) {
                escritor.write(aTexto(a));   // objeto Animal → texto
                escritor.newLine();
            }
            return true;    // ← le avisa al Controlador que salió bien
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            return false;   // ← le avisa al Controlador que falló (él decide qué hacer)
        }
    }

    // ═════════════════════════════════════════════════════════
    //  Conversión objeto ⇄ texto (privados: nadie más las usa)
    // ═════════════════════════════════════════════════════════

    // Animal → "Lobo;Fenrir;3;100;SALUDABLE"
    private String aTexto(Animal a) {
        return a.getClass().getSimpleName() + SEPARADOR   // el nombre de la clase = la especie
             + a.getNombre()                + SEPARADOR
             + a.getEdad()                  + SEPARADOR
             + a.getEnergia()               + SEPARADOR
             + a.getEstadoSalud().name();
    }

    // "Lobo;Fenrir;3;100;SALUDABLE" → Animal
    private Animal deTexto(String linea) {
        String[] partes = linea.split(SEPARADOR);

        String      especie = partes[0];
        String      nombre  = partes[1];
        int         edad    = Integer.parseInt(partes[2]);       // puede lanzar NumberFormatException
        int         energia = Integer.parseInt(partes[3]);
        EstadoSalud estado  = EstadoSalud.valueOf(partes[4]);    // texto → valor del enum

        Animal a = FabricaAnimales.crear(especie, nombre, edad);   // → Modelo: crea la subclase correcta
        if (a == null) {
            throw new IllegalArgumentException("Especie desconocida: " + especie);
        }
        a.setEstadoSalud(estado);   // primero el estado (define el tope de energía)...
        a.setEnergia(energia);      // ...y después la energía que tenía guardada
        return a;
    }
}
