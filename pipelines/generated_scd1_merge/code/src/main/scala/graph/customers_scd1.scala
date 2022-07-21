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
    if (DeltaTable.isDeltaTable(spark, "dbfs:/data/tmp/customers_merge_1"))
      DeltaTable
        .forPath(spark, "dbfs:/data/tmp/customers_merge_1")
        .as("target")
        .merge(in.as("source"),
               col("source.customer_id") === col("target.customer_id")
        )
        .whenMatched()
        .updateAll()
        .whenNotMatched()
        .insertAll()
        .execute()
    else
      in.write
        .format("delta")
        .option("mergeSchema",     true)
        .option("overwriteSchema", false)
        .mode("overwrite")
        .save("dbfs:/data/tmp/customers_merge_1")
  }

}
