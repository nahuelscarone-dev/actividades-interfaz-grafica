package com.libreria.controlador;

import com.libreria.modelo.*;
import com.libreria.vistas.ModalAltaLibro;
import com.libreria.vistas.PanelInventario;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class InventarioController {

    private static final String TODOS_TIPOS = "Todos los tipos";
    private static final String TODOS_GENEROS = "Todos los géneros";
    private static final String PLACEHOLDER_BUSQUEDA = "Buscar por título o ISBN...";

    private final clsLibros modelo;
    private final PanelInventario vista;

    public InventarioController(clsLibros modelo, PanelInventario vista) {
        this.modelo = modelo;
        this.vista = vista;

        cargarFiltros();
        registrarEventos();
        aplicarFiltros();
    }

    private void cargarFiltros() {
        javax.swing.JComboBox<String> cmbTipo = vista.getCmbTipoFiltro();
        cmbTipo.removeAllItems();
        cmbTipo.addItem(TODOS_TIPOS);
        cmbTipo.addItem("Físico");
        cmbTipo.addItem("Digital");
        cmbTipo.addItem("Usado");
        cmbTipo.addItem("De colección");

        // Los géneros salen de los libros cargados, sin repetir
        javax.swing.JComboBox<String> cmbGenero = vista.getCmbGeneroFiltro();
        cmbGenero.removeAllItems();
        cmbGenero.addItem(TODOS_GENEROS);
        TreeSet<String> generos = new TreeSet<>();
        for (clsLibro libro : modelo.getListaLibros()) {
            if (libro.getGenero() != null) {
                generos.add(libro.getGenero());
            }
        }
        for (String genero : generos) {
            cmbGenero.addItem(genero);
        }
    }

    private void registrarEventos() {
        vista.getTxtBuscarInventario().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltros();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltros();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltros();
            }
        });

        vista.getCmbTipoFiltro().addActionListener(e -> aplicarFiltros());
        vista.getCmbGeneroFiltro().addActionListener(e -> aplicarFiltros());

        vista.getRbtnTodos().addActionListener(e -> aplicarFiltros());
        vista.getRbtnBajo().addActionListener(e -> aplicarFiltros());
        vista.getRbtnAgotado().addActionListener(e -> aplicarFiltros());

        vista.getBtnAbrirModalLibro().addActionListener(e -> abrirModalAlta());
    }

    private void aplicarFiltros() {
        String texto = vista.getTxtBuscarInventario().getText();
        if (PLACEHOLDER_BUSQUEDA.equals(texto)) {
            texto = null;
        }

        String tipo = filtroSeleccionado(vista.getCmbTipoFiltro(), TODOS_TIPOS);
        String genero = filtroSeleccionado(vista.getCmbGeneroFiltro(), TODOS_GENEROS);

        String stock = null;
        if (vista.getRbtnBajo().isSelected()) {
            stock = clsLibros.STOCK_BAJO;
        } else if (vista.getRbtnAgotado().isSelected()) {
            stock = clsLibros.STOCK_AGOTADO;
        }

        cargarTabla(modelo.filtrar(texto, tipo, genero, stock));
    }

    // Devuelve null cuando el combo está en la opción "Todos"
    private String filtroSeleccionado(javax.swing.JComboBox<String> combo, String opcionTodos) {
        String seleccion = (String) combo.getSelectedItem();
        return opcionTodos.equals(seleccion) ? null : seleccion;
    }

    private void cargarTabla(List<clsLibro> libros) {
        DefaultTableModel tabla = new DefaultTableModel(
                new Object[] { "NOMBRE", "NRO. ARTÍCULO", "TIPO", "GÉNERO", "AUTOR", "CANTIDAD STOCK", "PRECIO UNITARIO", "ACCIONES" },
                0);

        for (clsLibro libro : libros) {
            tabla.addRow(new Object[] {
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getTipo(),
                libro.getGenero(),
                libro.getAutor(),
                stockDe(libro),
                String.format("$%.2f", libro.calcularPrecioFinal()),
                "Editar | Baja"
            });
        }

        JTable tblInventario = vista.getTblInventario();
        tblInventario.setModel(tabla);
    }

    private String stockDe(clsLibro libro) {
        if (libro instanceof clsLibroFisico fisico) {
            return String.valueOf(fisico.getStock());
        }
        return "N/A";
    }

    private void abrirModalAlta() {
        Window ventana = SwingUtilities.getWindowAncestor(vista);
        Frame padre = ventana instanceof Frame frame ? frame : null;

        ModalAltaLibro modal = new ModalAltaLibro(padre, true);
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);

        // Al cerrarse el modal (bloqueante) se refresca la tabla con los filtros actuales
        aplicarFiltros();
    }
}
