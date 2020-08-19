//package mvc.stroe.springStore.control;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import mvc.stroe.springStore.service.serviceImpl;
//import mvc.stroe.springStore.service.serviceIntreface;
//
//
//@WebServlet("*.cus")
////매니저 요청 -> host
////고객요청->cus
//public class controller_customer extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public controller_customer() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		action(request,response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//	public void action(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException{
//		serviceIntreface ser = new serviceImpl();   
//		req.setCharacterEncoding("UTF-8");
//		String path="";
//		
//		String uri=req.getRequestURI();
//		String context=req.getContextPath();
//		String url=uri.substring(context.length());
//		//메인페이지--완료
//		if(url.equals("/main.cus")||url.equals("/*.cus")) {
//			System.out.println("/*.cus");
//			
//			ser.bestAndRecently(req, res);
//			
//			path="/store/main.jsp";
//		//회원가입요청페이지이동--완료
//		}else if(url.equals("/join.cus")) {
//			System.out.println("url=>/join.cus");
//			
//			//이메일 체크용 랜덤키 생성서비스
//			ser.randomKey(req, res);
//			
//			path="/store/join.jsp";
//		//이메일 체크--완료
//		}else if(url.equals("/sendKey.cus")) {
//			System.out.println("url==>sendKey.cus");
//			ser.sendEmail(req, res);
//			path="/store/email.jsp";
//		//아이디중복 체크--완료
//		}else if(url.equals("/confirmId.cus")) {
//			System.out.println("url=>/confirmId.cus");
//			
//			ser.idCheck(req, res);
//			
//			path="/store/confirmId.jsp";
//			
//		//회원가입 처리--완료
//		}else if(url.equals("/joinPro.cus")) {
//			System.out.println("url=>/joinPro.cus");
//			
//			ser.joinMem(req, res);
//			
//			path="/store/joinPro.jsp";
//		//로그인화면 전환--완료
//		}else if(url.equals("/login.cus")) {
//			System.out.println("url=>/login.cus");
//			
//			
//			
//			path="/store/login.jsp";
//		//로그인 처리--완료
//		}else if(url.equals("/loginPro.cus")) {
//			System.out.println("url=>/loginPro.cus");
//			
//			ser.loginPro(req, res);
//			
//			path="/store/loginPro.jsp";
//		//로그아웃 처리--완료
//		}else if(url.equals("/logOut.cus")) {
//			System.out.println("url=>/logOut.cus");
//			
//			//jsp에서 스크립트릿으로 세션지우고 빠꾸하자
//			
//			path="/store/logOut.jsp";
//		//회원정보 변경용 비밀번호 창띄우기--완료
//		}else if(url.equals("/pwdCheck_modify.cus")){
//			System.out.println("url=>/pwdCheck.cus");
//			
//			path="/store/pwdCheck_modify.jsp";
//		//회원 정보 변경 폼--완료
//		}else if(url.equals("/modifyForm.cus")) {
//			System.out.println("url=>/modifyForm.cus");
//			
//			ser.modifyForm(req, res);
//			
//			path="/store/modifyForm.jsp";
//		//회원정보 변경 프로세스--완료
//		}else if (url.equals("/modifyPro.cus")) {
//			System.out.println("url=>/modifyConfirm.cus");
//			
//			ser.modifyPro(req, res);
//			
//			path="/store/modifyPro.jsp";
//		//회원탈퇴용 비밀번호 체크창-----완료
//		}else if(url.equals("/pwdCheck_delete.cus")) {
//			System.out.println("url=>/pwdCheck_delete.cus");
//			
//			path="/store/pwdCheck_delete.jsp";
//		//회원탈퇴 프로세스--완료
//		}else if(url.equals("/deletePro.cus")) {
//			System.out.println("url=>/deletePro.cus");
//			
//			ser.deletePro(req, res);
//			
//			path="/store/deletePro.jsp";

//		//아이디찾기--완료
//		}else if(url.equals("/searchId.cus")) {
//			System.out.println("url=>/searchId.cus");
//			
//			path="/store/searchId.jsp";
//		//아이디찾기 프로세스--완료
//		}else if(url.equals("/searchIdPro.cus")) {
//			System.out.println("url=>/searchIdPro.cus");
//			
//			ser.searchIdPro(req, res);
//			
//			path="/store/searchId.jsp";
//		//비밀번호 찾기--완료
//		}else if(url.equals("/searchPwd.cus")) {
//			System.out.println("url=/searchPwd.cus");
//			
//			ser.randomKey(req, res);
//			
//			path="/store/searchPwd.jsp";
//		//비밀번호찾기 프로새스--완료
//		}else if(url.equals("/searchPwdPro.cus")) {
//			System.out.println("url=>/searchPwdPro.cus");
//			
//			ser.searchPwdPro(req, res);
//			
//			path="/store/searchPwd.jsp";
//		//매니저로그인 패이지--완료
//		}else if(url.equals("/managerLogIn.cus")) {
//			System.out.println("url=>/managerLogIn.cus");
//			
//			path="/store/managerLogin.jsp";
//		//매니저 로그인 프로세스--완료
//		}else if(url.equals("/managerLoginPro.cus")) {
//			System.out.println("url=>/managerLoginPro.cus");
//			
//			ser.loginManager(req, res);
//			
//			path="/store/managerLoginPro.jsp";
//		//매니저 로그아웃--완료
//		}else if(url.equals("/managerlogOut.cus")) {
//			System.out.println("url=>managerlogOut.cus");
//			//메인에 뿌려준 리스트
//			ser.bestAndRecently(req, res);
//			//세션값만 지워주면 로그아웃 성공
//			req.getSession().invalidate();			
//			path="/store/main.jsp";

//		//장바구니 이동
//		}else if(url.equals("/cart.cus")) {
//			System.out.println("url=>/cart.cus");
//			
//			ser.cartList(req, res);
//			
//			path="/store/cart.jsp";
//			
//		//장바구니에 등록
//		}else if(url.equals("/regCart.cus")) {
//			System.out.println("url=>/regCart.cus");
//			
//			ser.regCart(req, res);
//			
//			path="/store/regCart.jsp";
//		//장바구니 삭제
//		}else if(url.equals("/deleteCart.cus")) {
//			System.out.println("url=>deleteCart.cus");
//			
//			ser.deleteCart(req, res);
//			
//			ser.cartList(req, res);
//			
//			path="/store/cart.jsp";
//		//장바구니 수정 페이지 띄우기
//		}else if(url.equals("/modifyCart.cus")) {
//			System.out.println("url=>/modifyCart.cus");
//			
//			path="/store/modifyCart.jsp";
//		//장바구니 품목 조회
//		}else if(url.equals("/modifyCartRef.cus")) {
//			System.out.println("url=>/modifyCartRef.cus");
//			
//			ser.modifyCart(req, res);
//			
//			path="/store/modifyCart.jsp";
//		//장바구니 수정 프로세스
//		}else if(url.equals("/cartModifyPro.cus")) {
//			System.out.println("url=>/modifyCartConfirm.cus");
//			
//			ser.cartModifyPro(req, res);
//			
//			path="/store/modifyCart.jsp";
//		//주문확정
//		}else if(url.equals("/confirmList.cus")) {
//			System.out.println("url=>/confirmList.cus");
//			
//			ser.confirmCart(req, res);
//			
//			path="/store/cart.jsp";
//		//바로사기
//		}else if( url.equals("/nowBuy.cus")) {
//			System.out.println("url=>nowBuy.cus");
//			
//			ser.nowBuy(req, res);
//			
//			path="/store/goodsInfo.jsp";
//		//주문 리스트
//		}else if(url.equals("/buyList.cus")){
//			System.out.println("url=>/buyList.cus");
//			
//			ser.buyList(req, res);
//			
//			path="/store/buyList.jsp";
//		//주문리스트 삭제
//		}else if(url.equals("/deleteBuyList.cus")) {
//			System.out.println("url=>/deleteBuyList.cus");
//			
//			ser.deleteBuyList(req, res);
//			
//			path="/store/buyList.jsp";
//		//매니저 주문 확정 페이지
//		}else if(url.equals("/decide.cus")) {
//			System.out.println("url=>/decide.cus");
//			
//			ser.decide(req, res);
//			
//			path="/store/decide.jsp";
//		//매니저용 주문확정 리스트 삭제
//		}else if(url.equals("/deleteDecideList.cus")) {
//			System.out.println("url=>/deleteDecideList");
//			
//			ser.deleteDecideList(req, res);
//			
//			path="/store/decide.jsp";
//		//매니저용 주문확정 리스트 확정!
//		}else if(url.equals("/confirmDecide.cus")) {
//			System.out.println("url=>/confirmDecide.cus");
//			
//			ser.confirmDecide(req, res);
//			
//			path="/store/decide.jsp";
//		//매니저용 결산
//		}else if(url.equals("/total.cus")) {
//			System.out.println("url=>/total.cus");
//			
//			ser.viewChart(req, res);
//			
//			path="/store/total.jsp";
//			
//		//환불목록
//		}else if(url.equals("/refund.cus")) {
//			System.out.println("url=>/refund.cus");
//			
//			ser.refundList(req, res);
//			
//			path="/store/refund.jsp";
//		//환불목록 확정
//		}else if(url.equals("/confirmRefund.cus")) {
//			System.out.println("url=>/confirmRefund.cus");
//			
//			ser.confirmRefund(req, res);
//			 
//			path="/store/refund.jsp";
//		//환불목록 삭제(감추기)
//		}else if(url.equals("/deleteRefund.cus")) {
//			System.out.println("url=>/deleteConfirm.cus");
//			
//			ser.refundDelete(req, res);
//			
//			path="/store/refund.jsp";
//		}
//		
//		RequestDispatcher dispatcher=req.getRequestDispatcher(path);
//		dispatcher.forward(req, res);
//	}
//}
