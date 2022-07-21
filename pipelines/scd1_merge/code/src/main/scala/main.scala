import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import graph._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_SQLStatement_2 = SQLStatement_2(spark)
    val df_Filter_5       = Filter_5(spark,       df_SQLStatement_2)
    val df_customers_raw  = customers_raw(spark)
    val df_Reformat_1     = Reformat_1(spark,     df_customers_raw)
    val df_Deduplicate_1  = Deduplicate_1(spark,  df_customers_raw)
    val df_Aggregate_1_1  = Aggregate_1_1(spark,  df_Deduplicate_1)
    val df_Filter_2_1     = Filter_2_1(spark,     df_Aggregate_1_1)
    val df_OrderBy_1_1    = OrderBy_1_1(spark,    df_Filter_2_1)
    val df_Filter_4       = Filter_4(spark,       df_Reformat_1)
    val df_SQLStatement_1 = SQLStatement_1(spark, df_customers_raw)
    val df_Filter_3       = Filter_3(spark,       df_SQLStatement_1)
    val df_Aggregate_1    = Aggregate_1(spark,    df_customers_raw)
    val df_Filter_2       = Filter_2(spark,       df_Aggregate_1)
    val df_OrderBy_1      = OrderBy_1(spark,      df_Filter_2)
    customers_scd1(spark, df_Deduplicate_1)
    val df_customers_scd1_read = customers_scd1_read(spark)
    val df_Filter_1            = Filter_1(spark, df_customers_scd1_read)
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
    spark.conf
      .set("prophecy.metadata.pipeline.uri", "2348/pipelines/scd1_merge")
    MetricsCollector.start(spark)
    apply(spark)
    MetricsCollector.end()
  }

}
