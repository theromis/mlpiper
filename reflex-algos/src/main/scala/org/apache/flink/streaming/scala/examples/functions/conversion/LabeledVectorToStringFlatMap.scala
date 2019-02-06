/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.streaming.scala.examples.functions.conversion

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.streaming.scala.examples.clustering.math.LabeledVector
import org.apache.flink.streaming.scala.examples.clustering.utils.ParsingUtils
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory

class LabeledVectorToStringFlatMap(elementSeparator: Char,
                                   labeledElementSeparator: Option[Char] = None,
                                   timestampElementSeparator: Option[Char] = None,
                                   debug: Boolean = false)
  extends RichFlatMapFunction[LabeledVector[Double], String] {

  private val logger = LoggerFactory.getLogger(getClass)

  override def flatMap(value: LabeledVector[Double], out: Collector[String]): Unit = {
    val labeledVectorString = ParsingUtils.labeledVectorToString(
      value, elementSeparator, labeledElementSeparator, timestampElementSeparator, debug)

    if (labeledVectorString.isDefined) {
      out.collect(labeledVectorString.get)
    } else {
      logger.error(s"Failed to convert LabeledVector (${value}) to string")
    }
  }
}
