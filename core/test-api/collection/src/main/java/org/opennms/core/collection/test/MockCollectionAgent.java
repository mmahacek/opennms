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
package org.opennms.core.collection.test;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.opennms.netmgt.collection.api.CollectionAgent;
import org.opennms.netmgt.model.ResourcePath;

/**
 * The Class MockCollectionAgent.
 * 
 * @author <a href="mailto:agalue@opennms.org">Alejandro Galue</a>
 */
public class MockCollectionAgent implements CollectionAgent {

    /** The node id. */
    private int nodeId;

    /** The node label. */
    private String nodeLabel;

    /** The foreign source. */
    private String foreignSource;

    /** The foreign id. */
    private String foreignId;

    /** The ip address. */
    private InetAddress ipAddress;

    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Instantiates a new mock collection agent.
     *
     * @param nodeId the node id
     * @param nodeLabel the node label
     * @param foreignSource the foreign source
     * @param foreignId the foreign id
     * @param ipAddress the ip address
     */
    public MockCollectionAgent(int nodeId, String nodeLabel, String foreignSource, String foreignId, InetAddress ipAddress) {
        this.nodeId = nodeId;
        this.nodeLabel = nodeLabel;
        this.foreignSource = foreignSource;
        this.foreignId = foreignId;
        this.ipAddress = ipAddress;
    }

    public MockCollectionAgent(int nodeId, String nodeLabel, InetAddress ipAddress) {
        this.nodeId = nodeId;
        this.nodeLabel = nodeLabel;
        this.ipAddress = ipAddress;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.poller.NetworkInterface#getAddress()
     */
    @Override
    public InetAddress getAddress() {
        return ipAddress;
    }

    @Override
    public Set<String> getAttributeNames() {
        return Collections.emptySet();
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.poller.NetworkInterface#getAttribute(java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <V> V getAttribute(String property) {
        return (V) attributes.get(property);
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.poller.NetworkInterface#setAttribute(java.lang.String, java.lang.Object)
     */
    @Override
    public Object setAttribute(String property, Object value) {
        attributes.put(property, value);
        return value;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#isStoreByForeignSource()
     */
    @Override
    public Boolean isStoreByForeignSource() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getHostAddress()
     */
    @Override
    public String getHostAddress() {
        return ipAddress.getHostAddress();
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getNodeId()
     */
    @Override
    public int getNodeId() {
        return nodeId;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getNodeLabel()
     */
    @Override
    public String getNodeLabel() {
        return nodeLabel;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getForeignSource()
     */
    @Override
    public String getForeignSource() {
        return foreignSource;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getForeignId()
     */
    @Override
    public String getForeignId() {
        return foreignId;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getLocationName()
     */
    @Override
    public String getLocationName() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getStorageResourcePath()
     */
    @Override
    public ResourcePath getStorageResourcePath() {
        if (foreignSource != null && foreignId != null) {
            return ResourcePath.get("fs", foreignSource, foreignId);
        } else {
            return new ResourcePath(Integer.toString(nodeId));
        }
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#getSavedSysUpTime()
     */
    @Override
    public long getSavedSysUpTime() {
        return 0;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.collection.api.CollectionAgent#setSavedSysUpTime(long)
     */
    @Override
    public void setSavedSysUpTime(long sysUpTime) {
    }

}

