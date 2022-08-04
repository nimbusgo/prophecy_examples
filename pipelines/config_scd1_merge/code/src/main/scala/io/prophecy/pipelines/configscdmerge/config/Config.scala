package io.prophecy.pipelines.configscdmerge.config

import io.prophecy.pipelines.configscdmerge.config.ConfigStore._
import pureconfig._
import io.prophecy.libs._

case class Config(
  fabricName:      String,
  output_path:     String,
  merge_condition: String
) extends ConfigBase
