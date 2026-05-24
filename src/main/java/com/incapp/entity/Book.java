package com.incapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20)
	private String name;
	private int price;
	@Column(columnDefinition = "longblob", nullable = true)
	private byte[] coverImage;
	@Column(columnDefinition = "longblob",nullable = true)
	private byte[] content;
	
	@ManyToOne
	private User user;
	
}
