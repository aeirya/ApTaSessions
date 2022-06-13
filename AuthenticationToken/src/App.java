import java.util.HashMap;
import java.util.Map;

import util.auth.AuthTokenGenerator;
import util.connection.Connection;
import util.message.Message;
import util.server.Server;

public class App {
    public static void main(String[] args) throws Exception {
        int serverPort = 8080;

        AuthTokenGenerator tokenGenerator = new AuthTokenGenerator();
        Map<String, Thread> clientHandlers = new HashMap<>();

        new Server(connection -> {
            Message greeting = connection.readObject();
            if (greeting.getAuthToken() == null) {
                String token = tokenGenerator.nextToken();
                connection.sendObject(new Message("hi", token));
                clientHandlers.put(token, 
                    new Thread(() -> {
                        while (true) {
                            Message message = connection.readObject();
                            if (!message.getAuthToken().equals(token)) continue;
                            if (message.getContent().equals(MessageType.HELLO)) {
                                connection.sendObject(new Message(MessageType.HELLO_THERE, token));
                            }
                        }
                    })
                ).start();
            }
        }).listen(serverPort);

        Thread.sleep(500);

        Connection connection = new Connection(serverPort);
        connection.sendObject(new Message("hi", null));
        Message response = connection.readObject();
        System.out.println("my token is: ");
        String token = response.getAuthToken();
    }
}
