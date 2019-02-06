package org.apache.flink.streaming.scala.examples.clustering.stat.algos.pca

import breeze.linalg.{DenseVector => BreezeDenseVector}
import com.parallelmachines.reflex.common.InfoType._
import org.apache.flink.streaming.scala.examples.clustering.utils.ParsingUtils
import org.apache.flink.streaming.scala.examples.common.stats._

case class ExplainedVarianceStat(explainedVariance: BreezeDenseVector[Double])
  extends Serializable {
  override def toString: String = {
    ParsingUtils.breezeDenseVectorToJsonMap(explainedVariance)
  }
}

object ExplainedVarianceStat {
  def getAccumulator(explainedVarianceWrapper: ExplainedVarianceStat)
  : GlobalAccumulator[ExplainedVarianceStat] = {
    StatInfo(
      StatNames.ExplainedVariance,
      StatPolicy.REPLACE,
      StatPolicy.REPLACE
    ).toGlobalStat(
      explainedVarianceWrapper,
      accumDataType = AccumData.getGraphType(explainedVarianceWrapper.explainedVariance),
      infoType = InfoType.General
    )
  }
}
