
package clase7.actividad1;

/**
 *
 * @author Alumno
 */
public class clsLibros {
    
    private String codLibro; //ISBN
    private String autor;
    private String genero;
    private String tipo;
    private int cantidad;
    private double precioVenta;
    private double precioCompra;
    
    public clsLibros(String codLibro, String autor, String genero, String tipo, int cantidad, double precioVenta, double precioCompra){
        this.codLibro = codLibro;
        this.autor = autor;
        this.genero = genero;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra; 
        
    }
    
    //Getters

    public String getCodLibro() {
        return codLibro;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    //Setters
    public void setCodLibro(String codLibro) {
        this.codLibro = codLibro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        if(cantidad>=0)
             {this.cantidad = cantidad;
        }else{
            System.out.println("Error: el stock no puede ser negativo"); 
        }
    }

    public void setPrecioVenta(double precioVenta) {
        if(precioVenta>=0){
            this.precioVenta = precioVenta;
        }else{
            System.out.println("Error: el precio de venta no puede ser negativo");
        }
    }

    public void setPrecioCompra(double precioCompra) {
        if(precioCompra>=0){
            this.precioCompra = precioCompra;  
        }else{
            System.out.println("Error:El precio de compra no puede ser negativo");
        }
    }
    // Método para mostrar la ficha del libro
    public void mostrarInfo() {
        System.out.println("--- Ficha del Libro ---");
        System.out.println("ISBN: " + this.codLibro);
        System.out.println("Autor: " + this.autor);
        System.out.println("Género: " + this.genero);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Stock actual: " + this.cantidad + " unidades");
        System.out.println("Precio de Venta: $" + this.precioVenta);
        System.out.println("-----------------------");
    }
    
    // Método para vender libros y actualizar stock
    public void vender(int cantidadVendida) {
        // Verificamos que haya suficiente stock
        if (cantidadVendida <= this.cantidad) {
            this.cantidad = this.cantidad - cantidadVendida; // Restamos el stock
            System.out.println("Venta exitosa. Se vendieron " + cantidadVendida + " unidades.");
        } else {
            System.out.println("Error: Stock insuficiente. Solo quedan " + this.cantidad + " unidades disponibles.");
        }
    }
}
    