import java.util.Random;
import java.util.ArrayList;

abstract class Graph {
    protected ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    protected int order;
    protected int size;
    protected Random rand = new Random();


    public abstract void generate();
    public int getOrder(){return order;}
    public int getSize(){return size;}
    public ArrayList<Integer> getAdjacentNodes(int n){
        return adjList.get(n);
    }
    public ArrayList<ArrayList<Integer>> getAdjList(){
        return adjList;
    }
    public boolean isAdjacent(int i, int j){
        return getAdjacentNodes(i).contains(j);
    }

}
