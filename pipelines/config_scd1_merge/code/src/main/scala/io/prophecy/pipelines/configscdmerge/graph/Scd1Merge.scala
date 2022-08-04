package io.prophecy.pipelines.configscdmerge.graph

import io.prophecy.libs._
import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import io.prophecy.pipelines.configscdmerge.udfs.UDFs._
import io.prophecy.pipelines.configscdmerge.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Scd1Merge {
  def apply(spark: SparkSession, in: DataFrame): Unit = {
    import _root_.io.delta.tables._
    if (DeltaTable.isDeltaTable(spark, Config.output_path))
      DeltaTable
        .forPath(spark, Config.output_path)
        .as("target")
        .merge(in.as("source"),
               Config.merge_condition
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
        .save(Config.output_path)
  }

}
