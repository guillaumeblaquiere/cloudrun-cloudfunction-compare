package fr.gblaquiere.scala8.function

import java.io.IOException

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class Fibonacci extends HttpServlet {
  // Only for Cloud Run compatibility
  @throws[IOException]
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    return fibonacci(req, resp)
  }

  def fibonacci(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    var n:Long = 30
    val nParam:String = req.getParameter("n")
    if (nParam != null && !(nParam == "")) try
      n = nParam.toLong
    catch {
      case _: NumberFormatException =>
        System.out.println("Error in Number format " + nParam + ". Use default value 30")
    }
    resp.setStatus(HttpServletResponse.SC_OK)
    resp.getWriter.println("Fibonacci(" + n + ") = " + fibo(n))
  }


  // Function for nth Fibonacci number
  private def fibo(n: Long): Long = {
    if (n <= 2) return n - 1
    else return fibo(n - 1) + fibo(n - 2)
  }

}