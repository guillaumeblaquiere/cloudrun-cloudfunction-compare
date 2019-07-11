package fr.gblaquiere.java8.function;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorld extends HttpServlet { //extends only usefull for Cloud Run

    //With function, the name can be different. Not with Cloud Run. Here a GET request
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Hello World!");
    }
}