import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.*;

public class GraphViewer extends JPanel{
    private int order = 6;
    private GraphType gt = GraphType.Simple;
    private GraphGenerator gg;
    private Graph graph;
    //private DFSManager dfsManager;
    //private BFSManager bfsManager;

    public GraphViewer(){
        gg = new GraphGenerator();
        //setupViewer(gt, order);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g); //clears the panel
        //super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.drawOval(10, 10, 20, 20);
        if (graph == null){return;}
        graph.paintNodes(g);
    }

    public void setGraph(GraphType g, int n){
        order = n;
        gt = g;
        setupViewer(g, n);
        //removeAll();
        paintComponent(getGraphics());
    }

    public void setupViewer(GraphType gt, int order){
        graph = gg.createGraph(gt, order);
        graph.dfs();
        graph.bfs();
        System.out.println(graph);
        /*System.out.println("There are " + dfsManager.getNumComponents() + " components");
        System.out.println("DFS order: " + Arrays.toString(dfsManager.getDfsOrder()));
        System.out.println("Seen time: " +Arrays.toString(dfsManager.getSeen()));
        System.out.println("Done: " + Arrays.toString(dfsManager.getDone()));*/
    }

    //public DFSManager getDfsManager(){return dfsManager;}
    //public BFSManager getBfsManager(){return bfsManager;}
    public Graph getGraph(){return graph;}



}
