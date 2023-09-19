package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Component
public class BookService {

//	With Database ****************************************************************************
	
	@Autowired
	private BookRepository bookRepository;

	//get all book
	public List<Book> getAllBooks()
	{
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}
	
	//get single book by id
		public Book getBookById(int id)
		{
			Book book=null;
			try {
				book = this.bookRepository.findById(id);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return book;
		}
		
		//Adding the Book
		public Book addBook(Book b)
		{
			Book book = bookRepository.save(b); // this can be skipped by default it consider it
			return book;
		}
	
		//Deleting the Book
		public void deleteBook(int bid)
		{
			bookRepository.deleteById(bid);			
		}
		
		//Update the book
		public void updateBook(Book book, int id)
		{
			book.setId(id);
			bookRepository.save(book);
		}
	
//	Without Data Base ***********************************************************************
//	private static List<Book> list = new ArrayList<>();
//	
//	static {
//		list.add(new Book(1,"Book1","Author-1"));
//		list.add(new Book(2,"Book2","Author-2"));
//		list.add(new Book(3,"Book3","Author-3"));
//		list.add(new Book(4,"Book4","Author-4"));
//		list.add(new Book(5,"Book5","Author-5"));
//		list.add(new Book(6,"Book6","Author-6"));
//	}
//
//	//get all book
//	public List<Book> getAllBooks()
//	{
//		return list;
//	}
//	
//	//get single book by id
//		public Book getBookById(int id)
//		{
//			Book book=null;
//			try {
//				book=list.stream().filter(e->e.getId()==id).findFirst().get();
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//			return book;
//		}
//		
//		//Adding the Book
//		public Book addBook(Book b)
//		{
//			list.add(b);
//			return b;
//		}
//	
//		//Deleting the Book
//		public void deleteBook(int bid)
//		{
//			list = list.stream().filter(book->{
//			if(book.getId() != bid) {
//				return true;
//			}else {
//				return false;
//			}
//		}).collect(Collectors.toList());
////			or
////		list = list.stream().filter(book->book.getId() != bid).collect(Collectors.toList());
//			
//		}
//		
//		//Update the book
//		public void updateBook(Book book, int id)
//		{
//			list = list.stream().map(b->{
//				if(b.getId()==id)
//				{
//					b.setTitle(book.getTitle());
//					b.setAuthor(book.getAuthor());
//				}
//				return b;
//			}).collect(Collectors.toList());
//		}
		
		
}

