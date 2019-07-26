package fr.gblaquiere.scala8.function

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class HelloWorld extends HttpServlet{
  // Only for Cloud Run compatibility
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    return helloWorld(req, resp)
  }

  def helloWorld(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.getWriter().write("Hello world!")
  }
}