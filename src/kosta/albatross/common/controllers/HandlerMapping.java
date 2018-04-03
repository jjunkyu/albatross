package kosta.albatross.common.controllers;

import kosta.albatross.book.controllers.BookDeleteController;
import kosta.albatross.book.controllers.BookDetailController;
import kosta.albatross.book.controllers.BookListController;
import kosta.albatross.book.controllers.BookRegisterController;
import kosta.albatross.book.controllers.BookSearchController;
import kosta.albatross.member.controllers.IdCheckController;
import kosta.albatross.member.controllers.LoginCheckController;
import kosta.albatross.member.controllers.LoginController;
import kosta.albatross.member.controllers.LogoutController;
import kosta.albatross.member.controllers.MyAccountController;
import kosta.albatross.member.controllers.RegisterController;
import kosta.albatross.member.controllers.RegisterViewController;
import kosta.albatross.post.controllers.PostListController;
import kosta.albatross.post.controllers.PostWriteViewController;
import kosta.albatross.post.controllers.PostWriteController;
import kosta.albatross.post.controllers.PostDeleteController;
import kosta.albatross.post.controllers.PostUpdateController;
import kosta.albatross.post.controllers.PostUpdateViewController;
import kosta.albatross.post.controllers.PostDetailController;
import kosta.albatross.rent.controllers.RentBookController;
import kosta.albatross.rent.controllers.RentListController;
import kosta.albatross.rent.controllers.ReturnBookController;

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
			//회원
		} else if (command.equals("privacyPolicy")) {
			ctrl = new PrivacyPolicyController();
		} else if (command.equals("termsOfUse")) {
			ctrl = new TermsOfUseController();
			// footer.jsp의 이용약관, 개인정보처리방침
		} else if (command.equals("register")) {
			ctrl = new RegisterController();
		} else if (command.equals("registerView")) { // 레지스터 뷰
			ctrl = new RegisterViewController();
		} else if (command.equals("idcheck")) {
			ctrl = new IdCheckController();
		} else if (command.equals("login")) {// 로그인 페이지
			ctrl = new LoginController();
		} else if (command.equals("loginCheck")) {// 로그인 성공&실패
			ctrl = new LoginCheckController();
		} else if (command.equals("logout")) {// 로그아웃
			ctrl = new LogoutController();
			//도서관
		} else if (command.equals("BookSearch")) {
			ctrl = new BookSearchController();
		} else if (command.equals("rentBook")) {
			ctrl = new RentBookController();
		} else if (command.equals("returnBook")) {
			ctrl = new ReturnBookController();
		} else if (command.equals("bookList")) {
			ctrl = new BookListController();
		} else if (command.equals("bookDetail")) {
			ctrl = new BookDetailController();
		} else if (command.equals("bookRegister")) {
			ctrl = new BookRegisterController();
		} else if (command.equals("rentList")) {
			ctrl = new RentListController();
			//게시판
		} else if (command.equals("postList")) {
			ctrl = new PostListController();
		} else if (command.equals("postDetail")) {
			ctrl = new PostDetailController();
		} else if (command.equals("postUpdate")) {
			ctrl = new PostUpdateController();
		}else if (command.equals("postUpdateView")) {
			ctrl = new PostUpdateViewController();
		}else if(command.equals("deletePost")) {
			ctrl = new PostDeleteController();
		} else if (command.equals("postWriteView")) {// 게시판글쓰기
			ctrl = new PostWriteViewController();
		} else if (command.equals("postWrite")) {
			ctrl = new PostWriteController();
		} else if (command.equals("myAccount")) {
			ctrl = new MyAccountController();
		} else if (command.equals("deleteBook")) {
			ctrl = new BookDeleteController();
		} else if (command.equals("myPosting")) {
			ctrl = new MyPostingController();
		}
		return ctrl;
	}
}
