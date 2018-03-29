package kosta.albatross.controller;

public class HandlerMapping {
	public static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping() {}
	
	public static HandlerMapping getInstance() {
		return instance;
	}
	
	public Controller create(String command) {
		Controller ctrl = null;
		if(command.equals("home")) {
			ctrl = new HomeController();
		}else if(command.equals("PostList")) {
			ctrl = new PostListController();
		}else if(command.equals("search")) {
			ctrl = new SearchController();
		}else if(command.equals("rentBook")) {
			ctrl = new RentBookController();
		}else if(command.equals("returnBook")) {
			ctrl = new returnBookController();
		}else if(command.equals("bookList")) {
			ctrl = new BookListController();
		}
		return ctrl;
	}
}
