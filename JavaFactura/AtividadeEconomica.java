/**
 * class AtividadeEconomica - Classe que representa uma atividade economica.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class AtividadeEconomica {
    private String nome;
    private double coeficiente;

    /**
     * Construtor por omissão de AtividadeEconomica.
     */
    public AtividadeEconomica(){
        this.nome = "";
        this.coeficiente = 0.0;
    }

    /**
     * Construtor parametrizado de AtividadeEconomica.
     */
    public AtividadeEconomica(String nome, double coef){
        this.nome = nome;
        this.coeficiente = coef;
    }

    /**
     * Construtor de cópia de AtividadeEconomica.
     */
    public AtividadeEconomica(AtividadeEconomica a){
        this.nome = a.getNome();
        this.coeficiente = a.getCoeficiente();
    }

    /**
     * Devolde a string do nome da AtividadeEconomica.
     * @return Nome da AtividadeEconomica.
     */
    public String getNome() {
        return this.nome;
    }

    public double getCoeficiente() {
        return this.coeficiente;
    }

    /**
     * Método que devolve a representação em string da AtividadeEconomica.
     * @return String com as informações da AtividadeEconomica.
     */
    public String toString(){
        return "A atividade econômica é: " + this.nome;
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor boleano da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        AtividadeEconomica p = (AtividadeEconomica) o;
        return (this.nome == p.getNome());
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public AtividadeEconomica clone() {
        return new AtividadeEconomica(this);
    }
}
