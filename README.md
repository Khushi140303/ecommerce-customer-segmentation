# ecommerce-customer-segmentation
This project aims to analyze the e-commerce data and to do that I work with a series of Scala applications designed to do specific tasks. The applications perform tasks ranging from data ingestion and ETL (extract, transform, load) to analytics and profiling, as specified in the homework by the professor. 
# Directory Structure
- ‘/user/ka2653_nyu_edu/’: Home directory on HDFS where all project-related files and directories are stored.
- ‘/user/ka2653_nyu_edu/output/’: Directory where the output of each application is stored.


# Files Description


- ‘official_ecommerce_data.csv’: The raw input dataset of e-commerce transactions (can be found on the hdfs.)
- ‘AnalyticsApp.scala’: Scala application for performing data analytics on the e-commerce dataset.
- ‘DataIngestionApp.scala’: Scala application for ingesting data from the raw CSV file into a format suitable for analysis.
- ‘ETLApp.scala’: Scala application for transforming the ingested data, including cleaning and preparing for analysis as required on specific columns.
- ‘DataProfilingApp.scala’: Scala application for profiling the data to understand its characteristics, printing the basic statistical information after analysis.


# Building the Code


The Scala applications are designed/ coded to be compiled and executed within the Spark shell on Dataproc. We read input data from HDFS, process it, and store the results back to HDFS.


# Running the Code


To run any of the Scala applications, please follow these steps in the Spark shell:


1. Start the Spark shell on your Dataproc cluster.
* spark-shell --deploy-mode client


2. Load the Scala script using the ‘: load’ command. 
* :load AnalyticsApp.scala
* :load DataIngestionApp.scala
* :load ETLApp.scala
* :load DataProfilingApp.scala
  
3. See the output on the screen by calling the main method.
* scala>AnalyticsApp.main(Array.empty[String])
* scala>DataIngestionApp.main(Array.empty[String])
* scala>ETLApp.main(Array.empty[String])
* scala>DataProfilingApp.main(Array.empty[String])
