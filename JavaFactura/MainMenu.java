import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

/**
 * Classe MainMenu.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class MainMenu extends EstadoMenu
{
    /**
     * Construtor parametrizado de MainMenu.
     */
    public MainMenu(Scanner s, Plataforma p) {
        super(s, p);
    }

    /**
     * Método que permite fazer login.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu login() {
        System.out.println("login()");
        System.out.println("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.println("Password: ");
        String pw = this.scanner.next();
        try {
            Contribuinte c = this.plataforma.login(nif, pw);
            System.out.println("Login com sucesso.");
            if (c instanceof ContribuinteIndividual) {
                return new IndividuoMenu(this.scanner, this.plataforma, nif, pw);
            } else if (c instanceof Empresa) {
                return new EmpresaMenu(this.scanner, this.plataforma, nif, pw);
            } else if (c instanceof Administrador) {
                return new AdministradorMenu(this.scanner, this.plataforma, nif, pw);
            }
        } catch (FailureOnLoginException e) {
            System.out.println("NIF ou password incorretos.");
        }
        return this;
    }

    /**
     * Método que permite sair do sistema.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu sair() {
        System.out.println(" Deseja mesmo sair ?\n  (1) - Sim\n  (2) - Não");
        int decisao = this.scanner.nextInt();
        switch(decisao) {
            case 1:
                return null;
            case 2:
                this.interact();
        }
        return this;
    }

    /**
     * Método que guarda o estado do sistema.
     * @return EstadoMenu
     */
    private EstadoMenu guardarEstado() {
        System.out.println("Nome do ficheiro: ");
        String nome = this.scanner.next();
        this.guardaEstado(nome);
        return this;
    }

    /**
     * Método que regista um novo contribuinte no sistema.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu registarContribuinte() {
        Contribuinte c = null;
        System.out.println("(1) - Contribuinte individual\n(2) - Empresa\n> ");
        int type = this.scanner.nextInt();
        System.out.println("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.println("Password: ");
        String pw = this.scanner.next();
        System.out.println("Nome: ");
        this.scanner.nextLine();
        String name = this.scanner.nextLine();
        System.out.println("Email: ");
        String email = this.scanner.nextLine();
        System.out.println("Morada: ");
        String address = this.scanner.nextLine();
        switch (type) {
            case 1:
                System.out.println("Número de dependentes no agregado familiar: ");
                int numDep = this.scanner.nextInt();
                ArrayList<Integer> nifsDep = new ArrayList<Integer>();
                for(int i = 0; i < numDep; i++) {
                    System.out.print("Nif de um dependente do agregado familiar: ");
                    int nifDep = this.scanner.nextInt();
                    nifsDep.add(nifDep);
                }
                c = new ContribuinteIndividual(nif, name, email, address, pw, numDep, nifsDep);
                break;
            case 2:
                System.out.println("Número de atividades económicas: ");
                int numAtividades = this.scanner.nextInt();
                HashSet<String> atividades = new HashSet<String>(numAtividades);
                for(int i = 0; i < numAtividades; i++) {
                    System.out.println("(1) - Habitação\n(2) - Educação\n(3) - Saúde\n(4) - DespesasGerais\n>");
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
                c = new Empresa(nif, name, email, address, pw, atividades);
                break;
        }
        this.plataforma.adicionarContribuinte(c);
        return this;
    }

    /**
     * Método que carrega o estado do sistema a partir de um ficheiro.
     * @return EstadoMenu estado do sistema atualizado.
     */
    private EstadoMenu carregarEstado(){
        try {
            System.out.println("Nome do ficheiro: ");
            String nome = this.scanner.next();
            Plataforma p = Plataforma.carregarPlataforma(nome);
            return new MainMenu(this.scanner, p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Não conseguiu carregar");
        return this;
    }

    /**
     * Método que permite ao utilizador interagir com o programa.
     * @return EstadoMenu estado do sistema atualizado.
     */
    public EstadoMenu interact() {
        System.out.println("\n (1) - Fazer login\n (2) - Registar novo contribuinte\n (3) - Carregar estado\n (4) - Guardar estado\n (5) - Sair\n> ");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.login();
            case 2:
                return this.registarContribuinte();
            case 3:
                return this.carregarEstado();
            case 4:
                return this.guardarEstado();
            case 5:
                return this.sair();
            }
        return this;
    }
}