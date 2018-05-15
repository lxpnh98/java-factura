import java.util.Scanner;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

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

        /* Codigo para confirmacao do utilizador
        System.out.println("Tipo de despesa (opcoes):");
        Iterator<String> it = this.plataforma.getAtividadesEconomicas().keySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
        System.out.print("> ");
        String atividade = this.scanner.nextLine();
        */

        Fatura f = new Fatura(this.nif, "", new Date(), nifCliente, "", "", valor);
        try {
            this.plataforma.adicionarFatura(f, this.nif, this.password);
        } catch (FailureOnLoginException e) {
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e) {
            System.out.println("Sem permissão.");
        } catch (NonExistentClientException e) {
            System.out.println("Cliente não registado no sistema.");
        }
        return this;
    }

    private EstadoMenu criarTotalAcumulado(){
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();

        System.out.print("Dia da data inicial:");
        int dia1 = this.scanner.nextInt();
        System.out.print("Mês da data inicial:");
        int mes1 = this.scanner.nextInt();
        System.out.print("Ano da data inicial:");
        int ano1 = this.scanner.nextInt();

        data1.set(ano1, (mes1 - 1), dia1, 0, 0, 0);
        Date begin = data1.getTime();
        System.out.print("Data inicial: " + begin + "\n\n");

        System.out.print("Dia da data final:");
        int dia2 = this.scanner.nextInt();
        System.out.print("Mês da data final:");
        int mes2 = this.scanner.nextInt();
        System.out.print("Ano da data final:");
        int ano2 = this.scanner.nextInt();

        data2.set(ano2, (mes2 - 1), dia2, 0, 0, 0);
        Date end = data2.getTime();
        System.out.print("Data final: " + end + "\n\n");

        try {
            System.out.println("Total acumulado: " + this.plataforma.getTotalFaturado(this.nif, this.password, begin, end));
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    public EstadoMenu listarFaturasPorValor(){
        try {
            for(Fatura f : this.plataforma.getFaturasPorValor(this.nif, this.password)){
                System.out.println(f.toString());
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    public EstadoMenu listarFaturasPorData(){
        try {
            for(Fatura f : this.plataforma.getFaturasPorData(this.nif, this.password)){
                System.out.println(f.toString());
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    public EstadoMenu listarFaturasPorValorContribuinte(){
        try {
            for(Fatura f : this.plataforma.getFaturasPorValorContribuinte(this.nif, this.password)){
                System.out.println(f.toString());
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Criar fatura\n(2) - Logout\n(3) - Listar faturas por valor\n(4) - Listar faturas por data\n(5) - Calcular total acumulado da empresa\n(6) - Listar faturas por contribuinte e valor");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.criarFatura();
            case 2:
                return new MainMenu(this.scanner, this.plataforma);
            case 3:
                return this.listarFaturasPorValor();
            case 4:
                return this.listarFaturasPorData();
            case 5:
                return this.criarTotalAcumulado();
            case 6:
                return this.listarFaturasPorValorContribuinte();
            }
        return this;
    }
}
