
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * <p>Title: Proyecto Envolvente</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class AppletEnvolvente extends Applet
{
    private boolean isStandalone = false;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    CanvasGrafica cnvGrafica = new CanvasGrafica ();
    BevelPanel panelDatos = new BevelPanel("Datos", BevelPanel.BEVEL_RAISED);
    BevelPanel panelAcciones = new BevelPanel("Acciones", BevelPanel.BEVEL_RAISED);
    BevelPanel panelOpciones = new BevelPanel("Opciones", BevelPanel.BEVEL_RAISED);
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    Label lblX0 = new Label();
    TextField tfX0 = new TextField();
    Label lblY0 = new Label();
    TextField tfY0 = new TextField();
    Label lblV0 = new Label();
    TextField tfV0 = new TextField();
    Label lblNumProy = new Label();
    TextField tfNumProy = new TextField();
    Button btnGraficar = new Button();
    CheckboxGroup checkboxGroup1 = new CheckboxGroup();
    Checkbox cbVisionGlobal = new Checkbox();
    Checkbox cbLocal = new Checkbox();
    Checkbox cbMaximos = new Checkbox();
    Label lblPlaneta = new Label();
    Choice choicePlaneta = new Choice();
    TextField tfG = new TextField();
    Label lblG = new Label();

    Tirador tirador = null;
    Checkbox cbEnvolvente = new Checkbox();

    //Get a parameter value
    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public AppletEnvolvente()
    {
    }

    //Initialize the applet
    public void init()
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

    //Component initialization
    private void jbInit() throws Exception
    {
        this.setLayout(gridBagLayout1);
        panelDatos.setLayout(gridBagLayout2);
        panelAcciones.setLayout(gridBagLayout3);
        panelOpciones.setLayout(gridBagLayout4);
        lblX0.setText("x0:");
        tfX0.setColumns(5);
        tfX0.setText("");
        tfX0.addFocusListener(new AppletEnvolvente_tfX0_focusAdapter(this));
        tfX0.addActionListener(new AppletEnvolvente_tfX0_actionAdapter(this));
        lblY0.setText("y0:");
        tfY0.setColumns(5);
        tfY0.setText("");
        tfY0.addFocusListener(new AppletEnvolvente_tfY0_focusAdapter(this));
        tfY0.addActionListener(new AppletEnvolvente_tfY0_actionAdapter(this));
        lblV0.setText("v0:");
        tfV0.setColumns(5);
        tfV0.setText("");
        lblNumProy.setText("Número de proyectiles:");
        tfNumProy.setColumns(5);
        tfNumProy.setText("");
        btnGraficar.setLabel("Graficar");
        btnGraficar.addActionListener(new AppletEnvolvente_btnGraficar_actionAdapter(this));
        cbVisionGlobal.setCheckboxGroup(checkboxGroup1);
        cbVisionGlobal.setLabel("Visión Global");
        cbVisionGlobal.setState(true);
        cbVisionGlobal.addItemListener(new AppletEnvolvente_cbVisionGlobal_itemAdapter(this));
        cbLocal.setCheckboxGroup(checkboxGroup1);
        cbLocal.setForeground(Color.black);
        cbLocal.setLabel("Visión Local");
        cbLocal.setLocale(java.util.Locale.getDefault());
        cbLocal.addItemListener(new AppletEnvolvente_cbLocal_itemAdapter(this));
        cbMaximos.setLabel("Lugar geométrico de los máximos");
        cbMaximos.setState(false);
        cbMaximos.addItemListener(new AppletEnvolvente_cbMaximos_itemAdapter(this));
        lblPlaneta.setText("Planeta:");
        tfG.setColumns(5);
        tfG.setEditable(false);
        tfG.setEnabled(true);
        tfG.setText("9.81");
        lblG.setText("g:");
        choicePlaneta.addItemListener(new AppletEnvolvente_choicePlaneta_itemAdapter(this));
        cbEnvolvente.setLabel("Envolvente");
        cbEnvolvente.addItemListener(new AppletEnvolvente_cbEnvolvente_itemAdapter(this));
        this.add(cnvGrafica,       new GridBagConstraints(0, 1, 3, 1, 100.0, 70.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelDatos,      new GridBagConstraints(0, 2, 1, 1, 0.0, 30.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblX0,             new GridBagConstraints(0, 0, 1, 1, 10.0, 33.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelAcciones,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panelAcciones.add(btnGraficar,  new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelOpciones,     new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(tfX0,             new GridBagConstraints(1, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblY0,          new GridBagConstraints(2, 0, 1, 1, 10.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(tfY0,         new GridBagConstraints(3, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblV0,         new GridBagConstraints(4, 0, 1, 1, 10.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(tfV0,       new GridBagConstraints(5, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblNumProy,        new GridBagConstraints(0, 1, 3, 1, 0.0, 33.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(tfNumProy,       new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblPlaneta,  new GridBagConstraints(0, 2, 1, 1, 0.0, 33.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelOpciones.add(cbVisionGlobal,       new GridBagConstraints(0, 0, 1, 1, 100.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelOpciones.add(cbLocal,       new GridBagConstraints(0, 1, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelOpciones.add(cbMaximos,      new GridBagConstraints(0, 2, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelOpciones.add(cbEnvolvente,  new GridBagConstraints(0, 3, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(choicePlaneta, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(tfG,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelDatos.add(lblG, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        choicePlaneta.add("Mercurio");
        choicePlaneta.add("Venus");
        choicePlaneta.add("Tierra");
        choicePlaneta.add("Luna");
        choicePlaneta.add("Marte");
        choicePlaneta.add("Júpiter");
        choicePlaneta.add("Saturno");
        choicePlaneta.add("Urano");
        choicePlaneta.add("Neptuno");
        choicePlaneta.add("Plutón");
        choicePlaneta.add("Otro");
        choicePlaneta.select(2);
    }

    //Start the applet
    public void start()
    {
    }

    //Stop the applet
    public void stop()
    {
    }

    //Destroy the applet
    public void destroy()
    {
    }

    //Get Applet information
    public String getAppletInfo()
    {
        return "Applet Information";
    }

    //Get parameter info
    public String[][] getParameterInfo()
    {
        return null;
    }

    //Main method
    public static void main(String[] args)
    {
        AppletEnvolvente applet = new AppletEnvolvente();
        applet.isStandalone = true;
        Frame frame;
        frame = new Frame();
        frame.setTitle("Applet Frame");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(600,420);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }

    // oidor para el botón de graficar
    void btnGraficar_actionPerformed(ActionEvent e)
    {
        tira ();
    }

    private void tira ()
    {
        double x0, y0, v0, g;
        int numProy;

        try {
            x0 = new Double (tfX0.getText()).doubleValue();
        }
        catch (Exception e) {
            x0 = 0;
        }

        try {
            y0 = new Double (tfY0.getText()).doubleValue();
        }
        catch (Exception e) {
            y0 = 0;
        }

        try {
            v0 = new Double (tfV0.getText()).doubleValue();
        }
        catch (Exception e) {
            v0 = 0;
        }

        try {
            numProy = new Integer (tfNumProy.getText()).intValue();
        }
        catch (Exception e) {
            numProy = 0;
        }

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception e) {
            g = 9.81;
        }

        if (v0 <= 0 || numProy <= 0 || g < 0 || y0 < 0) {
            return;
        }

        Explosion explosion = new Explosion (x0, y0, v0, g, numProy);
        int TipoGrafica = (cbVisionGlobal.getState() ? CanvasGrafica.GLOBAL : CanvasGrafica.LOCAL);
        cnvGrafica.setDatos (explosion.getDatos(), TipoGrafica, cbMaximos.getState(), cbEnvolvente.getState());
    }

    void choicePlaneta_itemStateChanged(ItemEvent e)
    {
        ponG (choicePlaneta, tfG);
    }

    private void ponG (Choice ch, TextField tf)
    {
        tf.setEditable(false);

        switch (ch.getSelectedIndex()) {
            case 0:
                tf.setText("3.70");
                break;

            case 1:
                tf.setText("8.87");
                break;

            case 2:
                tf.setText("9.81");
                break;

            case 3:
                tf.setText("1.62");
                break;

            case 4:
                tf.setText("3.73");
                break;

            case 5:
                tf.setText("25.94");
                break;

            case 6:
                tf.setText("11.18");
                break;

            case 7:
                tf.setText("8.97");
                break;

            case 8:
                tf.setText("9.76");
                break;

            case 9:
                tf.setText("0.64");
                break;

            case 10:
                tf.setEditable(true);
                tf.setText ("0");
                break;
        }
    }

    void tfX0_actionPerformed(ActionEvent e)
    {
        updateXTirador ();
    }

    void tfX0_focusLost(FocusEvent e)
    {
        updateXTirador ();
    }

    void tfY0_actionPerformed(ActionEvent e)
    {
        updateYTirador ();
    }

    void tfY0_focusLost(FocusEvent e)
    {
        updateYTirador ();
    }

    private void updateXTirador ()
    {
        double x0;

        try {
            x0 = new Double (tfX0.getText()).doubleValue();
        }
        catch (Exception e) {
            x0 = 0;
        }

        if (tirador == null)
            tirador = new Tirador (x0, 0);
        else
            tirador.setX (x0);

        cnvGrafica.setTirador (tirador);
    }

    private void updateYTirador ()
    {
        double y0;

        try {
            y0 = new Double (tfY0.getText()).doubleValue();
        }
        catch (Exception e) {
            y0 = 0;
        }

        if (tirador == null)
            tirador = new Tirador (0, y0);
        else
            tirador.setY (y0);

        cnvGrafica.setTirador (tirador);
    }

    void cbVisionGlobal_itemStateChanged(ItemEvent e)
    {
        tira ();
    }

    void cbLocal_itemStateChanged(ItemEvent e)
    {
        tira ();
    }

    void cbMaximos_itemStateChanged(ItemEvent e)
    {
        tira ();
    }

    void cbEnvolvente_itemStateChanged(ItemEvent e)
    {
        tira ();
    }
}

class AppletEnvolvente_btnGraficar_actionAdapter implements java.awt.event.ActionListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_btnGraficar_actionAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnGraficar_actionPerformed(e);
    }
}

class AppletEnvolvente_choicePlaneta_itemAdapter implements java.awt.event.ItemListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_choicePlaneta_itemAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.choicePlaneta_itemStateChanged(e);
    }
}

class AppletEnvolvente_tfX0_actionAdapter implements java.awt.event.ActionListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_tfX0_actionAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfX0_actionPerformed(e);
    }
}

class AppletEnvolvente_tfX0_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_tfX0_focusAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfX0_focusLost(e);
    }
}

class AppletEnvolvente_tfY0_actionAdapter implements java.awt.event.ActionListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_tfY0_actionAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfY0_actionPerformed(e);
    }
}

class AppletEnvolvente_tfY0_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_tfY0_focusAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfY0_focusLost(e);
    }
}

class AppletEnvolvente_cbVisionGlobal_itemAdapter implements java.awt.event.ItemListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_cbVisionGlobal_itemAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbVisionGlobal_itemStateChanged(e);
    }
}

class AppletEnvolvente_cbLocal_itemAdapter implements java.awt.event.ItemListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_cbLocal_itemAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbLocal_itemStateChanged(e);
    }
}

class AppletEnvolvente_cbMaximos_itemAdapter implements java.awt.event.ItemListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_cbMaximos_itemAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbMaximos_itemStateChanged(e);
    }
}

class AppletEnvolvente_cbEnvolvente_itemAdapter implements java.awt.event.ItemListener
{
    AppletEnvolvente adaptee;

    AppletEnvolvente_cbEnvolvente_itemAdapter(AppletEnvolvente adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbEnvolvente_itemStateChanged(e);
    }
}
