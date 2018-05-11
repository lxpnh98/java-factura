import java.util.Scanner;

/**
 * Write a description of interface EstadoInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class EstadoMenu
{
    protected Scanner scanner;
    protected Plataforma plataforma;

    public EstadoMenu(Scanner s, Plataforma p) {
        this.scanner = s;
        this.plataforma = p;
    }

    public abstract EstadoMenu interact();
}
