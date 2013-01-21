package com.umermansoor;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import org.apache.hadoop.io.Text;



public class EarthquakeReducer extends 
        Reducer<Text, DoubleWritable, Text, DoubleWritable> 
{

    /**
     * The `Reducer` function. Iterates through all earthquake magnitudes for a
     * region to find the maximum value. The output key is the `region name` and  
     * the value is the `maximum magnitude` for that region.
     * @param key - Input key - Name of the region
     * @param values - Input Value - Iterator over quake magnitudes for region
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
