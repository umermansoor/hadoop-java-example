## Hadoop Map-Reduce Example in Java

**Get up and running in less than 5 minutes**

### Overview
This program demonstrates Hadoop's Map-Reduce concept in Java using a very simple example. The input is raw data files listing earthquakes by region, magnitude and other information. 

> nc,71920701,1,”Saturday, January 12, 2013 19:43:18 UTC”,38.7865,-122.7630,**1.5**,1.10,27,**“Northern California”**

The fields in bold are magnitude of the quake and name of region where the reading was taken, respectively. The _goal_ is to process all input files to find the maximum magnitude quake reading for every region listed. The output is in the form:

        "region_name"      <maximum magnitude of earthquake recorded> 

The raw data files are in the `input/` folder.

### Instructions for Setting Up Hadoop
1. Download Hadoop 1.1.1 binary. [Mirror](http://mirror.csclub.uwaterloo.ca/apache/hadoop/common/hadoop-1.1.1/hadoop-1.1.1.tar.gz)


2. Extract it to a folder on your computer:
        
        $ tar xvfz hadoop-1.1.1.tar.gz

3. Setup JAVA_HOME environment variable to point to the directory where Java is installed. For my Mac OS X, I did the following:

        $ export JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Home

 Note: If you are running Lion, you may want to update the JAVA_HOME to point to `java_home` command which outputs Java's home directory, that is,

        $ export JAVA_HOME=$(/usr/libexec/java_home)

4. Setup HADOOP_INSTALL environment variable to point the directory where you extracted hadoop binary in step 2:

        $ export HADOOP_INSTALL=/Users/umermansoor/Documents/hadoop-1.1.1

5. Edit the PATH environment variable:

        $ export PATH=$PATH:$HADOOP_INSTALL/bin

> Or you can add these variables to your standard shell script. For example, checkout my Mac OSX's [`~/.bash_profile`](https://gist.github.com/4525814)

### Instructions for Running the Sample
1. Clone the project:

	    $ git clone git@github.com:umermansoor/hadoop-java-example.git
	
2. Change to the project directory:

	    $ cd hadoop-java-example

3. Build the project:

	    $ mvn clean install

4. Setup the HADOOP_CLASSPATH environment variable to tell Hadoop where to find the java classes for the sample:

	    $ export HADOOP_CLASSPATH=target/classes/

5. Run the sample. The `output` directory shouldn't exists otherwise this will fail.

        $ hadoop com.umermansoor.App input/ output

> Note: the output will go to the `output/` folder which Hadoop will create when run. The output will be in a file called `part-r-00000`.

### Common Errors:
1. Exception: java.lang.NoClassDefFoundError
Cause: You didn't setup the HADOOP_CLASSPATH environment variable. You need to tell Hadoop where to find the java classes. 
Resolution: In this case, execute the following to setup HADOOP_CLASSPATH variable to point to the `target/classes/` folder.

        $ export HADOOP_CLASSPATH=target/classes/

2. Exception: org.apache.hadoop.mapred.FileAlreadyExistsException or 'Output directory output already exists'. 
Cause: Output directory already exists. Hadoop requires that the output directory doesn't exists when run. 
Resolution: Change the output directory or remove the existing one:

        $ hadoop com.umermansoor.App input/input.csv output_new 

> Note: Hadoop failing if the output folder already exists is a good thing: it ensures that you don't accidentally overwrite your previous output, as typical Hadoop jobs take hours to complete.

