import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphViewer extends JPanel{
    private int size = 6;
    private GraphType gt = GraphType.Simple;
    private GraphGenerator gg;
    private Graph graph;
    private final int nodeHeight = 40;
    private final int centerX = 300 - nodeHeight / 2;
    private final int centerY = 250 - nodeHeight / 2;
    private final int dist = 150;

    public GraphViewer(){
        gg = new GraphGenerator();
        graph = gg.createGraph(gt, size);
        repaint();
    }

    protected void paintComponent(Graphics g){
        //super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.drawOval(10, 10, 20, 20);
        paintNodes(g);
    }

    public void paintNodes(Graphics g){
        //g.drawOval(300, 250, 10, 10);

        for (int i = 0; i < size; i++){
            int x = getX(i);
            int y = getY(i);
            //System.out.println(Math.round(x));
            //System.out.println(y);
            g.drawOval(x, y, nodeHeight, nodeHeight);
            g.drawString(String.valueOf(i), x, y);
            for (int j: graph.getAdjacentNodes(i)){
                drawLines(g, i, j);
            }
        }
        //drawLines(g, 2, 9);
    }

    public void drawLines(Graphics g, int i, int j){
        g.drawLine(getX(i) + nodeHeight / 2, getY(i) + nodeHeight / 2, getX(j) + nodeHeight / 2, getY(j) + nodeHeight / 2);
    }

    public int getX(int i){
        return (int) Math.round(centerX + dist * Math.cos(2 * Math.PI * i / size));
    }

    public int getY(int i){
        return (int) Math.round(centerY + dist * Math.sin(2 * Math.PI * i / size));
    }

    public void setGraph(GraphType g, int n){
        size = n;
        gt = g;
        graph = gg.createGraph(g, n);
    }



}
