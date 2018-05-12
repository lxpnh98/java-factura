import java.util.Scanner;

public class EmpresaMenu extends EstadoMenu {
    private int nif;
    private String password;

    public EmpresaMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }

    private EstadoMenu criarFatura() {
        System.out.print("NIF do cliente: ");
        int nifCliente = this.scanner.nextInt();
        System.out.print("Valor da despesa: ");
        double valor = this.scanner.nextDouble();
        Fatura f = new Fatura(this.nif, "", "", nifCliente, "", new AtividadeEconomica(), valor);
        try {
            this.plataforma.adicionarFatura(f, this.nif, this.password);
        } catch (FailureOnLoginException e) {
            System.out.println("Informacao de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissao: " + e);
        } catch (NonExistentClientException e) {
            System.out.println("Cliente nao registado no sistema.");
        }
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Criar fatura\n(2) - Voltar");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.criarFatura();
            case 2:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return new MainMenu(this.scanner, this.plataforma);
    }
}
