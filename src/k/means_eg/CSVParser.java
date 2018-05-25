/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means_eg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

/**
 *
 * @author huynguyen
 */
public class CSVParser {
    public static DataPoint[] parseFile(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.filter(line -> !line.isEmpty())
                    .map(line -> line.split(","))
                    .map(CSVParser::parseLine)
                    .toArray(DataPoint[]::new);     
        } catch (IOException e) {
            throw e;
	}
    }
    
    
    private static DataPoint parseLine(String[] line) {
        // ignore day attribute
        double[] vector = new double[line.length - 1];
        
        for (int i = 0; i < vector.length; i++) {
            double val;
            try {
                val = Double.parseDouble(line[i+1]);
            } catch (NumberFormatException e) { // if missing val;
                val = 0;
            }
            
            vector[i] = val;
        }
        
        return new DataPoint(vector);
    } 
    
}
