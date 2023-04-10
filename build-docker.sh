#!/bin/bash
echo Organisation name?
read organisation
if [ -z "$organisation" ]
	then organisation="lundih"
fi
echo Repository name?
read repo
if [ -z "$repo" ]
	then repo="user-contact"
fi
echo Tag?
read tag
if [ -z "$tag" ]
	then tag="latest"
fi
# Build project
./gradlew build
# Create docker image
sudo docker build -t $organisation/$repo:$tag .