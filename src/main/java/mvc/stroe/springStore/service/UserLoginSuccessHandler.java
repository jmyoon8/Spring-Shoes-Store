package mvc.stroe.springStore.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import mvc.stroe.springStore.dto.UserVO;

// 로그인이 성공한 경우 자동으로 실행
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserVO vo = (UserVO) authentication.getPrincipal();
		System.out.println("UserVO ==> " + vo);
		
		String msg = authentication.getName() + "님 환영합니다.";
		request.setAttribute("msg", msg);
		
		if(authentication.getName().equals("host")) {
			//아이디 받아와서 세션에 올리기
			request.getSession().setAttribute("hostId", authentication.getName());
											//세션에 값 던져주자authentication.getName() 는 로그인한 회원이 아이디
			request.setAttribute("result", 1);
		}else {
			//아이디 받아와서 세션에 올리기
			request.getSession().setAttribute("sessId", authentication.getName());
											//세션에 값 던져주자authentication.getName() 는 로그인한 회원이 아이디
			request.setAttribute("result", 1);
		}
		
		//컨트롤로url / 전달
		RequestDispatcher dispatcher = request.getRequestDispatcher("toLoginPro");
		dispatcher.forward(request, response);
		
	}
}
