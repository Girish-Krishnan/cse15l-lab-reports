import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The string held by the server
    // As new add requests come in, we keep appending to the string
    String state = "";
    int currentIdx = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return state;
        } 
        else {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    currentIdx++;
                    if (currentIdx != 1)
                        state += "\n" + currentIdx + ". " + parameters[1];
                    else
                        state += currentIdx + ". " + parameters[1];
                    return state;
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
