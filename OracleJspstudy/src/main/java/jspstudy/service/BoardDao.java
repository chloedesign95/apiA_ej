package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.BoardVo;
//import jspstudy.domain.Criteria;    // SearchCriteria 추가로 인해 사용하지 않음
import jspstudy.domain.SearchCriteria;

public class BoardDao {

  // 글쓰기  6번
  Connection conn;
  // 글쓰기  5번
  PreparedStatement pstmt;

  // 글쓰기  4번
  public BoardDao() {
    Dbconn db = new Dbconn();
    this.conn = db.getConnection();
  }

  // 글쓰기  7번 쿼리 만들기 (sql gate에서 확인)
  public int insertBoard(String subject, String content, String writer, String ip, int midx, String filename) {//파일업로드 11 : fileName 추가
    int value = 0;
    String sql =
        "INSERT INTO a_board (bidx, originbidx, depth, level_, subject, content, writer, ip, midx, filename)"//파일업로드 12 : fileName 추가
            + "VALUES (BIDX_SEQ.NEXTVAL, BIDX_SEQ.NEXTVAL,0,0,?,?,?,?,?,?)";//파일업로드 13 : fileName을 위한 ?추가
    try {
      pstmt = conn.prepareStatement(sql); //// sql을 여기 pstmt형태대로 담는다 (빨간줄trycatch)
      pstmt.setString(1, subject);// 첫번째 물음표에 subject를 담는다
      pstmt.setString(2, content); // 두번째 물음표에 content를 담는다
      pstmt.setString(3, writer); // 두번째 물음표에 content를 담는다
      pstmt.setString(4, ip); // 4번째 물음표에 content를 담는다
      pstmt.setInt(5, midx); // 다섯번째 물음표에 content를 담는다
      pstmt.setString(6, filename);//파일업로드 14 : fileName 추가
      value = pstmt.executeUpdate(); // 실행 :업데이트시 성공은 1 업데이트시 실패는 0

    } catch (SQLException e) {

      e.printStackTrace();
    } // 글쓰기  8번 finally까지 하면 메소드 하나 만들기가 완성된다
    finally {
      try {
        pstmt.close();
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return value;
  }

  // L5 . 메소드 하나 만들기
  public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) { // () -> pg6. 매개변수로 int형으로 Criteria cri를 넘겨받는다    //검색기능 13. 매개변수를 SearchCritera scri로 변경
    // return을 arrayList에 담기
    ArrayList<BoardVo> alist = new ArrayList<BoardVo>(); // 빨간줄 ArrayList import
    /* L11 초기화 */
    ResultSet rs = null;
    //String sql = "select * from a_board where delyn ='N' order by originbidx desc, depth asc";
    
  //검색기능 15. 필터
    String str ="";
    if (scri.getSearchType().equals("subject")) {
      str = "and subject like ?"; // 제목이면 이부분을 구문에 추가
    }else {
      str="and writer like ?"; // 제목이 아니면 이부분을 구문에 추가
    }
    
    //pg4. 페이징 쿼리 넣기
    String sql = "SELECT * FROM ("
        + " SELECT ROWNUM AS rnum, A.* FROM ("
        + "     SELECT * FROM a_board WHERE delyn='N' "+str+" ORDER BY originbidx desc, depth ASC) A"
        + " )B "
        + "WHERE rnum BETWEEN ? AND ? ";         
    
    // L8. try catch 
    try {
      pstmt = conn.prepareStatement(sql);
      /*추가 -> pg5. 페이징 쿼리 ? 에 값 넣기*/ //pstmt.setInt(1, (scri.getPage()-1)*15+1); //검색기능 14. cri를 scri로 변경 
      /*추가 -> pg5. 페이징 쿼리 ? 에 값 넣기*/  //pstmt.setInt(2, (scri.getPage()*15)); //검색기능 14. cri를 scri로 변경. 
      
      /*추가 ->  //검색기능 16. 페이징 쿼리 ? 에 값 넣기*/ pstmt.setString(1, "%"+scri.getKeyword()+"%"); //검색기능 14. cri를 scri로 변경. 
      /*추가 ->//검색기능 16.  페이징 쿼리 ? 에 값 넣기*/pstmt.setInt(2, (scri.getPage()-1)*15+1); //검색기능 14. cri를 scri로 변경 
      /*추가 ->//검색기능 16.  페이징 쿼리 ? 에 값 넣기*/ pstmt.setInt(3, (scri.getPage()*15)); //검색기능 14. cri를 scri로 변경. 
      
      rs = pstmt.executeQuery(); // 빨간줄 ResultSet import

      // L9. while 다음값이 존재하면 참 true
      while (rs.next()) {
        BoardVo bv = new BoardVo();
        bv.setBidx(rs.getInt("bidx")); // rs에 복사된 bidx를 bv에 옮겨담는다
        bv.setSubject(rs.getString("subject"));
        bv.setWriter(rs.getString("writer"));
        bv.setWriteday(rs.getString("writeday"));
        /* L.16 level_추가 */bv.setLevel_(rs.getInt("level_"));
        alist.add(bv); // 각각의 bv객체를 alist에 추가한다
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } /* L10. */ finally {
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

  // C6. 게시판 내용보기에 데이터 뿌려주기
  public BoardVo boardSelectOne(int bidx) {
    BoardVo bv = null;
    // C8.
    ResultSet rs = null;


    String sql = "select * from a_board where bidx=?";
    // C7. try catch
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, bidx);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        bv = new BoardVo();
        bv.setBidx(rs.getInt("bidx"));
        bv.setOriginbidx(rs.getInt("originbidx"));
        bv.setDepth(rs.getInt("depth"));
        bv.setLevel_(rs.getInt("level_"));

        bv.setSubject(rs.getString("subject"));
        bv.setContent(rs.getString("content"));
        bv.setWriter(rs.getString("writer"));
        bv.setWriteday(rs.getNString("writeday"));
      //파일업로드 보기 3 : fileName 추가
        bv.setFilename(rs.getString("filename"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } /* L10. */ finally {
      try {
        rs.close();
        pstmt.close();
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


    return bv;
  }

  // M6. Update 쿼리 사용 및 데이터 수정
  public int boardUpdate(String subject, String content, String writer, int bidx) {

    String sql = "update a_board set subject=?, content=?, writer=? where bidx=?";
    // M7. try catch
    int edt = 0;
    try {
      pstmt = conn.prepareStatement(sql); // conn객체를 이용 SQL문장을 실행준비로 만듬
      pstmt.setString(1, subject);
      pstmt.setString(2, content);
      pstmt.setString(3, writer);
      pstmt.setInt(4, bidx);
      edt = pstmt.executeUpdate(); // 성공적으로 수행시 0이상의 값을 반환
    } catch (Exception e) {
      e.printStackTrace();
    } /* M10. */ finally {
      try {
        pstmt.close();
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return edt;
  }

  // Delete6. delyn을 N에서 Y로 Update
  public int deleteBoard(int bidx) {
    String sql = "update a_board set delyn='Y', writeday=sysdate where bidx=?"; // 쿼리문 형식
    int del = 0; // 값 초기화 //성공적으로 수행시 0이상의 값을 반환

    // Delete7. try catch
    try {
      pstmt = conn.prepareStatement(sql); // conn객체를 이용 sql문장을 실행준비로 만듬
      pstmt.setInt(1, bidx); // update되는 bidx
      del = pstmt.executeUpdate(); // 성공적으로 수행시 0이상의 값을 반환
    } catch (Exception e) {
      e.printStackTrace();
    } /* Delete10. */ finally {
      try {
        pstmt.close();
        conn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return del;
  }

  // Reply10. replyBoard 객체생성 : 많은 매개변수를 사용하지 않고 bv로 넘겨 받는 방식으로 사용
  public int replyBoard(BoardVo bv) {
    int value = 0;

    // Reply11. sql1 에서 Update 구문 사용 sql2 에서 Insert 구문 사용
    String sql1="update a_board set depth=depth+1 where originbidx=? and depth > ?";
    String sql2="insert into a_board(bidx,originbidx,depth,level_,subject,content,writer,ip,midx) "
            + "VALUES(bidx_seq.nextval,?,?,?,?,?,?,?,?)";

    // Reply12. try catch 쿼리 2개 만들기
    try {

      // Reply15. 트랜젝션 - 실행을 하기위해 하나하나 커밋을 해줘야하는 방식으로 바꾸기
      conn.setAutoCommit(false);

      // Reply13. sql1 Update 구문 에서 값 꺼내기
      pstmt = conn.prepareStatement(sql1);
      pstmt.setInt(1, bv.getOriginbidx());
      pstmt.setInt(2, bv.getDepth());
      value = pstmt.executeUpdate();

      // Reply14. sql2 Insert 구문에서 값 꺼내기
      pstmt = conn.prepareStatement(sql2);
      pstmt.setInt(1, bv.getOriginbidx());
      pstmt.setInt(2, bv.getDepth() + 1); // 답변하기니까 +1 넣어준다
      pstmt.setInt(3, bv.getLevel_() + 1); // 답변하기니까 +1 넣어준다
      pstmt.setString(4, bv.getSubject()); // ?에 넣어준다
      pstmt.setString(5, bv.getContent());
      pstmt.setString(6, bv.getWriter());
      pstmt.setString(7, bv.getIp());
      pstmt.setInt(8, bv.getMidx());
      value = pstmt.executeUpdate();

      // Reply16. Update 및 Insert 후 커밋
      conn.commit();

    } catch (SQLException e) {
          // Reply17. 오류가 날때 롤백
            try {
              conn.rollback();
            } catch (SQLException e1) {
              e1.printStackTrace();
            }
      e.printStackTrace();
    } // finally까지 하면 메소드 하나 만들기가 완성된다
      finally {
          try {
            pstmt.close();
            conn.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
      }

    return value;
  }
  
  //pg14. boardTotal 메소드 만들기
  public int boardTotal(SearchCriteria scri) {  //검색기능이후 페이징 2 : 매개변수  SearchCriteria scri 추가
    //pg15. cnt 초기화
    int cnt = 0;

    //pg20. rs 초기화
    ResultSet rs = null;
    
  //검색기능이후 페이징 3 : 
    String str ="";
    if (scri.getSearchType().equals("subject")) {
        str = "and subject like ? "; 
    }else {
        str = "and writer like ?";
    }
    
    //pg17. 총 갯수 뽑는 쿼리 만들기 //검색기능이후 페이징 4 : 으로 수정됨
    //String sql = "select count(*) as cnt from a_board where delyn='N'";
    
  //검색기능이후 페이징 4 : 
    String sql = "select count(*) as cnt from a_board where delyn='N' "+str+"";
    
    //pg18 trycatch
    try {
      pstmt = conn.prepareStatement(sql);
    //검색기능이후 페이징 5 : 해당 라인 추가됨 
      pstmt.setString(1, "%"+scri.getKeyword()+"%");
      //pg19.  rs
      rs = pstmt.executeQuery();
      
      //pg21. 전체갯수 뽑은것을 받아온다
      if (rs.next()) {
        cnt = rs.getInt("cnt");
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }//pg22. finally로 닫아주기 
    finally {
      //pg23. trycatch
      try {
        rs.close();
        pstmt.close();
//        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
    //pg16. return에 cnt 넣기
    return cnt;
    
  }



}


