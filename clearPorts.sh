#!/bin/bash

# clear service discovery port
kill $(lsof -ti:8761)

# clear security port
kill $(lsof -ti:8094)

# clear orders port
kill $(lsof -ti:8093)
