package modelo;

/*
 * ════════════════════════════════════════════════════════════════════════
 *  FabricaAnimales  (MODELO)
 * ────────────────────────────────────────────────────────────────────────
 *  Sabe crear el animal correcto a partir del NOMBRE de su especie.
 *  Antes este switch vivía en el Controlador. Ahora lo necesitan DOS
 *  clases (el Controlador, cuando el usuario elige una especie en el
 *  combo, y el DAO, cuando lee una línea del archivo), así que se
 *  escribe una sola vez acá y las dos lo reutilizan.
 *
 *  Es una clase del modelo: no importa Swing ni archivos.
 * ════════════════════════════════════════════════════════════════════════
 */
public class FabricaAnimales {

    // Devuelve null si el texto no es ninguna especie conocida.
    public static Animal crear(String especie, String nombre, int edad) {
        if (especie == null) {
            return null;
        }
        switch (especie) {
            case "Tigre":      return new Tigre(nombre, edad);
            case "GatoMontes": return new GatoMontes(nombre, edad);
            case "Zorro":      return new Zorro(nombre, edad);
            case "Lobo":       return new Lobo(nombre, edad);
            case "Aguila":     return new Aguila(nombre, edad);
            case "Loro":       return new Loro(nombre, edad);
            default:           return null;
        }
    }
}
