package sk.ygor.stackoverflow.q53295480;

class Edge  {

    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        System.out.println(String.format("Edge.hashCode.%d.%d.%d", source, destination, weight));
        final int prime = 31;
        int result = 1;
        result = prime * result + destination;
        result = prime * result + source;
        result = prime * result + weight;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(String.format("Edge.equals.%d.%d.%d", source, destination, weight));
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (destination != other.destination)
            return false;
        if (source != other.source)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edge [source=" + source + ", destination=" + destination + ", weight=" + weight + "]";
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
