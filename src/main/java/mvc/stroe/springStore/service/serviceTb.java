package mvc.stroe.springStore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import mvc.stroe.springStore.dao.DaoInterface;
import mvc.stroe.springStore.dao.dao;
import mvc.stroe.springStore.dto.board;
import mvc.stroe.springStore.dto.shoesInfo;

@Service
public class serviceTb implements tbBoardInterface {
	
	@Autowired
	DaoInterface daoo;
	
	@Autowired 
    BCryptPasswordEncoder passwordEncoder;
	
	//게시글
	@Override
	public void boardList(HttpServletRequest req, Model model) {
		
		String curPage=req.getParameter("nowPage");
		if(curPage==null||curPage.equals("")) {
			curPage="1";
		}
		int nowPage=0;
		int total=0;
		int maxList=0;
		int startList=0;
		int endList=0;
		int number=0;
		int totalBlock=0;
		int startBlock=0;
		int maxBlock=0;
		int endBlock=0;
		
				
		
		nowPage=Integer.parseInt(curPage);					//현제페이지
		String to=daoo.stockTotal();
		total=Integer.parseInt(to);							//전체글수
		
		///////////게시물//////////////////////////////////////////////////////////////////////
		maxList=6;											//한 페이지에 뿌려 줄 글수
		startList=(nowPage-1)*maxList+1;					//페이지당 게시글시작 번호
		endList=startList+maxList-1;						//페이지당 게시글 끝 번호
		number=total-(nowPage-1)*maxList;					//형식상 보여줄 글번호(jsp단에서 게시물이 소환 될때마다 -1해주면된다)
		//////////////////////////////////////////////////////////////////////////////////////
		
		///////////블록////////////////////////////////////////////////////////////////////////
		totalBlock=(total/maxList)+(total%maxList>0?1:0);	//전체페이지블록
		
		maxBlock=5;											//전체페이지 중 한번에 보여즐 페이지 블록
		
		startBlock=(nowPage/maxBlock)*maxBlock+1;			//시작하는 페이지 블록
		if(nowPage%maxBlock==0)startBlock=startBlock-maxBlock;	//->현제페이지가 한번에 보여즐 블록의 숫자와 같을경우 블록을 넘기지 않도록
		
		endBlock=startBlock+maxBlock-1;						//끝나는 페이지 블록 
		if(endBlock>totalBlock)endBlock=totalBlock;				//끝나는 페이지가 총블록의 겟수를 넘기지 못하도록한다.
		///////////////////////////////////////////////////////////////////////////////////////
		
		
		model.addAttribute("total", total);
		model.addAttribute("number", number);
		model.addAttribute("nowPage",nowPage);
		
		if(total>0) {
			model.addAttribute("nowPage",nowPage);
			
			model.addAttribute("startList", startList);
			model.addAttribute("endList", endList);
			
			model.addAttribute("totalBlock", totalBlock);
			model.addAttribute("maxBlock", maxBlock);
			model.addAttribute("startBlock", startBlock);
			model.addAttribute("endBlock", endBlock);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startList", startList);
			map.put("endList", endList);
			
			ArrayList<shoesInfo> vo=daoo.stockList(map);
			
			model.addAttribute("vo", vo);
		}
		
	}
	
	//조회수 증가
	@Override
	public void viewCnt(HttpServletRequest req, Model model) {
		//클릭한 게시글의 고유 num	
		int num=Integer.parseInt(req.getParameter("num"));
		daoo.stockTotal();
		
	}
	//게시글 상세정보
	@Override
	public void viewContent(HttpServletRequest req, Model model) {
		
		//값받기
		int num=Integer.parseInt(req.getParameter("num"));
		String nowPage=req.getParameter("nowPage");
		String number=req.getParameter("number");
		
		board vo=daoo.viewContent(num);
		
		//값 뿌리기
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("number", number);
		model.addAttribute("num", num);
		model.addAttribute("vo", vo);
		
	}
	//게시글 폼 소환
	@Override
	public void modifyContent(HttpServletRequest req, Model model) {
			
			int num=Integer.parseInt(req.getParameter("num"));
			String nowPage=req.getParameter("nowPage");
			
			board vo =daoo.viewContent(num);
			
			model.addAttribute("vo", vo);
			model.addAttribute("num", num);
			model.addAttribute("nowPage", nowPage);
			
	}
	//게시글 수정 프로세스
	@Override
	public void modifyContentPro(HttpServletRequest req, Model model) {
			String nowPage=req.getParameter("nowPage");
			int num=Integer.parseInt(req.getParameter("num"));
			
			board vo = new board();
			
			vo.setNum(num);
			vo.setSubject(req.getParameter("subject"));
			vo.setContents(req.getParameter("content"));
			
			int result=daoo.modifyContentPro(vo);
			
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("result", result);
	}
	//게시글 삭제 프로세스
	@Override
	public void deleteStockPro(HttpServletRequest req, Model model) {
		int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
		int result=daoo.deleteStockPro(shoesNumber);
		model.addAttribute("result", result);
	}
	
	//제고 등록 프로세스
	@Override
	public void insertStock(MultipartHttpServletRequest req, Model model) {
		MultipartFile file=req.getFile("pic");
		System.out.println(file+"ddddddd");
		
		
		//저장경로(C:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_store\store\shoesPic\)
		String saveDir =  req.getSession().getServletContext().getRealPath("/resources/shoesImages/");
		
		// 업로드할 파일이 위치하게될 물리적인 경로
		String realDir = "D:\\dev65\\workspace\\spring_store\\src\\main\\webapp\\resources\\shoesPic";
		
		try {
			file.transferTo(new File(saveDir+file.getOriginalFilename()));
			//업로드된 파일을 캐치한다.
			FileInputStream fis = new FileInputStream(saveDir+file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(realDir+file.getOriginalFilename());
			
			int data=0;
			
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
			
			shoesInfo vo = new shoesInfo();
			vo.setPic(file.getOriginalFilename());
			//get.OriginalFileName은 업로드한 파일명을 뜻한다(name명으로 안불러와도 된다)
			vo.setBrand(req.getParameter("brand"));
			vo.setModelName(req.getParameter("modelName"));
			vo.setPrice(Integer.parseInt(req.getParameter("price")));
			vo.setStockCount(Integer.parseInt(req.getParameter("stockCount")));
			vo.setReg_date(new Timestamp(System.currentTimeMillis()));
			int result=daoo.insertStock(vo);
			
			model.addAttribute("result", result);
			
			} catch (Exception e) {
			e.printStackTrace();
			}
	}
	//제고수정 프로세스
	@Override
	public void modifyStock(MultipartHttpServletRequest req, Model model) {
		
		try {
			MultipartFile file=req.getFile("pic");
			if(!file.getOriginalFilename().equals("")) {
				
				//저장경로(C:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_store\store\shoesPic\)
				String saveDir =  req.getSession().getServletContext().getRealPath("/resources/shoesImages/");
				
				// 업로드할 파일이 위치하게될 물리적인 경로
				String realDir = "D:\\dev65\\workspace\\spring_store\\src\\main\\webapp\\resources\\shoesPic";
				
					file.transferTo(new File(saveDir+file.getOriginalFilename()));
					//업로드된 파일을 캐치한다.
					FileInputStream fis = new FileInputStream(saveDir+file.getOriginalFilename());
					FileOutputStream fos = new FileOutputStream(realDir+file.getOriginalFilename());
					
					int data=0;
					
					while((data=fis.read())!=-1) {
						fos.write(data);
					}
					fis.close();
					fos.close();
					
					//사진변경 값이 있다면 pic으로 사진이름 전달
					shoesInfo vo = new shoesInfo();
					vo.setShoesNumber(Integer.parseInt(req.getParameter("shoesNumber")));
					vo.setPic(file.getOriginalFilename());
					vo.setBrand(req.getParameter("brand"));
					vo.setModelName(req.getParameter("modelName"));
					vo.setPrice(Integer.parseInt(req.getParameter("price")));
					vo.setStockCount(Integer.parseInt(req.getParameter("stockCount")));
					vo.setCategory(req.getParameter("category"));
					int result=daoo.modifyStock(vo);
					model.addAttribute("result", result);
			}else {
				//사진변경 하지 않는다면? 기존의 값인  pic2값만 받아서 다시 넣는다.
				shoesInfo vo = new shoesInfo();
				vo.setShoesNumber(Integer.parseInt(req.getParameter("shoesNumber")));
				vo.setPic(req.getParameter("pic2"));
				//기존에 있던 사진 명을 hidden값으로 줬다.
				vo.setBrand(req.getParameter("brand"));
				vo.setModelName(req.getParameter("modelName"));
				vo.setPrice(Integer.parseInt(req.getParameter("price")));
				vo.setStockCount(Integer.parseInt(req.getParameter("stockCount")));
				
				int result=daoo.modifyStock(vo);
				model.addAttribute("result", result);
			}
				
			
			} catch (Exception e) {
			e.printStackTrace();
			}
				
	}
	
	//제고정보 불러오기(제고수정전에 원래 항목의 값을 보기위해)
	@Override
	public void modifyRef(HttpServletRequest req, Model model) {
			int shoesNumber = Integer.parseInt(req.getParameter("shoesNumber"));
			shoesInfo vo=daoo.modifyRef(shoesNumber);
			model.addAttribute("vo", vo);
	}
	//제품상세페이지
	@Override
	public void goodsInfo(HttpServletRequest req, Model model) {
		int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
		shoesInfo vo =daoo.modifyRef(shoesNumber);
		daoo.cnt(shoesNumber);
		
		String curPage=req.getParameter("nowPage");
		if(curPage==null)curPage="1";
		int nowPage=Integer.parseInt(curPage);
		String number=req.getParameter("number");
		
		
		model.addAttribute("number", number);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("vo", vo);
		
	}
}
