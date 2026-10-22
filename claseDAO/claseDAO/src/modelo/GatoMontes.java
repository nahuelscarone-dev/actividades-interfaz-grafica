package modelo;

public class GatoMontes extends Felino implements Cazador {
    public GatoMontes(String nombre, int edad) { super(nombre, edad); }
    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Fffff! (bufido)"); }
    @Override
    public void cazar() {
        if (estaCansado()) { System.out.println(getNombre() + " está muy cansado para cazar."); return; }
        energia = energia - 20;
        System.out.println(getNombre() + " acecha y atrapa un roedor. Energía: " + energia + "/100.");
    }
}
