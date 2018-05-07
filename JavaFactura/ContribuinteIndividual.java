/**
 * class ContribuinteIndividual - Classe que representa um contribuinte individual.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class ContribuinteIndividual extends Contribuinte
{
    private int numDependentes;

    /**
     * Construtor por omissão do contribuiente individual.
     */
    public ContribuinteIndividual(){
        super();
        this.numDependentes = 0;
    }

    /**
     * Construtor parametrizado do contribuiente individual.
     */
    public ContribuinteIndividual(int nif, String nome, String email, String morada, String password, int numDep){
        super(nif, nome, email, morada, password);
        this.numDependentes = numDependentes;
    }

    /**
     * Construtor de cópia do contribuiente individual.
     */
    public ContribuinteIndividual(ContribuinteIndividual c){
        super(c);
        this.numDependentes = c.getNumDependentes();
    }

    /**
     * Devolde o valor do número de dependentes do agregado familiar.
     * @return Valor do NumDep.
     */
    public int getNumDependentes(){
        return this.numDependentes;
    }

    /**
     * Atualiza número de dependentes do agregado familiar.
     * @param Novo numDep.
     */
    public void setNumDependentes(int numDep){
        this.numDependentes = numDep;
    }

    /**
     * Cria uma cópia do objecto contribuinte individual.
     * @return 
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
        return super.toString() + "\nNúmero de dependentes: " + this.numDependentes;
    }
}