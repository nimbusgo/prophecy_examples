package io.prophecy.pipelines.configscdmerge

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import io.prophecy.pipelines.configscdmerge.config._
import io.prophecy.pipelines.configscdmerge.udfs.UDFs._
import io.prophecy.pipelines.configscdmerge.udfs._
import io.prophecy.pipelines.configscdmerge.graph._
import io.prophecy.pipelines.configscdmerge.graph.GenerateRandomIncrement_0
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_customers_scd1            = customers_scd1(spark)
    val df_Filter_1                  = Filter_1(spark, df_customers_scd1)
    val df_GenerateRandomIncrement_0 = GenerateRandomIncrement_0.apply(spark)
    Scd1Merge(spark, df_GenerateRandomIncrement_0)
    val (df_DataValidator_1_out0, df_DataValidator_1_out1) =
      DataValidator_1(spark, df_customers_scd1)
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
      .set("prophecy.metadata.pipeline.uri", "2348/pipelines/config_scd1_merge")
    MetricsCollector.start(spark,            "2348/pipelines/config_scd1_merge")
    apply(spark)
    MetricsCollector.end(spark)
  }

}
