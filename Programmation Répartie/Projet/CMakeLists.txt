cmake_minimum_required(VERSION 3.23)
project(Projet C)

set(CMAKE_C_STANDARD 11)

set(GCC_COVERAGE_COMPILE_FLAGS "-pthread")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${GCC_COVERAGE_COMPILE_FLAGS}" )

add_executable(Projet
        main.c testParser.c cmake-build-debug/testThreadSocket.c SocketClientServer.c)
