package clase.pkg6.pkg31.pkg08.pkg2026.java.fundamentos;
import java.util.Scanner;

public class Clase631082026JAVAFundamentos {

    public static void main(String[] args) {
        
        //1 Imprimir "Hola, mundo" en consola.
        System.out.println("Hola, mundo");
       //2 Pedir el nombre del usuario e imprimirlo con un saludo. Por ejemplo: Hola, Ruperto.
        
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        String mensajeRechazo = "Por favor, ingrese un nombre en el campo.";
        if(nombre!= ""){
            System.out.println("nombre Ingresado correctamente. Bienvenido" + nombre);
        }else{
            System.out.println(mensajeRechazo);
        }
        
        //3 Pedir dos números enteros e imprimir su suma.
        int numeroA = sc.nextInt();
        int numeroB = sc.nextInt();

        int suma = numeroA + numeroB;
        
        //4 Pedir dos números e imprimir la suma, resta, multiplicación y división.
        int resta= numeroA - numeroB;
        double division =(numeroA / numeroB);
        int producto = numeroA * numeroB;
        System.out.println(resta);
        System.out.println(division);
        System.out.println(producto);


        
        

    }
}
