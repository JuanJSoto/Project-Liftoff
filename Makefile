# Define the name of your project
PROJECT_NAME=projectliftoff

# Define the name of your JAR file
JAR_FILE=$(PROJECT_NAME).jar

# Define the path to your project's source code
SRC_PATH=src/main/java

# Define the path to your project's build output directory
BUILD_PATH=build

# Define the path to your project's main class
MAIN_CLASS=org.launchcode.projectliftoff.ProjectLiftoffApplication

# Define the command to build your project
build:
    ./mvnw clean package

# Define the command to start your project
start:
    java -jar $(BUILD_PATH)/$(JAR_FILE)

# Define the command to build and start your project
run: build start

# Define the command to clean your project's build output
clean:
    rm -rf $(BUILD_PATH)
