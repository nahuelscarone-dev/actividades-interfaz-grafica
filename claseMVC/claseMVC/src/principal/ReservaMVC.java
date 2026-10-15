package principal;
 
import controlador.ReservaController;
import javax.swing.JFrame;
import modelo.Reserva;
import vista.ReservaView;
 
/*
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  ReservaMVC.java — MAIN (arma las tres piezas y las conecta) ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  Orden de armado (el Controlador necesita a las otras dos):
 *     1) Modelo     → no necesita a nadie
 *     2) Vista      → todavía no conoce al Controlador
 *     3) Controlador→ recibe Modelo y Vista
 *     4) La Vista recibe al Controlador (queda todo conectado)
 */
public class ReservaMVC {
 
    public static void main(String[] args) {
 
        Reserva modelo = new Reserva();                  // ① Modelo (ya trae 5 animales)
        ReservaView vista = new ReservaView();           // ② Vista (un JPanel, sin datos todavía)
 
        JFrame ventana = new JFrame("Reserva — MVC");    //    la ventana que contiene al panel
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(vista);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
 
        ReservaController controlador =
                new ReservaController(modelo, vista);    // ③ Controlador conoce a Modelo y Vista
 
        vista.setControlador(controlador);               // ④ → la Vista conoce al Controlador
 
        controlador.iniciar();                           // ⑤ → el Controlador llena la Vista
                                                         //    con los datos del Modelo (FLUJO 1)
        ventana.setVisible(true);                        // ⑥ recién ahora se muestra la ventana
    }
}