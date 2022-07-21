package config

import config.ConfigStore._
import pureconfig._
import io.prophecy.libs._

case class Config(
  fabricName:   String,
  j_cond:       String,
  in0_id:       String,
  in1_id:       String,
  target_state: String,
  s_cond:       String
) extends ConfigBase
