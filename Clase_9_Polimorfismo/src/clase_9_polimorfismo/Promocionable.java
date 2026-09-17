/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clase_9_polimorfismo;

/**
 *
 * @author GZ TIENDA
 */
public interface Promocionable {
    
    double calcularDescuento();
    String getMotivoPromocion();
    
    
    default double precioConPromocion(double precioBase)
    {
        return precioBase - calcularDescuento();
    }
}
