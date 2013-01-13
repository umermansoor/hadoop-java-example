package com.umermansoor;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EarthquakeReducer extends 
        Reducer<Text, DoubleWritable, Text, DoubleWritable> 
{

    /**
     * The `Reducer` function. Iterates through all earthquake magnitudes for a
     * region to find the maximum value. The output is the region name and the 
     * maximum value of the magnitude.
     * @param key - The name of the region
     * @param values - Iterator over earthquake magnitudes in the region
     * @param context - Used for collecting output
     * @throws IOException
     * @throws InterruptedException 
     */
    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, 
            Context context) throws IOException, InterruptedException {
        
        // Standard algorithm for finding the max value
        double maxMagnitude = Double.MIN_VALUE;
        for (DoubleWritable value : values) {
            maxMagnitude = Math.max(maxMagnitude, value.get());
        }
        
        context.write(key, new DoubleWritable(maxMagnitude));
    }
}
