/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

/**
 *
 * @author huynguyen
 */
public class DataPoint {
    double[] vector;
    private boolean isNormalize = false;
    public DataPoint(double[] vector) {
        this.vector = vector;
    }  
    
    
    public void zscore() {
        if(!isNormalize) {
            this.isNormalize = true;
            double mean = this.mean();
            double std = this.std();
            for (int i = 0; i < this.vector.length; i++) {
                this.vector[i] = (this.vector[i] - mean) / std;
            }
        }
        
    }
    
    public double mean() {
        double mean = 0.0;
        for (int i = 0; i < this.vector.length; i++) {
            mean += this.vector[i] / this.vector.length;
        }
        return mean;
    }
    
    public double std() {
        double mean = this.mean();
        double std = 0;
        for (int i = 0; i < this.vector.length; i++) {
            double d = this.vector[i] - mean;
            std += d*d / (this.vector.length - 1);
        }
        return Math.sqrt(std);
        
        
    }
    
}
