package modelo;

/*
 * ════════════════════════════════════════════════════════════════════════
 *  INTERFAZ Domesticable  ·  Clase 9: interfaces con método default
 * ────────────────────────────────────────────────────────────────────────
 *  No todos los animales de la reserva pueden domesticarse: un tigre o un
 *  águila se mantienen salvajes, pero un lobo criado en cautiverio o un
 *  loro sí pueden vincularse con un cuidador. Domesticable agrupa esa
 *  capacidad, sin importar de qué familia es el animal (Lobo es Canino,
 *  Loro es Ave: ramas distintas, misma capacidad).
 *
 *  entrenar() y respondeAEntrenamiento() son abstractos: cada especie
 *  entrena distinto y tiene su propio criterio. Pero la lógica de
 *  "intentar vincularse" es la misma para cualquier Domesticable, así que
 *  se resuelve UNA sola vez acá como método default.
 * ════════════════════════════════════════════════════════════════════════
 */
public interface Domesticable {

    void entrenar(String comando);
    boolean respondeAEntrenamiento();

    // MÉTODO DEFAULT: tiene cuerpo, se hereda tal cual.
    default void vincularConCuidador() {
        if (respondeAEntrenamiento()) {
            System.out.println("Responde bien al vínculo con su cuidador.");
            entrenar("acercarse");
        } else {
            System.out.println("Todavía no está listo para vincularse.");
        }
    }
}
