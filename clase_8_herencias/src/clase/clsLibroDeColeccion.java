/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase.pkg07.pkg09.pkg2026;

/**
 *
 * @author GZ TIENDA
 */
public class clsLibroDeColeccion extends clsLibro{
    
    private double porcentajeRecargo;
    
    public clsLibroDeColeccion (String titulo, String autor, double precioBase, double porcentajeRecargo)
    {
        super(titulo, autor, precioBase);
        
        this.porcentajeRecargo = porcentajeRecargo;
    }
    
    @Override
    public double calcularPrecioFinal()
    {
        double calculoRecargo = getPrecioBase() + (getPrecioBase() * (porcentajeRecargo / 100));
        return calculoRecargo;
    }
    
    @Override 
    public void mostrarDetalle()
    {
        System.out.println("Edición de colección. Recargo aplicado: " + this.porcentajeRecargo + "%");
    }
    
    @Override
    public boolean disponibleParaVenta()
    {
        return true;
    }
}
