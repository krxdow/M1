/**
 * @author Fanus Ludovic
 */

/** * @author AMAH GNIMDOU RICHARD
 */
import java.util.ArrayList;

class Test {

    public static Graph fillPetitGraph() {
        ArrayList<Node> petitVertices = new ArrayList<>();
        petitVertices.add(new Node("x"));
        petitVertices.add(new Node("y"));
        petitVertices.add(new Node("z"));
        petitVertices.add(new Node("t"));
        petitVertices.add(new Node("u"));
        petitVertices.add(new Node("v"));

        ArrayList<Edges> petitEdges = new ArrayList<>();
        petitEdges.add(new Edges(new Node("x"), new Node("y")));
        petitEdges.add(new Edges(new Node("x"), new Node("u")));
        petitEdges.add(new Edges(new Node("x"), new Node("v")));
        petitEdges.add(new Edges(new Node("y"), new Node("t")));
        petitEdges.add(new Edges(new Node("y"), new Node("u")));
        petitEdges.add(new Edges(new Node("z"), new Node("v")));
        petitEdges.add(new Edges(new Node("t"), new Node("v")));

        Graph petitGraph = new Graph(petitVertices, petitEdges);

        return petitGraph;
    }

    public static Graph fillGrandGraph() {
        ArrayList<Node> grandVertices = new ArrayList<>();

        grandVertices.add(new Node("b"));
        grandVertices.add(new Node("c"));
        grandVertices.add(new Node("d"));
        grandVertices.add(new Node("e"));
        grandVertices.add(new Node("f"));
        grandVertices.add(new Node("g"));
        grandVertices.add(new Node("h"));
        grandVertices.add(new Node("j"));
        grandVertices.add(new Node("k"));
        grandVertices.add(new Node("m"));

        ArrayList<Edges> grandEdges = new ArrayList<>();

        grandEdges.add(new Edges(new Node("b"), new Node("c")));
        grandEdges.add(new Edges(new Node("b"), new Node("d")));
        grandEdges.add(new Edges(new Node("b"), new Node("e")));
        grandEdges.add(new Edges(new Node("b"), new Node("k")));
        grandEdges.add(new Edges(new Node("b"), new Node("m")));
        grandEdges.add(new Edges(new Node("c"), new Node("m")));
        grandEdges.add(new Edges(new Node("d"), new Node("k")));
        grandEdges.add(new Edges(new Node("d"), new Node("m")));
        grandEdges.add(new Edges(new Node("e"), new Node("f")));
        grandEdges.add(new Edges(new Node("e"), new Node("j")));
        grandEdges.add(new Edges(new Node("e"), new Node("m")));
        grandEdges.add(new Edges(new Node("f"), new Node("j")));
        grandEdges.add(new Edges(new Node("f"), new Node("m")));
        grandEdges.add(new Edges(new Node("g"), new Node("h")));
        grandEdges.add(new Edges(new Node("g"), new Node("j")));
        grandEdges.add(new Edges(new Node("g"), new Node("k")));
        grandEdges.add(new Edges(new Node("h"), new Node("j")));
        grandEdges.add(new Edges(new Node("j"), new Node("k")));

        Graph grandGraph = new Graph(grandVertices, grandEdges);

        return grandGraph;
    }

    public static Graph fillGraphDiamant() {
        ArrayList<Node> diamantVertices = new ArrayList<>();
        diamantVertices.add(new Node("x"));
        diamantVertices.add(new Node("y"));
        diamantVertices.add(new Node("z"));
        diamantVertices.add(new Node("t"));

        ArrayList<Edges> diamantEdges = new ArrayList<>();
        diamantEdges.add(new Edges(new Node("x"), new Node("y")));
        diamantEdges.add(new Edges(new Node("y"), new Node("z")));
        diamantEdges.add(new Edges(new Node("z"), new Node("t")));
        diamantEdges.add(new Edges(new Node("t"), new Node("x")));

        Graph diamantGraph = new Graph(diamantVertices, diamantEdges);

        return diamantGraph;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Graph petitGraph = fillPetitGraph();
        System.out.println("\n");
        System.out.println("Pretit graphe\n===============");
        System.out.println(petitGraph);

        try {
            System.out.println("\n");
            System.out.println("2-coloriage du petit graphe\n===========================");
            petitGraph = petitGraph.colorierOptimiste(2);
            System.out.println(petitGraph);

            petitGraph = fillPetitGraph();
            System.out.println("\n");
            System.out.println("3-coloriage du petit graphe\n===========================");
            petitGraph = petitGraph.colorierOptimiste(3);
            System.out.println(petitGraph);

            Graph grandGraph = fillGrandGraph();
            System.out.println("\n");
            System.out.println("Grand graphe\n===============");
            System.out.println(grandGraph);
            System.out.println("\n");
            System.out.println("4-coloriage du grand graphe\n===========================");
            grandGraph = grandGraph.colorierOptimiste(4);
            System.out.println(grandGraph);

            Graph diamantGraph = fillGraphDiamant();
            System.out.println("\n");
            System.out.println("Graph diamant\n===============");
            System.out.println(diamantGraph);
            System.out.println("\n");
            System.out.println("2-coloriage du graphe diamant\n===========================");
            diamantGraph = diamantGraph.colorierOptimiste(2);
            System.out.println(diamantGraph);

        } catch (NodeNotFoundException | GraphColorException e) {
            e.printStackTrace();
        }
    }
}