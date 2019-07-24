package fr.gblaquiere.groovy8.function

import javax.servlet.http.*

class HelloWorld extends HttpServlet {
        void doGet(HttpServletRequest req, HttpServletResponse res) {
                res.writer.write "Hello Groovy World!"
        }
}