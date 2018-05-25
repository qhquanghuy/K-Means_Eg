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

    @Override
    public String toString() {
        String s = new String();
        
        for (double d : vector) {
            s += String.valueOf(d) + ", ";
        }
        
        return s;
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
    
    public void unitLength() {
        double len = this.euclideanLength();
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = this.vector[i] / len;
        }
    }
    
    public double euclideanLength() {
        double len = 0.0;
        for (int i = 0; i < this.vector.length; i++) {
            len += this.vector[i] * this.vector[i];
        }
        return Math.sqrt(len);
    }
    
    public void rescaling() {
        double min = this.min();
        double scale = this.max() - min;
        
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = (this.vector[i] - min) / scale;
        }
    }
    
    public void meanNorm() {
        double mean = this.mean();
        double scale = this.max() - mean;
        
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = (this.vector[i] - mean) / scale;
        }
    }
    
    public double min() {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < this.vector.length; i++) {
            min = min > this.vector[i] ? this.vector[i] : min;
        }
        return min;
    }
    public double max() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < this.vector.length; i++) {
            max = max < this.vector[i] ? this.vector[i] : max;
        }
        return max;
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
