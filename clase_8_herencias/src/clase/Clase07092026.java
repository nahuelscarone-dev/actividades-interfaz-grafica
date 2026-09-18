/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase.pkg07.pkg09.pkg2026;

import java.util.ArrayList;
/**
 *
 * @author Alumno
 */
public class Clase07092026 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // El físico necesita: título, autor, precio base, costo de envío y stock
    /*    

       
        System.out.println(libroD1.calcularPrecioFinal());
        libroF1.mostrarDetalle();
        libroU1.mostrarDetalle();
        libroU1.mostrarInfo();
        
        
        libroF1.mostrarInfo();
        libroF1.mostrarDetalle();
        
        
      */  
        clsLibroFisico libroF1 = new clsLibroFisico("El Señor de los Anillos", "Tolkien", 20000, 1500, 5);
        clsLibroDigital libroD1 = new clsLibroDigital ("El Señor de los Anillos", "Tolkien", 23424, "8MBs");
        clsLibroUsado libroU1 = new clsLibroUsado ("Winnie The Pooh", "Christhopher Robin", 42421, "Deteriorado");
        clsLibroDeColeccion libroC1 = new clsLibroDeColeccion ("Harry Potter", "J.K. Rowling", 642, 47212);

        
        ArrayList<clsLibro> listaLibros = new ArrayList<>();
        
        listaLibros.add(libroF1);
        listaLibros.add(libroD1);
        listaLibros.add(libroU1);
        listaLibros.add(libroC1);
        for(clsLibro libro : listaLibros)
        {
            libro.mostrarInfo();       
            
            libro.mostrarDetalle();
            System.out.println("Precio final: $" + libro.calcularPrecioFinal());
           
            
            if (libro.disponibleParaVenta()) {
            System.out.println("Estado Comercial: LISTO PARA LA VENTA");
            } else {
            // Si responde false, el main decide qué mensaje de error mostrar
            System.out.println("Estado Comercial: RETENIDO (Sin stock o deteriorado)");
            }
             System.out.println("-----------------");
        }
    }
} 
