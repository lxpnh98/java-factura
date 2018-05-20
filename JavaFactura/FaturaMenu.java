import java.util.Scanner;

/**
 * Write a description of class FaturaMenu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FaturaMenu extends EstadoMenu
{
    private int nif;
    private String password;
    private int id;

    public FaturaMenu(Scanner s, Plataforma p, int nif, String password, int id) {
        super(s, p);
        this.nif = nif;
        this.password = password;
        this.id = id;
    }

    private EstadoMenu imprimirInformacao() {
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
        return this;
    }

    private EstadoMenu alterarAtividade() {
        Fatura f = null;
        try {
            f = this.plataforma.getFatura(id, this.nif, this.password);
            System.out.print("(1) - Habitacao\n(2) - Educacao\n(3) - Saude\n(4) - DespesasGerais\n(5) - Cancelar\n>");
            int decisao = this.scanner.nextInt();
            switch (decisao) {
                case 1:
                    f.setAtividade("Habitacao");
                    break;
                case 2:
                    f.setAtividade("Educacao");
                    break;
                case 3:
                    f.setAtividade("Saude");
                    break;
                case 4:
                    f.setAtividade("DespesasGerais");
                    break;
                case 5:
                    return this;
            }
            this.plataforma.setFatura(id, f, this.nif, this.password);
        } catch (NonExistentBillException e) {
            System.out.println("Fatura nao existente.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissao.");
        } catch (FailureOnLoginException e) {
            System.out.println("Informacao de login incorreta.");
        }
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Imprimir informacao\n(2) - Alterar atividade economica\n(3) - Voltar");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.imprimirInformacao();
            case 2:
                return this.alterarAtividade();
            case 3:
                return new IndividuoMenu(this.scanner, this.plataforma, this.nif, this.password);
        }
        return this;
    }
}
