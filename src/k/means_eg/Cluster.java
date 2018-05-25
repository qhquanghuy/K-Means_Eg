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

    @Override
    public String toString() {
        String s = center.toString();
        for (int i = 0; i < this.points.size(); i++) {
            s += "\n\t" + i + "---------" + this.points.get(i).toString();
        }
        return s;
    }
    
    
    

    public Cluster(DataPoint center) {
        this.center = center;
        this.points = new ArrayList<>();
    }
    
    
    public DataPoint centroid() {
        
        double[] vector = new double[this.center.vector.length];
        int spaceSize = points.size();
        for (DataPoint point : points) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] += point.vector[i] / spaceSize;
            }
        }

        return new DataPoint(vector);
    }
    
    public boolean shouldUpdateCenter(double epsilon) {
        double d = KMeans.euclidean(new Pair(this.center, this.centroid()));
        return d > epsilon;
    }
    
}
