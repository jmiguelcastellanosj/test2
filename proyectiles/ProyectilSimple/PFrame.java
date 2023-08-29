
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * <p>Title: PFrame</p>
 * <p>Description: Ventana donde se grafica el proyectil</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class PFrame extends Frame {
    private Proyectil p1, p2;
    private PCanvas pc = new PCanvas (this);
    private MenuBar mb_main = new MenuBar ();
    private Menu mnu_opciones = new Menu ("Opciones");
    private MenuItem mni_g1 = new MenuItem ("Gráfica y(x)");
    private MenuItem mni_g2 = new MenuItem ("Gráfica x(t)");
    private MenuItem mni_g3 = new MenuItem ("Gráfica y(t)");
    private MenuItem mni_g4 = new MenuItem ("Gráfica Vx(t)");
    private MenuItem mni_g5 = new MenuItem ("Gráfica Vy(t)");
    private MenuItem mni_sp = new MenuItem ("-");
    private MenuItem mni_ex = new MenuItem ("Cerrar");

    public PFrame (Proyectil p1, Proyectil p2)
    {
        this ();
        this.p1 = p1;
        this.p2 = p2;
        pc.setDatos (p1, p2);
        pc.repaint();
    }

    public PFrame() {
        super ();
        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception
    {
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setSize(500, 400);
        this.setLocation (250, 150);
        this.setMenuBar(mb_main);

        mni_ex.addActionListener(new PFrame_mni_ex_actionAdapter(this));
        mni_g2.addActionListener(new PFrame_mni_g2_actionAdapter(this));
        mni_g1.addActionListener(new PFrame_mni_g1_actionAdapter(this));
        mni_g3.addActionListener(new PFrame_mni_g3_actionAdapter(this));
        mni_g4.addActionListener(new PFrame_mni_g4_actionAdapter(this));
        mni_g5.addActionListener(new PFrame_mni_g5_actionAdapter(this));
        mb_main.add (mnu_opciones);
        mnu_opciones.add (mni_g1);
        mnu_opciones.add (mni_g2);
        mnu_opciones.add (mni_g3);
        mnu_opciones.add (mni_g4);
        mnu_opciones.add (mni_g5);
        mnu_opciones.add (mni_sp);
        mnu_opciones.add (mni_ex);

        this.setTitle("Gráfica XY");
        this.addWindowListener(new PFrame_this_windowAdapter(this));
        this.add("Center", pc);
    }

  void this_windowClosing(WindowEvent e) {
      this.dispose ();
  }

  void mni_ex_actionPerformed(ActionEvent e) {
      this.dispose ();
  }

  void mni_g1_actionPerformed(ActionEvent e)
  {
      pc.setTipoGrafica (PCanvas.GRAFICA_XY);
      pc.repaint();
  }

  void mni_g2_actionPerformed(ActionEvent e)
  {
      pc.setTipoGrafica(PCanvas.GRAFICA_TX);
      pc.repaint();
  }

  void mni_g3_actionPerformed(ActionEvent e)
  {
      pc.setTipoGrafica(PCanvas.GRAFICA_TY);
      pc.repaint();
  }

  void mni_g4_actionPerformed(ActionEvent e)
  {
      pc.setTipoGrafica(PCanvas.GRAFICA_TVX);
      pc.repaint();
  }

  void mni_g5_actionPerformed(ActionEvent e)
  {
      pc.setTipoGrafica(PCanvas.GRAFICA_TVY);
      pc.repaint();
  }

}

class PFrame_this_windowAdapter extends java.awt.event.WindowAdapter {
  PFrame adaptee;

  PFrame_this_windowAdapter(PFrame adaptee) {
      this.adaptee = adaptee;
  }
  public void windowClosing(WindowEvent e) {
      adaptee.this_windowClosing(e);
  }
}

class PFrame_mni_ex_actionAdapter implements java.awt.event.ActionListener {
    PFrame adaptee;

    PFrame_mni_ex_actionAdapter(PFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.mni_ex_actionPerformed(e);
    }
}

class PFrame_mni_g2_actionAdapter implements java.awt.event.ActionListener
{
    PFrame adaptee;

    PFrame_mni_g2_actionAdapter(PFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.mni_g2_actionPerformed(e);
    }
}

class PFrame_mni_g1_actionAdapter implements java.awt.event.ActionListener
{
    PFrame adaptee;

    PFrame_mni_g1_actionAdapter(PFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.mni_g1_actionPerformed(e);
    }
}

class PFrame_mni_g3_actionAdapter implements java.awt.event.ActionListener
{
    PFrame adaptee;

    PFrame_mni_g3_actionAdapter(PFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.mni_g3_actionPerformed(e);
    }
}

class PFrame_mni_g4_actionAdapter implements java.awt.event.ActionListener
{
    PFrame adaptee;

    PFrame_mni_g4_actionAdapter(PFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.mni_g4_actionPerformed(e);
    }
}

class PFrame_mni_g5_actionAdapter implements java.awt.event.ActionListener
{
    PFrame adaptee;

    PFrame_mni_g5_actionAdapter(PFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.mni_g5_actionPerformed(e);
    }
}
