import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;
import java.io.Serializable;
import java.util.Date;

/**
 * class Empresa - Classe que representa um contribuinte coletivo.
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

    public TreeMap<Double,List<Fatura>> getMapFaturasPorValor(){
        TreeMap<Double,List<Fatura>> v = new TreeMap<>();
        return v;
    }

    public TreeMap<Date,List<Fatura>> getMapFaturasPorData(){
        TreeMap<Date,List<Fatura>> d = new TreeMap<>();
        return d;
    }

    public Set<String> getAtividadesEconomicas() {
        return new HashSet<String>(this.atividadesEconomicas);
    }

    public String getDefaultAtividade() {
        if (this.atividadesEconomicas.size() == 1) {
            return this.atividadesEconomicas.iterator().next();
        } else {
            return "";
        }
    }

    /**
     * Cria uma cópia do objecto Empresa.
     * @return
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
     * @param emp;
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
     * @param Fatura.
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
     * @param Fatura.
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

    public ArrayList<Fatura> faturasPorValor(){
        ArrayList<Fatura> listaPorValor = new ArrayList<>();

        Iterator ittwo = this.faturasPorValor.keySet().iterator();
        while (ittwo.hasNext()) {
            for (Fatura f : this.faturasPorValor.get(ittwo.next())) {
                listaPorValor.add(f.clone());
            }
            //ittwo.remove();
        }
        return listaPorValor;
    }

    public ArrayList<Fatura> faturasPorData(){
        ArrayList<Fatura> listaPorData = new ArrayList<>();

        Iterator ittwo = this.faturasPorData.keySet().iterator();
        while (ittwo.hasNext()) {
            for (Fatura f : this.faturasPorData.get(ittwo.next())) {
                listaPorData.add(f.clone());
            }
            // ittwo.remove();
        }
        return listaPorData;
    }

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

    public ArrayList<Fatura> faturasPorValorContribuinte(){
        int size, nif;
        ArrayList<Fatura> aux = new ArrayList<Fatura>(faturasPorValor());
        ArrayList<Fatura> faturas = new ArrayList<Fatura>();

        while(!(aux.isEmpty())){
            nif = aux.get(0).getNifCliente();
            faturas.add(aux.get(0));
            aux.remove(0);
            size = aux.size();
            for (int i = 0; i < size; i++){
                if (aux.get(i).getNifCliente() == nif){
                    faturas.add(aux.get(i));
                    aux.remove(i);
                    size--;
                    i--;
                }
            }
        }
        return faturas;
    }
}
