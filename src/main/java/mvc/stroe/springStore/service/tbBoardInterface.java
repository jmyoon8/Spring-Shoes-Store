package mvc.stroe.springStore.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface tbBoardInterface {
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
	//제고삭제
	public void deleteStockPro(HttpServletRequest req, Model model);
	//제고등록
	public void insertStock(MultipartHttpServletRequest req, Model model);
	//제고수정
	public void modifyStock(MultipartHttpServletRequest req, Model model);
	//제고정보 참조 조회
	public void modifyRef(HttpServletRequest req, Model model);
	//제품 상세 페이지
	public void goodsInfo(HttpServletRequest req, Model model);

}
