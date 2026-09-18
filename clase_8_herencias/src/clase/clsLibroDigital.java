/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase.pkg07.pkg09.pkg2026;

/**
 *
 * @author Alumno
 */
public class clsLibroDigital extends clsLibro {
    
    private String formato;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public clsLibroDigital(String titulo, String autor, double precioBase, String formato)
    {
        super(titulo, autor, precioBase);
        
        this.formato = formato;
    }
    
    @Override 
     public double calcularPrecioFinal()
     {
         return getPrecioBase() * 0.80;
     }       
     
     @Override
     public void mostrarDetalle()
     {
         System.out.println("Formato del libro Digital es: " + formato);
     } 
     
     @Override
     public boolean disponibleParaVenta()
     {
         return true;
     }
}
