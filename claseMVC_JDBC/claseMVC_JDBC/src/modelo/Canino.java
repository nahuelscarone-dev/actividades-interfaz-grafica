package modelo;

public abstract class Canino extends Animal {
    public Canino(String nombre, int edad) { super(nombre, edad); }
    public void marcarTerritorio() { System.out.println(getNombre() + " marca su territorio."); }
    @Override
    public void alimentarse() {
        energia = Math.min(energia + 25, 100);
        System.out.println(getNombre() + " come con la manada. Energía: " + energia + "/100.");
    }
}
