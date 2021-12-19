# docker_gatling
Run [Gatling](https://gatling.io/) on docker.

Not for serious use.

## Paths
- user scripts  
  target/user-files/simulations/*
- config  
  The current file is a duplicate of that of version 3.7.2.
  target/gatling.conf
- generated reports  
  target/results/*

## Build
```sh
docker build . -t docker_gatling
```

## Run
```sh
# To mount user scripts & reports
docker run --rm -it \
  -v `pwd`/target/results:/gatling/results \
  -v `pwd`/target/user-files:/gatling/user-files \
  docker_gatling

# To mount user scripts & reports & conf file
docker run --rm -it \
  -v `pwd`/target/gatling.conf:/gatling/conf/gatling.conf \
  -v `pwd`/target/results:/gatling/results \
  -v `pwd`/target/user-files:/gatling/user-files \
  docker_gatling
```

### Access to localhost
Docker on Linux has `--network host` option. ([document](https://docs.docker.com/network/host/))  
Gatling can access to `localhost` when it is used.

Without that option, docker on Windows/Mac can access to localhost by `host.docker.internal`. ([document1](https://docs.docker.com/desktop/mac/networking/), [document2](https://docs.docker.com/desktop/windows/networking/))
