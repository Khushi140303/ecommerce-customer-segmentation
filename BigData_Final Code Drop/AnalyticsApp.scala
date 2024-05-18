import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object AnalyticsApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Sales Analytics")
      .getOrCreate()

    val data = spark.read.parquet("hdfs:///user/ka2653_nyu_edu/output/clean_data")
    val salesByCountry = data.groupBy("Country")
      .agg(sum($"Quantity" * $"Price").alias("TotalSales"))

    salesByCountry.write.mode("overwrite").format("csv").save("hdfs:///user/ka2653_nyu_edu/output/sales_by_country")
    salesByCountry.show()
    spark.stop()
  }
}

