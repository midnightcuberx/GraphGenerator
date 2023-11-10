import java.util.*;

class Main{
    public static void main(String[] args){
        //ArrayList<ArrayList<ArrayList<Integer>>> a = new ArrayList<>();
        //for (int i = 0; i)
        GraphGenerator gg = new GraphGenerator();
        gg.createGraph(GraphType.Simple, 6);
        System.out.println(gg.getGraphs().get(0).getAdjList());
    }

    public static ArrayList<ArrayList<Integer>> getGraph(){
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            a.add(new ArrayList<Integer>());
        }
        return a;
    }
}