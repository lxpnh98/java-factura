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
            if (f.getAtividade().equals("")) {
                System.out.println("(1) - Definir atividade economica\n(2) - Ignorar");
                int decisao = this.scanner.nextInt();
                switch (decisao) {
                    case 1:
                        System.out.print("(1) - Habitacao\n(2) - Educacao\n(3) - Saude\n(4) - DespesasGerais\n");
                        decisao = this.scanner.nextInt();
                        switch (decisao) {
                            case 1:
                                f.setAtividade("Habitacao"); // TODO: falta registar todas as alteracoes
                                break;
                            case 2:
                                f.setAtividade("Educacao"); // TODO: falta registar todas as alteracoes
                                break;
                            case 3:
                                f.setAtividade("Saude"); // TODO: falta registar todas as alteracoes
                                break;
                            case 4:
                                f.setAtividade("DespesasGerais"); // TODO: falta registar todas as alteracoes
                                break;
                        }
                        this.plataforma.setFatura(id, f, this.nif, this.password);
                        break;
                    case 2:
                        break;
                }
            } else {
                System.out.println("(1) - Confirmar atividade economica\n(2) - Alterar atividade economica\n(3) - Ignorar");
                int decisao = this.scanner.nextInt();
                switch (decisao) {
                    case 1:
                        break;
                    case 2:
                        System.out.print("(1) - Habitacao\n(2) - Educacao\n(3) - Saude\n(4) - DespesasGerais\n");
                        decisao = this.scanner.nextInt();
                        switch (decisao) {
                            case 1:
                                f.setAtividade("Habitacao"); // TODO: falta registar todas as alteracoes
                                break;
                            case 2:
                                f.setAtividade("Educacao"); // TODO: falta registar todas as alteracoes
                                break;
                            case 3:
                                f.setAtividade("Saude"); // TODO: falta registar todas as alteracoes
                                break;
                            case 4:
                                f.setAtividade("DespesasGerais"); // TODO: falta registar todas as alteracoes
                                break;
                        }
                        this.plataforma.setFatura(id, f, this.nif, this.password);
                }
            }
        } catch (NonExistentBillException e) {
            System.out.println("Fatura nao existente.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissao.");
        } catch (FailureOnLoginException e) {
            System.out.println("Informacao de login incorreta.");
        }
        return this;
    }

    public EstadoMenu calcularDeducaoTotal() {
        try {
            System.out.println(this.plataforma.calcularDeducaoTotal(this.nif, this.password));
        } catch (FailureOnLoginException e) {
            System.out.println("Informacao de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissao.");
        }
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Verificar fatura\n(2) - Calcular valor de deducao total\n(3) - Logout");
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
