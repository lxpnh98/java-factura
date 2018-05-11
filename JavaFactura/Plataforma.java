import java.util.Map;
import java.util.HashMap;

class FailureOnLoginException extends Exception {
    FailureOnLoginException(String s) {
        super(s);
    }
}

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
    private Plataforma()
    {
        contribuintes = new HashMap<Integer,Contribuinte>();
        faturas = new HashMap<Integer,Fatura>();
    }

    public void adicionarContribuinte(Contribuinte c) {
        this.contribuintes.put(c.getNIF(), c.clone());
    }

    public void adicionarFatura(Fatura f, int nif, String password) throws Exception {
        Contribuinte c = this.login(nif, password);
        if (c == null) throw new FailureOnLoginException("");
        this.faturas.put(f.getId(), f.clone());
    }

    public Contribuinte login(int nif, String password) {
        Contribuinte c = this.contribuintes.get(nif);
        if (c != null && c.getPassword().equals(password))
            return c.clone();
        return null;
    }

    public static Plataforma carregarPlataforma() {
        System.out.println("carregarPlataforma()");
        return new Plataforma();
    }
}
