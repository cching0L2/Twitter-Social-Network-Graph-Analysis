package ca.ubc.ece.cpen221.mp3.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;

@RunWith(Parameterized.class)
public class GraphTest {
    
    Graph graph; 
    
    @Before
    public void initialize() throws Exception {
        // TODO: make graph here 
    }
    
    public GraphTest(Graph graph){
        this.graph = graph; 
    }

    @Test
    public void testAddVertex() {
        
    }
    
    @Test
    public void testAddEdge() {
        
    }
    
    @Test
    public void testEdgeExists() {
        
    }
    
    @Test
    public void testGetDownstreamNeighbors() {
        
    }
    
    @Test
    public void testGetUpstreamNeighbors() {
        
    }
    
    @Test
    public void testGetVertices() {
        
    }
    
    @Parameters
    public static List<Graph> getGraph(){
        List<Graph> graphs = new ArrayList<Graph>();
        graphs.add(new AdjacencyListGraph());
        graphs.add(new AdjacencyMatrixGraph());
        return graphs; 
    }

}
