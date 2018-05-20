import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class AdministradorMenu extends EstadoMenu {
    private int nif;
    private String password;

    public AdministradorMenu(Scanner s, Plataforma p, int nif, String password) {
        super(s, p);
        this.nif = nif;
        this.password = password;
    }

    public EstadoMenu listarEmpresasPorFaturacao(){
        System.out.print("Número de empresas que pretende listar:");
        int x = this.scanner.nextInt();

        try {
            for(Empresa e : this.plataforma.getXEmpresasComMaisFaturas(x, this.nif, this.password)){
                System.out.println("A empresa é: " + e.toString() + "\nCom a dedução fiscal de:" + e.getDeducaoTotal());
            }
        } catch (FailureOnLoginException e){
            System.out.println("Informação de login incorreta.");
        } catch (PermissionDeniedException e){
            System.out.println("Sem permissão.");
        }
        return this;
    }

    public EstadoMenu interact() {
        System.out.println("(1) - Listar empresas com maior faturação\n(2) - Logout");
        int decisao = this.scanner.nextInt();
        switch (decisao) {
            case 1:
                return this.listarEmpresasPorFaturacao();
            case 2:
                return new MainMenu(this.scanner, this.plataforma);
            }
        return this;
    }
}
