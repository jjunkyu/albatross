package kosta.albatross.common.controllers;

import kosta.albatross.book.controllers.*;
import kosta.albatross.member.controllers.*;
import kosta.albatross.post.controllers.*;
import kosta.albatross.rent.controllers.*;

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
		} else if (command.equals("login")) {// 로그인 페이지
			ctrl = new LoginController();
		} else if (command.equals("loginCheck")) {// 로그인 성공&실패
			ctrl = new LoginCheckController();
		} else if (command.equals("logout")) {// 로그아웃
			ctrl = new LogoutController();
			//도서관
		} else if (command.equals("bookSearchByTitle")) {
			ctrl = new BookSearchByTitleController();
		} else if (command.equals("bookSearchByAuthor")) {
			ctrl = new BookSearchByAuthorController();
		} else if (command.equals("bookSearchByMix")) {
			ctrl = new BookSearchByMixController();
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
		} else if (command.equals("bookRegisterView")) {
			ctrl = new BookRegisterViewController();
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
		} else if (command.equals("register")) {
			ctrl = new RegisterController();
		} else if (command.equals("postWriteView")) {// 게시판글쓰기
			ctrl = new PostWriteViewController();
		} else if (command.equals("postWrite")) {
			ctrl = new PostWriteController();
		}else if (command.equals("memberFindView")) {
			ctrl = new MemberFindViewController();
		}else if (command.equals("memberFind")) {
			ctrl = new MemberFindController();
		} else if (command.equals("myAccount")) {
			ctrl = new MyAccountController();
		} else if (command.equals("deleteBook")) {
			ctrl = new BookDeleteController();
		} else if (command.equals("myPosting")) {
			ctrl = new MyPostingController();
		}else if(command.equals("memberUpdateView")) {//회원정보수정
			ctrl = new MemberUpdateViewController();
		}else if(command.equals("memberUpdate")) {//회원정보수정완료 적용
			ctrl = new MemberUpdateController();
		}
		return ctrl;
	}
}
