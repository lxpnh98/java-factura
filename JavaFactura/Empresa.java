
/**
 * Write a description of class Empresa here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    * Método que devolve a representação em string da Empresa.
    * @return String com as informações da Empresa.
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
        Empresa p = (Empresa) o;
        return super.equals();
    }
}
