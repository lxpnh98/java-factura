import java.util.Scanner;

/**
 * Write a description of class MainInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainInterface extends EstadoInterface
{
    public MainInterface(Scanner s, Plataforma p) {
        super(s, p);
    }

    public EstadoInterface login() {
        System.out.println("login()");
        System.out.print("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.print("Password: ");
        String pw = this.scanner.next();
        Contribuinte c = this.plataforma.login(nif, pw);
        if (c != null)  {
            System.out.println("Login com sucesso.");
        } else {
            System.out.println("NIF ou password incorretos.");
        }
        return new IndividuoInterface(this.scanner, this.plataforma);
    }

    public EstadoInterface sair() {
        System.out.println("sair()");
        return null;
    }

    public EstadoInterface registarContribuinte() {
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
                c = new Empresa(nif, "", "", "", pw);
                break;
        }
        this.plataforma.adicionarContribuinte(c);
        return this;
    }
    
    public EstadoInterface interact() {
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
