package clase7.actividad1;

import java.util.ArrayList;


/**
 *
 * @author Alumno
 */
public class Clase7Actividad1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instanciamos un nuevo libro usando el constructor que creaste.
        // Debemos pasar los parámetros en el orden exacto que definiste.
        clsLibros miLibro1 = new clsLibros("978-1-16", "J.R.R. Tolkien", "Fantasía", "Físico", 10, 25000.0, 63000.0);
        clsLibros miLibro2 = new clsLibros("123-2-19", "Shigatsu", "Terror", "Digital", 3422, 324000.0, 15000.0);
        clsLibros miLibro3 = new clsLibros("753-4-23", "Kekkai sen", "Yokais", "Físico", 10, 2423200.0, 51000.0);
        
        //Creamos la lista de la clase libros llamada catalogo
        ArrayList<clsLibros> catalogo = new ArrayList<>();

        //Agregamos los libros creados
        catalogo.add(miLibro1);
        catalogo.add(miLibro2);
        catalogo.add(miLibro3);
        
        // Mostramos los libros con su autor y stock inicial
        System.out.println("=== TABLA DE STOCK INICIAL ===");
        int totalStock = 0;
        for (clsLibros libro : catalogo){
            System.out.println(libro.getAutor() + " | Stock: " + libro.getCantidad());
            
            totalStock = totalStock + libro.getCantidad();
        }
        System.out.println("TOTAL DE EJEMPLARES: " + totalStock);
        System.out.println("==============================\n");
        
        
        // --- VENTAS ---
        System.out.println("Ejecutando ventas...");
        catalogo.get(0).vender(2); // Vendes 2 de Tolkien
        catalogo.get(1).vender(100); // Vendes 100 de Shigatsu
        System.out.println("\n");   
        

        // --- SEGUNDA IMPRESIÓN (Para ver la actualización) ---
        System.out.println("=== TABLA DE STOCK ACTUALIZADA ===");
        totalStock = 0; // Volvemos a cero el acumulador para recalcular desde cero
        
        for (clsLibros libro : catalogo){
            System.out.println(libro.getAutor() + " | Stock: " + libro.getCantidad());
            totalStock = totalStock + libro.getCantidad(); 
        }
        System.out.println("TOTAL DE EJEMPLARES: " + totalStock);
        System.out.println("==============================");
        
        
      /*  // 2. Probamos el método mostrarInfo inicial
        System.out.println("--- ESTADO INICIAL ---");
        miLibro1.mostrarInfo();

        // 3. Probamos una venta exitosa (alcanza el stock de 10)
        System.out.println("\n--- INTENTANDO VENDER 3 UNIDADES ---");
        miLibro1.vender(3);

        // 4. Verificamos cómo quedó el stock llamando a mostrarInfo de nuevo
        System.out.println("\n--- ESTADO POST-VENTA ---");
        miLibro1.mostrarInfo();

        // 5. Probamos la validación lógica de la venta (intentar vender más de lo que queda)
        System.out.println("\n--- INTENTANDO VENDER 15 UNIDADES ---");
        miLibro1.vender(15);

        // 6. Probamos la seguridad de tu encapsulamiento (los Setters)
        System.out.println("\n--- INTENTANDO CORROMPER EL PRECIO ---");
        miLibro1.setPrecioVenta(-5000.0); // Esto debe ser interceptado por tu validación
*/
        
        
    }
}
   
