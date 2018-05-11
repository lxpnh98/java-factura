import java.util.Scanner;

public class IndividuoInterface extends EstadoInterface
{
    public IndividuoInterface(Scanner s, Plataforma p) {
        super(s, p);
    }

    public EstadoInterface interact() {
        System.out.println("IndividuoInterface.interact()");
        return new MainInterface(this.scanner, this.plataforma);
    }
}
