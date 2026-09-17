/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase_9_polimorfismo;

/**
 *
 * @author Alumno
 */
public abstract class clsLibro {
    
    private String titulo;
    private String autor;
    private double precioBase;
    private EstadoLibro estado;
    
    public clsLibro (String titulo, String autor, double precioBase)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.precioBase = precioBase;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }
    
    public void mostrarInfo()
    {
        System.out.println("Libro: " + this.titulo);
        System.out.println("Autor: " + this.autor );
    }
    
    public abstract double calcularPrecioFinal();
    
    public abstract void mostrarDetalle();
    
    public abstract boolean disponibleParaVenta();
}
