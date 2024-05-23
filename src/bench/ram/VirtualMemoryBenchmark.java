package bench.ram;

import java.io.IOException;
import java.util.Random;

import timing.ITimer;
import timing.NanoTimer;
import bench.IBenchmark;

/**
 * Maps a large file into RAM triggering the virtual memory mechanism. Performs
 * reads and writes to the respective file.<br>
 * The access speeds depend on the file size: if the file can fit the available
 * RAM, then we are measuring RAM speeds.<br>
 * Conversely, we are measuring the access speed of virtual memory, implying a
 * mixture of RAM and HDD access speeds (i.e., lower speeds).
 */
public class VirtualMemoryBenchmark implements IBenchmark {

	private String result = "";
	private MemoryMapper core = null;

	@Override
	public void initialize(Object... params) {
		/* not today */
	}

	@Override
	public void warmUP() {
		/* summer is coming anyway */
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException("Use run(Object[]) instead");
	}

	@Override
	public void run(Object... options) {
		// expected: {fileSize, bufferSize}	
		Object[] params = (Object[]) options;
		long fileSize = Long.parseLong(params[0].toString()); // e.g. 2-16GB
		int bufferSize = Integer.parseInt(params[1].toString()); // e.g. 4+KB
		double speed;

		try {
			core = new MemoryMapper("D:\\UPT\\DC\\UPTBenchmarking\\vm", fileSize); // change path as needed
			byte[] buffer = new byte[bufferSize];
			Random rand = new Random();

			ITimer timer = new NanoTimer();

			// write to VM
			timer.start();
			for (long i = 0; i < fileSize; i += bufferSize) {
				// 1. generate random content (see assignments 9,11)
				rand.nextBytes(buffer);
				// 2. write to memory mapper
				core.put(i,buffer);
			}
//			double speed = 0.0; /* 3. fileSize/time [MB/s] */
			long elapsed = timer.stop();

			speed = (double)(fileSize / (1024 * 1024)) / elapsed * 1000000000;

			result = "\nWrote " + (fileSize / 1024 / 1024L)
					+ " MB to virtual memory at " + speed /* 4. speed, with exactly 2 decimals*/ + " MB/s";

			// read from VM
			timer.start();
			for (long i = 0; i < fileSize; i += bufferSize) {
				// 5. get from memory mapper
				buffer = core.get(i,bufferSize);
			}
//			speed = 0.0; /* 6. MB/s */

			elapsed = timer.stop();

			speed = (double)(fileSize / (1024 * 1024)) / elapsed * 1000000000;

			// append to previous 'result' string
			result += "\nRead " + (fileSize / 1024 / 1024L)
					+ " MB from virtual memory at " + speed/*7. speed, with exactly 2 decimals*/ + " MB/s";
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (core != null)
				core.purge();
		}
	}

	@Override
	public void clean() {
		if (core != null)
			core.purge();
	}

	@Override
	public void cancel(){

	}

	public String getResult() {
		return result;
	}

}
