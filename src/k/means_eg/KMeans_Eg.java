/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;





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
        
        
        double[] sses = new double[99];
        
        KMeans kmeans = new KMeans();
        
//        for (int k = 2; k <= 100; k++) {
            Cluster[] clusters = kmeans
                                    .maxLoops(500000)
                                    .clusters(50)
                                    .distance(KMeans::euclidean)
                                    .runWith(points);
            
//            sses[k-2] = kmeans.sse(clusters);
//        }
        
        String[] str = new String[sses.length];
        
        for (int i = 0; i < sses.length; i++) {
            str[i] = String.valueOf(sses[i]);
        }
        
                 
        Files.write(Paths.get("/Users/huynguyen/NetBeansProjects/K-Means_Eg/water-treatment-SSES_zscore.csv"), String.join(",", str).getBytes());
        

        Arrays.stream(clusters).forEach(System.out::println);
        
    }
    
    
     
}
