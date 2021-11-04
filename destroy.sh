#!/bin/sh

REGION="us-central1"
DIRECTORIES="go java8 nodejs python3"
SUFFIX="-test-fibo"

echo "Destroy in region ${REGION}."

# Loop over the regions
for directory in ${DIRECTORIES}; do
  echo "start deleting region ${region}"

  gcloud functions delete ${directory}${SUFFIX} --region=${REGION}
  gcloud run delete ${directory}${SUFFIX} --region=${REGION}

  echo "end of deletion region ${region}"
done
