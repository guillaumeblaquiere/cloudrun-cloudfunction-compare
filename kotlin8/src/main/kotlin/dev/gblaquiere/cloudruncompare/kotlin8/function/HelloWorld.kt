package dev.gblaquiere.cloudruncompare.kotlin8.function

import javax.servlet.http.*

class HelloWorld : HttpServlet() {

    // Only for Cloud Run compatibility
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        helloWorld(req,resp)
    }

    fun helloWorld(req: HttpServletRequest, resp: HttpServletResponse) {
        with(resp.writer) {
            println("Hello World!")
        }
    }
}
