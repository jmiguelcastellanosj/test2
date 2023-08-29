
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;

/**
 * <p>Title: Un Proyectil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class AppletUnProyectil extends Applet
{
    private boolean isStandalone = false;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    CanvasGrafica cnvGrafica = new CanvasGrafica ();
    Panel pnlDatos = new BevelPanel("Datos");
    Panel pnlAcciones = new BevelPanel("Acciones");
    Panel pnlOpciones = new BevelPanel("Opciones");
    Panel pnlResultados = new Panel();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    Label lblX0 = new Label();
    TextField tfX0 = new TextField();
    Label lblY0 = new Label();
    TextField tfY0 = new TextField();
    Label lblV0 = new Label();
    TextField tfV0 = new TextField();
    Label lblAng = new Label();
    TextField tfAng = new TextField();
    Choice choicePlaneta = new Choice();
    TextField tfG = new TextField();
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    Button btnTirar = new Button();
    Button btnTabla = new Button();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    Checkbox cbGlobal = new Checkbox();
    CheckboxGroup cbGroup = new CheckboxGroup();
    Checkbox cbLocal = new Checkbox();
    GridBagLayout gridBagLayout5 = new GridBagLayout();
    Label lblResultados = new Label();
    TextField tfResultados = new TextField();

    private Proyectil proyectil = null;
    private Tirador tirador = null;
    Label lblPlaneta = new Label();
    Label lblG = new Label();
    CheckboxGroup cbGroup2 = new CheckboxGroup();
    Checkbox cbXY = new Checkbox();
    Checkbox cbTX = new Checkbox();
    Checkbox cbTY = new Checkbox();
    Checkbox cbTVx = new Checkbox();
    Checkbox cbTVy = new Checkbox();

    //Get a parameter value
    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public AppletUnProyectil()
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
        pnlDatos.setLayout(gridBagLayout2);
        lblX0.setText("x0:");
        tfX0.setColumns(10);
        tfX0.setText("");
        tfX0.addFocusListener(new AppletUnProyectil_tfX0_focusAdapter(this));
        tfX0.addActionListener(new AppletUnProyectil_tfX0_actionAdapter(this));
        lblY0.setText("y0:");
        tfY0.setColumns(10);
        tfY0.setText("");
        tfY0.addFocusListener(new AppletUnProyectil_tfY0_focusAdapter(this));
        tfY0.addActionListener(new AppletUnProyectil_tfY0_actionAdapter(this));
        lblV0.setText("v0:");
        tfV0.setColumns(10);
        tfV0.setText("");
        lblAng.setText("ang:");
        tfAng.setColumns(10);
        tfAng.setText("");
        tfG.setColumns(10);
        tfG.setEditable(false);
        tfG.setText("9.81");
        pnlAcciones.setLayout(gridBagLayout3);
        btnTirar.setLabel("Tirar");
        btnTirar.addActionListener(new AppletUnProyectil_btnTirar_actionAdapter(this));
        btnTabla.setEnabled(false);
        btnTabla.setLabel("Ver Tabla");
        btnTabla.addActionListener(new AppletUnProyectil_btnTabla_actionAdapter(this));
        pnlOpciones.setLayout(gridBagLayout4);
        cbGlobal.setCheckboxGroup(cbGroup);
        cbGlobal.setLabel("Visión Global");
        cbGlobal.setState(true);
        cbGlobal.addItemListener(new AppletUnProyectil_cbGlobal_itemAdapter(this));
        cbLocal.setCheckboxGroup(cbGroup);
        cbLocal.setLabel("Visión Local");
        cbLocal.setVisible(true);
        cbLocal.addItemListener(new AppletUnProyectil_cbLocal_itemAdapter(this));
        pnlResultados.setLayout(gridBagLayout5);
        lblResultados.setText("Resultados:");
        tfResultados.setColumns(40);
        lblPlaneta.setText("Planeta:");
        lblG.setText("g:");
        choicePlaneta.addItemListener(new AppletUnProyectil_choicePlaneta_itemAdapter(this));
        cbXY.setCheckboxGroup(cbGroup2);
        cbXY.setLabel("Gráfica y(x)");
        cbXY.setState(true);
        cbXY.addItemListener(new AppletUnProyectil_cbXY_itemAdapter(this));
        cbTX.setCheckboxGroup(cbGroup2);
        cbTX.setLabel("Gráfica x(t)");
        cbTX.addItemListener(new AppletUnProyectil_cbTX_itemAdapter(this));
        cbTY.setCheckboxGroup(cbGroup2);
        cbTY.setLabel("Gráfica y(t)");
        cbTY.addItemListener(new AppletUnProyectil_cbTY_itemAdapter(this));
        cbTVx.setCheckboxGroup(cbGroup2);
        cbTVx.setLabel("Gráfica vx(t)");
        cbTVx.addItemListener(new AppletUnProyectil_cbTVx_itemAdapter(this));
        cbTVy.setCheckboxGroup(cbGroup2);
        cbTVy.setLabel("Gráfica vy(t)");
        cbTVy.addItemListener(new AppletUnProyectil_cbTVy_itemAdapter(this));
        this.add(cnvGrafica,    new GridBagConstraints(0, 0, 3, 1, 100.0, 70.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlDatos,    new GridBagConstraints(0, 1, 1, 1, 0.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(lblX0,  new GridBagConstraints(0, 0, 1, 1, 10.0, 33.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(tfX0,  new GridBagConstraints(1, 0, 1, 1, 40.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(lblY0,   new GridBagConstraints(2, 0, 1, 1, 10.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(tfY0,   new GridBagConstraints(3, 0, 1, 1, 0.0, 40.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(lblV0,     new GridBagConstraints(0, 1, 1, 1, 0.0, 33.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(tfV0,      new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(lblAng,   new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(tfAng,    new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(choicePlaneta,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(tfG,     new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlDatos.add(lblPlaneta,  new GridBagConstraints(0, 2, 1, 1, 0.0, 33.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlAcciones,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlAcciones.add(btnTirar,  new GridBagConstraints(0, 0, 1, 1, 100.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlAcciones.add(btnTabla,  new GridBagConstraints(0, 1, 1, 1, 0.0, 50.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlOpciones,   new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(pnlResultados,   new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlResultados.add(lblResultados,  new GridBagConstraints(0, 0, 1, 1, 20.0, 100.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlResultados.add(tfResultados,   new GridBagConstraints(1, 0, 1, 1, 80.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbGlobal,       new GridBagConstraints(0, 0, 2, 1, 100.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbLocal,        new GridBagConstraints(0, 1, 2, 1, 0.0, 20.0
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
        pnlDatos.add(lblG,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbXY,     new GridBagConstraints(0, 2, 1, 1, 0.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbTX,      new GridBagConstraints(0, 3, 1, 1, 0.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbTY,     new GridBagConstraints(0, 4, 1, 1, 0.0, 20.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbTVx,   new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pnlOpciones.add(cbTVy,  new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        choicePlaneta.select(2);

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
        AppletUnProyectil applet = new AppletUnProyectil();
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
        tira (true);
    }

    private void tira (boolean escribe)
    {
        double x0, y0, v0, ang, g;

        DecimalFormat df2 = new DecimalFormat ("####.00");
        df2.setMinimumIntegerDigits (1);

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
            ang = new Double (tfAng.getText()).doubleValue();
            ang = (ang * Math.PI) / 180;
        }
        catch (Exception e) {
            ang = 0;
        }

        try {
            g = new Double (tfG.getText()).doubleValue();
        }
        catch (Exception e) {
            g = 9.81;
        }

        if (!hayErrores (y0, v0, ang, g, escribe)) {
            tirador = new Tirador (x0, y0);
            proyectil = new Proyectil (v0, ang, g, x0, y0);
            tfResultados.setText("Alcance: " + df2.format (proyectil.Alcance()) + " m. " +
                                 "Altura máxima: " + df2.format (proyectil.AlturaMaxima()) + " m. " +
                                 "Tiempo en el aire: " + df2.format (proyectil.TiempoAire()) + " s.");
            int modoGrafica = (cbGlobal.getState() ? CanvasGrafica.GLOBAL : CanvasGrafica.LOCAL);
            int tipoGrafica = -1;

            if (cbXY.getState())
                tipoGrafica = CanvasGrafica.GRAFICA_XY;
            else if (cbTX.getState())
                tipoGrafica = CanvasGrafica.GRAFICA_TX;
            else if (cbTY.getState())
                tipoGrafica = CanvasGrafica.GRAFICA_TY;
            else if (cbTVx.getState())
                tipoGrafica = CanvasGrafica.GRAFICA_TVX;
            else if (cbTVy.getState())
                tipoGrafica = CanvasGrafica.GRAFICA_TVY;

            cnvGrafica.setDatos (proyectil, tirador, tipoGrafica, modoGrafica);
            btnTabla.setEnabled (true);
        }
    }

    private boolean hayErrores (double y0, double v0, double ang, double g, boolean escribe)
    {
        String mensaje = "";
        boolean error = false;

        if (v0 == 0) {
            mensaje = "La velocidad debe ser positiva";
            error = true;
        }

        if (v0 < 0 || g < 0 || y0 < 0) {
            mensaje = "Hay valores negativos";
            error = true;
        }

        if (ang < 0 || ang > 2 * Math.PI) {
            mensaje = "El ángulo debe estar entre 0 y 360 grados";
            error = true;
        }

        if (escribe)
            tfResultados.setText (mensaje);

        return error;
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
        btnTabla.setEnabled (false);
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
        btnTabla.setEnabled (false);
    }

    void cbGlobal_itemStateChanged(ItemEvent e)
    {
        tira (false);
    }

    void cbLocal_itemStateChanged(ItemEvent e)
    {
        tira (false);
    }

    void btnTabla_actionPerformed(ActionEvent e)
    {
        TablaFrame tf = new TablaFrame ();
        tf.setDatos(proyectil);
        tf.setVisible(true);
        tf.toFront();
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

    void cbXY_itemStateChanged(ItemEvent e)
    {
        cnvGrafica.setTipoGrafica (CanvasGrafica.GRAFICA_XY);
    }

    void cbTX_itemStateChanged(ItemEvent e)
    {
        cnvGrafica.setTipoGrafica (CanvasGrafica.GRAFICA_TX);
    }

    void cbTY_itemStateChanged(ItemEvent e)
    {
        cnvGrafica.setTipoGrafica (CanvasGrafica.GRAFICA_TY);
    }

    void cbTVx_itemStateChanged(ItemEvent e)
    {
        cnvGrafica.setTipoGrafica (CanvasGrafica.GRAFICA_TVX);
    }

    void cbTVy_itemStateChanged(ItemEvent e)
    {
        cnvGrafica.setTipoGrafica (CanvasGrafica.GRAFICA_TVY);
    }
}

class AppletUnProyectil_btnTirar_actionAdapter implements java.awt.event.ActionListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_btnTirar_actionAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnTirar_actionPerformed(e);
    }
}

class AppletUnProyectil_tfX0_actionAdapter implements java.awt.event.ActionListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_tfX0_actionAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfX0_actionPerformed(e);
    }
}

class AppletUnProyectil_tfX0_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_tfX0_focusAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfX0_focusLost(e);
    }
}

class AppletUnProyectil_tfY0_actionAdapter implements java.awt.event.ActionListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_tfY0_actionAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.tfY0_actionPerformed(e);
    }
}

class AppletUnProyectil_tfY0_focusAdapter extends java.awt.event.FocusAdapter
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_tfY0_focusAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void focusLost(FocusEvent e)
    {
        adaptee.tfY0_focusLost(e);
    }
}

class AppletUnProyectil_cbGlobal_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbGlobal_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbGlobal_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbLocal_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbLocal_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbLocal_itemStateChanged(e);
    }
}

class AppletUnProyectil_btnTabla_actionAdapter implements java.awt.event.ActionListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_btnTabla_actionAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnTabla_actionPerformed(e);
    }
}

class AppletUnProyectil_choicePlaneta_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_choicePlaneta_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.choicePlaneta_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbXY_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbXY_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbXY_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbTX_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbTX_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbTX_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbTY_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbTY_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbTY_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbTVx_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbTVx_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbTVx_itemStateChanged(e);
    }
}

class AppletUnProyectil_cbTVy_itemAdapter implements java.awt.event.ItemListener
{
    AppletUnProyectil adaptee;

    AppletUnProyectil_cbTVy_itemAdapter(AppletUnProyectil adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.cbTVy_itemStateChanged(e);
    }
}
