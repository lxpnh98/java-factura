import java.util.Set;

/**
 * classe Saude
 * 
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Saude extends AtividadeEconomica {
    /**
     * Construtor por omissão de Saude.
     */
    public Saude() {
        super();
    }

    /**
     * Construtor parametrizado de Saude.
     */
    public Saude(String nome) {
        super("Saude");
    }

    /**
     * Construtor de cópia de Saude.
     */
    public Saude(Saude s) {
        super(s);
    }

    /**
     * Método que devolve o valor a deduzir.
     * @param Double valor
     * @param Set propriedades
     * @return Double com o valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        Double res = valor * 0.4;
        return res;
    }

    /**
     * Método que devolve a representação em string da Saude.
     * @return String com as informações da Saude.
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
        Saude p = (Saude) o;
        return super.equals(o);
    }

    /**
     * Metodo que faz o clone de uma atividade economica
     */
    public Saude clone() {
        return new Saude(this);
    }
}