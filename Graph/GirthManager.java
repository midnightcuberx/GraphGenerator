import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class GirthManager {
    private Graph g;
    private int girth;
    private int order;
    //private int underlyingGirth;

    public GirthManager(Graph g){
        this.g = g;
        girth = Integer.MAX_VALUE;
        //underlyingGirth = Integer.MAX_VALUE;
        order = g.getOrder();
    }

    public void girthOnePass(int i){
        ArrayList<Integer> adjNodes;
        int [] level = new int [order];
        for (int j = 0; j < order; j++){
            level[j] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        level[i] = 0;
        
        while (!q.isEmpty()){
            int node = q.remove();
            if (g instanceof Digraph){
                adjNodes = ((Digraph) g).getUnderlyingAdjacent(node);
            }
            else{
                adjNodes = g.getAdjacentNodes(node);
            }

            for (int neighbour: adjNodes){
                if (level[neighbour] == -1){
                    level[neighbour] = level[node] + 1;
                    q.add(neighbour);
                } else if (level[neighbour] >= level[node]){
                    int dist = level[neighbour] + level[node] + 1;
                    if (dist < girth){
                        girth = dist;
                    }
                }
            }
        }
        // add switch case with type of girth we want
    }

    public void calculateGirth(){
        for (int i = 0; i < order; i++){
            girthOnePass(i);
        }
    }

    public int getGirth(){
        return girth;
    }


}
