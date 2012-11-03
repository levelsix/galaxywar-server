package com.lvl6.galaxywars.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "users" )
public class User {
	
	protected String name = "";
	protected String udid = "";
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	protected Date lastLogin;
	
	
	
}
