import java.util.ArrayList;


public class Digraph extends Graph{

    public Digraph(){
        this(6);
    }
    public Digraph(int n){
        order = n;
        for (int i = 0; i < order; i++){
            adjList.add(new ArrayList<Integer>());
        }
        generate();
    }

    public void generate(){
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                if (i == j){
                    continue;
                }
                int in;
                if (order < 6){in = rand.nextInt(4);}
                else{in = rand.nextInt(6);}
                if (in == 1){
                    adjList.get(i).add(j);
                    size++;
                }
            }
        }
    }

}
