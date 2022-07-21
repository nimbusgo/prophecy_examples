package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object customers_scd1 {

  def apply(spark: SparkSession, in: DataFrame): Unit = {
    import _root_.io.delta.tables._
    in.write
      .format("delta")
      .option("mergeSchema", true)
      .mode("overwrite")
      .save("dbfs:/data/tmp/customers_merge_1")
  }

}
