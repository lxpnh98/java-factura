import java.util.Set;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe Empresa - Classe que representa um contribuinte coletivo.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Empresa extends Contribuinte implements Serializable
{
    private Set<String> atividadesEconomicas;
    public TreeMap<Double,List<Fatura>> faturasPorValor = new TreeMap<>();
    public TreeMap<Date,List<Fatura>> faturasPorData = new TreeMap<>();

    /**
     * Construtor por omissão de Empresa.
     */
    public Empresa(){
        super();
        this.atividadesEconomicas = new HashSet<>();
    }

    /**
     * Construtor parametrizado de Empresa.
     */
    public Empresa(int nif, String nome, String email, String morada, String password, Set<String> atividadesEconomicas){
        super(nif, nome, email, morada, password);
        this.atividadesEconomicas = new HashSet<>(atividadesEconomicas);
    }

    /**
     * Construtor de cópia de Empresa.
     */
    public Empresa(Empresa e){
        super(e);
        this.faturasPorValor = e.getMapFaturasPorValor();
        this.faturasPorData = e.getMapFaturasPorData();
        this.atividadesEconomicas = e.getAtividadesEconomicas();
    }

    /**
     * Devolde o número de faturas associadas a uma empresa.
     * @return Integer número de faturas.
     */
    public Integer getNumFaturas() {
        return ((Long)this.faturasPorValor.values().stream().count()).intValue();
    }

    /**
     * Devolde uma TreeMap.
     * @return TreeMap ordenada por Valor.
     */
    public TreeMap<Double,List<Fatura>> getMapFaturasPorValor(){
        TreeMap<Double,List<Fatura>> v = new TreeMap<>();
        for (Entry<Double,List<Fatura>> e : this.faturasPorValor.entrySet()) {
            v.put(e.getKey(), e.getValue().stream().map(f -> f.clone()).collect(Collectors.toList()));
        }
        return v;
    }

    /**
     * Devolde uma TreeMap.
     * @return TreeMap ordenada por data.
     */
    public TreeMap<Date,List<Fatura>> getMapFaturasPorData(){
        TreeMap<Date,List<Fatura>> d = new TreeMap<>();
        for (Entry<Date,List<Fatura>> e : this.faturasPorData.entrySet()) {
            d.put(e.getKey(), e.getValue().stream().map(f -> f.clone()).collect(Collectors.toList()));
        }
        return d;
    }

    /**
     * Atualiza uma fatura.
     * @param Fatura fatura a atualizar.
     */
    public void atualizarFatura(Fatura f) {
        List<Fatura> l = this.faturasPorValor.get(f.getValor());
        for (Fatura f2 : l) {
            if (f.getId() == f2.getId()) {
                f2.setAtividade(f.getAtividade());
                break;
            }
        }

        List<Fatura> l2 = this.faturasPorData.get(f.getData());
        for (Fatura f2 : l2) {
            if (f.getId() == f2.getId()) {
                f2.setAtividade(f.getAtividade());
                break;
            }
        }
    }

    /**
     * Devolde um Set.
     * @return Set Atividades económicas.
     */
    public Set<String> getAtividadesEconomicas() {
        return new HashSet<String>(this.atividadesEconomicas);
    }

    /**
     * Calcula a dedução.
     * @return Double valor da dedução.
     */
    public Double getDeducaoTotal() {
        double sum = 0.0;
        for (Double d : this.faturasPorValor.keySet()) {
            for (Fatura f : this.faturasPorValor.get(d)) {
                AtividadeEconomica a = new AtividadeEconomica();
                switch (f.getAtividade()) {
                    case "Habitacao":
                        a = new Habitacao();
                        break;
                    case "Saude":
                        a = new Saude();
                        break;
                    case "Educacao":
                        a = new Educacao();
                        break;
                    case "DespesasGerais":
                        a = new DespesasGerais();
                        break;
                }
                sum += a.calcularDeducao(f.getValor(), new HashSet());
            }
        }
        return sum;
    }

    /**
     * Devolde uma String da atividade económica escolhida default.
     * @return String atividade económica default.
     */
    public String getDefaultAtividade() {
        if (this.atividadesEconomicas.size() == 1) {
            return this.atividadesEconomicas.iterator().next();
        } else {
            return "";
        }
    }

    /**
     * Cria uma cópia do objecto Empresa.
     * @return Empresa
     */
    public Empresa clone() {
        return new Empresa(this);
    }

    /**
     * Método que devolve a representação em string da Empresa.
     * @return String com as informações da Empresa.
     */
    public String toString(){
        return super.toString();
    }

    /**
     * Verifica a igualdade de dois objectos
     * @param emp.
     * @return Valor boleano da comparação.
     */
    public boolean equals(Object emp){
        if (emp == this) return true;
        if ((emp == null) || (emp.getClass() != this.getClass())) return false;
        Empresa p = (Empresa) emp;
        return super.equals(p);
    }

    /**
     * Método que adiciona uma fatura a uma TreeMap em função do seu valor.
     * @param Fatura fatura a adicionar.
     * @return TreeMap com a fatura inserida.
     */
    public void inserirPorValor(Fatura f){
        Double key = f.getValor();

        if (this.faturasPorValor.get(key) == null){
            List<Fatura> faturasMesmoValor = new ArrayList<>();
            faturasMesmoValor.add(f.clone());
            this.faturasPorValor.put(key, faturasMesmoValor);
        } else {
            this.faturasPorValor.get(key).add(f.clone());
        }
    }

    /**
     * Método que adiciona uma fatura a uma TreeMap em função da sua Data.
     * @param Fatura fatura a inserir.
     * @return TreeMap com a fatura inserida.
     */
    public void inserirPorData(Fatura f){
        Date key = f.getData();

        if (this.faturasPorData.get(key) == null){
            List<Fatura> faturasMesmaData = new ArrayList<>();
            faturasMesmaData.add(f.clone());
            this.faturasPorData.put(key, faturasMesmaData);
        } else {
            this.faturasPorData.get(key).add(f.clone());
        }
    }

    /**
     * Método que devolve um ArrayList das faturas ordenadas em função do seu valor.
     * @return ArrayList array das faturas.
     */
    public ArrayList<Fatura> faturasPorValor(){
        ArrayList<Fatura> listaPorValor = new ArrayList<>();

        Iterator ittwo = this.faturasPorValor.keySet().iterator();
        while (ittwo.hasNext()) {
            for (Fatura f : this.faturasPorValor.get(ittwo.next())) {
                listaPorValor.add(f.clone());
            }
        }
        return listaPorValor;
    }

    /**
     * Método que devolve um ArrayList das faturas ordenadas em função da sua Data.
     * @return ArrayList array das faturas.
     */
    public ArrayList<Fatura> faturasPorData(){
        ArrayList<Fatura> listaPorData = new ArrayList<>();

        Iterator ittwo = this.faturasPorData.keySet().iterator();
        while (ittwo.hasNext()) {
            for (Fatura f : this.faturasPorData.get(ittwo.next())) {
                listaPorData.add(f.clone());
            }
        }
        return listaPorData;
    }

    /**
     * Método que calcula o total faturado por uma empresa num dado intervalo de tempo.
     * @param Date data de início.
     * @param Date data de fim.
     * @return Double valor do total acumulado. 
     */
    public double totalFaturado(Date begin, Date end){
        double totalAcumulado = 0;

            Iterator ittwo = this.faturasPorData.entrySet().iterator();
            while (ittwo.hasNext()) {
                Map.Entry pairs = (Map.Entry)ittwo.next();
                if (((Date)pairs.getKey()).after(begin)) {
                    if (((Date)pairs.getKey()).before(end)) {
                        for (Fatura f : this.faturasPorData.get(pairs.getKey())) {
                            totalAcumulado += f.getValor();
                        }
                    }
                }
            }
        return totalAcumulado;
    }

    /**
     * Método que calcula o total faturado por uma empresa.
     * @param Date data de início.
     * @param Date data de fim.
     * @return Double valor do total faturado.
     */
    public double totalFaturadoDesdeSempre(){
        double totalAcumulado = 0;

            Iterator ittwo = this.faturasPorData.entrySet().iterator();
            while (ittwo.hasNext()) {
                Map.Entry pairs = (Map.Entry)ittwo.next();
                for (Fatura f : this.faturasPorData.get(pairs.getKey())) {
                    totalAcumulado += f.getValor();
                }
            }
        return totalAcumulado;
    }

    /**
     * Método que devolve um ArrayList das faturas ordenadas em função do nif do cliente e por Valor.
     * @return ArrayList array das faturas.
     */
    public ArrayList<Fatura> faturasPorValorContribuinte(int nif){
        ArrayList<Fatura> faturas = new ArrayList<Fatura>();
        for (Fatura f : this.faturasPorValor()){
            if (f.getNifCliente() == nif){
                faturas.add(f.clone());
            }
        }
        return faturas;
    }

    /**
     * Método que devolve um ArrayList das faturas ordenadas em função da sua Data.
     * @param Date data de início.
     * @param Date data de fim.
     * @return ArrayList array de faturas.
     */
    public ArrayList<Fatura> faturasPorDataContribuinte(Date begin, Date end, int nif){
        ArrayList<Fatura> faturas = new ArrayList<Fatura>();
        for (Fatura f : this.faturasPorData()){
            if ((f.getData().after(begin)) && (f.getData().before(end)) && (f.getNifCliente() == nif)){
                faturas.add(f.clone());
            }
        }
        return faturas;
    }
}
