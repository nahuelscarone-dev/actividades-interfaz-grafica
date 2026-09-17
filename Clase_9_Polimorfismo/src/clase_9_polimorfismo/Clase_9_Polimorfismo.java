/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase_9_polimorfismo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author GZ TIENDA
 */
public class Clase_9_Polimorfismo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        clsLibroFisico libroF1 = new clsLibroFisico("El Señor de los Anillos", "Tolkien", 20000, 1500, 5);
        clsLibroDigital libroD1 = new clsLibroDigital ("El Señor de los Anillos", "Tolkien", 23424, "8MBs");
        clsLibroUsado libroU1 = new clsLibroUsado ("Winnie The Pooh", "Christhopher Robin", 42421, "Deteriorado");
        clsLibroDeColeccion libroC1 = new clsLibroDeColeccion ("Harry Potter", "J.K. Rowling", 642, 47212);

        libroU1.setEstado(EstadoLibro.AGOTADO);
        ArrayList<clsLibro> listaLibros = new ArrayList<>();
        
        listaLibros.add(libroF1);
        listaLibros.add(libroD1);
        listaLibros.add(libroU1);
        listaLibros.add(libroC1);
        
        Scanner scanner = new Scanner(System.in);
        
        int cantidadLibros = 0;
        boolean entradaValida = false;
        
        System.out.println("SISTEMA DE CARGA DE INVENTARIO");
        
        while(!entradaValida)
        {
            System.out.println("¿Cuántos libros nuevos desea cargar al catálogo?: ");
            String entradaUsuario = scanner.nextLine();
            
            try{
                cantidadLibros = Integer.parseInt(entradaUsuario);
                entradaValida = true;
                System.out.println("Perfecto, preparamos el sistema para " + cantidadLibros + " libros.\n");
                
            }
            catch(NumberFormatException e){
                System.out.println("ERROR: Ingrese únicamente números enteros. Intentando nuevamente. \n ");
            }
        }
        
        
        for(clsLibro libro : listaLibros)
        {
            libro.mostrarInfo();       
            
            libro.mostrarDetalle();
            System.out.println("Precio final: $" + libro.calcularPrecioFinal());
           
           
           if(libro.getEstado() != EstadoLibro.AGOTADO)
           { 
            
           //Pattern Matching. En una sola línea Java verifica si el libro es promocionable, y si da true, hace el downcast.
           // Este libro firmó el contrato Promocionable?
               if(libro instanceof Promocionable lp)
           { 
               
               System.out.println("Motivo de descuento: " + lp.getMotivoPromocion());
               System.out.println("Precio con promo: " + lp.precioConPromocion(libro.calcularPrecioFinal()));
           } else
           {
               System.out.println("Precio normal:" + libro.calcularPrecioFinal());
           }
           }
            System.out.println("-----------------------");
        }
    }
    
}
