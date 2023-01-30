import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> history = new ArrayList<>();
    String listOut="";

    public String handleRequest(URI url) {
        if(url.getPath().equals("/")) {
            return String.format("Please add /add-message?s=<your string here> to the end of your URL, or use /search?s=<your string here> to search for existing strings");
        }
        else {
            System.out.println("Path: " + url.getPath());
            if(url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")) {
                    listOut=listOut + parameters[1] + "\n";
                    return String.format("%s", listOut);  //print all
                }
            }
        }
        return "404 Not Found!";
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
