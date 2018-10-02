package utilities;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckConnection {


    public static boolean checkCOnnection() {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("wwww.google.com", 80);
        try {
            socket.connect(address, 2500);
        } catch (IOException e) {
            return false;
        }
        return true;

    }

}
