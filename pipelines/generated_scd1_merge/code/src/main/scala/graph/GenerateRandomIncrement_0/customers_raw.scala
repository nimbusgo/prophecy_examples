package graph.GenerateRandomIncrement_0

import io.prophecy.libs._
import config.ConfigStore._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object customers_raw {

  def apply(spark: SparkSession): DataFrame =
    spark.read
      .format("csv")
      .option("header",      true)
      .option("inferSchema", true)
      .option("sep",         ",")
      .load("dbfs:/databricks-datasets/retail-org/customers/customers.csv")

}
