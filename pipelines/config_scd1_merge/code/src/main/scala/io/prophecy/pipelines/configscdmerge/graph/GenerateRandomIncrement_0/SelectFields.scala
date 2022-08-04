package io.prophecy.pipelines.configscdmerge.graph.GenerateRandomIncrement_0

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import io.prophecy.pipelines.configscdmerge.udfs.UDFs._
import io.prophecy.pipelines.configscdmerge.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object SelectFields {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(col("customer_id"),
              col("tax_id"),
              col("tax_code"),
              col("customer_name"),
              col("state")
    )

}
