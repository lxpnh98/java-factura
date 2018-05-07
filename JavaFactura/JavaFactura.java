import java.util.Scanner;
/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFactura {
    private static Scanner scanner = new Scanner(System.in);
    private Plataforma plataforma;
    private Contribuinte contribuinteAtual;

    /**
     * Constructor for objects of class Interface
     */
    public JavaFactura()
    {
        plataforma = Plataforma.carregarPlataforma();
        contribuinteAtual = null;
    }

    public void login() {
        System.out.println("login()");
        System.out.print("NIF: ");
        int nif = scanner.nextInt();
        System.out.print("Password: ");
        String pw = scanner.next();
        this.contribuinteAtual = this.plataforma.login(nif, pw);
        if (this.contribuinteAtual != null)  {
            System.out.println("Login com sucesso.");
        } else {
            System.out.println("NIF ou password incorretos.");
        }
    }

    public void sair() {
        System.out.println("sair()");
    }

    public void registarContribuinte() {
        System.out.print("NIF: ");
        int nif = scanner.nextInt();
        System.out.print("Password: ");
        String pw = scanner.next();
        this.plataforma.adicionarContribuinte(new ContribuinteIndividual(nif, "", "", "", pw, 0));
    }

    public static void main(String[] args) {
        JavaFactura j = new JavaFactura();
        boolean done = false;
        while (done == false) {
            System.out.print("(1) - Fazer login\n(2) - Registar novo contribuinte\n(3) - Sair\n> ");
            int decisao = scanner.nextInt();
            if (decisao == 1) j.login();
            else if (decisao == 2) j.registarContribuinte();
            else if (decisao == 3) {
                j.sair();
                done = true;
            } else ;
        }
    }
}
