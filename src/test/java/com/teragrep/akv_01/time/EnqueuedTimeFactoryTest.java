/*
 * Teragrep Key Value Mapping for Microsoft Azure EventHub
 * Copyright (C) 2024 Suomen Kanuuna Oy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * Additional permission under GNU Affero General Public License version 3
 * section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with other code, such other code is not for that reason alone subject to any
 * of the requirements of the GNU Affero GPL version 3 as long as this Program
 * is the same Program as licensed from Suomen Kanuuna Oy without any additional
 * modifications.
 *
 * Supplemented terms under GNU Affero General Public License version 3
 * section 7
 *
 * Origin of the software must be attributed to Suomen Kanuuna Oy. Any modified
 * versions must be marked as "Modified version of" The Program.
 *
 * Names of the licensors and authors may not be used for publicity purposes.
 *
 * No rights are granted for use of trade names, trademarks, or service marks
 * which are in The Program if any.
 *
 * Licensee must indemnify licensors and authors for any liability that these
 * contractual assumptions impose on licensors and authors.
 *
 * To the extent this program is licensed as part of the Commercial versions of
 * Teragrep, the applicable Commercial License may apply to this file if you as
 * a licensee so wish it.
 */
package com.teragrep.akv_01.time;

import com.teragrep.akv_01.Stubable;
import com.teragrep.akv_01.event.metadata.time.EnqueuedTime;
import com.teragrep.akv_01.event.metadata.time.EnqueuedTimeFactory;
import com.teragrep.akv_01.event.metadata.time.EnqueuedTimeImpl;
import com.teragrep.akv_01.event.metadata.time.EnqueuedTimeStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public final class EnqueuedTimeFactoryTest {

    @Test
    void createEnqueuedTime() {
        final String time = "2010-01-01T00:00:00";
        final EnqueuedTimeFactory factory = new EnqueuedTimeFactory(time);
        final EnqueuedTime enqueuedTime = factory.enqueuedTime();
        Assertions.assertEquals(EnqueuedTimeImpl.class, enqueuedTime.getClass());
        Assertions.assertEquals(time, enqueuedTime.toString());
        Assertions.assertEquals(ZonedDateTime.parse(time + "Z"), enqueuedTime.zonedDateTime());
    }

    @Test
    void createStub() {
        final String time = null;
        final EnqueuedTimeFactory factory = new EnqueuedTimeFactory(time);
        final EnqueuedTime enqueuedTime = factory.enqueuedTime();
        Assertions.assertEquals(EnqueuedTimeStub.class, enqueuedTime.getClass());
        Assertions.assertInstanceOf(Stubable.class, enqueuedTime);
        final UnsupportedOperationException exception = Assertions
                .assertThrows(UnsupportedOperationException.class, enqueuedTime::zonedDateTime);
        Assertions.assertEquals("Stub object does not provide zonedDateTime", exception.getMessage());
    }
}
