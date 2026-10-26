package com.libreria.controlador;

import com.libreria.modelo.*;
import com.libreria.vistas.ModalAltaLibro;
import com.libreria.vistas.PanelInventario;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class InventarioController {

    private static final String TODOS_TIPOS = "Todos los tipos";
    private static final String TODOS_GENEROS = "Todos los géneros";
    private static final String PLACEHOLDER_BUSQUEDA = "Buscar por título o ISBN...";

    // Textos de ejemplo que trae ModalAltaLibro; no cuentan como datos cargados
    private static final String TEXTO_TITULO = "Ingrese el título...";
    private static final String TEXTO_AUTOR = "Ingrese el autor...";
    private static final String TEXTO_NRO_ARTICULO = "122.1.12";

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
        modal.getBtnGuardarLibroModal().addActionListener(e -> guardarLibro(modal));
        modal.getBtnCancelarModal().addActionListener(e -> modal.dispose());
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);

        // Al cerrarse el modal (bloqueante) se refresca el combo de géneros y la tabla
        cargarFiltros();
        aplicarFiltros();
    }

    private void guardarLibro(ModalAltaLibro modal) {
        List<String> errores = new ArrayList<>();

        String titulo = textoObligatorio(modal.getTxtTitulo(), TEXTO_TITULO, "Título", errores);
        String autor = textoObligatorio(modal.getTxtAutor(), TEXTO_AUTOR, "Autor", errores);
        String isbn = textoObligatorio(modal.getTxtNroArticulo(), TEXTO_NRO_ARTICULO, "Nro. Artículo", errores);

        String genero = null;
        if (modal.getCmbGenero().getSelectedIndex() == 0) {
            errores.add("Seleccione un género.");
        } else {
            genero = (String) modal.getCmbGenero().getSelectedItem();
        }

        double precio = parsearPrecio(modal.getTxtPrecio().getText(), errores);
        int cantidad = parsearCantidad(modal.getTxtCantidad().getText(), errores);

        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(modal, String.join("\n", errores), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // El formulario no tiene selector de tipo ni costo de envío: se da de alta como físico sin envío
        clsLibroFisico libro = new clsLibroFisico(titulo, autor, precio, 0, cantidad);
        libro.setIsbn(isbn);
        libro.setGenero(genero);
        libro.setEstado(cantidad > 0 ? EstadoLibro.DISPONIBLE : EstadoLibro.AGOTADO);
        modelo.agregarLibro(libro);

        limpiarCampos(modal);
        modal.dispose();
    }

    // Devuelve null y registra el error si está vacío o conserva el texto de ejemplo
    private String textoObligatorio(JTextField campo, String textoEjemplo, String nombreCampo, List<String> errores) {
        String texto = campo.getText().trim();
        if (texto.isEmpty() || texto.equals(textoEjemplo)) {
            errores.add("El campo " + nombreCampo + " es obligatorio.");
            return null;
        }
        return texto;
    }

    // Acepta "$ 1.500,00" y "1500.50"; el precio debe ser mayor a 0
    private double parsearPrecio(String texto, List<String> errores) {
        String limpio = texto.replace("$", "").replace(" ", "");
        if (limpio.contains(",")) {
            limpio = limpio.replace(".", "").replace(",", ".");
        }
        try {
            double precio = Double.parseDouble(limpio);
            if (precio > 0) {
                return precio;
            }
        } catch (NumberFormatException e) {
            // Se informa abajo como error de validación
        }
        errores.add("El precio debe ser un número mayor a 0.");
        return 0;
    }

    private int parsearCantidad(String texto, List<String> errores) {
        try {
            int cantidad = Integer.parseInt(texto.trim());
            if (cantidad >= 0) {
                return cantidad;
            }
        } catch (NumberFormatException e) {
            // Se informa abajo como error de validación
        }
        errores.add("La cantidad debe ser un número entero mayor o igual a 0.");
        return 0;
    }

    private void limpiarCampos(ModalAltaLibro modal) {
        modal.getTxtTitulo().setText("");
        modal.getTxtAutor().setText("");
        modal.getTxtNroArticulo().setText("");
        modal.getTxtPrecio().setText("");
        modal.getTxtCantidad().setText("1");
        modal.getCmbGenero().setSelectedIndex(0);
    }
}
