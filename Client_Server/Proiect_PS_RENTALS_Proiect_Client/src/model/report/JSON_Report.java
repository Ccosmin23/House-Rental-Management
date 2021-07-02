package model.report;

import com.google.gson.Gson;
import model.RentalHouse;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSON_Report extends Report{
    public JSON_Report(){}

    @Override
    public void writeReport() throws IOException {
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        List<String> rentals = new ArrayList<>();
        for (RentalHouse r : this.getRentals()) {
            jsonObject.put(r, "");
        }


        try {
            FileWriter file = new FileWriter("E:/Facultate/An3/Sem2/PS/Proiect_PS_RENTALS_Proiect_Client/rentalsJSON.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: \n" + jsonObject);

    }
}
