
/**
 * <p>Title: DatosV</p>
 * <p>Description: Representa los datos de velocidad</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author H�ctor Antonio Villa Mart�nez y Sa�l Robles Garc�a
 * @version 1.0
 */

public class DatosV {
  private double t;
  private double vx;
  private double vy;
  private double x;
  private double y;

  public DatosV (double t, double vx, double vy, double x, double y) {
    this.t = t;
    this.vx = vx;
    this.vy = vy;
    this.x = x;
    this.y = y;
  }

  public double getT ()
  {
    return t;
  }

  public double getVx ()
  {
    return vx;
  }

  public double getVy ()
  {
    return vy;
  }

  public double getX ()
  {
    return x;
  }

  public double getY ()
  {
    return y;
  }
}
