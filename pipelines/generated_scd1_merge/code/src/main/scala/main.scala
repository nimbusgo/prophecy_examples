import io.prophecy.libs._
import config.ConfigStore._
import config._
import udfs.UDFs._
import udfs._
import graph._
import graph.GenerateRandomIncrement_0
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_GenerateRandomIncrement_0 = GenerateRandomIncrement_0.apply(spark)
    customers_scd1(spark, df_GenerateRandomIncrement_0)
    val df_customers_scd1_1 = customers_scd1_1(spark)
    val df_Filter_1         = Filter_1(spark, df_customers_scd1_1)
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
                   "2348/pipelines/generated_scd1_merge"
    )
    MetricsCollector.start(spark, "2348/pipelines/generated_scd1_merge")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
