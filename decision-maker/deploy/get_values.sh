#!/bin/bash
function file_exists {
  if [ -f "$1" ]; then
    echo '- '$1
  fi
}
env_name=$2
stage=${env_name##*-}

paths=("values/default-values" "values/providers/$1", "values/environments/$2")
  if [ "$stage" = "uat" ]; then
  stage="staging"
  fi


paths=("values/default-values" "values/providers/$1", "values/stages/$stage", "values/environments/$2")
extentions=("yaml/gotmpl" "yaml/gotmpl" "yaml" "yml")

for path in "${path[0]}"
do:
  for extention in "${extention[0]}"
  do:
    file_exists "$path.$extention"
  done
done