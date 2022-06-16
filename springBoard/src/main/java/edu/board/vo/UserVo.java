package edu.board.vo;

// 0616 회원가입 후 정보 뜨기  01. Vo 생성
public class UserVo {
	private String name;
	private int age;
	private String addr;
	private String phone;
	// // 0616  회원가입하기 06. 추가
	private String id;
	private String password;
	private int midx;
	
	// // 0616  회원가입하기 07. Getter Setter 추가
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	// 0616 회원가입 후 정보 뜨기  02.GetterSetter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
