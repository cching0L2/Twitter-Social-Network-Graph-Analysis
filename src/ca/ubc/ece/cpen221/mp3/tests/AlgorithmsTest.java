package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.ubc.ece.cpen221.mp3.graph.*; 
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

@RunWith(Parameterized.class)
public class AlgorithmsTest {
    
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
    
    public AlgorithmsTest(Class<?> graphClass){
        this.graphClass = graphClass; 
    }

    @Test
    public void shortestDistanceTest() {
        assertEquals(1, Algorithms.shortestDistance(graph, a, b));
        assertEquals(1, Algorithms.shortestDistance(graph, h, j));
        assertEquals(3, Algorithms.shortestDistance(graph, i, k));
        assertEquals(2, Algorithms.shortestDistance(graph, b, e));
        assertEquals(1, Algorithms.shortestDistance(graph, m, l));
        assertEquals(-1, Algorithms.shortestDistance(graph, c, e));
        assertEquals(-1, Algorithms.shortestDistance(graph, a, n));
        assertEquals(-1, Algorithms.shortestDistance(graph, a, j));
        assertEquals(-1, Algorithms.shortestDistance(graph, m, b));
    }
    
    @Test
    public void depthFirstSearchTest() {
        List<List<Vertex>> connectedComponents = new ArrayList<List<Vertex>>(); 
        connectedComponents.addAll(Algorithms.depthFirstSearch(graph)); 
        Collections.sort(connectedComponents, new ListSizeComparator());
        
        assertTrue(connectedComponents.size() == 4); 
        assertTrue(connectedComponents.get(0).size() == 1); 
        assertTrue(connectedComponents.get(1).size() == 2); 
        assertTrue(connectedComponents.get(2).size() == 5); 
        assertTrue(connectedComponents.get(3).size() == 6); 
        
        assertTrue(connectedComponents.get(0).contains(n)); 
        assertTrue(connectedComponents.get(1).contains(m)); 
        assertTrue(connectedComponents.get(1).contains(l)); 
        assertTrue(connectedComponents.get(2).contains(g)); 
        assertTrue(connectedComponents.get(2).contains(h)); 
        assertTrue(connectedComponents.get(3).contains(a)); 
        assertTrue(connectedComponents.get(3).contains(b)); 
        
    }
    
    @Test
    public void breadthFirstSearchTest() {
        List<List<Vertex>> connectedComponents = new ArrayList<List<Vertex>>(); 
        connectedComponents.addAll(Algorithms.breadthFirstSearch(graph)); 
        Collections.sort(connectedComponents, new ListSizeComparator());
        
        assertTrue(connectedComponents.size() == 4); 
        assertTrue(connectedComponents.get(0).size() == 1); 
        assertTrue(connectedComponents.get(1).size() == 2); 
        assertTrue(connectedComponents.get(2).size() == 5); 
        assertTrue(connectedComponents.get(3).size() == 6); 
        
        assertTrue(connectedComponents.get(0).contains(n)); 
        assertTrue(connectedComponents.get(1).contains(m)); 
        assertTrue(connectedComponents.get(1).contains(l)); 
        assertTrue(connectedComponents.get(2).contains(g)); 
        assertTrue(connectedComponents.get(2).contains(h)); 
        assertTrue(connectedComponents.get(3).contains(a)); 
        assertTrue(connectedComponents.get(3).contains(b)); 
        
    }
    
    @Test
    public void commonUpstreamVerticesTest() {
        List<Vertex> bdCommon = Algorithms.commonUpstreamVertices(graph, b, d);
        assertTrue(bdCommon.contains(a));
        assertTrue(bdCommon.contains(f));
        assertTrue(bdCommon.size() == 2);
        
        List<Vertex> dfCommon = Algorithms.commonUpstreamVertices(graph, d, f);
        assertTrue(dfCommon.contains(a));
        assertTrue(dfCommon.contains(e));
        assertTrue(dfCommon.size() == 2);
        
        List<Vertex> naCommon = Algorithms.commonUpstreamVertices(graph, a, n);
        assertTrue(naCommon.isEmpty());
        
        List<Vertex> lmCommon = Algorithms.commonUpstreamVertices(graph, l, m);
        assertTrue(lmCommon.isEmpty());
    }
    
    @Test
    public void commonDownstreamVerticesTest() {
        List<Vertex> abCommon = Algorithms.commonDownstreamVertices(graph, a, b); 
        assertTrue(abCommon.isEmpty()); 
        
        List<Vertex> afCommon = Algorithms.commonDownstreamVertices(graph, a, f);
        assertTrue(afCommon.contains(b));
        assertTrue(afCommon.contains(c));
        assertTrue(afCommon.contains(d));
        assertTrue(afCommon.size() == 3);
        
        List<Vertex> hjCommon = Algorithms.commonDownstreamVertices(graph, j, h);
        assertTrue(hjCommon.contains(i));
        assertTrue(hjCommon.size() == 1);
        
        List<Vertex> nmCommon = Algorithms.commonDownstreamVertices(graph, m, n);
        assertTrue(nmCommon.isEmpty());
    }
    
    @Parameters
    public static Collection<Class<?>> getGraph(){
        List<Class<?>> graphs = new ArrayList<Class<?>>();
        graphs.add(AdjacencyListGraph.class);
        //graphs.add(AdjacencyMatrixGraph.class);
        return graphs; 
    }
    
    private class ListSizeComparator implements Comparator<List<Vertex>>{
        @Override
        public int compare(List<Vertex> o1, List<Vertex> o2) {
            return o1.size() - o2.size();
        }
        
    }

}
