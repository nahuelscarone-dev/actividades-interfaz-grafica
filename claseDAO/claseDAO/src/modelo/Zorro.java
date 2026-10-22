package modelo;

public class Zorro extends Canino implements Cazador {
    public Zorro(String nombre, int edad) { super(nombre, edad); }
    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Guau agudo!"); }
    @Override
    public void cazar() {
        if (estaCansado()) { System.out.println(getNombre() + " está muy cansado para cazar."); return; }
        energia = energia - 15;
        System.out.println(getNombre() + " caza un conejo. Energía: " + energia + "/100.");
    }
}
