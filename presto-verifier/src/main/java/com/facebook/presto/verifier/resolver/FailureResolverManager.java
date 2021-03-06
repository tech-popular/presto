/*
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
package com.facebook.presto.verifier.resolver;

import com.facebook.presto.jdbc.QueryStats;
import com.facebook.presto.verifier.framework.QueryBundle;
import com.facebook.presto.verifier.framework.QueryException;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class FailureResolverManager
{
    private final Set<FailureResolver> failureResolvers;

    public FailureResolverManager(Set<FailureResolver> failureResolvers)
    {
        this.failureResolvers = requireNonNull(failureResolvers, "failureResolvers is null");
    }

    public Optional<String> resolve(QueryStats controlQueryStats, QueryException queryException, Optional<QueryBundle> test)
    {
        for (FailureResolver failureResolver : failureResolvers) {
            Optional<String> resolveMessage = failureResolver.resolve(controlQueryStats, queryException, test);
            if (resolveMessage.isPresent()) {
                return resolveMessage;
            }
        }
        return Optional.empty();
    }
}
