package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import constant.BaiduCconstant;

/**
 * 回调页面
 * http://localhost:8080/oauth2/third_login
 */
public class ThirdLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThirdLoginController() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code= request.getParameter("code");
		if(code != null){
			String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code"
					+ "&code="+code
					+ "&client_id="+BaiduCconstant.CLIENT_ID
					+ "&client_secret="+BaiduCconstant.SECRET_KEY
					+ "&redirect_uri="+BaiduCconstant.REDIRECT_URI;
			//根据返回原code获取包含access_token的json字符串
			String resultJsonStr = httpGet(url);
			//将得到的结果转成json对象
			JSONObject json = new JSONObject(resultJsonStr);
			//获取access_token（访问令牌）
			String accessToken = (String) json.get("access_token");
			if(accessToken != null){
				//根据access_token获取用户的令牌
				String u = "https://openapi.baidu.com/rest/2.0/"
						+ BaiduCconstant.PASSPORT_USERS_GETLOGGEDINUSER
						+ "?access_token="+accessToken;
				String result = httpGet(u);
				JSONObject jo = new JSONObject(result);
				String uname = (String) jo.get("uname");
				System.out.println("用户名："+uname);
				request.setAttribute("username", uname);
				request.getRequestDispatcher("third_login_success.jsp").forward(request, response);
			}else{
				//TODO
				//如果为空，做错误处理
			}
		}else{
			//TODO
			//如果为空，做错误处理
		}
	}
	
	//发送http get类型请求，获取返回结果
	private String httpGet(String url) throws MalformedURLException, IOException, UnsupportedEncodingException {
		URL ur = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
		byte[] buff = new byte[is.available()];
		is.read(buff);
		String ret = new String(buff,"utf-8");
		is.close();
		conn.disconnect();
		return ret;
	}

}
