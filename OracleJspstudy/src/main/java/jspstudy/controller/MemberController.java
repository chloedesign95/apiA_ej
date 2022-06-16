package jspstudy.controller;

// controller : 이동하고 통제하는 용도로 만든것

import java.io.IOException;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;

@WebServlet("/MemberController") // .do를 웹 서버에 등록
public class MemberController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    //한글 인코딩 깨짐현상 방지 
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    // 주소의 풀경로를 추출
    // "<%=request.getContextPath()%>/member/memberJoinAction.do";
    String uri = request.getRequestURI();
    // 프로젝트 이름을 추출
    String pj = request.getContextPath();
    // 프로젝트 이름을 뺀 나머지 가상경로를 추출
    String command = uri.substring(pj.length());
    System.out.println("command:" + command);

    // 가상 처리
    if (command.equals("/member/memberJoinAction.do")) {
      // OK페이지 넘어가는 값 복사해서 붙이기

      // request 객체 생성
      request.setCharacterEncoding("UTF-8"); // request할때 넘어간후 한글이 깨지는 현상은 request인코딩을 시켜주어야한다.
      // input 객체의 이름을 담은 파라미터를 호출하면 결과값으로 그 객체의 값을 리턴한다
      String memberId = request.getParameter("memberId");// request 라는 객체를 쓰게된다 (요청) //request.을 치면
                                                         // 이 객체않에 많은 메소드가 들어가져있는것을 확인할 수 있다
      // String타입으로 memberID에 담는다
      String memberPwd = request.getParameter("memberPwd");
      String memberName = request.getParameter("memberName");
      String memberEmail = request.getParameter("memberEmail");
      String memberGender = request.getParameter("memberGender");
      String memberAddr = request.getParameter("memberAddr");
      String memberPhone = request.getParameter("memberPhone");
      String memberJumin = request.getParameter("memberJumin");

      /*
       * //화면에 출력하는 객체의 메소드 out.println(memberId+"<br>"); out.println(memberPwd+"<br>");
       * out.println(memberName+"<br>"); out.println(memberEmail+"<br>");
       * out.println(memberGender+"<br>"); out.println(memberAddr+"<br>");
       * out.println(memberPhone+"<br>"); out.println(memberJumin+"<br>");
       */

      // Hobby : 여러개의 값을 담은 객체의 이름을 호출하면 배열형태의 값을 리턴한다
      String[] memberHobby = request.getParameterValues("memberHobby"); // 체크박스의 경우 배열을 사용해서 여러가지 값
      String hobby = "";
      if (memberHobby != null) {
        for (int i = 0; i < memberHobby.length; i++) {
          // 목록 사이에 , 넣기
          hobby = hobby + "," + memberHobby[i];
          // out.println(memberHobby[i]+"<br>"); //담긴값을 출력
        }
        // 문자 자르기로 맨 목록중 맨 앞 내용의 앞 쉼표 , 지우기
        hobby = hobby.substring(1);
      } else {
        // out.println("선택안함");
      }

      // SQL로 ip 주소 가져오기
      String ip = InetAddress.getLocalHost().getHostAddress();

      MemberDao md = new MemberDao();

      int value = md.insertMember(memberId, memberPwd, memberName, memberEmail, memberGender,
          memberAddr, memberPhone, memberJumin, hobby, ip);

      //PrintWriter out = response.getWriter();

      // 여기까지 썻으면 디버깅
      // int value = stmt.executeUpdate(sql);

      // 폼 작성 확인 후 홈으로 넘어가짐
      if (value == 1) {
        /*
         * out.println("<script>alert('회원가입성공'); location.href='" + request.getContextPath() +
         * "/index.jsp'</script>");
         */
        response.sendRedirect(request.getContextPath() + "/member/memberList.do");
      } else {
        response.sendRedirect(request.getContextPath() + "/member/memberJoin.do");
        //out.println("<script>alert('회원가입실패'); location.href='./memberJoin.jsp'</script>");
      }

    } // 여기까지 memberJoinAction

    else if (command.equals("/member/memberJoin.do")) { // 조인
      // 회원가입 페이지로 들어오면 처리를 함
      RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
      rd.forward(request, response);

    } else if (command.equals("/member/memberList.do")) { // 리스트
      MemberDao md = new MemberDao();
      ArrayList<MemberVo> alist = md.memberSelectAll();

      request.setAttribute("alist", alist);

      RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
      rd.forward(request, response);
    }
    
    /*ml memberLogin을 위한 컨트롤 만들기*/
    
    //ml3. Index에서 로그인 버튼 누르면 memberLogin.do 가상경로로 이동
    else if (command.equals("/member/memberLogin.do")) { // 멤버로그인 가상경로
      RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
      rd.forward(request, response);
      

    } //ml4. memberLogin.do에서 로그인을 하면 memberLoginAction.do 가상경로를 통한다
    else if (command.equals("/member/memberLoginAction.do")) {
      //ml5. 넘어온 ID와 PwD값을 member??이라는 변수로 받는다
      String memberId = request.getParameter("memberId");
      String memberPwd = request.getParameter("memberPwd");;
      
      //ml14. MemberDao에 만든 메소드로 처리
      MemberDao md = new MemberDao();
      MemberVo mv = md.memberLogin(memberId,  memberPwd); 
      
      //ml15. HttpSession이라는 세션정보를 session에 넣는다
      HttpSession session = request.getSession(); 
      
    //ml16. mv로 나온값이 존재하면 session으로 옮겨담는다
      if(mv !=null) {
        session.setAttribute("midx", mv.getMidx());
        session.setAttribute("memberId", mv.getMemberid());
        session.setAttribute("memberName", mv.getMembername());
        
        //login후 경로2. 
        if (session.getAttribute("saveUrl") !=null) {
          //login 후 경로 4.  : saveUrl값으로 이동
          response.sendRedirect((String)session.getAttribute("saveUrl"));
        }else {
          response.sendRedirect(request.getContextPath()+"/member/memberList.do");
        }
        
        //ml17. response.sendRedirect 를 이용해 memberList.do로 이동
        //login 후 경로 3. response.sendRedirect(request.getContextPath()+"/member/memberList.do");
      }
      //ml18. mv 가 null 이면 다시  로그인페이지로 이동
      else {
        response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
      }
      
    }//lo2. 로그아웃
    else if (command.equals("/member/memberLogout.do")){
      HttpSession session = request.getSession();
      session.invalidate();
      
      response.sendRedirect(request.getContextPath()+"/"); //메인으로 이동
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
