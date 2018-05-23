import java.util.Scanner;

/**
 * Classe JavaFatura.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class JavaFactura {
    private EstadoMenu estado;

    /**
     * Constructor por omissão de JavaFatura.
     */
    public JavaFactura() {
        this.estado = new MainMenu(new Scanner(System.in), new Plataforma());
    }

    /**
     * Método main.
     */
    public static void main(String[] args) {
        JavaFactura j = new JavaFactura();
        while ((j.estado = j.estado.interact()) != null);
    }
}