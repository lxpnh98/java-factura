import java.util.Set;
import java.io.Serializable;

/**
 * Classe AtividadeEconomica - Classe que representa uma atividade económica.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class AtividadeEconomica implements Serializable {
    private String nome;

    /**
     * Construtor por omissão de AtividadeEconomica.
     */
    public AtividadeEconomica(){
        this.nome = "";
    }

    /**
     * Construtor parametrizado de AtividadeEconomica.
     */
    public AtividadeEconomica(String nome){
        this.nome = nome;
    }

    /**
     * Construtor de cópia de AtividadeEconomica.
     */
    public AtividadeEconomica(AtividadeEconomica a){
        this.nome = a.getNome();
    }

    /**
     * Devolde a string do nome da AtividadeEconomica.
     * @return Nome da AtividadeEconomica.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Calcula a dedução.
     * @return Double valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        return 0.0;
    }

    /**
     * Método que devolve a representação em string da AtividadeEconomica.
     * @return String com as informações da AtividadeEconomica.
     */
    public String toString(){
        return this.nome;
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