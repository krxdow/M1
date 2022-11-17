/**
 * @author Fanus Ludovic
 */
/** * @author AMAH GNIMDOU RICHARD
 */

import java.util.*;
import java.util.stream.Collectors;


public interface GraphInterface {


    /*methods*/
    //getters
    public ArrayList<Node> getNodes();
    public ArrayList<Edges> getEdges();

    //toString
    @Override
    public String toString();

    //clone

    public Object clone() throws CloneNotSupportedException;
    //other methods
    /**
     * L'ordre du graphe ou le nombre de ses nodes
     * @return le nombre de nodes du graphe
     */
    public int ordre() ;

    /**
     * La taille du graphe ou le nombre de ses arêtes
     * @return le nombre des arêtes du graphe
     */
    public int taille();
    /**
     * vérifie si node est un node du graphe
     * @param node le node à tester
     * @return true si node est bien un node du graphe,
     * false sinon
     */
    public boolean isNode(Node node);
    /**
     * vérifie si edge est une arête du graphe
     * @param edge l'arête à tester
     * @return true si edge est bien une arête du graphe,
     * false sinon
     */
    public boolean isEdges(Edges edge) ;
    /**
     * vérifie si node est un node du graphe,
     * spillé lors du coloriage du graphe
     * @param node le node à tester
     * @return true si node a bien spillé,
     * false sinon
     */
    public boolean isSpilled(Node node) ;
    /**
     * supprime un node du graphe
     * @param node le node à supprimer
     * @throws NodeNotFoundException levée si node n'est pas un node
     * du graphe
     */
    public void supprimerNode(Node node);

    /**
     * supprime une arête du graphe
     * @param edge l'arête à supprimer
     * @throws EdgesNotFoundException levée si edge n'est pas une arête
     * du graphe
     */
    public void supprimerEdges(Edges edge);


    /**
     * empile le node "node" sur la pile des nodes "spillés"
     *
     * @param node le node qui a spillé
     */
    private void empileSpilled(Node node) {

    }

    /**
     * dépile le dernier node spillé du graphe et le renvoie
     * @param node le dernier node spillé à dépiler
     * @return le node spillé dépilé
     */
    public Spilled depileSpilled();

    /**
     * Renvoie le degré d'un node du graphe. Autrement dit, renvoie
     * le nombre d'arêtes le reliant à d'autres nodes
     * @param node le node dont le degré on souhaite calculer
     * @return le degré du node "node", -1 si le node n'est pas un node du graphe
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     */
    public int degre(Node node)
            throws NodeNotFoundException;

    /**
     * renvoie le minimum des degrés de tous les nodes du graphe
     *
     * @return le degré minimum parmis tous les degrés de tous les nodes
     * du graphe.
     */
    private int minDegre() {
        return 0;
    }

    /**
     * renvoie le maximum des degrés de tous les nodes du graphe
     *
     * @return le degré maximum parmis tous les degrés de tous les nodes
     * du graphe.
     */
    private int maxDegre() {
        return 0;
    }

    /**
     * trier les nodes du graphe par ordre croissant sur leurs couleurs
     */
    public void trierNodesParCouleur() ;
    /**
     * trier les nodes passé en paramètre par ordre croissant sur leurs couleurs
     * @param nodes les nodes à trier
     */
    public void trierNodesParCouleur(ArrayList<Node> nodes) ;
    /**
     * Les voisins du node, ou l'ensemble des nodes qui y sont reliés par
     * une arête
     * @param node le node à tester
     * @return l'ensemble des nodes reliés au node "node" par une arête
     */
    public ArrayList<Node> voisins(Node node);

    /**
     * renvoie la liste des nodes dans un graphe auxquels l'arête edge est incidente
     * @param l'edge du graphe à tester
     * @return si edge est une ârete du graphe renvoie
     * la liste des nodes auxquels edge est incidente,
     * @throws EdgesNotFoundException si l'arête n'est pas une arête du graphe
     * sinon renvoie null
     */
    public ArrayList<Node> nodesIncidents(Edges edge)
            throws EdgesNotFoundException;


    /**
     * renvoie la liste des arêtes incidentes au node node
     *
     * @param node le node dont les arêtes incidentes on souhaite récupérer
     * @return la liste des arêtes incidentes à node
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     */
    private ArrayList<Edges> aretesIncidentes(Node node)
            throws NodeNotFoundException {
        return null;
    }

    /**
     * supprime les arêtes incidentes au node "node" dans le graphe
     *
     * @param node le node dont les arêtes incidentes sont à supprimer
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     */
    private void supprimerEdgessIncidentes(Node node)
            throws NodeNotFoundException {

    }

    /**
     * Un node est trivialement colorable ssi il est un node du graphe et
     * son degré est inférieur à une constante k désignant le nombre de couleurs
     * utilisées pour colorier le graphe
     * @param node le node à tester
     * @param k le nombre de couleurs utilisées pour colorier le graphe
     * @return true si "node" est trivialement colorable, false sinon
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     * @throws InvalidColorException si le nombre de couleurs à tester est négatif ou nul
     */
    public boolean estTrivialColorable(Node node, int k)
            throws NodeNotFoundException,
            InvalidColorException;





    /**
     * renvoie le premier node du graphe trivialement colorable par k s'il en existe un
     * @param k le nombre de couleurs utilisées pour colorier le graphe
     * @return le premier node du graphe trivialement colorable par k s'il en existe un,
     * sinon renvoie null
     * @throws NodeNotFoundException cf. estTrivialColorable
     * @throws InvalidColorException si le nombre de couleurs à tester est négatif ou nul
     */
    public Node trivialColorable(int k);


    /**
     * renvoie le premier node du graphe qui a spillé
     * On choisit en utilisant l'heuristique maxDegré sur
     * les nodes du graphe
     * @return le node qui a spillé ou null si aucun node n'a spillé
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     */
    public Node spilled()
            throws NodeNotFoundException;

    /**
     * attribut une couleur k à un node du graphe et extrémité des arêtes "incidentes"
     *
     * @param node       le node auquel on souhaite attribuer une couleur
     * @param incidentes les arêtes incidentes à node
     * @param k          la couleur à attribuer au node
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     * @throws GraphColorException   si k est négative ou nulle InvalidColorException est levée,
     *                               sinon si le graphe est non colorable par k couleurs, NotColorableException
     *                               sera levée
     */
    private void attribueCouleur(Node node, ArrayList<Edges> incidentes, int k) {

    }

    /**
     * colorie le graphe avec k couleurs en essayant de colorier les nodes spillés
     * @param k le nombre de couleurs avec lesquelles on souhaite colorier le graphe
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     * @throws GraphColorException
     * si k est négative ou nulle NegativeOrNullColorException est levée,
     * sinon si le graphe est non colorable par k couleurs, NotColorableException
     * sera levée
     * @throws CloneNotSupportedException
     */
    public Graph colorierOptimiste(int k) throws NodeNotFoundException,
            GraphColorException,
            CloneNotSupportedException;

    /**
     * colorie le graphe avec k couleurs
     *
     * @param k le nombre de couleurs avec lesquelles on souhaite colorier le graphe
     * @throws NodeNotFoundException      si le node n'est pas un node du graphe
     * @throws GraphColorException        si k est négative ou nulle NegativeOrNullColorException est levée,
     *                                    sinon si le graphe est non colorable par k couleurs, NotColorableException
     *                                    sera levée
     * @throws CloneNotSupportedException
     */
    private Graph colorier(int k) {
        return null;
    }

    /**
     * essaye de colorier les nodes spillés une fois les autres nodes
     * sont colorés (coloriage optimiste)
     *
     * @param k le nombre de couleurs possibles
     * @throws NodeNotFoundException si le node n'est pas un node du graphe
     * @throws GraphColorException   si k est négative ou nulle NegativeOrNullColorException est levée,
     *                               sinon si le graphe est non colorable par k couleurs, NotColorableException
     *                               sera levée
     */
    private void colorierSpilled(int k)
            throws NodeNotFoundException,
            GraphColorException {

    }

    /**
     * colorer les nodes des arêtes ayant chacun la même valeur que node
     * avec la couleur de node et les renvoyer
     *
     * @param edges les arêtes dont les nodes sont à colorer
     * @param node  le node dont la couleur est choisie pour colorer les nodes
     *              des arêtes
     * @return l'ensemble des arêtes dont les nodes sont colorés par la couleur de node
     */
    private ArrayList<Edges> colorerEdgessIncidentesNode(ArrayList<Edges> edges, Node node) {
        return null;
    }

    /**
     * colorer les nodes de toutes les arêtes incidentes à tous les nodes colorés
     * et les renvoyer
     *
     * @param edges les arêtes dont les nodes sont à colorer
     * @return l'ensemble des arêtes dont les nodes sont colorés
     */
    private ArrayList<Edges> colorerEdgessIncidentes(ArrayList<Edges> edges) {
        return null;
    }

    /**
     * colorer les nodes des arêtes incidentes à tous les nodes sauf node
     * et les renvoyer
     *
     * @param edges les arêtes incidentes à tous les nodes sauf node
     *               et dont les nodes sont à colorer
     * @param node le node à ne pas considérer lors du coloriage
     * @return les arêtes incidentes à tous les nodes sauf node
     * dont les nodes sont colorés
     */
    private ArrayList<Edges> colorerEdgessIncidentesSaufNode(ArrayList<Edges> edges, Node node) {
        return null;
    }

}
