package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {

    DictionaryManager dm;

    public BookScrabbleHandler() {
        dm = DictionaryManager.get();
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {
            Scanner in = new Scanner(inFromclient);
            PrintWriter out = new PrintWriter(outToClient);
            String[] args = in.next().split(",");
            ArrayList<String> argsList = new ArrayList<String>(Arrays.asList(args));
            argsList.remove(0);
            boolean result = false;
            if (!args[0].equals("Q") && !args[0].equals("C")) {
                out.println("ERROR");
            }

            else if (args[0].equals("Q")) {
                String[] query = argsList.toArray(new String[argsList.size()]);
                result = dm.query(query);
            }

            else if (args[0].equals("C")) {
                String[] challenge = argsList.toArray(new String[argsList.size()]);
                result = dm.challenge(challenge);
            }

            if (result) {
                out.println("true");
            } else {
                out.println("false");
            }

            out.flush();
            in.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

}
