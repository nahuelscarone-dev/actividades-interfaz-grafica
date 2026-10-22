package modelo;

/*
 * ════════════════════════════════════════════════════════════════════════
 *  ENUM EstadoSalud  ·  con atributos, constructor y método
 * ────────────────────────────────────────────────────────────────────────
 *  Un enum es, por dentro, una clase especial: cada valor es un objeto ya
 *  creado, y puede llevar sus propios datos. Acá cada estado de salud
 *  define hasta cuánta energía puede recuperar un animal mientras esté
 *  en ese estado: un animal HERIDO no puede volver a estar al 100%,
 *  solo hasta su tope (60).
 * ════════════════════════════════════════════════════════════════════════
 */
public enum EstadoSalud {

    // Cada valor llama al constructor con SU propio dato.
    SALUDABLE(100),
    HERIDO(60),
    ENFERMO(40),
    EN_TRATAMIENTO(70);

    private final int energiaMaxima;   // el dato propio de cada valor

    // El constructor de un enum es SIEMPRE implícitamente private:
    // nadie puede crear un valor nuevo con "new" desde afuera.
    EstadoSalud(int energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }
}
