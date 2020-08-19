//package mvc.stroe.springStore.control;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.ui.Model;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import mvc.stroe.springStore.service.serviceTb;
//
//@WebServlet("*.tb")
////매니저 요청 -> host
////고객요청->cus
//public class controller_tb extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public controller_tb() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, Model model)
//			throws ServletException, IOException {
//		action(request,model);
//	}
//
//	protected void doPost(HttpServletRequest request, Model model)
//			throws ServletException, IOException {
//		doGet(request, model);
//	}
//	public void action(HttpServletRequest req, Model model)
//			throws ServletException, IOException{
//		req.setCharacterEncoding("UTF-8");
//		String path="";
//		
//		String uri=req.getRequestURI();
//		String context=req.getContextPath();
//		String url=uri.substring(context.length());
//		serviceTb serTb = new serviceTb();
//		//게시판에 글뿌리기
//		if(url.equals("/board.tb")||url.equals("/*.tb")) {
//			System.out.println("url=>/*.tb");
//			
//			serTb.boardList(req, model);
//			
//			path="/store/tb_board.jsp";
//		//게시판 보기
//		}else if(url.equals("/content.tb")) {
//			System.out.println("url=>/write.tb");
//			//조회수 증가
//			serTb.viewCnt(req, model);
//			//게시글 정보
//			serTb.viewContent(req, model);
//			path="/store/content.jsp";
//			
//		//게시글 상세정보
//		}else if(url.equals("/modifyContent.tb")) {
//			System.out.println("url=>/modifyContent.tb");
//			
//			serTb.modifyContent(req, model);
//			 
//			path="/store/modifyContent.jsp";
//		//제고 수정 프로세스
//		}else if(url.equals("/modifyContentPro.tb")) {
//			System.out.println("url=>/modifyContentPro.tb");
//			
//			serTb.modifyContentPro(req, model);
//			
//			path="/store/modifyContent.jsp";
//		//제고 삭제 페이지
//		}else if(url.equals("/deleteStock.tb")) {
//			System.out.println("url=/deleteStock.tb");
//			
//			
//			path="/store/tb_deleteStockPro.jsp";
//		//제고삭제 프로세스
//		}else if(url.equals("/deletePro.tb")) {
//			System.out.println("url=>deletePro.tb");
//			
//			serTb.deleteStockPro(req, model);
//			
//			path="/store/tb_deleteStockPro.jsp";
//		//재고등록 페이지
//		}else if(url.equals("/write.tb")) {
//			System.out.println("url=>write.tb");
//			
//			
//			path="/store/Tb_write.jsp";
//		//제고등록프로세스
//		}else if(url.equals("/insertStock.tb")) {
//			System.out.println();

//			MultipartHttpServletRequest req1 = null;
//			serTb.insertStock(req1, model);
//			
//			path="/store/tb_write.jsp";
//		//제고수정
//		}else if(url.equals("/modifyStock.tb")) {
//			System.out.println("url=>/modifyStock.tb");
//			
//			path="/store/tb_modifyStock.jsp";
//		//제고정보 조회
//		}else if(url.equals("/modifyRef.tb")) {
//			System.out.println("url=>/modifyRef.tb");
//			
//			serTb.modifyRef(req, model);
//			
//			path="/store/tb_modifyStock.jsp";
//		//제고수정 프로세스
//		}else if(url.equals("/modifystock.tb")) {

//			System.out.println("url=>/modifystock.tb");

//			MultipartHttpServletRequest req2 = null;
//			serTb.modifyStock(req2, model);
//			
//			path="/store/tb_modifyStock.jsp";
//		//제품 상세페이지
//		}else if(url.equals("/goodsInfo.tb")) {
//			System.out.println("url=>/goodsInfo.tb");
//			
//			serTb.goodsInfo(req, model);
//			
//			path="/store/goodsInfo.jsp";
//		}
//			
////		RequestDispatcher dispatcher=req.getRequestDispatcher(path);
////		dispatcher.forward(req, model);
//	}
//}
