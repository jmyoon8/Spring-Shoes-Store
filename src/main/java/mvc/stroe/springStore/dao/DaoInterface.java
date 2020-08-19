package mvc.stroe.springStore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import mvc.stroe.springStore.dto.Total;
import mvc.stroe.springStore.dto.board;
import mvc.stroe.springStore.dto.memberInfo;
import mvc.stroe.springStore.dto.shoesInfo;

public interface DaoInterface {
	//이메일 인증
	public void sendMail(String toEmail, String key);
	//랜덤키 생성
	public String randomkey();
	//로그인확인
	public Map<String,String> idCheck(String memId);
	//아이디중복확인
	public int idconfirm(String memId);
	//회원가입
	public int joinMem(memberInfo mem);

	//비밀번호체크
	public String passwordChk(String memId);
	//회원정보폼에 뿌릴 정보 
	public memberInfo modifyForm(String memId);
	
	//회원정보 수정 프로세스
	public int modifyPro(Map<String, Object> map);
	//회원정보 삭제 프로세스
	public int deletePro(String memId);
	//아이디찾기 프로세스
	public String searchIdPro(String email);
	
	//비밀번호 찾기 프로세스
	public int searchPwdPro(Map<String,Object> map);
	//비밀번호 체크
	public int pwdChk(Map<String, Object> map);
	//비밀번호 업데이트 
	public int updatePwd(Map<String, Object> newPwd);
	
	//매니저 로그인
	public int managerLogin(String hostId, String hostPwd);
	//공지사항 게시판 검색 글수
	public String boardTotal(String word);
	//일반게시물 총 리스트 =>어레이 리스트로 담아서 한번에 뿌리자
	public ArrayList<board> boardList(Map<String, Object>map);
	//공지사항리스트
	public ArrayList<board> newsList();
	//조회수 증가 프로세스
	public void viewCnt(int num);
	//게시글 상세정보 프로세스
	public board viewContent(int num);
	//게시글 수정 프로세스
	public int modifyContentPro(board vo);
	
	
	/////////게시글 삭제 프로세스/////////////////////////////////
	public int deleteContentPro(int num);
	
	//지울 게시물의 ref, ref_step,ref_level 구하기
	public board ref(int num);
	//max_step구하기
	public String maxRef(Map<String, Object>map);
	//리플.하위리플 지우기
	public int deleteRe(Map<String, Object>map);
	//원글 다지우기
	public int deleteOri(Map<String, Object>map);
	
	///////////////////////////////////////////////////////////
	
	
	
	//////////////////게시글 등록 프로세스/////////////////
	public int writePro(board vo);
	
	//원본글 등록 프로세스
	public int original(board vo);
	//리플일때 ref_step 밀기
	public int pushRef(board vo);
	//리플달기
	public int writeRe(board vo);
	
	/////////////////////////////////////////////////
	
	//총 재고목록
	public String stockTotal();
	//신발 번호 랜덤키
	public int shoesKey();
	//게시판 총 리스트 =>어레이 리스트로 담아서 한번에 뿌리자
	public ArrayList<shoesInfo> stockList(Map<String, Object> map);
	//재고리스트 등록(사진포함)
	public int insertStock(shoesInfo vo);
	//제고리스트 삭제
	public int deleteStockPro(int shoesNumber);
	//제고 정보 조회
	public shoesInfo modifyRef(int shoesNumber);
	//제고 업데이트
	public int modifyStock(shoesInfo vo);
	//제고 조회수 증가
	public void cnt(int shoesNumber);
	//최근상품 리스트
	public ArrayList<shoesInfo> recently();
	//베스트상품 리스트
	public ArrayList<shoesInfo> best();
	
	
	//장바구니 등록/////////////////////////////////////
	//장바구니 중복체크
	public int checkCart(Map<String, Object>map);
	//중복이있으면 카운트만 업데이트
	public int updateCart(Map<String, Object>map);
	//Cnum찾기
	public int cNum(Map<String, Object>map);
	//중복이 없으면 인설트
	public int insertCart(Map<String, Object>map);
	//장바구니 등록 
	public int regCart(Map<String, Object>map);
	//////////////////////////////////////////////////
	
	
	//////////////////////
	//장비구니 총 갯수 구하기
	public String totalCart(String memId);
	//장바구니 목록
	public ArrayList<shoesInfo> cartList(String memId);
	///////////////////////////////
	
	//장바구니 삭제
	public int deleteCart(String cNum);
	//장바구니 수정정보 호출
	public shoesInfo modifyCartRef(Map<String, Object>map);
	//장바구니 정보수정
	public int modifyCartPro(Map<String, Object>map);
	//장바구니 주문확정
	public int confirmCart(int cNum);
	//바로사기 (회원용)
	public int nowBuy(Map<String, Object>map);
	
	//주문목록 보여주기(회원용)/////////
	public ArrayList<shoesInfo> buyList(Map<String , Object>map);
	//총 주문목록 
	public String totalBuyList(String memId);
	//주문목록 지우기
	public int deleteBuyList(int oNum);
	
	//주문확정 리스트(매니저용)
	public ArrayList<shoesInfo> decide(Map<String, Object>map);
	
	//매니저용 주문목록 총갯수
	public String totalDecide();
	//매니저용 주문확정리스트 삭제
	public int deleteDecideList(String oNum);
	
	//////////////매니저용 주문확정 리스트 확정////////////////////////////////////////////
	public int confirmDecide(String oNum);
	//주문확정 정보 가져오기
	public shoesInfo getDecideInfo(String oNum);
	//결산리스트에 입력하기
	public int insertRAT(shoesInfo vo);
	//오더리스트 지우기
	public int deleteOrderList(String oNum);
	//재고리스트 수량 지우기
	public int updateStock(shoesInfo vo);
	/////////////////////////////////////////////////////////////////////////////
	
	//환불 리스트 뿌리기////////
	public ArrayList<shoesInfo> refundList(String memId);
	//환불 총 갯수 
	public String totalRefund(String memId);
	///////////////////////
	
	/////////환불 확정/////////////////////////
	//환불 확정
	public int confirmRefund(ArrayList<shoesInfo> list);
	//환불수량만큼 만큼 환불리스트 수량에서 빼기
	public void minusRefuntCount(Map<String, Object>map);
	//환불리스트에 수량0인 부분 지우기
	public void ifZerodelete();
	//환불한만큼 stock에 update
	public int plusStock(Map<String, Object>map);
	///////////////////////////////////////////
	
	//환불 리스트 삭제
	public int deleteRefund(String pNum);
	
	//제이슨 차트
	public ArrayList<JSONArray> chart(Map<String, Object>map);
	//월별 총매출
	public ArrayList<Total> monthTotal(Map<String, Object>map);
	//총매출
	public int total(Map<String , Object>map);
	//평균매출
	public int avg(Map<String, Object>map);
}

