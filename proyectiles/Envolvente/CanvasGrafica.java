
import java.awt.*;

/**
 * <p>Title: Proyecto Envolvente</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.util.*;

public class CanvasGrafica extends Canvas
{
    private Vector proyectiles = null;
    public static final int GLOBAL = 0;
    public static final int LOCAL = 1;
    private int tipoGrafica = GLOBAL;
    private int minX = 0, minY = 0, maxX = 5, maxY = 5;
    private Color colorEjes = Color.black;
    private Color colorEtiq = Color.black;
    private Color colorProy = Color.red;
    private Color colorElipse = Color.blue;
    private Color colorTirador = Color.magenta;
    private Color colorEnvolvente = Color.green;

    private boolean trazaElipse = false;
    private boolean trazaEnvolvente = false;
    private Vector elipse = new Vector ();
    private Vector envolvente = new Vector ();
    private Tirador tirador = null;

    public CanvasGrafica()
    {
    }

    public void setDatos (Vector p, int t, boolean b, boolean e)
    {
        proyectiles = p;
        tipoGrafica = t;
        trazaElipse = b;
        trazaEnvolvente = e;

        obtenMaximos ();
        if (trazaElipse)
            calculaElipse ();

        if (trazaEnvolvente)
            calculaEnvolvente ();

        repaint ();
    }

    public void setTirador (Tirador t)
    {
        tirador = t;
        repaint ();
    }

    public void limpia ()
    {
        proyectiles = null;
        trazaElipse = false;
        trazaEnvolvente = false;
        repaint ();
    }

    public void paint (Graphics g)
    {
        pintaProyectiles (g);
    }

    private void pintaProyectiles (Graphics g)
    {
        int w = this.getSize().width;
        int h = this.getSize().height;
        int xi, yi, xf, yf, x0, y0;

        // minimo en 1/10
        xi = w / 10;
        yi = (9 * h) / 10;

        // finales en 9/10
        xf = (9 * w) / 10;
        yf = h / 10;

        // determina el origen
        x0 = mapX (0, xi, xf, minX, maxX);
        y0 = mapY (0, yi, yf, minY, maxY);

        // pinta los ejes
        g.setColor(colorEjes);
        g.drawLine(xi, yi, xf, yi);

        // si el origen está fuera del rango que se dibuja
        if (0 < minX || 0 > maxX)
            g.drawLine(xi, yi, xi, yf);
        else
            g.drawLine(x0, yi, x0, yf);

/*
        // pinta las rayas en x
        int step = (int) ((xf - x0) / 10);
        for (int i = 1; i < 11; i++)
            g.drawLine (x0 + step * i, y0 - 3, x0 + step * i, y0 + 3);

        // pinta las rayas en y
        step = (int) ((yf - y0) / 10);
        for (int i = 1; i < 11; i++)
            g.drawLine (x0 - 3, y0 + step * i, x0 + 3, y0 + step * i);
*/

        pintaEtiquetas (g, xi, yi, x0, y0, xf, yf);

        pintaTirador (g, xi, yi, xf, yf);

        // pinta los proyectiles
        if (proyectiles != null && proyectiles.size() > 0) {
            for (int i = 0; i < proyectiles.size (); i++) {
                Proyectil p = (Proyectil) proyectiles.elementAt (i);
                pintaProyectil (g, xi, yi, xf, yf, p, colorProy);
            }
        }

        if (trazaElipse)
            pintaElipse (g, xi, yi, xf, yf);

        if (trazaEnvolvente)
            pintaEnvolvente (g, xi, yi, xf, yf);
    }

    private void pintaEtiquetas (Graphics g, int xi, int yi, int x0, int y0, int xf, int yf)
    {
        g.setColor (colorEtiq);
        Font f = g.getFont();
        g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
        g.drawString (minX + "", xi + 5, y0 + 10);
        g.drawString (maxX + "", xf - 5, y0 + 10);

        // si el origen está fuera del rango que se dibuja
        if (0 < minX || 0 > maxX)
            g.drawString (maxY + "", xi - 25, yf + 10);
        else
            g.drawString (maxY + "", x0 - 25, yf + 10);

        // pinta el 0
        if (minX != 0 && maxX != 0)
            g.drawString("0", x0 - 5, y0 + 10);
        g.setFont (f);
    }

    private void pintaTirador (Graphics g, int xi, int yi, int xf, int yf)
    {
        if (tirador == null)
            return;

        g.setColor(colorTirador);
        int xt = mapX (tirador.getX(), xi, xf, minX, maxX);
        int yt = mapY (tirador.getY(), yi, yf, minY, maxY);

        g.drawLine (xt - 4, yt, xt + 4, yt);
        g.drawLine (xt, yt - 4, xt, yt + 4);
    }

    private void pintaProyectil (Graphics g, int xi, int yi, int xf, int yf, Proyectil p, Color c)
    {
        int x_old, y_old, x_new, y_new;

        if (p == null)
            return;

        Vector datosXY = p.DatosXY();

        // pinta la gráfica
        g.setColor (c);

        DatosXY d = (DatosXY) datosXY.elementAt(0);
        x_old = mapX (d.getX(), xi, xf, minX, maxX);
        y_old = mapY (d.getY(), yi, yf, minY, maxY);

        for (int i = 1; i < datosXY.size(); i++) {
            d = (DatosXY) datosXY.elementAt(i);

            x_new = mapX (d.getX(), xi, xf, minX, maxX);
            y_new = mapY (d.getY(), yi, yf, minY, maxY);

            g.drawLine(x_old, y_old, x_new, y_new);

            x_old = x_new;
            y_old = y_new;
        }
    }

    private void calculaElipse ()
    {
        elipse.setSize(0);
        if (proyectiles != null && proyectiles.size() > 0) {
            for (int i = 0; i < proyectiles.size (); i++) {
                Proyectil p = (Proyectil) proyectiles.elementAt (i);
                double x = p.PosXMaximo();
                double y = p.AlturaMaxima();
                elipse.addElement (new DatosXY (x, y));
            }
        }
    }

    private void calculaEnvolvente ()
    {
        envolvente.setSize(0);
        if (proyectiles != null && proyectiles.size() > 0) {
            double democracia = 0.01;
            Proyectil p = (Proyectil) proyectiles.elementAt(0);
            double v0 = p.VelInicial();
            double g = p.getG ();
            double x0 = p.getX0();
            double y0 = p.getY0();

            for (double x = minX; x < maxX; x += democracia) {
                double dx = x - x0;
                double y = (v0 * v0) / (2 * g) - (g * dx * dx) / (2 * v0 * v0) + y0;

                envolvente.addElement (new DatosXY (x, y));
            }
        }
    }

    private void pintaElipse (Graphics g, int xi, int yi, int xf, int yf)
    {
        int x_old, y_old, x_new, y_new;

        g.setColor(colorElipse);

        DatosXY d = (DatosXY) elipse.elementAt(0);
        x_old = mapX (d.getX(), xi, xf, minX, maxX);
        y_old = mapY (d.getY(), yi, yf, minY, maxY);

        for (int i = 1; i < elipse.size(); i++) {
            d = (DatosXY) elipse.elementAt(i);

            x_new = mapX (d.getX(), xi, xf, minX, maxX);
            y_new = mapY (d.getY(), yi, yf, minY, maxY);

            g.drawLine(x_old, y_old, x_new, y_new);

            x_old = x_new;
            y_old = y_new;
        }
    }

    private void pintaEnvolvente (Graphics g, int xi, int yi, int xf, int yf)
    {
        int x_old, y_old, x_new, y_new;

        g.setColor(colorEnvolvente);

        DatosXY d = (DatosXY) envolvente.elementAt(0);
        x_old = mapX (d.getX(), xi, xf, minX, maxX);
        y_old = mapY (d.getY(), yi, yf, minY, maxY);

        for (int i = 1; i < envolvente.size(); i++) {
            d = (DatosXY) envolvente.elementAt(i);

            x_new = mapX (d.getX(), xi, xf, minX, maxX);
            y_new = mapY (d.getY(), yi, yf, minY, maxY);

            g.drawLine(x_old, y_old, x_new, y_new);

            x_old = x_new;
            y_old = y_new;
        }
    }

    private int mapX (double xn, int xi, int xf, int minX, int maxX)
    {
        return (int) ((xn - minX) / (maxX - minX) * (xf - xi) + xi);
    }

    private int mapY (double yn, int yi, int yf, int minY, int maxY)
    {
        return (int) ((1 - (yn - minY) / (maxY - minY)) * (yi - yf) + yf);
    }

    private void obtenMaximos ()
    {
        double miX = 0, miY = 0, mxX = 0, mxY = 0;

        if (proyectiles != null && proyectiles.size() > 0) {
            Proyectil p = (Proyectil) proyectiles.elementAt (0);
            miX = p.AlcanceReal ();
            mxX = p.AlcanceReal ();
            mxY = p.AlturaMaxima ();

            for (int i = 1; i < proyectiles.size (); i++) {
                p = (Proyectil) proyectiles.elementAt (i);

                if (miX > p.AlcanceReal ())
                    miX = p.AlcanceReal ();

                if (mxX < p.AlcanceReal ())
                    mxX = p.AlcanceReal ();

                if (mxY < p.AlturaMaxima ())
                    mxY = p.AlturaMaxima ();
            }
        }

        if (tirador != null) {
            if (tirador.getX() < miX)
                miX = tirador.getX();

            if (tirador.getX() > mxX)
                mxX = tirador.getX();

            if (tirador.getY() < miY)
                miY = tirador.getY();

            if (tirador.getY() > mxY)
                mxY = tirador.getY();
        }

        maxX = (int) mxX;
        maxY = (int) (mxY + 1);
        minX = (int) miX;
        minY = 0;

        if (tipoGrafica == GLOBAL) {
            minX = Math.min (0, minX);
            maxX = Math.max (0, maxX);
        }

        if (minX != 0)
            minX--;

        if (maxX != 0)
            maxX++;
    }
}
