
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: AcercaFrame</p>
 * <p>Description: Despliega la venta de acerca</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class AcercaFrame extends Frame {
    Button btnCerrar = new Button();
    Label lblTitle = new Label();
    Label lblCopyright = new Label();
    Label lblProgramador = new Label();
    Label lblhvm = new Label();
    Label lblsrg = new Label();
    Label lblTeoria = new Label();
    Label lblDiseno = new Label();
    Label lbljmod = new Label();

  public AcercaFrame() {
      try {
          jbInit();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  void jbInit() throws Exception {
      btnCerrar.setLabel("Cerrar");
      btnCerrar.setBounds(new Rectangle(170, 260, 71, 25));
      btnCerrar.addActionListener(new AcercaFrame_btnCerrar_actionAdapter(this));
      this.setVisible(true);
      this.setBackground(SystemColor.control);
      this.setTitle("Acerca");
      this.addWindowListener(new AcercaFrame_this_windowAdapter(this));
      this.setLayout(null);
      this.setSize(400, 320);
      this.setLocation(200, 200);
      lblTitle.setFont(new java.awt.Font("Dialog", 1, 14));
      lblTitle.setForeground(Color.black);
      lblTitle.setText("Estudio de Proyectiles");
      lblTitle.setBounds(new Rectangle(125, 40, 190, 15));
      lblCopyright.setText("(c) 2004 Universidad de Sonora");
      lblCopyright.setBounds(new Rectangle(20, 80, 175, 15));
      lblProgramador.setText("Programador:");
      lblProgramador.setBounds(new Rectangle(20, 110, 80, 15));
      lblhvm.setText("Héctor Antonio Villa Martínez   Email: hvilla@gauss.mat.uson.mx");
      lblhvm.setVisible(true);
      lblhvm.setBounds(new Rectangle(30, 125, 360, 15));
      lblTeoria.setText("Teoría:");
      lblTeoria.setBounds(new Rectangle(20, 150, 46, 15));
      lblsrg.setText("Saúl Robles García   Email: robles@fisica.uson.mx");
      lblsrg.setBounds(new Rectangle(30, 165, 350, 15));
      lblDiseno.setText("Diseño:");
      lblDiseno.setBounds(new Rectangle(20, 190, 53, 15));
      lbljmod.setLocale(java.util.Locale.getDefault());
      lbljmod.setText("José Manuel Ochoa de la Torre   Email: jmochoa@arq.uson.mx");
      lbljmod.setBounds(new Rectangle(30, 205, 365, 15));
      this.add(lblTitle, null);
      this.add(btnCerrar, null);
      this.add(lblCopyright, null);
      this.add(lblProgramador, null);
      this.add(lblhvm, null);
      this.add(lblTeoria, null);
      this.add(lblsrg, null);
      this.add(lblDiseno, null);
      this.add(lbljmod, null);
  }

  void btnCerrar_actionPerformed(ActionEvent e) {
      this.dispose();
  }

  void this_windowClosing(WindowEvent e) {
      this.dispose ();
  }
}

class AcercaFrame_btnCerrar_actionAdapter implements java.awt.event.ActionListener {
    AcercaFrame adaptee;

  AcercaFrame_btnCerrar_actionAdapter(AcercaFrame adaptee) {
      this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
      adaptee.btnCerrar_actionPerformed(e);
  }
}

class AcercaFrame_this_windowAdapter extends java.awt.event.WindowAdapter {
    AcercaFrame adaptee;

    AcercaFrame_this_windowAdapter(AcercaFrame adaptee) {
        this.adaptee = adaptee;
  }
  public void windowClosing(WindowEvent e) {
      adaptee.this_windowClosing(e);
  }
}
