package public_datasets

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object DropRandomId {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.drop("random_id")

}
