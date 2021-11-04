
exports.helloWorld = (req, res) => {
    res.send('Hello World!');
};

exports.Fibonacci = (req, res) => {
    let n = req.query.n;
    if (!n) n=30
    res.send('Fibonacci(' + n + ') = ' + Fibo(n));
};

function Fibo(n) {
    if (n <= 2)
        return n - 1;
    else
        return Fibo(n - 1) + Fibo(n - 2);
}