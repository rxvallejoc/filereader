/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test.classes;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class Contact {

	private Long pk;

	private String contactType;
	private String contactValue;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", contactType, contactValue);
	}
}
