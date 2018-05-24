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
    private ArrayList<String> codigosAtividades;    
    private HashSet<Integer> idFaturas;
    private Double totalFaturado;

    /**
     * Construtor por omissão do contribuiente individual.
     */
    public ContribuinteIndividual() {
        super();
        this.numDependentes = 0;
        this.nifsDependentes = new ArrayList<Integer>();
        this.codigosAtividades = new ArrayList<String>();
        this.idFaturas = new HashSet<Integer>();
        this.totalFaturado = 0.0;
    }

    /**
     * Construtor parametrizado do contribuiente individual.
     */
    public ContribuinteIndividual(int nif, String nome, String email, String morada, String password, int numDep, ArrayList<Integer> nifsDep, ArrayList<String> codigosAE) {
        super(nif, nome, email, morada, password);
        this.numDependentes = numDep;
        this.setNifsDep(nifsDep);
        this.setCodigos(codigosAE);
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
        this.codigosAtividades = c.getCodigos();
        this.idFaturas = new HashSet<Integer>(c.getFaturas());
        this.totalFaturado = c.getTotalFaturado();
    }

    /**
     * Devolde o valor do número de dependentes do agregado familiar.
     * @return Valor do NumDep.
     */
    public int getNumDependentes(){
        return this.numDependentes;
    }

    public ArrayList<Integer> getNifsDep() {
       ArrayList<Integer> res = new ArrayList<>();
       for(int n : this.nifsDependentes) {
           res.add(n);
       }
       return res;
    }

    public void setNifsDep(ArrayList<Integer> nifsDep) {
        this.nifsDependentes = new ArrayList<>();
        nifsDep.forEach(n -> {this.nifsDependentes.add(n);});
    }

    public boolean pertenceAgredado(int nif) {
        for(Integer i : this.nifsDependentes) {
            if (i.equals(nif)) return true;
        }
        return false;
    }

    /**
     * Atualiza os códigos das atividades económicas do ContribuinteIndividual.
	 * @param ArrayList<String> códigos das atividades.
     */
    public void setCodigos(ArrayList<String> codigosAE) {
    	this.codigosAtividades = new ArrayList<>();
    	codigosAE.forEach(n -> {this.codigosAtividades.add(n);});
    }

    /**
     * Devolve os códigos das atividades económicas do ContribuinteIndividual.
	 * @return ArrayList<String> códigos das atividades.
     */
    public ArrayList<String> getCodigos() {
    	ArrayList<String> res = new ArrayList<>();
    	for (String n : this.codigosAtividades) {
    		res.add(n);
    	}
    	return res;
	}

    /**
     * Atualiza número de dependentes do agregado familiar.
     * @param int novo numDep.
     */
    public void setNumDependentes(int numDep){
        this.numDependentes = numDep;
    }

    public Double getTotalFaturado() {
        return this.totalFaturado;
    }

    /**
     * Adiciona uma fatura à lista de faturas.
     * @param Fatura.
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
        return super.toString() + "\n Número de dependentes: " + this.numDependentes + 
                "\n NIFs dos elementos do Agredado Familiar: " + this.nifsDependentes +
                "\n Atividades económicas que podem ser deduzidas: " + this.codigosAtividades +
                "\n IDs das faturas: " + this.idFaturas + "\n Total faturado: " + this.totalFaturado;
    }
}