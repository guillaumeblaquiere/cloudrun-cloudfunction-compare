#!/bin/sh

REGION="us-central1"
DIRECTORIES="go java nodejs python3"
SUFFIX="-test-fibo"

echo "Destroy in region ${REGION}."

# Loop over the regions
for directory in ${DIRECTORIES}; do
  echo "start deleting region ${region}"

  gcloud functions delete ${directory}${SUFFIX} --quiet --region=${REGION}
  gcloud run services delete ${directory}${SUFFIX} --quiet --region=${REGION}

  echo "end of deletion region ${region}"
done
