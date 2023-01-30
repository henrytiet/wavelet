import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> history = new ArrayList<>();

    public String handleRequest(URI url) {
        if(url.getPath().equals("/")) {
            return String.format("Please add /add?s=<your string here> to the end of your URL, or use /search?s=<your string here> to search for existing strings");
        }
        else {
            System.out.println("Path: " + url.getPath());
            if(url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")) {
                    history.add(parameters[1]);
                    return String.format("%s added to the search history", parameters[1]);
                }
            }
            if(url.getPath().contains("/search")) {
                String[] arguments = url.getQuery().split("=");
                if(arguments[0].equals("s")) {
                    String searches= "";
                    for(int i=0; i<history.size(); i++) {
                        if(history.get(i).contains(arguments[1])) {
                            searches= searches + " " + history.get(i);
                        }
                    }
                    return String.format("%s", searches);
                }
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
