/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase_9_polimorfismo;

/**
 *
 * @author Alumno
 */
public class clsLibroUsado extends clsLibro implements Promocionable{
    
    private String condicionFisica;

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    
    public clsLibroUsado (String titulo, String autor, double precioBase, String condicionFisica)
    {
        super(titulo, autor, precioBase);
        
        this.condicionFisica = condicionFisica; 
        
    }
    
    @Override
    public double calcularPrecioFinal()
    {
        return getPrecioBase() * 0.50;
    }
    
    @Override 
    public void mostrarDetalle()
    {
        System.out.println("El estado del libro es:" + condicionFisica);
    }
    
    @Override
    public boolean disponibleParaVenta()
    {
        return !"Deteriorado".equals(condicionFisica);
    }

    @Override
    public double calcularDescuento() {
        
        return getPrecioBase() * 0.45;
    }

    @Override
    public String getMotivoPromocion() {
        
        return "Libro Usado";
        
    }
}
