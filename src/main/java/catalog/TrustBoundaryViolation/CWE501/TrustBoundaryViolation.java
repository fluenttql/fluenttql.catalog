package catalog.TrustBoundaryViolation.CWE501;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

public class TrustBoundaryViolation {

    private int authenticate(String user, String pass) {
        if("admin".equals(user)) {
            if("mypass".equals(pass)) {
                return 0;
            }
        }
        return 1;
    }

    public void doGetWithoutSanitizer(HttpServletRequest request, HttpServletResponse response) {
        String sessId = request.getParameter("session-id");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        authenticate(user,pass);
        
        if(sessId == null) {

            mySession.setAttribute("user",user);


            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            System.out.println("Inside web container = " + mySession.getId());

            response.addHeader("session-id",mySession.getId());
            try {
                response.getWriter().append("authenticated");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(mySession.getId().equals(sessId)) {
            if(mySession.getAttribute("user").equals(user)) {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.addHeader("session-id",mySession.getId());
                try {
                    response.getWriter().append("Already Logged in");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    HttpSession mySession = new HttpSession() {
        private String user = null;
        public long getCreationTime() {
            return 0;
        }

        public String getId() {
            return "testing-id";
        }

        public long getLastAccessedTime() {
            return 0;
        }

        public ServletContext getServletContext() {
            return null;
        }

        public void setMaxInactiveInterval(int i) {

        }

        public int getMaxInactiveInterval() {
            return 0;
        }

        public HttpSessionContext getSessionContext() {
            return null;
        }

        public Object getAttribute(String s) {
            if("user".equals(s))
                return user;
            return null;
        }

        public Object getValue(String s) {
            return null;
        }

        public Enumeration<String> getAttributeNames() {
            return null;
        }

        public String[] getValueNames() {
            return new String[0];
        }

        public void setAttribute(String s, Object o) {
            if("user".equals(s))
                user = (String) o;
        }

        public void putValue(String s, Object o) {

        }

        public void removeAttribute(String s) {

        }

        public void removeValue(String s) {

        }

        public void invalidate() {

        }

        public boolean isNew() {
            return false;
        }
    };
}