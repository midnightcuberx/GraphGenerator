import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

abstract class Graph {
    protected ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    protected int order;
    protected int size;
    protected Random rand = new Random();
    protected final int nodeHeight = 40;
    protected final int centerX = 300 - nodeHeight / 2;
    protected final int centerY = 250 - nodeHeight / 2;
    protected final int dist = 150;


    protected abstract void generate();
    protected abstract void generateRandom();
    public abstract void drawLines(Graphics g, int i, int j);
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

    public String toString(){
        return getAdjList().toString();
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

    public int getX(int i){
        return (int) Math.round(centerX + dist * Math.cos(2 * Math.PI * i / order));
    }

    public int getY(int i){
        return (int) Math.round(centerY + dist * Math.sin(2 * Math.PI * i / order));
    }


}
