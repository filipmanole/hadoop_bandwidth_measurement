import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.FileStatus;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

public class HDFSFileDownload {

	public static void main(String args[]) throws IOException {
		/* Source file in the local File System */
		Path localPath = new Path(args[0]);

		/* Destination path in Hadoop File System */
		Path hadoopPath = new Path(args[1]);

		/* Get configuration of Hadoop System */
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS","hdfs://localhost/");

		FileSystem fs = FileSystem.get(conf);

		/* Download Hadoop File System */
		RemoteIterator<LocatedFileStatus> it = fs.listFiles(hadoopPath, false);

		while(it.hasNext()) {
			Path file = it.next().getPath();
			FileStatus fileStatus = fs.getFileStatus(file);

			long startTime = System.nanoTime();
			fs.copyToLocalFile(file, localPath);
			long stopTime = System.nanoTime();

			double downloadTime = (stopTime - startTime) / 1000000000.0;
			long fileLength = fileStatus.getLen();
			double bandwidth = fileLength / downloadTime;

			System.out.println(file + " " + fileLength + " " + Double.toString(downloadTime) + " " + bandwidth);
		}
	}

}
