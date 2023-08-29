
/**
 * <p>Title: Applet del chango y el cazador</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.util.*;

public class Proyectil
{
    private double v0, ang, g, friccion, x0, y0, xf, yf, t_aire, h_max, alcance, vf, vfx, vfy;
    private boolean choca;
    private Vector datosXY = new Vector ();

    public Proyectil (double v0, double a, double g)
    {
        this (v0, a, g, 0, 0, 0, 0, 0, false);
    }

    public Proyectil (double v0, double a, double g, double x0, double y0)
    {
        this (v0, a, g, 0, x0, y0, 0, 0, false);
    }

    public Proyectil (double v0, double a, double g, double f, double x0, double y0, double xf, double yf, boolean ch)
    {
        this.v0 = v0;
        ang = a;
        this.g = g;
        friccion = f;
        this.x0 = x0;
        this.y0 = y0;
        this.xf = xf;
        this.yf = yf;
        choca = ch;

        if (ang >= 0 & ang <= Math.PI)
            h_max = y0 + (v0 * v0 * Math.sin (ang) * Math.sin (ang)) / ( 2 * g);
        else
            h_max = y0;

        double w2 = v0 * Math.sin (ang);
        w2 *= w2;
        t_aire = (v0 * Math.sin (ang)) / g + Math.sqrt (w2 / (g * g) + (2 * y0) / g);

        alcance = x0 + v0 * t_aire * Math.cos (ang);

        vfx = v0 * Math.cos (ang);
        vfy = v0 * Math.sin (ang) - g * t_aire;
        vf = Math.sqrt(vfx * vfx + vfy * vfy);

        calculaXY ();
    }

    private void calculaXY ()
    {
      if ((ang == Math.PI / 2) || (ang == (3 * Math.PI) / 2)) {
          datosXY.addElement(new DatosXY (x0, 0));
          datosXY.addElement(new DatosXY (x0, h_max));
      }
      else {
          double x, y;
          double democracia;

          // saca posicion xy
          if (v0 == 0)
              democracia = 0.1;
          else
              democracia = Math.pow (10, 2 * Math.floor (log10 (v0)) - 2);

          if ((ang > Math.PI / 2) && (ang < (3 * Math.PI) / 2))
              democracia *= -1;

          x = x0;
          boolean salida = false;

          do {
              double dx = x - x0;
              if (v0 > 0)
                  y = dx * Math.tan (ang) -
                      (g * dx * dx) / (2 * v0 * v0) *
                      (1 + Math.tan (ang) * Math.tan (ang)) + y0;
              else {
                  y = y0;
                  salida = true;
              }

              if (y >= 0) {
                  datosXY.addElement (new DatosXY (x, y));
                  x += democracia;
              }

          }
          while (y >= 0 && !salida);

          // el último y nos vamos
          datosXY.addElement (new DatosXY (alcance, 0));
      }
    }

    public double getX0 ()
    {
        return x0;
    }

    public double getY0 ()
    {
        return y0;
    }

    public double getAngulo ()
    {
        return ang;
    }

    public double getAltura (double x)
    {
        double dx = x - x0;
        double y;

        if (v0 > 0)
            y = dx * Math.tan(ang) - (g * dx * dx) / (2 * v0 * v0) * (1 + Math.tan(ang) * Math.tan(ang)) + y0;
        else
            y = 0;

        return y;
    }

    public double VelInicial ()
    {
        return v0;
    }

    public double velInicialX ()
    {
        return v0 * Math.cos (ang);
    }

    public double velFinalY ()
    {
        return vfy;
    }

    public double velFinal ()
    {
        return vf;
    }

    public double velInicialY ()
    {
        return v0 * Math.sin (ang);
    }

    public double Alcance ()
    {
        return alcance - x0;
    }

    public double AlcanceReal ()
    {
        return alcance;
    }

    public double AlturaMaxima ()
    {
        return h_max;
    }

    public double TiempoAire ()
    {
        return t_aire;
    }

    public double getG ()
    {
        return g;
    }

    public Vector DatosXY ()
    {
        return datosXY;
    }

    public double PosXMaximo ()
    {
        if (ang > 0 && ang < Math.PI)
            return x0 + (v0 * v0 * Math.sin (ang) * Math.cos (ang)) / g;
        else
            return x0;
    }

    private double log10 (double x)
    {
        return Math.log (x) / Math.log (10);
    }

}
