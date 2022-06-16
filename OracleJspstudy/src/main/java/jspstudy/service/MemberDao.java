package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jspstudy.dbconn.Dbconn;
import jspstudy.domain.MemberVo;

public class MemberDao { // 메소드들로만 구성되어있는 클래스
  
  //연결객체 : 멤버변수에 대입해 사용
  private Connection conn; //conn은 전역변수라서 멤버변수 안에서 사용할 수 있다
  //구문객체
  // State구문객체에서 진화된 구문객체 선언 및 초기화: SQL injection을 막기위한 진화
  private PreparedStatement pstmt;
  
  //memberDao 생성자 생성
  public MemberDao() {
    //dbconn 객체생성
    Dbconn db = new Dbconn();
    this. conn = db.getConnection();
  }

   //쿼리 불러오기
  public int insertMember( String memberId, String memberPwd, String memberName,
      String memberEmail, String memberGender, String memberAddr, String memberPhone,
      String memberJumin, String hobby, String ip) {
    int value = 0;

    // stmt에 들어가져있는 excuteUpdate라는 메소드 = string 타입으로sql문장을 적어주면 문장을 실행해준다
    // insert구문을 쓴다 //VALUES에 변수값을 적어준다 // 쿼리 구문 완성하기
    String sql =
        "insert into a_member(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBEREMAIL,MEMBERGENDER,MEMBERADDR,MEMBERPHONE,MEMBERJUMIN,MEMBERHOBBY,MEMBERIP)"
            // memberID와같이 values에 변수값을 집어넣는다 // 변수 대입
            /*
             * 해킹방지 +"values(midx_seq.nextval,'"+memberId+"','"+memberPwd+"','"+memberName+"','"+
             * memberEmail+"','"+memberGender+"','"+memberAddr+"','"+memberPhone+"','"+memberJumin+
             * "','"+hobby+"','"+ip+"')";
             */

            // 해킹방지
            + "values(midx_seq.nextval,?,?,?,?,?,?,?,?,?,?)";


    // 쿼리를 실행하기 위한 쿼리실행 클래스를 생성한다
    // 예외처리
    try {
      // 해킹방지 Statement stmt = conn.createStatement();
      // 각각의 ?에 값을 집어넣는다
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, memberId);
      pstmt.setString(2, memberPwd);
      pstmt.setString(3, memberName);
      pstmt.setString(4, memberEmail);
      pstmt.setString(5, memberGender);
      pstmt.setString(6, memberAddr);
      pstmt.setString(7, memberPhone);
      pstmt.setString(8, memberJumin);
      pstmt.setString(9, hobby);
      pstmt.setString(10, ip);

      value = pstmt.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        pstmt.close();
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return value;
  }

  // 회원목록 메소드
  public ArrayList<MemberVo> memberSelectAll() {
    // 객체생성
    ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
    // 초기화

    ResultSet rs = null;

    try {
      // 쿼리문
      String sql = "select * from a_member where delyn ='N' order by midx desc";
      // Connection 객체를 통해서 문자열을 쿼리화 시킨다.
      // 쿼리가 구문화가 된다
      pstmt = conn.prepareStatement(sql);
      // 여러 데이터를 그대로 복사해서 담는 클래스 ResultSet
      rs = pstmt.executeQuery();

      // 반복문 사용해서 값을 꺼낸다
      // rs.next() 메소드 : 다음행에 값이 있는지 보고 있으면 true 없으면 false 반환.. 있으면 그행으로 이동
      while (rs.next()) {
        // 객체생성
        MemberVo mv = new MemberVo();
        // 옮겨담고
        mv.setMidx(rs.getInt("midx"));
        mv.setMembername(rs.getString("memberName"));
        mv.setMemberemail(rs.getString("memberEmail"));
        mv.setMemberphone(rs.getString("memberPhone"));
        mv.setWriteday(rs.getString("writeday"));
        // 담은 mv를 alist에 추가한다
        alist.add(mv);

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return alist;
  }
  
  /*ml memberLogin을 위한 메소드 만들기*/
  
  //ml4. Id와 pwd가 존재하는지 확인하는 메소드 만들기 (존재하면 1, 존재하지않으면 0)
  //ml9. 매개변수로 만들어 ? 에 담는다
  public MemberVo memberLogin(String memberId, String memberPwd) {
    //ml6. return값으로 value= 0; 을 받는다 
   // int value =0;    // mv로 변경됩
    
    //ml10. rs 초기화
    ResultSet rs = null;
    
    //ml12
    MemberVo mv = null;
    
    //ml7. 쿼리 실행시킬 준비
    String sql = "select * from a_member where delyn='N' and memberid=? and memberpwd=?";
    
    //ml8. pstmt = conn.prepareStatement(sql); <--try catch
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, memberId);   //?에 담기
      pstmt.setString(2, memberPwd);   //?에 담기
      rs = pstmt.executeQuery(); //pstmt를 rs에 담기 (bv에 담기 위함)
      
      //ml11.  꺼내서 mv에 넘어서 mv로 옮겨담아 return
      if (rs.next()) { //rs 가 true면 진행
        mv = new MemberVo();
        mv.setMidx(rs.getInt("midx"));
        mv.setMemberid(rs.getString("memberId"));
        mv.setMembername(rs.getString("memberName"));
      }
      
    } catch (SQLException e) {
      
      e.printStackTrace();
    }//ml13.
    finally {
      try {
        rs.close(); //trycatch
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
    //m5. return값으로 mv 을 받는다 
    return mv; 
  }
  
}
