# Overview

This project is a performance test comparison on Cloud Run and Cloud Functions of the same fibonacci algorithm (in 
recursive mode) in 4 different languages (Java, nodeJS, python, Golang). It's the purpose of this [Medium article]()

# Deployment

In the root directory, run the command `deploy.sh`. Only gcloud SDK is required with the correct target project added
in the configuration.

The deployed services have this pattern `<language-directory>-test-fibo`, with language directory equals to:

* `python3` for Python
* `go` for Golang
* `java` for Java
* `nodejs` for NodeJS

*For a fair comparison between Cloud Functions and Cloud Run, both use 1vCPU and 2Gb of memory.*

# Performance tests

You can run the performance test by running the file `test-fibo.sh`. The complexity is set to `fibonacci(45)`. *You can
change the N in the top of the file to tests another values*

The run perform a dummy query to avoid the cold start, and run 3 times the same query and get the results. At the end,
The result summary is displayed in CSV format (copy/paste it in a file to use it in Excel or Google Sheet or whatever.)

# Clean up

You can clean your environment by using the `destroy.sh` file.

# Results

A `result.csv` file is present. But you can run your own test, you will stay in the free tier and pay nothing at then end.

# License

This library is licensed under Apache 2.0. Full license text is available in
[LICENSE](https://github.com/guillaumeblaquiere/cloudrun-cloudfunction-compare/tree/master/LICENSE).