const express = require('express');
const app = express();
const functionNode = require('./function');

app.get('/', (req, res) => {
    functionNode.helloWorld(req,res)
});

app.get('/fibo', (req, res) => {
    functionNode.Fibonacci(req,res)
});

const port = process.env.PORT || 8080;
app.listen(port, () => {});