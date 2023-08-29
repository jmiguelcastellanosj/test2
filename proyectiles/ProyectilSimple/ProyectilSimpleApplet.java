
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * <p>Title: Estudio de Proyectiles</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class ProyectilSimpleApplet extends Applet {
    private boolean isStandalone = false;
    BevelPanel PanelDatos = new BevelPanel("Proyectil 1", BevelPanel.BEVEL_RAISED);
    GridLayout gridLayout1 = new GridLayout();
    BevelPanel PanelOpciones = new BevelPanel("Proyectil 2", BevelPanel.BEVEL_RAISED);
    BevelPanel PanelResultados = new BevelPanel("Resultados", BevelPanel.BEVEL_RAISED);
    BevelPanel PanelBotones = new BevelPanel("Acciones", BevelPanel.BEVEL_RAISED);
    Label lblVelocidad = new Label();
    Label lblAngulo = new Label();
    TextField tfVelocidad = new TextField();
    TextField tfAngulo = new TextField();
    TextArea taResultados = new TextArea();
    Button btnCalcular = new Button();
    Button btnGraficar = new Button();
    Button btnAyuda = new Button();
    Button btnAcerca = new Button();

    private Proyectil p1, p2;
    Label lblVelocidadInicial2 = new Label();
    TextField tfVelocidad2 = new TextField();
    Label lblAngulo2 = new Label();
    TextField tfAngulo2 = new TextField();
    Button btnConfig = new Button();
    Button btnTabla = new Button();
    Label lblPlaneta1 = new Label();
    Label lblG1 = new Label();
    Label lblPlaneta2 = new Label();
    Label lblG2 = new Label();
    Choice chPlaneta1 = new Choice();
    Choice chPlaneta2 = new Choice();
    TextField tfG1 = new TextField();
    TextField tfG2 = new TextField();

  //Get a parameter value
  public String getParameter(String key, String def)
  {
      return isStandalone ? System.getProperty(key, def) :
          (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public ProyectilSimpleApplet()
  {
  }

  //Initialize the applet
  public void init()
  {
      try {
          jbInit();
      }
      catch(Exception e) {
          e.printStackTrace();
      }
  }

  //Component initialization
  private void jbInit() throws Exception
  {
      gridLayout1.setColumns(2);
      gridLayout1.setHgap(0);
      gridLayout1.setRows(2);
      gridLayout1.setVgap(0);
      this.setLayout(gridLayout1);

      PanelDatos.setEnabled(true);
      PanelDatos.setLayout(null);
      PanelOpciones.setLayout(null);
      PanelResultados.setLayout(null);
      PanelBotones.setLocale(java.util.Locale.getDefault());
      PanelBotones.setLayout(null);

// panel datos
      lblVelocidad.setFont(new java.awt.Font("Dialog", 0, 11));
      lblVelocidad.setLocale(java.util.Locale.getDefault());
      lblVelocidad.setText("Velocidad inicial (m/s):");
      lblVelocidad.setBounds(new Rectangle(10, 35, 117, 15));
      lblAngulo.setFont(new java.awt.Font("Dialog", 0, 11));
      lblAngulo.setLocale(java.util.Locale.getDefault());
      lblAngulo.setText("Angulo de disparo (grados):");
      lblAngulo.setBounds(new Rectangle(10, 65, 136, 15));
      tfVelocidad.setColumns(10);
      tfVelocidad.setFont(new java.awt.Font("Dialog", 0, 11));
      tfVelocidad.setSelectionStart(0);
      tfVelocidad.setText("");
      tfVelocidad.setBounds(new Rectangle(150, 32, 58, 22));
      tfAngulo.setColumns(10);
      tfAngulo.setFont(new java.awt.Font("Dialog", 0, 11));
      tfAngulo.setBounds(new Rectangle(150, 62, 58, 22));
      lblPlaneta1.setLocale(java.util.Locale.getDefault());
      lblPlaneta1.setText("Planeta:");
      lblPlaneta1.setBounds(new Rectangle(10, 95, 55, 15));
      chPlaneta1.setBounds(new Rectangle(80, 92, 60, 22));
      chPlaneta1.addItemListener(new ProyectilSimpleApplet_chPlaneta1_itemAdapter(this));
      lblG1.setText("g (m/s):");
      lblG1.setBounds(new Rectangle(10, 120, 40, 15));
      tfG1.setColumns(10);
      tfG1.setEditable(false);
      tfG1.setText("9.81");
      tfG1.setBounds(new Rectangle(70, 117, 60, 22));

// panel opciones
      lblVelocidadInicial2.setFont(new java.awt.Font("Dialog", 0, 11));
      lblVelocidadInicial2.setLocale(java.util.Locale.getDefault());
      lblVelocidadInicial2.setText("Velocidad inicial (m/s):");
      lblVelocidadInicial2.setBounds(new Rectangle(10, 35, 110, 15));
      tfVelocidad2.setColumns(10);
      tfVelocidad2.setForeground(Color.black);
      tfVelocidad2.setText("");
      tfVelocidad2.setBounds(new Rectangle(150, 32, 58, 22));
      lblAngulo2.setFont(new java.awt.Font("Dialog", 0, 11));
      lblAngulo2.setText("Angulo de disparo (grados)");
      lblAngulo2.setVisible(true);
      lblAngulo2.setBounds(new Rectangle(10, 65, 136, 15));
      tfAngulo2.setText("");
      tfAngulo2.setBounds(new Rectangle(150, 62, 58, 22));
      lblPlaneta2.setText("Planeta:");
      lblPlaneta2.setBounds(new Rectangle(10, 95, 55, 15));
      chPlaneta2.setBounds(new Rectangle(80, 92, 60, 22));
      chPlaneta2.addItemListener(new ProyectilSimpleApplet_chPlaneta2_itemAdapter(this));
      lblG2.setText("g (m/s):");
      lblG2.setBounds(new Rectangle(10, 120, 40, 15));
      tfG2.setColumns(10);
      tfG2.setEditable(false);
      tfG2.setText("9.81");
      tfG2.setBounds(new Rectangle(70, 117, 60, 22));

// panel resultados
      taResultados.setColumns(30);
      taResultados.setFont(new java.awt.Font("Monospaced", 0, 12));
      taResultados.setRows(10);
      taResultados.setSelectionEnd(9);
      taResultados.setText("");
      taResultados.setBounds(new Rectangle(9, 19, 188, 122));

// panel acciones
      btnCalcular.setForeground(Color.black);
      btnCalcular.setLabel("Calcular");
      btnCalcular.setBounds(new Rectangle(10, 25, 85, 25));
      btnCalcular.addActionListener(new ProyectilSimpleApplet_btnCalcular_actionAdapter(this));
      btnGraficar.setEnabled(false);
      btnGraficar.setLabel("Graficar");
      btnGraficar.setBounds(new Rectangle(10, 55, 85, 25));
      btnGraficar.addActionListener(new ProyectilSimpleApplet_btnGraficar_actionAdapter(this));
      btnAyuda.setLabel("Ayuda");
      btnAyuda.setBounds(new Rectangle(10, 85, 85, 25));
      btnAcerca.setLabel("Acerca");
      btnAcerca.setBounds(new Rectangle(10, 115, 85, 25));
      btnAcerca.addActionListener(new ProyectilSimpleApplet_btnAcerca_actionAdapter(this));
      btnConfig.setLabel("Configurar");
      btnConfig.setLocale(java.util.Locale.getDefault());
      btnConfig.setBounds(new Rectangle(100, 25, 85, 25));
      btnConfig.addActionListener(new ProyectilSimpleApplet_btnConfig_actionAdapter(this));
      btnTabla.setEnabled(false);
      btnTabla.setLabel("Ver Tabla");
      btnTabla.setBounds(new Rectangle(100, 55, 85, 25));
      btnTabla.addActionListener(new ProyectilSimpleApplet_btnTabla_actionAdapter(this));
      this.add(PanelDatos, null);

      this.add(PanelOpciones, null);
      PanelOpciones.add(lblVelocidadInicial2, null);
      PanelOpciones.add(tfVelocidad2, null);
      this.add(PanelResultados, null);
      PanelResultados.add(taResultados, null);
      this.add(PanelBotones, null);
      PanelDatos.add(lblVelocidad, null);
      PanelDatos.add(tfVelocidad, null);
      PanelDatos.add(lblAngulo, null);
      PanelDatos.add(tfAngulo, null);
      PanelDatos.add(lblPlaneta1, null);
      PanelDatos.add(lblG1, null);
      PanelDatos.add(tfG1, null);
      PanelDatos.add(chPlaneta1, null);
      PanelBotones.add(btnAcerca, null);
      PanelBotones.add(btnAyuda, null);
      PanelBotones.add(btnGraficar, null);
      PanelBotones.add(btnCalcular, null);
      PanelBotones.add(btnConfig, null);
      PanelBotones.add(btnTabla, null);
      PanelOpciones.add(lblAngulo2, null);
      PanelOpciones.add(tfAngulo2, null);
      PanelOpciones.add(lblPlaneta2, null);
      PanelOpciones.add(lblG2, null);
      PanelOpciones.add(chPlaneta2, null);
      PanelOpciones.add(tfG2, null);

      chPlaneta1.add("Mercurio");
      chPlaneta1.add("Venus");
      chPlaneta1.add("Tierra");
      chPlaneta1.add("Luna");
      chPlaneta1.add("Marte");
      chPlaneta1.add("Júpiter");
      chPlaneta1.add("Saturno");
      chPlaneta1.add("Urano");
      chPlaneta1.add("Neptuno");
      chPlaneta1.add("Plutón");
      chPlaneta1.add("Otro");
      chPlaneta1.select(2);

      chPlaneta2.add("Mercurio");
      chPlaneta2.add("Venus");
      chPlaneta2.add("Tierra");
      chPlaneta2.add("Luna");
      chPlaneta2.add("Marte");
      chPlaneta2.add("Júpiter");
      chPlaneta2.add("Saturno");
      chPlaneta2.add("Urano");
      chPlaneta2.add("Neptuno");
      chPlaneta2.add("Plutón");
      chPlaneta2.add("Otro");
      chPlaneta2.select(2);

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

  void btnAcerca_actionPerformed(ActionEvent e)
  {
      AcercaFrame af = new AcercaFrame ();
      af.setVisible(true);
      af.requestFocus();
  }

  void btnCalcular_actionPerformed(ActionEvent e)
  {
      double v0_1 = 0, angulo1 = 0, v0_2, angulo2, g1, g2;
      boolean valido1 = true, valido2 = true;

      StringBuffer r = new StringBuffer ();
      DecimalFormat df0 = new DecimalFormat ("####");
      DecimalFormat df2 = new DecimalFormat ("####.00");

      df0.setMinimumIntegerDigits (1);
      df2.setMinimumIntegerDigits (1);
      btnGraficar.setEnabled(false);
      btnTabla.setEnabled(false);
      taResultados.setText("");

      try {
          v0_1 = new Double(tfVelocidad.getText()).doubleValue();
      }
      catch (Exception d) {
          v0_1 = 0;
          valido1 = false;
      }

      try {
          v0_2 = new Double(tfVelocidad2.getText()).doubleValue();
      }
      catch (Exception d) {
          v0_2 = 0;
          valido2 = false;
      }

      try {
          angulo1 = new Double(tfAngulo.getText()).doubleValue();
          angulo1 = Math.PI * angulo1 / 180;  // radianes
      }
      catch (Exception d) {
          angulo1 = 0;
          valido1 = false;
      }

      try {
          angulo2 = new Double(tfAngulo2.getText()).doubleValue();
          angulo2 = Math.PI * angulo2 / 180;  // radianes
      }
      catch (Exception d) {
          angulo2 = 0;
          valido2 = false;
      }

      try {
          g1 = new Double (tfG1.getText()).doubleValue();
      }
      catch (Exception d) {
          g1 = 9.81;
          tfG1.setText ("9.81");
      }

      try {
          g2 = new Double (tfG2.getText()).doubleValue();
      }
      catch (Exception d) {
          g2 = 9.81;
          tfG2.setText ("9.81");
      }

      p1 = null; p2 = null;

      if (valido1)
          p1 = new Proyectil (v0_1, angulo1, g1);

      if (valido2)
          p2 = new Proyectil (v0_2, angulo2, g2);

      if (valido1) {
          r.append ("*** Proyectil 1 ***\n");
          r.append("\nTiempo en el aire: " + df2.format (p1.TiempoAire()) + " s");
          r.append("\nAltura máxima: " + df2.format (p1.AlturaMaxima()) + " mts");
          r.append("\nAlcance: " + df2.format (p1.Alcance()) + " mts\n\n");
      }

      if (valido2) {
          r.append ("*** Proyectil 2 ***\n");
          r.append("\nTiempo en el aire: " + df2.format (p2.TiempoAire()) + " s");
          r.append("\nAltura máxima: " + df2.format (p2.AlturaMaxima()) + " mts");
          r.append("\nAlcance: " + df2.format (p2.Alcance()) + " mts\n\n");
      }

      taResultados.setText (r.toString());
      btnGraficar.setEnabled(true);
      btnTabla.setEnabled(true);
  }

  void btnGraficar_actionPerformed(ActionEvent e)
  {
      PFrame pf = new PFrame (p1, p2);
      pf.setVisible (true);
      pf.requestFocus ();
  }

    void btnConfig_actionPerformed(ActionEvent e)
    {
        CFrame cf = new CFrame ();
        cf.setVisible (true);
        cf.requestFocus ();
    }

    void btnTabla_actionPerformed(ActionEvent e)
    {
        TablaFrame tf = new TablaFrame (p1, p2);
        tf.setVisible(true);
    }

    void chPlaneta1_itemStateChanged(ItemEvent e)
    {
        ponG (chPlaneta1, tfG1);
    }

    void chPlaneta2_itemStateChanged(ItemEvent e)
    {
        ponG (chPlaneta2, tfG2);
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
}

class ProyectilSimpleApplet_btnAcerca_actionAdapter implements java.awt.event.ActionListener {
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_btnAcerca_actionAdapter(ProyectilSimpleApplet adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnAcerca_actionPerformed(e);
    }
}

class ProyectilSimpleApplet_btnCalcular_actionAdapter implements java.awt.event.ActionListener {
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_btnCalcular_actionAdapter(ProyectilSimpleApplet adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnCalcular_actionPerformed(e);
    }
}

class ProyectilSimpleApplet_btnGraficar_actionAdapter implements java.awt.event.ActionListener {
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_btnGraficar_actionAdapter(ProyectilSimpleApplet adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnGraficar_actionPerformed(e);
    }
}

class ProyectilSimpleApplet_btnConfig_actionAdapter implements java.awt.event.ActionListener
{
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_btnConfig_actionAdapter(ProyectilSimpleApplet adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnConfig_actionPerformed(e);
    }
}

class ProyectilSimpleApplet_btnTabla_actionAdapter implements java.awt.event.ActionListener
{
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_btnTabla_actionAdapter(ProyectilSimpleApplet adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.btnTabla_actionPerformed(e);
    }
}

class ProyectilSimpleApplet_chPlaneta1_itemAdapter implements java.awt.event.ItemListener
{
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_chPlaneta1_itemAdapter(ProyectilSimpleApplet adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.chPlaneta1_itemStateChanged(e);
    }
}

class ProyectilSimpleApplet_chPlaneta2_itemAdapter implements java.awt.event.ItemListener
{
    ProyectilSimpleApplet adaptee;

    ProyectilSimpleApplet_chPlaneta2_itemAdapter(ProyectilSimpleApplet adaptee)
    {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e)
    {
        adaptee.chPlaneta2_itemStateChanged(e);
    }
}
