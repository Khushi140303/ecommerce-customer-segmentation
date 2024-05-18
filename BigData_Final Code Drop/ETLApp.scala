import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object ETLApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Data Cleaning")
      .getOrCreate()

    val rawData = spark.read.parquet("hdfs:///user/ka2653_nyu_edu/output/data_ingested")
    val cleanData = rawData
      .na.fill(0, Seq("Customer_ID"))
      .filter($"Quantity" > 0)

    cleanData.write.mode("overwrite").parquet("hdfs:///user/ka2653_nyu_edu/output/clean_data")
    cleanData.show()
    spark.stop()
  }
}

