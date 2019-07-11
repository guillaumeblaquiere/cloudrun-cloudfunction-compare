import os
from flask import Flask, request

from function.main import hello_world, fibonacci

app = Flask(__name__)

@app.route('/', methods=['GET'])
def call_hello_world():
    return hello_world(request)

@app.route('/fibo', methods=['GET'])
def call_fibonacci():
    return fibonacci(request)

if __name__ == "__main__":
    app.run(host='0.0.0.0',port=int(os.environ.get('PORT',8080)))