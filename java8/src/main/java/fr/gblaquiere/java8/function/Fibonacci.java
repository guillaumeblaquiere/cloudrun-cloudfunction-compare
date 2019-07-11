package fr.gblaquiere.java8.function;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Fibonacci extends HttpServlet { //extends only usefull for Cloud Run

    //With function, the name can be different. Not with Cloud Run. Here a GET request
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        long n = 30;
        String nParam = request.getParameter("n");
        if (nParam != null && !nParam.equals("")){
            try{
                n = Long.parseLong(nParam);
            } catch (NumberFormatException e){
                System.out.println("Error in Number format " + nParam + ". Use default value 30");
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Fibonacci(" + n + ") = " + Fibo(n));
    }


// Function for nth Fibonacci number
    private long Fibo(long n) {
        if (n <= 2)
            return n - 1;
        else
        return Fibo(n - 1) + Fibo(n - 2);
    }
}