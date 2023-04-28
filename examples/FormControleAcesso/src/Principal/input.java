package Principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JTable;

import jdk.nashorn.api.scripting.JSObject;

public class input {

    public static String send(String cracha){
        try {
            URL url = new URL("http://127.0.0.1:8080/login/" + cracha);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            String script = "JSON.parse('" + response.toString() + "')";
            JSObject jsObject = (JSObject) engine.eval(script);
            String returnMessage = jsObject.getMember("returnMessage").toString();
            return returnMessage;
        } catch (Exception e) {
            return "Exception error: " + e.getMessage();
        }

    }
    
}
