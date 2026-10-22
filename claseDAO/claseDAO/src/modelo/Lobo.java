package modelo;

/*
 *  Lobo implementa Cazador Y Domesticable: puede cazar y, si fue criado
 *  en la reserva desde pequeño, también vincularse con su cuidador.
 */
public class Lobo extends Canino implements Cazador, Domesticable {

    public Lobo(String nombre, int edad) { super(nombre, edad); }

    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Auuuuu!"); }

    public void aullarALaLuna() { System.out.println(getNombre() + " aúlla a la luna llena."); }

    @Override
    public void cazar() {
        if (estaCansado()) { System.out.println(getNombre() + " está muy cansado para cazar."); return; }
        energia = energia - 25;
        System.out.println(getNombre() + " persigue a su presa en manada. Energía: " + energia + "/100.");
    }

    // Requeridos por Domesticable:
    @Override
    public void entrenar(String comando) {
        System.out.println(getNombre() + " aprende el comando \"" + comando + "\".");
    }
    @Override
    public boolean respondeAEntrenamiento() {
        return getEdad() < 5;    // los lobos jóvenes responden mejor
    }
    // vincularConCuidador() NO se escribe: se hereda de Domesticable (default).
}
