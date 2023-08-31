/*
 * Copyright (c) 2022-present Doodle. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.doodle.jvmtool.faketime;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class FakeTimeOps {
  final String property;
  volatile long value;

  static final AtomicLongFieldUpdater<FakeTimeOps> UPDATER =
      AtomicLongFieldUpdater.newUpdater(FakeTimeOps.class, "value");

  FakeTimeOps(String property) {
    this.property = property;
    String value = System.getProperty(property);
    this.value = (value == null || value.isEmpty()) ? 0L : Long.parseLong(value);
  }

  public long get() {
    return this.value;
  }

  protected abstract long getAndSet(long value);

  public void reset() {
    UPDATER.getAndUpdate(
        this,
        value -> {
          System.clearProperty(property);
          return 0L;
        });
  }
}
