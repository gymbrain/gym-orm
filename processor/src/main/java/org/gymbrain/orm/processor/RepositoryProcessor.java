package org.gymbrain.orm.processor;

import org.gymbrain.orm.common.entity.Column;
import org.gymbrain.orm.common.entity.Table;
import org.gymbrain.orm.generator.clazz.GymClass;
import org.gymbrain.orm.generator.clazz.GymMethod;
import org.gymbrain.orm.generator.sql.PostgresGenerator;
import org.gymbrain.orm.generator.sql.QueryModel;
import org.gymbrain.orm.generator.sql.QueryType;
import org.gymbrain.orm.generator.utils.GymUtils;
import org.gymbrain.orm.generator.utils.KeyValue;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositoryProcessor {
    private final static String SAVE = "save";
    private final static String UPDATE = "update";
    private final static String FIND_ALL = "findAll";
    private final static String FIND_ONE = "findOne";
    private final static String DELETE_ONE = "deleteOne";

    public void processMethods(String fullName, GymClass aClass, List<? extends Element> methods, ProcessorContext processorContext, boolean isDefaultMethod) {
        if (isDefaultMethod) {
            aClass.setClassName(GymUtils.getClassName(fullName) + "Impl");
            aClass.setPackageName(GymUtils.getPackage(fullName));
        }
        for (Element method : methods) {
            GymMethod m = new GymMethod();
            m.setMethodName(method.getSimpleName().toString());
            String returnTypeFullName = ((ExecutableElement) method).getReturnType().toString();
            addImport(returnTypeFullName, aClass);
            m.setMethodReturnType(GymUtils.getClassName(returnTypeFullName));
            List<? extends VariableElement> parameterElements = ((ExecutableElement) method).getParameters();
            for (VariableElement parameterElement : parameterElements) {
                String parameterFullName = parameterElement.asType().toString();
                addImport(parameterFullName, aClass);
                String key = parameterFullName;
                String value = parameterElement.getSimpleName().toString();
                m.getParameters().add(new KeyValue(key, value));
            }
            aClass.getMethods().add(m);
        }
        PostgresGenerator postgresGenerator = new PostgresGenerator();
        Set<? extends Element> tableElements = processorContext.getTableElements();

        for (GymMethod method : aClass.getMethods()) {
            QueryModel queryModel = new QueryModel();
            if (isDefaultMethod) {
                //todo implement default methods
                KeyValue defaultParameter = method.getParameters().get(0);
                Element parameterElement = tableElements.stream().filter(element -> element.asType().toString().equals(defaultParameter.getKey()))
                        .findFirst().get();
                Table tableAnnotation = parameterElement.getAnnotation(Table.class);
                queryModel.setTableName(tableAnnotation.name());
                switch (method.getMethodName()) {
                    case SAVE: {
                        //todo implement save method
                        queryModel.setType(QueryType.INSERT);
                        List<String> insertColumns = parameterElement.getEnclosedElements().stream()
                                .filter(element -> element.getKind().equals(ElementKind.FIELD))
                                .map(element -> {
                                    Column columnAnnotation = element.getAnnotation(Column.class);
                                    return columnAnnotation.name();
                                })
                                .filter(s -> !s.equals("id"))
                                .collect(Collectors.toList());
                        queryModel.setInsertColumns(insertColumns);
                        queryModel.setInsertValues(GymUtils.buildRepetitiveChar("?",queryModel.getInsertColumns().size()));

                        postgresGenerator.getQuery(queryModel);
                        System.out.println(queryModel);

                    }
                    case UPDATE: {
                        //todo implement update method
                    }
                    case FIND_ALL: {
                        //todo implement findAll method
                    }
                    case FIND_ONE: {
                        //todo implement findOne method
                    }
                    case DELETE_ONE: {
                        //todo implement deleteOne method
                    }
                }
            }
        }
        System.out.println(aClass);

    }

    private void addImport(String importValue, GymClass aClass) {
        if (!aClass.getImports().contains(importValue)) {
            aClass.getImports().add(importValue);
        }
    }


}
