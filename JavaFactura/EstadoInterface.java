import java.util.Scanner;

/**
 * Write a description of interface EstadoInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class EstadoInterface
{
    protected Scanner scanner;
    protected Plataforma plataforma;

    public EstadoInterface(Scanner s, Plataforma p) {
        this.scanner = s;
        this.plataforma = p;
    }

    public abstract EstadoInterface interact();
}
