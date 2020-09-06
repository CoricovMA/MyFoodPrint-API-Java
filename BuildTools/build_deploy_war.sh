#!/bin/bash

mvn clean install

heroku war:deploy target/api.war --app mfp-j