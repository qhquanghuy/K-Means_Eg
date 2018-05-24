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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fileName = "/Users/huynguyen/NetBeansProjects/K-Means_Eg/water-treatment.csv";
        DataPoint[] points = CSVParser.parseFile(fileName);
        
        
        for (DataPoint point : points) {
            point.zscore();
        }
        
        Cluster[] clusters = new KMeans()
                                    .maxLoops(5000)
                                    .numberOfClusters(15)
                                    .distance(KMeans::euclidean)
                                    .runWith(points);
                System.out.println("done");
        
        
        
        
    }
    
    
     
}
