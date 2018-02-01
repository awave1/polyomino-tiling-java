SRC_DIR = ./src/com/awave 
JAVA_SRC = $(shell find $(SRC_DIR) -name "*.java") 
CLASS_DIR = out
MAIN = com.awave.Main

all: build
	java -cp $(CLASS_DIR) $(MAIN) 

run: build
	java -cp $(CLASS_DIR) $(MAIN) 


build:
	@mkdir -p $(CLASS_DIR)
	@javac -d $(CLASS_DIR) -cp src $(JAVA_SRC)

build_verbose:
	@mkdir -p $(CLASS_DIR)
	@javac -d $(CLASS_DIR) -cp src $(JAVA_SRC) -verbose
