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

import lombok.experimental.UtilityClass;

@UtilityClass
public class FakeTime {
  public static final String PREFIX = "doodle.jvmtool.faketime";
  public static final String OFFSET_PROPERTY = PREFIX + ".offset.ms";
  public static final String ABS_PROPERTY = PREFIX + ".abs.ms";

  static final FakeTimeOps OFFSET_OPS = new FakeTimeOffset();
  static final FakeTimeAbs ABS_OPS = new FakeTimeAbs();

  public static FakeTimeOps offset() {
    return OFFSET_OPS;
  }

  public static FakeTimeOps abs() {
    return ABS_OPS;
  }

  public static void reset() {
    OFFSET_OPS.reset();
    ABS_OPS.reset();
  }
}
