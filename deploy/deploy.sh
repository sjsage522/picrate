# Gradle Command Execute function
function gradle_exec()
{
    CMD=$1
    ./gradlew $CMD

    exit_code=$?

    if [ $exit_code -eq 0 ]; then
        echo "> Gradle $CMD succeeded"
    else
        echo "> Gradle $CMD failed with exit code $exit_code\nDeploy is stopped."
        exit 0
    fi
}

SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
REPOSITORY=$SCRIPT_DIR/..
BUILD_FILE_PATH=$REPOSITORY/build/libs/*.jar

echo "> Change Directory.."
cd $REPOSITORY

echo "> Git Pull.."
git pull

echo "> Gradle Testing.."
gradle_exec "test"

echo "> Gradle Build.."
gradle_exec "build"

CURRENT_PID=$(lsof -i :8080 | awk 'NR>1 {print $2}')

echo "Application Running On [$CURRENT_PID] Port."

if [ "$CURRENT_PID" ]; then
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 3
fi

echo "> Deploying Application.."

JAR_NAME=$(ls -tr $BUILD_FILE_PATH | grep -v plain)

echo "> JAR Name: $JAR_NAME"

nohup java -jar \
       -Dspring.profiles.active=prod \
       $BUILD_FILE_PATH > /var/log/nohup.out 2>&1 &