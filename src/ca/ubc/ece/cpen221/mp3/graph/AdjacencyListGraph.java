package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    Map<Vertex, List<Vertex>> graph; 
    
    public AdjacencyListGraph() {
        graph = new HashMap<Vertex, List<Vertex>>();
    }

    @Override
    public void addVertex(Vertex v) {
        graph.put(v, new LinkedList<Vertex>());
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) { // v1 -> v2, means v2 is a follower of v1
        List<Vertex> v1Follower = graph.get(v1); 
        v1Follower.add(v2); 
        graph.put(v1, v1Follower); 
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) { // if v2 is a follower of v1
        List<Vertex> v1Follower = graph.get(v1); 
        
        for(Vertex follower : v1Follower){
            if(follower.equals(v2))
                return true; 
        }
        return false;
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        return graph.get(v);
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        List<Vertex> upstream = new LinkedList<Vertex>();
        
        for(Entry<Vertex, List<Vertex>> entry : graph.entrySet()){
            List<Vertex> listOfNeighbor = entry.getValue(); 
            
            for(Vertex neighbor : listOfNeighbor){
                if(neighbor.equals(v)){
                    upstream.add(entry.getKey());
                    break; 
                }
            }
        }
        
        return upstream;
    }

    @Override
    public List<Vertex> getVertices() {
        List<Vertex> allVertices = new LinkedList<Vertex>(); 
        allVertices.addAll(graph.keySet());
        return allVertices;
    }
}
