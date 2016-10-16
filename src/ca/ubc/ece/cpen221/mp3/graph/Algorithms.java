package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        Set<Vertex> visited = new HashSet<Vertex>(); 
        Queue<Vertex> traverse = new LinkedList<Vertex>(); 
        
        Vertex special = new Vertex("special"); 
        int distance = 0; 
        
        traverse.add(a); 
        traverse.add(special); 
        
        while(traverse.size() > 1){
            Vertex v = traverse.poll(); 
            
            if(v.equals(special)){
                distance++; 
                traverse.add(v); 
                continue; 
            }
            
            
            if(v.equals(b))
                return distance; 
            
            if(!visited.contains(v)){
                visited.add(v); 
                traverse.addAll(graph.getDownstreamNeighbors(v)); 
            }
        }
        
        return -1; 
	}

	/**
	 * Perform a complete depth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a DFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 * @param
	 * @return
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        List<Vertex> allVertices = graph.getVertices(); 
        Set<List<Vertex>> allComponents = new HashSet<List<Vertex>>(); 
        Set<Vertex> visited = new HashSet<Vertex>(); 
        Stack<Vertex> traverse = new Stack<Vertex>(); 
        
        if(allVertices.isEmpty())
            return allComponents; 
        
        while(visited.size() < allVertices.size()){
            Vertex start = allVertices.get(0); 
            List<Vertex> connected = new ArrayList<Vertex>(); 
            
            int pos = 0; 
            while(visited.contains(start)){
                start = allVertices.get(pos); 
                pos++; 
            }
            
            traverse.add(start); 
            
            while(!traverse.isEmpty()){
                Vertex v = traverse.pop(); 
                
                if(!visited.contains(v)){
                    visited.add(v); 
                    connected.add(v); 
                    traverse.addAll(graph.getDownstreamNeighbors(v)); 
                }
            }
            
            allComponents.add(connected); 
        }
        
        return allComponents; 

	}

	/**
	 * Perform a complete breadth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a BFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
	    List<Vertex> allVertices = graph.getVertices(); 
	    Set<List<Vertex>> allComponents = new HashSet<List<Vertex>>(); 
	    Set<Vertex> visited = new HashSet<Vertex>(); 
	    Queue<Vertex> traverse = new LinkedList<Vertex>(); 
	    
	    if(allVertices.isEmpty())
            return allComponents; 
	    
	    while(visited.size() < allVertices.size()){
	        Vertex start = allVertices.get(0); 
    	    List<Vertex> connected = new ArrayList<Vertex>(); 
    	    
    	    int pos = 0; 
    	    while(visited.contains(start)){
    	        start = allVertices.get(pos); 
    	        pos++; 
    	    }
    	    
    	    traverse.add(start); 
    	    
    	    while(!traverse.isEmpty()){
    	        Vertex v = traverse.poll(); 
    	        
    	        if(!visited.contains(v)){
    	            visited.add(v); 
    	            connected.add(v); 
    	            traverse.addAll(graph.getDownstreamNeighbors(v)); 
    	        }
    	    }
    	    
    	    allComponents.add(connected); 
	    }
	    
	    return allComponents; 
	    
	}

	 public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
	     List<Vertex> commonUpstream = new ArrayList<Vertex>(); 
         List<Vertex> bUpstream = graph.getUpstreamNeighbors(b);
         Set<Vertex> s = new HashSet<Vertex>(); 
         s.addAll(graph.getUpstreamNeighbors(a));
         
         for(Vertex neighbor : bUpstream){
             if(s.contains(neighbor))
                 commonUpstream.add(neighbor);
         }
         
         return commonUpstream; 
	 }

 	 public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
 	     List<Vertex> commonDownstream = new ArrayList<Vertex>(); 
 	     List<Vertex> bDownstream = graph.getDownstreamNeighbors(b);
 	     Set<Vertex> s = new HashSet<Vertex>(); 
 	     s.addAll(graph.getDownstreamNeighbors(a));
 	     
 	     for(Vertex neighbor : bDownstream){
 	         if(s.contains(neighbor))
 	             commonDownstream.add(neighbor);
 	     }
 	     
 	     return commonDownstream; 
 	 }


}
