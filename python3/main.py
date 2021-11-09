import os
from flask import Flask, request

from function.main import Fibonacci

app = Flask(__name__)


@app.route('/fibo', methods=['GET'])
def call_fibonacci():
    return Fibonacci(request)


if __name__ == "__main__":
    app.run(host='0.0.0.0',port=int(os.environ.get('PORT',8080)))