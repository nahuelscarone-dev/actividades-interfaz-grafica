package modelo;

/*
 * ════════════════════════════════════════════════════════════════════════
 *  CLASE Animal  (raíz de la jerarquía)  ·  Clase 9
 * ════════════════════════════════════════════════════════════════════════
 */
public abstract class Animal {
    private String nombre;
    private int    edad;
    protected int  energia;
    private EstadoSalud estadoSalud;

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        setEdad(edad);
        this.energia = 100;
        this.estadoSalud = EstadoSalud.SALUDABLE;   // todo animal nace saludable
    }

    public void presentarse() {
        System.out.println("Soy " + nombre + ", tengo " + edad +
                           " años. Energía: " + energia + "/" + estadoSalud.getEnergiaMaxima() +
                           ". Estado: " + estadoSalud);
    }

    public boolean estaCansado() { return energia < 30; }

    // Usa el DATO del enum: no puede recuperar más energía que el tope
    // que permite su estado de salud actual.
    public void recuperarEnergia(int cantidad) {
        int tope = estadoSalud.getEnergiaMaxima();
        energia = energia + cantidad;
        if (energia > tope) {
            energia = tope;   // el tope lo define el ENUM, no un número fijo
        }
        System.out.println(nombre + " recupera energía. Energía: " + energia + "/" + tope + ".");
    }

    public abstract void hacerSonido();
    public abstract void alimentarse();

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { if (edad < 0) return; this.edad = edad; }
    public int getEnergia() { return energia; }

    // Se usa al CARGAR un animal desde el archivo: restaura la energía que tenía.
    // Respeta siempre el tope que permite su estado de salud (el enum).
    public void setEnergia(int energia) {
        if (energia < 0) {
            energia = 0;
        }
        int tope = estadoSalud.getEnergiaMaxima();
        if (energia > tope) {
            energia = tope;
        }
        this.energia = energia;
    }

    public EstadoSalud getEstadoSalud() { return estadoSalud; }

    // Al cambiar el estado de salud, si la energía actual supera el nuevo
    // tope, se ajusta al toque: el efecto del enum se ve inmediatamente.
    public void setEstadoSalud(EstadoSalud estadoSalud) {
        this.estadoSalud = estadoSalud;
        int tope = estadoSalud.getEnergiaMaxima();
        if (energia > tope) {
            energia = tope;
        }
    }
}
