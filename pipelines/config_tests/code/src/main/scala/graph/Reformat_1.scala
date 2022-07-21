package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Reformat_1 {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(
      col("customer_id").as("alt_customer_id"),
      col("customer_name").as("alt_customer_name"),
      lit(Config.j_cond).as("cond"),
      (col("state") === lit(Config.target_state)).as("is_target"),
      col("state").as("alt_state")
    )

}
