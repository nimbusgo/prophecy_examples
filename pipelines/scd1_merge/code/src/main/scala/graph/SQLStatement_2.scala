package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object SQLStatement_2 {

  def apply(spark: SparkSession): DataFrame = {
    List()
      .zip(List("in0"))
      .foreach(dfWithSlugName =>
        dfWithSlugName._1.createOrReplaceTempView(dfWithSlugName._2)
      )
    spark.sql("select * from default.customer_table")
  }

}
