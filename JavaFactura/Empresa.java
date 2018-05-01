
/**
 * Write a description of class Empresa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Empresa extends Contribuinte
{
    // instance variables - replace the example below with your own
    private int nif;
    private String nome;
    private String email;
    private String morada;
    private String password;


    /**
    * Construtor por omissão de Empresa.
    */
    public Empresa(){
        this.nif = 0;
        this.nome = "";
        this.email = "";
        this.morada = "";
        this.password = "";
    }

     /**
     * Construtor parametrizado de Empresa.
     */
    public Empresa(int nif, String nome, String email, String morada, String password){
        this.nif = nif;
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.password = password;
    }

     /**
     * Construtor de cópia de Empresa.
     */
    public Empresa(Empresa umaEmpresa){
        this.nif = umaEmpresa.getNIF();
        this.nome = umaEmpresa.getNome();
        this.email = umaEmpresa.getEmail();
        this.morada = umaEmpresa.getMorada();
        this.password = umaEmpresa.getPassword();
    }

     /**
     * Devolde o valor do nif.
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
    * Método que devolve a representação em String da Empresa.
    * @return String com as informações da Empresa.
    */
    public String toString(){
        return "NIF: " + this.nif + "\nNome: " + this.nome + "\nEmail: " + 
               this.email + "\nMorada: " + this.morada + "\nPassword: " + this.password;
    }

    /**
    * Método que verifica se dois objetos são iguais, isto é, se são da mesma classe e se têm os mesmos valores.
    * @return Valor da comparação.
    */
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass()!= this.getClass())) return false;
        Empresa p = (Empresa) o;
        return (this.nif == p.getNIF()) && (this.nome == p.getNome()) &&
               (this.email == p.getEmail()) && (this.morada == p.getMorada()) &&
               (this.password == p.getPassword());
    }
}
