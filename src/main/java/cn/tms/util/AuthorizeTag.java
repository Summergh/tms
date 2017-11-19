package cn.tms.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.tms.dao.IUserInfoDAO;
import cn.tms.entity.Privilege;
import cn.tms.entity.UserInfo;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AuthorizeTag  extends BodyTagSupport {


	   private IUserInfoDAO userDao;

	    private String URL;  
	  
	    public String getURL() {  
	        return URL;  
	    }  
	      
	    public void setURL(String uRL) {  
	        URL = uRL;  
	    }  
	    @Override  
	    public int doStartTag() { 
	    	  
	        // 如果URL不空就显示URL，否则就不显  
	        if (null != URL) {
	        	getUserDao();
	        	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
				UserInfo info=(UserInfo)request.getSession().getAttribute("userinfo");
	        	//System.out.println(userDao);
	        	List<Privilege> list = userDao.getPersonPrivilege(info.getUserid());
	        	System.out.println(list.size());
	        	
	        	for (Privilege item : list) {
					if(item.getName().equals(URL)){
						  return EVAL_BODY_INCLUDE;  
					}
				}
	          
	        }  
	        return this.SKIP_BODY;  
	    }
		public void getUserDao() {
			  WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			  userDao=(IUserInfoDAO)applicationContext.getBean("IUserInfoDAO");
			
		}

}
