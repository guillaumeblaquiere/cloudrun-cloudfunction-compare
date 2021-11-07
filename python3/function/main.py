def Fibonacci(request):
    """
    n can be passed in query parameter to customize the fibo value. Else use 30
    :param request:
    :return:
    """
    n = 30
    if request.args and 'n' in request.args:
        n = request.args.get('n')
    return 'Fibonacci({}) = {}'.format(n, Fibo(int(n)))


# Function for nth Fibonacci number
def Fibo(n):
    if n <= 2:
        return n - 1
    else:
        return Fibo(n - 1) + Fibo(n - 2)
