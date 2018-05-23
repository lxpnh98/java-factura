import java.util.Set;

/**
 * Classe Saude - Subclasse de AtividadeEconomica
 * 
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Saude extends AtividadeEconomica {
    /**
     * Construtor por omissão de Saude.
     */
    public Saude() {
        super("Saude");
    }

    /**
     * Método que devolve o valor a deduzir.
     * @param Double valor.
     * @param Set propriedades.
     * @return Double com o valor da dedução.
     */
    public Double calcularDeducao(Double valor, Set propriedades) {
        return valor * 0.4;
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor boleano da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        Saude p = (Saude) o;
        return super.equals(p);
    }
}