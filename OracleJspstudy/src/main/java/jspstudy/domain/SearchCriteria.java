package jspstudy.domain;
//검색기능 2 : SearchCriteria 클래스 만들기 (상속)
public class SearchCriteria extends Criteria{
  
  //검색기능 3 : 지정
    private String searchType; //검색기능 5 : Generate setter getter
    private String keyword; //검색기능 5 : Generate setter getter
    
    //검색기능 4 : SearchCriteria 생성자 안에서 생성 할때
    public SearchCriteria() {
      this.searchType="";
      this.keyword="";
      
      //검색기능 5 : Generate setter getter
    }

    public String getSearchType() {
      return searchType;
    }

    public void setSearchType(String searchType) {
      this.searchType = searchType;
    }

    public String getKeyword() {
      return keyword;
    }

    public void setKeyword(String keyword) {
      this.keyword = keyword;
    }
}
