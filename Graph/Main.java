import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

class Main extends JFrame{
    private GraphViewer panel;
    JComboBox<Integer> sizeComboBox;
    JComboBox<GraphType> graphComboBox;
    JButton graphButton;
    DefaultTableModel bfsModel;
    DefaultTableModel dfsModel;
    JTable bfsTable;
    JTable dfsTable;

    public static void main(String[] args){
        //ArrayList<ArrayList<ArrayList<Integer>>> a = new ArrayList<>();
        //for (int i = 0; i)
        //GraphGenerator gg = new GraphGenerator();
        //gg.createGraph(GraphType.Directed, 6);
        //System.out.println(gg.getGraphs().get(0).toString());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
    }

    public Main(){
		super("Graph Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 600);
        add(setup(), BorderLayout.NORTH);
        add(mainSetup(), BorderLayout.EAST);
        add(infoSetup(), BorderLayout.WEST);
		setVisible(true);

    }

    public JPanel mainSetup(){
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel j = new JPanel();
        panel = new GraphViewer();
        panel.setPreferredSize(new Dimension(600, 500));
        //j.add(panel);
        //j.add(new JButton("Yes"));
        graphButton = new JButton("Generate Graph");
        graphButton.addActionListener(new GenerateListener());
        buttonPanel.add(graphButton);
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, panel);
        //JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, j, mainSplitPane);
        mainPanel.add(mainSplitPane);
        return mainPanel;
    }

    public JPanel infoSetup(){
        JPanel infoPanel = new JPanel();
        Object [] o = {"Node num", "Seen", "Done"};
        dfsModel = new DefaultTableModel(new Object[][] {}, o);
        bfsModel = new DefaultTableModel(new Object[][] {}, new Object[] {"Node", "Level"});
        dfsTable = new JTable(dfsModel);
        bfsTable = new JTable(bfsModel);
        JScrollPane dfs = new JScrollPane(dfsTable);
        JScrollPane bfs = new JScrollPane(bfsTable);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //infoPanel.add(new JLabel("Graph info"));
        infoPanel.add(new JLabel("DFS info"));
        infoPanel.add(dfs);
        infoPanel.add(new JLabel("BFS info"));
        infoPanel.add(bfs);
        return infoPanel;
    }

    public JPanel setup(){
        JPanel j = new JPanel();
        Vector<Integer> a = new Vector<>();
        for (int i = 3; i < 9; i++){
            a.add(i);
        }
        sizeComboBox = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(a));
        sizeComboBox.setSelectedIndex(3);
        graphComboBox = new JComboBox<GraphType>(new DefaultComboBoxModel<GraphType>(GraphType.values()));

        j.setLayout(new BoxLayout(j, BoxLayout.X_AXIS));
        j.add(new JLabel("Graph type:", JLabel.LEFT));
        j.add(graphComboBox);
        j.add(new JLabel("Order of graph:", JLabel.RIGHT));
        j.add(sizeComboBox);
        //j.setPreferredSize(new Dimension(j.getWidth(), 50));

        return j;
    }

    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            panel.setGraph((GraphType) graphComboBox.getSelectedItem(), (int) sizeComboBox.getSelectedItem());
            clearTable(dfsModel);
            dfsSetup();
        }

        public void dfsSetup(){
            DFSManager manager = panel.getDfsManager();
            Graph g = manager.getGraph();
            int order = g.getOrder();
            int [] seen = manager.getSeen();
            int [] done = manager.getDone();
            int [] dfsOrder = manager.getDfsOrder();
            for (int i = 0; i < order; i++){
                Object [] r = {i, seen[i], done[i]};
                dfsModel.addRow(r);
                //dfsModel.fireTableDataChanged();
            }        
        }

        public void clearTable(DefaultTableModel t){
            int size = t.getRowCount();
            for (int i = 0; i < size; i++){
                t.removeRow(0);
            }
        }
    }
}