## Hadoop Example in Java

**Get up and running in less than 5 minutes**

Tis program demonstrates using Hadoop's Map-Reduce concept in Java. The input is a raw data file listing earthquakes by region, magnitude and other information. The goal is to find the maximum magnitude of earthquake for a given region.

### Instructions for Setting Up Hadoop
1. Download Hadoop 1.1.1 binary [Mirror](http://mirror.csclub.uwaterloo.ca/apache/hadoop/common/hadoop-1.1.1/hadoop-1.1.1.tar.gz)
2. Extract it to a folder on your computer [tar xvfz hadoop-1.1.1.tar.gz]. 
3. Setup the JAVA_HOME environment variable to point to the directory where Java is installed. For my Mac OSX, I did the following:
    $ export JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Home
4. Setup the HADOOP_INSTALL environment variable to point the directory where you extracted hadoop binary in step 2:
    $ export HADOOP_INSTALL=/Users/umermansoor/Documents/hadoop-1.1.1
5. Edit the PATH environment variable:
    $ export PATH=$PATH:$HADOOP_INSTALL/bin


### Instructions for Running the Sample
1. Clone the project:

	$ git clone git@github.com:umermansoor/hadoop-java-example.git
	
2. Setup the HADOOP_CLASSPATH environment variable to tell Hadoop where to find the java classes for the sample:

	$ export HADOOP_CLASSPATH=target/classes/
	
3. Change to the project directory:

	$ cd hadoop-java-example
	
4. Build the project:

	$ mvn clean install
	
5. Run the sample:


	$ hadoop com.umermansoor.App input/input.csv output
> Note: the output will go to the `output/` folder which Hadoop will create when run.

### Common Errors:
1. Exception: java.lang.NoClassDefFoundError
Cause: You didn't setup the HADOOP_CLASSPATH environment variable.
Resolution: Setup the variable:
    $ export HADOOP_CLASSPATH=target/classes/

2. Exception: org.apache.hadoop.mapred.FileAlreadyExistsException or 'Output directory output already exists'. 
Cause: Output directory already exists. Hadoop requires that the output directory doesn't exists when run. 
Resolution: Change the output directory or remove the existing one:
    $ hadoop com.umermansoor.App input/input.csv output_new -- Change the output directory

> Note: Hadoop failing if the output folder already exists is a good thing: it ensures that you don't accidentally overwrite your previous output.

