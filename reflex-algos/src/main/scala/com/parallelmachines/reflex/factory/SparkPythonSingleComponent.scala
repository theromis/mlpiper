package com.parallelmachines.reflex.factory


import com.parallelmachines.reflex.pipeline._

class SparkPythonSingleComponent(componentMetadata: ComponentMetadata, componentDir: String, additionalFiles: List[String])
  extends SingleComponent(componentMetadata, componentDir, additionalFiles) {

  override val engineType = ComputeEngineType.PySpark
}

object SparkPythonSingleComponent {
  def getAsSparkPythonSingleComponent(comp: ReflexPipelineComponent): SparkPythonSingleComponent = {
    require(comp.engineType == ComputeEngineType.PySpark, "Must provide a component of type SparkPythonComponent")
    comp.asInstanceOf[SparkPythonSingleComponent]
  }
}