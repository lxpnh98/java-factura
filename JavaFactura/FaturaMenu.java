import java.util.Scanner;

/**
 * Classe FaturaMenu.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class FaturaMenu extends EstadoMenu
{
    private int nif;
    private String password;
    private int id;

    /**
     * Construtor parametrizado de FaturaMenu.
     */
    public FaturaMenu(Scanner s, Plataforma p, int nif, String password, int id) {
        super(s, p);
        this.nif = nif;
        this.password = password;
        this.id = id;
    }

    /**
     * Método que imprime a informação de uma fatura.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu imprimirInformacao() {
        try {
            Fatura f = this.plataforma.getFatura(id, this.nif, this.password);
            System.out.println(f.toString());
        } catch (NonExistentBillException e) {
            System.out.println("Fatura não existente.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        }
        return this;
    }

    /**
     * Método que altera a atividade económica de uma fatura.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu alterarAtividade() {
        Fatura f = null;
        try {
            f = this.plataforma.getFatura(id, this.nif, this.password);
            System.out.println("\n (1) - Habitação\n (2) - Educação\n (3) - Saúde\n (4) - DespesasGerais\n (5) - Cancelar\n>");
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
            System.out.println("Fatura não existente.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        }
        return this;
    }

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu interact() {
        System.out.println("\n (1) - Imprimir informação\n (2) - Alterar atividade económica\n (3) - Voltar");
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