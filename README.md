
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
gcloud beta run deploy python3  --image gcr.io/${PROJECT_ID}/python3 \
    --timeout 900 --memory 2G  --platform managed
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
#Docker version
gcloud beta run deploy java8 --image gcr.io/${PROJECT_ID}/java8 \
    --timeout 900 --memory 2G  --platform managed
#JIB version
gcloud beta run deploy java8-jib --image gcr.io/${PROJECT_ID}/java8-jib \
    --timeout 900 --memory 2G  --platform managed
```

```bash
cd kotlin8
#For Hello World
gcloud alpha functions deploy kotlin8-hello --runtime java8 --trigger-http \
    --entry-point fr.gblaquiere.kotlin8.function.HelloWorld.helloWorld --memory 2Gb --timeout 540
#For Fibonacci
gcloud alpha functions deploy kotlin8-fibo --runtime java8 --trigger-http \
    --entry-point fr.gblaquiere.kotlin8.function.Fibonacci.fibonacci --memory 2Gb --timeout 540

#For building container with Cloud Build
gcloud builds submit 
#For building with JIB (change PROJECT_ID in the pom.xml)
mvn compile jib:build

#For deploying
#Docker version
gcloud beta run deploy kotlin8 --image gcr.io/${PROJECT_ID}/kotlin8 \
    --timeout 900 --memory 2G  --platform managed
#JIB version
gcloud beta run deploy kotlin8-jib --image gcr.io/${PROJECT_ID}/kotlin8-jib \
    --timeout 900 --memory 2G  --platform managed
```

```bash
cd nodejs
#NodeJs 8
#For Hello World
gcloud functions deploy nodejs8-hello --runtime nodejs8 --trigger-http \
    --entry-point helloWorld --source function --memory 2Gb --timeout 540
#For Fibonacci
gcloud functions deploy nodejs8-fibo --runtime nodejs8 --trigger-http \
    --entry-point fibonacci --source function --memory 2Gb --timeout 540

#NodeJs 10
#For Hello World
gcloud beta functions deploy nodejs10-hello --runtime nodejs10 --trigger-http \
    --entry-point helloWorld --source function --memory 2Gb --timeout 540
#For Fibonacci
gcloud beta functions deploy nodejs10-fibo --runtime nodejs10 --trigger-http \
    --entry-point fibonacci --source function --memory 2Gb --timeout 540

#For building container with Cloud Build
#NodeJs8
gcloud builds submit 
#NodeJs10
gcloud builds submit --config cloudbuild10.yaml

#For deploying
#NodeJs8
gcloud beta run deploy nodejs8 --image gcr.io/${PROJECT_ID}/nodejs8 \
    --timeout 900 --memory 2G  --platform managed
#NodeJs10
gcloud beta run deploy nodejs10 --image gcr.io/${PROJECT_ID}/nodejs10 \
    --timeout 900 --memory 2G  --platform managed
```
```bash
cd go
#Go 1.11
#For Hello World
gcloud functions deploy go111-hello --runtime go111 --trigger-http \
    --entry-point HelloWorld --source function --memory 2Gb --timeout 540
#For Fibonacci
gcloud functions deploy go111-fibo --runtime go111 --trigger-http \
    --entry-point Fibonacci --source function --memory 2Gb --timeout 540 

#Go 1.12
#For Hello World
gcloud alpha functions deploy go112-hello --runtime go112 --trigger-http \
    --entry-point HelloWorld --source function --memory 2Gb --timeout 540
#For Fibonacci
gcloud alpha functions deploy go112-fibo --runtime go112 --trigger-http \
    --entry-point Fibonacci --source function --memory 2Gb --timeout 540

#For building container with Cloud Build
#Go 1.11
gcloud builds submit 
#Go 1.12
gcloud builds submit --config cloudbuild112.yaml

#For deploying
#Go 1.11
gcloud beta run deploy go111 --image gcr.io/${PROJECT_ID}/go111 \
    --timeout 900 --memory 2G  --platform managed
#Go 1.12
gcloud beta run deploy go112 --image gcr.io/${PROJECT_ID}/go112 \
    --timeout 900 --memory 2G --platform managed
```
# Observed result

## Cold start and Memory Used
The cold start and the minimal memory used are calculated only on HelloWorld endpoint. 
* For cold start, I considered that the processing time of HelloWorld is not impacting
* For memory, I considered that the additional code for fibonacci (in Cloud Run) is also not impacting

Benchmark is performed with [hey](https://github.com/rakyll/hey) with 
- 1 concurrency request
- 500 requests 
- secure mode for Cloud Run and Function (add header token)
- 5 requests per second

The aim is to limit the test to 1 container and to have a nice memory usage graph. 
If you perform only 1 request, the graph take the memory value when it want and it's not relevant

`hey -c 1 -n 500 -q 5 -H "Authorization: Bearer $(gcloud config config-helper --format='value(credential.id_token)')" <url>`

Data are taken on Google Cloud Platform graphs and logs. The average values are provided here.

| | Cold Start | Memory used | Execution Time 99th% | Execution Time 95th% | Execution Time 50th% |
|:-----|:-----:|:-----:|:-----:|:-----:|:-----:|
| Python3 - function| 17ms (6.5s*) | 41Mb | 10ms | 22ms | 45ms |
| Python3 - Cloud Run| 1.3s (1.7s*) | | | | |
| NodeJS8 - function| 10ms (2.3s*) | 54Mb | 10ms | 15ms | 21ms |
| NodeJS8 - Cloud Run| 1.5s (1.9s*)| | | | |
| NodeJS10 - function| 462ms (4.6s*) | 47Mb | 10ms | 15ms | 24ms |
| NodeJS10 - Cloud Run| 1.7s (2.2s*) | | | | |
| Java8 - function| 1.8s (4.1s*) | 75Mb | 10ms | 13ms | 96ms |
| Java8 - Cloud Run| 1.9s (2.5s*) | | | | |
| Java8-JIB - Cloud Run| 2.1s (2.6s*) | | | | |
| Go 1.11 - function| 10ms (1.7s*) | 10.5Mb | 1Oms | 13ms | 27ms |
| Go 1.11 - Cloud Run| 1.1s (1.5s*) | | | | |
| Go 1.12 - function| 110ms (2s*) | 13Mb | 10ms | 11ms | 24ms |
| Go 1.12 - Cloud Run| 1.2s(1.5s*) | | | | |
*Value taken from Hey in Cloud Shell

## Processing performance
For the processing, the fibonacci algorithm is used, in the **recursive form**.

Tests are performed with the `test_fibo.sh` file which perform successively 10 fibonacci request with an increment of 5 from 5 to 50.

Data are taken on Google Cloud Platform graphs and logs. The average values are provided here

| | Memory used | fibo 5 | fibo 10 | fibo 15 | fibo 20 | fibo 25 | fibo 30 | fibo 35 | fibo 40 | fibo 45 | fibo 50 
|:-----|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| Python3 - function| 43Ms | 5ms | 5ms | 9ms | 10ms | 27ms | 216ms | 3.7s | 34s | 6m38s | >9m |
| Python3 - Cloud Run| | 5ms | 5ms | 5ms | 7ms | 26ms | 200ms | 3.2s | 28.9s | 5m30s | >15m |
| NodeJS8 - function| 55Mb | 7ms | 7ms | 7ms | 7ms | 15ms | 21ms | 200ms | 2.1s | 24.5s | 5m10s |
| NodeJS8 - Cloud Run| | 4ms | 4ms | 5ms | 6ms | 6ms | 21ms | 208ms | 2s | 21s | 4m8s |
| NodeJS10 - function| 47Mb | 9ms | 11ms | 10ms | 11ms | 9ms | 43ms | 416ms | 4.1s | 36.5s | 7m29s |
| NodeJS10 - Cloud Run| | 5ms | 4ms | 4ms | 5ms | 8ms | 20ms | 182ms | 2s | 22.6s | 4m45s |
| Java8 - function| 55Mb | 5ms | 5ms | 7ms | 8ms | 8ms | 8ms | 43ms | 388ms | 4.4s | 46.5s |
| Java8 - Cloud Run| | 4ms | 5ms | 7ms | 7ms | 7ms | 8ms | 35ms | 333ms | 3.7s | 40.5s |
| Java8-JIB - Cloud Run| | 5ms | 6ms | 5ms | 5ms | 5ms | 6ms | 35ms | 326ms | 3.7s | 42s |
| Go 1.11 - function| 10Mb | 4ms | 4ms | 5ms | 5ms | 7ms | 16ms | 63ms | 605ms | 6.4s | 69.8s |
| Go 1.11 - Cloud Run| | 3ms | 6ms | 5ms | 3ms | 4ms | 15ms | 52ms | 577ms | 6.2s | 71.2s |
| Go 1.12 - function| 11mb | 5ms | 5ms | 4ms | 4ms | 4ms | 9ms | 76ms | 731ms | 7s | 76s |
| Go 1.12 - Cloud Run| | 4ms | 3ms | 4ms | 4ms | 3ms | 8ms | 52ms | 549ms | 6s | 67.4s |

# License

This library is licensed under Apache 2.0. Full license text is available in
[LICENSE](https://github.com/guillaumeblaquiere/cloudrun-cloudfunction-compare/tree/master/LICENSE).