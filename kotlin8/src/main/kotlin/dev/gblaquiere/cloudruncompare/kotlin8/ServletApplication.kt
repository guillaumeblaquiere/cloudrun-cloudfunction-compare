package dev.gblaquiere.cloudruncompare.kotlin8

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler

import org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS


fun main(args: Array<String>) {
    val portEnvVar = System.getenv("PORT")
    var port = 8080;
    if (portEnvVar != null && !portEnvVar.equals("")) {
        //Assume that the port is correctly set
        port = Integer.parseInt(portEnvVar)
    }

    val server = Server(port)

    val servletHandler = ServletContextHandler(NO_SESSIONS);
    servletHandler.addServlet("dev.gblaquiere.cloudruncompare.kotlin8.function.HelloWorld", "/")
    servletHandler.addServlet("dev.gblaquiere.cloudruncompare.kotlin8.function.Fibonacci", "/fibo")
    server.setHandler(servletHandler)

    try {
        server.start()
        server.join()
    } catch (ex: Exception) {
        System.exit(1)
    } finally {
        server.destroy()
    }
}

