package main

import (
	"fmt"
	"gblaquiere.dev/server/function"
	"log"
	"net/http"
	"os"
)

func main() {
	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
	}
	http.HandleFunc("/fibo", function.Fibonacci)
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", port), nil))
}
