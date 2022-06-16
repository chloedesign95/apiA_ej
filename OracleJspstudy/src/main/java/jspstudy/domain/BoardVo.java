package jspstudy.domain;

//bin 클래스 용도  : db에서 board의 컬럼 값을 담는 역할
//L6.BoardVo class 만들기
//값을 담는 객체 Vo(value object)
public class BoardVo {
  private int bidx;
  private String subject;
  private String content;
  private String writer;
  private String writeday;
  private String delyn;
  private String ip; // L7.  ip : 마우스 오른쪽  -> Source -> Generate setter getter 
  //L15.
  private int originbidx;
  private int depth;
  private int level_;  //getter setter 만들기
  private int midx;
  //파일업로드 보기 2 : fileName 추가
  private String filename;  //getter setter 만들기
  
  public String getFilename() {
    return filename;
  }
  public void setFilename(String filename) {
    this.filename = filename;
  }
  public int getMidx() {
    return midx;
  }
  public void setMidx(int midx) {
    this.midx = midx;
  }
  public int getOriginbidx() {
    return originbidx;
  }
  public void setOriginbidx(int originbidx) {
    this.originbidx = originbidx;
  }
  public int getDepth() {
    return depth;
  }
  public void setDepth(int depth) {
    this.depth = depth;
  }
  public int getLevel_() {
    return level_;
  }
  public void setLevel_(int level_) {
    this.level_ = level_;
  }
  public int getBidx() {
    return bidx;
  }
  public void setBidx(int bidx) {
    this.bidx = bidx;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public String getWriteday() {
    return writeday;
  }
  public void setWriteday(String writeday) {
    this.writeday = writeday;
  }
  public String getDelyn() {
    return delyn;
  }
  public void setDelyn(String delyn) {
    this.delyn = delyn;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
}
