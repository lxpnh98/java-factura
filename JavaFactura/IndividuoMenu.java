import java.util.Scanner;

/**
 * Classe IndividuoMenu.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class IndividuoMenu extends EstadoMenu
{
    private int nif;
    private String password;

    /**
     * Construtor parametrizado de IndividuoMenu.
     */
    public IndividuoMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }
    /**
     * Método que verifica uma fatura.
     * @return EstadoMenu
     */
    public EstadoMenu verificarFatura() {
        System.out.print("Id: ");
        int id = this.scanner.nextInt();
        return new FaturaMenu(this.scanner, this.plataforma, this.nif, this.password, id);
    }

    /**
     * Método que calcula a dedução total de um contribuinte.
     * @return EstadoMenu
     */
    public EstadoMenu calcularDeducaoTotal() {
        try {
            System.out.println(this.plataforma.calcularDeducaoTotal(this.nif, this.password));
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        }
        return this;
    }

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu
     */
    public EstadoMenu interact() {
        System.out.println("(1) - Verificar fatura\n(2) - Calcular valor de dedução total\n(3) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.verificarFatura();
            case 2:
                return this.calcularDeducaoTotal();
            case 3:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}