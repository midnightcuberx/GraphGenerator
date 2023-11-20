import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


class Main extends JFrame{
    private GraphViewer panel;
    JComboBox<Integer> sizeComboBox;
    JComboBox<GraphType> graphComboBox;
    JButton graphButton;
    JButton dfsButton;
    JButton bfsButton;
    JButton infoButton;
    JButton distanceButton;
    JTable infoTable;
    JTable distanceTable;
    DefaultTableModel infoModel;
    DefaultTableModel bfsModel;
    DefaultTableModel dfsModel;
    DefaultTableModel distanceModel;
    JTable bfsTable;
    JTable dfsTable;
    boolean infoVisible;
    boolean dfsVisible;
    boolean bfsVisible;
    boolean distanceVisible;

    public static void main(String[] args){
        //ArrayList<ArrayList<ArrayList<Integer>>> a = new ArrayList<>();
        //for (int i = 0; i)
        //GraphGenerator gg = new GraphGenerator();
        //gg.createGraph(GraphType.Directed, 6);
        //System.out.println(gg.getGraphs().get(0).toString());
        /*Graph g = new SimpleGraph();
        BFSManager m = new BFSManager(g);
        m.bfs();
        System.out.println(Arrays.toString(m.getBfsOrder()));
        System.out.println(g.getAdjList());*/
        //System.out.println(Double.POSITIVE_INFINITY > Double.POSITIVE_INFINITY - 1);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
    }

    public Main(){
		super("Graph Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Graph.WIDTH, Graph.HEIGHT);
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
        panel.setPreferredSize(new Dimension(Graph.WIDTH / 2, Graph.HEIGHT));
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
        infoModel = new DefaultTableModel(new Object [][] {}, new Object[]{"Key", "Value"});
        distanceModel = new DefaultTableModel(new Object [] [] {}, new Object[] {});
        distanceTable = new JTable(distanceModel);
        infoTable = new JTable(infoModel);
        dfsTable = new JTable(dfsModel);
        bfsTable = new JTable(bfsModel);
        JScrollPane dfs = new JScrollPane(dfsTable);
        JScrollPane bfs = new JScrollPane(bfsTable);
        JScrollPane info = new JScrollPane(infoTable);
        JScrollPane distance = new JScrollPane(distanceTable);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JPanel distanceInfo = new JPanel();
        distanceButton = new JButton("Show/Hide info");
        distanceButton.addActionListener(new DistanceListener());
        distanceInfo.setLayout(new BoxLayout(distanceInfo, BoxLayout.X_AXIS));
        distanceInfo.add(new JLabel("Distance matrix/table"));
        distanceInfo.add(new JSeparator());
        distanceInfo.add(distanceButton);

        JPanel graphInfo = new JPanel();
        infoButton = new JButton("Show/Hide info");
        infoButton.addActionListener(new InfoListener());
        graphInfo.setLayout(new BoxLayout(graphInfo, BoxLayout.X_AXIS));
        graphInfo.add(new JLabel("Graph info"));
        graphInfo.add(new JSeparator());
        graphInfo.add(infoButton);

        JPanel dfsInfo = new JPanel();
        dfsButton = new JButton("Show/Hide info");
        dfsButton.addActionListener(new DFSListener());
        dfsInfo.setLayout(new BoxLayout(dfsInfo, BoxLayout.X_AXIS));
        dfsInfo.add(new JLabel("DFS info"));
        dfsInfo.add(new JSeparator());
        dfsInfo.add(dfsButton);

        JPanel bfsInfo = new JPanel();
        bfsButton = new JButton("Show/Hide info");
        bfsButton.addActionListener(new BFSListener());
        bfsInfo.setLayout(new BoxLayout(bfsInfo, BoxLayout.X_AXIS));
        bfsInfo.add(new JLabel("BFS info"));
        bfsInfo.add(new JSeparator());
        bfsInfo.add(bfsButton);


        //infoPanel.add(new JLabel("Graph info"));
        infoPanel.add(graphInfo);
        infoPanel.add(info);
        infoPanel.add(distanceInfo);
        infoPanel.add(distance);
        infoPanel.add(dfsInfo);
        infoPanel.add(dfs);
        infoPanel.add(bfsInfo);
        infoPanel.add(bfs);
        return infoPanel;
    }

    public JPanel setup(){
        JPanel j = new JPanel();
        sizeComboBox = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(new Integer []{3,4,5,6,7,8}));
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

    public static String ArrayToString2D(Object [] [] arr){
        StringBuffer s = new StringBuffer();
        s.append(Arrays.toString(arr[0]));
        for (int i = 1; i < arr.length; i++){
            s.append("," + Arrays.toString(arr[i]));
        }
        return s.toString();
    }

    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            panel.setGraph((GraphType) graphComboBox.getSelectedItem(), (int) sizeComboBox.getSelectedItem());
            clearTable(infoModel);
            clearTable(dfsModel);
            clearTable(bfsModel);
            infoTable.setVisible(false);
            dfsTable.setVisible(false);
            bfsTable.setVisible(false);
            distanceTable.setVisible(false);
            infoVisible = false;
            dfsVisible = false;
            bfsVisible = false;
            distanceVisible = false;
            infoSetup();
            dfsSetup();
            bfsSetup();
            distanceSetup();
        }

        public void infoSetup(){
            Graph g = panel.getGraph();
            int order = g.getOrder();
            int size = g.getSize();
            Object girth = g.getGirth();
            Object diameter = g.getDiameter();
            Object radius = g.getRadius();
            int [] dfsOrder = g.getDfsOrder();
            int [] bfsOrder = g.getBfsOrder();
            int numComponents = g.getNumComponents();

            if (diameter.equals((Object) Integer.MAX_VALUE)){
                diameter = "\u221e";
            }
            if (radius.equals((Object) Integer.MAX_VALUE)){
                radius = "\u221e";
            }

            if (girth.equals((Object) Integer.MAX_VALUE)){
                girth = "\u221e";
            }

            infoModel.addRow(new Object [] {"Order", order});
            infoModel.addRow(new Object[] {"Size", size});
            infoModel.addRow(new Object[]{"Girth", girth});
            infoModel.addRow(new Object[]{"Diameter", diameter});
            infoModel.addRow(new Object[]{"Radius", radius});
            infoModel.addRow(new Object[] {"Number of Components", numComponents});
            infoModel.addRow(new Object [] {"Degree sequence", Arrays.toString(g.getDegreeSequence())});
            infoModel.addRow(new Object [] {"DFS Order", Arrays.toString(dfsOrder)});
            infoModel.addRow(new Object [] {"BFS Order", Arrays.toString(bfsOrder)});
            infoModel.addRow(new Object[] {"Eccentricity array", Arrays.toString(g.getEccentricityArray())});
            //infoModel.addRow(new Object[] {"Distance Matrix", ArrayToString2D(g.getDistanceMatrix())});
        }

        public void dfsSetup(){
            Graph g = panel.getGraph();
            int order = g.getOrder();
            int [] seen = g.getSeen();
            int [] done = g.getDone();
            int [] dfsOrder = g.getDfsOrder();
            for (int i = 0; i < order; i++){
                Object [] r = {i, seen[i], done[i]};
                dfsModel.addRow(r);
                //dfsModel.fireTableDataChanged();
            }        
        }

        public void bfsSetup(){
            Graph g = panel.getGraph();
            int order = g.getOrder();
            int [] bfsOrder = g.getBfsOrder();
            int [] levels = g.getLevels();

            for (int i = 0; i < order; i++){
                bfsModel.addRow(new Object [] {i, levels[i]});
            }
        }

        public void clearTable(DefaultTableModel t){
            int size = t.getRowCount();
            for (int i = 0; i < size; i++){
                t.removeRow(0);
            }
        }

        public void distanceSetup(){
            Graph g = panel.getGraph();
            int order = g.getOrder();
            Object [] [] distMatrix = g.getDistanceMatrix();
            Object [] arr = new Object[order + 1];
            arr[0] = "Node Num";
            for (int i = 1; i <= order; i++){
                arr[i] = i - 1;
            }

            distanceModel = new DefaultTableModel(new Object [] [] {}, arr);
            distanceTable.setModel(distanceModel);

            for (int i = 0; i < order; i++){
                Object [] row = new Object [order + 1];
                row[0] = "Node " + i;
                for (int j = 0; j < order; j++){
                    row[j + 1] = distMatrix[i][j];
                }
                distanceModel.addRow(row);
            }

        }
    }

    class DistanceListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean bool = distanceVisible == true ? false:true;
            distanceVisible = bool;
            distanceTable.setVisible(bool);
        }
    }

    class InfoListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean bool = infoVisible == true ? false:true;
            infoVisible = bool;
            infoTable.setVisible(bool);
        }
    }

    class DFSListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean bool = dfsVisible == true ? false:true;
            dfsVisible = bool;
            dfsTable.setVisible(bool);
            //System.out.println(bool);
            if (dfsVisible == true){
                panel.paintDFS(panel.getGraphics());
            } else{
                panel.paintComponent(panel.getGraphics());
            }
        }
    }

    class BFSListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean bool = bfsVisible == true ? false:true;
            bfsVisible = bool;
            bfsTable.setVisible(bool);
            if (bfsVisible == true){
                panel.paintBFS(panel.getGraphics());
            } else{
                panel.paintComponent(panel.getGraphics());
            }
        }
    }
}