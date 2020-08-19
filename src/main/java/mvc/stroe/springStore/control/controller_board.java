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
//
//@WebServlet("*.bo")
////매니저 요청 -> host
////고객요청->cus
//public class controller_board extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public controller_board() {
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
//		req.setCharacterEncoding("UTF-8");
//		String path="";
//		
//		String uri=req.getRequestURI();
//		String context=req.getContextPath();
//		String url=uri.substring(context.length());
//		
//		serviceImpl ser =new serviceImpl();
//		//게시판에 글뿌리기
//		if(url.equals("/board.bo")||url.equals("/*.bo")) {
//			System.out.println("url=>/*.bo");
//			
//			ser.boardList(req, res);
//			
//			path="/store/board.jsp";

//		//게시판 보기
//		}else if(url.equals("/content.bo")) {
//			System.out.println("url=>/write.bo");
//			//조회수 증가
//			ser.viewCnt(req, res);
//			//게시글 정보
//			ser.viewContent(req, res);
//			path="/store/content.jsp";
//			
//		//게시글 상세정보
//		}else if(url.equals("/modifyContent.bo")) {
//			System.out.println("url=>/modifyContent.bo");
//			
//			ser.modifyContent(req, res);
//			 
//			path="/store/modifyContent.jsp";
//		//게시글 수정 프로세스
//		}else if(url.equals("/modifyContentPro.bo")) {
//			System.out.println("url=>/modifyContentPro.bo");
//			
//			ser.modifyContentPro(req, res);
//			
//			path="/store/modifyContent.jsp";
//		//게시글 삭제 프로세스
//		}else if(url.equals("/deleteContent.bo")) {
//			System.out.println("url=/deleteContent.bo");
//			
//			ser.deleteContentPro(req, res);
//			
//			path="/store/deleteContentPro.jsp";
//		//게시글 등록 페이지 이동
//		}else if(url.equals("/write.bo")) {
//			System.out.println("url=>write.bo");
//			
//			
//			path="/store/write.jsp";
//		//게시글 등록 프로세스
//		}else if(url.equals("/writePro.bo")) {
//			System.out.println();
//			
//			ser.writePro(req, res);
//			
//			path="/store/write.jsp";
//		}
//			
//		RequestDispatcher dispatcher=req.getRequestDispatcher(path);
//		dispatcher.forward(req, res);
//	}
//}
