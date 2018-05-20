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

    public EstadoMenu verificarFatura() {
        System.out.print("Id: ");
        int id = this.scanner.nextInt();
        return new FaturaMenu(this.scanner, this.plataforma, this.nif, this.password, id);
    }

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
