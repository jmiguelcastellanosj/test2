
import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.*;
import java.util.*;

/**
 * <p>Title: Proyecto Tirador</p>
 * <p>Description: Simula tiro a un blanco fijo</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class CanvasTirador extends Canvas
{
    private Proyectil p = null;
    private Blanco b = null;
    private Tirador t = null;
    private Proyectil pc = null, p1 = null, p2 = null;

    private static final int SENCILLA = 0;
    private static final int CRITICA = 1;

    private int TipoGrafica = SENCILLA;
    private Color colorEjes = Color.black;
    private Color colorProy = Color.blue;
    private Color colorEtiq = Color.black;
    private Color colorBlanco = Color.red;
    private Color colorTirador = Color.magenta;
    private Color colorProyCrit = Color.orange;
    private Color colorProyP1 = Color.blue;
    private Color colorProyP2 = Color.red;

    private int maxX = 5, maxY = 5, minX = 0, minY = 0;

    public CanvasTirador ()
    {
        super ();
    }

    public CanvasTirador(GraphicsConfiguration p0)
    {
        super(p0);
    }

    public void setDatos (Proyectil p, Blanco b, Tirador t)
    {
        this.p = p;
        this.b = b;
        this.t = t;
        this.TipoGrafica = SENCILLA;

        updateMayores ();
    }

    public void setDatos (Proyectil pc, Proyectil p1, Proyectil p2, Tirador t, Blanco b)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.pc = pc;
        this.b = b;
        this.t = t;
        this.TipoGrafica = CRITICA;

        updateMayores ();
    }

    public void setTirador (Tirador t)
    {
        this.t = t;
        updateMayores ();
        repaint ();
    }

    public void setBlanco (Blanco b)
    {
        this.b = b;
        updateMayores ();
        repaint ();
    }

    public void limpia ()
    {
        p = null;
        TipoGrafica = SENCILLA;

        updateMayores ();

        repaint ();
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

        // pinta etiquetas
        pintaEtiquetas (g, x0, y0, xf, yf);

        // pinta al tirador
        pintaTirador (g, x0, y0, xf, yf);

        // dibuja la gráfica
        if (TipoGrafica == SENCILLA)
            graficaXY (g, x0, y0, xf, yf, p);
        else
            graficaCritica (g, x0, y0, xf, yf);

        // pinta el blanco
        pintaBlanco (g, x0, y0, xf, yf);
    }

    private void pintaEtiquetas (Graphics g, int x0, int y0, int xf, int yf)
    {
        g.setColor (colorEtiq);
        Font f = g.getFont();
        g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
        g.drawString (maxX + "", xf - 10, y0 + 10);
        g.drawString (maxY + "", x0 - 25, yf + 10);

        // pinta el 0
        g.drawString("0", x0 - 5, y0 + 10);
        g.setFont (f);
    }

    private void graficaXY (Graphics g, int x0, int y0, int xf, int yf, Proyectil p)
    {
        pintaProyectil (g, x0, y0, xf, yf, p, colorProy);
    }

    private void graficaCritica (Graphics g, int x0, int y0, int xf, int yf)
    {
        pintaProyectil (g, x0, y0, xf, yf, pc, colorProyCrit);
        if (pc != null) {
            g.drawLine (xf - 4, yf - 3, xf - 14, yf - 3);
            Font f = g.getFont ();
            g.setFont (new Font ("Times Roman", Font.PLAIN, 10));
            g.setColor (Color.black);
            g.drawString ("Trayectoria Crítica", xf - 2, yf);
            g.setFont (f);
        }

        pintaProyectil (g, x0, y0, xf, yf, p1, colorProyP1);
        pintaProyectil (g, x0, y0, xf, yf, p2, colorProyP2);
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

    private void pintaBlanco (Graphics g, int x0, int y0, int xf, int yf)
    {
        if (b == null)
            return;

        g.setColor(colorBlanco);
        int x_blanco = mapX (b.getX(), x0, xf, minX, maxX);
        int y_blanco = mapY (b.getY(), y0, yf, minY, maxY);
        g.drawOval (x_blanco, y_blanco, 1, 1);
        g.drawOval (x_blanco - 2, y_blanco - 2, 6, 6);
    }

    private void pintaTirador (Graphics g, int x0, int y0, int xf, int yf)
    {
        if (t == null)
            return;

        g.setColor(colorTirador);
        int xt = mapX (t.getX(), x0, xf, minX, maxX);
        int yt = mapY (t.getY(), y0, yf, minY, maxY);

        g.drawLine (xt - 4, yt, xt + 4, yt);
        g.drawLine (xt, yt - 4, xt, yt + 4);
    }

    private int mapX (double xi, int x0, int xf, int minX, int maxX)
    {
        return (int) ((xi - minX) / (maxX - minX) * (xf - x0) + x0);
    }

    private int mapY (double yi, int y0, int yf, int minY, int maxY)
    {
        return (int) ((1 - (yi - minY) / (maxY - minY)) * (y0 - yf) + yf);
    }

    private void updateMayores ()
    {
        minX = minY = 0;
        double mX = 0, mY = 0;

        if (TipoGrafica == SENCILLA) {
            if (p != null) {
                mX = p.AlcanceReal ();
                mY = p.AlturaMaxima ();
            }
        }
        else {
            // Saca el máximo de p1
            if (p1 != null) {
                mX = p1.AlcanceReal ();
                mY = p1.AlturaMaxima ();
            }

            // Saca el máximo de p2
            if (p2 != null) {
                if (mX < p2.AlcanceReal ())
                    mX = p2.AlcanceReal ();
                if (mY < p2.AlturaMaxima ())
                    mY = p2.AlturaMaxima ();
            }

            // Saca el máximo de pc
            if (pc != null) {
                if (mX < pc.AlcanceReal ())
                    mX = pc.AlcanceReal ();
                if (mY < pc.AlturaMaxima ())
                    mY = pc.AlturaMaxima ();
            }
        }

        if (b != null) {
            if (b.getX () > mX)
                mX = b.getX ();

            if (b.getY () > mY)
                mY = b.getY ();
        }

        if (t != null) {
            if (t.getX() > mX)
                mX = t.getX();

            if (t.getY() > mY)
                mY = t.getY();
        }

        maxX = (int) (mX + 1);
        maxY = (int) (mY + 1);
    }

}
