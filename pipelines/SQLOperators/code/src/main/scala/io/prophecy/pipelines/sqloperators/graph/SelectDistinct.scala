package io.prophecy.pipelines.sqloperators.graph

import io.prophecy.libs._
import io.prophecy.pipelines.sqloperators.config.ConfigStore._
import io.prophecy.pipelines.sqloperators.udfs.UDFs._
import io.prophecy.pipelines.sqloperators.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object SelectDistinct {

  def apply(spark: SparkSession, in0: DataFrame): DataFrame = {
    in0.createOrReplaceTempView("in0")
    spark.sql("select c_nationkey, c_mktsegment from in0 group by 1,2")
  }

}
