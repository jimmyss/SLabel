package com.jimmyss.slabel.component.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jimmyss.slabel.entity.LabelTask;
import lombok.Data;

import java.awt.*;
import java.io.IOException;
import java.util.Set;

@Data
public class TaskResponse extends JsonSerializer<Set<LabelTask>> {

    @JsonSerialize(using = TaskResponse.class)
    private Set<LabelTask> labelTasks;

    public TaskResponse(Set<LabelTask> labelTasks){
        this.labelTasks=labelTasks;
    }

    @Override
    public void serialize(Set<LabelTask> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (LabelTask task : value) {
            gen.writeObject(task); // 序列化 LabelTask 对象
        }
        gen.writeEndArray();
    }
}
