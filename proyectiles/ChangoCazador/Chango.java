
/**
 * <p>Title: Applet del chango y el cazador</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class Chango
{
    private double x, y, w;

    public Chango (double x, double y)
    {
        this (x, y, 0);
    }

    public Chango (double x, double y, double w)
    {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public double getX ()
    {
        return x;
    }

    public double getY ()
    {
        return y;
    }

    public double getW ()
    {
        return w;
    }

    public void setX (double x)
    {
        this.x = x;
    }

    public void setY (double y)
    {
        this.y = y;
    }

    public void setW (double w)
    {
        this.w = w;
    }
}
