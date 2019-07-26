package fr.gblaquiere.scala8

import fr.gblaquiere.scala8.function.{Fibonacci, HelloWorld}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS


object ServletApplication {
  def main(args: Array[String]): Unit = {
    val portEnvVar: String = System.getenv.get("PORT")
    var port: Integer = 8080
    if (portEnvVar != null && !(portEnvVar == "")) { //Assume that the port is correctly set
      port = portEnvVar.toInt
    }
    val server: Server = new Server(port)
    val servletHandler: ServletContextHandler = new ServletContextHandler(NO_SESSIONS)
    servletHandler.addServlet(classOf[HelloWorld], "/")
    servletHandler.addServlet(classOf[Fibonacci], "/fibo")
    server.setHandler(servletHandler)
    try {
      server.start()
      server.join()
    } catch {
      case ex: Exception =>
        System.exit(1)
    } finally {
      server.destroy()
    }

  }
}