#!/usr/bin/env bash

# Test the fibonacci URL passed in parameter with increment of 5 from 5 to 50
# Request are called sequentially
# Use config helper to be run on Cloud Shell

if [ -z ${1} ]
then
    echo "The app need 1 parameter to test fibonacci with different parameters."
    echo "URL must end just before setting the value parameter for Fibonacci. Like that:"
    echo "http://<url>/<path>?<param>="
    exit 1
fi


for i in {1..10}
do
    curl -H "Authorization: Bearer $(gcloud config config-helper --format='value(credential.id_token)')" ${1}$((${i}*5))
    echo
done

