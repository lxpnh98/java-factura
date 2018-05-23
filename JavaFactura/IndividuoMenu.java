import java.util.Scanner;
import java.util.List;

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

    public EstadoMenu listarFaturas() {
        try {
            List<Fatura> faturas = this.plataforma.getFaturasIndividuo(this.nif, this.password);
            for(Fatura f : faturas) {
                System.out.println(f.getId() + ": " + f.getData());
            }
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        }
        return this;
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
        System.out.println(" (1) - Verificar fatura\n (2) - Listar faturas\n (3) - Calcular valor de dedução total\n (4) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.verificarFatura();
            case 2:
                return this.listarFaturas();
            case 3:    
                return this.calcularDeducaoTotal();
            case 4:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}