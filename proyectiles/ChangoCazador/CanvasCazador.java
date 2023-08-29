
import java.awt.Canvas;

/**
 * <p>Title: Applet del chango y el cazador</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.awt.*;
import java.util.*;

public class CanvasCazador extends Canvas
{
    private DatosXY puntoChoque;
    private Chango chango;
    private Proyectil flecha;
    private Proyectil changoCayendo;

    private Color colorEjes = Color.black;
    private Color colorFlecha = Color.blue;
    private Color colorEtiq = Color.black;
    private Color colorChango = Color.red;
    private Color colorChoque = Color.magenta;
    private Color colorChangoCayendo = Color.orange;

    private int maxX = 5, maxY = 5, minX = 0, minY = 0;

    public CanvasCazador()
    {
    }

    public void paint (Graphics g)
    {
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x0, y0, xf, yf;

        // origen en 1/10
        x0 = w / 10;
        y0 = (9 * h) / 10;

        // finales en 9/10
        xf = (9 * w) / 10;
        yf = h / 10;

        // pinta los ejes
        g.setColor(colorEjes);
        g.drawLine(x0, y0, xf, y0);
        g.drawLine(x0, y0, x0, yf);

        // pinta las rayas en x
        int step = (int) ((xf - x0) / 10);
        for (int i = 1; i < 11; i++)
            g.drawLine (x0 + step * i, y0 - 3, x0 + step * i, y0 + 3);

        // pinta las rayas en y
        step = (int) ((yf - y0) / 10);
        for (int i = 1; i < 11; i++)
            g.drawLine (x0 - 3, y0 + step * i, x0 + 3, y0 + step * i);

        pintaEtiquetas (g, x0, y0, xf, yf);
        pintaProyectil (g, x0, y0, xf, yf, changoCayendo, colorChangoCayendo);
        pintaChango (g, x0, y0, xf, yf);
        pintaProyectil (g, x0, y0, xf, yf, flecha, colorFlecha);
        pintaChoque (g, x0, y0, xf, yf);
    }

    public void setDatos (DatosXY p, Chango c, Proyectil f, Proyectil cc)
    {
        puntoChoque = p;
        chango = c;
        flecha = f;
        changoCayendo = cc;

        updateMayores ();
        repaint ();
    }

    public void setChango (Chango c)
    {
        chango = c;
        updateMayores ();
        repaint ();
    }

    public void limpia ()
    {
        setDatos (null, null, null, null);
    }

    private void updateMayores ()
    {
        minX = minY = 0;
        double mX = 5, mY = 5;

/*
        if (flecha != null) {
            if (flecha.AlcanceReal () > mX)
                mX = flecha.AlcanceReal ();

            if (flecha.AlturaMaxima () > mY)
                mY = flecha.AlturaMaxima ();
        }
*/
        if (changoCayendo != null) {
            if (changoCayendo.AlcanceReal () > mX)
                mX = changoCayendo.AlcanceReal ();

            if (changoCayendo.AlturaMaxima () > mY)
                mY = changoCayendo.AlturaMaxima ();
        }

        if (chango != null) {
            if (chango.getX () > mX)
                mX = chango.getX ();

            if (chango.getY () > mY)
                mY = chango.getY ();
        }

        maxX = (int) (mX + 1);
        maxY = (int) (mY + 1);
    }

    private void pintaEtiquetas (Graphics g, int x0, int y0, int xf, int yf)
    {
        g.setColor (colorEtiq);
        Font f = g.getFont();
        g.setFont(new Font ("Times Roman", Font.PLAIN, 10));
        g.drawString (maxX + "", xf - 10, y0 + 10);
        g.drawString (maxY + "", x0 - 25, yf + 10);
        g.drawString ("0", x0 - 5, y0 + 10);
        g.setFont (f);
    }

    private void pintaChango (Graphics g, int x0, int y0, int xf, int yf)
    {
        if (chango == null)
            return;

        g.setColor (colorChango);
        int x_chango = mapX (chango.getX(), x0, xf, minX, maxX);
        int y_chango = mapY (chango.getY(), y0, yf, minY, maxY);
        g.drawLine (x_chango - 5, y_chango, x_chango + 5, y_chango);
        g.drawLine (x_chango, y_chango - 5, x_chango, y_chango + 5);
    }

    private void pintaChoque (Graphics g, int x0, int y0, int xf, int yf)
    {
        if (puntoChoque == null)
            return;

        g.setColor(colorChoque);
        int xt = mapX (puntoChoque.getX(), x0, xf, minX, maxX);
        int yt = mapY (puntoChoque.getY(), y0, yf, minY, maxY);

        g.drawLine (xt - 5, yt - 5, xt + 5, yt + 5);
        g.drawLine (xt - 5, yt + 5, xt + 5, yt - 5);
    }

    private int mapX (double xi, int x0, int xf, int minX, int maxX)
    {
        return (int) ((xi - minX) / (maxX - minX) * (xf - x0) + x0);
    }

    private int mapY (double yi, int y0, int yf, int minY, int maxY)
    {
        return (int) ((1 - (yi - minY) / (maxY - minY)) * (y0 - yf) + yf);
    }

    private void pintaProyectil (Graphics g, int x0, int y0, int xf, int yf, Proyectil p, Color c)
    {
        int x_old, y_old, x_new, y_new;

        if (p == null)
            return;

        Vector datosXY = p.DatosXY();

        // pinta la gráfica
        g.setColor (c);

        DatosXY d = (DatosXY) datosXY.elementAt(0);
        x_old = mapX (d.getX(), x0, xf, minX, maxX);
        y_old = mapY (d.getY(), y0, yf, minY, maxY);

        for (int i = 1; i < datosXY.size(); i++) {
            d = (DatosXY) datosXY.elementAt(i);

            x_new = mapX (d.getX(), x0, xf, minX, maxX);
            y_new = mapY (d.getY(), y0, yf, minY, maxY);

            g.drawLine(x_old, y_old, x_new, y_new);

            x_old = x_new;
            y_old = y_new;
        }
    }

}
