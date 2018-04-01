package kosta.albatross.controller;

public class HandlerMapping {
	public static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		return instance;
	}

	public Controller create(String command) {
		Controller ctrl = null;
		if (command.equals("home")) {
			ctrl = new HomeController();
		} else if (command.equals("postList")) {
			ctrl = new PostListController();
		} else if (command.equals("search")) {
			ctrl = new SearchController();
		} else if (command.equals("login")) {// 로그인 페이지
			ctrl = new LoginController();
		} else if (command.equals("loginCheck")) {// 로그인 성공&실패
			ctrl = new LoginCheckController();
		} else if (command.equals("logout")) {// 로그아웃
			ctrl = new LogoutController();
		} else if (command.equals("rentBook")) {
			ctrl = new RentBookController();
		} else if (command.equals("returnBook")) {
			ctrl = new ReturnBookController();
		} else if (command.equals("bookList")) {
			ctrl = new BookListController();
		} else if (command.equals("showDetailContent")) {
			ctrl = new showDetailContentController();
		} else if (command.equals("bookDetail")) {
			ctrl = new BookDetail_jjsController();
		} else if (command.equals("rentList")) {
			ctrl = new RentListController();
		} else if (command.equals("postUpdate")) {
			ctrl = new postUpdateController();
		}else if (command.equals("postUpdateView")) {
			ctrl = new postUpdateViewController();
		}else if(command.equals("deletePost")) {
			ctrl = new deletePostController();
		} else if (command.equals("register")) {
			ctrl = new RegisterController();
		} else if (command.equals("idcheck")) {
			ctrl = new IdCheckController();
		} else if (command.equals("postWriteView")) {// 게시판글쓰기
			ctrl = new PostWriteViewController();
		} else if (command.equals("registerView")) { // 레지스터 뷰
			ctrl = new RegisterViewController();
		} else if (command.equals("write")) { // 레지스터 뷰
			ctrl = new WriteController();
		}
		return ctrl;
	}
}
