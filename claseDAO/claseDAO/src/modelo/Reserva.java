package modelo;

import java.util.ArrayList;

/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Reserva.java — MODELO (el "contenedor" de los datos)        ║
 * ║  Guarda la lista de animales y sabe operar sobre ella.       ║
 * ║  NO importa javax.swing · NO conoce a la Vista ni al         ║
 * ║  Controlador. Solo responde cuando le preguntan.             ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  Animal, Tigre, Lobo, etc. son el Modelo "por dentro" (qué ES cada
 *  animal). Reserva es el Modelo "por fuera": el conjunto de animales
 *  que el Controlador va a consultar y modificar.
 */
public class Reserva {

    private ArrayList<Animal> animales = new ArrayList<>();

    // La reserva arranca VACÍA: los animales los carga el Controlador desde
    // el archivo (a través del DAO) con setAnimales().
    public Reserva() { }

    public void setAnimales(ArrayList<Animal> animales) {
        this.animales = animales;
    }

    // ── Consultas ─────────────────────────────────────────────

    // El Controlador pide la lista...
    public ArrayList<Animal> getAnimales() {
        return animales;   // ← devuelve la lista al Controlador
    }

    public Animal get(int indice) {
        return animales.get(indice);   // ← devuelve UN animal al Controlador
    }

    public int cantidad() {
        return animales.size();
    }

    // ── Modificaciones ────────────────────────────────────────

    public void agregar(Animal nuevo) {
        animales.add(nuevo);
    }

    public Animal eliminar(int indice) {
        return animales.remove(indice);   // ← devuelve el que se sacó
    }

    // ── Lógica de negocio ─────────────────────────────────────
    // Cuántos de los animales de la reserva implementan Cazador.
    // (Es una regla de la reserva, no de la pantalla: por eso vive acá.)
    public int contarCazadores() {
        int total = 0;
        for (Animal a : animales) {
            if (a instanceof Cazador) {
                total++;
            }
        }
        return total;   // ← devuelve el número al Controlador
    }

    // Cuántos animales están cansados (energía < 30).
    public int contarCansados() {
        int total = 0;
        for (Animal a : animales) {
            if (a.estaCansado()) {
                total++;
            }
        }
        return total;   // ← devuelve el número al Controlador
    }
}
