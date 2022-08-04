import io.prophecy.libs._
import config.ConfigStore._
import config._
import udfs.UDFs._
import udfs._
import graph._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_customers_raw  = customers_raw(spark)
    val df_Reformat_2     = Reformat_2(spark,     df_customers_raw)
    val df_Deduplicate_1  = Deduplicate_1(spark,  df_Reformat_2)
    val df_Reformat_1     = Reformat_1(spark,     df_Deduplicate_1)
    val df_SQLStatement_1 = SQLStatement_1(spark, df_Reformat_1)
    val df_Join_1_1       = Join_1_1(spark,       df_Deduplicate_1, df_Reformat_1)
    val df_Join_1         = Join_1(spark,         df_Deduplicate_1, df_Reformat_1)
    val df_Filter_1       = Filter_1(spark,       df_Join_1)
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
    spark.conf
      .set("prophecy.metadata.pipeline.uri", "2348/pipelines/config_tests")
    MetricsCollector.start(spark,            "2348/pipelines/config_tests")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
