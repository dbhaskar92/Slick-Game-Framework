# Author: Dhananjay Bhaskar
# Last Modified: Nov 28, 2013
# Description: Compile and run game framework
# Tested On: Mac OSX 10.6, Ubuntu Linux

LIBS := $(shell find ../libs -name *.jar | tr "\\n" ":")

all:
	javac -classpath $(LIBS). *.java
	java -Djava.library.path="../libs/natives/" -cp $(LIBS). Slick2dGame

clean:
	rm -rf *.class
