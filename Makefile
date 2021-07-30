FLAGS_C = -d bin/
FLAGS_R = -classpath bin/

all:
	javac $(FLAGS_C) src/frontend/Main.java 
	java $(FLAGS_R) src/frontend/Main
c:
	javac $(FLAGS_C) src/frontend/Main.java 

r:
	java $(FLAGS_R) src/frontend/Main

clean:
	rm **/*.class