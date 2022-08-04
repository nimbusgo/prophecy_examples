package graph

import io.prophecy.libs._
import config.ConfigStore._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object customers_scd2_1 {

  def apply(spark: SparkSession): DataFrame =
    spark.read.format("delta").load("/data/tmp/scd2_customers")

}
