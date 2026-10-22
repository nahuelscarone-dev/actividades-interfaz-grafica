package modelo;

public class Aguila extends Ave implements Cazador {

    public Aguila(String nombre, int edad) { super(nombre, edad); }

    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Screeech!"); }

    @Override
    public void alimentarse() {
        energia = Math.min(energia + 30, 100);
        System.out.println(getNombre() + " se alimenta de su presa. Energía: " + energia + "/100.");
    }

    @Override
    public void cazar() {
        if (estaCansado()) { System.out.println(getNombre() + " está muy cansada para cazar."); return; }
        energia = energia - 25;
        System.out.println(getNombre() + " se lanza en picada y atrapa una presa. Energía: " + energia + "/100.");
    }
}
