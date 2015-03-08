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
@Table(name="adress")
public class Adress implements Serializable {
	private Integer id;
	private String street;
	private Integer building;
	private Integer apartment;

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
	 * @return the street
	 */
	@Column(name="street")
	public String getStreet() {
		return street;
	}

	/**
	 * @return the building
	 */
	@Column(name="building")
	public Integer getBuilding() {
		return building;
	}


	/**
	 * @return the apartment
	 */
	@Column(name="apartment")
	public Integer getApartment() {
		return apartment;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(Integer building) {
		this.building = building;
	}


	/**
	 * @param apartment the apartment to set
	 */
	public void setApartment(Integer apartment) {
		this.apartment = apartment;
	}
	
}
