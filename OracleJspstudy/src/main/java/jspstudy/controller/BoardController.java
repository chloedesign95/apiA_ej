package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
//import jspstudy.domain.Criteria; // 검색기능12 : SearchCriteria 로 인해 사용하지않는다
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
  private static final long serialVersionUID = 1L;


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 컨트롤러 세팅 2번
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");

    // 컨트롤러 세팅 1번
    String uri = request.getRequestURI();
    String pj = request.getContextPath();
    String command = uri.substring(pj.length());

  //파일업로드 6 . 파일 저장 경로 및 용량 설정
    String uploadPath = "D:\\openApi(A)\\dev\\Oraclejspstudy\\src\\main\\webapp\\";
    String saveFolder="images";
    String saveFullPath = uploadPath +saveFolder;

    // 글쓰기  2번. 실제경로 작성
    if (command.equals("/board/boardWrite.do")) {
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
      rd.forward(request, response); // forward로 넘긴다
      
    }
    //파일 업로드 3 :  src/main/webapp/WEB-INF/ lib 안에 드라이버 추가 cos.jar   imgscalr-lib-4.2.jar
    //파일 업로드 4 : WriteAction안에 파일업로드 만들기
    else if (command.equals("/board/boardWriteAction.do")){
     
      int sizeLimit =1024*1025* 15; //15mb
      
      //파일업로드 5
      MultipartRequest multi = null;
      multi = new MultipartRequest (request,saveFullPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy() );


      // 글쓰기  3. 값 받기  
      //String subject = request.getParameter("subject");
      //String content = request.getParameter("content");
      //String writer = request.getParameter("writer");
      //System.out.println(subject);
      
      //파일업로드 7. 값 받기
      String subject = multi.getParameter("subject");
      String content = multi.getParameter("content");
      String writer = multi.getParameter("writer");
      System.out.println(subject);
      //파일업로드 8   :  SQL 열어서 파일 컬럼 하나 추가하기 
      
      //파일업로드 9 :  열거자에 저장된 파일을 담는 객체를 생성한다
      Enumeration<?> files = multi.getFileNames();
      //파일업로드 10 : 드라이버에 담긴 파일의 객체의 파일 이름을 얻는다 //드라이버 메소드 사용
      String file = (String)files.nextElement();
      String fileName = multi.getFilesystemName(file); // 저장되는 파일이름
      String originFileName = multi.getOriginalFileName(file); //원래 파일이름

      // 글쓰기  10. ip 꺼내기
      String ip = InetAddress.getLocalHost().getHostAddress();
      
      //ml추가설정 . login 로그인 된 사람 회원 만 작성 가능
      HttpSession session = request.getSession();
      int midx = (int)session.getAttribute("midx");

      // 글쓰기  9번.
      BoardDao bd = new BoardDao(); // 빨간줄 import
      int value = bd.insertBoard(subject, content, writer, ip, midx, fileName); //파일업로드 11 : fileName 추가
      System.out.println(value);

      if (value == 1) {
        // 입력이 되었으면 index로 간다
        response.sendRedirect(request.getContextPath() + "/index.jsp");
      } else {
        // 입력이 되지 않았으면 write.do 가상경로로 간다
        response.sendRedirect(request.getContextPath() + "/board/boardWrite.do");
      }

    } /* L2. */ else if (command.equals("/board/boardList.do")) {
      System.out.println("리스트 들어왔음");
      
    //pg12.
      String page = request.getParameter("page");
      if (page == null) page ="1";
      int pagex = Integer.parseInt(page);
      
      //검색기능 7 : 넘겨받기
      String keyword = request.getParameter("keyword");
      if(keyword == null) keyword="";
      String searchType = request.getParameter("searchType");
      if(searchType == null) searchType="subject";
      
      //pg33.
     // int cnt = bd.boardTotal();   //검색기능이후 페이징 1 : SearchCriteria scri  매개변수 추가
      
      
      //Criteria cri = new Criteria();
      //cri.setPage(pagex);
      
      //검색기능 8
      SearchCriteria scri = new SearchCriteria();
      scri.setPage(pagex);
      scri.setKeyword(keyword);
      scri.setSearchType(searchType);
      
   // L11. 처리
      BoardDao bd = new BoardDao();
      
    //검색기능이후 페이징 1 : scri  매개변수 추가
       int cnt = bd.boardTotal(scri);
      
      //pg34.
     // PageMaker pm = new PageMaker();
     // pm.setCri(cri);
      //pm.setTotalCount(cnt);
      
    //검색기능 11.
         PageMaker pm = new PageMaker();
         pm.setScri(scri);
         pm.setTotalCount(cnt);
      
     
      
      //L11.처리
      //ArrayList<BoardVo> alist = bd.boardSelectAll(cri); //() -> pg13. boardSelectAll에 pagex를 넣는다 
      
    //검색기능 12. : cri를 scri로 변경
         ArrayList<BoardVo> alist = bd.boardSelectAll(scri); //() -> pg13. boardSelectAll에 pagex를 넣는다 
         
      request.setAttribute("alist", alist); // 데이터(자원) 공유
      //pg35.
      request.setAttribute("pm", pm); //이후 boardList 화면에서 pm을 꺼낸다

      // L4. 이동하는 부분
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
      rd.forward(request, response);
    } // C3. 내용보기 들어왔음
    else if (command.equals("/board/boardContent.do")) {
      System.out.println(("내용자세히 보기 들어왔음"));
      // C4. 파라미터가 넘어옴
      String bidx = request.getParameter("bidx");

      // C10. bidx를 int형으로 바꾼다
      int bidx_ = Integer.parseInt(bidx);

      // C9. 처리함
      BoardDao bd = new BoardDao();
      BoardVo bv = bd.boardSelectOne(bidx_);

      // C11.내부적으로 자원공유
      request.setAttribute("bv", bv);

      // C5. 이동함
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
      rd.forward(request, response);

    } // M3. 내용보기 들어왔음
    else if (command.equals("/board/boardModify.do")) {
      System.out.println(("수정페이지 들어왔음"));
      // M4. 파라미터가 넘어옴
      String bidx = request.getParameter("bidx");

      // M10. bidx를 int형으로 바꾼다
      int bidx_ = Integer.parseInt(bidx);

      // M9. 처리함
      BoardDao bd = new BoardDao();
      BoardVo bv = bd.boardSelectOne(bidx_);

      // M11.내부적으로 자원공유
      request.setAttribute("bv", bv);

      // M5. 이동함
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
      rd.forward(request, response);
    }
    // Msave3. 따로이동
    else if (command.equals("/board/boardModifyAction.do")) {
      System.out.println("수정완료페이지 들어왔음");
      // Msave4. 넘어오는거
      String subject = request.getParameter("subject");
      String content = request.getParameter("content");
      String writer = request.getParameter("writer");
      String bidx = request.getParameter("bidx");

      // 10 Msave. bidx를 bidx_로 인트형으로 바꿔준다
      int bidx_ = Integer.parseInt(bidx);

      // Msave9. 객체생성 사용
      BoardDao bd = new BoardDao();
      bd.boardUpdate(subject, content, writer, bidx_);

      // Msave5. 이동
      response.sendRedirect(request.getContextPath() + "/board/boardContent.do?bidx=" + bidx_);
    }

    // DeletePage로넘기기2. 페이지로 이동해서 삭제
    else if (command.equals("/board/boardDelete.do")) {
      System.out.println("삭제페이지로 이동됨");

      // DeletePage로넘기기P3.bidx 선언
      String bidx = request.getParameter("bidx");
      System.out.println("bidx" + bidx);
      request.setAttribute("bidx", bidx);

      // DeletePage로넘기기P4. 이동
      // send는 값을 꺼내지 못하게 이동하지만 forward방식은 값을 꺼낼 수 있다
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
      rd.forward(request, response);
    }

    // Delete2. delete페이지에서 삭제 확인을 누를경우 넘어가
    else if (command.equals("/board/boardDeleteAction.do")) {
      System.out.println(("삭제됨"));
      // Delete3. 파라미터가 넘어옴
      String bidx = request.getParameter("bidx");
      int bidx_ = Integer.parseInt(bidx);

      BoardDao bd = new BoardDao();

      int value = bd.deleteBoard(bidx_);


      // Delete4. 이동
      if (value == 1) {
        // 입력이 되었으면 boardList로 간다 //boardList 가상경로로 갔을때 삭제처리 완료
        response.sendRedirect(request.getContextPath() + "/board/boardList.do");
      } else {
        // 입력이 되지 않았으면 boardDelete.do 가상경로로 간다
        response.sendRedirect(request.getContextPath() + "/board/boardDelete.do?bidx=" + bidx_);
      }

    }
    // Reply2 : 답변하기 버튼 누르면 이동 boardReply.do 가상경로
    else if (command.equals("/board/boardReply.do")) {
      String bidx = request.getParameter("bidx");
      String originbidx = request.getParameter("originbidx");
      String depth = request.getParameter("depth");
      String level_ = request.getParameter("level_");

      // Reply4 : 객체생성 및 bv 넘기기
      BoardVo bv = new BoardVo();
      bv.setBidx(Integer.parseInt(bidx));
      bv.setOriginbidx(Integer.parseInt(originbidx));
      bv.setDepth(Integer.parseInt(depth));
      bv.setLevel_(Integer.parseInt(level_));

      // Reply5 : bv로 담고 꺼내기
      request.setAttribute("bv", bv);

      // Reply3: 이동
      RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");
      rd.forward(request, response);
    }
    // Reply8: 답변 게시 Action 처리 boardReplyAction.do 가상경로
    else if (command.contentEquals("/board/boardReplyAction.do")) {
      
      //Reply9 : 7개의 각기 다른 값 넘겨받기
      String bidx = request.getParameter("bidx");
      String originbidx = request.getParameter("originbidx");
      String depth = request.getParameter("depth");
      String level_ = request.getParameter("level_");
      String subject = request.getParameter("subject");
      String content = request.getParameter("content");
      String writer = request.getParameter("writer");
      String ip = InetAddress.getLocalHost().getHostAddress();
      
    //ml추가설정 . login 로그인 된 사람 회원 만 작성 가능
      HttpSession session = request.getSession();
      int midx = (int)session.getAttribute("midx");
      
      //Reply 19. BoardDao bd의 bv 객체 생성 및 bd에 있는것을 bv로 담는다
      BoardVo bv = new BoardVo();
      bv.setBidx(Integer.parseInt(bidx));
      bv.setOriginbidx(Integer.parseInt(originbidx));
      bv.setDepth(Integer.parseInt(depth));
      bv.setLevel_(Integer.parseInt(level_));
      bv.setSubject(subject);
      bv.setContent(content);
      bv.setWriter(writer);
      bv.setIp(ip);
      bv.setMidx(midx);
      
      //Reply18. 생성자 호출
      BoardDao bd = new BoardDao();
      int value = bd.replyBoard(bv);
      
      //Reply20. 이동
      if(value ==1) {
        response.sendRedirect(request.getContextPath()+"/board/boardList.do");
      }else { //만약 오류발생시
        response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);
      }
  
    } //파일다운로드 1 : 가상경로 만들기
    else if (command.contentEquals("/board/fileDownload.do")) {
      //파일다운로드 2. 파일 이름을 넘겨받는다
       String filename = request.getParameter("filename");
       
     //파일다운로드3  : 파일의 전체 경로
       String filePath = saveFullPath + File.separator +filename;
       
       
     //파일다운로드 5  : 해당위치에 있는 파일을 읽어들인다.
       FileInputStream fileInputStream = new FileInputStream (filePath);
       
       //파일다운로드 6 : 헤더정보에 추출한 파일형식 (mimeType)을 담는다
       Path source = Paths.get(filePath);
       String mimeType = Files.probeContentType(source);
       response.setContentType(mimeType);
       
       //파일다운로드 7 :  헤더정보에 파일이름을 담는다
       String sEncoding = new String(filename.getBytes("UTF-8"));
       response.setHeader("Content-Disposition", "attachment;fileName=" + sEncoding);
       
       //파일다운로드 8  : 파일 쓰기
       ServletOutputStream servletOutStream = response.getOutputStream();
       
       //파일다운로드4  : 
       byte[] b = new byte[4096];
       
       //파일다운로드 9
       int read = 0;
       while((read=fileInputStream.read(b,0,b.length))!=-1) {
         servletOutStream.write(b,0,read);
       }
       //파일다운로드 10 
       servletOutStream.flush();
       servletOutStream.close();
       fileInputStream.close();
    }
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
