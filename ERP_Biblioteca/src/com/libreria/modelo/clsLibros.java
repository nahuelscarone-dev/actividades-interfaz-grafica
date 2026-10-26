package com.libreria.modelo;

import java.util.ArrayList;

public class clsLibros {
    public static final String STOCK_BAJO = "Bajo";
    public static final String STOCK_AGOTADO = "Agotado";

    private static final int UMBRAL_STOCK_BAJO = 5;

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

        libroF1.setIsbn("9780618640157");
        libroF1.setGenero("Fantasía");
        libroD1.setIsbn("9780618640158");
        libroD1.setGenero("Fantasía");
        libroU1.setIsbn("9780721408392");
        libroU1.setGenero("Infantil");
        libroC1.setIsbn("9780747532743");
        libroC1.setGenero("Fantasía");

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

    // Los criterios en null o vacíos significan "sin filtro"
    public ArrayList<clsLibro> filtrar(String texto, String tipo, String genero, String stock) {
        ArrayList<clsLibro> resultado = new ArrayList<>();

        for (clsLibro libro : listaLibros) {
            if (coincideTexto(libro, texto)
                    && coincideTipo(libro, tipo)
                    && coincideGenero(libro, genero)
                    && coincideStock(libro, stock)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    private boolean coincideTexto(clsLibro libro, String texto) {
        if (texto == null || texto.isBlank()) {
            return true;
        }
        String busqueda = texto.trim().toLowerCase();
        return libro.getTitulo().toLowerCase().contains(busqueda)
                || (libro.getIsbn() != null && libro.getIsbn().contains(busqueda));
    }

    private boolean coincideTipo(clsLibro libro, String tipo) {
        return tipo == null || tipo.isBlank() || libro.getTipo().equals(tipo);
    }

    private boolean coincideGenero(clsLibro libro, String genero) {
        return genero == null || genero.isBlank() || genero.equals(libro.getGenero());
    }

    private boolean coincideStock(clsLibro libro, String stock) {
        if (stock == null || stock.isBlank()) {
            return true;
        }
        if (stock.equals(STOCK_AGOTADO)) {
            return libro.getEstado() == EstadoLibro.AGOTADO;
        }
        if (stock.equals(STOCK_BAJO)) {
            return libro instanceof clsLibroFisico fisico
                    && fisico.getStock() > 0
                    && fisico.getStock() <= UMBRAL_STOCK_BAJO;
        }
        return true;
    }
}
