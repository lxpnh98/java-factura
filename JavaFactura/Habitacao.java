/**
 * classe Habitacao
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Habitacao extends AtividadeEconomica {
    /**
     * Construtor por omissão de Habitacao.
     */
    public Habitacao() {
        super();
    }

    /**
     * Construtor parametrizado de Habitacao.
     */
    public Habitacao(String nome) {
        super(nome);
    }

    /**
     * Construtor de cópia de Habitacao.
     */
    public Habitacao(Habitacao h) {
        super(h);
    }

    /**
     * Método que devolve o valor a deduzir.
     * @param Double valor
     * @param Set propriedades
     * @return Double com o valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        Double res = valor * 0.1;
        return res;
    }

    /**
     * Método que devolve a representação em string da Habitacao.
     * @return String com as informações da Habitacao.
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
        Habitacao p = (Habitacao) o;
        return (this.nome == p.getNome());
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public Habitacao clone() {
        return new Habitacao(this);
    }
}