package nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	private Selector selector;

	public void initServer() {
		try {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress(8000));
			this.selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listen() throws IOException {
		while (true) {
			selector.select();
			Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("hello,i am server").getBytes()));
					channel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					SocketChannel channel=(SocketChannel)key.channel();
					ByteBuffer buffer=ByteBuffer.allocate(100);
					channel.read(buffer);
					byte[] data=buffer.array();
					String msg=new String(data).trim();
					System.out.println("服务端接到信息:"+msg);
					ByteBuffer outBuffer=ByteBuffer.wrap("hello,i am server".getBytes());
					channel.write(outBuffer);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		NioServer server=new NioServer();
		server.initServer();
		try {
			server.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
