package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    Map<Vertex, List<Vertex>> graph; 
    
    public AdjacencyListGraph() {
        graph = new HashMap<Vertex, List<Vertex>>();
    }

    @Override
    public void addVertex(Vertex v) {
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        return false;
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        return null;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        return null;
    }

    @Override
    public List<Vertex> getVertices() {
        return null;
    }
}
