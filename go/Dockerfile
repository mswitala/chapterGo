# We specify the base image we need for our
# go application
FROM golang:latest

# We create an /app directory within our
# image that will hold our application source
# files
RUN mkdir /app
# We copy everything in the root directory
# into our /app directory
ADD . /app

#ENV http_proxy 10.0.2.2:3128
#ENV https_proxy 10.0.2.2:3128

#it requires Internet connection
#RUN go get github.com/lib/pq

# We specify that we now wish to execute
# any further commands inside our /app
# directory
WORKDIR /app

#COPY lib/ /usr/local/go/src/github.com/lib/

# we run go build to compile the binary
# executable of our Go program
RUN go build -o main .

# Our start command which kicks off
# our newly created binary executable
CMD ["/app/main"]
