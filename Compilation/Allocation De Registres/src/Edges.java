/**
 * @author Fanus Ludovic
 */

/** * @author AMAH GNIMDOU RICHARD
 */
public class Edges implements EdgesInterface,Cloneable {

    private  Node first, second;

    public Edges(Node first,Node second){
        this.first=first;
        this.second=second;
    }


    public Node getFirst() {
        return this.first;
    }


    public Node getSecond() {
        return this.second;
    }


    public void setFirst(Node first) {
        this.first=first;
    }


    public void setSecond(Node second) {
                this.second=second;
    }


    //toString
    @Override
    public String toString(){
        return "(" + this.first + ", " + this.second + ")";
    }


    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Edges clonnedEdge = (Edges) obj;
        clonnedEdge.first=(Node) this.first;
        clonnedEdge.second=(Node) this.second;
        return clonnedEdge;
    }


    //other methods
    /**
     * test si l'arête courante est incidente au sommet "sommet"
     * @param node le sommet à tester
     * @return true si l'arête est incidente à "sommet", false sinon
     */
    public boolean isIncidente(Node node){
        return this.first.equals(node) || this.second.equals(node);
    }

    public boolean isColored() {
        return this.first.isColored() && this.second.isColored();
    }
}
