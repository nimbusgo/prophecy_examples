package io.prophecy.pipelines.configscdmerge.graph.GenerateRandomIncrement_0

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import io.prophecy.pipelines.configscdmerge.udfs.UDFs._
import io.prophecy.pipelines.configscdmerge.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object random_edits {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.withColumn(
      "customer_name",
      expr("if((rand() > 0.5D), upper(customer_name), lower(customer_name))")
    )

}
