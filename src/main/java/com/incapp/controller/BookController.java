package com.incapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.entity.Book;
import com.incapp.entity.User;
import com.incapp.service.BookService;
import com.incapp.service.UserService;


@RestController
public class BookController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String home() {
		return "Welcome to ResTful Book-WebService Application";
	}
	
	@PostMapping("/Login")
	public User login(@RequestParam String email,@RequestParam String password) {
		User u=userService.checkLogin(email,password);
		return u;
	}
	@PostMapping("/SaveUser")
	public boolean saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	@GetMapping("/BooksByName/{name}")
	public List<Book> booksByName(@PathVariable String name) {
		return bookService.searchBook(name);
	}

	@GetMapping("/GetBook/{id}")
	public Book getBook(@PathVariable int id) {
		return bookService.getBook(id);
	}
	@GetMapping("/GetAllBooks")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping("/GetAllBooksByUser")
	public List<Book> getAllBooks(@RequestBody User user) {
		return bookService.getAllBooks(user);
	}
	
	@PostMapping("/SaveBook")
	public boolean saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	@PostMapping("/SaveBook2")
	public boolean saveBook2(@ModelAttribute Book book,@RequestParam String email, @RequestPart(required = false) MultipartFile photo,@RequestPart(required = false) MultipartFile pdf) {
		return bookService.saveBook2(book,email,photo,pdf);
	}
	@PutMapping("/UpdateBook")
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	@PutMapping("/UpdateBookCoverImage/{id}")
	public boolean updateBookCoverImage(@PathVariable int id,@RequestBody byte ci[]) {
		return bookService.updateBookCoverImage(id,ci);
	}
	@PutMapping("/UpdateBookCoverImage2/{id}")
	public boolean updateBookCoverImage2(@PathVariable int id,@RequestPart MultipartFile photo) {
		byte ci[]=null;
		try {
			ci = photo.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookService.updateBookCoverImage(id,ci);
	}
	@PutMapping("/UpdateBookContent/{id}")
	public boolean updateBookContent(@PathVariable int id,@RequestBody byte c[]) {
		return bookService.updateBookContent(id,c);
	}
	@DeleteMapping("/DeleteBook/{id}")
	public boolean deleteBook(@PathVariable int id) {
		return bookService.deleteBook(id);
	}
}
