import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

/**
 * Write a description of class MainInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainMenu extends EstadoMenu
{
    public MainMenu(Scanner s, Plataforma p) {
        super(s, p);
    }

    private EstadoMenu login() {
        System.out.println("login()");
        System.out.print("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.print("Password: ");
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

    private EstadoMenu sair() {
        return null;
    }

    private EstadoMenu guardarEstado() {
        System.out.print("Nome do ficheiro: ");
        String nome = this.scanner.next();
        this.guardaEstado(nome);
        return this;
    }

    private EstadoMenu registarContribuinte() {
        Contribuinte c = null;
        System.out.print("(1) - Contribuinte individual\n(2) - Empresa\n> ");
        int type = this.scanner.nextInt();
        System.out.print("NIF: ");
        int nif = this.scanner.nextInt();
        System.out.print("Password: ");
        String pw = this.scanner.next();
        System.out.print("Nome: ");
        this.scanner.nextLine();
        String name = this.scanner.nextLine();
        System.out.print("Email: ");
        this.scanner.nextLine();
        String email = this.scanner.nextLine();
        System.out.print("Morada: ");
        this.scanner.nextLine();
        String address = this.scanner.nextLine();
        switch (type) {
            case 1:
                System.out.print("Número de dependentes no agregado familiar: ");
                int numDep = this.scanner.nextInt();
                ArrayList<Integer> nifsDep = new ArrayList<Integer>(numDep);
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
    
    private EstadoMenu carregarEstado(){
        try {
            System.out.print("Nome do ficheiro: ");
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
    
    public EstadoMenu interact() {
        System.out.print("(1) - Fazer login\n(2) - Registar novo contribuinte\n(3) - Carregar estado\n(4) - Guardar estado\n(5) - Sair\n> ");
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