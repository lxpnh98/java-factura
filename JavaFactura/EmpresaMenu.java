import java.util.Scanner;

public class EmpresaMenu extends EstadoMenu {
    private int nif;
    private String password;

    public EmpresaMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }

    public EstadoMenu interact() {
        System.out.println("EmpresaInterface.interact()");
        return new MainMenu(this.scanner, this.plataforma);
    }
}
