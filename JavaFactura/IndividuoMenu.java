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
        try {
            Fatura f = this.plataforma.getFatura(id, this.nif, this.password);
            System.out.println(f.toString());
        } catch (NonExistentBillException e) {
            System.out.println("Fatura nao existente.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissao.");
        } catch (FailureOnLoginException e) {
            System.out.println("Informacao de login incorreta.");
        }
        // imprimir informacao relevante da fatura
        // pedir para confirmar atividade economica
        // se nao confirmar ou se atividade economica for vazia, pedir nome da atividade economica
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Verificar fatura\n(2) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.verificarFatura();
            case 2:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}
