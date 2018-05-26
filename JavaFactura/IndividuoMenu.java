import java.util.Scanner;
import java.util.List;

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
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu verificarFatura() {
        System.out.print("Id: ");
        int id = this.scanner.nextInt();
        return new FaturaMenu(this.scanner, this.plataforma, this.nif, this.password, id);
    }

    /**
     * Método que lista as faturas associadas a um contribuinte.
     * @return EstadoMenu estado do sistema atualizado.
     */
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

    /**
     * Método que calcula a dedução total de um contribuinte.
     * @return EstadoMenu estado do sistema atualizado.
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
     * Método que imprime a informação de um contribuinte Individual.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu imprimirInformacao() {
        try {
            System.out.println(this.plataforma.imprimirInformacao(this.nif, this.password));
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        }
        return this;
    }
    

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu interact() {
        System.out.println("\n (1) - Verificar fatura\n (2) - Listar faturas\n (3) - Calcular valor de dedução total\n (4) - Imprimir Informação\n (5) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.verificarFatura();
            case 2:
                return this.listarFaturas();
            case 3:    
                return this.calcularDeducaoTotal();
            case 4: 
                return this.imprimirInformacao();
            case 5:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}