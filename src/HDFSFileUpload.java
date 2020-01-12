import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

public class HDFSFileUpload {
	public static void main(String args[]) throws IOException {
		/* Source file in the local File System */
		String localPath = args[0];

		/* Destination path in Hadoop File System */
		Path hadoopPath = new Path(args[1]);

		/* Get configuration of Hadoop System */
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS","hdfs://localhost/");
		FileSystem fs = FileSystem.get(conf);

		/* Upload the directory from Local File System to Hadoop File System */
		Files.list(Paths.get(localPath))
    		.filter(Files::isRegularFile)
    		.forEach((file) -> {
				Path localFile = new Path(file.toString());

				try {
					long startTime = System.nanoTime();
					fs.copyFromLocalFile(false, true, localFile, hadoopPath);
					long stopTime = System.nanoTime();

					double uploadTime = (stopTime - startTime) / 1000000000.0;
					long fileLength = file.toFile().length();
					double bandwidth = fileLength / uploadTime;

					System.out.println(file.toString() + " " + fileLength + " " + Double.toString(uploadTime) + " " + bandwidth);
				}
				catch(IOException e) {
					System.out.println(e);
				}
			});
	}
}
