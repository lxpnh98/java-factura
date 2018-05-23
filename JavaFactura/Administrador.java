import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe Administrador - Classe que representa um administrador.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Administrador extends Contribuinte implements Serializable
{    
    /**
     * Construtor por omissão de Administrador.
     */
    public Administrador(){
        super();
    }

    /**
     * Construtor parametrizado de Administrador.
     */
    public Administrador(int nif, String nome, String email, String morada, String password){
        super(nif, nome, email, morada, password);
    }

    /**
     * Construtor de cópia de Administrador.
     */
    public Administrador(Administrador a){
        super(a);
    }

    /**
     * Cria uma cópia do objecto Administrador.
     * @return Administrador
     */
    public Administrador clone() {
        return new Administrador(this);
    }

    /**
     * Verifica a igualdade de dois objectos
     * @param emp.
     * @return Valor boleano da comparação.
     */
    public boolean equals(Object emp){
        if (emp == this) return true;
        if ((emp == null) || (emp.getClass() != this.getClass())) return false;
        Administrador p = (Administrador) emp;
        return super.equals(p);
    }
}
