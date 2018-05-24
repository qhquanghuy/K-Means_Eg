/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

import java.util.ArrayList;

/**
 *
 * @author huynguyen
 */
public class Cluster {
    public DataPoint center;
    public ArrayList<DataPoint> points;

    public Cluster(DataPoint center) {
        this.center = center;
        this.points = new ArrayList<>();
    }
    
    
    public DataPoint centroid() {
        
        double[] vector = new double[this.center.vector.length];
        
        for (DataPoint point : points) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] += point.vector[i];
            }
        }
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] / points.size();
        }
        
        return new DataPoint(vector);
    }
    
    public boolean shouldUpdateCenter(double epsilon) {
        double d = KMeans.euclidean(new Pair(this.center, this.centroid()));
        return d > epsilon;
    }
    
}
