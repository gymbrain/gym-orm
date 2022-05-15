package org.gymbrain.orm.generator.clazz;

import java.util.ArrayList;
import java.util.List;

public final class GymClass {
    private String packageName;
    private List<String> imports = new ArrayList<>();
    private String className;
    private List<GymVariable> variables = new ArrayList<>();
    private List<GymMethod> methods = new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<GymVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<GymVariable> variables) {
        this.variables = variables;
    }

    public List<GymMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<GymMethod> methods) {
        this.methods = methods;
    }
}
