# Project Albatross


## Members
Participated members

   * GWJ
   * PJS
   * CSK
   * LJK
   * KHJ
   * JJS

## naming conventions

   * Do not capitalize filename except for class and interface files
   * jsp file name always starts with lowercase.

## Commit Rules

   * Commit message + committer 
   * Unit by Unit   

## Coding Rules
   
### General Rules
   
   * Add whitespace before and after an operator
   * Do not use global style coding use namespace
   
#### Java / JSP

   * When writing jsp/EL with HTML, HTML indentation is used over EL jsp/EL indentation.
   * When constructing SQL string on DAO, use StringBuilder instead of String concatenation.

### CSS

   * Do not use camelCase on css class name. Instead use style-like-this.
   * Do not use global style.
   * Always comment the style.

### Javascript
   
   * Use single quote instead of double quote.
   * When mixed with jsp/EL with javacript, use secure placeholder for javascript.
   
### SQL

   * Use CAPITAL LETTERS for SQL keywords. 


   
## Project Directory
```
├─src
│  └─com
│      └─kosta
│          └─albatross
│              │  Controller.java
│              │  DataSourceManager.java
│              │  DispatcherServlet.java
│              │  HandlerMapping.java
│              │  HomeController.java
│              │  ListVO.java
│              │  PagingBean.java
│              │  
│              ├─book
│              │  ├─controllers
│              │  │      BookDeleteController.java
│              │  │      BookDetailController.java
│              │  │      BookListController.java
│              │  │      BookRegisterController.java
│              │  │      BookRegisterViewController.java
│              │  │      BookSearchByAuthorController.java
│              │  │      BookSearchByMixController.java
│              │  │      BookSearchByTitleController.java
│              │  │      BookUpdateController.java
│              │  │      BookUpdateViewController.java
│              │  │      
│              │  └─models
│              │          BookDAO.java
│              │          BookVO.java
│              │          
│              ├─common
│              │      PrivacyPolicyController.java
│              │      TermsOfUseController.java
│              │      
│              ├─exception
│              │      NotDeleteBookException.java
│              │      
│              ├─member
│              │  ├─controllers
│              │  │      CheckEmailServlet.java
│              │  │      CheckIdServlet.java
│              │  │      LoginCheckController.java
│              │  │      LoginController.java
│              │  │      LoginServlet.java
│              │  │      LogoutController.java
│              │  │      MemberFindController.java
│              │  │      MemberFindViewController.java
│              │  │      MemberId_pwCheckServlet.java
│              │  │      MemberUpdateController.java
│              │  │      MemberUpdateViewController.java
│              │  │      MyAccountController.java
│              │  │      MyPostingController.java
│              │  │      RegisterController.java
│              │  │      RegisterViewController.java
│              │  │      
│              │  └─models
│              │          MemberDAO.java
│              │          MemberVO.java
│              │          
│              ├─post
│              │  ├─controllers
│              │  │      PostDeleteController.java
│              │  │      PostDetailController.java
│              │  │      PostListController.java
│              │  │      PostUpdateController.java
│              │  │      PostUpdateViewController.java
│              │  │      PostWriteController.java
│              │  │      PostWriteViewController.java
│              │  │      
│              │  └─models
│              │          PostDAO.java
│              │          PostVO.java
│              │          
│              ├─rent
│              │  ├─controllers
│              │  │      RentBookController.java
│              │  │      RentListController.java
│              │  │      ReturnBookController.java
│              │  │      
│              │  └─models
│              │          RentDAO.java
│              │          RentVO.java
│              │          
│              └─test
│                  └─book
└─WebContent
    │  index.jsp
    │  
    ├─jsp
    │  ├─book
    │  │      bookDelete_fail.jsp
    │  │      bookDetail.jsp
    │  │      bookList.jsp
    │  │      bookRegister.jsp
    │  │      bookSearch_fail.jsp
    │  │      bookSearch_ok.jsp
    │  │      bookUpdate.jsp
    │  │      
    │  ├─member
    │  │      login.jsp
    │  │      memberFindView.jsp
    │  │      memberFind_ok.jsp
    │  │      memberUpdate.jsp
    │  │      myAccount.jsp
    │  │      myPosting.jsp
    │  │      register.jsp
    │  │      
    │  ├─post
    │  │      postDetail.jsp
    │  │      postList.jsp
    │  │      postUpdate.jsp
    │  │      postWrite.jsp
    │  │      
    │  ├─rent
    │  │      rentList.jsp
    │  │      rent_ok.jsp
    │  │      return_ok.jsp
    │  │      
    │  ├─templates
    │  │      footer.jsp
    │  │      header.jsp
    │  │      home.jsp
    │  │      main.jsp
    │  │      
    │  └─topic
    │          privacyPolicy.jsp
    │          termsOfUse.jsp
    │          
    ├─META-INF
    │      MANIFEST.MF
    │      
    ├─static
    │  ├─css
    │  │  │  ajax-loader.gif
    │  │  │  bootstrap.min.css
    │  │  │  bootstrap.min.css.map
    │  │  │  slick-theme.css
    │  │  │  slick.css
    │  │  │  styles.css
    │  │  │  
    │  │  └─fonts
    │  │          slick.eot
    │  │          slick.svg
    │  │          slick.ttf
    │  │          slick.woff
    │  │          
    │  ├─font
    │  │      NanumBarunpenB.ttf
    │  │      NanumBarunpenR.ttf
    │  │      
    │  ├─js
    │  │      bootstrap.bundle.js
    │  │      bootstrap.bundle.js.map
    │  │      jquery.js
    │  │      slick.js
    │  │      
    │  └─media
    │          jumbotron.jpg
    │          
    ├─upload
    │      
    └─WEB-INF
        │  web.xml
        │  
        └─lib
                cos.jar
                json.jar
                jstl-1.2.jar
                semi.sql
                      
```

## URL Mapping

`home` : ...
`booklist` : ...
