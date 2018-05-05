

/**
 * classe Fatura - Classe que representa uma fatura.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Fatura
{
    // Variáveis de instância
    private int nifemitente;
    private String emitente;
    private String data;
    private int nifcliente;
    private String descrição;
    private AtividadeEconomica ae;
    private int valor;

    /**
     * Construtor por omissão de Fatura.
     */
    public Fatura(){
        this.nifemitente = 0;
        this.emitente = "";
        this.data = "";
        this.nifcliente = 0;
        this.descrição = "";
        this.ae = new AtividadeEconomica();
        this.valor = 0;
    }

    /**
     * Construtor parametrizado de Fatura.
     */
    public Fatura(int nifemitente, String emitente, String data, int nifcliente, String descrição, AtividadeEconomica novaae, int valor){
        this.nifemitente = nifemitente;
        this.emitente = emitente;
        this.data = data;
        this.nifcliente = nifcliente;
        this.descrição = descrição;
        this.ae = ae.setAtividade(novaae);
        this.valor = valor;
    }

    /**
     * Construtor de cópia de Fatura.
     */
    public Fatura(Fatura umaFatura){
        this.nifemitente = getNifemitente();
        this.emitente = getEmitente();
        this.data = getData();
        this.nifcliente = getNifcliente();
        this.descrição = getDescrição();
        this.ae = ae.getAtividade();
        this.valor = getValor();
    }

    /**
     * Devolde o nif do emissor da fatura.
     * @return Nif do emissor.
     */
    public int getNifemitente(){
        return this.nifemitente;
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
    public int getNifcliente(){
        return this.nifcliente;
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
     * Atualiza a Fatura.
     * @param Novo nif de emitente.
     */
    public void setNifemitente(int nifemitente){
        this.nifemitente = nifemitente;
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
        this.nifcliente = nifcliente;
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
        Fatura p = (Fatura) o;
        return (this.nifemitente == p.nifemitente) && (this.emitente == p.emitente) && (this.data == p.data) && (this.nifcliente == p.nifcliente) && (this.descrição == p.descrição) && (this.valor == p.valor) && this.ae.equals(o.ae);
    }

    /**
     * Método que devolve a representação em string da atura.
     * @return String com as informações da Fatura.
     */
    public String toString(){
        return "Fatura: Nif do Emissor - " + this.nifemitente + " Emissor - " + this.emitente + " Data - " + this.data + " Nif do cliente - " + this.nifcliente + " Descrição da fatura - " + this.descrição + ae.toString() + " Valor da fatura - " + this.valor;
    }
}
