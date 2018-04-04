package kosta.albatross.book.controllers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;

public class BookRegisterController implements Controller {
	final static String ENCODING = "UTF-8";
	final static int SIZE_LIMIT = 1024*1024*10;
	final static String WORKSPACE_UPLOAD_PATH = "C:\\Users\\woolj\\git\\albatross2\\WebContent\\upload";
	final static String RELATIVE_UPLOAD_PATH = "upload";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return REDIRECT_PREFIX + "index.jsp";
		}
		ServletContext ctx = request.getServletContext();
		String mode = ctx.getInitParameter("mode");
		Encoder encoder;
		byte[] encodedBytes;
		String encodedFilename;
		File temporarySavedFile;
		String[] tokens;
		Path tempPath;
		Path movePath;
		BookVO bookToRegister;
		BookVO bookVO;
		
		String title;
		String content;
		String author;
		String publisher;
		String savePath;
		String saveFilename;
		String fileExtension;
		StringBuilder sb = new StringBuilder();
		if(mode.equals("development")) {
			savePath = WORKSPACE_UPLOAD_PATH;
		} else {
			savePath = request.getServletContext().getRealPath("upload");
		}
		encoder = Base64.getEncoder();
		
		
		// 파일을 업로드함
		MultipartRequest multi = new MultipartRequest(request, savePath, SIZE_LIMIT, ENCODING, new DefaultFileRenamePolicy());
		
		// multipart request객체로 부터 파라메터를 추출
		title = multi.getParameter("title");
		author = multi.getParameter("author");
		publisher = multi.getParameter("publisher");
		content = multi.getParameter("content");
		// 랜덤으로 정해진 이름의 파일을(File type) 가져옴
		temporarySavedFile = multi.getFile("imagePath");
		
		// 청크를 만듬
		sb.append(title); sb.append(",");
		sb.append(author); sb.append(",");
		sb.append(publisher);
		
		// Base64로 인코딩
		encodedBytes = sb.toString().getBytes(StandardCharsets.UTF_8);
		encodedFilename = encoder.encodeToString(encodedBytes);
				
		// 파일확장자를 추출
		tokens = temporarySavedFile.getName().split(Pattern.quote("."));
		fileExtension = tokens[tokens.length - 1];
		
		// 파일이름을 수정함
		tempPath = Paths.get(temporarySavedFile.getPath());
		saveFilename = String.format("%s.%s", encodedFilename, fileExtension);
		saveFilename.replace("/", "%");
		movePath = Paths.get(temporarySavedFile.getParent(), saveFilename);
		Files.move(tempPath, movePath);
		
		bookToRegister = new BookVO();
		bookToRegister.setTitle(title);
		bookToRegister.setContent(content);
		bookToRegister.setAuthor(author);
		bookToRegister.setPublisher(publisher);
		bookToRegister.setImagePath(String.format("%s/%s", RELATIVE_UPLOAD_PATH, saveFilename));
		bookVO = BookDAO.getInstance().bookRegister(bookToRegister);
		return REDIRECT_PREFIX + "dispatcher?command=bookDetail&bNo=" + bookVO.getbNo();
	}
}
