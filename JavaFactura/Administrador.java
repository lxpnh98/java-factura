import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;
import java.io.Serializable;
import java.util.Date;

/**
 * class Administrador - Classe que representa um administrador.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Administrador extends Contribuinte implements Serializable
{
    public TreeMap<Double,Empresa> empresasComMaisFaturacao = new TreeMap<>();

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
        this.empresasComMaisFaturacao = a.getMapEmpresasComMaisFaturacao();
    }

     /**
     * Devolde uma TreeMap.
     * @return TreeMap.
     */
    public TreeMap<Double,Empresa> getMapEmpresasComMaisFaturacao(){
        TreeMap<Double,Empresa> a = new TreeMap<>();
        return a;
    }

    /**
     * Cria uma cópia do objecto Administrador.
     * @return
     */
    public Administrador clone() {
        return new Administrador(this);
    }

    /**
     * Método que devolve a representação em string da Administrador.
     * @return String com as informações da Administrador.
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
        Administrador p = (Administrador) emp;
        return super.equals(p);
    }

    public void inserirPorFaturacao(Empresa e){
        Double key = e.totalFaturadoDesdeSempre();

        this.empresasComMaisFaturacao.put(key, e.clone());
    }

    public ArrayList<Empresa> empresasPorFaturacao(int x){
        int i = 0;
        ArrayList<Empresa> listaempresas = new ArrayList<>();

        Iterator ittwo = this.empresasComMaisFaturacao.keySet().iterator();
        while (ittwo.hasNext() && i < x) {
            listaempresas.add(((Empresa)(ittwo.next())).clone());
            i++;
        }
        return listaempresas;
    }
}
