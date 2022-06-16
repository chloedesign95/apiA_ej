<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<title>form</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="./css/theme.css" />
<style>
section{border:none; color:gray; font-weight:500;padding:5px;margin:auto; margin-top:30px;}
.formWrap{    max-width: 310px; background: rgba(255, 228, 196,0.3);margin: auto;padding: 20px;box-shadow: #c9c9c9 0px 0px 10px; }
.formWrap h2{text-align:center; color:#5d874e;}
.formWrap hr{margin:8px; border:1px dashed #5d874e;}

input:focus{outline:2px solid #5d874e;}
input[type=text]{border: none; border-radius:5px;width:-webkit-fill-available; font-size:14px; padding:8px; margin:8px;}
input[type=password]{border: none; border-radius:5px;width:-webkit-fill-available; font-size:14px; padding:8px; margin:8px;}
input[type=email]{border: none; border-radius:5px;width:-webkit-fill-available; font-size:14px; padding:8px; margin:8px;}

select {border: none; border-radius:5px;width: 60%; font-size: 14px; padding: 8px; margin: 8px; cursor:pointer;}
option {cursor:pointer;}
.date{border: none; border-radius:5px;font-size:14px; }
.date span{padding-left: 8px;}
input[type=date]{border: none; border-radius:5px;font-size:14px;  padding:8px; margin:8px; cursor:pointer; }
input[type=radio] {font-size:15pt ; width:20px;height:20px; color:olive;}
input[type=checkbox] {font-size:15pt ; width:20px;height:20px; color:olive;}

.hobby{font-size:14px; padding:8px; margin:0px 8px 8px 8px;}
.hobby div {display:inline;}
.hobby label {cursor:pointer; width:20px;}
.hobby label span { text-align: center; padding: 3px 10px; width:20px;}
.hobby label input {position: absolute; display: none; color: #fff !important;}
.hobby label input + span{background:white;color: #5d874e; border: 1px solid #5d874e; border-radius:4px;}
.hobby input:checked + span {color: white; background:#5d874e;}

.gender{font-size:14px; padding:8px; margin:0px 8px 8px 8px;}
.gender div {display:inline;}
.gender label {cursor:pointer; width:20px;}
.gender label span { text-align: center; padding: 3px 10px; width:20px;}
.gender label input {position: absolute; display: none; color: #fff !important;}
.gender label input + span{background-color:white;color: #5d874e; border: 1px solid #5d874e; border-radius:4px;}
.gender input:checked + span {color: white; background:#5d874e;}

button{cursor:pointer;  background:none; border:none;}
button img{width:14px;}
.btnarea{text-align:center; margin-top:20px;}
.btnarea .btn1 {min-width:35%; color:#5d874e; background:white;cursor:pointer; margin:3px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid white;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn1:hover {border: 1px solid #5d874e;}
.btnarea .btn2 {min-width:55%; color:white; background:#5d874e;cursor:pointer; margin:3px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid #5d874e;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn2:hover {background:#405c36; border: 1px solid #405c36;}
</style>

<script>

//체크함수 만들기
function check() {
    //alert("test"); //메소드 실행하여 check라는 메소드가 실행되는지테스트

    var fm = document.frm; //frm 객체를 fm으로 담겠다

    if (fm.memberId.value == "") { //input이 비어있을때 알림창을 띄운다
        alert("아이디를 입력하세요");
        return;
    }else if (fm.memberPwd.value==""){
    	alert("비밀번호를 입력하세요");
    	fm.memberPwd.focus();
    	return;
    }else if (fm.memberPwd2value==""){
    	alert("비밀번호 확인을 입력하세요");
    	fm.memberPwd2.focus();
    	return;
    }else if (fm.memberPwd.value !== fm.memberPwd2.value){
    	alert("비밀번호가 일치하지 않습니다.");
    	fm.memberPwd2.value = "";
    	fm.memberPwd2.focus();
    	return;
    }else if (fm.memberName.value==""){
    	alert("이름을 입력하세요");
    	fm.memberName.focus();
    	retrun;
    }else if (fm.memberEmail.value==""){
    	alert("이메일을 입력하세요");
    	fm.memberEmail.focus();
    	return;
    }else if (fm.memberPhone.value==""){
    	alert("전화번호를 입력하세요");
    	fm.memberPhone.focus();
    	return;
    }else if (fm.memberJumin.value==""){
    	alert("주민번호를 입력하세요");
    	fm.memberJumin.focus();
    	return;
    }else{
    	var chkYn = false; // chkYn이라는 변수를 false로 기본값을 지정
    	for(var i =0; i<fm.memberHobby.length; i++){
    		if (fm.memberHobby[i]. checked == true) { // 하나라도 체크가 되어있다면
    			chkYn = true;
    		    break;
    		} 
    	}
    	if (chkYn == false) {
    		var checkingbox = confirm("취미를 선택하지않고 넘어가시겠습니까?");
    		if(checkingbox == true){ //확인을 눌렀을때
    			    fm.action = "./memberJoinOk.jsp"; //action이라는 속성에 넣는다
    			    fm.method ="post"; //값을 감추어서 넘기는 방식
    			    fm.submit(); //넘긴다
    		}else{ //취소버튼을 눌렀을때
    			return; // 으로 막는다
    		}
    	}
    }
    
  //가상경로를 사용해서 페이지 이동시킨다
    //fm.action = "./memberJoinOk.jsp";
    fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
    /* fm.action = "./memberJoinOk.jsp"; //action이라는 속성에 넣는다 */
    fm.method ="post"; //값을 감추어서 넘기는 방식
    fm.submit(); //넘긴다

    return;
}
</script>

</head>

<body>
    
<!--     <header>
        <a href="./index.html"> <img src="./image/leaf.png" width="50" aria-label="HOME" /></a>
        <h1>JSP 회원가입 페이지</h1>
    </header> -->
    
    <!-- Section1 -->
    <section>
        
        <!-- formWrap -->
        <div class="formWrap">
            <h2>Join Us</h2>
            
            <!-- form -->
            <form name="frm">
                <input type="text" name="memberId" placeholder="아이디 입력"><br>
                <input type="text" name="memberName" placeholder="이름"><br>
                <input type="password" name="memberPwd" placeholder="비밀번호 입력"><br>
                <input type="password" name="memberPwd2" placeholder="비밀번호 확인"><br><br>
                <hr><br>
                <input type="email" name="memberEmail" placeholder="이메일 입력"><br>
                <div class="gender">성별&nbsp; : &nbsp;
                	<div>
 	                	<label><input type="radio" name="memberGender" value="M"><span>남</span></label>
    	                <label><input type="radio" name="memberGender" value="F"><span>여</span></label>
                	</div>
                </div><br>
                <!-- <div class="date"><span>생년월일&nbsp : &nbsp</span>
                    <input type="date" name="memberBirth" placeholder="날짜"><br>
                </div><br>-->
                <hr><br>
                <input type="text" name="memberJumin" placeholder="주민번호 입력"><br>
                
                <div class="hobby">지역&nbsp; : &nbsp;
                <select name="memberAddr">
						<option value="서울">서울</option>
						<option value="대전">대전</option>
						<option value="전주">전주</option>
				</select>
                </div>
                
                <input type="text" name="memberPhone" placeholder="전화번호 입력"><br>
                <br>
                <hr><br>
                <div class="hobby">취미&nbsp; : &nbsp;
                	<div>
                		<label><input type="checkbox" name="memberHobby" value="football"><span>축구</span></label>
                    	<label><input type="checkbox" name="memberHobby" value="basketball"><span>농구</span></label>
                    	<label><input type="checkbox" name="memberHobby" value="baseball"><span>야구</span></label>
                	</div>
                </div>
            </form>
            <!-- /form -->
            
            <br>

            <div class="btnarea">
   				<input type="reset" value="수 정" class="btn1"> 
                <button type="button" name ="btn" value="확인" onclick="check();" class="btn2"><!--<img src="./image/leaf.png" alt="로고">-->확 인</button>
            </div>
            
        </div>
        <!--/ formWrap -->
        
    </section>
    <!--/ Section1 -->
    
    <footer></footer>
    
</body>

</html>
	