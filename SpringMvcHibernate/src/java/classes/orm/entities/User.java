/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.orm.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="users")
public class User  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String login;
	private String password;
	private String email;

    public User() {
    }

	
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
   
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="login", nullable=false, length=64)
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    
    @Column(name="password", nullable=false, length=32)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email", nullable=false, length=64)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }


}
