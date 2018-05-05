/**
 * class AtividadeEconomica - Classe que representa uma atividade economica.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class AtividadeEconomica
{
    // Variáveis de instância.
    private String atividade;

    /**
     * Construtor por omissão de AtividadeEconomica.
     */
    public AtividadeEconomica(){
        this.atividade = "";
    }

    /**
     * Construtor parametrizado de AtividadeEconomica.
     */
    public AtividadeEconomica(String atividade){
        this.atividade = atividade;
    }

    /**
     * Construtor de cópia de AtividadeEconomica.
     */
    public AtividadeEconomica(AtividadeEconomica umaAtividade){
        this.atividade = umaAtividade.getAtividade();
    }

    /**
     * Devolde a string do nome da AtividadeEconomica.
     * @return Nome da AtividadeEconomica.
     */
    public String getAtividade(){
        return this.atividade;
    }

    /**
     * Atualiza a AtividadeEconomica.
     * @param Nova AtividadeEconomica.
     */
    public void setAtividade(String atividade){
        this.atividade = atividade;
    }

    /**
     * Método que devolve a representação em string da AtividadeEconomica.
     * @return String com as informações da AtividadeEconomica.
     */
    public String toString(){
        return "A atividade econômica é: " + this.atividade;
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor boleano da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        AtividadeEconomica p = (AtividadeEconomica) o;
        return (this.atividade == p.atividade);
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public AtividadeEconomica clone() {
        return new AtividadeEconomica(this);
    }
}
