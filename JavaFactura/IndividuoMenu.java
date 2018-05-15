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
                        System.out.print("> ");
                        f.setAtividade(this.scanner.next()); // TODO: falta registar todas as alteracoes
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
                        System.out.print("> ");
                        f.setAtividade(this.scanner.next());
                        this.plataforma.setFatura(id, f, this.nif, this.password);
                        break;
                }
            }
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
