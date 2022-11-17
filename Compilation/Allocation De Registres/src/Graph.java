
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
    public GraphColorException() {}
    public GraphColorException(String message) {super(message);}
}


 class InvalidColorException extends GraphColorException
 {
    public InvalidColorException() {}
    public InvalidColorException(String message) {super(message);}
}

 class ComparatorNodeParCouleur implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getColor() < o2.getColor())
            return -1;
        else if(o1.getColor() == o2.getColor())
            return 0;
        else return 1;
    }}
 class NotColorableException extends GraphColorException{
    public NotColorableException() {}
    public NotColorableException(String message) {super(message);}
}

class GraphException extends Exception {
    public GraphException() {
    }

    public GraphException(String message) {
        super(message);
    }
}

class EdgesNotFoundException extends GraphException{
    public EdgesNotFoundException() {}
    public EdgesNotFoundException(String message) {super(message);}
}
 class NodeNotFoundException extends GraphException{
    public NodeNotFoundException() {}
    public NodeNotFoundException(String message) {super(message);}
}

 class NegativeOrNullColorException extends GraphColorException{
    public NegativeOrNullColorException() {}
    public NegativeOrNullColorException(String message) {super(message);}
}




public class Graph implements Cloneable{
    /*attributes*/
    private ArrayList<Node> sommets;
    private ArrayList<Edges> aretes;
    private Deque<Spilled> spilled = new ArrayDeque<Spilled>();

    /*constructors*/
    public Graph(){
        sommets = new ArrayList<Node>();
        aretes = new ArrayList<Edges>();
    }

    public Graph(ArrayList<Node> sommets){
        this.sommets = sommets;
        aretes = new ArrayList<Edges>();
    }

    public Graph(ArrayList<Node> sommets, ArrayList<Edges> aretes){
        this.sommets = sommets;
        this.aretes = aretes;
    }

    /*methods*/
    //getters
    public ArrayList<Node> getNodes(){return sommets;}
    public ArrayList<Edges> getEdgess(){return aretes;}

    //toString
    @Override
    public String toString(){
        return "Nodes : " + this.sommets.toString() +
                "\nArêtes : " + this.aretes.toString() +
                "\nSpillés : " + this.spilled.toString();
    }

    //clone
    @Override
    public Object clone() throws CloneNotSupportedException{
        Object obj = super.clone();
        Graph clonedGraph = (Graph)obj;

        clonedGraph.sommets = new ArrayList<Node>();
        clonedGraph.sommets.addAll(this.sommets);

        clonedGraph.aretes = new ArrayList<Edges>();
        clonedGraph.aretes.addAll(this.aretes);

        clonedGraph.spilled = new ArrayDeque<Spilled>();
        clonedGraph.spilled.addAll(this.spilled);

        return clonedGraph;
    }

    //other methods

    public int ordre() {
        return this.sommets.size();
    }


    public int taille(){
        return this.aretes.size();
    }

    public boolean isNode(Node sommet) {
        return this.sommets.contains(sommet);
    }


    public boolean isEdges(Edges arete) {
        return this.aretes.contains(arete);
    }


    public boolean isSpilled(Node sommet) {
        for (Spilled s : this.spilled)
            if(s.getNode().equals(sommet))
                return true;
        return false;
    }

    public void supprimerNode(Node sommet)
            throws NodeNotFoundException{
        if(!this.isNode(sommet))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + sommet + " n'est pas un sommet du graphe");
        else
            this.sommets.remove(sommet);
    }


    public void supprimerEdges(Edges arete)
            throws EdgesNotFoundException{
        if(!this.isEdges(arete))
            throw new EdgesNotFoundException("Erreur : l'arête "
                    + arete + " n'est pas une arête du graphe");
        else
            this.aretes.remove(arete);
    }


    private void empileSpilled(Node sommet){
        this.spilled.push(new Spilled(sommet));
    }


    public Spilled depileSpilled(){
        return this.spilled.pop();
    }


    public int degre(Node sommet)
            throws NodeNotFoundException{
        if (!this.isNode(sommet))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + sommet + " n'est pas un sommet du graphe");

        int deg = 0;
        for (Edges arete : aretes){
            if (arete.isIncidente(sommet))
                deg++;
        }

        return deg;
    }


    private int minDegre() {
        return Collections.min(
                sommets
                        .stream()
                        .map(s -> {
                            try {
                                return this.degre(s);
                            } catch (NodeNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList()));
    }


    private int maxDegre() {
        return Collections.max(
                sommets
                        .stream()
                        .map(s -> {
                            try {
                                return this.degre(s);
                            } catch (NodeNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList()));
    }


    public void trierNodesParCouleur() {
        Collections.sort(this.sommets, new ComparatorNodeParCouleur());
    }


    public void trierNodesParCouleur(ArrayList<Node> sommets) {
        Collections.sort(sommets, new ComparatorNodeParCouleur());
    }


    public ArrayList<Node> voisins(Node sommet){
        ArrayList<Node> voisins = new ArrayList<>();
        for (Edges arete : this.aretes) {
            if (arete.getFirst().equals(sommet))
                voisins.add(arete.getSecond());
            else if(arete.getSecond().equals(sommet))
                voisins.add(arete.getFirst());
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

        for (Node sommet : this.sommets)
            if(arete.isIncidente(sommet))
                sommetsIncidents.add(sommet);
        return sommetsIncidents;
    }

    /**
     * renvoie la liste des arêtes incidentes au sommet sommet
     * @param sommet le sommet dont les arêtes incidentes on souhaite récupérer
     * @return la liste des arêtes incidentes à sommet
     * @throws NodeNotFoundException si le sommet n'est pas un sommet du graphe
     */
    private ArrayList<Edges> aretesIncidentes(Node sommet)
            throws NodeNotFoundException{
        if (!this.isNode(sommet))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + sommet + " n'est pas un sommet du graphe");
        ArrayList<Edges> incidentes = new ArrayList<>();
        for (Edges arete : this.aretes)
            if(arete.isIncidente(sommet))
                incidentes.add(arete);
        return incidentes;
    }

    /**
     * supprime les arêtes incidentes au sommet "sommet" dans le graphe
     * @param sommet le sommet dont les arêtes incidentes sont à supprimer
     * @throws NodeNotFoundException si le sommet n'est pas un sommet du graphe
     */
    private void supprimerEdgessIncidentes(Node sommet)
            throws NodeNotFoundException{
        if (!this.isNode(sommet))
            throw new NodeNotFoundException("Erreur : le sommet "
                    + sommet + " n'est pas un sommet du graphe");

        ListIterator<Edges> iter = aretes.listIterator();
        while(iter.hasNext()){
            if (iter.next().isIncidente(sommet))
                iter.remove();
        }
    }


    public boolean estTrivialColorable(Node sommet, int k)
            throws NodeNotFoundException,
            InvalidColorException{
        if (k <= 0)
            throw new InvalidColorException("Erreur : " + k
                    + " n'est pas un nombre de couleurs valide");

        if (this.isNode(sommet) && this.degre(sommet) < k &&
                !sommet.isColored() && this.degre(sommet) == this.minDegre())
            return true;
        return false;
    }


    public Node trivialColorable(int k)
            throws InvalidColorException,
            NodeNotFoundException{
        for (Node sommet : this.sommets)
            if(estTrivialColorable(sommet, k))
                return sommet;
        return null;
    }


    public Node spilled()
            throws NodeNotFoundException {
        for (Node sommet : this.sommets)
            if(this.degre(sommet) == this.maxDegre())
                return sommet;
        return null;
    }


    private void attribueCouleur(Node sommet, ArrayList<Edges> incidentes, int k)
            throws NodeNotFoundException,
            GraphColorException{
        if (!this.isNode(sommet))
            throw new NodeNotFoundException("Erreur : le sommet"
                    + sommet + " n'est pas un sommet du graphe");

        if (k <= 0)
            throw new InvalidColorException("Erreur : " + k
                    + " n'est pas un nombre de couleurs valide");

        if (sommet.isColored())
            return;

        if (!this.isSpilled(sommet))
            this.aretes.addAll(this.colorerEdgessIncidentesSaufNode(incidentes, sommet));

        if (this.voisins(sommet).size() == 0)
            sommet.setColor(1);
        else {
            int couleur;
            for (Node voisin : this.voisins(sommet)) {
                if (!sommet.isColored())
                    couleur = 1;
                else
                    couleur = sommet.getColor();
                while(couleur <= k && couleur == voisin.getColor())
                    couleur++;
                if (couleur > k) {
                    if (this.isSpilled(sommet)) {
                        sommet.setColor(Node.COULEUR_DEFAUT);
                        System.err.println("Erreur : le sommet " + sommet + " spillé n'est pas "
                                + "coloriable avec " + k + " couleurs en coloriage optimiste");
                        break;
                    }
                    else
                        throw new NotColorableException("Erreur : le graphe n'est pas " + k + "-coloriable");
                }
                else
                    sommet.setColor(couleur);
            }
            this.aretes = this.colorerEdgessIncidentesNode(this.aretes, sommet);
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
            this.attribueCouleur(this.sommets.get(0), null, k);
            return this;
        }

        Graph graphe = (Graph)this.clone();
        Node sommet = this.trivialColorable(k);
        if (sommet != null){
            graphe.supprimerEdgessIncidentes(sommet);
            graphe.supprimerNode(sommet);

            graphe = graphe.colorier(k);

            if(!graphe.isNode(sommet))
                graphe.sommets.add(sommet);

            ArrayList<Edges> incidentes = this.aretesIncidentes(sommet);
            graphe.attribueCouleur(sommet, incidentes, k);
        }
        else {
            sommet = this.spilled();
            graphe.empileSpilled(sommet);
            graphe.supprimerEdgessIncidentes(sommet);
            graphe.supprimerNode(sommet);

            graphe = graphe.colorier(k);

            ArrayList<Edges> incidentes = this.aretesIncidentes(sommet);
            graphe.spilled.peek().getEdges().addAll(
                    graphe.colorerEdgessIncidentesSaufNode(incidentes, sommet));

        }
        return graphe;
    }

    private void colorierSpilled(int k)
            throws NodeNotFoundException,
            GraphColorException {
        for (Spilled s : this.spilled) {
            this.sommets.add(s.getNode());
            this.aretes.addAll(s.getEdges());
        }

        for (Spilled s : this.spilled)
            this.attribueCouleur(s.getNode(), s.getEdges(), k);
        int i = this.spilled.size();
        while (i > 0) {
            this.spilled.pop();
            i--;
        }
    }


    private ArrayList<Edges> colorerEdgessIncidentesNode(ArrayList<Edges> aretes, Node sommet){
        ArrayList<Edges> colores = new ArrayList<>();
        for (Edges arete : aretes) {
            if(arete.getFirst().getValue().equals(sommet.getValue()))
                colores.add(new Edges(
                        new Node(
                                sommet.getValue(), sommet.getColor()),
                        arete.getSecond()));
            else if (arete.getSecond().getValue().equals(sommet.getValue()))
                colores.add(new Edges(
                        arete.getFirst(),
                        new Node(sommet.getValue(), sommet.getColor())));
            else if(arete.isColored())
                colores.add(arete);
        }

        return colores;
    }

    private ArrayList<Edges> colorerEdgessIncidentes(ArrayList<Edges> aretes){
        ArrayList<Edges> in = new ArrayList<>();

        for (Node vertex : this.sommets)
            in.addAll(this.colorerEdgessIncidentesNode(aretes, vertex));

        return in;
    }

    private ArrayList<Edges> colorerEdgessIncidentesSaufNode(ArrayList<Edges> aretes, Node sommet) {
        ArrayList<Edges> in = new ArrayList<>();

        for (Node vertex : this.sommets)
            if (!vertex.equals(sommet))
                in.addAll(this.colorerEdgessIncidentesNode(aretes, vertex));

        return in;
    }
}
