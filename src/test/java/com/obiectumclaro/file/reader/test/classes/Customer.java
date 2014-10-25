/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test.classes;

import java.util.Date;
import java.util.List;

import com.obiectumclaro.file.reader.descriptor.annotations.FCD;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDGroup;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDList;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDs;
import com.obiectumclaro.file.reader.descriptor.annotations.FD;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
@FD(columnSeparator = ",", name = "Cliente", validator = CustomerValidator.class)
public class Customer {

	private Long pk;

	@FCD(position = 0)
	private String id;

	@FCD(position = 1)
	private String name;

	@FCD(position = 2)
	private String telephone;

	@FCD(position = 3)
	private Integer age;

	@FCD(position = 4)
	private Double amount;

	@FCD(position = 5, name = "Fecha Nacimiento", datePattern = "dd/MM/yyyy")
	private Date birthDate;

	@FCDs({ @FCD(name = "mainStreet", position = 6), @FCD(name = "secondaryStreet", position = 7), @FCD(name = "number", position = 8) })
	private Address address;

	@FCDList(elementsClass = Contact.class, group = {
			@FCDGroup(name = "contactType", positions = { 9, 11, 13, -1 }, defaultValues = { "", "", "", "OTRO" }),
			@FCDGroup(name = "contactValue", positions = { 10, 12, 14, 15 }) })
	private List<Contact> contacts;

	@FCDList(elementsClass = Contact.class, group = {
			@FCDGroup(name = "contactType", positions = { -1, 11, -1, -1 }, defaultValues = { "AAA", "BBB", "CCC", "OTRO" }),
			@FCDGroup(name = "contactValue", positions = { 10, 12, 14, 15 }) })
	private List<Contact> contacts2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("Cliente (pk:%s, id:%s, name:%s, telephone:%s, ammount:%s, birthDate:%s, address:%s)", pk, id, name, telephone, amount,
				birthDate, address);
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Contact> getContacts2() {
		return contacts2;
	}

	public void setContacts2(List<Contact> contacts2) {
		this.contacts2 = contacts2;
	}

}
