package modelo;

public abstract class Felino extends Animal {
    public Felino(String nombre, int edad) { super(nombre, edad); }
    public void afilarGarras() { System.out.println(getNombre() + " afila sus garras."); }
    @Override
    public void alimentarse() {
        energia = Math.min(energia + 30, 100);
        System.out.println(getNombre() + " devora su presa. Energía: " + energia + "/100.");
    }
}
