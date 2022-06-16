package jspstudy.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {

  // SQL 접속 정보

  String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // jdbc 통해 자기 컴퓨터 안에 있는 xe서비스로 접속한다
  String user = "system";
  String password = "1234";

  public Connection getConnection() {
    Connection conn = null;
    try {
      // DB 연결 소프트웨어 드라이버안에 있는 OracleDriver.class 사용하기
      // 해당 패키지에 있는 클래스를 가져온다
      Class.forName("oracle.jdbc.driver.OracleDriver");
      // java sql패키지 안에 있는 driverManager 접속정보를 활용해서 연결객체를 만든다
      // 이렇게 길게 쓰지않고 상단에 import 하여 connection 객체를 만든다
      conn = DriverManager.getConnection(url, user, password);

      // create속성을 사용할 수 있는 메소드를 사용
      /* Statement stmt = conn.createStatement(); //Statement stmt라는 쿼리구문을 실행하기 위한 쿼리실행 클래스 */
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

}
