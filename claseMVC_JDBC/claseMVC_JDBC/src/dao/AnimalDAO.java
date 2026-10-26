package dao;

import java.util.ArrayList;
import modelo.Animal;

/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  AnimalDAO.java — INTERFAZ DEL DAO                           ║
 * ║  Es el "contrato": qué le puede pedir el Controlador a       ║
 * ║  cualquier DAO, sin importar DÓNDE se guardan los datos.     ║
 * ║                                                              ║
 * ║  La implementan:                                             ║
 * ║    · AnimalDAOArchivo → guarda en animales.txt   (clase 17)  ║
 * ║    · AnimalDAOJDBC    → guarda en MySQL          (clase 18)  ║
 * ║                                                              ║
 * ║  El Controlador solo conoce ESTE contrato. Por eso no se     ║
 * ║  entera de cuál de las dos está usando.                      ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public interface AnimalDAO {

    // Lee los animales guardados y los devuelve como lista.
    // Si todavía no hay nada guardado, devuelve una lista vacía.
    ArrayList<Animal> cargarAnimales();

    // Guarda la lista completa. Devuelve true si salió bien y false si falló
    // (el Controlador decide cómo avisarle al usuario).
    boolean guardarAnimales(ArrayList<Animal> lista);
}
