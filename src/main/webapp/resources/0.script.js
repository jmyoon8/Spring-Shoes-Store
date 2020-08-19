//아이디 중복 체크
function idCheck(){
	var memId = document.join.memId;
	
	var idForm=/^[a-z0-9_]{4,20}$/;
	var idCheck=idForm.test(memId.value);
	if(!idCheck){
		alert("아이디 양식을 체크해주세요");
		memId.focus();
		return false;
	}
	var url ="confirmId?memId="+document.join.memId.value;
	window.open(url,"아이디중복확인","menubar=no, width=350, height=400");
}

//중복안된 아이디 쓰기
function setId(id){
	opener.document.join.memId.value=id;
	opener.document.join.hiddenId.value=1;
	self.close();
}
//자동 커서 넘기기
function onkeyUp(){
	if(document.join){
		var root=document.join;
		if(root.jumin1.value.length==6){
			root.jumin2.focus();
			if(root.jumin2.value.length==7){
				root.hp1.focus();
				if(root.hp1.value.length==3){
					root.hp2.focus();
					if(root.hp2.value.length==4){
						root.hp3.focus();
						if(root.hp3.value.length==4){
							root.email1.focus();
						}
					}
				}
			}
		}
	}else if(document.modifyMem){
		var root=document.modifyMem;
		if(root.hp1.value.length==3){
			root.hp2.focus();
			if(root.hp2.value.length==4){
				root.hp3.focus();
				if(root.hp3.value.length==4){
					root.email1.focus();
				}
			}
		}
	}
}
//이메일2 값 변경
function email(){
	if(document.join){
		document.join.email2.value=document.join.email3.value;
	}else if(document.modifyMem){
		document.modifyMem.email2.value=document.modifyMem.email3.value;
	}else if(document.searchId){
		document.searchId.email2.value=document.searchId.email3.value;
	}
}

//이메일2 값 변경
function emaill(){
	if(document.searchPwd){
		document.searchPwd.email2.value=document.searchPwd.email3.value;
	}
}
//이메일 인증키 보내기(회원가입)
function sendKey(){
	var select=confirm("인증메일 보내시겠습니까?");
	if(select){
		if(document.join.email1.value&&document.join.email2.value){
			alert("회원인증 키를 이메일로 발송했습니다.");
			var email=document.join.email1.value+"@"+document.join.email2.value;
			var key=document.join.randomKey.value;
			window.open("sendKey?key="+key+"&email="+email,"이메일 체크","menubar=no, width=350, height=400");
		}else{
			alert("이메일을 작성해주세요!!");
		}
	}else{
		alert("취소되었습니다.");
	}
}

//이메일 인증키 보내기(비밀번호 찾기)
function sendKeyPwd(){
	var select=confirm("인증메일 보내시겠습니까?");
	if(select){
		if(document.searchPwd.email1.value&&document.searchPwd.email2.value){
			alert("회원인증 키를 이메일로 발송했습니다.");
			var email=document.searchPwd.email1.value+"@"+document.searchPwd.email2.value;
			var key=document.searchPwd.randomKey.value;
			window.open("sendKey.cus?key="+key+"&email="+email,"이메일 체크","menubar=no, width=350, height=400");
		}else{
			alert("이메일을 작성해주세요!!");
		}
	}else{
		alert("취소되었습니다.");
	}
}	

//이메일 키 확인
function key(){
	var re=0;
	if(re==0){
		var on;
		if(document.join){var on=document.join}
		if(document.searchPwd){var on=document.searchPwd}
		
		if(on.insertKey.value==on.randomKey.value){
			alert("인증성공!");
			on.hiddenEmail.value=1;
			re=1;
		}else{
			alert("인증실패!");
		}
	}else if(re==1){
		alert("이미 인증하셨습니다.");
	}
}

//Form 체크
function joinMem(){
	
	if(document.join){
		var on=document.join;
		if(on.hiddenId.value==0){
			alert("아이디중복확인해주세요!");
			on.memId.focus();
			return false;
		}
		if(on.memPwd.value!=on.rPwd.value){
			alert("비밀번호가 일치하지 않습니다.");
			on.memPwd.focus();
			return false;
		}
		if(on.hiddenEmail.value==0){
			alert("이메일 인증을 확인해주세요!!");
			on.insertKey.focus();
			return false;
		}
	}else if(document.modifyMem){
		var on=document.modifyMem;
		
		if(on.memPwd.value!=on.rPwd.value){
			alert("비밀번호가 일치하지 않습니다.");
			on.memPwd.focus();
			return false;
		}
	}else if(document.searchPwd){
		var on = document.searchPwd;
		if(!on.memId){
			alert("아이디를 입력하세요");
			on.memId.focus();
			return false;
		}
		if(!on.email1||!on.email2){
			alert("이메일을 입력해주세요!");
			on.email1.focus();
			return false;
		}
		if(on.hiddenEmail.value==0){
			alert("이메일 인증을 진행해주세요");
			on.insertKey.focus();
			return false;
		}
	}
}

//우편번호 찾기
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
//에러발생시
function error(msg){
	alert(msg);
	window.history.back();
}
//비밀번호 췍!_정보변경
function pwdCheck_modify(){
	window.open("/springStore/member/pwdCheck_modify","비밀번호 확인","menubar=no, width=600, height=500");
}
//비밀번호 췍!_회원탈퇴
function pwdCheck_delete(){
	window.open("/springStore/member/pwdCheck_delete","비밀번호 확인","menubar=no, width=600, height=500");
}
//아이디찾기
function searchId(){
	window.open("searchId","아이디찾기","menubar=no, width=800, height=150");
}
//비밀번호 찾기
function searchPwd(){
	window.open("searchPwd","비밀번호찾기","menubar=no, width=800, height=350");
}
//글삭제
function deleteConfirm(){
	
	var con =confirm("정말 삭제하시겠습니까?");
	if(con){
		var num=document.getElementsByName("num");
		var nowPage=document.getElementsByName("nowPage");
		location.href="/springStore/member/deleteContent?num="+num[0].value+"&nowPage="+nowPage[0].value;
	}else {
		
	}
}
//제고 등록
function writee(){
	window.open("/springStore/manager/write","재고등록","menubar=no, width=500, height=500");
}
//제고 삭제
function deleteStock(){
	window.open("/springStore/manager/deleteStock","재고삭제","menubar=no, width=300, height=150");
}
//제고 수정
function modifyStock(){
	window.open("/springStore/manager/modifyStock","재고등록","menubar=no, width=500, height=350");
}
//제고조회
function modifyRef(){
	var shoesNumber= document.modifyStock.shoesNumber.value;
	if(!shoesNumber){
		
	}else{
		location.href="/springStore/manager/modifyRef?shoesNumber="+shoesNumber;
	}
}

//사진보이기
function readImage() {
	var file = document.getElementById('file');
	if(file.files && file.files[0]) {
		var reader = new FileReader();
		//이미지 읽기
		reader.readAsDataURL(file.files[0]);
		//이미지 전부 읽어들였으면 호출
		reader.onload = function(){
			var image = document.getElementById('image');
			image.src = reader.result;
			//img 태그 노출
			image.style.display = '';
			
			//사진이 보여지는 태그
			/*<td><input type="file" id="file" name="pic" accept="image/*"></td>*/
		};
	}
};
//재고조회 카태고리 
function refCategory(){
	/*document.getElementsByName("category").setAttribute("selected")*/
	var category=document.modifyStock.category;
	var refCategory=document.modifyStock.refCategory;
	category[selected]=refCategory.value;
}

//제고 정보 조회 확인
function confirmModifyStock(){
	if(document.modifyStock.shoesNumber.value==0){
		alert("정확한 신발번호를 조회해주세요 입력해주세요");
		return false;
	}
}
//운영자 공지사항 히든값
function changeType(){
	var select=document.writeform.type;
	var type=document.writeform.select;
	type.value=select.value;
	console.log(type.value);
}
//장바구니 등록
function regCart(){
	var memId=document.buy.memId.value;
	var shoesNumber=document.buy.shoesNumber.value;
	var count=document.buy.count.value;
	if(count==0){
		alert("수량은 0게 이상을 입력해주세요");
		return false;
	}
	location.href="/springStore/member/regCart?shoesNumber="+shoesNumber+"&memId="+memId+"&count="+count;
}
//장바구니 전체선택 전체해제
$(function(){
	$("#all").click(function(){
		var d=$("#all").prop("checked");
		$(".re").prop("checked", d);
	});
});

//장바구니 삭제..
function deleteCart(){
	var list=document.getElementsByName("box");
	var deleteList="";
	var memId=document.cartForm.memId.value;
	
	
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			if(!deleteList){
				deleteList="&deleteList="+list[i].value;
			}else{
				deleteList=deleteList+"&deleteList="+list[i].value;
			}
		}
	}
	if(!deleteList){
		alert("삭제할 목록을 선택해 주세요");
		return false;
	}else{
		var confirm1=confirm("정말 장바구니에서 삭제하시겠습니까?");
		if(confirm1){
			location.href="/springStore/member/deleteCart?memId="+memId+deleteList;
			console.log(deleteList);
		}else{
			alert("삭제 취소헀습니다.");
		}
	}
}
//장바구니 정보조회
function modifyCartRef(){
	var memId= document.modifyCart.memId.value;
	var shoesNumber=document.modifyCart.shoesNumber.value;
	if(shoesNumber){
		location.href="/springStore/member/modifyCartRef?memId="+memId+"&shoesNumber="+shoesNumber;
	}else{
		alert("신발번호를입력해 주세요!");
		return false;
	}
}

//장바구니 주문확정
function confirmCart(){
	var list=document.getElementsByName("box");
	var confirmList="";
	
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			if(!confirmList){
				confirmList="&cNum="+list[i].value;
			}else{
				confirmList=confirmList+"&cNum="+list[i].value;
			}
		}
	}
	if(!confirmList){
		alert("주문할 목록을 선택해 주세요");
		return false;
	}else{
		var confirm1=confirm("정말 주문 하시겠습니까?");
		if(confirm1){
			
			location.href="/springStore/member/confirmList?"+confirmList;
		}else{
			alert("주문 취소헀습니다.");
		}
	}
}

//주문취소하기
function deleteBuyList(){
	var list=document.getElementsByName("box");
	var deleteBuyList="";
	
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			if(!deleteBuyList){
				deleteBuyList="deleteBuyList="+list[i].value;
			}else{
				deleteBuyList=deleteBuyList+"&deleteBuyList="+list[i].value;
			}
		}
	}
	if(!deleteBuyList){
		alert("삭제할 목록을 선택해 주세요");
		return false;
	}else{
		var confirm1=confirm("정말 주문 리스트에서 삭제하시겠습니까?");
		if(confirm1){
			location.href="/springStore/member/deleteBuyList?"+deleteBuyList;
			console.log(deleteBuyList);
		}else{
			alert("삭제 취소헀습니다.");
		}
	}
}

//매니저 주문 리스트 삭제
function deleteDecideList(){
	var list=document.getElementsByName("box");
	var deleteDecideList="";
	
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			if(!deleteDecideList){
				deleteDecideList="deleteDecideList="+list[i].value;
			}else{
				deleteDecideList=deleteDecideList+"&deleteDecideList="+list[i].value;
			}
		}
	}
	if(!deleteDecideList){
		alert("삭제할 목록을 선택해 주세요");
		return false;
	}else{
		var confirm1=confirm("정말 주문 리스트에서 삭제하시겠습니까?");
		if(confirm1){
			location.href="/springStore/manager/deleteDecideList?"+deleteDecideList;
			console.log(deleteDecideList);
		}else{
			alert("삭제 취소헀습니다.");
		}
	}
}

//매니저 주문확정 리스트컨펌
function confirmDecide(){
	var list=document.getElementsByName("box");
	var confirmDecide="";
	
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			if(!confirmDecide){
				confirmDecide="confirmDecide="+list[i].value;
			}else {
				confirmDecide=confirmDecide+"&confirmDecide="+list[i].value;
			}
		}
	}
	if(!confirmDecide){
		alert("확정할 목록을 선택해 주세요");
		return false;
	}else{
		var confirm1=confirm("정말 확정 하시겠습니까?");
		if(confirm1){
			location.href="/springStore/manager/confirmDecide?"+confirmDecide;
		}else{
			alert("취소했습니다.");
		}
	}
}

//회원용 바로사기
function nowBuy(){
	var count=document.buy.count.value;
	var shoesNumber=document.buy.shoesNumber.value;
	if(confirm("바로구매하시겠습니까?")){
		location.href="/springStore/member/nowBuy?count="+count+"&shoesNumber="+shoesNumber;
	}else{
		
	}
}


//환불 체크
function check(){
	var box=document.getElementsByName("box");
	
	var check = false;
	for(var i=0; i<box.length; i++){
		if(box[i].checked){
			check=true;
		}
	}
	if(!check){
		alert("환불한 목록을 체크해주세요!");
		return false;
	}
		
}

//환불삭제
function deleteRefund(){
	var pNum=document.getElementsByName("box");
	var pNumbers="";
	for(var i =0; i<pNum.length; i++){
		if(pNum[i].checked){
			if(!pNumbers){
				pNumbers=pNumbers+"pNum="+pNum[i].value;
			}else{
				pNumbers=pNumbers+"&pNum="+pNum[i].value;
			}
		}
	}
	if(!pNumbers){
		alert("환불할 목록을 선택해주세요");
		return false;
	}else{
		if(confirm("정말삭제하시겠습니까>?")){
			location.href="/springStore/member/deleteRefund?"+pNumbers;
		}else{
			alert("취소되었습니다.");
		}
	}
}

//게시판 검색
function search(){
	var word= document.getElementById("word").value;
	console.log(word);
	location.href="notice?word="+word;
}