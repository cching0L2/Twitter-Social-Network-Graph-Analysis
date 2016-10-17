package ca.ubc.ece.cpen221.mp3.twitterAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TwitterAnalysis{
    static BufferedReader reader; 
    static FileWriter writer; 
    
    static Graph graph; 
    
    public static void main(String[] args){
        String queryInput  = args[0]; 
        String queryOutput = args[1];
        
        makeGraph("datasets/twitter.txt");
        
        try{
            reader = new BufferedReader(new FileReader(queryInput)); 
            writer = new FileWriter(new File(queryOutput)); 
            
            String line = reader.readLine(); 
            
            while(line != null){
                String[] in = line.split("\\s+"); 
                
                switch(in[0]){
                    case "commonInfluencers": 
                        getCommonInfluences(in[1], in[2], writer); 
                        break; 
                    case "numRetweets":
                        getNumRetweets(in[1], in[2], writer); 
                        break; 
                }
                
                line = reader.readLine(); 
            }
            
            writer.flush();
            writer.close();
            reader.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void makeGraph(String file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine(); 
            
            System.out.println("start making graph...");
            
            graph = new AdjacencyListGraph(); 
            
            while(line != null){
                String[] in = line.split("\\s+"); 
                
                Vertex v1 = new Vertex(in[0]);
                Vertex v2 = new Vertex(in[2]);
                
                graph.addVertex(v1);
                graph.addVertex(v2);
                
                line = br.readLine(); 
            }
            
            br.close(); 
            System.out.println("finished adding vertices.");
            
            br = new BufferedReader(new FileReader(file)); 
            line = br.readLine(); 
            
            while(line != null){
                String[] in = line.split("\\s+"); 
                
                Vertex v1 = new Vertex(in[0]);
                Vertex v2 = new Vertex(in[2]);
                
                graph.addEdge(v1, v2);
                
                line = br.readLine();
            }
            
            System.out.println("finished adding edges.");
            System.out.println("make graph complete.");
            
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private static void getCommonInfluences(String userA, String userB, FileWriter writer){
        System.out.println("getting common influences for " + userA + " and " + userB + "...");
        Vertex a = new Vertex(userA); 
        Vertex b = new Vertex(userB); 
        
        List<Vertex> commonUpstream = Algorithms.commonUpstreamVertices(graph, a, b); 
        
        try {
            writer.write("query: commonInfluencers " + userA + " " + userB + "\n");
            writer.write("<result>\n");
            for(Vertex v : commonUpstream){
                System.out.println(v.getLabel());
                writer.write(v.getLabel() + " \n");
                
            }
            writer.write("</result>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void getNumRetweets(String userA, String userB, FileWriter writer){
        System.out.println("getting number of retweets for " + userA + " and " + userB + "...");
        
        Vertex a = new Vertex(userA); 
        Vertex b = new Vertex(userB); 
        
        try{
            int numRetweets = Algorithms.shortestDistance(graph, a, b) - 1; 
            System.out.println(numRetweets);
            
            writer.write("query: numRetweets " + userA + " " + userB + "\n");
            writer.write("<result>\n"); 
            writer.write(numRetweets + "\n");
            writer.write("</result>\n"); 
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}