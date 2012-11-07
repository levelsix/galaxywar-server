package com.lvl6.galaxywars.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table( name = "users" )
public class User {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true)
	private String id;
	
	
	@Version
	private Integer version;
	
	
	@NotNull
	protected String name = "";
	
	@NotNull
	@Size(min = 10)
	protected String udid = "";
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	@NotNull
	protected Date lastLogin;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUdid() {
		return udid;
	}


	public void setUdid(String udid) {
		this.udid = udid;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
}
