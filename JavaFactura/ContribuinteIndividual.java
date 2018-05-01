public class ContribuinteIndividual extends Contribuinte
{
	private int numDep;

    /**
    * Construtor por omissão do contribuiente individual.
    */
    public ContribuinteIndividual(){
        super();
        this.numDep = 0;
    }

    /**
     * Construtor parametrizado do contribuiente individual.
     */
    public ContribuinteIndividual(int numDep){
        super();
        this.numDep = numDep;
    }

    /**
     * Construtor de cópia do contribuiente individual.
     */
    public ContribuinteIndividual(ContribuinteIndividual familia){
        super(familia);
        this.numDep = familia.getNumDep();
    }

    /**
     * Devolde o valor do número de dependentes do agregado familiar.
     * @return Valor do NumDep.
     */
    public int getNumDep(){
        return this.numDep;
    }

    /**
     * Atualiza número de dependentes do agregado familiar.
     * @param Novo numDep.
     */
    public void setNumDep(int numDep){
        this.numDep = numDep;
    }

    /**
     * Método que devolve a representação em String do contribuiente individual.
     * @return String com as informações do contribuiente individual.
     */
    public String toString(){
        return super.toString() + "\nNúmero de dependentes: " + this.numDep;
    }
}
