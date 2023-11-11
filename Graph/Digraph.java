import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Digraph extends Graph{

    public Digraph(){
        this(6);
    }
    public Digraph(int n){
        order = n;
        for (int i = 0; i < order; i++){
            adjList.add(new ArrayList<Integer>());
        }
        generateRandom();
    }
    
    protected void generate(){
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                if (i == j){
                    continue;
                }
                int in;
                if (order < 6){in = rand.nextInt(4);}
                else{in = rand.nextInt(6);}
                if (in == 1){
                    adjList.get(i).add(j);
                    size++;
                }
            }
        }
    }

    protected void generateRandom(){
        int graphNum = rand.nextInt((int) Math.pow(2, order * (order - 1)));
        //System.out.println(order * (order - 1)/2);
        String binaryString = Integer.toBinaryString(graphNum);
        int count = 0;
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                if (i == j){
                    continue;
                }
                int in = Integer.parseInt(binaryString.substring(count, count + 1));
                if (in == 1){
                    adjList.get(i).add(j);
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
