package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object SQLStatement_1 {

  def apply(spark: SparkSession, in0: DataFrame): DataFrame = {
    in0.createOrReplaceTempView("in0")
    spark.sql("select * from default.customer_table")
  }

}
