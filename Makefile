UPLOAD = HDFSFileUpload
DOWNLOAD = HDFSFileDownload
SRC = src/*.java
OUT = out

build: $(SRC)
	mkdir -p $(OUT)
	javac -classpath `$(HADOOP_HOME)/bin/hadoop classpath` -d $(OUT) $(SRC)
	jar -cvf app.jar -C $(OUT) .

run_upload:
	$(HADOOP_HOME)/bin/hadoop jar app.jar $(UPLOAD) $(LOCAL_PATH) $(HADOOP_PATH)

run_download:
	$(HADOOP_HOME)/bin/hadoop jar app.jar $(DOWNLOAD) $(LOCAL_PATH) $(HADOOP_PATH)

clean:
	rm -fr $(OUT) *.jar
