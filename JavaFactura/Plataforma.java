import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
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

/**
 * Classe FailureOnLoginException - subclasse de Exception.
 */
class FailureOnLoginException extends Exception {
    FailureOnLoginException() {
        super();
    }

    FailureOnLoginException(String s) {
        super(s);
    }
}

/**
 * Classe PermissionDeniedException - subclasse de Exception.
 */
class PermissionDeniedException extends Exception {
    PermissionDeniedException() {
        super();
    }

    PermissionDeniedException(String s) {
        super(s);
    }
}

/**
 * Classe NonExistentClientException - subclasse de Exception.
 */
class NonExistentClientException extends Exception {
    NonExistentClientException() {
        super();
    }

    NonExistentClientException(String s) {
        super(s);
    }
}

/**
 * Classe NonExistentBillException - subclasse de Exception.
 */
class NonExistentBillException extends Exception {
    NonExistentBillException() {
        super();
    }

    NonExistentBillException(String s) {
        super(s);
    }
}

/**
 * Classe Plataforma
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public class Plataforma implements Serializable {
    private Map<Integer,Contribuinte> contribuintes;
    private Map<Integer,Fatura> faturas;
    private Map<String,AtividadeEconomica> atividadesEconomicas;
    private TreeMap<Integer,List<Integer>> empresasComMaisFaturas;
    private TreeMap<Double,Set<Integer>> contribuintesQueMaisGastam;

    /**
     * Constructor por omissão de Plataforma.
     */
    public Plataforma()
    {
        this.contribuintes = new HashMap<Integer,Contribuinte>();
        this.faturas = new HashMap<Integer,Fatura>();
        this.atividadesEconomicas = new HashMap<String,AtividadeEconomica>();
        this.empresasComMaisFaturas = new TreeMap<Integer,List<Integer>>();
        this.contribuintesQueMaisGastam = new TreeMap<Double,Set<Integer>>();
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

    /**
     * Método que adiciona um contribuinte ao sistema.
     * @param Contribuinte
     */
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
        } else if (c instanceof ContribuinteIndividual) {
            Double totalFaturado = ((ContribuinteIndividual)c).getTotalFaturado();
            Set<Integer> l = new HashSet();
            l.add(((ContribuinteIndividual)c).getNIF());
            System.out.println(""+totalFaturado);
            this.contribuintesQueMaisGastam.put(totalFaturado, l);
        }
    }

    /**
     * Método que atualiza a lista das empresas que mais faturam.
     * @param int
     */
    private void updateEmpresasComMaisFaturas(int nif) {
        int numFaturas = ((Empresa)this.contribuintes.get(nif)).getNumFaturas();
        System.out.println(numFaturas);
        List<Integer> antiga = this.empresasComMaisFaturas.get(numFaturas - 1);
        if (antiga.indexOf(nif) != -1){
            antiga.remove(antiga.indexOf(nif));
        }
        List<Integer> l = this.empresasComMaisFaturas.get(numFaturas);
        if (l == null) {
            l = new ArrayList<Integer>();        
        }
        l.add(nif);
        this.empresasComMaisFaturas.put(numFaturas, l);
    }

    /**
     * Método que atualiza a lista dos contribuintes que mais gastam.
     * @param Fatura
     */
    private void updateContribuintesQueMaisGastam(Fatura f) {
        ContribuinteIndividual c = (ContribuinteIndividual)this.contribuintes.get(f.getNifCliente());
        Set<Integer> s = this.contribuintesQueMaisGastam.get(c.getTotalFaturado());
        s.remove(c.getNIF());
        Set<Integer> s2 = this.contribuintesQueMaisGastam.get(c.getTotalFaturado() + f.getValor());
        if (s2 == null) {
            s2 = new HashSet();
        }
        s2.add(c.getNIF());
        this.contribuintesQueMaisGastam.put(c.getTotalFaturado() + f.getValor(), s2);
    }

    /**
     * Método que adiciona uma fatura.
     * @param Fatura
     * @param int
     * @param String
     */
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
            this.updateContribuintesQueMaisGastam(f);
            ((ContribuinteIndividual)this.contribuintes.get(f.getNifCliente())).adicionarFatura(f);
            ((Empresa)this.contribuintes.get(f.getNifEmitente())).inserirPorValor(f);
            ((Empresa)this.contribuintes.get(f.getNifEmitente())).inserirPorData(f);
            this.updateEmpresasComMaisFaturas(c.getNIF());
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    /**
     * Método que permite fazer login.
     * @param int
     * @param String
     * @return Contribuinte
     */
    public Contribuinte login(int nif, String password) throws FailureOnLoginException {
        Contribuinte c = this.contribuintes.get(nif);
        if (c != null && c.getPassword().equals(password))
            return c.clone();
        throw new FailureOnLoginException("");
    }

    /**
     * Método que verifica se existe um individuo a partir do seu nif.
     * @param int
     * @return boolean
     */
    public boolean existsIndividuo(int nif) {
        if (this.contribuintes.containsKey(nif) == false)
            return false;
        if (this.contribuintes.get(nif) instanceof ContribuinteIndividual == false)
            return false;
        return true;
    }

    /**
     * Método que devolve o nome de uma empresa a partir do seu nif.
     * @param int
     * @return String
     */
    public String getNomeEmpresa(int nif) {
        if (this.contribuintes.containsKey(nif) == false)
            return "";
        String nome = this.contribuintes.get(nif).getNome();
        return nome;
    }

    /**
     * Método que devolve uma fatura.
     * @param int
     * @param int
     * @param String
     * @return Fatura
     */
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

    /**
     * Método que atualiza uma fatura.
     * @param int
     * @param Fatura
     * @param int
     * @param String
     */
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

    /**
     * Método que devolve as atividades económicas.
     * @return Map
     */
    public Map<String,AtividadeEconomica> getAtividadesEconomicas() {
        return new HashMap(this.atividadesEconomicas);
    }

    /**
     * Método que devolve as faturas ordenadas por valor crescente.
     * @param int
     * @param String
     * @return ArrayList
     */
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

    /**
     * Método que devolve as faturas ordenadas por data crescente.
     * @param int
     * @param String
     * @return ArrayList
     */
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

    /**
     * Método que a dedução total associada a um contribuinte individual.
     * @param int
     * @param String
     * @return double
     */
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

    /**
     * Método que devolve o total faturado por uma empresa entre duas datas.
     * @param int
     * @param String
     * @param Date
     * @param Date
     * @return double
     */
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

    /**
     * Método que devolve as faturas de um contribuinte ordenadas por valor crescente.
     * @param int
     * @param String
     * @param int
     * @return ArrayList
     */
    public ArrayList<Fatura> getFaturasPorValorContribuinte(int nif, String password, int nifc) throws FailureOnLoginException,
                                                                                                       PermissionDeniedException,
                                                                                                       NonExistentClientException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (this.existsIndividuo(nifc) == false) {
            throw new NonExistentClientException(""+nifc);
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorValorContribuinte(nifc));
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    /**
     * Método que devolve as faturas de um contribuinte entre duas datas ordenadas por data crescente.
     * @param int
     * @param String
     * @param Date
     * @param Date
     * @param int
     * @return ArrayList
     */
    public ArrayList<Fatura> getFaturasPorDataContribuinte(int nif, String password, Date begin, Date end, int nifc) throws FailureOnLoginException,
                                                                                                                            PermissionDeniedException,
                                                                                                                            NonExistentClientException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
         if (this.existsIndividuo(nifc) == false) {
            throw new NonExistentClientException(""+nifc);
        }
        if (c instanceof Empresa){
            ArrayList<Fatura> faturas = new ArrayList<>(((Empresa)this.contribuintes.get(c.getNIF())).faturasPorDataContribuinte(begin, end, nifc));
            return faturas;
        } else {
            throw new PermissionDeniedException("Não é Empresa");
        }
    }

    /**
     * Método que devolve os dez contribuintes que mais gastam no sistema.
     * @param int
     * @param String
     * @return Collection
     */
    public Collection<ContribuinteIndividual> getTop10Contribuintes(int nif, String password) throws FailureOnLoginException,
                                                                                                     PermissionDeniedException {
        Contribuinte c;
        try {
            c = this.login(nif, password);
        } catch (FailureOnLoginException e) {
            throw e;
        }
        if (c instanceof Administrador) {
            List<Integer> r = new ArrayList(10);
            Iterator<Double> it = this.contribuintesQueMaisGastam.navigableKeySet().descendingIterator();
            while (true) {
                if (it.hasNext() == false) break;
                for (Integer i : this.contribuintesQueMaisGastam.get(it.next())) {
                    r.add(i);
                    if (r.size() == 10) break;
                }
                if (r.size() == 10) break;
            }
            return r.stream().map(i -> ((ContribuinteIndividual)this.contribuintes.get(i)).clone()).collect(Collectors.toList());
        } else {
            throw new PermissionDeniedException("Não é Administrador");
        }
    }

    /**
     * Método que devolve as X empresas com mais faturas.
     * @param int
     * @param int
     * @param String
     * @return Collection
     */
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
