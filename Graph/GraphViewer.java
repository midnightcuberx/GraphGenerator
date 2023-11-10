import java.awt.Graphics;
import javax.swing.JPanel;

public class GraphViewer extends JPanel{
    public GraphViewer(){
        
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.setColor(Color.WHITE);
        g.drawOval(10, 10, 20, 20);
    }
}
