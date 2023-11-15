import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Digraph extends Graph{

    private ArrayList<ArrayList<Integer>> underlying = new ArrayList<>();

    public Digraph(){
        this(6);
    }
    public Digraph(int n){
        order = n;
        for (int i = 0; i < order; i++){
            adjList.add(new ArrayList<Integer>());
            underlying.add(new ArrayList<Integer>());
        }
        generateRandom();
        bfsManager = new BFSManager(this);
        dfsManager = new DFSManager(this);
        eccentricityManager = new EccentricityManager(this);
        girthManager = new GirthManager(this);
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
                if (in == 1){   underlying.get(i).add(j);
                    adjList.get(i).add(j);
                    underlying.get(j).add(i);
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
                    if (!underlying.get(i).contains(j)){
                        underlying.get(i).add(j);
                    }
                    if (!underlying.get(j).contains(i)){
                        underlying.get(j).add(i);
                    }
                    size++;
                }
                count++;

                if (count == binaryString.length()){return;}
            }
        }
    }

    public void drawLines(Graphics g, int i, int j){
        int arrowLength = 15;

        int x1, x2, y1, y2;
        x1 = getX(i) + nodeHeight / 2;
        y1 = getY(i) + nodeHeight / 2;
        x2 = getX(j) + nodeHeight / 2;
        y2 = getY(j) + nodeHeight / 2;

        int diffX = x1 - x2;
        int diffY = y1 - y2;
        double hypotenuse = Math.sqrt(diffX * diffX + diffY * diffY);
        double lineLength = nodeHeight / 2 + 10;
        double xShift = lineLength * diffX / hypotenuse;
        double yShift = lineLength * diffY / hypotenuse;
        g.drawLine(x1 - (int) xShift, y1 - (int) yShift, x2 + (int) xShift, y2 + (int) yShift);

        double angle = Math.atan2(diffY, diffX); //+-20
        int x = x2 + (int) xShift;
        int y = y2 + (int) yShift;
        double deg1 = angle + Math.toRadians(20);
        double deg2 = angle - Math.toRadians(20);

        double arrowX1 = Math.cos(deg1) * arrowLength;
        double arrowX2 = Math.cos(deg2) * arrowLength;
        double arrowY1 = Math.sin(deg1) * arrowLength;
        double arrowY2 = Math.sin(deg2) * arrowLength;

        g.drawLine(x + (int) arrowX1, y + (int) arrowY1, x, y);
        g.drawLine(x + (int) arrowX2, y + (int) arrowY2, x, y);
    }

    public ArrayList<ArrayList<Integer>> getUnderlyingGraph(){
        return underlying;
    }

    public ArrayList<Integer> getUnderlyingAdjacent(int i){
        return underlying.get(i);
    }

}
