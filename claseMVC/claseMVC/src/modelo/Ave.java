package modelo;

public abstract class Ave extends Animal {
    public Ave(String nombre, int edad) { super(nombre, edad); }
    public void volar() {
        if (estaCansado()) { System.out.println(getNombre() + " no tiene energía para volar."); return; }
        energia = energia - 10;
        System.out.println(getNombre() + " levanta vuelo. Energía: " + energia + "/100.");
    }
}
