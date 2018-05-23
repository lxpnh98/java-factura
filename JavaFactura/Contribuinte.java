import java.io.*;

/**
 * Classe abstrata Contribuinte - Classe que representa um contribuinte.
 *
 * @author Alexandre Pinho (a82441); Joel Gama (a82202); Tiago Pinheiro (a82491).
 */
public abstract class Contribuinte implements Serializable
{
    private int nif;
    private String nome;
    private String email;
    private String morada;
    private String password;

    /**
     * Construtor por omissão do Contribuinte.
     */
    public Contribuinte(){
        this.nif = 0;
        this.nome = "";
        this.email = "";
        this.morada = "";
        this.password = "";
    }

    /**
     * Construtor parametrizado do Contribuinte.
     */
    public Contribuinte(int nif, String nome, String email, String morada, String password){
        this.nif = nif;
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.password = password;
    }

    /**
     * Construtor de cópia do Contribuinte.
     */
    public Contribuinte(Contribuinte contribuinte){
        this.nif = contribuinte.getNIF();
        this.nome = contribuinte.getNome();
        this.email = contribuinte.getEmail();
        this.morada = contribuinte.getMorada();
        this.password = contribuinte.getPassword();
    }

    /**
     * Devolde o valor do NIF.
     * @return Valor do nif.
     */
    public int getNIF(){
        return this.nif;
    }

    /**
     * Devolde a string do nome do contribuinte.
     * @return Nome do contribuinte.
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Devolde a string do email do contribuinte.
     * @return Email do contribuinte.
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Devolde a string da morada do contribuinte.
     * @return Morada do contribuinte.
     */
    public String getMorada(){
        return this.morada;
    }

    /**
     * Devolde a string da password do contribuinte.
     * @return Password do contribuinte.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Atualiza o nif.
     * @param Novo nif.
     */
    public void setNIF(int nif){
        this.nif = nif;
    }

    /**
     * Atualiza o nome do contribuinte.
     * @param Novo nome.
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Atualiza o email do contribuinte.
     * @param Novo email.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Atualiza a morada do contribuinte.
     * @param Nova morada.
     */
    public void setMorada(String morada){
        this.morada = morada;
    }

    /**
     * Atualiza a password do contribuinte.
     * @param Nova password.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Cria uma cópia do objecto contribuinte.
     * @return
     */
    public abstract Contribuinte clone();

    /**
     * Verifica a igualdade de dois objectos.
     * @param contribuinte;
     * @return Valor boleano da comparação. 
     */
    public boolean equals(Object contribuinte) {
        if(contribuinte==this) return true;
        if(contribuinte==null || contribuinte.getClass() !=this.getClass()) return false;
        Contribuinte c = (Contribuinte) contribuinte;
        return this.nif == c.getNIF() && this.nome.equals(c.getNome()) &&
                this.email.equals(c.getEmail()) && this.morada.equals(c.getMorada()) &&
                this.password.equals(c.getPassword());
    }

    /**
     * Método que devolve a representação em String do contribuinte.
     * @return String com as informações do contribuiente.
     */
    public String toString(){
        return "NIF: " + this.nif + "\nNome: " + this.nome + "\nEmail: " + 
               this.email + "\nMorada: " + this.morada + "\nPassword: " + this.password;
    }
}