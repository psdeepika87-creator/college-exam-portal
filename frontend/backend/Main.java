import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server running on http://localhost:8080");

        while (true) {
            Socket socket = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();

            String request = br.readLine();
            if (request == null) {
                socket.close();
                continue;
            }

            String response = "<html><body>"
                    + "<h2>Backend Working!</h2>"
                    + "<p>This Java server is running successfully.</p>"
                    + "</body></html>";

            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + response.length() + "\r\n" +
                    "\r\n" +
                    response;

            out.write(httpResponse.getBytes());
            out.flush();
            socket.close();
        }
    }
}
