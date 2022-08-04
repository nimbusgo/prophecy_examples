package io.prophecy.pipelines.configscdmerge.graph.GenerateRandomIncrement_0

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import io.prophecy.pipelines.configscdmerge.udfs.UDFs._
import io.prophecy.pipelines.configscdmerge.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Split3 {

  def apply(
    spark: SparkSession,
    in:    DataFrame
  ): (DataFrame, DataFrame, DataFrame) =
    (in.filter(col("random_id") === lit(0)),
     in.filter(col("random_id") === lit(1)),
     in.filter(col("random_id") === lit(2))
    )

}
