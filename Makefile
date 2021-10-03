run: downloadJavaFX run1 project4FX.class
	java --module-path JavaFXForLinux/ --add-modules javafx.controls,javafx.web project4FX

project4FX.class: project4FX.java
	javac --module-path JavaFXForLinux/ --add-modules javafx.controls,javafx.web project4FX.java

downloadJavaFX:
	wget http://35.187.102.24/libjfxwebkit.so
	mv libjfxwebkit.so ./JavaFXForLinux/

run1:
	javac GraphADT.java
	javac CS400Graph.java
	javac RedBlackTree.java
	javac Restaurant.java
	javac Menu.java
	javac Dish.java
	javac BackEnd.java
	javac --module-path JavaFXForLinux/ --add-modules javafx.controls AlertBox.java

test: compileTest
	java -jar junit5.jar -cp . --scan-classpath

compileTest:
	javac -cp .:junit5.jar  FinalTest.java

clean:
	rm *.class
