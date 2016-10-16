package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
    
    List<Vertex> allVertices; 
    List<List<Boolean>> graph; 
    
    public AdjacencyMatrixGraph() {
        allVertices = new ArrayList<Vertex>(); 
        graph = new ArrayList<List<Boolean>>(); 
    }
    
    @Override
    public void addVertex(Vertex v) {
        int vPos = allVertices.size(); 
        allVertices.add(vPos, v); 
        
        List<Boolean> row = new ArrayList<Boolean>(); 
        
        for(int index = 0; index <= vPos; index++){
            row.add(new Boolean(false));
        }
        
        graph.add(vPos, row);
        
        for(int rowIndex = 0; rowIndex < vPos; rowIndex++){
            graph.get(rowIndex).add(new Boolean(false));
        }
        
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        int i = getVertexIndex(v1); 
        int j = getVertexIndex(v2); 
        
        List<Boolean> row = graph.get(j); 
        row.set(i, new Boolean(true));
        
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        int i = getVertexIndex(v1); 
        int j = getVertexIndex(v2); 

        return graph.get(j).get(i).booleanValue();
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        List<Vertex> downstream = new ArrayList<Vertex>(); 
        
        int colIndex = getVertexIndex(v); 
        
        for(int rowIndex = 0; rowIndex < graph.size(); rowIndex++){
            if(graph.get(rowIndex).get(colIndex).booleanValue()){
                downstream.add(allVertices.get(rowIndex)); 
            }
        }
        
        return downstream;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        List<Vertex> upstream = new ArrayList<Vertex>(); 
        
        int rowIndex = getVertexIndex(v); 
        
        for(int colIndex = 0; colIndex < graph.size(); colIndex++){
            if(graph.get(rowIndex).get(colIndex).booleanValue()){
                upstream.add(allVertices.get(colIndex)); 
            }
        }
        
        return upstream;
    }

    @Override
    public List<Vertex> getVertices() {
        return allVertices;
    }
    
    private int getVertexIndex(Vertex v){
        for(int index = 0; index < allVertices.size(); index++){
            if(allVertices.get(index).equals(v))
                return index; 
                        
        }
        
        return -1; 
    }
    
    @SuppressWarnings("unused")
    private void printMatrix(){
        System.out.println();
        System.out.print("  ");
        for(Vertex v : allVertices){
            System.out.print("   " + v + "   ");
        }
        
        System.out.println();
        System.out.println("   -----------------------------------------------------------------------------------------------");
        
        for(int col = 0; col < graph.size(); col++){
            List<Boolean> row = graph.get(col);
            System.out.print(allVertices.get(col) + "| ");
            for(int index = 0; index < graph.size(); index++){
                Boolean val = row.get(index);
                System.out.print(((val.booleanValue()) ? "true   " : "  -    "));
            }
            System.out.println();
        }
        
        System.out.println("   -----------------------------------------------------------------------------------------------");
    }
    
}
