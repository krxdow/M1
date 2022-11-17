/**
 * @author Fanus Ludovic
 */

/**
 * * @author AMAH GNIMDOU RICHARD
 */
public class Node implements Cloneable,NodeInterface{
 /*attributes*/
 private String value;
 private int color;
 public static final int COULEUR_DEFAUT = 0;

 /*constructors*/
 public Node(String value){
  this.value = value;
  color = COULEUR_DEFAUT;
 }

 public Node(String value, int color){
  this.value = value;
  this.color = color;
 }

 /*methods*/
 //getters & setters
 public String getValue(){return this.value;}
 public int getColor(){return this.color;}
 public void setValue(String value){this.value = value;}
 public void setColor(int color){
  if (color > COULEUR_DEFAUT)
   this.color = color;
  else
   this.color = COULEUR_DEFAUT;
 }

 //toString
 @Override
 public String toString(){
  return "(" + value + ", " + color + ")";
 }

 //equals
 @Override
 public boolean equals(Object obj){
  if (this == obj)
   return true;

  if(getClass() != obj.getClass())
   return false;

  Node sommet = (Node)obj;
  return this.value.equals(sommet.value) && this.color == sommet.color;
 }

 //clone
 @Override
 public Object clone() throws CloneNotSupportedException{
  Object obj = super.clone();
  Node clonedSommet = (Node)obj;
  clonedSommet.color = this.color;
  clonedSommet.value = this.value;
  return clonedSommet;
 }

 //other methods
 /**
  * teste si un sommet est colorié
  * @return true si le sommet est déjà colorié (n'a pas la color par défaut), false sinon
  */
 public boolean isColored(){
  return this.color != COULEUR_DEFAUT;
 }
}
