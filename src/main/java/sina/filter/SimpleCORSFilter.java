package sina.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCORSFilter implements Filter {
    //解决跨域问题
    @Override
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            //跨域访问允许的路径
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            //跨域允许的请求类型
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            //再次预检options的间隔时间
            response.setHeader("Access-Control-Max-Age", "1728000");
            //允许前端访问数据的类型Content-Type    application/json;charset=utf-8
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            //允许cookie跨域发送
            response.setHeader("Access-Control-Allow-Credentials","true");
            //response.setContentType("application/json;charset=utf-8");
            chain.doFilter(req, res);  
          
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {
          
    }  
  
}
