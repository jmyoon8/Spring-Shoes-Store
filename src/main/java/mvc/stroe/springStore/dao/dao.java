package mvc.stroe.springStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mvc.stroe.springStore.dto.Total;
import mvc.stroe.springStore.dto.board;
import mvc.stroe.springStore.dto.memberInfo;
import mvc.stroe.springStore.dto.shoesInfo;

@Repository
public class dao implements DaoInterface {
	
	@Autowired
	SqlSession sqlSession;
	
	//베스트상품(cnt높은순 5개만)
		@Override
		public ArrayList<shoesInfo> best() {

			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			
			return dao.best();
		}
		//최근상품(date 높은순 5개만)
		@Override
		public ArrayList<shoesInfo> recently() {
			
				DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
				return dao.recently();
		}
	
	//이메일 체크용 랜덤키 생성
	@Override
		public String randomkey() {
			StringBuffer temp = new StringBuffer();
			Random random=new Random();
			for(int i = 0; i <6; i++) {
				int rInext = random.nextInt(2);
				if(rInext==0) {
					//A~Z
					temp.append((char)((int) random.nextInt(26))+65);
				}else if(rInext==1) {
					//0~9
					temp.append((random.nextInt(10)));
				}
			}
			String key=temp.toString();
			
			return key;
		}	
	/*- gmail 이용 전송 세팅 
	# gmail -> 환경 설정 -> 전달 및 pop/IMAP -> IMAP액세스 ->IMAP 사용 설정 : 
	# 내 계정 -> 로그인및 보안 -> 연결된 앱 및 사이트 -> 보안 수준이 낮은 앱 허용 : 사용 으로 변경 */
	//이메일 확인용
	@Override
	  public void sendMail(String toEmail, String key){
	    	final String username="jmyoon888@gmail.com";
		    final String password="dbswowls12!";
		    
	        Properties props = new Properties(); 
	        props.put("mail.smtp.user", username); 
	        props.put("mail.smtp.password", password);
	        props.put("mail.smtp.host", "smtp.gmail.com"); 
	        props.put("mail.smtp.port", "25"); 
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.auth", "true"); 
	        props.put("mail.smtp.starttls.enable","true"); 
	        props.put("mail.smtp.EnableSSL.enable","true");
	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	        props.setProperty("mail.smtp.port", "465");   
	        props.setProperty("mail.smtp.socketFactory.port", "465"); 
	    
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
	        protected PasswordAuthentication getPasswordAuthentication() { 
	        return new PasswordAuthentication(username, password); 
	        }});
		        try{
		            Message message = new MimeMessage(session); 
		           
		            message.setFrom(new InternetAddress("admin@eoulim.com"));
		            
		            message.setRecipients(Message.RecipientType.TO,
		            		InternetAddress.parse(toEmail)); 
		    	    
		    	    String content = "앗 신발이 타이어보다 싸다!! 입니다. 이메일 인증 부탁드립니다.! 인증키 : "+key+" 입니다.";
		    	    /* "<a href='http://localhost:8081/store/emailChk.do?key=" + key +"'> 링크 </a>"; // 글 내용
*/		    	    
		            message.setSubject("앗신타싸 회원가입 인증메일"); // 이메일 제목
		            message.setContent(content, "text/html; charset=utf-8");//글내용을 html타입 charset설정
			         
		            System.out.println("send!!!");
		            Transport.send(message); 
		            System.out.println("SEND");
		            
		        } catch(Exception e){
		            e.printStackTrace();
	        	}
	    	}
	

	//로그인확인DAO
	@Override
	public Map<String,String> idCheck(String memId) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			
		return dao.idCheck(memId);
	}
	//아이디 중복확인
	@Override
	public int idconfirm(String memId) {

		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		return dao.idconfirm(memId);
	}
	
	//회원가입
	@Override
	public int joinMem(memberInfo mem) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.joinMem(mem);
	}
	
	//패스워드 체크
	@Override
	public String passwordChk(String memId) {
			
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.passwordChk(memId);
	}
	
	//회원정보 변경 서비스(변경 폼에 뿌릴 회원정보 리턴)
	@Override
	public memberInfo modifyForm(String memId) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		return dao.modifyForm(memId);
	}
	//회원정보 수정 프로세스
	@Override
	public int modifyPro(Map<String, Object> map) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.modifyPro(map);
	}
	//회원탈퇴
	@Override
	public int deletePro(String memId) {
			
		return sqlSession.delete("mvc.stroe.springStore.dao.DaoInterface.deletePro",memId);
	}
	//이메일 체크(아이디찾기용)
	@Override
	public String searchIdPro(String email) {
		
			
		return sqlSession.selectOne("mvc.stroe.springStore.dao.DaoInterface.searchIdPro",email);
	}
	
	//비밀번호 채크
	@Override
	public int pwdChk(Map<String, Object> newPwd) {

		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		return dao.pwdChk(newPwd);
	}
	//새비밀번호 업데이트
	@Override
	public int updatePwd(Map<String, Object> newPwd) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		return dao.updatePwd(newPwd);
	}
	
	//비밀번호찾기 프로세스
	@Override
	public int searchPwdPro(Map<String, Object> newPwd) {
		int result=0;
		
		
		if(pwdChk(newPwd)==1) {
			updatePwd(newPwd);
			result=1;
		}else {
			result=0;
		}
		
		return result;
	}
	
	
	
	//메니저 로그인
	@Override
	public int  managerLogin(String hostId, String hostPwd) {
		return 0;
	}
	//검색 게시글 총 수
	@Override
	public String boardTotal(String word) {
			
		
				DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
	return dao.boardTotal(word);
	}
	
	//게시판 리스트
	@Override
	public ArrayList<board> boardList(Map<String, Object>map) {
				
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
				
		return dao.boardList(map);
		
	}
	//공지사항
	@Override
	public ArrayList<board> newsList() {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		return dao.newsList();
		
	}
	//조회수 증가
	@Override
	public void viewCnt(int num) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		dao.viewCnt(num);
	}
	//게시글 상세정보
	@Override
	public board viewContent(int num) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.viewContent(num);
	}
	
	//게시글 수정
	@Override
	public int modifyContentPro(board vo) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.modifyContentPro(vo);
	}
	//게시글 삭제/////////////////////////////////
	
	//ref,ref_step,ref_level 구하기
	@Override
	public board ref(int num) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.ref(num);
	}
	//max ref_step 구하기
	@Override
	public String maxRef(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.maxRef(map);
	}
	//리플, 하위리플지우기
	@Override
	public int deleteRe(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.deleteRe(map);
	}
	//원글다지우기
	@Override
	public int deleteOri(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.deleteOri(map);
	}
	//글삭제 프로세스
	@Override
	public int deleteContentPro(int num) {
		int result=0;
		
		
		int ref=0;
		int ref_step=0;
		int ref_level=0;
		String max_step="";
				
			//우선 ref값 소환 후 Map에 담기
			Map<String, Object>map= new HashMap<String, Object>();
			
			ref=ref(num).getRef();
			System.out.println(ref);
			
			ref_step=ref(num).getRef_step();
			ref_level=ref(num).getRef_level();

			
			map.put("ref",ref);
			map.put("ref_step",ref_step);
			map.put("ref_level", ref_level);
			
			//자신과 레벨이 같고 step이 자신보다 큰 1번째 값을 구한다.
			max_step=maxRef(map);
			map.put("max_step",max_step);
			
			if(max_step!=null) {
				
				result=deleteRe(map);
				
			}else {
				result=deleteOri(map);
			}
		
		return result;
	}
	
	
	//게시글 등록/////////메소드들/////////////////////////////
	//원본글 인서트
	@Override
	public int original(board vo) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.original(vo);
	}
	//리플달기용 ref밀기
	@Override
	public int pushRef(board vo) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.pushRef(vo);
	}
	//리플달기
	@Override
	public int writeRe(board vo) {

		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.writeRe(vo);
	}
	
	
	
	@Override
	public int writePro(board vo) {
		int result=0;
			//원본글 등록인지 답글등록인지 일단확인하자
				if(vo.getRef()==0) {
					
					//원본글일떄
					result=original(vo);
					//그냥등록
				}else {
					//답글일때
					//글에 달린 답글은 최신답글일수록 step이 낮다 (답글달려는 글의 ref(참조번호)가 같고 쓰려는 답글의 ref_step초과하는 글들 모두 +1)
					//=>step=모든 답글들의 step+1  level은 답글 달려는 글보다 1만큼만 크면된다(들여쓰기 1만큼만)
					result=pushRef(vo);
					
					//이후에 글 등록(step, level +1씩해준다)
					vo.setRef_step(vo.getRef_step()+1);
					vo.setRef_level(vo.getRef_level()+1);
					result=writeRe(vo);
				}
		return result;
	}
	///////////////////////////////////////////
	//총제고
	@Override
	public String stockTotal() {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
	return dao.stockTotal();
	}
	
	//랜덤 신발키
	@Override
	public int shoesKey() {
		StringBuffer temp = new StringBuffer();
		Random random=new Random();
		for(int i = 0; i <4; i++) {
			int rInext = random.nextInt(2);
			if(rInext==0) {
				//A~Z
				temp.append((char)((int)random.nextInt(26))+65);
			}else if(rInext==1) {
				//0~9
				temp.append((random.nextInt(10)));
			}
		}
		int key =Integer.parseInt(temp.toString());
		
		return key;
	}
	
	//뿌려줄 제고리스트
	@Override
	public ArrayList<shoesInfo> stockList(Map<String, Object>map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			//리스트형으로 리턴할땐 리스트에 select값을 담아서 리스트를 리턴하자
		return dao.stockList(map);
	}
	
	//제고등록
	@Override
	public int insertStock(shoesInfo vo) {
		vo.setShoesNumber(shoesKey());
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.insertStock(vo);
	}
	//제고삭제
	@Override
	public int deleteStockPro(int shoesNumber) {
			DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.deleteStockPro(shoesNumber);
	}
	//제고 정보 조회
	@Override
	public shoesInfo modifyRef(int shoesNumber) {
			
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.modifyRef(shoesNumber);
	}
	//제고업데이트
	@Override
	public int modifyStock(shoesInfo vo) {
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.modifyStock(vo);
	}
	//조회수 증가
	@Override
	public void cnt(int shoesNumber) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			dao.cnt(shoesNumber);
	}
	
	//장바구니 중복체크
	@Override
	public int checkCart(Map<String, Object> map) {
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.checkCart(map);
	}
	//cNum찾기
	@Override
	public int cNum(Map<String, Object> map) {
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.cNum(map);
	}
	//중복이있으면 업데이트
	@Override
	public int updateCart(Map<String, Object> map) {
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.updateCart(map);
	}
	//장바구니 중복없으면 인설트
	@Override
	public int insertCart(Map<String, Object> map) {
		DaoInterface dao = sqlSession.getMapper(DaoInterface.class);
		return dao.insertCart(map);
	}
	
	//장바구니 등록
	@Override
	public int regCart(Map<String, Object>map) {
			
		int result=0;
		
		int check=checkCart(map);
		System.out.println(check);
		if(check!=0) {
			//만약 아이디와 슈스넘버가 같으면
			//같은 값이있으면 업데이트 실행
			map.put("cNum", cNum(map));
			result=updateCart(map);
		}else {
			//업으면 인서트 실행
			result=insertCart(map);
		}
		return result;
	}
	
	//카트 목록뿌리기//////////////////////////////////
	@Override
	public ArrayList<shoesInfo> cartList(String memId) {
			
		 DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.cartList(memId);
	}
	//카트 토탈 목록
	@Override
	public String totalCart(String memId) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.totalCart(memId);
	}
	/////////////////////////////////////////////////
	//카트 지우기
	@Override
	public int deleteCart(String cNum) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.deleteCart(cNum);
	}
	//카트수정용 정보조회
	@Override
	public shoesInfo modifyCartRef(Map<String, Object>map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.modifyCartRef(map);
	}
	//카트 정보 수정 프로세스
	@Override
	public int modifyCartPro(Map<String, Object>map) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.modifyCartPro(map);
	}
	//카트 구매확정
	@Override
	public int confirmCart(int cNum) {
		
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.confirmCart(cNum);
	}
	//주문리스트보기
	@Override
	public ArrayList<shoesInfo> buyList(Map<String, Object>map) {

		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		
		HttpServletRequest req=(HttpServletRequest) map.get("req");
		//총갯수도 같이 보내주기
		req.setAttribute("total", totalBuyList((String)map.get("memId")));
		 
		return dao.buyList(map);
	}
	//주문목록 총 갯수 보기
	@Override
	public String totalBuyList(String memId) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.totalBuyList(memId);
	}
	
	//주문목록지우기
	@Override
	public int deleteBuyList(int oNum) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			return dao.deleteBuyList(oNum);
	}
	
	//매니저용 주문 리스트
	@Override
	public ArrayList<shoesInfo> decide(Map<String, Object>map) {
		
				DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			return dao.decide(map);
	}
	//매니저용 주문리스트 총 갯수
	@Override
	public String totalDecide() {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.totalDecide();
	}
	
	//매니저용 주문확정 리스트 삭제
	@Override
	public int deleteDecideList(String oNum) {
				DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.deleteDecideList(oNum);
	}
	
	
	//주문확정//////////////////////////////////////////////////////
	@Override
	public int confirmDecide(String oNum) {
		int dresult=0;
		shoesInfo vo=getDecideInfo(oNum);
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
				if(vo.getMemId()!=null) {
					//토탈에 넣어주기
					insertRAT(vo);
					//오더리스트에서 지우기
					dresult=deleteOrderList(oNum);
					//스톡수량도 줄어든다.
					dresult=updateStock(vo);
				}
		return dresult;
	}

	@Override
	public shoesInfo getDecideInfo(String oNum) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.getDecideInfo(oNum);
	}
	
	@Override
	public int insertRAT(shoesInfo vo) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.insertRAT(vo);
	}
	@Override
	public int deleteOrderList(String oNum) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.deleteOrderList(oNum);
	}
	@Override
	public int updateStock(shoesInfo vo) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.updateStock(vo);
	}
	/////////////////////////////////////////////////////////////
	
	//바로사기!
	@Override
	public int nowBuy(Map<String, Object>map) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.nowBuy(map);
	}
	
	//환불 총 갯수
	@Override
	public String totalRefund(String memId) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.totalRefund(memId);
	}
	//환불 게시물 뿌려주기
	@Override
	public ArrayList<shoesInfo> refundList(String memId) {
			DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.refundList(memId);
	}
	//환불확정
	@Override
	public int confirmRefund(ArrayList<shoesInfo> List) {
		Map<String, Object>map =new HashMap<String, Object>	();
		int result=0;
		
			for(shoesInfo info : List) {
				//환불한 만큼 총 수량에서 빼기
				
				map.put("pCount", info.getpCount());
				map.put("pNum", info.getpNum());
				map.put("shoesNumber", info.getShoesNumber());
				//환불 목록 업데이트
				minusRefuntCount(map);
				//0인환불목록 지우기
				ifZerodelete();
				//환불수량 만큼 stock 업데이트
				result=plusStock(map);
			}
		return result;
	}
	//환불목록중 수량 0인것 지우기
	@Override
	public void ifZerodelete() {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		dao.ifZerodelete();
	}
	//환불한 만큼 환불목록 update
	@Override
	public void minusRefuntCount(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		dao.minusRefuntCount(map);
	}
	
	//환불한 만큼 스톡 update
	@Override
	public int plusStock(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.plusStock(map);
	}
	
	//환불목록 감추기
	@Override
	public int deleteRefund(String pNum) {
				DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
			return dao.deleteRefund(pNum);
	}
	//차트뿌리기
	@Override
	public ArrayList<JSONArray> chart(Map<String, Object>map) {
		JSONArray ja=null;
		ArrayList<JSONArray> totalJson=new JSONArray();
		HttpServletRequest req=(HttpServletRequest)map.get("req");
		ja=new JSONArray();
		ja.add("년/월");
		ja.add("매출액");
		totalJson.add(ja);
		
		String sD=(String)map.get("sD");
		if(!sD.equals("null01")) {
			ArrayList<Total> to=monthTotal(map);
			if(to!=null) {
				
				System.out.println("여기탐?");
				//월별 총매출
				for(int i =0; i<to.size();i++) {
					ja=new JSONArray();
					ja.add(to.get(i).getMonth());
					ja.add(to.get(i).getTotal());
					totalJson.add(ja);
				}
				
				//총매출
				req.setAttribute("total",total(map));
				//평균매출
				req.setAttribute("avg", avg(map));
			}
		}
		
		return totalJson;
	}
	//평균매출
	@Override
	public int avg(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.avg(map);
	}
	//총매출
	@Override
	public int total(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.total(map);
	}
	//월별총매출
	@Override
	public ArrayList<Total> monthTotal(Map<String, Object> map) {
		DaoInterface dao=sqlSession.getMapper(DaoInterface.class);
		return dao.monthTotal(map);
	}
}
