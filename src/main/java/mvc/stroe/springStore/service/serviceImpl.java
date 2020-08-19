package mvc.stroe.springStore.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import mvc.stroe.springStore.dao.DaoInterface;
import mvc.stroe.springStore.dto.board;
import mvc.stroe.springStore.dto.memberInfo;
import mvc.stroe.springStore.dto.shoesInfo;

@Service
public class serviceImpl implements serviceIntreface {
	
	@Autowired
	DaoInterface daoo;
	
	@Autowired 
    BCryptPasswordEncoder passwordEncoder;  // 비밀번호 암호화 객체
	
	
	//화면에 뿌려주기!
	@Override
	public void bestAndRecently(HttpServletRequest req, Model model) {
		
		ArrayList<shoesInfo> best =daoo.best();
		
		ArrayList<shoesInfo> recently =daoo.recently();
		
		model.addAttribute("best", best);
		model.addAttribute("recently",recently);
		
	}
	
	@Override
	//아이디 체크 서비스
	public void idconfirm(HttpServletRequest req, Model model) {
		//아이디 받아오고
		String memId=req.getParameter("memId");
		
		int result =daoo.idconfirm(memId);
		System.out.println(result);
		//값 셋팅
		
		model.addAttribute("id", memId);
		model.addAttribute("result", result);
	}
	//랜덤키 생성
	@Override
	public void randomKey(HttpServletRequest req, Model model) {
		String key=daoo.randomkey();
		model.addAttribute("key", key);
	}
	//인증 이메일 보내기
	@Override
	public void sendEmail(HttpServletRequest req, Model model) {
		
		String key=req.getParameter("key");
		String toEmail=req.getParameter("email");
		daoo.sendMail(toEmail, key);
		
	}
	
	//회원가입 처리
	@Override
	public void joinMem(HttpServletRequest req, Model model) {
			String memId=req.getParameter("memId");
			String memPwd=req.getParameter("memPwd");
			
			String address1=req.getParameter("address1");
			String address2=req.getParameter("address2");
			String address3=req.getParameter("address3");
			
			String name=req.getParameter("name");
			String jumin1=req.getParameter("jumin1");
			String jumin2=req.getParameter("jumin2");
			
			String hp1=req.getParameter("hp1");
			String hp2=req.getParameter("hp2");
			String hp3=req.getParameter("hp3");
			String hp=hp1+"-"+hp2+"-"+hp3;
			
			
			String email1=req.getParameter("email1");
			String eamil2=req.getParameter("email2");
			String email=email1+"@"+eamil2;
					
			memberInfo mem = new memberInfo();
			
			mem.setMemId(memId);
			
			String encodingpassword=passwordEncoder.encode(memPwd);
			mem.setMemPwd(encodingpassword);
			mem.setAddress1(address1);
			mem.setAddress2(address2);
			mem.setAddress3(address3);
			mem.setName(name);
			mem.setJumin1(jumin1);
			mem.setJumin2(jumin2);
			mem.setHp(hp);
			mem.setEmail(email);
			mem.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			int result=daoo.joinMem(mem);
			
			model.addAttribute("result", result);
	}
	//회원정보 변경 서비스
	@Override
	public void modifyForm(HttpServletRequest req, Model model) {
		//회원정보 체크(아이디&패스워드)
		String memId=(String)req.getSession().getAttribute("sessId");
		String memPwd=req.getParameter("memPwd");
		
		//화면에 뿌려줄 멤버정보
		memberInfo memInfo=null;
		
		//입력 비밀번호 확인
		int result=0;
		String pwd=daoo.passwordChk(memId);
		System.out.println(pwd);
		boolean pass=passwordEncoder.matches(memPwd, pwd);
		//암호화전 password, 암호화된password(DB에있는)
		if(pass) {
			//비밀번호 일치
			memInfo=new memberInfo();
			memInfo=daoo.modifyForm(memId);
			memInfo.setMemPwd("");
			result=1;
			
		}else {
			//비밀번호 불일치
			result=0;
		}
		//화면에 뿌려줄 값 셋팅
		
		model.addAttribute("mem", memInfo);
		model.addAttribute("result", result);
	}
	//회원정보 수정 프로세스
	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		
		//변경처리할 값 처리
		String memId=(String)req.getSession().getAttribute("sessId");
		String memPwd=req.getParameter("memPwd");
		
		String address1=req.getParameter("address1");
		String address2=req.getParameter("address2");
		String address3=req.getParameter("address3");
		
		String hp1=req.getParameter("hp1");
		String hp2=req.getParameter("hp2");
		String hp3=req.getParameter("hp3");
		String hp=hp1+"-"+hp2+"-"+hp3;
		
		String email1=req.getParameter("email1");
		String email2=req.getParameter("email2");
		String email=email1+"@"+email2;
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("memPwd", passwordEncoder.encode(memPwd));
		map.put("address1",address1);
		map.put("address2",address2);
		map.put("address3",address3);
		
		map.put("hp", hp);
		map.put("email", email);
		
		int result=daoo.modifyPro(map);
		model.addAttribute("result", result);
		
	}
	//회원탈퇴 프로세스
	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		String memId=(String)req.getSession().getAttribute("sessId");
		String memPwd=req.getParameter("memPwd");
		int result=0;
		//DDL문 성공확인
		
		//비밀번호 체크
		String pwd=daoo.passwordChk(memId);
		boolean pass=passwordEncoder.matches(memPwd, pwd);
		//입력한값과 암호화되어 DB에 있는 값이 일치한다면 TRUE 
		
		if(pass) {
			//일치하면 지우고 세션에서 지우고 DB에서 제거
			result=daoo.deletePro(memId);
			req.getSession().invalidate();
			result=1;
			
		}
		model.addAttribute("result", result);
		
	}
	//아이디찾기 서비스
	@Override
	public void searchIdPro(HttpServletRequest req, Model model) {
		String email1=req.getParameter("email1");
		String email2=req.getParameter("email2");
		String email=email1+"@"+email2;
			String memId=daoo.searchIdPro(email);
			
			if(!memId.equals("")) {
				model.addAttribute("memId", memId);
			}else {
				model.addAttribute("memId", "notF");
			}
	}
	//비밀번호 찾기 서비스(비밀번호를 변경해야한다)
	@Override
	public void searchPwdPro(HttpServletRequest req, Model model) {
		String memId=req.getParameter("memId");
		
		String newPwd=daoo.randomkey();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("memId",memId);
		String encode=passwordEncoder.encode(newPwd);
		map.put("newPwd",encode);
		
		int memPwd=daoo.searchPwdPro(map);
		
		//이메일 아이디가 맞으면 비밀번호 재생성하여 그값을 뿌려주자.
		if(memPwd==1) {
			model.addAttribute("memPwd",newPwd);
		}else {
			model.addAttribute("memPwd","notF");
		}
		
	}
	//관리자 로그인
	@Override
	public void loginManager(HttpServletRequest req, Model model) {
			
			String hostId=req.getParameter("hostId");
			
			String hostPwd=req.getParameter("hostPwd");
			
			int result=daoo.managerLogin(hostId, hostPwd);
			if(result==1) {
				//로그인성공
				req.getSession().setAttribute("hostId", hostId);
				System.out.println(hostId);
			}
			model.addAttribute("result",result);
	}
	//게시글
	@Override
	public void boardList(HttpServletRequest req, Model model) {
		ArrayList<board> vo=null;
		ArrayList<board> news=null;
		
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
		
		////검색어////
		String word=req.getParameter("word");
			if(word==null||word.equals("")) {
				word="";
			}
		
		String a=daoo.boardTotal(word);
		total=Integer.parseInt(a);							//전체글수
		nowPage=Integer.parseInt(curPage);					//현제페이지
		
		///////////게시물//////////////////////////////////////////////////////////////////////
		maxList=5;											//한 페이지에 뿌려 줄 글수
		startList=(nowPage-1)*maxList+1;					//페이지당 게시글시작 번호
		endList=startList+maxList-1;						//페이지당 게시글 끝 번호
		number=total-(nowPage-1)*maxList;					//형식상 보여줄 글번호(jsp단에서 게시물이 소환 될때마다 -1해주면된다)
		//////////////////////////////////////////////////////////////////////////////////////
		
		///////////블록////////////////////////////////////////////////////////////////////////
		totalBlock=(total/maxList)+(total%maxList>0?1:0);	//전체페이지블록

		maxBlock=4;											//한번에 보여즐 페이지 블록
		
		startBlock=(nowPage/maxBlock)*maxBlock+1;			//시작하는 페이지 블록
		if(nowPage%maxBlock==0)startBlock=startBlock-maxBlock;	//->현제페이지가 한번에 보여즐 블록의 숫자와 같을경우 블록을 넘기지 않도록
		
		endBlock=startBlock+maxBlock-1;						//끝나는 페이지 블록 
		if(endBlock>totalBlock)endBlock=totalBlock;			//끝나는 페이지가 총블록의 겟수를 넘기지 못하도록한다.
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
			//일반글
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startList", startList);
			map.put("endList", endList);
			map.put("word", word);
			vo=daoo.boardList(map);
			//검색어 볼드값 주기
			for(int i=0; i<vo.size(); i++) {
				String writer=vo.get(i).getWriter().replace(word, "<b>"+word+"</b>");
				vo.get(i).setWriter(writer);
				String subject=vo.get(i).getSubject().replace(word, "<b>"+word+"</b>");
				vo.get(i).setSubject(subject);
			}
			model.addAttribute("vo", vo);
			
			//공지사항
			news=daoo.newsList();
			model.addAttribute("news", news);
			//검색단어
			model.addAttribute("word", word);
		}
		
	}
	//조회수 증가
	@Override
	public void viewCnt(HttpServletRequest req, Model model) {
		//클릭한 게시글의 고유 num	
		int num=Integer.parseInt(req.getParameter("num"));
		daoo.viewCnt(num);
		
	}
	//게시글 상세정보
	@Override
	public void viewContent(HttpServletRequest req, Model model) {
		
		//값받기
		int num=Integer.parseInt(req.getParameter("num"));
		String nowPage=req.getParameter("nowPage");
		String number=req.getParameter("number");
		String word=req.getParameter("word");
		
		board vo=daoo.viewContent(num);
		
		//값 뿌리기
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("number", number);
		model.addAttribute("num", num);
		model.addAttribute("vo", vo);
		model.addAttribute("word", word);
		
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
	public void deleteContentPro(HttpServletRequest req, Model model) {
		String nowPage=req.getParameter("nowPage");
		int num=Integer.parseInt(req.getParameter("num"));
		
		int result =daoo.deleteContentPro(num);
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("result", result);
		
	}
	//게시글 등록 프로세스
	@Override
	public void writePro(HttpServletRequest req, Model model) {
			int num=Integer.parseInt(req.getParameter("num"));
			int ref=Integer.parseInt(req.getParameter("ref"));
			int ref_step=Integer.parseInt(req.getParameter("ref_step"));
			int ref_level=Integer.parseInt(req.getParameter("ref_level"));
			String writer=req.getParameter("writer");
			String subject=req.getParameter("subject");
			String contents=req.getParameter("content");
			String select=req.getParameter("select");
			String nowPage=req.getParameter("nowPage");
			board vo =new board();
			
			vo.setNum(num);
			vo.setRef(ref);
			vo.setRef_level(ref_level);
			vo.setRef_step(ref_step);
			vo.setWriter(writer);
			vo.setContents(contents);
			vo.setSubject(subject);
			vo.setNews(select);
			vo.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			int result=daoo.writePro(vo);
			model.addAttribute("nowPage",nowPage); 
			model.addAttribute("result", result);
			
	}
	//카트 등록 
	@Override
	public void regCart(HttpServletRequest req, Model model) {
		int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
		int count=Integer.parseInt(req.getParameter("count"));
		String memId = req.getParameter("memId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("shoesNumber", shoesNumber);
		map.put("count", count);
		int result=daoo.regCart(map);
		
		model.addAttribute("result", result);
	}
	
	//카트 목록보기(카트목록 총갯수 같이보내기)
	@Override	
	public void cartList(HttpServletRequest req, Model model) {
		String memId=(String)req.getSession().getAttribute("sessId");
		
		ArrayList<shoesInfo> vo=daoo.cartList(memId);
		int total=Integer.parseInt(daoo.totalCart(memId));
		model.addAttribute("total",total);
		model.addAttribute("vo", vo);
	}
	
	//카트지우기
	@Override
	public void deleteCart(HttpServletRequest req, Model model) {
		//파라미터가 배열의 경우(키=값&키=값 일경우 키값이 배열명이된다) 배열로 받으면된다
		String[] deleteList=req.getParameterValues("deleteList");
		String cNum="";
		int result=0;
		for(int i =0; i<deleteList.length; i++) {
			cNum=deleteList[i];
			result=daoo.deleteCart(cNum);
		}
		
		model.addAttribute("result", result);

		
	}
	//카트수정 정보 서비스
	@Override
	public void modifyCart(HttpServletRequest req, Model model) {
			String memId=req.getParameter("memId");
			int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("memId", memId);
			map.put("shoesNumber", shoesNumber);
			shoesInfo vo=daoo.modifyCartRef(map);
			
			model.addAttribute("vo", vo);
			
	}
	//카트 정보수정 프로세스
	@Override
	public void cartModifyPro(HttpServletRequest req, Model model) {
		String memId =(String)req.getSession().getAttribute("sessId");
		int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
		int cCount=Integer.parseInt(req.getParameter("cCount"));
		Map<String, Object>map =new HashMap<String, Object>();
		
		map.put("memId", memId);
		map.put("shoesNumber", shoesNumber);
		map.put("cCount", cCount);
		int result = daoo.modifyCartPro(map);
		
		model.addAttribute("result", result);
		
	}
	//카트 주문확정
	@Override
	public void confirmCart(HttpServletRequest req, Model model) {
		String[] cNumList=req.getParameterValues("cNum");
		int cNum=0;
		int cResult=1;
		
		try {
			for(int i=0; i<cNumList.length; i++) {
				cNum=Integer.parseInt(cNumList[i]);
				daoo.confirmCart(cNum);
			}
		}catch (Exception e) {
		
		}finally {
			
		}
		model.addAttribute("cResult", cResult);
	}
	//주문리스트 보기(회원)
	@Override
	public void buyList(HttpServletRequest req, Model model) {
		String memId=(String)req.getSession().getAttribute("sessId");
		Map<String, Object > map =new HashMap<String, Object> ();
		map.put("memId",memId);
		map.put("req", req);
		
		ArrayList<shoesInfo> vo= daoo.buyList(map);
		
		model.addAttribute("vo", vo);
	}
	//주문리스트에서 삭제
	@Override
	public void deleteBuyList(HttpServletRequest req, Model model) {
		String[] deleteBuyList=req.getParameterValues("deleteBuyList");
		
			int result=0;
			for(int i=0; i<deleteBuyList.length;i++) {
				int oNum=Integer.parseInt(deleteBuyList[i]);
				result=daoo.deleteBuyList(oNum);
			}
			
		model.addAttribute("result", result);
		
	}
	//주문 확정 리스트(매니저)
	@Override
	public void decide(HttpServletRequest req, Model model) {
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
		
		
		total=Integer.parseInt(daoo.totalDecide());							//전체글수
		
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
			
			/* ArrayList<shoesInfo> vo=daoo.stockList(startList, endList); */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startList", startList);
			map.put("endList", endList);
			ArrayList<shoesInfo> vo=daoo.decide(map);
			model.addAttribute("vo", vo);
			model.addAttribute("total",total);
		}
		
		
			
	}
	//매니저용 주문확정리스트 삭제
	@Override
	public void deleteDecideList(HttpServletRequest req, Model model) {
			String[] deleteDecideList=req.getParameterValues("deleteDecideList");
			int result=0;
			for(int i=0; i<deleteDecideList.length;i++) {
				String oNum=deleteDecideList[i];
				result=daoo.deleteDecideList(oNum);
			}
			model.addAttribute("result", result);
	}
	//매니저용 주문확정 확정
	@Override
	public void confirmDecide(HttpServletRequest req, Model model) {
		String[] confirmDecide=req.getParameterValues("confirmDecide");
		int dresult=0;
		for(int i =0; i<confirmDecide.length; i++) {
			String oNum=confirmDecide[i];
			dresult=daoo.confirmDecide(oNum);
		}
		model.addAttribute("dResult", dresult);
	}
	//회원용 바로사기
	@Override
	public void nowBuy(HttpServletRequest req, Model model) {
		
		int count=Integer.parseInt(req.getParameter("count"));
		int shoesNumber=Integer.parseInt(req.getParameter("shoesNumber"));
		String memId=(String)req.getSession().getAttribute("sessId");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("shoesNumber", shoesNumber);
		map.put("count",count );
		int result=daoo.nowBuy(map);
		
		model.addAttribute("result", result);
		
	}
	//환불 확정
	@Override
	public void confirmRefund(HttpServletRequest req, Model model) {
		String[] check=req.getParameterValues("box");
		String[] count=req.getParameterValues("count");
		String[] shoesNumber=req.getParameterValues("shoesNumber");
		
		ArrayList<shoesInfo> list =new ArrayList<shoesInfo>();
		
		shoesInfo vo= new shoesInfo();
		for(int i=0; i<check.length; i++) {
			vo.setShoesNumber(Integer.parseInt(shoesNumber[i]));
			vo.setpNum(Integer.parseInt(check[i]));
			vo.setpCount(Integer.parseInt(count[i]));
			list.add(vo);
		}
		
		int result=daoo.confirmRefund(list);
		model.addAttribute("result", result);
		
	}
	//환불목록 지우기 환불 리스트에 상태값을 주어 환불목록에서 안보이게 한다(0이면 환불 목록에서 안보이게)
	@Override
	public void refundDelete(HttpServletRequest req, Model model) {
		String[] pNumList =req.getParameterValues("pNum");
		int dResult=0;
		for(int i=0; i<pNumList.length; i++) {
			String pNum=pNumList[i];
			dResult=daoo.deleteRefund(pNum);
		}
		model.addAttribute("dResult", dResult);
		
	}
	//환불목록 보이기(목록에 상태값을 주어 화면에  if 체크로 환불 목록에서 지워진 상태값 0이 아닌 리스트만 뿌린다.)
	@Override
	public void refundList(HttpServletRequest req, Model model) {
		String memId=(String)req.getSession().getAttribute("sessId");
		ArrayList<shoesInfo> vo=daoo.refundList(memId);
		String total=daoo.totalRefund(memId);
		model.addAttribute("total",total);
		model.addAttribute("vo",vo);
	}
	//결산
	@Override
	public void viewChart(HttpServletRequest req, Model model) {
		
		
		String sD=req.getParameter("startMonth");
		String eD=req.getParameter("endMonth");
		Map<String, Object>map=new HashMap<String, Object>();
		
		
		map.put("sD", sD+"01");
		map.put("eD", eD+"31");
		map.put("req",req);
		
		ArrayList<JSONArray> totalJson=daoo.chart(map);
		System.out.println(totalJson);
		model.addAttribute("totalJson", totalJson);
	}
}
