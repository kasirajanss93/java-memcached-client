/*
 * Copyright 2018-2019 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing.contrib.spymemcached;

import io.opentracing.Span;
import net.spy.memcached.internal.BulkGetCompletionListener;
import net.spy.memcached.internal.BulkGetFuture;
import net.spy.memcached.ops.OperationStatus;

class TracingBulkGetCompletionListener implements
    BulkGetCompletionListener {

  private final Span span;

  TracingBulkGetCompletionListener(Span span) {
    this.span = span;
  }

  @Override
  public void onComplete(BulkGetFuture<?> future) {
    OperationStatus status = future.getStatus();
    TracingHelper.setStatusAndFinish(span, status);
  }
}
