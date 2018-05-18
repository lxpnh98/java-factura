import java.util.Scanner;

/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFactura {
    private EstadoMenu estado;

    /**
     * Constructor for objects of class Interface
     */
    public JavaFactura() {
        this.estado = new MainMenu(new Scanner(System.in), new Plataforma());
    }

    public static void main(String[] args) {
        JavaFactura j = new JavaFactura();
        while ((j.estado = j.estado.interact()) != null);
    }
}