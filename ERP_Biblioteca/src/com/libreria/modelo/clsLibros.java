package com.libreria.modelo;

import java.util.ArrayList;

public class clsLibros {
    private ArrayList<clsLibro> listaLibros;

    public clsLibros() {
        this.listaLibros = new ArrayList<>();
        cargarLibrosIniciales(); // Igual que Reserva() carga los 5 animales en claseMVC
    }

    private void cargarLibrosIniciales() {
        clsLibroFisico libroF1 = new clsLibroFisico("El Señor de los Anillos", "Tolkien", 20000, 1500, 5);
        clsLibroDigital libroD1 = new clsLibroDigital("El Señor de los Anillos", "Tolkien", 23424, "8MBs");
        clsLibroUsado libroU1 = new clsLibroUsado("Winnie The Pooh", "Christhopher Robin", 42421, "Deteriorado");
        clsLibroDeColeccion libroC1 = new clsLibroDeColeccion("Harry Potter", "J.K. Rowling", 642, 47212);

        libroU1.setEstado(EstadoLibro.AGOTADO);

        listaLibros.add(libroF1);
        listaLibros.add(libroD1);
        listaLibros.add(libroU1);
        listaLibros.add(libroC1);
    }

    public ArrayList<clsLibro> getListaLibros() {
        return listaLibros;
    }

    public void agregarLibro(clsLibro nuevoLibro) {
        listaLibros.add(nuevoLibro);
    }
}
