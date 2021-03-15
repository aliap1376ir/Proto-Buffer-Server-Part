package ir.aliap1376ir.sources.protobuf.server;

import java.io.IOException;

import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.google.protobuf.util.JsonFormat;


@RestController
@CrossOrigin
public class EarthquakeController {
    private final EarthquakeDb earthquakeDb;

    public EarthquakeController(EarthquakeDb earthquakeDb) {
        this.earthquakeDb = earthquakeDb;
    }

    @GetMapping("/earthquakes")
    public String getEarthquakesJson() throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(this.earthquakeDb.getEarthquakes());
    }

    @GetMapping(path = "/earthquakes", produces = "application.properties/x-protobuf")
    public EarthquakeOuterClass.Earthquakes getEarthquakesProtobuf() {
        return this.earthquakeDb.getEarthquakes();
    }

    @GetMapping(path = "/refresh")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void refresh() throws IOException {
        this.earthquakeDb.readEarthquakeData();
    }

}