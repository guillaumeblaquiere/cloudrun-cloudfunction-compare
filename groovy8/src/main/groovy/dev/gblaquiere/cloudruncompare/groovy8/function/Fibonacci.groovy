package dev.gblaquiere.cloudruncompare.groovy8.function


import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Fibonacci extends HttpServlet {
    void doGet(HttpServletRequest req, HttpServletResponse res) {
        long n = 30
        String nParam = req.getParameter("n")
        if (nParam != null && nParam != "") {
            try {
                n = Long.parseLong(nParam)
            } catch (NumberFormatException e) {
                System.out.println("Error in Number format " + nParam + ". Use default value 30")
            }
        }
        res.writer.write "Fibonacci(" + n + ") = " + fibo(n)
    }


    // Function for nth Fibonacci number
    def fibo(long n) {
        if (n <= 2)
            return n - 1;
        else
            return fibo(n - 1) + fibo(n - 2);
    }
}