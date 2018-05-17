/**
 * classe Educacao
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Educacao extends AtividadeEconomica {
    /**
     * Construtor por omissão de Educacao.
     */
    public Educacao() {
        super();
    }

    /**
     * Construtor parametrizado de Educacao.
     */
    public Educacao(String nome) {
        super(nome);
    }

    /**
     * Construtor de cópia de Educacao.
     */
    public Educacao(Educacao e) {
        super(e);
    }

    /**
     * Método que devolve o valor a deduzir.
     * @param Double valor
     * @param Set propriedades
     * @return Double com o valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        Double res = valor * 0.2;
        return res;
    }

    /**
     * Método que devolve a representação em string da Educacao.
     * @return String com as informações da Educacao.
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
        Educacao p = (Educacao) o;
        return (this.nome == p.getNome());
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public Educacao clone() {
        return new Educacao(this);
    }
}