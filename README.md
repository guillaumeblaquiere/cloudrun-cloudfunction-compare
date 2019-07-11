
```bash
cd python3
#For Hello World
gcloud functions deploy python3-hello  --source function --trigger-http \
    --entry-point hello_world --memory 2Gb --runtime python37 --timeout 540
#For Fibonacci
gcloud functions deploy python3-fibo  --source function --trigger-http \
    --entry-point fibonacci --memory 2Gb --runtime python37 --timeout 540
    
#For building container
gcloud builds submit 
#For deploying
gcloud beta run deploy python3  --image gcr.io/${PROJECT_ID}/python3 --timeout 900 --memory 2G
```

```bash
cd java8
#For Hello World
gcloud alpha functions deploy java8-hello --runtime java8 --trigger-http \
    --entry-point fr.gblaquiere.java8.function.HelloWorld.doGet --memory 2Gb --timeout 540
#For Fibonacci
gcloud alpha functions deploy java8-fibo --runtime java8 --trigger-http \
    --entry-point fr.gblaquiere.java8.function.Fibonacci.doGet --memory 2Gb --timeout 540

#For building container with Cloud Build
gcloud builds submit 
#For building with JIB (change PROJECT_ID in the pom.xml)
mvn compile jib:build

#For deploying
gcloud beta run deploy python3  --image gcr.io/${PROJECT_ID}/java8 --timeout 900 --memory 2G
```

# License

This library is licensed under Apache 2.0. Full license text is available in
[LICENSE](https://github.com/guillaumeblaquiere/cloudrun-cloudfunction-compare/tree/master/LICENSE).