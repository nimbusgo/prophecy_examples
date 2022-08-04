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
    val df_SQLStatement_1 = SQLStatement_1(spark)
    val df_Filter_1_1     = Filter_1_1(spark, df_SQLStatement_1)
    val df_SQLStatement_0 = SQLStatement_0(spark)
    val df_Filter_1       = Filter_1(spark,   df_SQLStatement_0)
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
      .set("prophecy.metadata.pipeline.uri", "2348/pipelines/sql_examples")
    MetricsCollector.start(spark,            "2348/pipelines/sql_examples")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
