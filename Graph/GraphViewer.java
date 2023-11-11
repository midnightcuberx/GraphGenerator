import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphViewer extends JPanel{
    private int order = 6;
    private GraphType gt = GraphType.Simple;
    private GraphGenerator gg;
    private Graph graph;

    public GraphViewer(){
        gg = new GraphGenerator();
        graph = gg.createGraph(gt, order);
        System.out.println(graph);
        //repaint();
        //paintComponent(getGraphics());
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g); //clears the panel
        //super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.drawOval(10, 10, 20, 20);
        graph.paintNodes(g);
    }

    public void setGraph(GraphType g, int n){
        order = n;
        gt = g;
        graph = gg.createGraph(g, n);
        removeAll();
        paintComponent(getGraphics());
    }



}
