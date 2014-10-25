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
public class Address {

	private Long pk;

	private String mainStreet;
	private String secondaryStreet;
	private Integer number;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getMainStreet() {
		return mainStreet;
	}

	public void setMainStreet(String mainStreet) {
		this.mainStreet = mainStreet;
	}

	public String getSecondaryStreet() {
		return secondaryStreet;
	}

	public void setSecondaryStreet(String secondaryStreet) {
		this.secondaryStreet = secondaryStreet;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return String.format("[%s Numero(%s) y %s]", mainStreet, number, secondaryStreet);
	}
}
