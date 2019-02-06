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
package org.apache.flink.streaming.scala.examples.common.parameters.ml.sgd

import org.apache.flink.streaming.scala.examples.common.parameters.common.PositiveDoubleParameter
import org.apache.flink.streaming.scala.examples.common.parameters.tools.WithArgumentParameters


case object GammaFrequency extends PositiveDoubleParameter {
  override val key = "gammaFrequency"
  override val label: String = "Gamma Frequency"
  override val required = false
  override val defaultValue: Option[Double] = Some(1000)
  override val description = "Number of samples to process before we combine the current model with the previously aggregated model with a decay factor. (Default: 1000)"
}

trait GammaFrequency[Self] extends WithArgumentParameters {

  that: Self =>

  def setGammaFrequency(gammaFrequency: Double): Self = {
    this.parameters.add(GammaFrequency, gammaFrequency)
    that
  }
}
