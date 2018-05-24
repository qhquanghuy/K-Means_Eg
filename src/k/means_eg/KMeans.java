/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author huynguyen
 */
public class KMeans {
    
    
    private int k = 5;
    private int loops = 5000;
    Function<Pair<DataPoint, DataPoint>,Double> distance = KMeans::euclidean;// distance between 2 vector, apply to each dimension;
    public KMeans() {
        
    }
    
    public KMeans numberOfClusters(int k) {
        this.k = k;
        return this;
    }
    
    public KMeans maxLoops(int loops) {
        this.loops = loops;
        return this;
    }
    
    public KMeans distance(Function<Pair<DataPoint, DataPoint>,Double> f) {
        this.distance = f;     
        
        return this;
    }
    
    private Cluster[] randomCenter(DataPoint[] points, int k) {
        Random rand = new Random();
        int numberOfPoints = points.length;
        HashSet<Integer> set = new HashSet<>();
        
        while (set.size() < k) {
            int idex = rand.nextInt(numberOfPoints);
            set.add(idex);
        }
        
        return set.stream()
                .map(idx -> new Cluster(points[idx]))
                .toArray(Cluster[]::new);
        
    } 
    
    
    public Cluster[] runWith(DataPoint[] points) {
        Cluster[] clusters = randomCenter(points, k);
        double epsilon = 0;
        
        for (int i = 0; i < this.loops; i++) {
            this.arrangePointToCluster(clusters, points);
        
            Stream<Cluster> stream = Arrays.stream(clusters);

            if(stream.anyMatch(cluster -> cluster.shouldUpdateCenter(epsilon))) {
                for (Cluster cluster : clusters) {
                    cluster.center = cluster.centroid();
                    cluster.points.clear();
                }
            } else {
                return clusters;
            }           
        }
                
        
        return clusters;
                
        
    } 
    
    private void arrangePointToCluster(Cluster[] clusters, DataPoint[] points) {
        for (DataPoint point : points) {
            
            Pair<Double,Integer> minDistance = new Pair(Double.MAX_VALUE, -1);
            for (int idx = 0; idx < clusters.length; idx++) {
                Cluster cluster = clusters[idx];
                Double d = this.distance.apply(new Pair(cluster.center,point));

                minDistance = minDistance.first > d ? new Pair(d,idx) : minDistance;
                               
            }
            clusters[minDistance.second].points.add(point);
        }
    }
    
    
       
    
    public static Double euclidean(Pair<DataPoint, DataPoint> pairVectors) {
        Double sum = 0.0;
        for (int i = 0; i < pairVectors.first.vector.length; i++) {
            double d = pairVectors.first.vector[i] - pairVectors.second.vector[i];
            sum += d*d;
        }
        return Math.sqrt(sum);
    }
    
    
}
