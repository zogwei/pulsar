/**
 * Copyright 2016 Yahoo Inc.
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
package com.yahoo.pulsar.common.policies.data;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReplicatorStatsTest {

    @Test
    public void testReplicatorStatsNull() {
        ReplicatorStats rs = new ReplicatorStats();
        try {
            rs.add(null);
            Assert.fail("Must fail.");
        } catch (NullPointerException ne) {
            // ok
        }
    }

    @Test
    public void testReplicatorStatsAdd() {
        ReplicatorStats replicatorStats = new ReplicatorStats();
        replicatorStats.msgRateIn = 5;
        replicatorStats.msgThroughputIn = 10;
        replicatorStats.msgRateOut = 5;
        replicatorStats.msgThroughputOut = 10;
        replicatorStats.msgRateExpired = 3;
        replicatorStats.replicationBacklog = 4;
        replicatorStats.connected = true;
        replicatorStats.replicationDelayInSeconds = 3;
        replicatorStats.add(replicatorStats);
        Assert.assertEquals(replicatorStats.msgRateIn, 10.0);
        Assert.assertEquals(replicatorStats.msgThroughputIn, 20.0);
        Assert.assertEquals(replicatorStats.msgRateOut, 10.0);
        Assert.assertEquals(replicatorStats.msgThroughputOut, 20.0);
        Assert.assertEquals(replicatorStats.msgRateExpired, 6.0);
        Assert.assertEquals(replicatorStats.replicationBacklog, 8);
        Assert.assertTrue(replicatorStats.connected);
        Assert.assertEquals(replicatorStats.replicationDelayInSeconds, 3);
    }
}