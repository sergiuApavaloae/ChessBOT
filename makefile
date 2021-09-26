default:run
build:Main.java
	javac Main.java
run:build
	java Main

clean:
	rm *.class
