package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {
	
//	@GetMapping("books")
//	public Book getBooks()
//	{
//		Book book = new Book();
//		book.setId(1);
//		book.setTitle("java");
//		book.setAuthor("seema");		
//		return book;
//	}
	
//	Getting data with fake server
	
	@Autowired
	private BookService bookService;
	
	//getting all book handler 
//	@GetMapping("books")
//	public List<Book> getBooks()
//	{
//		return this.bookService.getAllBooks();
//	}
//	
//		//getting single book handler
//	@GetMapping("/books/{id}")
//	public Book getBook(@PathVariable("id") int id)
//	{
//		return this.bookService.getBookById(id);
//	}
//	
//	//adding new book handler
//	@PostMapping("/books")
//	public Book addBook(@RequestBody Book book)
//	{
//		Book b=this.bookService.addBook(book);
//		return b;
//	}
//	
//	//delete book handler
//	@DeleteMapping("/books/{bookId}")
//	public void deleteBook(@PathVariable("bookId") int bookId)
//	{
//		this.bookService.deleteBook(bookId);
//	}
//	
//	@PutMapping("/books/{id}")
//	public Book updateBook(@RequestBody Book book,@PathVariable("id") int id)
//	{
//		this.bookService.updateBook(book, id);
//		return book;
//	}

	
//	*********************All Methods with Status ***********************************************

	//getting all book handler with status
		@GetMapping("books")
		public ResponseEntity<List<Book>> getBooks()
		{
			List<Book> list = bookService.getAllBooks();
			if(list.size()<=0)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
		
		//getting single book handler
		@GetMapping("/books/{id}")
		public ResponseEntity<Book> getBook(@PathVariable("id") int id)
		{
			Book book = bookService.getBookById(id);
			if(book==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(book));
		}
		
		//adding new book handler
		@PostMapping("/books")
		public ResponseEntity<Book> addBook(@RequestBody Book book)
		{
			Book b= null;
			try {
				b =this.bookService.addBook(book);
				System.out.println(book);
				return ResponseEntity.of(Optional.of(b));
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		//delete book handler
		@DeleteMapping("/books/{bookId}")
		public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId)
		{
			try {
				this.bookService.deleteBook(bookId);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//				return ResponseEntity.ok().build();
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		@PutMapping("/books/{id}")
		public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id)
		{
			try {
				this.bookService.updateBook(book, id);
				return ResponseEntity.ok().body(book);
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
}
