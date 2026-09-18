/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase.pkg07.pkg09.pkg2026;

/**
 *
 * @author Alumno
 */
public class clsLibroFisico extends clsLibro {
    
    private double costoEnvio;
    private int stock;

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public clsLibroFisico(String titulo, String autor, double precioBase, double costoEnvio, int stock)
    {
        super(titulo,autor,precioBase);
        
        this.costoEnvio = costoEnvio;
        this.stock = stock;
    }
      
    @Override
    public double calcularPrecioFinal()
    {
        return getPrecioBase() + costoEnvio;
    }
    
    @Override
    public void mostrarDetalle()
    {
        System.out.println("Stock disponible: " + stock);
    }
    
    @Override
    public boolean disponibleParaVenta()
    {
        return stock > 0;
    }
}
    
  