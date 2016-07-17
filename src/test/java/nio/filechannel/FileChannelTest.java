package nio.filechannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelTest {
	public static void main(String[] args) {
		try {
			// File aFile=new File("nio-data.txt");

			RandomAccessFile aFile = new RandomAccessFile("E:/nio-data.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(200);
			int bytesRead = inChannel.read(buffer);
			while (bytesRead != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
//					buffer.get();
					System.out.print((char) buffer.get());
				}
				System.out.print(new String(buffer.array(), "utf-8"));
				System.out.println();
				buffer.clear();
				bytesRead = inChannel.read(buffer);
			}
			aFile.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
