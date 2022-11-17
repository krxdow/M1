
/**
 * @author Fanus Ludovic
 */
/** * @author AMAH GNIMDOU RICHARD
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.ListIterator;


 class GraphColorException extends GraphException{

    public GraphColorException(String message) {super(message);}
}


 class InvalidColorException extends GraphColorException
 {

    public InvalidColorException(String message) {super(message);}
}

 class ComparatorNodeParCouleur implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getColor(), o2.getColor());
    }}
 class NotColorableException extends GraphColorException{

    public NotColorableException(String message) {super(message);}
}

class GraphException extends Exception {

    public GraphException(String message) {
        super(message);
    }
}

class EdgesNotFoundException extends GraphException{

    public EdgesNotFoundException(String message) {super(message);}
}
 class NodeNotFoundException extends GraphException{

    public NodeNotFoundException(String message) {super(message);}
}






public class Graph implements Cloneable{
    /*attributes*/
    private ArrayList<Node> nodes;
    private ArrayList<Edges> edges;
    private Deque<Spilled> spilled = new ArrayDeque<>();

    /*constructors*/
    public Graph(){
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edges>();
    }

    public Graph(ArrayList<Node> nodes){
        this.nodes = nodes;
        edges = new ArrayList<Edges>();
    }

    public Graph(ArrayList<Node> nodes, ArrayList<Edges> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    /*methods*/
    //getters
    public ArrayList<Node> getNodes(){return nodes;}
    public ArrayList<Edges> getEdgess(){return edges;}

    //toString
    @Override
    public String toString(){
        return "Nodes : " + this.nodes.toString() +
                "\nArêtes : " + this.edges.toString() +
                "\nSpillés : " + this.spilled.toString();
    }

    //clone
    @Override
    public Object clone() throws CloneNotSupportedException{
        Object obj = super.clone();
        Graph clonedGraph = (Graph)obj;

        clonedGraph.nodes = new ArrayList<>();
        clonedGraph.nodes.addAll(this.nodes);

        clonedGraph.edges = new ArrayList<>();
        clonedGraph.edges.addAll(this.edges);

        clonedGraph.spilled = new ArrayDeque<>();
        clonedGraph.spilled.addAll(this.spilled);

        return clonedGraph;
    }

    //other methods

    public int ordre() {
        return this.nodes.size();
    }


    public int taille(){
        return this.edges.size();
    }

    public boolean isNode(Node node) {
        return this.nodes.contains(node);
    }


    public boolean isEdges(Edges arete) {
        return this.edges.contains(arete);
    }


    public boolean isSpilled(Node node) {
        for (Spilled s : this.spilled)
            if(s.getNode().equals(node))
                return true;
        return false;
    }

    public void supprimerNode(Node node)
            throws NodeNotFoundException{
        if(!this.isNode(node))
            throw new NodeNotFoundException("Erreur : le node "
                    + node + " n'est pas un node du graphe");
        else
            this.nodes.remove(node);
    }


    public void supprimerEdges(Edges edge)
            throws EdgesNotFoundException{
        if(!this.isEdges(edge))
            throw new EdgesNotFoundException("Erreur : l'arête "
                    + edge + " n'est pas une arête du graphe");
        else
            this.edges.remove(edge);
    }


    private void empileSpilled(Node node){
        this.spilled.push(new Spilled(node));
    }


    public Spilled depileSpilled(){
        return this.spilled.pop();
    }


    public int degre(Node node)
            throws NodeNotFoundException{
        if (!this.isNode(node))
            throw new NodeNotFoundException("Erreur : le node "
                    + node + " n'est pas un sommet du graphe");

        int deg = 0;
        for (Edges arete : edges){
            if (arete.isIncidente(node))
                deg++;
        }

        return deg;
    }


    private int minDegre() {
        return Collections.min(
                nodes
                        .stream()
                        .map(s -> {
                            try {
                                return this.degre(s);
                            } catch (NodeNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }).toList());
    }


    private int maxDegre() {
        return Collections.max(
                nodes
                        .stream()
                        .map(s -> {
                            try {
                                return this.degre(s);
                            } catch (NodeNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }).toList());
    }


    public void trierNodesParCouleur() {
        Collections.sort(this.nodes, new ComparatorNodeParCouleur());
    }


    public void trierNodesParCouleur(ArrayList<Node> nodes) {
        Collections.sort(nodes, new ComparatorNodeParCouleur());
    }


    public ArrayList<Node> voisins(Node node){
        ArrayList<Node> voisins = new ArrayList<>();
        for (Edges edge : this.edges) {
            if (edge.getFirst().equals(node))
                voisins.add(edge.getSecond());
            else if(edge.getSecond().equals(node))
                voisins.add(edge.getFirst());
        }

        this.trierNodesParCouleur(voisins);
        return voisins;
    }


    public ArrayList<Node> sommetsIncidents(Edges arete)
            throws EdgesNotFoundException{
        ArrayList<Node> sommetsIncidents = new ArrayList<>();
        if (!this.isEdges(arete))
            throw new EdgesNotFoundException("Erreur : l'arête "
                    + arete + " n'est pas une arête du graphe");

        for (Node node : this.nodes)
            if(arete.isIncidente(node))
                sommetsIncidents.add(node);
        return sommetsIncidents;
    }


    private ArrayList<Edges> aretesIncidentes(Node node)
            throws NodeNotFoundException{
        if (!this.isNode(node))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + node + " n'est pas un sommet du graphe");
        ArrayList<Edges> incidentes = new ArrayList<>();
        for (Edges arete : this.edges)
            if(arete.isIncidente(node))
                incidentes.add(arete);
        return incidentes;
    }


    private void supprimerEdgessIncidentes(Node node)
            throws NodeNotFoundException{
        if (!this.isNode(node))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + node + " n'est pas un sommet du graphe");

        edges.removeIf(edges1 -> edges1.isIncidente(node));
    }


    public boolean estTrivialColorable(Node node, int k)
            throws NodeNotFoundException,
            InvalidColorException{
        if (k <= 0)
            throw new InvalidColorException("Erreur : " + k
                    + " n'est pas un nombre de couleurs valide");

        if (this.isNode(node) && this.degre(node) < k &&
                !node.isColored() && this.degre(node) == this.minDegre())
            return true;
        return false;
    }


    public Node trivialColorable(int k)
            throws InvalidColorException,
            NodeNotFoundException{
        for (Node node : this.nodes)
            if(estTrivialColorable(node, k))
                return node;
        return null;
    }


    public Node spilled()
            throws NodeNotFoundException {
        for (Node node : this.nodes)
            if(this.degre(node) == this.maxDegre())
                return node;
        return null;
    }


    private void attribueCouleur(Node node, ArrayList<Edges> incidentes, int k)
            throws NodeNotFoundException,
            GraphColorException{
        if (!this.isNode(node))
            throw new NodeNotFoundException("Erreur : le sommet"
                    + node + " n'est pas un node du graphe");

        if (k <= 0)
            throw new InvalidColorException("Erreur : " + k
                    + " n'est pas un nombre de couleurs valide");

        if (node.isColored())
            return;

        if (!this.isSpilled(node))
            this.edges.addAll(this.colorerEdgessIncidentesSaufNode(incidentes, node));

        if (this.voisins(node).size() == 0)
            node.setColor(1);
        else {
            int couleur;
            for (Node voisin : this.voisins(node)) {
                if (!node.isColored())
                    couleur = 1;
                else
                    couleur = node.getColor();
                while(couleur <= k && couleur == voisin.getColor())
                    couleur++;
                if (couleur > k) {
                    if (this.isSpilled(node)) {
                        node.setColor(Node.COULEUR_DEFAUT);
                        System.err.println("Erreur : le sommet " + node + " spillé n'est pas "
                                + "coloriable avec " + k + " couleurs en coloriage optimiste");
                        break;
                    }
                    else
                        throw new NotColorableException("Erreur : le graphe n'est pas " + k + "-coloriable");
                }
                else
                    node.setColor(couleur);
            }
            this.edges = this.colorerEdgessIncidentesNode(this.edges, node);
        }
    }


    public Graph colorierOptimiste(int k) throws NodeNotFoundException,
            GraphColorException,
            CloneNotSupportedException {
        Graph graphe = this.colorier(k);
        graphe.colorierSpilled(k);
        return graphe;
    }


    private Graph colorier(int k)
            throws NodeNotFoundException,
            GraphColorException,
            CloneNotSupportedException{
        if (k <= 0)
            throw new InvalidColorException("Erreur : " + k
                    + " n'est pas un nombre de couleurs valide");

        if(this.ordre() == 1) {
            this.attribueCouleur(this.nodes.get(0), null, k);
            return this;
        }

        Graph graphe = (Graph)this.clone();
        Node node = this.trivialColorable(k);
        if (node != null){
            graphe.supprimerEdgessIncidentes(node);
            graphe.supprimerNode(node);

            graphe = graphe.colorier(k);

            if(!graphe.isNode(node))
                graphe.nodes.add(node);

            ArrayList<Edges> incidentes = this.aretesIncidentes(node);
            graphe.attribueCouleur(node, incidentes, k);
        }
        else {
            node = this.spilled();
            graphe.empileSpilled(node);
            graphe.supprimerEdgessIncidentes(node);
            graphe.supprimerNode(node);

            graphe = graphe.colorier(k);

            ArrayList<Edges> incidentes = this.aretesIncidentes(node);
            graphe.spilled.peek().getEdges().addAll(
                    graphe.colorerEdgessIncidentesSaufNode(incidentes, node));

        }
        return graphe;
    }

    private void colorierSpilled(int k)
            throws NodeNotFoundException,
            GraphColorException {
        for (Spilled s : this.spilled) {
            this.nodes.add(s.getNode());
            this.edges.addAll(s.getEdges());
        }

        for (Spilled s : this.spilled)
            this.attribueCouleur(s.getNode(), s.getEdges(), k);
        int i = this.spilled.size();
        while (i > 0) {
            this.spilled.pop();
            i--;
        }
    }


    private ArrayList<Edges> colorerEdgessIncidentesNode(ArrayList<Edges> edges, Node node){
        ArrayList<Edges> colores = new ArrayList<>();
        for (Edges arete : edges) {
            if(arete.getFirst().getValue().equals(node.getValue()))
                colores.add(new Edges(
                        new Node(
                                node.getValue(), node.getColor()),
                        arete.getSecond()));
            else if (arete.getSecond().getValue().equals(node.getValue()))
                colores.add(new Edges(
                        arete.getFirst(),
                        new Node(node.getValue(), node.getColor())));
            else if(arete.isColored())
                colores.add(arete);
        }

        return colores;
    }

    private ArrayList<Edges> colorerEdgessIncidentes(ArrayList<Edges> edges){
        ArrayList<Edges> in = new ArrayList<>();

        for (Node vertex : this.nodes)
            in.addAll(this.colorerEdgessIncidentesNode(edges, vertex));

        return in;
    }

    private ArrayList<Edges> colorerEdgessIncidentesSaufNode(ArrayList<Edges> edges, Node node) {
        ArrayList<Edges> in = new ArrayList<>();

        for (Node vertex : this.nodes)
            if (!vertex.equals(node))
                in.addAll(this.colorerEdgessIncidentesNode(edges, vertex));

        return in;
    }
}
