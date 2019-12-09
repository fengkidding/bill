package com.bill.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * nio客户端
 *
 * @author f
 * @date 2019-12-08
 */
public class NioClientTest {

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //连接服务器端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));

        //接收服务器端响应
        // 新开线程，专门负责来接收服务器端的响应数据
        // selector ， socketChannel ， 注册
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        try {
            /**
             * 向服务器端发送数据
             */
            int index = 1;
            while (index < 5) {
                socketChannel.write(Charset.forName("UTF-8").encode("Client : test------------" + index));
                index++;
            }

            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;
                /**
                 * 获取可用channel的集合
                 */
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    /**
                     * selectionKey实例
                     */
                    SelectionKey selectionKey = (SelectionKey) iterator.next();

                    /**
                     * **移除Set中的当前selectionKey**
                     */
                    iterator.remove();

                    /**
                     * 7. 根据就绪状态，调用对应方法处理业务逻辑
                     */

                    /**
                     * 如果是 可读事件
                     */
                    if (selectionKey.isReadable()) {
                        NioClientTest.readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可读事件处理器
     */
    private static void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        /**
         * 要从 selectionKey 中获取到已经就绪的channel
         */
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        /**
         * 创建buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /**
         * 循环读取服务器端响应信息
         */
        String response = "";
        while (socketChannel.read(byteBuffer) > 0) {
            /**
             * 切换buffer为读模式
             */
            byteBuffer.flip();

            /**
             * 读取buffer中的内容
             */
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /**
         * 将channel再次注册到selector上，监听他的可读事件
         */
        socketChannel.register(selector, SelectionKey.OP_READ);

        /**
         * 将服务器端响应信息打印到本地
         */
        if (response.length() > 0) {
            System.out.println(response);
        }
    }
}
