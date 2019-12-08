package com.bill.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 *
 * @author f
 * @date 2019-12-08
 */
public class ServerTest {

    /**
     * test
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10005);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String info = null;
        while ((info = bufferedReader.readLine()) != null) {
            System.out.println("服务端------------- " + info);
        }
        socket.shutdownInput();

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("服务端------------- success");
        printWriter.flush();
        socket.shutdownOutput();

        printWriter.close();
        outputStream.close();

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }

}
