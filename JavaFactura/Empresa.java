/**
 * class Empresa - Classe que representa um contribuinte coletivo.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Empresa extends Contribuinte
{
    // Variáveis de instâcia.

    /**
     * Construtor por omissão de Empresa.
     */
    public Empresa(){
        super();
    }

    /**
     * Construtor parametrizado de Empresa.
     */
    public Empresa(int nif, String nome, String email, String morada, String password){
        super(nif, nome, email, morada, password);
    }

    /**
     * Construtor de cópia de Empresa.
     */
    public Empresa(Empresa umaEmpresa){
        super(umaEmpresa);
    }

    /**
     * Cria uma cópia do objecto Empresa.
     * @return
     */
    public Empresa clone() {
        return new Empresa(this);
    }

    /**
     * Método que devolve a representação em string da Empresa.
     * @return String com as informações da Empresa.
     */
    public String toString(){
        return super.toString();
    }

    /**
     * Verifica a igualdade de dois objectos
     * @param emp;
     * @return Valor boleano da comparação.
     */
    public boolean equals(Object emp){
        if (emp == this) return true;
        if ((emp == null) || (emp.getClass() != this.getClass())) return false;
        Empresa p = (Empresa) emp;
        return super.equals(p);
    }
}