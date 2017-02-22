/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package structure;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Less
 */

@Entity
@Table(name="adresses")
public class Action implements Serializable {
	
	private Integer id;
	private String title;
	private Adress adress;
	private Date date;

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
	 * @return the title
	 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	/**
	 * @return the adress
	 */
//	@OneToOne
//	@JoinTable(name="id")
	public Adress getAdress() {
		return adress;
	}

	/**
	 * @return the date
	 */
	@Column(name="")
	public Date getDate() {
		return date;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
}
