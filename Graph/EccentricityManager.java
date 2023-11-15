import java.util.LinkedList;
import java.util.Queue;

public class EccentricityManager {
    private int [] [] distanceMatrix;
    private int [] eccentricityArray;
    private int diameter;
    private int radius;
    private int order;
    private Graph g;

    public EccentricityManager(Graph g){
        this.g = g;
        order = g.getOrder();
        distanceMatrix = new int [order][order];
        populateDistMatrix();
        eccentricityArray = new int [order];
        diameter = Integer.MIN_VALUE;
        radius = Integer.MAX_VALUE;
    }

    public void singlePassDistance(int i){
        int eccentricity = 0;
        int count = 1;
        int [] level = new int [order];
        populateLevels(level);

        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        level[i] = 0;
        distanceMatrix[i][i] = 0;
        
        while (!q.isEmpty()){
            int node = q.remove();
            for (int neighbour: g.getAdjacentNodes(node)){
                if (level[neighbour] != -1){continue;}
                level[neighbour] = level[node] + 1;
                distanceMatrix[i][neighbour] = level[neighbour];
                q.add(neighbour);
                count++;

                if (level[neighbour] > eccentricity){
                    eccentricity = level[neighbour];
                }
            }
        }

        if (count != order){
            eccentricity = Integer.MAX_VALUE;
        }
        if (eccentricity > diameter){
            diameter = eccentricity;
        }
        if (eccentricity < radius){
            radius = eccentricity;
        }

        eccentricityArray[i] = eccentricity;
    }

    public void populateLevels(int [] levels){
        for (int i = 0; i < levels.length; i++){
            levels[i] = -1;
        }
    }

    public void populateDistMatrix(){
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                distanceMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public void allNodesDistance(){
        for (int i = 0; i < order; i++){
            singlePassDistance(i);
        }
    }

    public int getDiameter(){return diameter;}
    public int getRadius(){return radius;}
    public int [] getEccentricityArray(){return eccentricityArray;}
    public int [] [] getDistanceMatrix(){return distanceMatrix;}
}
