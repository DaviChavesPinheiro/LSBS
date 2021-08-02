FLAGS_C = -d bin/
FLAGS_R = -classpath bin/

all:
	javac $(FLAGS_C) src/Main.java 
	java $(FLAGS_R) src/Main
c:
	javac $(FLAGS_C) src/Main.java 

r:
	java $(FLAGS_R) src/Main

clean:
	rm **/*.class