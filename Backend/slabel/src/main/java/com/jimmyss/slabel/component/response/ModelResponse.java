package com.jimmyss.slabel.component.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jimmyss.slabel.entity.LabelTask;
import com.jimmyss.slabel.entity.Model;
import com.jimmyss.slabel.repository.ModelRepository;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Data
public class ModelResponse extends JsonSerializer<List<Model>> {
    @JsonSerialize(using = ModelResponse.class)
    private List<Model> modelList;

    public ModelResponse(List<Model> modelList){
        this.modelList=modelList;
    }

    @Override
    public void serialize(List<Model> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Model model : value) {
            gen.writeObject(model); // 序列化 LabelTask 对象
        }
        gen.writeEndArray();
    }
}
