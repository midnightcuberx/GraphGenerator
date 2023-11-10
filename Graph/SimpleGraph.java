import java.util.ArrayList;

public class SimpleGraph extends Graph{

    public SimpleGraph(){
        this(6);
    }
    public SimpleGraph(int n){
        order = n;
        for (int i = 0; i < order; i++){
            adjList.add(new ArrayList<Integer>());
        }
        generate();
    }

    public void generate(){
        for (int i = 0; i < order; i++){
            for (int j = i+1; j < order; j++){
                int in;
                if (order < 6) {in = rand.nextInt(2);}
                else{
                    in = rand.nextInt(3);
                }
                if (in == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                    size++;
                }
            }
        }
    }

}
