/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

import java.io.IOException;



/**
 *
 * @author huynguyen
 */
public class KMeans_Eg {

    final static int k = 15;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fileName = "/Users/huynguyen/NetBeansProjects/K-Means_Eg/water-treatment.csv";
        DataPoint[] points = CSVParser.parseFile(fileName);
        
        
        Cluster[] clusters = new KMeans()
                                    .maxLoops(5000)
                                    .numberOfClusters(4)
                                    .distance(KMeans::euclidean)
                                    .runWith(points);
        
        System.out.println("done");
        
        
        
        
    }
    
    
     
}
