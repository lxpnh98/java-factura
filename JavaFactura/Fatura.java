

/**
 * classe Fatura - Classe que representa uma fatura.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Fatura
{
    // Variáveis de instância
    private int nifEmitente;
    private String emitente;
    private String data;
    private int nifCliente;
    private String descrição;
    private AtividadeEconomica atividadeEconomica;
    private int valor;

    /**
     * Construtor por omissão de Fatura.
     */
    public Fatura(){
        this.nifEmitente = 0;
        this.emitente = "";
        this.data = "";
        this.nifCliente = 0;
        this.descrição = "";
        this.atividadeEconomica = new AtividadeEconomica();
        this.valor = 0;
    }

    /**
     * Construtor parametrizado de Fatura.
     */
    public Fatura(int nifEmitente, String emitente, String data, int nifCliente, String descrição, AtividadeEconomica atividade, int valor){
        this.nifEmitente = nifEmitente;
        this.emitente = emitente;
        this.data = data;
        this.nifCliente = nifCliente;
        this.descrição = descrição;
        this.atividadeEconomica = atividade.clone();
        this.valor = valor;
    }

    /**
     * Construtor de cópia de Fatura.
     */
    public Fatura(Fatura f){
        this.nifEmitente = f.getNifEmitente();
        this.emitente = f.getEmitente();
        this.data = f.getData();
        this.nifCliente = f.getNifCliente();
        this.descrição = f.getDescrição();
        this.atividadeEconomica = f.getAtividade();
        this.valor = f.getValor();
    }

    /**
     * Devolde o nif do emissor da fatura.
     * @return Nif do emissor.
     */
    public int getNifEmitente(){
        return this.nifEmitente;
    }

    /**
     * Devolde o nome do emissor da fatura.
     * @return Nome do emissor.
     */
    public String getEmitente(){
        return this.emitente;
    }

    /**
     * Devolde a data da fatura.
     * @return Data da fatura.
     */
    public String getData(){
        return this.data;
    }

    /**
     * Devolde o nif do cliente da fatura.
     * @return Nif do cliente.
     */
    public int getNifCliente(){
        return this.nifCliente;
    }

    /**
     * Devolde a descrição da fatura.
     * @return Descrição da fatura.
     */
    public String getDescrição(){
        return this.descrição;
    }

    /**
     * Devolde o valor da fatura.
     * @return Valor da fatura.
     */
    public int getValor(){
        return this.valor;
    }

    /**
     * Devolde o tipo de atividade economica.
     * @return Atividade economica.
     */
    public AtividadeEconomica getAtividade() {
        return this.atividadeEconomica.clone();
    }

    /**
     * Atualiza a Fatura.
     * @param Novo nif de emitente.
     */
    public void setNifemitente(int nifemitente){
        this.nifEmitente = nifemitente;
    }

    /**
     * Atualiza a Fatura.
     * @param Novo emitente.
     */
    public void setEmitente(String emitente){
        this.emitente = emitente;
    }

    /**
     * Atualiza a Fatura.
     * @param Nova data.
     */
    public void setData(String data){
        this.data = data;
    }

    /**
     * Atualiza a Fatura.
     * @param Novo nif de cliente.
     */
    public void setNifcliente(int nifcliente){
        this.nifCliente = nifcliente;
    }

    /**
     * Atualiza a Fatura.
     * @param Nova descrição.
     */
    public void setDescrição(String descrição){
        this.descrição = descrição;
    }

    /**
     * Atualiza a Fatura.
     * @param Novo valor.
     */
    public void setValor(int valor){
        this.valor = valor;
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor boleano da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        Fatura p = (Fatura)o;
        return (this.nifEmitente == p.getNifEmitente()) && (this.emitente == p.getEmitente()) &&
               (this.data == p.getData()) && (this.nifCliente == p.getNifCliente()) && (this.descrição.equals(p.getDescrição())) &&
               (this.valor == p.getValor()) && this.atividadeEconomica.equals(p.getAtividade());
    }

    /**
     * Método que devolve a representação em string da atura.
     * @return String com as informações da Fatura.
     */
    public String toString(){
        return "Fatura: Nif do Emissor - " + this.nifEmitente + " Emissor - " + this.emitente +
               " Data - " + this.data + " Nif do cliente - " + this.nifCliente + " Descrição da fatura - " +
               this.descrição + this.atividadeEconomica + " Valor da fatura - " + this.valor;
    }
}
