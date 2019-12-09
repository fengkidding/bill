package com.bill.socket;

import java.io.*;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author f
 * @date 2019-12-08
 */
public class SocketClientTest {

    /**
     * test
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10005);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("客户端------------- test");
        printWriter.flush();
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String info = null;
        while ((info = bufferedReader.readLine()) != null) {
            System.out.println("客户端------------- " + info);
        }
        socket.shutdownInput();

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        printWriter.close();
        outputStream.close();
        socket.close();
    }

}
