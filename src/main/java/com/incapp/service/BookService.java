package com.incapp.service;

import java.io.IOException;
import java.util.List;
import com.incapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.entity.Book;
import com.incapp.entity.User;
import com.incapp.repo.BookRepo;


@Service
public class BookService {

    private final UserRepo userRepo;

   @Autowired
	private BookRepo bookRepo;

    BookService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

	public boolean saveBook(Book book) {
		bookRepo.save(book);
		return true;
	}

	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id).orElse(null);
	}

	public List<Book> getAllBooks(User u) {
		return bookRepo.findAllByUser(u);
		
//		return bookRepo.getMyBooks(u);
//		return bookRepo.getMyBooks(u.getEmail());
	}

	public boolean deleteBook(int id) {
		if(bookRepo.findById(id).orElse(null)==null) {
			return false;
		}
		bookRepo.deleteById(id);
		return true;
	}

	public List<Book> searchBook(String name) {
//		return bookRepo.findAllByName(name);
		return bookRepo.findAllByNameContains(name);
//		return bookRepo.findAllByNameLike("%"+name+"%");
	}

	public Book updateBook(Book book) {
		Book b=bookRepo.findById(book.getId()).orElse(null);
		if(b==null) {
			return null;
		}
		b.setName(book.getName());
		b.setPrice(book.getPrice());
		b.setUser(userRepo.findById(book.getUser().getEmail()).orElse(null));
		bookRepo.save(b);
		return b;
	}

	public boolean updateBookCoverImage(int id, byte[] ci) {
		Book b=bookRepo.findById(id).orElse(null);
		if(b==null) {
			return false;
		}
		b.setCoverImage(ci);
		bookRepo.save(b);
		return true;
	}
	public boolean updateBookContent(int id, byte[] c) {
		Book b=bookRepo.findById(id).orElse(null);
		if(b==null) {
			return false;
		}
		b.setContent(c);
		bookRepo.save(b);
		return true;
	}

	public boolean saveBook2(Book book,String email,MultipartFile photo,MultipartFile pdf) {
		byte[] ct=null,ci=null;
		try {
			ct=pdf.getBytes();
			ci=photo.getBytes();
			if(ct.length==0) {
				ct=null;
			}
			if(ci.length==0) {
				ci=null;
			}
			book.setUser(userRepo.findById(email).orElse(null));
			book.setCoverImage(ci);
			book.setContent(ct);
			bookRepo.save(book);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
