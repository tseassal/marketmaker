package com.example.marketmaker;

import com.example.marketmaker.controller.QuoteController;
import com.example.marketmaker.services.QuoteCalculationEngine;
import com.example.marketmaker.services.QuoteCalculationEngineImpl;
import com.example.marketmaker.services.ReferencePriceSource;
import com.example.marketmaker.services.ReferencePriceSourceRAMImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@SpringBootApplication
public class MarketmakerApplication {
	private static final Logger logger = LoggerFactory.getLogger(MarketmakerApplication.class);
	private static String adress = "127.0.0.1";
	private static int port = 1111;

	public static void main(String[] args) throws IOException {

		SpringApplication.run(MarketmakerApplication.class, args);

		//bean dependencies
		QuoteCalculationEngine quoteCalculationEngine = new QuoteCalculationEngineImpl();
		ReferencePriceSource referencePriceSource = new ReferencePriceSourceRAMImpl();
		QuoteController quoteController = new QuoteController(quoteCalculationEngine, referencePriceSource);
		/////


		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);

		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(adress, port));

		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		logger.info("TCP Server started on port: 1111");
		while(true){
			selector.select();

			Set<SelectionKey> keys = selector.selectedKeys();
			for(SelectionKey key : keys){

				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					logger.info("Accepting connection");
					ServerSocketChannel channel = (ServerSocketChannel) key.channel(); //useless here but in PROD environment we could have many channels
					SocketChannel socketChannel = channel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ); //now we are interested in read operations
					keys.remove(key);
				} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
					SocketChannel socketChannel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					socketChannel.read(buffer);
					buffer.flip();
					CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
					String request = new String(charBuffer.array());

					logger.info("Receive: {}", request);
					double quote = quoteController.getQuote(request);
					logger.info("req: {} // quote: {}", request, quote);

					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(bos);
					dos.writeDouble(quote);
					dos.flush();

					ByteBuffer bufferQuote = ByteBuffer.allocate(1024);
					bufferQuote.put(bos.toByteArray());
					logger.info("Replying to {}", request);
					socketChannel.write(bufferQuote);

					buffer.clear();
					bufferQuote.clear();
					keys.remove(key);
					key.cancel();
					socketChannel.close();
				}

			}

		}

	}

}