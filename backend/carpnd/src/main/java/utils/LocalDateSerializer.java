package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LocalDateSerializer extends JsonSerializer<List<LocalDate>> {

    @Override
    public void serialize(List<LocalDate> value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        for (LocalDate date: value){
            generator.writeString(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}