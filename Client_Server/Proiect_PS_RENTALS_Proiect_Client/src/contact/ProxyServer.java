package contact;

import java.io.IOException;

public class ProxyServer implements Server {

    private String host;
    private int port;
    private RealServer realServer;

    public ProxyServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void connect() throws IOException {
        if (realServer == null) {
            this.realServer = new RealServer(this.host, this.port);
            this.realServer.connect();
        }
    }

    public RealServer getRealServer() {
        return realServer;
    }

    public void setRealServer(RealServer realServer) {
        this.realServer = realServer;
    }
}
