#!/bin/sh

REGION="us-central1"
DIRECTORIES="java8"
#DIRECTORIES="go java8 nodejs python3"
SUFFIX="-test-fibo"

echo "Deploy in region ${REGION}."

# Loop over the regions
for directory in ${DIRECTORIES}
do
  RUNTIME=""
  ENTRYPOINT="Fibonacci"
  DIRECTORY_FUNCTION="function"
  if [ "${directory}" = "go" ]
  then
    RUNTIME="go113"
  elif [ "${directory}" = "python3" ]
  then
    RUNTIME="python37"
  elif [ "${directory}" = "nodejs" ]
  then
    RUNTIME="nodejs14"
  else
    RUNTIME="java11"
    ENTRYPOINT="dev.gblaquiere.cloudruncompare.java8.function.Fibonacci"
    DIRECTORY_FUNCTION="."
  fi

  cd ${directory}
  echo "start deploying ${directory} Cloud Functions with the runtime ${RUNTIME}"

  cd ${DIRECTORY_FUNCTION}

#  gcloud functions deploy --timeout=540 --region=${REGION} --memory=2048MB --runtime=${RUNTIME} --allow-unauthenticated --entry-point=${ENTRYPOINT} --trigger-http ${directory}${SUFFIX}
  cd -
  echo "start deploying ${directory} Cloud Run"

  gcloud run deploy --timeout=540 --region=${REGION} --memory=2048Mi --platform=managed --allow-unauthenticated --source=. ${directory}${SUFFIX}

  cd ..
done

echo "Done"