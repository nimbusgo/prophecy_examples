package io.prophecy.pipelines.configscdmerge.graph

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object customers_scd1 {

  def apply(spark: SparkSession): DataFrame =
    spark.read.format("delta").load("dbfs:/data/tmp/customers_merge_1")

}
