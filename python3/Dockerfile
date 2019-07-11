FROM python:3-alpine

# RUN apt-get update -y
# RUN apt-get install -y python-pip
WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY . .
ENV PORT 8080

# Run the web service on container startup.
CMD [ "python", "server.py" ]