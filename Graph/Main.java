import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class Main extends JFrame{
    private GraphViewer panel;
    JComboBox<Integer> sizeComboBox;
    JComboBox<GraphType> graphComboBox;
    JButton graphButton;
    public static void main(String[] args){
        //ArrayList<ArrayList<ArrayList<Integer>>> a = new ArrayList<>();
        //for (int i = 0; i)
        GraphGenerator gg = new GraphGenerator();
        gg.createGraph(GraphType.Directed, 6);
        System.out.println(gg.getGraphs().get(0).getAdjList());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
    }

    public Main(){
		super("Graph Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
        add(setup(), BorderLayout.NORTH);
        add(mainSetup(), BorderLayout.CENTER);

    }

    public JPanel mainSetup(){
        JPanel mainPanel = new JPanel();
        JPanel j = new JPanel();
        JPanel buttonPanel = new JPanel();
        panel = new GraphViewer();
        panel.setPreferredSize(new Dimension(600, 500));
        //j.add(panel);
        //j.add(new JButton("Yes"));
        graphButton = new JButton("Generate Graph");
        buttonPanel.add(graphButton);
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, panel);
        mainPanel.add(mainSplitPane);
        return mainPanel;
    }

    public JPanel setup(){
        JPanel j = new JPanel();
        Vector<Integer> a = new Vector<>();
        for (int i = 3; i < 11; i++){
            a.add(i);
        }
        sizeComboBox = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(a));
        graphComboBox = new JComboBox<GraphType>(new DefaultComboBoxModel<GraphType>(GraphType.values()));

        j.setLayout(new BoxLayout(j, BoxLayout.X_AXIS));
        j.add(new JLabel("Graph type:", JLabel.LEFT));
        j.add(graphComboBox);
        j.add(new JLabel("Size of graph:", JLabel.RIGHT));
        j.add(sizeComboBox);
        //j.setPreferredSize(new Dimension(j.getWidth(), 50));

        return j;
    }

    class SizeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //do stuff to show graph
        }
    }
}