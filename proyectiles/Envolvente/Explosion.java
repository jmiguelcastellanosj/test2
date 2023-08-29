
/**
 * <p>Title: Proyecto Envolvente</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.util.*;

public class Explosion
{
    private double x, y, v, g;
    private int np;
    private Vector proyectiles;

    public Explosion (double x, double y, double v, double g, int np)
    {
        this.x = x;
        this.y = y;
        this.v = v;
        this.g = g;
        this.np = np;
        proyectiles = new Vector ();

        creaProyectiles ();
    }

    public Vector getDatos ()
    {
        return proyectiles;
    }

    private void creaProyectiles ()
    {
        double angulo = 0;
        double incremento = (2 * Math.PI) / np;

        while (angulo < 2 * Math.PI) {
            Proyectil p = new Proyectil (v, angulo, g, x, y);
            proyectiles.addElement (p);

            angulo += incremento;
        }
    }
}
