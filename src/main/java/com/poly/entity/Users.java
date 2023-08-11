package com.poly.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("userid")
    private int userid;
	
    private String fullname;
    private String passwords;
    private String username;
    private boolean active;
    private String phone;
    private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	
}
