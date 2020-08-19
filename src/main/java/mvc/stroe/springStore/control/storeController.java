package mvc.stroe.springStore.control;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import mvc.stroe.springStore.service.serviceIntreface;
import mvc.stroe.springStore.service.tbBoardInterface;

@Controller
public class storeController {
	 
	private static final Logger logger = LoggerFactory.getLogger(storeController.class);
	 
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	serviceIntreface ser;
	
	@Autowired
	tbBoardInterface serTb;
	
	//메인화면   
	@RequestMapping("main")
	public String main(HttpServletRequest req, Model model) {
		logger.info("url=>main");
		
		ser.bestAndRecently(req, model);
		
		return "/store/main"; 
	}
	@RequestMapping("/store/login")
	public String denied() {
		
		return "/store/denied";
	}
	//로그인페이지
	@RequestMapping("login")
	public String login() {
		logger.info("url=>login");
		
		
		return "/store/login";
	} 
	//로그인 프로세스(회원,매니저)
	@RequestMapping("toLoginPro")
	public String loginPro() {
		logger.info("url=toLoginPro");
		
		return "/store/loginPro";
	}
	//로그아웃프로세스
	@RequestMapping("/member/logOut")
	public String logOut() {
		//jsp에서 세션지우고스크립트로 main패이지가기
		logger.info("url=>logOut");
		
		return "/store/logOut";
	}
	//회원가입
	@RequestMapping("join")
	public String join(HttpServletRequest req, Model model) {
		logger.info("url=>join");
		
		ser.randomKey(req, model);
		
		return "/store/join";
	}
	//아이디체크=>다시해야하는 곳
	@RequestMapping("confirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		logger.info("url=>confirmId");
		
		ser.idconfirm(req, model);
		
		return "/store/confirmId";
	}
	//이메일 체크
	@RequestMapping("sendKey")
	public String sendKey(HttpServletRequest req, Model model) {
		logger.info("url=sendKey");
		
		ser.sendEmail(req, model);
		
		return "/store/email";
	}
	//회원가입
	@RequestMapping("joinPro")
	public String joinPro(HttpServletRequest req, Model model) {
		
		ser.joinMem(req, model);
		
		return "/store/joinPro";
	}
	//회원정보 변경창 띄우기
	@RequestMapping("/member/pwdCheck_modify")
	public String pwdCheck_modify(HttpServletRequest req,Model model) {
		
		
		return "/store/pwdCheck_modify";
	} 
	//회원정보 변경을위한 비밀번호체크 
	@RequestMapping("/member/modifyForm")
	public String modifyForm(HttpServletRequest req,Model model) {
		
		ser.modifyForm(req, model);
		
		return "/store/modifyForm"; 
	}
	
	//회원정보 변경
	@RequestMapping("/member/modifyPro")
	public String modifyPro(HttpServletRequest req, Model model) {
		      
		ser.modifyPro(req, model);
		
		return "/store/modifyPro";
	}
	//회원탈퇴 폼
	@RequestMapping("/member/pwdCheck_delete")
	public String pwdCheck_delete(HttpServletRequest req, Model model) {
		
		 
		
		return"/store/pwdCheck_delete";
	}
	//회원탈퇴 프로세스
	@RequestMapping("/member/deletePro")
	public String deletePro(HttpServletRequest req, Model model) {
			
			ser.deletePro(req, model);
		
		return "/store/deletePro";
	}
	//메니저 로그아웃
	@RequestMapping("/manager/managerlogOut")
	public String managerlogOut(HttpServletRequest req, Model model) {
		
		
		return"/store/logOut";
	}
	//아이디찾기
	@RequestMapping("searchId")
	public String searchId(HttpServletRequest req, Model model) {
		
		
		return "/store/searchId";
	}
	//아이디찾기 프로새스
	@RequestMapping("searchIdPro")
	public String searchIdPro(HttpServletRequest req, Model model) {
		
		ser.searchIdPro(req, model);
		
		return"/store/searchId";
	}
	//비밀번호찾기 폼
	@RequestMapping("searchPwd")
	public String searchPwd(HttpServletRequest req, Model model) {
		
		ser.randomKey(req, model);
		
		return"/store/searchPwd";
	}
	  
	//비밀번호찾기 프로세스
	@RequestMapping("searchPwdPro")
	public String searchPwdPro(HttpServletRequest req, Model model) {
		
		ser.searchPwdPro(req, model);
		 
		return"/store/searchPwd";
	}
	//전제상품
	@RequestMapping("board")
	public String board(HttpServletRequest req, Model model) {
		
		serTb.boardList(req, model);
		 
		return "/store/tb_board";
	}
		
	//500에러페이지
	@RequestMapping("/store/500")
	public String error500(HttpServletRequest req ,Model model) {
		
		return "/store/500error";
	}
	
	//404에러페이지
	@RequestMapping("/store/404")
	public String error404(HttpServletRequest req ,Model model) {
		
		return "/store/404error";
	}
	
	//제고등록
	@RequestMapping("/manager/write")
	public String write(HttpServletRequest req, Model model) {
		

		
		return "/store/tb_write";
	}
	//제고등록 프로새스
	@RequestMapping(value="/manager/insertStock", method=RequestMethod.POST)
	public String insertStock(MultipartHttpServletRequest req, Model model) {
		
		serTb.insertStock(req, model);
		
		return "/store/tb_write";
	}
	//제고삭제 페이지
	@RequestMapping("manager/deleteStock")
	public String deleteStock(HttpServletRequest req, Model model) {
		 
		return "/store/tb_deleteStockPro";
	}
	//제고삭제 프로세스
	@RequestMapping("/manager/deletePro")
	public String deletePro1(HttpServletRequest req, Model model) {
		
		serTb.deleteStockPro(req, model);
		
		return "/store/tb_deleteStockPro";
	}
	//제고수정폼
	@RequestMapping("/manager/modifyStock")
	public String modifyStock(HttpServletRequest req, Model model) {
		
		return "/store/tb_modifyStock";
	}
	
	//제고수정 참조
	@RequestMapping("/manager/modifyRef")
	public String modifyRef(HttpServletRequest req, Model model) {
		
		serTb.modifyRef(req, model);
				
		return "/store/tb_modifyStock";
	}
	
	//제고수정
	@RequestMapping("/manager/modifystock")
	public String modifystock(MultipartHttpServletRequest req, Model model) {
		
		serTb.modifyStock(req, model);
		
		return "/store/tb_modifyStock";
	}
	

	//게시판
	@RequestMapping("notice")
	public String notice(HttpServletRequest req,Model model	) {
		
		ser.boardList(req, model);
		
		return "/store/board";
	}
	
	//게시판 글쓰기 폼
	@RequestMapping("/member/writeNotice")
	public String ManagerWriteNotice(HttpServletRequest req, Model model) {
		
		
		return"/store/write";
	}
	//게시판 글쓰기 프로
	@RequestMapping("/member/writePro")
	public String writePro(HttpServletRequest req, Model model) {
		
		ser.writePro(req, model);
		
		return "/store/write";
	}
	//게시판 글보기
	@RequestMapping("/member/content")
	public String content(HttpServletRequest req,Model model) {
		
		ser.viewCnt(req, model);
		ser.viewContent(req, model);
		
		return"/store/content";
	}
	//게시판 수정폼 
	@RequestMapping("/member/modifyContent")
	public String modifyContent(HttpServletRequest req, Model model) {
		
		ser.modifyContent(req, model);
		 
		return"/store/modifyContent";
	}
	//게시판 수정 프로세스
	@RequestMapping("/member/modifyContentPro")
	public String modifyContentPro(HttpServletRequest req, Model model) {
		
		ser.modifyContentPro(req, model);
		
		return"/store/modifyContent";
	}
	//게시글 삭제 프로세스
	@RequestMapping("/member/deleteContent")
	public String deleteContent(HttpServletRequest req, Model model) {
		
		ser.deleteContentPro(req, model);
		
		return"/store/deleteContentPro";
	}
	//물품보기
	@RequestMapping("/member/goodsInfo")
	public String goodsInfo(HttpServletRequest req, Model model) {
		
		serTb.goodsInfo(req, model);
		
		return"/store/goodsInfo";
		
	}
	//장바구니에 담기
	@RequestMapping("/member/regCart")
	public String regCart(HttpServletRequest req, Model model) {
		
		ser.regCart(req, model);
		
		return"/store/regCart";
	}
	//장바구니 보기
	@RequestMapping("/member/cart")
	public String cart(HttpServletRequest req, Model model) {
		
		ser.cartList(req, model);
		 
		return "/store/cart";
	}
	//장바구니 수정
	@RequestMapping("/member/modifyCart")
	public String modifyCart(HttpServletRequest req, Model model) {
		
		return"/store/modifyCart";
	}
	//장바구니 참조
	@RequestMapping("/member/modifyCartRef")
	public String modifyCartRef(HttpServletRequest req, Model model) {
		
		ser.modifyCart(req, model);
		
		return "/store/modifyCart";
	}
	//장바구니 수량 수정
	@RequestMapping("/member/cartModifyPro")
	public String cartModifyPro(HttpServletRequest req, Model model) {
		
		ser.cartModifyPro(req, model);
		
		return"/store/modifyCart";
	}
	//장바구니 삭제
	@RequestMapping("/member/deleteCart")
	public String deleteCart(HttpServletRequest req, Model model) {
		
		ser.deleteCart(req, model);
		
		return "/store/cart";
	}  
	//장바구니 컨펌
	@RequestMapping("/member/confirmList")
	public String confirmList(HttpServletRequest req, Model model) {
		
		ser.confirmCart(req, model);
		
		return "/store/cart";
	} 
	//주문목록
	@RequestMapping("/member/buyList")
	public String buyList(HttpServletRequest req, Model model) {
		
		ser.buyList(req, model);
		
		return"/store/buyList";
	}
	//주문목록 삭제
	@RequestMapping("/member/deleteBuyList")
	public String deleteBuyList(HttpServletRequest req, Model model) {
		
		ser.deleteBuyList(req, model);
		ser.buyList(req, model);
		
		return "/store/buyList";
	}
	//메니저 주문확정 페이지
	@RequestMapping("/manager/decide")
	public String managerBoard(HttpServletRequest req, Model model) {
		
		ser.decide(req, model);
		
		return "/store/decide";
	}
	//메니저 주문목록 삭제
	@RequestMapping("/manager/deleteDecideList")
	public String deleteDecideList(HttpServletRequest req, Model model) {
		
		ser.deleteDecideList(req, model);
		
		return "/store/decide";
	}
	//메니저 주문목록 확정
	@RequestMapping("/manager/confirmDecide")
	public String confirmDecide(HttpServletRequest req, Model model) {
		
		ser.confirmDecide(req, model);
		ser.decide(req, model);
		
		return "/store/decide";
	}
	//메니저 결산목록
	@RequestMapping("/manager/total")
	public String total(HttpServletRequest req, Model model) {
		
		ser.viewChart(req, model);
		
		return"/store/total";
	}
	//바로사기
	@RequestMapping("/member/nowBuy")
	public String nowBuy(HttpServletRequest req, Model model) {
		
		ser.nowBuy(req, model);
		
		return"/store/goodsInfo";
	}
	//회원 환불 리스트
	@RequestMapping("/member/refund")
	public String refund(HttpServletRequest req, Model model) {
		
		ser.refundList(req, model);
		
		return "/store/refund";
	}
	//회원 환불 진행
	@RequestMapping("/member/confirmRefund")
	public String confirmRefunds(HttpServletRequest req, Model model) {
		
		ser.confirmRefund(req, model);
		
		return"/store/refund";
	}
	//장바구니 삭제
	@RequestMapping("/member/deleteRefund")
	public String deleteRefund(HttpServletRequest req, Model model) {
		
		ser.refundDelete(req, model);
		
		return"/store/refund";
	}
	
}
