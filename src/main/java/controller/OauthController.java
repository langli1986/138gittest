package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.BaiduCconstant;

/**
 * Servlet implementation class OauthController
 */
public class OauthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.  zhangli xiaoli  ali
     */
    public OauthController() {
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("first".equals(method)){
			first(request,response);
		}
		
	}
	//向 百度发送请求，获取code
	private void first(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "http://openapi.baidu.com/oauth/2.0/authorize?response_type=code"
				+ "&client_id="+BaiduCconstant.CLIENT_ID
				+ "&redirect_uri="+BaiduCconstant.REDIRECT_URI;
		response.sendRedirect(url);
	}

}
