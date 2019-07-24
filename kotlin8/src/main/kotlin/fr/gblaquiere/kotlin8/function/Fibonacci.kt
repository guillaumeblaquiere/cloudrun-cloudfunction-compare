package fr.gblaquiere.kotlin8.function

import javax.servlet.http.*

class Fibonacci : HttpServlet() {

    // Only for Cloud Run compatibility
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        fibonacci(req,resp)
    }

    fun fibonacci(req: HttpServletRequest, resp: HttpServletResponse) {

        var n :Long =  30
        if (req.getParameter("n") != null){
            n = req.getParameter("n").toLongOrNull() ?: n
        }
        with(resp.writer) {
            println("Fibonacci(" + n + ") = " + fibo(n))
        }
    }

    fun fibo(n: Long) : Long {
        if (n <= 2)
            return n - 1
        else
            return fibo(n - 1) + fibo(n - 2)
    }
}

/*
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

}
 */
