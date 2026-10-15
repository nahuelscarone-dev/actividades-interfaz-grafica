package modelo;

public class Tigre extends Felino implements Cazador {

    public Tigre(String nombre, int edad) { super(nombre, edad); }

    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Grrr! (rugido)"); }

    @Override
    public void cazar() {
        if (estaCansado()) { System.out.println(getNombre() + " está muy cansado para cazar."); return; }
        energia = energia - 30;
        System.out.println(getNombre() + " embosca a un ciervo. Energía: " + energia + "/100.");
    }
}
