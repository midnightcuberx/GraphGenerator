import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSManager {
    private Graph graph;
    private int order;
    private int size;
    //private double girth;
    private int [] level;
    private int [] bfsOrder;
    private int count;
    private int numComponents;
    private ArrayList<Integer []> treeEdges;

    public BFSManager(Graph g){
        graph = g;
        graph.setBfsManager(this);
        order = g.getOrder();
        size = g.getSize();
        level = new int [order];
        for (int i = 0; i < order; i++){
            level[i] = -1;
        }
        //System.out.println(Arrays.toString(level));
        bfsOrder = new int [order];
        count = 0;
        numComponents = 0;
        treeEdges = new ArrayList<>();
        //girth = Double.POSITIVE_INFINITY;
    }

    public void bfs(){
        for (int i = 0; i < order; i++){
            if (level[i] != -1){continue;}
            bfsVisit(i);
            numComponents++;
        }
    }

    public void bfsVisit(int i){
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        level[i] = 0;
        
        while (!q.isEmpty()){
            int node = q.remove();
            bfsOrder[count++] = node;
            for (int neighbour: graph.getAdjacentNodes(node)){
                if (level[neighbour] != -1){continue;}
                treeEdges.add(new Integer[] {node, neighbour});
                level[neighbour] = level[node] + 1;
                q.add(neighbour);
            }
        }
    }

    public ArrayList<Integer []> getBFSEdges(){return treeEdges;}
    public int [] getLevels(){return level;}
    public int getNumComponents(){return numComponents;}
    public int [] getBfsOrder(){return bfsOrder;}
    public Graph getGraph(){return graph;}
    //private int getGirth(){return girth;}
}
