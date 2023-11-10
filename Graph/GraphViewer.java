import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphViewer extends JPanel{
    private int size = 10;
    private GraphType gt = GraphType.Simple;
    private GraphGenerator gg;
    private final int nodeHeight = 40;
    private final int centerX = 300 - nodeHeight / 2;
    private final int centerY = 250 - nodeHeight / 2;
    private final int dist = 150;

    public GraphViewer(){
        gg = new GraphGenerator();
        gg.createGraph(gt, size);
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
            int x = (int) Math.round(centerX + dist * Math.cos(2 * Math.PI * i / size));
            int y = (int) Math.round(centerY + dist * Math.sin(2 * Math.PI * i / size));
            //System.out.println(Math.round(x));
            //System.out.println(y);
            g.drawOval(x, y, nodeHeight, nodeHeight);
            g.drawString(String.valueOf(i), x, y);
        }
    }

    public void setGraph(GraphType g, int n){
        size = n;
        gt = g;
    }



}
