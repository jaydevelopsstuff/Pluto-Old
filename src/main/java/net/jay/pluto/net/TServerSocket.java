package net.jay.pluto.net;

import java.io.IOException;
import java.net.*;

/**
 * A utility class to allow us to accept a connected socket as a {@link ClientSocket}
 * @author Jay
 */
public class TServerSocket extends ServerSocket {
    protected TServerSocket(SocketImpl impl) {
        super(impl);
    }

    public TServerSocket(int port) throws IOException {
        super(port);
    }

    public TServerSocket(int port, int backlog) throws IOException {
        super(port, backlog);
    }

    public TServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
    }

    @Override
    public Socket accept() throws IOException {
        if(isClosed()) throw new SocketException("Socket is closed");
        if(!isBound()) throw new SocketException("Socket is not bound yet");
        Socket s = new ClientSocket();
        implAccept(s);
        return s;
    }
}
