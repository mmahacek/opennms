/*
 * Licensed to The OpenNMS Group, Inc (TOG) under one or more
 * contributor license agreements.  See the LICENSE.md file
 * distributed with this work for additional information
 * regarding copyright ownership.
 *
 * TOG licenses this file to You under the GNU Affero General
 * Public License Version 3 (the "License") or (at your option)
 * any later version.  You may not use this file except in
 * compliance with the License.  You may obtain a copy of the
 * License at:
 *
 *      https://www.gnu.org/licenses/agpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package org.opennms.netmgt.config.dao.thresholding.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

import org.opennms.core.xml.JaxbUtils;
import org.opennms.netmgt.config.dao.thresholding.api.OverrideableThresholdingDao;
import org.opennms.netmgt.config.threshd.ThresholdingConfig;

public class OverrideableThresholdingDaoImpl extends AbstractThresholdingDao implements OverrideableThresholdingDao {
    private ThresholdingConfig thresholdingConfig;

    public OverrideableThresholdingDaoImpl() {
        super();
    }

    @Override
    public synchronized void overrideConfig(ThresholdingConfig config) {
        thresholdingConfig = Objects.requireNonNull(config);
    }

    @Override
    public synchronized void overrideConfig(InputStream config) {
        Objects.requireNonNull(config);

        try (Reader reader = new InputStreamReader(config)) {
            overrideConfig(JaxbUtils.unmarshal(ThresholdingConfig.class, reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveConfig() {
        // no-op
    }

    @Override
    public void onConfigChanged() {
        // no-op
    }

    @Override
    public synchronized ThresholdingConfig getReadOnlyConfig() {
        return thresholdingConfig;
    }

    @Override
    public synchronized void reload() {
        if (thresholdingConfig == null) {
            thresholdingConfig = new ThresholdingConfig();
        }
    }
}
