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
package org.opennms.protocols.xml.config;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.opennms.netmgt.rrd.RrdRepository;

/**
 * The Class XmlDataCollectionConfig.
 * 
 * @author <a href="mailto:agalue@opennms.org">Alejandro Galue</a>
 */
@XmlRootElement(name="xml-datacollection-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataCollectionConfig implements Serializable, Comparable<XmlDataCollectionConfig>, Cloneable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7884808717236892997L;

    /** The Constant XML_DATACOLLECTION_CONFIG_FILE. */
    @XmlTransient
    public static final String XML_DATACOLLECTION_CONFIG_FILE = "xml-datacollection-config.xml";

    /** The Constant OF_DATA_COLLECTIONS. */
    private static final XmlDataCollection[] OF_DATA_COLLECTIONS = new XmlDataCollection[0];

    /** The XML data collections list. */
    @XmlElement(name="xml-collection")
    private List<XmlDataCollection> m_xmlDataCollections = new ArrayList<>();

    /** The RRD Repository. */
    @XmlAttribute(name="rrdRepository")
    private String m_rrdRepository;

    /**
     * Instantiates a new XML data collection configuration.
     */
    public XmlDataCollectionConfig() {

    }

    public XmlDataCollectionConfig(XmlDataCollectionConfig copy) {
        m_rrdRepository = copy.m_rrdRepository;
        copy.m_xmlDataCollections.stream().forEach(x -> m_xmlDataCollections.add(x));
    }

    /**
     * Gets the XML data collections.
     *
     * @return the XML data collections
     */
    public List<XmlDataCollection> getXmlDataCollections() {
        return m_xmlDataCollections;
    }

    /**
     * Sets the XML data collections.
     *
     * @param xmlDataCollections the new XML data collections
     */
    public void setXmlDataCollections(List<XmlDataCollection> xmlDataCollections) {
        m_xmlDataCollections = xmlDataCollections;
    }

    /**
     * Gets the RRD repository.
     *
     * @return the RRD repository
     */
    public String getRrdRepository() {
        return m_rrdRepository;
    }

    /**
     * Sets the RRD repository.
     *
     * @param rrdRepository the new RRD repository
     */
    public void setRrdRepository(String rrdRepository) {
        m_rrdRepository = rrdRepository;
    }

    /**
     * Adds the data collection.
     *
     * @param dataCollection the data collection
     */
    public void addDataCollection(XmlDataCollection dataCollection) {
        m_xmlDataCollections.add(dataCollection);
    }

    /**
     * Removes the data collection.
     *
     * @param dataCollection the data collection
     */
    public void removeDataCollection(XmlDataCollection dataCollection) {
        m_xmlDataCollections.remove(dataCollection);
    }

    /**
     * Removes the data collection by name.
     *
     * @param name the name
     */
    public void removeDataCollectionByName(String name) {
        for (Iterator<XmlDataCollection> itr = m_xmlDataCollections.iterator(); itr.hasNext(); ) {
            XmlDataCollection dataCollection = itr.next();
            if(dataCollection.getName().equals(name)) {
                m_xmlDataCollections.remove(dataCollection);
                return;
            }
        }
    }

    /**
     * Gets the data collection by name.
     *
     * @param name the name
     * @return the data collection by name
     */
    public XmlDataCollection getDataCollectionByName(String name) {
        for (XmlDataCollection dataCol :  m_xmlDataCollections) {
            if(dataCol.getName().equals(name)) {
                return dataCol;
            }
        }
        return null;
    }

    public static RrdRepository buildRrdRepository(String rrdRepositoryPath, XmlDataCollection collection) {
        XmlRrd rrd = collection.getXmlRrd();
        RrdRepository repo = new RrdRepository();
        repo.setRrdBaseDir(new File(rrdRepositoryPath));
        repo.setRraList(rrd.getXmlRras());
        repo.setStep(rrd.getStep());
        repo.setHeartBeat((2 * rrd.getStep()));
        return repo;
    }

    /**
     * Builds the RRD repository.
     *
     * @param collectionName the collection name
     * @return the RRD repository
     */
    public RrdRepository buildRrdRepository(String collectionName) {
        XmlDataCollection collection = getDataCollectionByName(collectionName);
        if (collection == null)
            return null;
        return buildRrdRepository(getRrdRepository(), collection);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(XmlDataCollectionConfig obj) {
        return new CompareToBuilder()
        .append(getRrdRepository(), obj.getRrdRepository())
        .append(getXmlDataCollections().toArray(OF_DATA_COLLECTIONS), obj.getXmlDataCollections().toArray(OF_DATA_COLLECTIONS))
        .toComparison();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XmlDataCollectionConfig) {
            XmlDataCollectionConfig other = (XmlDataCollectionConfig) obj;
            return new EqualsBuilder()
            .append(getRrdRepository(), other.getRrdRepository())
            .append(getXmlDataCollections().toArray(OF_DATA_COLLECTIONS), other.getXmlDataCollections().toArray(OF_DATA_COLLECTIONS))
            .isEquals();
        }
        return false;
    }
 
    @Override
    public XmlDataCollectionConfig clone() {
        return new XmlDataCollectionConfig(this);
    }
}
