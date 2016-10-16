package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex; 

@RunWith(Parameterized.class)
public class GraphTest {
    
    Class<?> graphClass; 
    Graph graph; 
    
    Vertex a; 
    Vertex b; 
    Vertex c; 
    Vertex d; 
    Vertex e; 
    Vertex f; 
    Vertex g; 
    Vertex h; 
    Vertex i; 
    Vertex j; 
    Vertex k; 
    Vertex l; 
    Vertex m; 
    Vertex n; 
    
    @Before
    public void initialize() throws Exception {
        graph = (Graph)graphClass.newInstance(); 
        
        a = new Vertex("a"); 
        b = new Vertex("b"); 
        c = new Vertex("c"); 
        d = new Vertex("d"); 
        e = new Vertex("e"); 
        f = new Vertex("f"); 
        g = new Vertex("g"); 
        h = new Vertex("h"); 
        i = new Vertex("i"); 
        j = new Vertex("j"); 
        k = new Vertex("k"); 
        l = new Vertex("l"); 
        m = new Vertex("m"); 
        n = new Vertex("n"); 
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);
        graph.addVertex(j);
        graph.addVertex(k);
        graph.addVertex(l);
        graph.addVertex(m);
        graph.addVertex(n);
        
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, f);
        graph.addEdge(a, e);
        graph.addEdge(a, d);
        graph.addEdge(b, a);
        graph.addEdge(d, c);
        graph.addEdge(d, b);
        graph.addEdge(e, d);
        graph.addEdge(e, f);
        graph.addEdge(f, b);
        graph.addEdge(f, c);
        graph.addEdge(f, d);
        
        graph.addEdge(g, j);
        graph.addEdge(g, k);
        graph.addEdge(h, g);
        graph.addEdge(h, i);
        graph.addEdge(h, j);
        graph.addEdge(i, h);
        graph.addEdge(j, i);
        graph.addEdge(k, h);
        
        graph.addEdge(m, l);
        graph.addEdge(l, m);
    }
    
    public GraphTest(Class<?> graphClass){
        this.graphClass = graphClass;
    }

    @Test
    public void testAddVertex() {
        Vertex z = new Vertex("z"); 
        graph.addVertex(z);
        List<Vertex> allVertices = graph.getVertices(); 
        assertTrue(allVertices.contains(z)); 
        
    }
    
    @Test
    public void testAddEdge() {
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");
        
        graph.addVertex(y);
        graph.addVertex(z);
        graph.addEdge(y, z); // y -> z
        
        assertTrue(graph.edgeExists(y, z));
    }
    
    @Test
    public void testEdgeExists() {
        assertTrue(graph.edgeExists(a, b));
        assertTrue(graph.edgeExists(f, b));
        assertFalse(graph.edgeExists(a, m));
        assertFalse(graph.edgeExists(n, m));
    }
    
    @Test
    public void testGetDownstreamNeighbors() {
        List<Vertex> fDownstream = graph.getDownstreamNeighbors(f); 
        assertTrue(fDownstream.contains(b)); 
        assertTrue(fDownstream.contains(c)); 
        assertTrue(fDownstream.contains(d)); 
        assertTrue(fDownstream.size() == 3); 
        
        List<Vertex> lDownstream = graph.getDownstreamNeighbors(l); 
        assertTrue(lDownstream.contains(m)); 
        assertTrue(lDownstream.size() == 1); 
        
        List<Vertex> nDownstream = graph.getDownstreamNeighbors(n); 
        assertTrue(nDownstream.size() == 0); 
    }
    
    @Test
    public void testGetUpstreamNeighbors() {
        List<Vertex> bUpstream = graph.getUpstreamNeighbors(b); 
        assertTrue(bUpstream.contains(a)); 
        assertTrue(bUpstream.contains(d)); 
        assertTrue(bUpstream.contains(f)); 
        assertTrue(bUpstream.size() == 3); 
        
        List<Vertex> mUpstream = graph.getUpstreamNeighbors(m);
        assertTrue(mUpstream.contains(l)); 
        assertTrue(mUpstream.size() == 1); 
        
        List<Vertex> nUpstream = graph.getUpstreamNeighbors(n); 
        assertTrue(nUpstream.size() == 0);
    }
    
    @Test
    public void testGetVertices() {
        List<Vertex> allVertices = graph.getVertices(); 
        assertTrue(allVertices.contains(a)); 
        assertTrue(allVertices.contains(b)); 
        assertTrue(allVertices.contains(c)); 
        assertTrue(allVertices.contains(h)); 
        assertTrue(allVertices.contains(i)); 
        assertTrue(allVertices.contains(m)); 
        assertTrue(allVertices.contains(n));
        assertTrue(allVertices.size() == 14); 
        
    }
    
    @Parameters
    public static Collection<Class<?>> getGraph(){
        List<Class<?>> graphs = new ArrayList<Class<?>>();
        graphs.add(AdjacencyListGraph.class);
        graphs.add(AdjacencyMatrixGraph.class);
        return graphs; 
    }

}
