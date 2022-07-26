package graph.GenerateData

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

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
