package sk.ygor.stackoverflow.q53295480;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 5),
                new Edge(1, 2, 4),
                new Edge(2, 3, 5),
                new Edge(0, 3, 6),
                new Edge(0, 3, 6), // duplicate
                new Edge(0, 2, 3),
                new Edge(1, 3, 7)
        );

        Map<Edge, Integer> hashMap = new HashMap<>();
        edges.forEach(edge -> hashMap.put(edge, edge.weight));
        hashMap.forEach((edge, value) -> System.out.printf("%s%n", edge));

        System.out.println("-----");

        SortedMap<Edge, Integer> treeMap = new TreeMap<>(new MyWeightComp());
        edges.forEach(edge -> treeMap.put(edge, edge.weight));
        treeMap.forEach((edge, value) -> System.out.printf("%s%n", edge));

        System.out.println("-----");

        Comparator<Edge> myEdgeComparator = Comparator
                .comparingInt(Edge::getWeight)
                .thenComparing(Edge::getSource)
                .thenComparing(Edge::getDestination);

        List<Edge> list = new ArrayList<>(edges);
        list.sort(myEdgeComparator);
        list.forEach(System.out::println);

        List<Edge> list2 = edges.stream().sorted(myEdgeComparator).collect(Collectors.toList());
        list2.forEach(System.out::println);
    }
}
