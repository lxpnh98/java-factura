import java.util.Scanner;
import java.util.HashSet;

/**
 * Write a description of class MainInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainMenu extends EstadoMenu
{
    public MainMenu(Scanner s, Plataforma p) {
        super(s, p);
    }

    private EstadoMenu login() {
        System.out.println("login()");
        System.out.print("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.print("Password: ");
        String pw = this.scanner.next();
        try {
            Contribuinte c = this.plataforma.login(nif, pw);
            System.out.println("Login com sucesso.");
            if (c instanceof ContribuinteIndividual) {
                return new IndividuoMenu(this.scanner, this.plataforma, nif, pw);
            } else if (c instanceof Empresa) {
                return new EmpresaMenu(this.scanner, this.plataforma, nif, pw);
            }
        } catch (FailureOnLoginException e) {
            System.out.println("NIF ou password incorretos.");
        }
        return this;
    }

    private EstadoMenu sair() {
        System.out.println("sair()");
        return null;
    }

    private EstadoMenu registarContribuinte() {
        Contribuinte c = null;
        System.out.print("(1) - Contribuinte individual\n(2) - Empresa\n> ");
        int tipo = this.scanner.nextInt();
        System.out.print("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.print("Password: ");
        String pw = this.scanner.next();
        switch (tipo) {
            case 1:
                c = new ContribuinteIndividual(nif, "", "", "", pw, 0);
                break;
            case 2:
                c = new Empresa(nif, "", "", "", pw, new HashSet<String>());
                break;
        }
        this.plataforma.adicionarContribuinte(c);
        return this;
    }
    
    public EstadoMenu interact() {
        System.out.print("(1) - Fazer login\n(2) - Registar novo contribuinte\n(3) - Sair\n> ");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.login();
            case 2:
                return this.registarContribuinte();
            case 3:
                return this.sair();
            }
        return this;
    }
}