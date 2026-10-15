package modelo;

/*
 *  Loro implementa Domesticable (no Cazador: no caza).
 */
public class Loro extends Ave implements Domesticable {

    public Loro(String nombre, int edad) { super(nombre, edad); }

    @Override
    public void hacerSonido() { System.out.println(getNombre() + ": Squawk!"); }

    @Override
    public void alimentarse() {
        energia = Math.min(energia + 20, 100);
        System.out.println(getNombre() + " come frutas. Energía: " + energia + "/100.");
    }

    public void repetir(String frase) { System.out.println(getNombre() + " repite: \"" + frase + "\""); }

    // Requeridos por Domesticable:
    @Override
    public void entrenar(String comando) {
        System.out.println(getNombre() + " repite el comando \"" + comando + "\" al entrenarlo.");
    }
    @Override
    public boolean respondeAEntrenamiento() {
        return true;    // los loros suelen ser muy receptivos
    }
}
