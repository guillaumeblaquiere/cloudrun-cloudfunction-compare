#!/bin/sh

REGION="us-central1"
#DIRECTORIES="go java8 nodejs python3"
DIRECTORIES="java8"
SUFFIX="-test-fibo"
N=43

echo "Perf test are performed in region ${REGION}."

project=$(gcloud config get-value project)

sum="\n\nPerformance summary\n"

# Loop over the regions
for directory in ${DIRECTORIES}; do

  echo "start querying 3 times language ${directory} - function"

  url="https://${REGION}-${project}.cloudfunctions.net/${directory}${SUFFIX}"

  sum="${sum}\n${directory}-function"

  #avoid cold start
  curl -o /dev/null -s ${url}?n=1

  for i in 1 2 3; do

    duration=$(curl -w "%{time_total}s" -o /dev/null -s ${url}?n=${N})

    sum="${sum},${duration}"
  done

  echo "start querying 3 times language ${directory} - run"

  url=$(gcloud run services list --filter=metadata.name=${directory}${SUFFIX} --format="value(status.address.url)")

  sum="${sum}\n${directory}-run"

  #avoid cold start
  curl -o /dev/null -s ${url}/fibo?n=1

  for i in 1 2 3; do

    duration=$(curl -w "%{time_total}s" -o /dev/null -s ${url}/fibo?n=${N})

    sum="${sum},${duration}"
  done

  echo "End of query language ${directory}"
done

echo ${sum}
