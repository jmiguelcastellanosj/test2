
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: Proyectil Simple</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class CFrame extends Frame
{
    Label lblPlaneta = new Label();
    Choice chPlaneta = new Choice();
    Label lblG = new Label();
    TextField tfG = new TextField();
    Button btnCerrar = new Button();

    public CFrame()
    {
        super ();
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
        lblPlaneta.setText("Planeta:");
        lblPlaneta.setBounds(new Rectangle(42, 33, 47, 15));
        this.setTitle("Configuración");
        this.addWindowListener(new CFrame_this_windowAdapter(this));
        this.setLayout(null);
        chPlaneta.setBounds(new Rectangle(99, 32, 27, 22));
        lblG.setForeground(Color.black);
        lblG.setText("G:");
        lblG.setBounds(new Rectangle(203, 40, 28, 15));
        tfG.setEditable(false);
        tfG.setBounds(new Rectangle(241, 35, 58, 22));
        btnCerrar.setLabel("button1");
        btnCerrar.setBounds(new Rectangle(156, 245, 71, 25));
        btnCerrar.addActionListener(new CFrame_btnCerrar_actionAdapter(this));
        this.add(lblPlaneta, null);
        this.add(chPlaneta, null);
        this.add(lblG, null);
        this.add(tfG, null);
        this.add(btnCerrar, null);

        chPlaneta.add("Tierra");
        chPlaneta.add("Luna");
        chPlaneta.add("Sol");
        chPlaneta.add("Mercurio");
        chPlaneta.add("Venus");
        chPlaneta.add("Marte");
        chPlaneta.add("Júpiter");
        chPlaneta.add("Saturno");
        chPlaneta.add("Neptuno");
        chPlaneta.add("Urano");
        chPlaneta.add("Plutón");
        chPlaneta.add("otro");
    }

    void this_windowClosing(WindowEvent e)
    {
        dispose ();
    }

    void btnCerrar_actionPerformed(ActionEvent e)
    {
        dispose ();
    }
}

class CFrame_this_windowAdapter extends java.awt.event.WindowAdapter
{
    CFrame adaptee;

    CFrame_this_windowAdapter(CFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void windowClosing(WindowEvent e)
    {
        adaptee.this_windowClosing(e);
    }
}

class CFrame_btnCerrar_actionAdapter implements java.awt.event.ActionListener
{
    CFrame adaptee;

    CFrame_btnCerrar_actionAdapter(CFrame adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnCerrar_actionPerformed(e);
    }
}
