
/**
 * Write a description of class Operacao here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Operacao implements Expressao
{
    // instance variables - replace the example below with your own
    private double x;

    /**
     * Constructor for objects of class Operacao
     */
    public Operacao()
    {
        // initialise instance variables
        x = 0;
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
        return x;
    }
}