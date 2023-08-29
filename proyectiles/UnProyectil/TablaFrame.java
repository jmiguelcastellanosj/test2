
import java.awt.event.*;
import java.awt.*;

/**
 * <p>Title: Un Proyectil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

import java.text.*;

public class TablaFrame extends Frame
{
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    TextArea taTabla = new TextArea();

    public TablaFrame() throws HeadlessException
    {
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception
    {
        this.setTitle("Resultados");
        this.setLayout(gridBagLayout1);
        this.addWindowListener(new TablaFrame_this_windowAdapter(this));
        this.setSize(400, 300);
        this.setLocation(150, 100);

        taTabla.setColumns(25);
        taTabla.setEditable(false);
        taTabla.setText("");
        this.add(taTabla,  new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    public void setDatos (Proyectil p)
    {
        StringBuffer r = new StringBuffer ();
        DecimalFormat df0 = new DecimalFormat ("####");
        DecimalFormat df2 = new DecimalFormat ("####.00");

        df0.setMinimumIntegerDigits (1);
        df2.setMinimumIntegerDigits (1);
        taTabla.setText("");

        if (p != null) {
            r.append ("*** Proyectil ***\n\n");
            r.append("         t         Vx         Vy          x          y\n");

            for (int i = 0; i < p.DatosV().size(); i++) {

                DatosV dv = (DatosV) p.DatosV().elementAt(i);

                r.append (fill (df2.format(dv.getT()), 8) +
                          fill (df2.format(dv.getVx()), 8) +
                          fill (df2.format(dv.getVy()), 8) +
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
