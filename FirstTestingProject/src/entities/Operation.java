package entities;

/**
 * Created by the Focking Jendoliver on 16/10/2017
 * GitHub: github.com/Jendoliver
 */
public class Operation
{
    public static int add(int x, int y) {
        return x + y;
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static int sub(int x, int y) {
        return (x > y) ? x - y : y - x;
    }
}
