package com.jimmyss.slabel.component.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jimmyss.slabel.entity.Dataset;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
public class DatasetResponse extends JsonSerializer<List<Dataset>> {

    @JsonSerialize(using= DatasetResponse.class)
    private List<Dataset> datasets;

    public DatasetResponse(List<Dataset> datasets){this.datasets=datasets;}

    @Override
    public void serialize(List<Dataset> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Dataset dataset : value) {
            gen.writeObject(dataset);
        }
        gen.writeEndArray();
    }
}
