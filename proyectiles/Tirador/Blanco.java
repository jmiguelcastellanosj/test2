
/**
 * <p>Title: Proyecto Tirador</p>
 * <p>Description: Simula tiro a un blanco fijo</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author H�ctor Antonio Villa Mart�nez y Sa�l Robles Garc�a
 * @version 1.0
 */

public class Blanco
{
    private double x, y, w;

    public Blanco (double x, double y)
    {
        this (x, y, 0);
    }

    public Blanco(double x, double y, double w)
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
