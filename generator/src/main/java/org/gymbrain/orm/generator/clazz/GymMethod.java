package org.gymbrain.orm.generator.clazz;

import org.gymbrain.orm.generator.utils.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GymMethod {
    private String methodName;
    private String methodReturnType;
    private List<KeyValue> parameters = new ArrayList<>();
    private String body;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodReturnType() {
        return methodReturnType;
    }

    public void setMethodReturnType(String methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    public List<KeyValue> getParameters() {
        return parameters;
    }

    public void setParameters(List<KeyValue> parameters) {
        this.parameters = parameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
