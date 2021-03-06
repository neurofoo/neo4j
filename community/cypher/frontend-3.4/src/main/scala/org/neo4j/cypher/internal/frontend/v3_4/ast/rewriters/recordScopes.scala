/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.cypher.internal.frontend.v3_4.ast.rewriters

import org.neo4j.cypher.internal.util.v3_4.{Rewriter, topDown}
import org.neo4j.cypher.internal.frontend.v3_4.semantics.SemanticState
import org.neo4j.cypher.internal.v3_4.expressions.{MapProjection, PatternComprehension}

case class recordScopes(semanticState: SemanticState) extends Rewriter {

  def apply(that: AnyRef): AnyRef = instance.apply(that)

  private val instance: Rewriter = topDown(Rewriter.lift {
    case x: PatternComprehension =>
      x.withOuterScope(semanticState.recordedScopes(x).symbolDefinitions.map(_.asVariable))
    case x: MapProjection =>
      x.withDefinitionPos(semanticState.recordedScopes(x).symbolTable(x.name.name).definition.position)
  })
}
