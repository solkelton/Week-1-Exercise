package data;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import db.DBConnector;
import db.DBType;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataGrabber {

    private final URL url = new URL("https://bootcamp-training-files.cfapps.io/week1/week1-stocks.json");
    private static final JsonFactory factory = new JsonFactory();
    JsonParser jsonParser = factory.createParser(url);
    DataContainer dataContainer = new DataContainer();
    DBConnector dbConnector = new DBConnector(DBType.MYSQL);

    public DataGrabber() throws IOException {}

    public void getData() throws IOException, ParseException {

        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            String fieldName = jsonParser.getCurrentName();

            if (DataType.SYMBOL.getDType().equals(fieldName)) {
                jsonParser.nextToken();
                this.dataContainer.setSymbol(jsonParser.getValueAsString());
                System.out.println("Symbol: " + this.dataContainer.getSymbol());
            }
            if (DataType.PRICE.getDType().equals(fieldName)) {
                jsonParser.nextToken();
                this.dataContainer.setPrice(jsonParser.getValueAsDouble());
                System.out.println("Price: " + this.dataContainer.getPrice());
            }
            if (DataType.VOLUME.getDType().equals(fieldName)) {
                jsonParser.nextToken();
                this.dataContainer.setVolume(jsonParser.getValueAsInt());
                System.out.println("Volume: " + this.dataContainer.getVolume());
            }
            if (DataType.DATE.getDType().equals(fieldName)) {
                jsonParser.nextToken();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = sdf.parse(jsonParser.getValueAsString());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.dataContainer.setDate(sqlDate);
                System.out.println("Date: " + this.dataContainer.getDate());

                this.dbConnector.insert(this.dataContainer);
            }
        }
    }
}
