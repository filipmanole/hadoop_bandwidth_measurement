# hadoop_bandwidth_measurement

## Project Setup
The setup for this project is available in the following link:
https://github.com/green-spark/bigdata-learning/wiki/Hadoop-setup---Single--node-cluster

## Build the app

```bash
make build
```

## Run the app

```bash
make run-upload LOCAL_PATH=/local/directory/to/be/uploaded HADOOP_PATH=/hadoop/path/to/upload
make run-download LOCAL_PATH=/local/path/to/download HADOOP_PATH=/hadoop/directory/to/be/downloaded
```

## Clean the app

```bash
make clean
```

