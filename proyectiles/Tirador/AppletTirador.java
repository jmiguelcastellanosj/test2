
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;

/**
 * <p>Title: Proyecto Tirador</p>
 * <p>Description: Simula tiro a un blanco fijo</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class AppletTirador extends Applet
{
    private boolean isStandalone = false;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Panel pnlBotones = new Panel();
    CanvasTirador cnvGrafica = new CanvasTirador ();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    Panel pnlTirador = new BevelPanel("Tirador");
    Panel pnlBlanco = new BevelPanel("Blanco");
    Panel pnlAccion = new BevelPanel("Acciones");
    Label lblYTirador = new Label();
    TextField tfYTirador = new TextField();
    Label lblAngTirador = new Label();
    TextField tfAngTirador = new TextField();
    Label lblV0Tirador = new Label();
    TextField tfV0Tirador = new TextField();
    Choice chPlaneta = new Choice();
    Button btnTira = new Button();
    Button btnObtenCriticos = new Button();
    Button btnGraficar = new Button();
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    Label lblXBlanco = new Label();
    TextField tfXBlanco = new TextField();
    Label lblYBlanco = new Label();
    TextField tfYBlanco = new TextField();
    Label lblWBlanco = new Label();
    TextField tfWBlanco = new TextField();
    Label lblXTirador = new Label();
    TextField tfXTirador = new TextField();
    Button btnObtenAngulos = new Button();
    TextField tfG = new TextField();
    Panel pnlResultados = new Panel();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    Label lblResultado = new Label();
    TextField tfResultado = new TextField();

    Blanco blanco = null;
    Tirador tirador = null;

    //Get a parameter value
    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public AppletTirador()
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
        pnlBotones.setLayout(gridBagLayout2);
        lblYTirador.setText("y0:");
        tfYTirador.setColumns(5);
        tfYTirador.setEnabled(true);
        tfYTirador.setSelectionEnd(0);
        tfYTirador.setText("");
        tfYTirador.addActionListener(new AppletTirador_tfYTirador_actionAdapter(this));
        tfYTirador.addFocusListener(new AppletTirador_tfYTirador_focusAdapter(this));
        lblAngTirador.setText("Ang:");
        tfAngTirador.setColumns(5);
        tfAngTirador.setText("");
        lblV0Tirador.setText("v0:");
        tfV0Tirador.setColumns(5);
        tfV0Tirador.setText("");
        btnTira.setLabel("Tira");
        btnTira.addActionListener(new AppletTirador_btnTira_actionAdapter(this));
        btnObtenCriticos.setLabel("Obtener Valores\nCríticos");
        btnObtenCriticos.addActionListener(new AppletTirador_btnObtenCriticos_actionAdapter(this));
        btnGraficar.setEnabled(false);
        btnGraficar.setLabel("Graficar");
        btnGraficar.setLocale(java.util.Locale.getDefault());
        pnlAccion.setLayout(gridBagLayout3);
        lblXBlanco.setText("xc:");
        tfXBlanco.setColumns(5);
        tfXBlanco.setText("");
        tfXBlanco.addFocusListener(new AppletTirador_tfXBlanco_focusAdapter(this));
        tfXBlanco.addActionListener(new AppletTirador_tfXBlanco_actionAdapter(this));
        lblYBlanco.setText("yc:");
        lblWBlanco.setText("W:");
        tfYBlanco.setColumns(5);
        tfYBlanco.setText("");
        tfYBlanco.addFocusListener(new AppletTirador_tfYBlanco_focusAdapter(this));
        tfYBlanco.addActionListener(new AppletTirador_tfYBlanco_actionAdapter(this));
        tfWBlanco.setColumns(5);
        tfWBlanco.setText("0.01");
        lblXTirador.setText("x0:");
        tfXTirador.setColumns(5);
        tfXTirador.setText("");
        tfXTirador.addActionListener(new AppletTirador_tfXTirador_actionAdapter(this));
        tfXTirador.addFocusListener(new AppletTirador_tfXTirador_focusAdapter(this));
        btnObtenAngulos.setLabel("Obtener Angulos");
        btnObtenAngulos.setLocale(java.util.Locale.getDefault());
        btnObtenAngulos.addActionListener(new AppletTirador_btnObtenAngulos_actionAdapter(this));
        tfG.setColumns(5);
        tfG.setEditable(false);
        tfG.setText("9.81");
        chPlaneta.addItemListener(new AppletTirador_chPlaneta_itemAdapter(this));
        pnlResultados.setVisible(true);
        pnlResultados.setLayout(gridBagLayout4);
        lblResultado.setText("Resultado:");
        tfResultado.setColumns(30);
        this.add(pnlBotones,             new GridBagConstraints(0, 1, 2, 1, 0.0, 30.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlBotones.add(pnlTirador,  new GridBagConstraints(0, 0, 1, 1, 60.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlTirador.add(lblXTirador, null);
        pnlTirador.add(tfXTirador, null);
        pnlTirador.add(lblYTirador, null);
        pnlBotones.add(pnlBlanco,  new GridBagConstraints(0, 1, 1, 1, 0.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlBlanco.add(lblXBlanco, null);
        pnlBotones.add(pnlAccion,  new GridBagConstraints(1, 0, 1, 2, 40.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlAccion.add(btnGraficar,     new GridBagConstraints(0, 3, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlAccion.add(btnObtenAngulos,   new GridBagConstraints(0, 2, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlAccion.add(btnTira,    new GridBagConstraints(0, 0, 1, 1, 100.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlAccion.add(btnObtenCriticos,   new GridBagConstraints(0, 1, 1, 1, 0.0, 25.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(cnvGrafica,               new GridBagConstraints(0, 0, 2, 1, 100.0, 70.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlResultados,       new GridBagConstraints(0, 2, 1, 1, 0.0, 10.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlTirador.add(tfYTirador, null);
        pnlTirador.add(lblAngTirador, null);
        pnlTirador.add(tfAngTirador, null);
        pnlTirador.add(lblV0Tirador, null);
        pnlTirador.add(tfV0Tirador, null);
        pnlTirador.add(chPlaneta, null);
        pnlTirador.add(tfG, null);

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

        pnlBlanco.add(tfXBlanco, null);
        pnlBlanco.add(lblYBlanco, null);
        pnlBlanco.add(tfYBlanco, null);
        pnlBlanco.add(lblWBlanco, null);
        pnlBlanco.add(tfWBlanco, null);
        pnlResultados.add(lblResultado,         new GridBagConstraints(0, 0, 1, 1, 20.0, 100.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlResultados.add(tfResultado,       new GridBagConstraints(1, 0, 3, 1, 80.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 115, 0));
        chPlaneta.select(2);
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
        AppletTirador applet = new AppletTirador();
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

    // oidor del botón "Tira"
    void btnTira_actionPerformed(ActionEvent e)
    {
        double x0, y0, ang, v0, g = 0, xc, yc, w;
        Proyectil p = null;
        boolean choca;

        cnvGrafica.limpia();

        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);

        try {
            x0 = new Double (tfXTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            x0 = 0;
        }

        try {
            y0 = new Double (tfYTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            y0 = 0;
        }

        try {
            ang = new Double (tfAngTirador.getText()).doubleValue();
            ang = Math.PI * ang / 180;  // radianes
        }
        catch (Exception x) {
            ang = 0;
        }

        try {
            v0 = new Double (tfV0Tirador.getText()).doubleValue();
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
            xc = new Double (tfXBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            xc = 0;
        }

        try {
            yc = new Double (tfYBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            yc = 0;
        }

        try {
            w = new Double (tfWBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            w = 0.01;
        }

        if (hayErrores (x0, y0, ang, v0, g, xc, yc, w))
            return;

        tirador = new Tirador (x0, y0);
        blanco = new Blanco (xc, yc, w);
        p = new Proyectil (v0, ang, g, x0, y0);

        choca = esIgual (yc, p.getAltura(xc));
        StringBuffer s = new StringBuffer ("Alcance: " + ponBien (df2.format(p.Alcance())) +
                                           " y(xc) = " + ponBien (df2.format (p.getAltura(xc))));
        if (choca)
            s.append (". Da en el blanco.");
        else
            s.append(". No da en el blanco.");

        tfResultado.setText(s.toString());
        cnvGrafica.setDatos(p, blanco, tirador);
        cnvGrafica.repaint();
    }

    private boolean hayErrores (double x0, double y0, double ang, double v0, double g,
                                double xc, double yc, double w)
    {
        boolean error = false;

        if ((x0 < 0) || (y0 < 0) || (g < 0) || (v0 < 0) ||
            (xc < 0) || (yc < 0) || (w < 0)) {
            tfResultado.setText("No puede haber valores negativos.");
            error = true;
        }

        if ((ang < 0) || (ang > Math.PI / 2)) {
            tfResultado.setText("El ángulo debe estar entre 0 y 90 grados.");
            error = true;
        }

        if (xc <= x0) {
            tfResultado.setText("El blanco debe estar a la derecha del tirador.");
            error = true;
        }

        return error;
    }

    private boolean hayErrores (double x0, double y0, double g, double xc, double yc)
    {
        boolean error = false;

        if ((x0 < 0) || (y0 < 0) || (g < 0) ||
            (xc < 0) || (yc < 0)) {
            tfResultado.setText("No puede haber valores negativos.");
            error = true;
        }

        if (xc <= x0) {
            tfResultado.setText("El blanco debe estar a la derecha del tirador.");
            error = true;
        }

        return error;
    }

    private boolean hayErrores (double x0, double y0, double v0, double g,
                                double xc, double yc, double w)
    {
        boolean error = false;

        if ((x0 < 0) || (y0 < 0) || (g < 0) || (v0 < 0) ||
            (xc < 0) || (yc < 0) || (w < 0)) {
            tfResultado.setText("No puede haber valores negativos.");
            error = true;
        }

        if (xc <= x0) {
            tfResultado.setText("El blanco debe estar a la derecha del tirador.");
            error = true;
        }

        return error;
    }

    private boolean esIgual (double x, double y)
    {
        double democracia = blanco.getW();

        return (x - democracia < y) && (x + democracia > y);
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

    private void updateXTirador ()
    {
        double x0;

        try {
            x0 = new Double (tfXTirador.getText()).doubleValue();
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
            y0 = new Double (tfYTirador.getText()).doubleValue();
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

    private void updateXBlanco ()
    {
        double x;

        try {
            x = new Double (tfXBlanco.getText()).doubleValue();
        }
        catch (Exception e) {
            x = 0;
        }

        if (blanco == null)
            blanco = new Blanco (x, 0);
        else
            blanco.setX (x);

        cnvGrafica.setBlanco (blanco);
    }

    private void updateYBlanco ()
    {
        double y;

        try {
            y = new Double (tfYBlanco.getText()).doubleValue();
        }
        catch (Exception e) {
            y = 0;
        }

        if (blanco == null)
            blanco = new Blanco (0, y);
        else
            blanco.setY (y);

        cnvGrafica.setBlanco (blanco);
    }

    void tfXTirador_focusLost(FocusEvent e)
    {
        updateXTirador ();
    }

    void tfXTirador_actionPerformed(ActionEvent e)
    {
        updateXTirador ();
    }

    void tfYTirador_focusLost(FocusEvent e)
    {
        updateYTirador ();
    }

    void tfYTirador_actionPerformed(ActionEvent e)
    {
        updateYTirador ();
    }

    void tfXBlanco_actionPerformed(ActionEvent e)
    {
        updateXBlanco ();
    }

    void tfXBlanco_focusLost(FocusEvent e)
    {
        updateXBlanco ();
    }

    void tfYBlanco_actionPerformed(ActionEvent e)
    {
        updateYBlanco ();
    }

    void tfYBlanco_focusLost(FocusEvent e)
    {
        updateYBlanco ();
    }

    // oidor botón para obtener valores críticos
    void btnObtenCriticos_actionPerformed(ActionEvent e)
    {
        double x0, y0, xc, yc, g;
        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);

        try {
            x0 = new Double (tfXTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            x0 = 0;
        }

        try {
            y0 = new Double (tfYTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            y0 = 0;
        }

        try {
            xc = new Double (tfXBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            xc = 0;
        }

        try {
            yc = new Double (tfYBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            yc = 0;
        }

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception x) {
            g = 9.81;
        }

        if (hayErrores (x0, y0, g, xc, yc))
            return;

        double dx = xc - x0;
        double dy = yc - y0;
        double x2 = (xc - x0) * (xc - x0);
        double y2 = (yc - y0) * (yc - y0);
        double vc = Math.sqrt (g * (dy + Math.sqrt(x2 + y2)));
        double thetac = 0;

        if (dx != 0)
            thetac = Math.atan ((dy + Math.sqrt(x2 + y2)) / dx);

        Font a1 = tfAngTirador.getFont();
        Font a2 = tfV0Tirador.getFont();

        Font f = new Font ("Times Roman", Font.ITALIC, 10);
        tfAngTirador.setFont(f);
        tfAngTirador.setText (ponBien ("" + (thetac * 180 / Math.PI)));
        tfAngTirador.setFont(a1);

        tfV0Tirador.setFont(f);
        tfV0Tirador.setText (ponBien ("" + vc));
        tfV0Tirador.setFont(a2);

        tfResultado.setText ("Vel. Crit = " + ponBien (df2.format(vc)) +
                             ". Ang. Crit = " + ponBien (df2.format(thetac * 180 / Math.PI)) +
                             ". Oprime el botón Tirar.");
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

    // oidor para obtener los ángulos
    void btnObtenAngulos_actionPerformed(ActionEvent e)
    {
        double x0, y0, v0, xc, yc, g, w;
        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);

        cnvGrafica.limpia();

        try {
            x0 = new Double (tfXTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            x0 = 0;
        }

        try {
            y0 = new Double (tfYTirador.getText()).doubleValue();
        }
        catch (Exception x) {
            y0 = 0;
        }

        try {
            v0 = new Double (tfV0Tirador.getText()).doubleValue();
        }
        catch (Exception x) {
            v0 = 0;
        }

        try {
            xc = new Double (tfXBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            xc = 0;
        }

        try {
            yc = new Double (tfYBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            yc = 0;
        }

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception x) {
            g = 9.81;
        }

        try {
            w = new Double (tfWBlanco.getText()).doubleValue();
        }
        catch (Exception x) {
            w = 0.01;
        }

        if (hayErrores (x0, y0, v0, g, xc, yc, w))
            return;

        double dx = xc - x0;
        double dy = yc - y0;

        // calcula la velocidad y el angulo crítico
        double x2 = (xc - x0) * (xc - x0);
        double y2 = (yc - y0) * (yc - y0);
        double vc = Math.sqrt (g * (dy + Math.sqrt(x2 + y2)));
        double thetac = Math.atan ((dy + Math.sqrt(x2 + y2)) / dx);

        // calcula los angulos con los que se da en el blanco
        if (v0 >= vc) {
            double dx2 = dx * dx;
            double t = 1 - (2 * g * dy) / (v0 * v0) - (g * g * dx2) / (v0 * v0 * v0 * v0);
            if (t < 0)
                t = 0;
            double radical = Math.sqrt (t);
            double theta1 = Math.atan ((v0 * v0) / (g * dx) * (1 + radical));
            double theta2 = Math.atan ((v0 * v0) / (g * dx) * (1 - radical));

            tirador = new Tirador (x0, y0);
            blanco = new Blanco (xc, yc, w);

            Proyectil p1 = new Proyectil (v0, theta1, g, x0, y0);
            Proyectil p2 = new Proyectil (v0, theta2, g, x0, y0);
            Proyectil p3 = new Proyectil (vc, thetac, g, x0, y0);

            cnvGrafica.setDatos (p1, p2, p3, tirador, blanco);
            cnvGrafica.repaint();

            tfResultado.setText ("Angulo 1: " + ponBien (df2.format (theta1 * 180 / Math.PI)) +
                                 " Angulo 2: " + ponBien (df2.format (theta2 * 180 / Math.PI)));
        }
        else {
            tfResultado.setText ("La velocidad es menor que la vel. crítica. El proyectil no da en el blanco. Oprime el botón Tirar para comprobarlo.");
        }

    }

}

class AppletTirador_btnTira_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_btnTira_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnTira_actionPerformed(e);
    }
}

class AppletTirador_chPlaneta_itemAdapter implements java.awt.event.ItemListener
{
    AppletTirador adaptee;

    AppletTirador_chPlaneta_itemAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.chPlaneta_itemStateChanged(e);
    }
}

class AppletTirador_tfXTirador_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletTirador adaptee;

    AppletTirador_tfXTirador_focusAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfXTirador_focusLost(e);
    }
}

class AppletTirador_tfXTirador_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_tfXTirador_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfXTirador_actionPerformed(e);
    }
}

class AppletTirador_tfYTirador_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletTirador adaptee;

    AppletTirador_tfYTirador_focusAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfYTirador_focusLost(e);
    }
}

class AppletTirador_tfYTirador_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_tfYTirador_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfYTirador_actionPerformed(e);
    }
}

class AppletTirador_tfXBlanco_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_tfXBlanco_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfXBlanco_actionPerformed(e);
    }
}

class AppletTirador_tfXBlanco_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletTirador adaptee;

    AppletTirador_tfXBlanco_focusAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfXBlanco_focusLost(e);
    }
}

class AppletTirador_tfYBlanco_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_tfYBlanco_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfYBlanco_actionPerformed(e);
    }
}

class AppletTirador_tfYBlanco_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletTirador adaptee;

    AppletTirador_tfYBlanco_focusAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfYBlanco_focusLost(e);
    }
}

class AppletTirador_btnObtenCriticos_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_btnObtenCriticos_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnObtenCriticos_actionPerformed(e);
    }
}

class AppletTirador_btnObtenAngulos_actionAdapter implements java.awt.event.ActionListener
{
    AppletTirador adaptee;

    AppletTirador_btnObtenAngulos_actionAdapter(AppletTirador adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnObtenAngulos_actionPerformed(e);
    }
}
