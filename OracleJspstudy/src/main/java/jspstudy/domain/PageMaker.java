package jspstudy.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//pg24. PageMaker 클래스 만들기
public class PageMaker {
  //pg25.  페이징 변수 지정
  private int totalCount;
  private int startPage;
  private int endPage;
  private boolean prev;
  private boolean next;
  private int displayPageNum =10; // 페이징 번호를 나타내는 변수 <1 2 3 4 5 6 7 8 9 10> 10개라고 정의
  //private Criteria cri;
//검색기능 9
  private SearchCriteria scri; // searchCriteria를 정의를 내리고 setter getter
  
  public SearchCriteria getScri() {
    return scri;
  }
  public void setScri(SearchCriteria scri) {
    this.scri = scri;
  }
  //pg26. setter getter
  //public Criteria getCri() {
    //return cri; 
 // }
  //public void setCri(Criteria cri) {
    //this.cri = cri;
  //}

  public int getTotalCount() {
    return totalCount;
  }
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    //pg32.값을 호출
    calcData();
  }
  public int getStartPage() {
    return startPage;
  }
  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }
  public int getEndPage() {
    return endPage;
  }
  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }
  public boolean isPrev() {
    return prev;
  }
  public void setPrev(boolean prev) {
    this.prev = prev;
  }
  public boolean isNext() {
    return next;
  }
  public void setNext(boolean next) {
    this.next = next;
  }
  public int getDisplayPageNum() {
    return displayPageNum;
  }
  public void setDisplayPageNum(int displayPageNum) {
    this.displayPageNum = displayPageNum;
  }
  
  //pg27. 몇페이지까지 나타내주어야하는지 정의
  public void calcData() {
    
    //pg28
    endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum)*displayPageNum); //검색기능 10 cri를 scri로 변경
    
    //pg29. 몇번부터 시작할 것인가
    startPage =(endPage - displayPageNum)+1;
    
    //pg30. 임시페이지 만들기
    int tempEndPage = (int) (Math.ceil(totalCount/(double)scri.getPerPageNum())); //전체  갯수를 나누는것을 Math.ceil로 활용해서 올림처리 //검색기능 10 cri를 scri로 변경
    
    //pg31
    if(endPage > tempEndPage) {
      endPage = tempEndPage;
    }
    prev = startPage ==1 ?false : true; // 첫번째페이지가 1이면 false 아니면 true
    next = endPage* scri.getPerPageNum() >= totalCount? false:true;  //검색기능 10 cri를 scri로 변경
    
  }
  
  //검색 추가 기능  (Encoding) 1 : 일단 만들면서 알아보기
  public String encoding(String keyword) {
  //검색 추가 기능 (Encoding)  2 : 
    String str = "";
    
  //검색 추가 기능 (Encoding) 3 : 
   // if(keyword !=null) {
      //검색 추가 기능 (Encoding) 4 : try catch 
      try {
        if (keyword != null) {
            str = URLEncoder.encode(keyword, "UTF-8");
        }
    } catch (UnsupportedEncodingException e) {
        
        e.printStackTrace();
    }
   // }
    System.out.println("str"+str);
    //검색 추가 기능 (Encoding) 2 : 
    return str;
  }
  
}
