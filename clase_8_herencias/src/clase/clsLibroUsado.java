/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase.pkg07.pkg09.pkg2026;

/**
 *
 * @author Alumno
 */
public class clsLibroUsado extends clsLibro{
    
    private String estado; 

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public clsLibroUsado (String titulo, String autor, double precioBase, String estado)
    {
        super(titulo, autor, precioBase);
        
        this.estado = estado; 
        
    }
    
    @Override
    public double calcularPrecioFinal()
    {
        return getPrecioBase() * 0.50;
    }
    
    @Override 
    public void mostrarDetalle()
    {
        System.out.println("El estado del libro es:" + estado);
    }
    
    @Override
    public boolean disponibleParaVenta()
    {
        return !"Deteriorado".equals(estado);
    }
}
