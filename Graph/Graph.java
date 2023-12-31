import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

abstract class Graph {
    protected ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    protected int order;
    protected int size;
    protected Random rand = new Random();
    protected static final int WIDTH = 1200;
    protected static final int HEIGHT = 770;
    protected static final int nodeHeight = 50;
    protected static final int centerX = WIDTH / 4- nodeHeight / 2;
    protected static final int centerY = HEIGHT/ 2 - nodeHeight / 2 - 30;
    protected static final int dist = WIDTH / 6;
    protected DFSManager dfsManager;
    protected BFSManager bfsManager;
    protected EccentricityManager eccentricityManager;
    protected GirthManager girthManager;
    protected int [] degreeSequence;


    protected abstract void generate();
    protected abstract void generateRandom();
    public abstract void drawLines(Graphics g, int i, int j);
    
    public int getOrder(){return order;}
    public int getSize(){return size;}
    public EccentricityManager getEccentricityManager(){return eccentricityManager;}
    public GirthManager getGirthManager(){return girthManager;}
    public DFSManager getDfsManager(){return dfsManager;}
    public void setDfsManager(DFSManager m){dfsManager = m;}
    public BFSManager getBfsManager(){return bfsManager;}
    public void setBfsManager(BFSManager m){bfsManager = m;}
    public ArrayList<Integer> getAdjacentNodes(int n){
        return adjList.get(n);
    }
    public ArrayList<ArrayList<Integer>> getAdjList(){
        return adjList;
    }
    public boolean isAdjacent(int i, int j){
        return getAdjacentNodes(i).contains(j);
    }

    public String toString(){
        return getAdjList().toString();
    }

    /*public void setDegreeSequence(){
        for (int i = 0; i < order; i++){
            degreeSequence[i] = adjList.get(i).size();
        }
        //Arrays.sort(degreeSequence);
    }*/

    public int [] getDegreeSequence(){
        return degreeSequence;
    }

    public void paintNodes(Graphics g){

        for (int i = 0; i < order; i++){
            int x = getX(i);
            int y = getY(i);

            g.setColor(Color.BLACK);
            g.fillOval(x, y, nodeHeight, nodeHeight);
            g.drawString(String.valueOf(i), x, y);
            for (int j: getAdjacentNodes(i)){
                drawLines(g, i, j);
            }
        }
    }

    public void drawDFS(Graphics g){
        paintNodes(g);
        ArrayList<Integer []> treeEdges = getDFSEdges();
        g.setColor(Color.RED);
        for (Integer [] edge: treeEdges){
            drawLines(g, edge[0], edge[1]);
        }
    }

    public void drawBFS(Graphics g){
        paintNodes(g);
        ArrayList<Integer []> treeEdges = getBFSEdges();
        g.setColor(Color.RED);
        for (Integer [] edge: treeEdges){
            drawLines(g, edge[0], edge[1]);
        }
    }

    public ArrayList<Integer []> getDFSEdges(){return dfsManager.getDFSEdges();}
    public ArrayList<Integer []> getBFSEdges(){return bfsManager.getBFSEdges();}

    public int getX(int i){return (int) Math.round(centerX + dist * Math.cos(2 * Math.PI * i / order));}
    public int getY(int i){return (int) Math.round(centerY + dist * Math.sin(2 * Math.PI * i / order));}
    public void dfs(){dfsManager.dfs();}
    public void bfs(){bfsManager.bfs();}
    public int [] getSeen(){return dfsManager.getSeen();}
    public int [] getDone(){return dfsManager.getDone();}
    public int [] getDfsOrder(){return dfsManager.getDfsOrder();}
    public int [] getBfsOrder(){return bfsManager.getBfsOrder();}
    public int getNumComponents(){return bfsManager.getNumComponents();}
    public int [] getLevels(){return bfsManager.getLevels();}

    public int getDiameter(){return eccentricityManager.getDiameter();}
    public int getRadius(){return eccentricityManager.getRadius();}
    public Object [] [] getDistanceMatrix(){return eccentricityManager.getDistanceMatrix();}
    public Object [] getEccentricityArray(){return eccentricityManager.getEccentricityArray();}
    public void setupDistanceMatrix(){eccentricityManager.allNodesDistance();}

    public int getGirth(){return girthManager.getGirth();}
    public void calculateGirth(){girthManager.calculateGirth();}

}
