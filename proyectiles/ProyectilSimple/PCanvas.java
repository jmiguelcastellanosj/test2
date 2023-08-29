
import java.awt.*;
import java.util.*;

/**
 * <p>Title: PCanvas</p>
 * <p>Description: Canvas para dibujar las gráficas del proyectil</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class PCanvas extends Canvas {
    private Frame padre;
    private Proyectil p1, p2;
    private int tipo_grafica = 0;
    private Color colorEjes = Color.black;
    private Color colorProy1 = Color.blue;
    private Color colorProy2 = Color.red;
    private Color colorEtiq = Color.black;
    private DatosV v1, v2;
    private DatosXY x1, x2;
    private int maxX, maxY, minX, minY,
        maxT, minT, maxVx, minVx, maxVy, minVy, maxX2, minX2, maxY2, minY2;

    public static final int GRAFICA_XY = 0;
    public static final int GRAFICA_TX = 1;
    public static final int GRAFICA_TY = 2;
    public static final int GRAFICA_TVX = 3;
    public static final int GRAFICA_TVY = 4;

  public PCanvas(Frame p)
  {
      super ();
      padre = p;

      try {
          jbInit();
      }
      catch(Exception e) {
          e.printStackTrace();
      }
  }

  public void setDatos (Proyectil p1, Proyectil p2)
  {
      this.p1 = p1;
      this.p2 = p2;

// Saca los máximos
      v1 = null; x1 = null;
      if (p1 != null) {
          v1 = getMaxDatosV (p1.DatosV ());
          x1 = getMaxDatosXY (p1.DatosXY ());
      }

      v2 = null; x2 = null;
      if (p2 != null) {
          v2 = getMaxDatosV (p2.DatosV ());
          x2 = getMaxDatosXY (p2.DatosXY ());
      }

      maxX = maxY = minX = minY = 0;
      maxT = minT = maxVx = minVx = maxVy = minVy = maxX2 = minX2 = maxY2 = minY2 = 0;
      if (p1 != null) {
          maxX = (int) x1.getX();
          maxY = (int) x1.getY();

          maxT = (int) v1.getT();
          maxVx = (int) v1.getVx();
          maxVy = (int) v1.getVy();
          maxX2 = (int) v1.getX();
          maxY2 = (int) v1.getY();

          if (p2 != null) {
              maxX = (maxX > x2.getX() ? maxX : (int) x2.getX());
              maxY = (maxY > x2.getY() ? maxY : (int) x2.getY());

              maxT = (maxT > v2.getT() ? maxT : (int) v2.getT());
              maxVx = (maxVx > v2.getVx() ? maxVx : (int) v2.getVx());
              maxVy = (maxVy > v2.getVy() ? maxVy : (int) v2.getVy());
              maxX2 = (maxX2 > v2.getX() ? maxX2 : (int) v2.getX());
              maxY2 = (maxY2 > v2.getY() ? maxY2 : (int) v2.getY());
          }
      }
      else {
          if (p2 != null) {
              maxX = (int) x2.getX();
              maxY = (int) x2.getY();

              maxT = (int) v2.getT();
              maxVx = (int) v2.getVx();
              maxVy = (int) v2.getVy();
              maxX2 = (int) v2.getX();
              maxY2 = (int) v2.getY();
          }
      }

  }

  private DatosV getMaxDatosV (Vector datosV)
  {
      DatosV dv = (DatosV) datosV.elementAt (0);

// Saca el máximo de cada variable

      double mT = dv.getT();
      double mX = dv.getX();
      double mY = dv.getY();
      double mVx = dv.getVx();
      double mVy = dv.getVy();

      for (int i = 1; i < datosV.size(); i++) {
          dv = (DatosV) datosV.elementAt(i);

          if (dv.getT() > mT)
              mT = dv.getT();

          if (dv.getX() > mX)
              mX = dv.getX();

          if (dv.getY() > mY)
              mY = dv.getY();

          if (dv.getVx() > mVx)
              mVx = dv.getVx();

          if (dv.getVy() > mVy)
              mVy = dv.getVy();
      }

      return new DatosV (mT + 1, mVx + 1, mVy + 1, mX + 1, mY + 1);
  }

  private DatosXY getMaxDatosXY (Vector datosXY)
  {

      DatosXY dxy = (DatosXY) datosXY.elementAt (0);

// Saca el máximo en cada variable
      double maxX = dxy.getX();
      double maxY = dxy.getY();

      for (int i = 1; i < datosXY.size (); i++) {
          dxy = (DatosXY) datosXY.elementAt(i);

          if (dxy.getX() > maxX)
              maxX = dxy.getX();

          if (dxy.getY() > maxY)
              maxY = dxy.getY();

      }

      return new DatosXY (maxX + 1, maxY + 1);
  }

  public void setTipoGrafica (int t)
  {
      tipo_grafica = t;

      switch (tipo_grafica) {
          case GRAFICA_XY:
              padre.setTitle("Gráfica y(x)");
              break;

          case GRAFICA_TX:
              padre.setTitle("Gráfica x(t)");
              break;

          case GRAFICA_TY:
              padre.setTitle("Gráfica y(t)");
              break;

          case GRAFICA_TVX:
              padre.setTitle("Gráfica Vx(t)");
              break;

          case GRAFICA_TVY:
              padre.setTitle("Gráfica Vy(t)");
              break;
      }
  }

  public void paint (Graphics g)
  {
      int w = this.getSize().width;
      int h = this.getSize().height;
      boolean es_4 = false;
      GPoint gp = new GPoint ();

// inicio en 1/10
      gp.xi = w / 10;
      gp.yi = (9 * h) / 10;

// origen en w/2 9h/10
      gp.x0 = w / 2;
      if (tipo_grafica == GRAFICA_XY || tipo_grafica == GRAFICA_TY)
          gp.y0 = gp.yi;
// origen en w/2 h/2
      else {
          gp.y0 = h / 2;
          es_4 = true;
      }

// finales en 9/10
      gp.xf = (9 * w) / 10;
      gp.yf = h / 10;

// pinta los ejes
      g.setColor(colorEjes);
      g.drawLine(gp.xi, gp.y0, gp.xf, gp.y0);
      g.drawLine(gp.x0, gp.yi, gp.x0, gp.yf);

// pinta las rayas en x
      int step = (int) ((gp.xf - gp.x0) / 10);
      for (int i = 1; i < 11; i++) {
          g.drawLine (gp.x0 + step * i, gp.y0 - 3, gp.x0 + step * i, gp.y0 + 3);
      }

      for (int i = 1; i < 11; i++) {
          g.drawLine (gp.x0 - step * i, gp.y0 - 3, gp.x0 - step * i, gp.y0 + 3);
      }

// pinta las rayas en y
      step = (int) ((gp.yf - gp.y0) / 10);
      for (int i = 1; i < 11; i++) {
          g.drawLine (gp.x0 - 3, gp.y0 + step * i, gp.x0 + 3, gp.y0 + step * i);
      }

      if (es_4)
          for (int i = 1; i < 11; i++) {
              g.drawLine (gp.x0 - 3, gp.y0 - step * i, gp.x0 + 3, gp.y0 - step * i);
          }

// pinta el 0
      g.drawString("0", gp.x0 - 10, gp.y0 + 15);

// dibuja la gráfica

    switch (tipo_grafica) {

        case GRAFICA_XY:
            graficaXY (g, gp, p1, colorProy1);
            graficaXY (g, gp, p2, colorProy2);
            break;

        case GRAFICA_TX:
            graficaTX (g, gp, p1, colorProy1);
            graficaTX (g, gp, p2, colorProy2);
            break;

        case GRAFICA_TY:
            graficaTY (g, gp, p1, colorProy1);
            graficaTY (g, gp, p2, colorProy2);
            break;

        case GRAFICA_TVX:
            graficaTVx (g, gp, p1, colorProy1);
            graficaTVx (g, gp, p2, colorProy2);
            break;

        case GRAFICA_TVY:
            graficaTVy (g, gp, p1, colorProy1);
            graficaTVy (g, gp, p2, colorProy2);
            break;
    }
  }

  private void graficaXY (Graphics g, GPoint gp, Proyectil p, Color c)
  {
      int x_old, y_old, x_new, y_new;

      if (p == null)
          return;

      Vector datosXY = p.DatosXY();

// pinta las etiquetas
      g.setColor (colorEtiq);
      Font f = g.getFont();
      g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
      g.drawString (maxX + "", gp.xf - 10, gp.y0 + 20);
      g.drawString (maxY + "", gp.x0 - 25, gp.yf + 10);
      g.setFont (f);

// pinta la gráfica
      g.setColor (c);

      DatosXY d = (DatosXY) datosXY.elementAt(0);
      x_old = mapX (d.getX(), gp.x0, gp.xf, minX, maxX);
      y_old = mapY (d.getY(), gp.y0, gp.yf, minY, maxY);

      for (int i = 1; i < datosXY.size(); i++) {
          d = (DatosXY) datosXY.elementAt(i);

          x_new = mapX (d.getX(), gp.x0, gp.xf, minX, maxX);
          y_new = mapY (d.getY(), gp.y0, gp.yf, minY, maxY);

          g.drawLine(x_old, y_old, x_new, y_new);

          x_old = x_new;
          y_old = y_new;
      }
  }

  private void graficaTX (Graphics g, GPoint gp, Proyectil p, Color c)
  {
      int x_old, y_old, x_new, y_new;

      if (p == null)
          return;

      Vector datosV = p.DatosV();

// pinta las etiquetas
      g.setColor(colorEtiq);
      Font f = g.getFont();
      g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
      g.drawString(maxT + "", gp.xf - 10, gp.y0 + 20);
      g.drawString(maxX2 + "", gp.x0 - 25, gp.yf + 10);
      g.setFont(f);

// pinta la gráfica
      g.setColor(c);

      DatosV dv = (DatosV) datosV.elementAt(0);
      x_old = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
      y_old = mapY (dv.getX(), gp.y0, gp.yf, minX2, maxX2);

      for (int i = 1; i < datosV.size(); i++) {
          dv = (DatosV) datosV.elementAt(i);

          x_new = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
          y_new = mapY (dv.getX(), gp.y0, gp.yf, minX2, maxX2);

          g.drawLine(x_old, y_old, x_new, y_new);

          x_old = x_new;
          y_old = y_new;
      }
  }

  private void graficaTY (Graphics g, GPoint gp, Proyectil p, Color c)
  {
      int x_old, y_old, x_new, y_new;

      if (p == null)
          return;

      Vector datosV = p.DatosV();

// pinta las etiquetas
      g.setColor(colorEtiq);
      Font f = g.getFont();
      g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
      g.drawString(maxT + "", gp.xf - 10, gp.y0 + 20);
      g.drawString(maxY2 + "", gp.x0 - 25, gp.yf + 10);
      g.setFont(f);

// pinta la gráfica
      g.setColor(c);

      DatosV dv = (DatosV) datosV.elementAt(0);
      x_old = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
      y_old = mapY (dv.getY(), gp.y0, gp.yf, minY2, maxY2);

      for (int i = 1; i < datosV.size(); i++) {
          dv = (DatosV) datosV.elementAt(i);

          x_new = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
          y_new = mapY (dv.getY(), gp.y0, gp.yf, minY2, maxY2);

          g.drawLine(x_old, y_old, x_new, y_new);

          x_old = x_new;
          y_old = y_new;
      }
  }

  private void graficaTVx (Graphics g, GPoint gp, Proyectil p, Color c)
  {
      int x_old, y_old, x_new, y_new;

      if (p == null)
          return;

      Vector datosV = p.DatosV();

// pinta las etiquetas
      g.setColor(colorEtiq);
      Font f = g.getFont();
      g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
      g.drawString(maxT + "", gp.xf - 10, gp.y0 + 20);
      g.drawString(maxVx + "", gp.x0 - 25, gp.yf + 10);
      g.setFont(f);

// pinta la gráfica
      g.setColor(c);

      DatosV dv = (DatosV) datosV.elementAt(0);
      x_old = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
      y_old = mapY (dv.getVx(), gp.y0, gp.yf, minVx, maxVx);

      for (int i = 1; i < datosV.size(); i++) {
          dv = (DatosV) datosV.elementAt(i);

          x_new = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
          y_new = mapY (dv.getVx(), gp.y0, gp.yf, minVx, maxVx);

          g.drawLine(x_old, y_old, x_new, y_new);

          x_old = x_new;
          y_old = y_new;
      }
  }

  private void graficaTVy (Graphics g, GPoint gp, Proyectil p, Color c)
  {
      int x_old, y_old, x_new, y_new;

      if (p == null)
          return;

      Vector datosV = p.DatosV();

      DatosV dv = (DatosV) datosV.elementAt(0);
      x_old = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
      y_old = mapY (dv.getVy(), gp.y0, gp.yf, minVy, maxVy);

// pinta las etiquetas
      g.setColor(colorEtiq);
      Font f = g.getFont();
      g.setFont(new Font ("Times Roman", Font.PLAIN, 12));
      g.drawString(maxT + "", gp.xf - 10, gp.y0 + 20);
      g.drawString(maxVy + "", gp.x0 - 25, gp.yf + 10);
      g.setFont(f);

// pinta la gráfica
      g.setColor(c);
      for (int i = 1; i < datosV.size(); i++) {
          dv = (DatosV) datosV.elementAt(i);

          x_new = mapX (dv.getT(), gp.x0, gp.xf, minT, maxT);
          y_new = mapY (dv.getVy(), gp.y0, gp.yf, minVx, maxVy);

          g.drawLine(x_old, y_old, x_new, y_new);

          x_old = x_new;
          y_old = y_new;
      }
  }

  private int mapX (double xi, int x0, int xf, int minX, int maxX)
  {
      return (int) ((xi - minX) / (maxX - minX) * (xf - x0) + x0);
  }

  private int mapY (double yi, int y0, int yf, int minY, int maxY)
  {
      return (int) ((1 - (yi - minY) / (maxY - minY)) * (y0 - yf) + yf);
  }

  private void jbInit() throws Exception {
    this.setBackground(Color.white);
  }
}
