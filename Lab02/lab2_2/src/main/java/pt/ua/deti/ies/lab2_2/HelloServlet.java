package pt.ua.deti.ies.lab2_2;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String customMessage = request.getParameter("message");

        boolean success = validateUser(username, password);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");

            if (customMessage != null && !customMessage.isEmpty()) {
                out.println("<h2>" + customMessage + "</h2>");
            }

            if (success) {
                out.println("<h2>Welcome, " + username + "!</h2>");
            } else {
                out.println("<h2>Validation failed. Please try again.</h2>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean validateUser(String username, String password) {
        return "admin".equals(username) && "qwerty".equals(password);
    }

    public void destroy() {
    }
}
