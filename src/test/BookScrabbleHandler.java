package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {

    DictionaryManager dm;// = DictionaryManager.get();

    public BookScrabbleHandler() {
        dm = DictionaryManager.get();
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {
            Scanner in = new Scanner(inFromclient);
            PrintWriter out = new PrintWriter(outToClient);

            String[] args = in.next().split(",");// get the input from the client

            ArrayList<String> argsList = new ArrayList<String>(Arrays.asList(args));
            // remove the first element of the array (the command) for the query and
            // challenge methods
            argsList.remove(0);

            boolean result = false;// the result of the query or challenge

            if (!args[0].equals("Q") && !args[0].equals("C")) {
                // not a valid command
                out.println("ERROR");
            }

            else if (args[0].equals("Q")) {
                String[] query = argsList.toArray(new String[argsList.size()]);
                result = dm.query(query);// query the dictionary manager with the query string array
            }

            else if (args[0].equals("C")) {
                String[] challenge = argsList.toArray(new String[argsList.size()]);
                result = dm.challenge(challenge);// challenge the dictionary manager with the challenge string array
            }

            if (result) {
                out.println("true");
            } else {
                out.println("false");
            }

            out.flush(); // flush the output stream
            in.close(); // close the input stream
            out.close(); // close the output stream
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

}
