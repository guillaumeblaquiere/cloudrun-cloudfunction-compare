import os
from flask import Flask, request

from function.main import hello_world, Fibonacci

app = Flask(__name__)


@app.route('/', methods=['GET'])
def call_hello_world():
    return hello_world(request)


@app.route('/fibo', methods=['GET'])
def call_fibonacci():
    return Fibonacci(request)


if __name__ == "__main__":
    app.run(host='0.0.0.0',port=int(os.environ.get('PORT',8080)))