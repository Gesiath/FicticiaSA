package com.FicticiaSA.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Client {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String email;
	private String password;
	private String fullName;
	private Long dni;
	private String address;
	private String birthday;
	private Long phone;
	private Boolean driver;
	private Boolean glasses;
	private Boolean diabetic;
	private String typeDiabetes;
	private Boolean diseases;
	private String whatDiseases;
	private Boolean active;
	private String rol;
	
	public Client() {
		
	}

	public Client(String id, String email, String password, String fullName, Long dni, String address, String birthday,
			Long phone, Boolean driver, Boolean glasses, Boolean diabetic, String typeDiabetes, Boolean diseases,
			String whatDiseases, Boolean active, String rol) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.dni = dni;
		this.address = address;
		this.birthday = birthday;
		this.phone = phone;
		this.driver = driver;
		this.glasses = glasses;
		this.diabetic = diabetic;
		this.typeDiabetes = typeDiabetes;
		this.diseases = diseases;
		this.whatDiseases = whatDiseases;
		this.active = active;
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Boolean getDriver() {
		return driver;
	}

	public void setDriver(Boolean driver) {
		this.driver = driver;
	}

	public Boolean getGlasses() {
		return glasses;
	}

	public void setGlasses(Boolean glasses) {
		this.glasses = glasses;
	}

	public Boolean getDiabetic() {
		return diabetic;
	}

	public void setDiabetic(Boolean diabetic) {
		this.diabetic = diabetic;
	}

	public String getTypeDiabetes() {
		return typeDiabetes;
	}

	public void setTypeDiabetes(String typeDiabetes) {
		this.typeDiabetes = typeDiabetes;
	}

	public Boolean getDiseases() {
		return diseases;
	}

	public void setDiseases(Boolean diseases) {
		this.diseases = diseases;
	}

	public String getWhatDiseases() {
		return whatDiseases;
	}

	public void setWhatDiseases(String whatDiseases) {
		this.whatDiseases = whatDiseases;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
