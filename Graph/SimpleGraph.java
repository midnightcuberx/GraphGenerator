import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SimpleGraph extends Graph{

    public SimpleGraph(){
        this(6);
    }
    public SimpleGraph(int n){
        order = n;
        for (int i = 0; i < order; i++){
            adjList.add(new ArrayList<Integer>());
        }
        //generate();
        generateRandom();
        bfsManager = new BFSManager(this);
        dfsManager = new DFSManager(this);
        eccentricityManager = new EccentricityManager(this);
    }

    protected void generate(){
        for (int i = 0; i < order; i++){
            for (int j = i+1; j < order; j++){
                int in;
                if (order < 6) {in = rand.nextInt(2);}
                else{
                    in = rand.nextInt(3);
                }
                if (in == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                    size++;
                }
            }
        }
    }

    protected void generateRandom(){
        int graphNum = rand.nextInt((int) Math.pow(2, order * (order - 1)/2));
        //System.out.println(order * (order - 1)/2);
        String binaryString = Integer.toBinaryString(graphNum);
        //System.out.println(graphNum + " " +   binaryString);
        //System.out.println(binaryString.length());
        int count = 0;
        for (int i = 0; i < order; i++){
            for (int j = i+1; j < order; j++){
                int in = Integer.parseInt(binaryString.substring(count, count + 1));
                if (in == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                    size++;
                }

                count++;

                if (count == binaryString.length()){return;}
            }
        }
    }

    public void drawLines(Graphics g, int i, int j){
        g.drawLine(getX(i) + nodeHeight / 2, getY(i) + nodeHeight / 2, getX(j) + nodeHeight / 2, getY(j) + nodeHeight / 2);
    }

}
