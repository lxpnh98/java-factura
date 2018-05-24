import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe ContribuinteIndividual - Classe que representa um contribuinte individual.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class ContribuinteIndividual extends Contribuinte implements Serializable
{
    private int numDependentes;
    private ArrayList<Integer> nifsDependentes;
    private HashSet<Integer> idFaturas;
    private Double totalFaturado;

    /**
     * Construtor por omissão do contribuiente individual.
     */
    public ContribuinteIndividual(){
        super();
        this.numDependentes = 0;
        this.nifsDependentes = new ArrayList<Integer>();
        this.idFaturas = new HashSet<Integer>();
        this.totalFaturado = 0.0;
    }

    /**
     * Construtor parametrizado do contribuiente individual.
     */
    public ContribuinteIndividual(int nif, String nome, String email, String morada, String password, int numDep, ArrayList<Integer> nifsDep){
        super(nif, nome, email, morada, password);
        this.numDependentes = numDep;
        this.setNifsDep(nifsDep);
        this.idFaturas = new HashSet<Integer>();
        this.totalFaturado = 0.0;
    }

    /**
     * Construtor de cópia do contribuiente individual.
     */
    public ContribuinteIndividual(ContribuinteIndividual c){
        super(c);
        this.numDependentes = c.getNumDependentes();
        this.nifsDependentes = c.getNifsDep();
        this.idFaturas = new HashSet<Integer>(c.getFaturas());
        this.totalFaturado = c.getTotalFaturado();
    }

    /**
     * Devolde o valor do número de dependentes do agregado familiar.
     * @return int número de dependentes.
     */
    public int getNumDependentes(){
        return this.numDependentes;
    }

    /**
     * Devolde um ArrayList com os nifs dos dependentes.
     * @return ArrayList array dos dependentes.
     */
    public ArrayList<Integer> getNifsDep() {
       ArrayList<Integer> res = new ArrayList<>();
       for(int n : this.nifsDependentes) {
           res.add(n);
       }
       return res;
    }

    /**
     * Atualiza o ArrayList dos nifs dos dependentes.
     * @param ArrayList array dos dependentes.
     */
    public void setNifsDep(ArrayList<Integer> nifsDep) {
        this.nifsDependentes = new ArrayList<>();
        nifsDep.forEach(n -> {this.nifsDependentes.add(n);});
    }

    /**
     * Verifica se um contribuinte pertence ao agregado familiar.
     * @param int nif do contribuinte.
     * @return boolean valor da verificação.
     */
    public boolean pertenceAgredado(int nif) {
        for(Integer i : this.nifsDependentes) {
            if (i.equals(nif)) return true;
        }
        return false;
    }

    /**
     * Atualiza número de dependentes do agregado familiar.
     * @param int novo numDep.
     */
    public void setNumDependentes(int numDep){
        this.numDependentes = numDep;
    }

    /**
     * Devolve o total faturado por um contribuinte.
     * @return Double total faturado.
     */
    public Double getTotalFaturado() {
        return this.totalFaturado;
    }

    /**
     * Adiciona uma fatura à lista de faturas.
     * @param Fatura fatura a adicionar.
     */
    public void adicionarFatura(Fatura f) {
        this.idFaturas.add(f.getId());
        this.totalFaturado += f.getValor();
    }

    /**
     * Devolde uma collection com as faturas.
     * @return Collection.
     */
    public Collection<Integer> getFaturas() {
        return this.idFaturas;
    }

    /**
     * Cria uma cópia do objecto contribuinte individual.
     * @return ContribuinteIndividual
     */
    public ContribuinteIndividual clone() {
        return new ContribuinteIndividual(this);
    }

    /**
     * Verifica a igualdade de dois objectos.
     * @param ci;
     * @return Valor boleano da comparação.
     */
    public boolean equals(Object ci){
        if (ci == this) return true;
        if ((ci == null) || (ci.getClass() != this.getClass())) return false;
        ContribuinteIndividual c = (ContribuinteIndividual) ci;
        return super.equals(c) && this.numDependentes == c.getNumDependentes();
    }

    /**
     * Método que devolve a representação em String do contribuiente individual.
     * @return String com as informações do contribuiente individual.
     */
    public String toString(){
        return super.toString() + "\nNúmero de dependentes: " + this.numDependentes + "\nTotal faturado: " + this.totalFaturado;
    }
}
