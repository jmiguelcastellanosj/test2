
import java.awt.Canvas;

/**
 * <p>Title: Un Proyectil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.awt.*;
import java.util.*;

public class CanvasGrafica extends Canvas
{
    public static final int GLOBAL = 0;
    public static final int LOCAL = 1;

    public static final int GRAFICA_XY = 0;
    public static final int GRAFICA_TX = 1;
    public static final int GRAFICA_TY = 2;
    public static final int GRAFICA_TVX = 3;
    public static final int GRAFICA_TVY = 4;

    private Proyectil proyectil = null;
    private Tirador tirador = null;
    private int modoGrafica = GLOBAL;
    private int tipoGrafica = GRAFICA_XY;
    private int minX = 0, minY = 0, maxX = 5, maxY = 5;
    private Color colorEjes = Color.black;
    private Color colorEtiq = Color.black;
    private Color colorProy = Color.red;
    private Color colorTirador = Color.magenta;

    public CanvasGrafica()
    {
        super ();
    }

    public void setDatos (Proyectil p, Tirador t, int g, int m)
    {
        proyectil = p;
        tirador = t;
        tipoGrafica = g;
        modoGrafica = m;

        updateMayores ();
        repaint ();
    }

    public void setTirador (Tirador t)
    {
        tirador = t;
        proyectil = null;
        updateMayores ();
        repaint ();
    }

    public void setTipoGrafica (int t)
    {
        tipoGrafica = t;
        updateMayores ();
        repaint ();
    }

    public void limpia ()
    {
        setDatos (null, null, GRAFICA_XY, GLOBAL);
    }

    public void paint (Graphics g)
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

        // eje X
        if (0 < minY || 0 > maxY)
            g.drawLine(xi, yi, xf, yi);
        else
            g.drawLine(xi, y0, xf, y0);

        // eje Y
        if (0 < minX || 0 > maxX)
            g.drawLine(xi, yi, xi, yf);
        else
            g.drawLine(x0, yi, x0, yf);

        pintaEtiquetas (g, xi, yi, x0, y0, xf, yf);
        if (tipoGrafica == GRAFICA_XY)
            pintaTirador (g, xi, yi, xf, yf);
        pintaProyectil (g, xi, yi, xf, yf, proyectil, colorProy);
    }

    private void pintaEtiquetas (Graphics g, int xi, int yi, int x0, int y0, int xf, int yf)
    {
        boolean yaPinteCero = false;

        g.setColor (colorEtiq);
        Font f = g.getFont();
        g.setFont(new Font ("Times Roman", Font.PLAIN, 12));

        // pinta máximos y mínimos

        // para X
        if (0 < minY || 0 > maxY) {
            g.drawString (minX + "", xi - 10, yi + 10);
            g.drawString (maxX + "", xf - 10, yi + 10);
        }
        else {
            g.drawString (minX + "", xi - 10, y0 + 10);
            g.drawString (maxX + "", xf - 10, y0 + 10);
        }

        if (minX == 0 || maxX == 0) {
            yaPinteCero = true;
        }

        // para Y
        if (0 < minX || 0 > maxX) {

            if (yaPinteCero) {
                if (minY != 0)
                    g.drawString (minY + "", xi - 10, yi - 2);
            }
            else {
                if (minY == 0)
                    yaPinteCero = true;
                g.drawString (minY + "", xi - 10, yi - 2);
            }

            if (yaPinteCero) {
                if (maxY != 0)
                    g.drawString (maxY + "", xi - 10, yf - 2);
            }
            else {
                if (maxY == 0)
                    yaPinteCero = true;
                g.drawString (maxY + "", xi - 10, yf - 2);
            }

        }
        else {

            if (yaPinteCero) {
                if (minY != 0)
                    g.drawString (minY + "", x0 - 10, yi - 2);
            }
            else {
                if (minY == 0)
                    yaPinteCero = true;
                g.drawString (minY + "", x0 - 10, yi - 2);
            }

            if (yaPinteCero) {
                if (maxY != 0)
                    g.drawString (maxY + "", x0 - 10, yf - 2);
            }
            else {
                if (maxY == 0)
                    yaPinteCero = true;
                g.drawString (maxY + "", x0 - 10, yf - 2);
            }
        }

        // pinta el 0 (si tiene que hacerlo)
        if (!yaPinteCero)
            if (0 > minX && 0 < maxX)
                g.drawString ("0", x0 - 2, yi + 10);
            else if (0 > minY && 0 < maxY)
                g.drawString ("0", xi - 10, y0 - 2);

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

        Vector datosV = p.DatosV();

        // pinta la gráfica
        g.setColor (c);

        DatosV d = (DatosV) datosV.elementAt(0);
        x_old = mapX (myX (d), xi, xf, minX, maxX);
        y_old = mapY (myY (d), yi, yf, minY, maxY);

        for (int i = 1; i < datosV.size(); i++) {
            d = (DatosV) datosV.elementAt(i);

            x_new = mapX (myX (d), xi, xf, minX, maxX);
            y_new = mapY (myY (d), yi, yf, minY, maxY);

            g.drawLine(x_old, y_old, x_new, y_new);

            x_old = x_new;
            y_old = y_new;
        }
    }

    private double myX (DatosV d)
    {
        double retVal = -1;

        if (tipoGrafica == GRAFICA_XY)
            retVal = d.getX();
        else
            retVal = d.getT();

        return retVal;
    }

    private double myY (DatosV d)
    {
        double retVal = -1;

        if (tipoGrafica == GRAFICA_XY || tipoGrafica == GRAFICA_TY)
            retVal = d.getY();
        else if (tipoGrafica == GRAFICA_TX)
            retVal = d.getX();
        else if (tipoGrafica == GRAFICA_TVX)
            retVal = d.getVx();
        else if (tipoGrafica == GRAFICA_TVY)
            retVal = d.getVy();

        return retVal;
    }

    private int mapX (double xn, int xi, int xf, int minX, int maxX)
    {
        return (int) ((xn - minX) / (maxX - minX) * (xf - xi) + xi);
    }

    private int mapY (double yn, int yi, int yf, int minY, int maxY)
    {
        return (int) ((1 - (yn - minY) / (maxY - minY)) * (yi - yf) + yf);
    }

    private void updateMayores ()
    {
        double miX = 0, miY = 0, mxX = 0, mxY = 0;

        if (proyectil != null) {

            // fija las x's
            if (tipoGrafica == GRAFICA_XY)
                miX = mxX = proyectil.AlcanceReal ();

            else {         // la x es el tiempo
                miX = 0;   // y el tiempo nunca es negativo
                mxX = proyectil.TiempoAire();
            }

            // fija las y's
            if (tipoGrafica == GRAFICA_XY || tipoGrafica == GRAFICA_TY) {
                miY = 0; // siempre sobre el suelo
                mxY = proyectil.AlturaMaxima ();
            }

            else if (tipoGrafica == GRAFICA_TX) {
                miY = Math.min (proyectil.getX0 (), proyectil.AlcanceReal ());
                mxY = Math.max (proyectil.getX0 (), proyectil.AlcanceReal ());
            }

            else if (tipoGrafica == GRAFICA_TVX) {
                miY = Math.min (0, proyectil.velInicialX ());
                mxY = Math.max (0, proyectil.velInicialX ());
            }

            else if (tipoGrafica == GRAFICA_TVY) {
                miY = Math.min (proyectil.velInicialY (), proyectil.velFinalY());
                mxY = Math.max (proyectil.velInicialY (), proyectil.velFinalY());
            }
        }

        if (tirador != null && tipoGrafica == GRAFICA_XY) {
            if (tirador.getX() < miX)
                miX = tirador.getX();

            if (tirador.getX() > mxX)
                mxX = tirador.getX();

            if (tirador.getY() > mxY)
                mxY = tirador.getY();
        }

        minX = (int) miX;
        maxX = (int) mxX;

        minY = (int) miY;
        maxY = (int) mxY;

        if (modoGrafica == GLOBAL) {
            minX = Math.min (0, minX);
            maxX = Math.max (0, maxX);

            minY = Math.min (0, minY);
            maxY = Math.max (0, maxY);
        }

        if (minX == 0 && maxX == 0)
            maxX++;
        else {
            if (minX != 0)
                minX--;

            if (maxX != 0)
                maxX++;
        }

        if (minY == 0 && maxY == 0)
            maxY++;
        else {
            if (minY != 0)
                minY--;

            if (maxY != 0)
                maxY++;
        }
    }
}
