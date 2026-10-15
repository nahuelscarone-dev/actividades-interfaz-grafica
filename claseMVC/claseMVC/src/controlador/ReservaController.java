package controlador;

import modelo.*;
import vista.ReservaView;
/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  ReservaController.java — CONTROLADOR                        ║
 * ║  Intermediario entre la Vista y el Modelo.                   ║
 * ║  Conoce a las dos. Valida, decide y ordena actualizar.       ║
 * ║                                                              ║
 * ║  Cómo leer las flechas:                                      ║
 * ║    // →  "acá voy a OTRA capa" (llamo a un método de otro)   ║
 * ║    // ←  "acá me VUELVE una respuesta" (return)              ║
 * ║  Los números ①②③ marcan el orden en que se ejecuta un FLUJO. ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class ReservaController {
 
    // ── Referencias a las otras dos capas ─────────────────────
    private Reserva     modelo;   // → puede llamar al Modelo
    private ReservaView vista;    // → puede llamar a la Vista
 
    // ── Constructor ───────────────────────────────────────────
    // El main crea al Modelo y a la Vista y se los pasa acá.
    public ReservaController(Reserva modelo, ReservaView vista) {
        this.modelo = modelo;
        this.vista  = vista;
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 1 · Arranque: mostrar los animales que ya existen
    // ═════════════════════════════════════════════════════════
    // ① El main llama a iniciar() justo antes de mostrar la ventana.
    public void iniciar() {
        refrescarVista();   // ② arma lo que la Vista tiene que mostrar (ver abajo)
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 2 · Agregar un animal
    // ═════════════════════════════════════════════════════════
    // ③ La Vista llama acá con lo que el usuario escribió (todo como texto).
    public void agregarAnimal(String nombre, String especie, String edadTexto) {
 
        // ④ El Controlador VALIDA. La Vista no valida nada.
        if (nombre.trim().isEmpty()) {
            vista.mostrarError("El nombre es obligatorio.");   // → va a la Vista
            return;
        }
 
        int edad;
        try {
            edad = Integer.parseInt(edadTexto.trim());   // puede fallar (clase 9: try-catch)
        } catch (NumberFormatException e) {
            vista.mostrarError("La edad tiene que ser un número.");   // → va a la Vista
            return;
        }
        if (edad < 0) {
            vista.mostrarError("La edad no puede ser negativa.");     // → va a la Vista
            return;
        }
 
        // ⑤ El Controlador CREA el objeto del Modelo (polimorfismo: queda como Animal).
        Animal nuevo = crearAnimal(nombre.trim(), especie, edad);
        if (nuevo == null) {
            // La especie no es ninguna de las conocidas (ej.: quedó "Selecciona un animal").
            vista.mostrarError("Elegí una especie.");   // → va a la Vista
            return;
        }
 
        modelo.agregar(nuevo);   // ⑥ → va al Modelo: "guardá este animal"
 
        refrescarVista();        // ⑦ → la Vista se actualiza (ver refrescarVista)
        vista.limpiarFormulario();                                  // → va a la Vista
        vista.mostrarMensaje(nuevo.getNombre() + " se sumó a la reserva.");   // → va a la Vista
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 3 · Eliminar el animal seleccionado
    // ═════════════════════════════════════════════════════════
    // ① La Vista (ya confirmó con el usuario) llama acá con la fila elegida.
    public void eliminarAnimal(int fila) {
        if (!filaValida(fila)) { return; }   // ② sin selección: se corta (la Vista ya avisó)
 
        Animal sacado = modelo.eliminar(fila);   // ③ → va al Modelo: "sacá este"
                                                 // ← el Modelo devuelve el animal que sacó
 
        refrescarVista();   // ④ → la Vista se actualiza
        vista.mostrarMensaje(sacado.getNombre() + " salió de la reserva.");   // → va a la Vista
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 4 · Alimentar (polimorfismo: cada especie come distinto)
    // ═════════════════════════════════════════════════════════
    public void alimentar(int fila) {
        if (!filaValida(fila)) { return; }
 
        Animal a = modelo.get(fila);   // → va al Modelo: "dame el de esta fila"
                                       // ← el Modelo devuelve el Animal
        a.alimentarse();               // → el Modelo ejecuta SU versión de alimentarse()
 
        refrescarVista();              // → la Vista se actualiza
        vista.mostrarMensaje(a.getNombre() + " se alimentó. Energía: " + a.getEnergia() + ".");
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 5 · Cazar (interfaz Cazador + instanceof)
    // ═════════════════════════════════════════════════════════
    public void cazar(int fila) {
        if (!filaValida(fila)) { return; }
 
        Animal a = modelo.get(fila);   // → Modelo / ← devuelve el Animal
 
        // La regla "¿puede cazar?" es del Modelo (implementa Cazador o no).
        if (!(a instanceof Cazador)) {
            vista.mostrarError(a.getNombre() + " no es cazador.");   // → va a la Vista
            return;
        }
 
        int energiaAntes = a.getEnergia();
        ((Cazador) a).cazar();   // → el Modelo ejecuta SU cazar() (cada especie, distinto)
 
        if (a.getEnergia() == energiaAntes) {
            // Si la energía no cambió, el animal estaba demasiado cansado.
            vista.mostrarMensaje(a.getNombre() + " está muy cansado para cazar.");
        } else {
            vista.mostrarMensaje(a.getNombre() + " cazó. Energía: " + a.getEnergia() + ".");
        }
        refrescarVista();   // → la Vista se actualiza
    }
 
    // ═════════════════════════════════════════════════════════
    //  FLUJO 6 · Cambiar el estado de salud (enum)
    // ═════════════════════════════════════════════════════════
    public void cambiarEstado(int fila, EstadoSalud nuevoEstado) {
        if (!filaValida(fila)) { return; }
 
        Animal a = modelo.get(fila);       // → Modelo / ← devuelve el Animal
        a.setEstadoSalud(nuevoEstado);     // → el Modelo ajusta la energía al nuevo tope del enum
 
        refrescarVista();                  // → la Vista se actualiza
        vista.mostrarMensaje(a.getNombre() + " ahora está " + nuevoEstado
                + " (tope de energía: " + nuevoEstado.getEnergiaMaxima() + ").");
    }
 
    // ═════════════════════════════════════════════════════════
    //  Métodos auxiliares (privados: la Vista no los ve)
    // ═════════════════════════════════════════════════════════
 
    // El corazón del patrón: le pasa a la Vista los datos ACTUALES del Modelo.
    // La Vista nunca va a buscar los datos sola: se los da el Controlador.
    private void refrescarVista() {
        vista.actualizarTabla(modelo.getAnimales());   // → va a la Vista (con la lista del Modelo)
        vista.actualizarResumen(                       // → va a la Vista
                modelo.cantidad(),                     // ← Modelo devuelve cuántos hay
                modelo.contarCazadores(),              // ← Modelo devuelve cuántos cazan
                modelo.contarCansados());              // ← Modelo devuelve cuántos están cansados
    }
 
    // Si no hay fila seleccionada, la tabla devuelve -1.
    private boolean filaValida(int fila) {
        if (fila < 0 || fila >= modelo.cantidad()) {
            vista.mostrarError("Seleccioná un animal de la tabla.");   // → va a la Vista
            return false;
        }
        return true;
    }
 
    // Decide qué clase concreta crear según el texto del combo.
    private Animal crearAnimal(String nombre, String especie, int edad) {
        switch (especie) {
            case "Tigre":      return new Tigre(nombre, edad);
            case "GatoMontes": return new GatoMontes(nombre, edad);
            case "Zorro":      return new Zorro(nombre, edad);
            case "Lobo":       return new Lobo(nombre, edad);
            case "Aguila":     return new Aguila(nombre, edad);
            case "Loro":       return new Loro(nombre, edad);
            default:           return null;   // texto que no es una especie
        }
    }
}
 