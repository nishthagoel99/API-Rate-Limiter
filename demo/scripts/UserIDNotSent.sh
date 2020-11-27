#!/bin/bash
#no cookies are sent---->userID not sent
# PURPOSE :no cookies are sent---->userID not sent
# INPUT:
#       1. API call without userID sent in cookie
#OUTPUT:
#       ERROR:UserID not sent in HTTP Request.  ----->for (1)

for value in {1..1}
do
echo $value
curl  "http://localhost:8090/documents" # should show that user is not sent.
done