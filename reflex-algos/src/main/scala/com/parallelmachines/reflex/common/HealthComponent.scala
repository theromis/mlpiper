package com.parallelmachines.reflex.common

import breeze.linalg.DenseVector
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

/**
  * HealthComponent is a base class for new Health Components
  * */
trait HealthComponent extends Serializable {

  def generateHealth(): Unit
  def generateHealthAndCompare(): Unit
  def getName(): String

  private var modelId: Option[String] = None
  def setModelId(value: String): Unit = {
    if (value != null) {
      modelId = Some(value)
    } else {
      modelId = None
    }
  }

  def getModelId(): Option[String] = {
    modelId
  }
}

/**
  * HealthComponentFlink is a base class for new Flink Health Components.
  *
  * Defines Flink specific data types setters
  * */
trait HealthComponentFlink extends HealthComponent {
  def setIncomingHealthStream(input: DataStream[String]): Unit
}

/**
  * HealthComponentSpark is a base class for new Spark Health Components.
  *
  * Defines Spark specific data types setters
  * */
trait HealthComponentSpark extends HealthComponent {
  def setIncomingHealthStream(input: RDD[String]): Unit
  def setContext(sc: SparkContext): Unit
  def setRddOfDenseVector(_rddOfDenseVector: RDD[DenseVector[Double]]): Unit
  def setDfOfDenseVector(_dfOfDenseVector: DataFrame): Unit
}
