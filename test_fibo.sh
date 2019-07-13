#!/usr/bin/env bash

# Test the fibonacci URL passed in parameter with increment of 5 from 5 to 50
# Request are called sequentially


for i in {1..10}
do
    curl -H "Authorization: Bearer $(gcloud auth print-identity-token)" ${1}$((${i}*5))
    echo
done

