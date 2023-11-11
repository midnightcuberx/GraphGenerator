public class DFSManager {
    private Graph graph;
    private int order;
    private int size;
    private int [] done;
    private int [] seen;
    private int [] dfsOrder;
    private int [] visited;
    private int time;
    private int count;
    private int numComponents;

    public DFSManager(Graph g){
        graph = g;
        graph.setDfsManager(this);
        order = g.getOrder();
        size = g.getSize();
        done = new int [order];
        seen = new int [order];
        dfsOrder = new int [order];
        visited = new int [order];
        time = 0;
        count = 0;
        numComponents = 0;
    }

    /*public void setGraph(Graph g){
        this(g);
    }*/

    public void recursiveDfs(int i){
        if (visited[i] != 0){return;}
        //System.out.println(i);
        visited[i] = 1;
        dfsOrder[count++] = i;
        seen[i] = time++;
        for (int neighbour: graph.getAdjacentNodes(i)){
            recursiveDfs(neighbour);
        }
        done[i] = time++;
    }

    public void dfs(){
        for (int i = 0; i < order; i++){
            if (visited[i] == 0){
                recursiveDfs(i);
                numComponents++;
            }
        }
    }

    public int [] getSeen(){return seen;}
    public int [] getDone(){return done;}
    public int getNumComponents(){return numComponents;}
    public int [] getDfsOrder(){return dfsOrder;}
    public Graph getGraph(){return graph;}
}
