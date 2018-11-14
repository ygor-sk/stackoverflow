package sk.ygor.stackoverflow.q53295480;

import java.util.Comparator;

public class MyWeightComp implements Comparator<Edge> {

    @Override
    public int compare(Edge e1, Edge e2) {
        System.out.println(String.format(
                "Edge.compare.%d.%d.%d:%d.%d.%d",
                e1.source, e1.destination, e1.weight, e2.source, e2.destination, e2.weight
        ));
        return e1.weight - e2.weight;
    }
}
