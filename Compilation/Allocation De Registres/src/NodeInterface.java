/**
 * @author Fanus Ludovic
 */

/** * @author AMAH GNIMDOU RICHARD
 */
public interface NodeInterface {


 public String getValue();

 public int getColor();

 public void setValue(String value);

 public void setColor(int color);


 public String toString();


 public boolean equals(Object obj);


 public Object clone() throws CloneNotSupportedException;

 //other methods

 /**
  * teste si un sommet est colorié
  *
  * @return true si le sommet est déjà colorié (n'a pas la color par défaut), false sinon
  */
 public boolean isColored();
}