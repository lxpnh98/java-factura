import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Classe AdministradorMenu.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class AdministradorMenu extends EstadoMenu {
    private int nif;
    private String password;
    
    /**
     * Construtor parametrizado de AdministradorMenu.
     */
    public AdministradorMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }

    /**
     * Método que lista as empresas por ordem decrescente de total faturado.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu listarEmpresasPorFaturacao(){
        System.out.print("Número de empresas que pretende listar:");
        int x = this.scanner.nextInt();

        try {
            for(Empresa e : this.plataforma.getXEmpresasComMaisFaturas(x, this.nif, this.password)){
                System.out.println("A empresa é: " + e.toString() + "\nCom a dedução fiscal de:" + e.getDeducaoTotal(this.plataforma));
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    /**
     * Método que lista os dez contribuintes que mais gastam no sistema.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu contribuintesQueMaisGastam() {
        try {
            for(ContribuinteIndividual c : this.plataforma.getTop10Contribuintes(this.nif, this.password)){
                System.out.println(c.toString());
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    /**
     * Método que permite ao Adminstrador alterar os códigos das atividades económicas de um contribuinte individual. 
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu alterarCodigosAtividades() {
        System.out.println(" Qual é o NIF do contribuinte que pretende alterar?");
        int contribuinte = this.scanner.nextInt();
        if (this.plataforma.existsIndividuo(contribuinte)) {
            System.out.println("Quantas atividades económicas pretende adicionar: ");
            int numAtividades = this.scanner.nextInt();
            ArrayList<String> atividades = new ArrayList<String>(numAtividades);
            for(int i = 0; i < numAtividades; i++) {
                System.out.println(" (1) - Habitação\n (2) - Educação\n (3) - Saúde\n (4) - DespesasGerais\n>");
                int decisao = this.scanner.nextInt();
                String atividade = "";
                switch (decisao) {
                    case 1:
                        atividade = "Habitacao";
                        break;
                    case 2:
                        atividade = "Educacao";
                        break;
                    case 3:
                        atividade = "Saude";
                        break;
                    case 4:
                        atividade = "DespesasGerais";
                        break;
                }
                if (!atividade.equals("")) {
                        atividades.add(atividade);
                }
            }
         	this.plataforma.alterarCodigosAtividades(contribuinte,atividades);
        } else {
            System.out.println(" Não existe nenhum contribuinte individual correspondente a esse NIF.");
        }
        return this;
    }

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu interact() {
        System.out.println(" (1) - Listar empresas com maior faturação\n (2) - Listar os 10 contribuintes que mais gastam\n (3) - Alterar/Adicionar códigos das Atividades Económicas\n (4) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.listarEmpresasPorFaturacao();
            case 2:
                return this.contribuintesQueMaisGastam();
            case 3:
                return this.alterarCodigosAtividades();
            case 4:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}
