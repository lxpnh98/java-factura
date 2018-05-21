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
        System.out.println("NIF do cliente:");
        int nifCliente = this.scanner.nextInt();
        System.out.println("Valor da despesa:");
        double valor = this.scanner.nextDouble();
        System.out.println("Data da Fatura:\n (1) - Data atual\n (2) - Outra");
        int decisao = this.scanner.nextInt();
        Date data = new Date();
        switch(decisao) {
            case 1:
                break;
            case 2:
                System.out.println("Dia:");
                int dia = this.scanner.nextInt();
                System.out.println("Mês:");
                int mes = this.scanner.nextInt();
                System.out.println("Ano:");
                int ano = this.scanner.nextInt();
                Calendar data1 = Calendar.getInstance();
                data1.set(ano, (mes - 1), dia, 0, 0, 0);
                data = data1.getTime();
                break;
        }
        System.out.println("Descrição:\n (1) - Sim\n (2) - Não");
        decisao = this.scanner.nextInt();
        String descricao = "";
        switch(decisao) {
            case 1:
                System.out.println("Escreva aqui:");
                descricao = this.scanner.next();
                break;
            case 2:
                break;
        }
        System.out.println("Qual é a atividade económica: \n (1) - Habitação\n (2) - Educação\n (3) - Saúde\n (4) - DespesasGerais");
        decisao = this.scanner.nextInt();
        String atividade = "";
        switch(decisao) {
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
        Fatura f = new Fatura(this.nif, plataforma.getNomeEmpresa(this.nif), data, nifCliente, descricao, atividade, valor);
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
        System.out.print("Nif do cliente que pretende listar:");
        int nif = this.scanner.nextInt();

        try {
            if(this.plataforma.getFaturasPorValorContribuinte(this.nif, this.password, nif).isEmpty()) {
                System.out.println("Esse contribuinte não está registado no sistema.");
                for(Fatura f : this.plataforma.getFaturasPorValorContribuinte(this.nif, this.password, nif)){
                    System.out.println(f.toString());
                }
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    private EstadoMenu listarFaturasPorDataContribuinte(){
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

        System.out.print("Nif do cliente que pretende listar:");
        int nif = this.scanner.nextInt();
        try {
            if(this.plataforma.getFaturasPorDataContribuinte(this.nif, this.password, begin, end, nif).isEmpty()) {
                System.out.println("Não existem faturas entre as datas introduzidas.");
            }
            for(Fatura f : this.plataforma.getFaturasPorDataContribuinte(this.nif, this.password, begin, end, nif)) {
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
        System.out.println("(1) - Criar fatura\n(2) - Listar faturas por valor\n(3) - Listar faturas por data\n(4) - Calcular total acumulado da empresa\n(5) - Listar faturas por contribuinte e valor\n(6) - Listar faturas por contribuinte e data\n(7) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.criarFatura();
            case 2:
                return this.listarFaturasPorValor();
            case 3:
                return this.listarFaturasPorData();
            case 4:
                return this.criarTotalAcumulado();
            case 5:
                return this.listarFaturasPorValorContribuinte();
            case 6: 
                return this.listarFaturasPorDataContribuinte();
            case 7:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}
