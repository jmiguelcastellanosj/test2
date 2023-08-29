
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;

/**
 * <p>Title: Applet del chango y el cazador</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class AppletCazador extends Applet
{
    private boolean isStandalone = false;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    CanvasCazador cnvCazador = new CanvasCazador ();
    Panel pnlCazador = new BevelPanel ("Cazador");
    Panel pnlChango = new BevelPanel ("Chango");
    Panel pnlBotones = new BevelPanel ("Acciones");
    Panel pnlResultados = new Panel();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    Label lblV0 = new Label();
    TextField tfV0 = new TextField();
    Label lblAng = new Label();
    TextField tfAng = new TextField();
    Choice chPlaneta = new Choice();
    TextField tfG = new TextField();
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    Label lblXc = new Label();
    TextField tfXc = new TextField();
    Label lblYc = new Label();
    TextField tfYc = new TextField();
    Label lblW = new Label();
    TextField tfW = new TextField();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    Button btnTirar = new Button();
    Button btnMinimos = new Button();
    GridBagLayout gridBagLayout5 = new GridBagLayout();
    Label lblResultados = new Label();
    TextField tfResultados = new TextField();

    Chango chango = null;
    Proyectil flecha = null;
    Proyectil changoCayendo = null;

    //Get a parameter value
    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public AppletCazador()
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
        pnlCazador.setLayout(gridBagLayout2);
        lblV0.setText("v0:");
        tfV0.setColumns(10);
        tfV0.setText("");
        lblAng.setText("Ang:");
        tfAng.setColumns(10);
        tfAng.setText("");
        tfG.setColumns(10);
        tfG.setEditable(false);
        tfG.setText("9.81");
        pnlChango.setLayout(gridBagLayout3);
        lblXc.setText("xc:");
        tfXc.setColumns(10);
        tfXc.setText("");
        tfXc.addFocusListener(new AppletCazador_tfXc_focusAdapter(this));
        tfXc.addActionListener(new AppletCazador_tfXc_actionAdapter(this));
        lblYc.setText("yc:");
        tfYc.setColumns(10);
        tfYc.setText("");
        tfYc.addFocusListener(new AppletCazador_tfYc_focusAdapter(this));
        tfYc.addActionListener(new AppletCazador_tfYc_actionAdapter(this));
        lblW.setText("w:");
        tfW.setColumns(10);
        tfW.setText("0.01");
        pnlBotones.setLayout(gridBagLayout4);
        btnTirar.setLabel("Tirar");
        btnTirar.addActionListener(new AppletCazador_btnTirar_actionAdapter(this));
        btnMinimos.setLabel("Obtener valores mínimos");
        btnMinimos.addActionListener(new AppletCazador_btnMinimos_actionAdapter(this));
        pnlResultados.setLayout(gridBagLayout5);
        lblResultados.setText("Resultados:");
        tfResultados.setColumns(40);
        tfResultados.setText("");
        chPlaneta.addItemListener(new AppletCazador_chPlaneta_itemAdapter(this));
        this.add(cnvCazador,     new GridBagConstraints(0, 0, 3, 1, 100.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlCazador,      new GridBagConstraints(0, 1, 2, 1, 80.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(lblV0,    new GridBagConstraints(0, 0, 1, 1, 10.0, 50.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(tfV0,   new GridBagConstraints(1, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(lblAng,        new GridBagConstraints(2, 0, 1, 1, 10.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(tfAng,   new GridBagConstraints(3, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(chPlaneta,    new GridBagConstraints(4, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlCazador.add(tfG,   new GridBagConstraints(5, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlChango,    new GridBagConstraints(0, 2, 2, 1, 0.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(lblXc,  new GridBagConstraints(0, 0, 1, 1, 20.0, 50.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(tfXc,  new GridBagConstraints(1, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(lblYc,  new GridBagConstraints(2, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(tfYc,  new GridBagConstraints(3, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(lblW,   new GridBagConstraints(4, 0, 1, 1, 20.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlChango.add(tfW,   new GridBagConstraints(5, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlBotones,        new GridBagConstraints(2, 1, 1, 2, 20.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlBotones.add(btnTirar,  new GridBagConstraints(0, 0, 1, 1, 100.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlBotones.add(btnMinimos,  new GridBagConstraints(0, 1, 1, 1, 0.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlResultados,     new GridBagConstraints(0, 3, 3, 1, 0.0, 10.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlResultados.add(lblResultados,  new GridBagConstraints(0, 0, 1, 1, 20.0, 100.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlResultados.add(tfResultados,  new GridBagConstraints(1, 0, 1, 1, 80.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        chPlaneta.add("Mercurio");
        chPlaneta.add("Venus");
        chPlaneta.add("Tierra");
        chPlaneta.add("Luna");
        chPlaneta.add("Marte");
        chPlaneta.add("Júpiter");
        chPlaneta.add("Saturno");
        chPlaneta.add("Urano");
        chPlaneta.add("Neptuno");
        chPlaneta.add("Plutón");
        chPlaneta.add("Otro");
        chPlaneta.select(2);

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
        AppletCazador applet = new AppletCazador();
        applet.isStandalone = true;
        Frame frame;
        frame = new Frame();
        frame.setTitle("Applet Frame");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(400,320);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }

    void btnTirar_actionPerformed(ActionEvent e)
    {
        double ang, v0, g = 0, xc, yc, w;
        boolean choca;

        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);



        try {
            ang = new Double (tfAng.getText()).doubleValue();
            ang = Math.PI * ang / 180;  // radianes
        }
        catch (Exception x) {
            ang = 0;
        }

        try {
            v0 = new Double (tfV0.getText()).doubleValue();
        }
        catch (Exception x) {
            v0 = 0;
        }

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception x) {
            g = 9.81;
        }

        try {
            xc = new Double (tfXc.getText()).doubleValue();
        }
        catch (Exception x) {
            xc = 0;
        }

        try {
            yc = new Double (tfYc.getText()).doubleValue();
        }
        catch (Exception x) {
            yc = 0;
        }

        try {
            w = new Double (tfW.getText()).doubleValue();
        }
        catch (Exception x) {
            w = 0.01;
        }

        if (hayErrores (ang, v0, g, xc, yc, w))
            return;

        chango = new Chango (xc, yc, w);
        flecha = new Proyectil (v0, ang, g, 0, 0);
        changoCayendo = new Proyectil (0, 1.5 * Math.PI, g, xc, yc);

        DatosXY puntoChoque = null;

        if (Choca (flecha, changoCayendo, chango)) {
            tfResultados.setText ("Da en el chango. Altura: " + ponBien (df2.format (flecha.getAltura (xc))));
            puntoChoque = new DatosXY (xc, flecha.getAltura(xc));
        }
        else {
            tfResultados.setText ("No da en el chango. Alcance: " + ponBien (df2.format (flecha.Alcance())));
        }

        cnvCazador.setDatos (puntoChoque, chango, flecha, changoCayendo);
    }

    private boolean hayErrores (double ang, double v0, double g, double xc, double yc, double w)
    {
        boolean error = false;

        if ((xc == 0) || (yc == 0)) {
            tfResultados.setText ("El chango no puede estar en el origen ni sobre los ejes.");
            error = true;
        }

        if ((g < 0) || (v0 < 0) || (xc < 0) || (yc < 0) || (w < 0)) {
            tfResultados.setText ("No puede haber valores negativos.");
            error = true;
        }

        if ((ang < 0) || (ang > Math.PI / 2)) {
            tfResultados.setText ("El ángulo debe estar entre 0 y 90 grados.");
            error = true;
        }

        return error;
    }

    private String ponBien (String s)
    {
        StringBuffer t = new StringBuffer ();

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == ',')
                t.append('.');
            else
                t.append(s.charAt(i));

        return t.toString();
    }

    private boolean Choca (Proyectil f, Proyectil cc, Chango c)
    {
        boolean choca = (f.getAltura (c.getX()) >= 0) && (f.getAltura (c.getX()) <= c.getY()) &&
            (esIgual (f.getAngulo(), Math.atan(c.getY() / c.getX()), 0.01));

        return choca;
    }

    private boolean esIgual (double x, double y, double democracia)
    {
        return (x - democracia < y) && (x + democracia > y);
    }

    void btnMinimos_actionPerformed(ActionEvent e)
    {
        double g, xc, yc, w;

        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception x) {
            g = 9.81;
        }

        try {
            xc = new Double (tfXc.getText()).doubleValue();
        }
        catch (Exception x) {
            xc = 0;
        }

        try {
            yc = new Double (tfYc.getText()).doubleValue();
        }
        catch (Exception x) {
            yc = 0;
        }

        try {
            w = new Double (tfW.getText()).doubleValue();
        }
        catch (Exception x) {
            w = 0.01;
        }

        if (hayErrores (0, 0, g, xc, yc, w))
            return;

        double v0min = Math.sqrt (g * (xc * xc + yc * yc) / (2 * yc));
        double theta = Math.atan (yc / xc);

        v0min = (v0min * 10000.0 + 1) / 10000.0;
        theta = (theta * 180) / Math.PI;

        tfResultados.setText ("Vel. min: " + ponBien (df2.format (v0min)) +
                              ". Ang. de disparo: " + ponBien (df2.format (theta)) +
                              ". Oprime tirar.");

        tfV0.setText ("" + v0min);
        tfAng.setText ("" + theta);
    }

    void chPlaneta_itemStateChanged(ItemEvent e)
    {
        ponG (chPlaneta, tfG);
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

    private void updateXChango ()
    {
        double x;

        try {
            x = new Double (tfXc.getText()).doubleValue();
        }
        catch (Exception e) {
            x = 0;
        }

        if (chango == null)
            chango = new Chango (x, 0);
        else
            chango.setX (x);

        cnvCazador.setChango (chango);
    }

    private void updateYChango ()
    {
        double y;

        try {
            y = new Double (tfYc.getText()).doubleValue();
        }
        catch (Exception e) {
            y = 0;
        }

        if (chango == null)
            chango = new Chango (0, y);
        else
            chango.setY (y);

        cnvCazador.setChango (chango);
    }

    void tfXc_actionPerformed(ActionEvent e)
    {
        updateXChango ();
    }

    void tfXc_focusLost(FocusEvent e)
    {
        updateXChango ();
    }

    void tfYc_actionPerformed(ActionEvent e)
    {
        updateYChango ();
    }

    void tfYc_focusLost(FocusEvent e)
    {
        updateYChango ();
    }
}

class AppletCazador_btnTirar_actionAdapter implements java.awt.event.ActionListener
{
    AppletCazador adaptee;

    AppletCazador_btnTirar_actionAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnTirar_actionPerformed(e);
    }
}

class AppletCazador_btnMinimos_actionAdapter implements java.awt.event.ActionListener
{
    AppletCazador adaptee;

    AppletCazador_btnMinimos_actionAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnMinimos_actionPerformed(e);
    }
}

class AppletCazador_chPlaneta_itemAdapter implements java.awt.event.ItemListener
{
    AppletCazador adaptee;

    AppletCazador_chPlaneta_itemAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.chPlaneta_itemStateChanged(e);
    }
}

class AppletCazador_tfXc_actionAdapter implements java.awt.event.ActionListener
{
    AppletCazador adaptee;

    AppletCazador_tfXc_actionAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfXc_actionPerformed(e);
    }
}

class AppletCazador_tfXc_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletCazador adaptee;

    AppletCazador_tfXc_focusAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfXc_focusLost(e);
    }
}

class AppletCazador_tfYc_actionAdapter implements java.awt.event.ActionListener
{
    AppletCazador adaptee;

    AppletCazador_tfYc_actionAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfYc_actionPerformed(e);
    }
}

class AppletCazador_tfYc_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletCazador adaptee;

    AppletCazador_tfYc_focusAdapter(AppletCazador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfYc_focusLost(e);
    }
}
