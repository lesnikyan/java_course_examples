/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.orm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="phones")
public class Phone  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String number;
	
	private User user;
	
	public Phone(){}
	
	public Phone(String num){
		number = num;
	}

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

    @Column(name="number", nullable=false, length=16)
	public String getNumber() {
		return number;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
