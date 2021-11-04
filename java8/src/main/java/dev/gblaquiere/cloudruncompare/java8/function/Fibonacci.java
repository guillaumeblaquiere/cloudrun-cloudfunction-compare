package dev.gblaquiere.cloudruncompare.java8.function;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.io.BufferedWriter;
import java.io.IOException;

public class Fibonacci implements HttpFunction {
    // Simple function to return "Hello World"
    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws IOException {
        long n = 30;
        if (request.getQueryParameters().get("n") != null && request.getQueryParameters().get("n").size() == 1 && !request.getQueryParameters().get("n").get(0).equals("")){
            try{
                n = Long.parseLong(request.getQueryParameters().get("n").get(0));
            } catch (NumberFormatException e){
                System.out.println("Error in Number format " + request.getQueryParameters().get("n").get(0) + ". Use default value 30");
            }
        }
        BufferedWriter writer = response.getWriter();
        writer.write("Fibonacci(" + n + ") = " + fibo(n));
    }



// Function for nth Fibonacci number
    private long fibo(long n) {
        if (n <= 2)
            return n - 1;
        else
        return fibo(n - 1) + fibo(n - 2);
    }
}