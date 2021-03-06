package com.parallelmachines.reflex.components.flink.streaming.dummy

import com.parallelmachines.reflex.components.flink.streaming.{FlinkStreamingComponent, StreamExecutionEnvironment}
import org.mlpiper.infrastructure._

import scala.collection.mutable.ArrayBuffer
import scala.reflect.runtime.universe._

class TestDataItemBSource extends FlinkStreamingComponent {
  override val isSource: Boolean = true
  override val group: String = ComponentsGroups.connectors
  override val label: String = "Test Source"
  override val description: String = "Testing purposes source component"
  override val version: String = "1.0.0"

  override val inputTypes: ConnectionList = ConnectionList.empty()

  val output = ComponentConnection(
    tag = typeTag[TestDataItemB],
    label = "Output",
    description = "Output",
    group = ConnectionGroups.OTHER)
  override var outputTypes: ConnectionList = ConnectionList(output)

  override lazy val paramInfo: String = s"""[]""".stripMargin

  override def configure(paramMap: Map[String, Any]): Unit = {

  }

  override def materialize(env: StreamExecutionEnvironment, dsArr: ArrayBuffer[DataWrapperBase], errPrefixStr: String): ArrayBuffer[DataWrapperBase] = {
    return ArrayBuffer[DataWrapperBase]()
  }
}
