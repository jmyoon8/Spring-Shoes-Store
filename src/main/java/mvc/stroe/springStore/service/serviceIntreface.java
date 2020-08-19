package mvc.stroe.springStore.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface serviceIntreface {
	
	//메인화면 베스트 최신 목록 뿌려주기
	public void bestAndRecently(HttpServletRequest req, Model model);
	//아이디 중복확인
	public void idconfirm(HttpServletRequest req, Model model);
	//이메일 체크용 랜덤키 생성
	public void randomKey(HttpServletRequest req, Model model);
	//이메일 보내기
	public void sendEmail(HttpServletRequest req, Model model);
	//회원가입 서비스
	public void joinMem(HttpServletRequest req, Model model);
	//회원정보 변경 폼 서비스
	public void modifyForm(HttpServletRequest req, Model model);
	//회원정보 수정 프로세스 서비스
	public void modifyPro(HttpServletRequest req, Model model);
	//회원탈퇴 프로세스 서비스
	public void deletePro(HttpServletRequest req, Model model);
	//아이디찾기 서비스
	public void searchIdPro(HttpServletRequest req, Model model);
	//비멀번호 찾기 서비스
	public void searchPwdPro(HttpServletRequest req, Model model);
	//메니저 로그인 서비스
	public void loginManager(HttpServletRequest req, Model model);
	//공지 게시판 로딩 서비스
	public void boardList(HttpServletRequest req, Model model);
	//조회수 증가 서비스
	public void viewCnt(HttpServletRequest req, Model model);
	//게시글 상세페이지 서비스
	public void viewContent(HttpServletRequest req, Model model);
	//게시글 수정 서비스
	public void modifyContent(HttpServletRequest req, Model model);
	//게시글 수정 프로세스
	public void modifyContentPro(HttpServletRequest req, Model model);
	//게시글 삭제 프로세스->미완
	public void deleteContentPro(HttpServletRequest req, Model model);
	//게시글 등록 프로세스
	public void writePro(HttpServletRequest req, Model model);
	//바로사기
	public void nowBuy(HttpServletRequest req, Model model);
	//카트 등록 서비스
	public void regCart(HttpServletRequest req, Model model);
	//카트 목록 보기
	public void cartList(HttpServletRequest req, Model model);
	//카트 지우기
	public void deleteCart(HttpServletRequest req, Model model);
	//카트 수정용 정보조회
	public void modifyCart(HttpServletRequest req, Model model);
	//카트 정보 수정 서비스
	public void cartModifyPro(HttpServletRequest req, Model model);
	//카트 주문확정
	public void confirmCart(HttpServletRequest req, Model model);
	//주문리스트 보기
	public void buyList(HttpServletRequest req, Model model);
	//주문취소
	public void deleteBuyList(HttpServletRequest req, Model model);
	//매니저용 주문확정 리스트
	public void decide(HttpServletRequest req, Model model);
	//매니저용 주문확정 리스트 삭제
	public void deleteDecideList(HttpServletRequest req, Model model);
	//매니저요 주문확정 확정!
	public void confirmDecide(HttpServletRequest req, Model model);

	//환불목록 보기
	public void refundList(HttpServletRequest req, Model model);
	//환불 목록 지우기=>상태값을주어 환불 목록에서 안보이도록한다.(if체크로 안띄어지게 하자)
	public void refundDelete(HttpServletRequest req, Model model);
	//환불 확정
	public void confirmRefund(HttpServletRequest req, Model model);
	
	//매니저용 차트보기
	public void viewChart(HttpServletRequest req, Model model);

}
