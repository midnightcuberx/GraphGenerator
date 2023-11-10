import java.util.*;

public class RandomGraphGenerator {
    private ArrayList<Graph> graphs;
    public RandomGraphGenerator(){
        graphs = new ArrayList<>();
    }

    public Graph createGraph(GraphType gt, int n){
        Graph g;
        switch (gt){
            case Simple:
                g = new SimpleGraph(n);
                graphs.add(g);
                return g;
            case Directed:
                g = new Digraph(n);
                graphs.add(g);
                return g;
            default:
                throw new IllegalArgumentException("Invalid type!");
                
        }
    }

    public Graph createGraph(GraphType gt){
        return createGraph(gt, 6);
    }
    public ArrayList<Graph> getGraphs(){
        return graphs;
    }

}
