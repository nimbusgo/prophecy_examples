package graph.GenerateData

import io.prophecy.libs._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object AddRandomID {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.withColumn("random_id", floor(lit(3) * rand()))

}
