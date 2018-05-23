import java.util.Scanner;
import java.io.*;

/**
 * Classe abstrata EstadoMenu.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public abstract class EstadoMenu
{
    protected Scanner scanner;
    protected Plataforma plataforma;

    /**
     * Construtor parametrizado de EstadoMenu.
     */
    public EstadoMenu(Scanner s, Plataforma p) {
        this.scanner = s;
        this.plataforma = p;
    }

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu
     */
    public abstract EstadoMenu interact();
    
    /**
     * Método que guarda o estado do sistema.
     * @param String
     */
    public void guardaEstado(String s) {
        try {
            this.plataforma.guardaEstado(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
