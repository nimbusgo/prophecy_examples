import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import graph._
import graph.GenerateRandomIncrement_0

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_GenerateRandomIncrement_0 = GenerateRandomIncrement_0.apply(spark)
    val df_SchemaTransform_1 =
      SchemaTransform_1(spark, df_GenerateRandomIncrement_0)
    customers_scd2(spark,      df_SchemaTransform_1)
    val df_customers_scd2_1 = customers_scd2_1(spark)
    val df_Filter_1         = Filter_1(spark, df_customers_scd2_1)
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
                   "2348/pipelines/generated_scd2_merge"
    )
    MetricsCollector.start(spark)
    apply(spark)
    MetricsCollector.end()
  }

}
