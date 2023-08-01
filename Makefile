
runTests:
	make runAlgorithmEngineerTests 
	make runBackendDeveloperTests
	make runDataWranglerTests

runDataWranglerTests:
	javac FileReaderDW.java
	javac StudentDW.java
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

runBackendDeveloperTests:
	javac -cp .:junit5.jar FileReaderDW.java
	javac -cp .:junit5.jar FileReaderInterface.java
	javac -cp .:junit5.jar GenNode.java
	javac -cp .:junit5.jar InfoGrabberBD.java
	javac -cp .:junit5.jar RBTreeInterface.java
	javac -cp .:junit5.jar SortedCollectionInterface.java
	javac -cp .:junit5.jar StudentBD.java
	javac -cp .:junit5.jar StudentInterface.java
	javac -cp .:junit5.jar BackendDeveloperTests.java
	javac -cp .:junit5.jar RBTree.java
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java	
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

AlgorithmEngineerTests.java:
	javac NewNode.java
	javac NodeAE.java
	javac RBTree.java
	javac RedBlackTree.java

clean:
	rm *.class
