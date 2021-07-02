package contact;

import java.io.*;
import java.net.Socket;

public class RealServer  implements Server{

    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public RealServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void connect() throws IOException {
        this.socket = new Socket(this.host, this.port);
        System.out.println("\nconnected to server");

        OutputStream outputStream = this.socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);

        InputStream inputStream = this.socket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
}
