import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import graph._
import graph.GenerateData

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_GenerateData = GenerateData.apply(spark)
    val df_Filter_2     = Filter_2(spark, df_GenerateData)
  }

  def main(args: Array[String]): Unit = {
    import config._
    ConfigStore.Config = ConfigurationFactoryImpl.fromCLI(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Prophecy Pipeline")
      .config("spark.default.parallelism",             "4")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()
    spark.conf.set("prophecy.metadata.pipeline.uri",
                   "2348/pipelines/generate_incremental_data"
    )
    MetricsCollector.start(spark)
    apply(spark)
    MetricsCollector.end()
  }

}
