import java.util.Scanner;
/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFactura {
    private static Scanner scanner = new Scanner(System.in);
    private static JavaFactura j = new JavaFactura();
    private Plataforma plataforma;
    private EstadoMenu estado;

    /**
     * Constructor for objects of class Interface
     */
    public JavaFactura() {
        this.plataforma = Plataforma.carregarPlataforma();
        this.estado = new MainMenu(scanner, this.plataforma);
        
    }

    public static void main(String[] args) {
        JavaFactura j = new JavaFactura();
        while ((j.estado = j.estado.interact()) != null)
            ;
    }
}
