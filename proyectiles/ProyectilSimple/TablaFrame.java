
import java.awt.*;
import java.awt.event.*;
import java.text.*;

/**
 * <p>Title: Proyectil Simple</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class TablaFrame extends Frame
{
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    TextArea taTabla = new TextArea(80, 15);
    Proyectil p1, p2;

    public TablaFrame(Proyectil p1, Proyectil p2)
    {
        super ("Resultados");
        this.p1 = p1;
        this.p2 = p2;

        try
        {
            jbInit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception
    {
        this.setLayout(gridBagLayout1);
        this.addWindowListener(new TablaFrame_this_windowAdapter(this));
        taTabla.setText("");
        this.setTitle("Resultados");
        this.add(taTabla,               new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.setSize(400, 300);
        this.setLocation(100, 100);
        llenaTabla ();
    }

    private void llenaTabla ()
    {
        StringBuffer r = new StringBuffer ();
        DecimalFormat df0 = new DecimalFormat ("####");
        DecimalFormat df2 = new DecimalFormat ("####.00");

        df0.setMinimumIntegerDigits (1);
        df2.setMinimumIntegerDigits (1);
        taTabla.setText("");

        if (p1 != null) {
            r.append ("*** Proyectil 1 ***\n\n");
            r.append ("Velocidad y posición contra el tiempo\n\n");
            r.append("         t          Vx      Vy            x          y\n");

            for (int i = 0; i < p1.DatosV().size(); i++) {

                DatosV dv = (DatosV) p1.DatosV().elementAt(i);

                r.append (fill (df2.format (dv.getT()), 8) +
                          fill (df2.format (dv.getVx()), 8) +
                          fill (df2.format (dv.getVy()), 8) +
                          fill (df2.format (dv.getX()), 8) +
                          fill (df2.format (dv.getY()), 8) + "\n");

            }
        }

        if (p2 != null) {
            r.append ("\n\n*** Proyectil 2 ***\n\n");
            r.append ("Velocidad y posición contra el tiempo\n\n");
            r.append("         t          Vx      Vy            x          y\n");

            for (int i = 0; i < p2.DatosV().size(); i++) {

                DatosV dv = (DatosV) p2.DatosV().elementAt(i);

                r.append (fill (df2.format (dv.getT()), 8) +
                          fill (df2.format (dv.getVx()), 8) +
                          fill (df2.format (dv.getVy()), 8) +
                          fill (df2.format (dv.getX()), 8) +
                          fill (df2.format (dv.getY()), 8) + "\n");

            }
        }

        taTabla.setText(r.toString());
    }

    private String fill (String s, int k)
    {
        while (s.length () < k)
            s = " " + s;
        return s;
    }

    void this_windowClosing(WindowEvent e)
    {
        dispose ();
    }
}

class TablaFrame_this_windowAdapter extends java.awt.event.WindowAdapter
{
    TablaFrame adaptee;

    TablaFrame_this_windowAdapter(TablaFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void windowClosing(WindowEvent e)
    {
        adaptee.this_windowClosing(e);
    }
}
