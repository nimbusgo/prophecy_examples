import io.prophecy.libs._
import config.ConfigStore._
import config._
import udfs.UDFs._
import udfs._
import graph._
import graph.GenerateData
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_GenerateData = GenerateData.apply(spark)
    val df_Filter_2     = Filter_2(spark, df_GenerateData)
  }

  def main(args: Array[String]): Unit = {
    ConfigStore.Config = ConfigurationFactoryImpl.fromCLI(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Prophecy Pipeline")
      .config("spark.default.parallelism",             "4")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()
      .newSession()
    spark.conf.set("prophecy.metadata.pipeline.uri",
                   "2348/pipelines/generate_incremental_data"
    )
    MetricsCollector.start(spark, "2348/pipelines/generate_incremental_data")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
