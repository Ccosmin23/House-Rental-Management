package model.report;

import model.RentalHouse;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CSV_Report extends Report {
    private static final String CSV_SEPARATOR = ",";

    public CSV_Report() {
        super();
    }

    @Override
    public void writeReport() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("rentalHouse.csv"), StandardCharsets.UTF_8));
            for (RentalHouse house : this.getRentals()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(house.getCity() != null ? house.getCity() : "eroareCity");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getCountry() != null ? house.getCountry() : "eroareCountry");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getPrice() > 0 ? house.getPrice() : "eroarePrice");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getHousing_type() != null ? house.getHousing_type() : "eroareHousingType");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getBedrooms() > 0 ? house.getBedrooms() : "eroareBedrooms");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getUsable_surface() > 0 ? house.getUsable_surface() : "eroareSurface");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getGarden_surface() > 0 ? house.getGarden_surface() : "eroareGarden");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getFloors() > 0 ? house.getFloors() : "eroareFloors");
                oneLine.append(CSV_SEPARATOR);

                oneLine.append(house.getHouse_name() != null ? house.getHouse_name() : "eroareHouseName");
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("\n");
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
