import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.*;

class FailureOnLoginException extends Exception {
    FailureOnLoginException(String s) {
        super(s);
    }
}

class PermissionDeniedException extends Exception {
    PermissionDeniedException(String s) {
        super(s);
    }
}

class NonExistentClientException extends Exception {
    NonExistentClientException(String s) {
        super(s);
    }
}

class NonExistentBillException extends Exception {
    NonExistentBillException(String s) {
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
    private Map<String,AtividadeEconomica> atividadesEconomicas;
    public TreeMap<Double,List<Fatura>> faturasPorValor;
    public TreeMap<Date,List<Fatura>> faturasPorData;

    /**
     * Constructor for objects of class Plataforma
     */
    private Plataforma()
    {
        contribuintes = new HashMap<Integer,Contribuinte>();
        faturas = new HashMap<Integer,Fatura>();
        atividadesEconomicas = new HashMap<String,AtividadeEconomica>();
        faturasPorValor  = new TreeMap<Double,List<Fatura>>();
        faturasPorData  = new TreeMap<Date,List<Fatura>>();
    }

    public void adicionarContribuinte(Contribuinte c) {
        this.contribuintes.put(c.getNIF(), c.clone());
    }

    public void adicionarFatura(Fatura f, int nif, String password) throws FailureOnLoginException,
                                                                           PermissionDeniedException,
                                                                           NonExistentClientException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (this.existsIndividuo(f.getNifCliente()) == false) {
            throw new NonExistentClientException(""+f.getNifCliente());
        }
        if (c instanceof Empresa) {
            f.setAtividade(((Empresa)c).getDefaultAtividade());
            this.faturas.put(f.getId(), f.clone());
            ((ContribuinteIndividual)this.contribuintes.get(f.getNifCliente())).adicionarFatura(f);
        } else {
            throw new PermissionDeniedException("Nao e Empresa");
        }
    }

    public Contribuinte login(int nif, String password) throws FailureOnLoginException {
        Contribuinte c = this.contribuintes.get(nif);
        if (c != null && c.getPassword().equals(password))
            return c.clone();
        throw new FailureOnLoginException("");
    }

    public boolean existsIndividuo(int nif) {
        if (this.contribuintes.containsKey(nif) == false)
            return false;
        if (this.contribuintes.get(nif) instanceof ContribuinteIndividual == false)
            return false;
        return true;
    }

    public Fatura getFatura(int id, int nif, String password) throws NonExistentBillException,
                                                                     PermissionDeniedException,
                                                                     FailureOnLoginException {
        Fatura f = this.faturas.get(id);
        if (f == null) {
            throw new NonExistentBillException("");
        }
        if (nif != f.getNifEmitente() && nif != f.getNifCliente()) {
            throw new PermissionDeniedException("");
        }
        try {
            this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        return f.clone();
    }

    public Map<String,AtividadeEconomica> getAtividadesEconomicas() {
        return new HashMap(this.atividadesEconomicas);
    }

    public static Plataforma carregarPlataforma() {
        System.out.println("carregarPlataforma()");
        return new Plataforma();
    }

    /**
     * Método que adiciona uma fatura a uma TreeMap em função do seu valor.
     * @param Fatura.
     * @return TreeMap com a fatura inserida.
     */
    public TreeMap inserirV(Fatura f){
        Double key = f.getValor();

        if (this.faturasPorValor.get(key) == null){
            List<Fatura> faturasMesmoValor = new ArrayList<>();
            faturasMesmoValor.add(f);
            this.faturasPorValor.put(key, faturasMesmoValor);
        } else {
            this.faturasPorValor.get(key).add(f);
        }

        return this.faturasPorValor;
    }

    /**
     * Método que adiciona uma fatura a uma TreeMap em função da sua Data.
     * @param Fatura.
     * @return TreeMap com a fatura inserida.
     */
    public TreeMap inserirD(Fatura f){
        Date key = f.getData();

        if (this.faturasPorData.get(key) == null){
            List<Fatura> faturasMesmaData = new ArrayList<>();
            faturasMesmaData.add(f);
            this.faturasPorData.put(key, faturasMesmaData);
        } else {
            this.faturasPorData.get(key).add(f);
        }

        return this.faturasPorData;
    }
}
