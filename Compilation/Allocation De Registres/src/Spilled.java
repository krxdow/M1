/**
 * @author Fanus Ludovic
 */

/** * @author AMAH GNIMDOU RICHARD
 */

import java.util.ArrayList;
public class Spilled {

 /*attributes*/
 private Node node;
 private ArrayList<Edges> edges;

 /*constructors*/
 public Spilled(Node node) {
  this.node = node;
  this.edges = new ArrayList<>();
 }

 public Spilled(Node node, ArrayList<Edges> edges) {
  this.node = node;
  this.edges = Spilled.this.edges;
 }

 /*methods*/
 //getters & setters
 public Node getNode() {
  return this.node;
 }

 public void setNode(Node node) {
  this.node = node;
 }

 public ArrayList<Edges> getEdges() {
  return this.edges;
 }

 public void setAretes(ArrayList<Edges> edges) {
  this.edges = Spilled.this.edges;
 }

 //toString
 @Override
 public String toString() {
  return "{" + this.node + ", " + this.edges + "}";
 }
}
