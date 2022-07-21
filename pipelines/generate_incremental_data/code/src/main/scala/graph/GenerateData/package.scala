package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
package object GenerateData {

  def apply(spark: SparkSession): DataFrame = {
    val df_customers_raw    = customers_raw(spark)
    val df_SelectFields     = SelectFields(spark,     df_customers_raw)
    val df_DedupeCustomerId = DedupeCustomerId(spark, df_SelectFields)
    val df_AddRandomID      = AddRandomID(spark,      df_DedupeCustomerId)
    val (df_Split3_out0, df_Split3_out1, df_Split3_out2) =
      Split3(spark, df_AddRandomID)
    val df_shift_ids    = shift_ids(spark,    df_Split3_out1)
    val df_random_edits = random_edits(spark, df_Split3_out2)
    val df_Union        = Union(spark,        df_Split3_out0, df_shift_ids, df_random_edits)
    val df_DropRandomId = DropRandomId(spark, df_Union)
    df_DropRandomId
  }

}
