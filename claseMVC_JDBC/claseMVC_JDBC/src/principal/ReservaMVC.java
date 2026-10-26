package principal;
 
import controlador.ReservaController;
import dao.AnimalDAO;
import dao.AnimalDAOArchivo;
import dao.AnimalDAOJDBC;
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
 *     3) DAO        → no necesita a nadie (archivo o base de datos: se elige acá)
 *     4) Controlador→ recibe Modelo, Vista y DAO
 *     5) La Vista recibe al Controlador (queda todo conectado)
 */
public class ReservaMVC {
 
    public static void main(String[] args) {

        Reserva modelo = new Reserva();                  // ① Modelo (arranca vacío)
        // ② DAO: acá, y SOLO acá, se elige DÓNDE se guardan los datos.
        //    El tipo es la interfaz AnimalDAO; el "new" decide cuál implementación.
        AnimalDAO dao = new AnimalDAOArchivo();          //    → archivo animales.txt   (clase 17)
        // AnimalDAO dao = new AnimalDAOJDBC();          //    → base de datos MySQL    (clase 18)
        ReservaView vista = new ReservaView();           // ③ Vista (un JPanel, sin datos todavía)

        JFrame ventana = new JFrame("Reserva — MVC");    //    la ventana que contiene al panel
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(vista);
        ventana.pack();
        ventana.setLocationRelativeTo(null);

        ReservaController controlador =
                new ReservaController(modelo, vista, dao);   // ④ Controlador conoce a Modelo, Vista y DAO

        vista.setControlador(controlador);               // ⑤ → la Vista conoce al Controlador

        controlador.iniciar();                           // ⑥ → el Controlador carga los datos (vía DAO)
                                                         //    y llena la Vista (FLUJO 1)
        ventana.setVisible(true);                        // ⑦ recién ahora se muestra la ventana
    }
}
