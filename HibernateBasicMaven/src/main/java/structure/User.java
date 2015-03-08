/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package structure;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Less
 */

@Entity
@Table(name="users")
public class User implements Serializable {
	
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private Double rating = .0;
	private Adress adress;
	
	public User(){}
	
	public User(String name, String gender, Integer age){
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public User(String name, String gender, Integer age, Adress addr){
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.adress = addr;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}

	/**
	 * @return the gender
	 */
	@Column(name="gender")
	public String getGender() {
		return gender;
	}

	/**
	 * @return the age
	 */
	@Column(name="age")
	public Integer getAge() {
		return age;
	}

	/**
	 * @return the rating
	 */
	@Column(name="rating")
	public Double getRating() {
		return rating;
	}

	/**
	 * @return the adress
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="adress_id")
	public Adress getAdress() {
		return adress;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	public class Gender {
		public static final String MALE = "male";
		public static final String FEMALE = "female";
	}
	
	
}
