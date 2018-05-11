import java.util.Scanner;

public class IndividuoMenu extends EstadoMenu
{
    private int nif;
    private String password;

    public IndividuoMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }

    public EstadoMenu interact() {
        System.out.println("IndividuoMenu.interact()");
        return new MainMenu(this.scanner, this.plataforma);
    }
}
