package graph

import io.prophecy.libs._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object SQLStatement_1 {

  def apply(spark: SparkSession, source_data: DataFrame): DataFrame = {
    source_data.createOrReplaceTempView("in0")
    spark.sql("select * from in0")
  }

}
