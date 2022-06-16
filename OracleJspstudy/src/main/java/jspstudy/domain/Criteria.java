package jspstudy.domain;

public class Criteria {
  //pg7. Criteria 클래스 만들기
  private int page;    //페이지
  private int perPageNum;    //화면에 리스트 출력개수
  
  //pg8. 초기화값 넣기
  public Criteria() {
      this.page = 1;
      this.perPageNum = 15;
  }
  
  //pg9. generate setter getter
  public int getPage() {
    return page;
  }
  
  public void setPage(int page) {
    //pg10. 따로 설정
    if (page <=1) {
      this.page = 1;
      return;
     }   
    this.page = page;
  }

  public int getPerPageNum() {
    return perPageNum;
  }

  public void setPerPageNum(int perPageNum) {
  //pg11.
    if (perPageNum <=0 || perPageNum >100) {
     this.perPageNum = 10;
     return;
    }
    this.perPageNum = perPageNum;
}
  
}
