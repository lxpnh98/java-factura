import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class Plataforma here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plataforma
{
    private Map<Integer,Contribuinte> contribuintes;
    private Map<Integer,Fatura> faturas;

    /**
     * Constructor for objects of class Plataforma
     */
    public Plataforma()
    {
        contribuintes = new HashMap<Integer,Contribuinte>();
        faturas = new HashMap<Integer,Fatura>();
    }

    public void adicionarContribuinte(Contribuinte c) {
        this.contribuintes.put(c.getNIF(), c);
    }

    public void adicionarFatura(Fatura f) {
        this.faturas.put(f.getId(), f);
    }
}
