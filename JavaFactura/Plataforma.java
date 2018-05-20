import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;


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
 * class Plataforma
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Plataforma implements Serializable {
    private Map<Integer,Contribuinte> contribuintes;
    private Map<Integer,Fatura> faturas;
    private Map<String,AtividadeEconomica> atividadesEconomicas;
    private TreeMap<Integer,List<Integer>> empresasComMaisFaturas;

    /**
     * Constructor for objects of class Plataforma
     */
    public Plataforma()
    {
        this.contribuintes = new HashMap<Integer,Contribuinte>();
        this.faturas = new HashMap<Integer,Fatura>();
        this.atividadesEconomicas = new HashMap<String,AtividadeEconomica>();
        this.empresasComMaisFaturas = new TreeMap<Integer,List<Integer>>();
        this.atividadesEconomicas.put("", new AtividadeEconomica());
        Habitacao h = new Habitacao();
        this.atividadesEconomicas.put(h.getNome(), h);
        Educacao e = new Educacao();
        this.atividadesEconomicas.put(e.getNome(), e);
        Saude s = new Saude();
        this.atividadesEconomicas.put(s.getNome(), s);
        DespesasGerais d = new DespesasGerais();
        this.atividadesEconomicas.put(d.getNome(), d);
        this.adicionarContribuinte(new Administrador(0, "", "", "", "admin"));
    }

    public void adicionarContribuinte(Contribuinte c) {
        this.contribuintes.put(c.getNIF(), c.clone());
        if (c instanceof Empresa) {
            Integer key = ((Empresa)c).getNumFaturas();
            List<Integer> l = this.empresasComMaisFaturas.get(key);
            if (l == null) {
                l = new ArrayList<Integer>();        
            }
            l.add(c.getNIF());
            this.empresasComMaisFaturas.put(key, l);
        }
    }

    private void updateEmpresasComMaisFaturas(int nif) {
        int numFaturas = ((Empresa)this.contribuintes.get(nif)).getNumFaturas();
        System.out.println(numFaturas);
        List<Integer> antiga = this.empresasComMaisFaturas.get(numFaturas - 1);
        antiga.remove(antiga.indexOf(nif));
        List<Integer> l = this.empresasComMaisFaturas.get(numFaturas);
        if (l == null) {
            l = new ArrayList<Integer>();        
        }
        l.add(nif);
        this.empresasComMaisFaturas.put(numFaturas, l);
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
            ((Empresa)this.contribuintes.get(f.getNifEmitente())).inserirPorValor(f);
            ((Empresa)this.contribuintes.get(f.getNifEmitente())).inserirPorData(f);
            this.updateEmpresasComMaisFaturas(c.getNIF());
        } else {
            throw new PermissionDeniedException("Não é Empresa");
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

    public void setFatura(int id, Fatura fatura, int nif, String password) throws NonExistentBillException,
                                                                                  PermissionDeniedException,
                                                                                  FailureOnLoginException {
        Fatura f = this.faturas.get(id);
        if (f == null) {
            throw new NonExistentBillException("");
        }
        if (nif != f.getNifCliente()) {
            throw new PermissionDeniedException("");
        }
        try {
            this.login(nif, password);
            this.faturas.put(id, fatura.clone());
            ((Empresa)this.contribuintes.get(fatura.getNifEmitente())).atualizarFatura(fatura);
        } catch (FailureOnLoginException e) {
            throw e;
        }
    }

    public Map<String,AtividadeEconomica> getAtividadesEconomicas() {
        return new HashMap(this.atividadesEconomicas);
    }

    public ArrayList<Fatura> getFaturasPorValor(int nif, String password) throws FailureOnLoginException, 
                                                                                 PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorValor());
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    public ArrayList<Fatura> getFaturasPorData(int nif, String password) throws FailureOnLoginException, 
                                                                                PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorData());
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    public double calcularDeducaoTotal(int nif, String password) throws FailureOnLoginException,
                                                                        PermissionDeniedException {
        Double sum = 0.0;
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof ContribuinteIndividual) {            
            Collection<Integer> faturas = ((ContribuinteIndividual)this.contribuintes.get(nif)).getFaturas();
            for (Integer i : faturas) {
                Fatura f = this.faturas.get(i);
                AtividadeEconomica a = this.atividadesEconomicas.get(f.getAtividade());
                switch (a.getNome()) {
                    case "Habitacao":
                        a = new Habitacao();
                        break;
                    case "Saude":
                        a = new Saude();
                        break;
                    case "Educacao":
                        a = new Educacao();
                        break;
                    case "DespesasGerais":
                        a = new DespesasGerais();
                        break;
                }
                sum += a.calcularDeducao(f.getValor(), new HashSet());
            }
            return sum;
        } else {
            throw new PermissionDeniedException("Não é contribuinte individual");
        }
    }

    public double getTotalFaturado(int nif, String password, Date begin, Date end) throws FailureOnLoginException,
                                                                                           PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Empresa){
            double v = 0;
            v = ((Empresa)this.contribuintes.get(c.getNIF())).totalFaturado(begin, end);
            return v;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    public ArrayList<Fatura> getFaturasPorValorContribuinte(int nif, String password) throws FailureOnLoginException,
                                                                                             PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorValorContribuinte());
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    public ArrayList<Fatura> getFaturasPorDataContribuinte(int nif, String password, Date begin, Date end) throws FailureOnLoginException,
                                                                                                                  PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorDataContribuinte(begin, end));
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    public Collection<Empresa> getXEmpresasComMaisFaturas(int X, int nif, String password) throws FailureOnLoginException,
                                                                                                  PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Administrador) {
            List<Integer> r = new ArrayList(X);
            Iterator<Integer> it = this.empresasComMaisFaturas.navigableKeySet().descendingIterator();
            int n;
            while (true) {
                if (it.hasNext() == false) break;
                for (Integer i : this.empresasComMaisFaturas.get(it.next())) {
                    r.add(i);
                    if (r.size() == X) break;
                }
                if (r.size() == X) break;
            }
            return r.stream().map(i -> ((Empresa)this.contribuintes.get(i)).clone()).collect(Collectors.toList());
        } else {
            throw new PermissionDeniedException("Não é Administrador");
        }

}

    /**
     * Método que guarda num ficheiro de objetos o estado atual da aplicação.
     * @param String nomeFicheiro.
     */
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream guardaFicheiro = new FileOutputStream(nomeFicheiro);
        if (guardaFicheiro == null) { 
            throw new FileNotFoundException("");
        } else { 
            ObjectOutputStream objeto = new ObjectOutputStream(guardaFicheiro);
            if (objeto == null) {
                throw new IOException("");
            } else {
                objeto.writeObject(this);
                objeto.flush();
                objeto.close();
            }
        }
    }
    
    /**
     * Método que carrega um estado apartir de um ficheiro de objetos;
     * @param String nomeFicheiro;
     * @return Plataforma p.
     */
    public static Plataforma carregarPlataforma(String nomeFicheiro) throws FileNotFoundException,
                                                            IOException, ClassNotFoundException {
       FileInputStream carregaFicheiro = new FileInputStream(nomeFicheiro);
       if (carregaFicheiro == null) { 
            throw new FileNotFoundException("");
       } else {
            ObjectInputStream obj = new ObjectInputStream(carregaFicheiro);
            if (obj == null) {
                throw new IOException("");
            } else {
                Plataforma p = (Plataforma) obj.readObject();
                if (p == null) {
                    throw new ClassNotFoundException("");
                } else {
                    obj.close();
                    return p;
                }
            }
       }
    }
}
