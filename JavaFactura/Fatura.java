import java.util.Set;
import java.util.Date;
import java.util.*;

/**
 * classe Fatura - Classe que representa uma fatura.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Fatura
{
    private static int idCounter = 0;
    private int id;
    private int nifEmitente;
    private String emitente;
    private Date data;
    private int nifCliente;
    private String descrição;
    private String atividadeEconomica;
    private double valor;

    /**
     * Construtor por omissão de Fatura.
     */
    public Fatura(){
        this.id = idCounter++;
        this.nifEmitente = 0;
        this.emitente = "";
        this.data = new Date();
        this.nifCliente = 0;
        this.descrição = "";
        this.atividadeEconomica = new AtividadeEconomica().getNome();
        this.valor = 0;
    }

    /**
     * Construtor parametrizado de Fatura.
     */
    public Fatura(int nifEmitente, String emitente, Date data, int nifCliente, String descrição, String atividade, double valor){
        this.id = idCounter++;
        this.nifEmitente = nifEmitente;
        this.emitente = emitente;
        this.data.setDate(dateToInt(data));
        this.nifCliente = nifCliente;
        this.descrição = descrição;
        this.atividadeEconomica = atividade;
        this.valor = valor;
    }

    /**
     * Construtor de cópia de Fatura.
     */
    public Fatura(Fatura f){
        this.id = f.getId();
        this.nifEmitente = f.getNifEmitente();
        this.emitente = f.getEmitente();
        this.data = f.getData();
        this.nifCliente = f.getNifCliente();
        this.descrição = f.getDescrição();
        this.atividadeEconomica = f.getAtividade();
        this.valor = f.getValor();
    }

    /**
     * Devolde o id da fatura.
     * @return Id da fatura.
     */
    public int getId() {
        return this.id;
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
     * Devolde a data da fatura.
     * @return Data da fatura.
     */
    public Date getData(){
        return this.data;
    }

    /**
     * Devolde o valor da fatura.
     * @return Valor da fatura.
     */
    public double getValor(){
        return this.valor;
    }

    /**
     * Devolde o tipo de atividade economica.
     * @return Atividade economica.
     */
    public String getAtividade() {
        return this.atividadeEconomica;
    }

    /**
     * Atualiza a Fatura.
     * @param Nova atividade económica.
     */
    public String setAtividade(String a) {
        return this.atividadeEconomica = a;
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
     * @param Novo nif cliente.
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
     * Método que converte uma data para milisegundos.
     * @param Data.
     * @return Data convertida em milisegundos.
     */
    public int dateToInt(Date data){
        int i = (int) (data.getTime()/1000);
        return i;
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
               (this.data.equals(p.getData())) && (this.nifCliente == p.getNifCliente()) && (this.descrição.equals(p.getDescrição())) &&
               (this.valor == p.getValor()) && this.atividadeEconomica.equals(p.getAtividade());
    }

    /**
     * Método que devolve a representação em string da atura.
     * @return String com as informações da Fatura.
     */
    public String toString(){
        return "Fatura: Nif do Emissor - " + this.nifEmitente + " Emissor - " + this.emitente +
               " Data - " + this.data.toString() + " Nif do cliente - " + this.nifCliente + " Descrição da fatura - " +
               this.descrição + this.atividadeEconomica + " Valor da fatura - " + this.valor;
    }

    public Fatura clone() {
        return new Fatura(this);
    }

    public TreeMap<Double,Fatura> abFaturasV = new TreeMap<Double,Fatura>();

    /**
     * Método que adiciona uma fatura a uma TreeMap em função do seu valor, caso esta não exista.
     * @param TreeMap.
     * @param Fatura.
     * @return TreeMap com a fatura inserida.
     */
    public TreeMap inserirV(TreeMap a, Fatura f){
        Double key = f.getValor();

        if (a == null){
            a = new TreeMap<Double,Fatura>();
            a.put(key, f);
        }

        if (a.get(key) == null){
            a.put(key, f);
        } else {
            if (a.get(key).equals(f)){
                return a;
            } else {
                a.put(key, f);
            }
        }

        return a;
    }

    public TreeMap<Date,Fatura> abFaturasD = new TreeMap<Date,Fatura>();

    /**
     * Método que adiciona uma fatura a uma TreeMap em função da sua Data, caso esta não exista.
     * @param TreeMap.
     * @param Fatura.
     * @return TreeMap com a fatura inserida.
     */
    public TreeMap inserirD(TreeMap a, Fatura f){
        Date key = f.getData();

        if (a == null){
            a = new TreeMap<Double,Fatura>();
            a.put(key, f);
        }

        if (a.get(key) == null){
            a.put(key, f);
        } else {
            if (a.get(key).equals(f)){
                return a;
            } else {
                a.put(key, f);
            }
        }

        return a;
    }
}
