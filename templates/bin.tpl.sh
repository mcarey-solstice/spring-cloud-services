#!/bin/bash

source "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/common.sh"

function {{ $bin }}() {
  # TODO: Task code
}

if [[ ${BASH_SOURCE[0]} != $0 ]]; then
  export -f {{ $bin }}
else
  {{ $bin }} "${@:-}"
  exit $?
fi

# {{ $bin }}
