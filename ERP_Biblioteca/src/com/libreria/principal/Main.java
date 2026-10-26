package com.libreria.principal;

import com.libreria.controlador.InventarioController;
import com.libreria.modelo.clsLibros;
import com.libreria.vistas.VentanaPrincipal;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            clsLibros modelo = new clsLibros();
            VentanaPrincipal vista = new VentanaPrincipal();
            new InventarioController(modelo, vista.getPanelInventario());
            vista.setVisible(true);
        });
    }
}
