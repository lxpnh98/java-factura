
/**
 * Write a description of class Constante here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Constante implements Expressao
{
    // instance variables - replace the example below with your own
    private double valor;

    /**
     * Constructor for objects of class Constante
     */
    public Constante()
    {
        // initialise instance variables
        valor = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public double eval()
    {
        // put your code here
        return this.valor;
    }
}
