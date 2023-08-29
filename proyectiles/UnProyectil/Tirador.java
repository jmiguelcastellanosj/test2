
/**
 * <p>Title: Un Proyectil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad de Sonora</p>
 * @author Héctor Antonio Villa Martínez y Saúl Robles García
 * @version 1.0
 */

public class Tirador
{
    private double x, y;

   public Tirador(double x, double y)
   {
       this.x = x;
       this.y = y;
   }

   public double getX ()
   {
       return x;
   }

   public double getY ()
   {
       return y;
   }

   public void setX (double x)
   {
       this.x = x;
   }

   public void setY (double y)
   {
       this.y = y;
   }
}
