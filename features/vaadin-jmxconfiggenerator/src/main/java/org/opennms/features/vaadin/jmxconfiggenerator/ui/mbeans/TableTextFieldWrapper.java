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
package org.opennms.features.vaadin.jmxconfiggenerator.ui.mbeans;

import java.util.Collection;

import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Validator;
import com.vaadin.v7.data.Validator.InvalidValueException;
import com.vaadin.server.ErrorMessage;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;

/**
 * This class wraps a {@link TextField} so it is laid out correctly inside a
 * editable Table. Because by default a {@link TextField} inside an editable
 * table does not show any error indicator on a failed validation. The Vertical-
 * or HorizontalLayout does show an error indicator, so we wrap the layout
 * around the text field.
 * 
 */
public class TableTextFieldWrapper extends HorizontalLayout implements Field<String> {

	private TextField textField;

	public TableTextFieldWrapper(final TextField field) {
		this.textField = field;
		addComponent(field);
	}

	@Override
	public boolean isInvalidCommitted() {
		return this.textField.isInvalidCommitted();
	}

	@Override
	public void setInvalidCommitted(final boolean isCommitted) {
		this.textField.setInvalidCommitted(isCommitted);
	}

	@Override
	public void commit() throws SourceException, InvalidValueException {
		this.textField.commit();
	}

	@Override
	public void discard() throws SourceException {
		this.textField.discard();
	}

	@Override
	public boolean isBuffered() {
		return this.textField.isBuffered();
	}

	@Override
	public void setBuffered(final boolean readThrough) throws SourceException {
		this.textField.setBuffered(readThrough);
	}

	@Override
	public boolean isModified() {
		return this.textField.isModified();
	}

	@Override
	public void addValidator(final Validator validator) {
		this.textField.addValidator(validator);
	}

	@Override
	public void removeValidator(final Validator validator) {
		this.textField.removeValidator(validator);
	}

	@Override
	public void removeAllValidators() {
		this.textField.removeAllValidators();
	}

	@Override
	public Collection<Validator> getValidators() {
		return this.textField.getValidators();
	}

	@Override
	public boolean isValid() {
		return this.textField.isValid();
	}

	@Override
	public void validate() throws InvalidValueException {
		this.textField.validate();
	}

	@Override
	public boolean isInvalidAllowed() {
		return this.textField.isInvalidAllowed();
	}

	@Override
	public void setInvalidAllowed(final boolean invalidValueAllowed) throws UnsupportedOperationException {
		this.textField.setInvalidAllowed(invalidValueAllowed);
	}

	@Override
	public String getValue() {
		return this.textField.getValue();
	}

	@Override
	public void setValue(final String newValue) throws ReadOnlyException {
		this.textField.setValue(newValue);
	}

	@Override
	public Class<String> getType() {
		return this.textField.getType();
	}

	@Override
	public boolean isReadOnly() {
		return textField.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean b) {
		textField.setReadOnly(b);
	}

	@Override
	public void addListener(final ValueChangeListener listener) {
		addValueChangeListener(listener);
	}

	@Override
	public void addValueChangeListener(final ValueChangeListener listener) {
		this.textField.addValueChangeListener(listener);
	}

	@Override
	public void removeListener(final ValueChangeListener listener) {
		removeValueChangeListener(listener);
	}

	@Override
	public void removeValueChangeListener(final ValueChangeListener listener) {
		this.textField.removeValueChangeListener(listener);
	}

	@Override
	public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
		this.textField.valueChange(valueChangeEvent);
	}

	@Override
	public void setPropertyDataSource(final Property newDataSource) {
		this.textField.setPropertyDataSource(newDataSource);
	}

	@Override
	public Property getPropertyDataSource() {
		return this.textField.getPropertyDataSource();
	}

	@Override
	public int getTabIndex() {
		return this.textField.getTabIndex();
	}

	@Override
	public void setTabIndex(final int tabIndex) {
		this.textField.setTabIndex(tabIndex);
	}

	@Override
	public boolean isRequired() {
		return this.textField.isRequired();
	}

	@Override
	public void setRequired(final boolean required) {
		this.textField.setRequired(required);
	}

	@Override
	public void setRequiredError(final String requiredMessage) {
		this.textField.setRequiredError(requiredMessage);
	}

	@Override
	public String getRequiredError() {
		return this.textField.getRequiredError();
	}

	@Override
	public boolean isEmpty() {
		return textField.isEmpty();
	}

	@Override
	public void clear() {
		textField.clear();
	}

	@Override
	public void focus() {
		super.focus();
	}

	@Override
	public void setData(Object data) {
		textField.setData(data);
	}

	@Override
	public Object getData() {
		return textField.getData();
	}

	@Override
	public void setComponentError(ErrorMessage componentError) {
		super.setComponentError(componentError);
		textField.setComponentError(componentError);
	}

	@Override
	public ErrorMessage getComponentError() {
		return textField.getComponentError();
	}
}
