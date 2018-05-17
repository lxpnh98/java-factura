import java.util.Set;

/**
 * classe DespesasGerais
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class DespesasGerais extends AtividadeEconomica {
    /**
     * Construtor por omissão de DespesasGerais.
     */
    public DespesasGerais() {
        super();
    }

    /**
     * Construtor parametrizado de DespesasGerais.
     */
    public DespesasGerais(String nome) {
        super("DespesasGerais");
    }

    /**
     * Construtor de cópia de DespesasGerais.
     */
    public DespesasGerais(DespesasGerais d) {
        super(d);
    }

    /**
     * Método que devolve o valor a deduzir.
     * @param Double valor
     * @param Set propriedades
     * @return Double com o valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        Double res = valor * 0.3;
        return res;
    }

    /**
     * Método que devolve a representação em string da DespesasGerais.
     * @return String com as informações da DespesasGerais.
     */
    public String toString(){
        return super.toString();
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor boleano da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        DespesasGerais p = (DespesasGerais) o;
        return super.equals(o);
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public DespesasGerais clone() {
        return new DespesasGerais(this);
    }
}