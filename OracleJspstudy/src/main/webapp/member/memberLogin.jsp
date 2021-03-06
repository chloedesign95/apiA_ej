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
.btnarea{text-align:center; margin-top:10px;}
.btnarea .btn1 {min-width:100%; color:#5d874e; background:white;cursor:pointer; margin:3px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid white;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn1:hover {border: 1px solid #5d874e;}
.btnarea .btn2 {min-width:100%; color:white; background:#5d874e;cursor:pointer; margin:3px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid #5d874e;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn2:hover {background:#405c36; border: 1px solid #405c36;}
</style>

<script>

//???????????? ?????????
function check() {
    //alert("test"); //????????? ???????????? check?????? ???????????? ????????????????????????

    var fm = document.frm; //frm ????????? fm?????? ?????????

    if (fm.memberId.value == "") { //input??? ??????????????? ???????????? ?????????
        alert("???????????? ???????????????");
        return;
    }else if (fm.memberPwd.value==""){
    	alert("??????????????? ???????????????");
    	fm.memberPwd.focus();
    	return;
    }
    	
  //??????????????? ???????????? ????????? ???????????????
    //fm.action = "./memberJoinOk.jsp";
    fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
    /* fm.action = "./memberJoinOk.jsp"; //action????????? ????????? ????????? */
    fm.method ="post"; //?????? ???????????? ????????? ??????
    fm.submit(); //?????????

    return;
}
</script>

</head>

<body>
    
<!--     <header>
        <a href="./index.html"> <img src="./image/leaf.png" width="50" aria-label="HOME" /></a>
        <h1>JSP ???????????? ?????????</h1>
    </header> -->
    
    <!-- Section1 -->
    <section>
        
        <!-- formWrap -->
        <div class="formWrap">
            <h2>Login</h2>
            
            <!-- form -->
            <form name="frm">
                <input type="text" name="memberId" placeholder="????????? ??????"><br>
                <input type="password" name="memberPwd" placeholder="???????????? ??????"><br>
            </form>
            <!-- /form -->
            
            <br>

            <div class="btnarea">
                <button type="button" name ="btn" value="?????????" onclick="check();" class="btn2">???????????????</button>
                <button type="button" name ="btn1" value="????????????" onclick="location.href='<%=request.getContextPath()%>/member/memberJoin.do'" class="btn1">????????????</button>
            </div>
            
        </div>
        <!--/ formWrap -->
        
    </section>
    <!--/ Section1 -->
    
    <footer></footer>
    
</body>

</html>
	