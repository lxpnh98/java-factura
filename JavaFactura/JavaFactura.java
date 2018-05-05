import java.util.Scanner;
/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFactura {
    // instance variables - replace the example below with your own
    private Contribuinte contribuinte_atual;

    /**
     * Constructor for objects of class Interface
     */
    public JavaFactura()
    {
        contribuinte_atual = null;
    }

    public void login() {
        System.out.println("login()");
    }

    public void registarContribuinte() {
        System.out.println("registarContribuinte()");
    }

    public static void main(String[] args) {
        JavaFactura j = new JavaFactura();
        Scanner s = new Scanner(System.in);
        System.out.print("(1) - Fazer login\n(2) - Registar novo contribuinte\n(3) - Sair\n> ");
        int decisao = s.nextInt();
        if (decisao == 1) j.login();
        if (decisao == 2) j.registarContribuinte();
        else ;
    }
}
