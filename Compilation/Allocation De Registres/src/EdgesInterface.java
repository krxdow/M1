/**
 * @author Fanus Ludovic
 */

/** * @author AMAH GNIMDOU RICHARD
 */
public interface EdgesInterface {

 public Node getFirst();
 public Node getSecond();
 public void setFirst(Node first);
 public void setSecond(Node second);


 public String toString();


 public Object clone() throws CloneNotSupportedException;

 /**
  * test si l'arête courante est incidente au sommet "sommet"
  * @param node le sommet à tester
  * @return true si l'arête est incidente à "sommet", false sinon
  */
 public boolean isIncidente(Node node);
 public boolean isColored();
}
