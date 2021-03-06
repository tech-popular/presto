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
package com.facebook.presto.orc;

import com.facebook.presto.hive.HiveFileContext;
import com.facebook.presto.orc.StripeReader.StripeId;
import io.airlift.slice.Slice;

import java.io.IOException;
import java.util.Map;

public interface StripeMetadataSource
{
    Slice getStripeFooterSlice(OrcDataSource orcDataSource, StripeId stripeId, long footerOffset, int footerLength, HiveFileContext hiveFileContext)
            throws IOException;

    Map<StreamId, OrcDataSourceInput> getInputs(
            OrcDataSource orcDataSource,
            StripeId stripeId,
            Map<StreamId, DiskRange> diskRanges,
            HiveFileContext hiveFileContext)
            throws IOException;
}
