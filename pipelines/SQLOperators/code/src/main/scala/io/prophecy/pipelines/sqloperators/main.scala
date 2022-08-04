package io.prophecy.pipelines.sqloperators

import io.prophecy.libs._
import io.prophecy.pipelines.sqloperators.config.ConfigStore._
import io.prophecy.pipelines.sqloperators.config._
import io.prophecy.pipelines.sqloperators.udfs.UDFs._
import io.prophecy.pipelines.sqloperators.udfs._
import io.prophecy.pipelines.sqloperators.graph._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_tpch_customer       = tpch_customer(spark)
    val df_SelectDistinct      = SelectDistinct(spark,      df_tpch_customer)
    val df_Filter_3_1          = Filter_3_1(spark,          df_SelectDistinct)
    val df_SqlExpressionChecks = SqlExpressionChecks(spark, df_tpch_customer)
    val df_AggChecks           = AggChecks(spark,           df_tpch_customer)
    val df_Filter_3            = Filter_3(spark,            df_AggChecks)
    val df_Filter_1            = Filter_1(spark,            df_SqlExpressionChecks)
    val df_WindowFunction      = WindowFunction(spark,      df_tpch_customer)
    val df_Filter_2            = Filter_2(spark,            df_WindowFunction)
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
      .set("prophecy.metadata.pipeline.uri", "2348/pipelines/SQLOperators")
    MetricsCollector.start(spark,            "2348/pipelines/SQLOperators")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
