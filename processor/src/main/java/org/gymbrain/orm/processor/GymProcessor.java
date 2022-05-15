package org.gymbrain.orm.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import org.gymbrain.orm.common.entity.BaseEntity;
import org.gymbrain.orm.common.repository.GymRepository;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("org.gymbrain.orm.common.repository.GymRepository")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Process.class)
public class GymProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        elements = processingEnv.getElementUtils();
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> gymRepositoryElements = roundEnv.getElementsAnnotatedWith(GymRepository.class);
        for (Element repositoryInterfaceElement : gymRepositoryElements) {
            extractTypeNameFromListGeneric(repositoryInterfaceElement);
            if (repositoryInterfaceElement.getKind().isInterface()) {
                List<MethodSpec> methods;
                methods = getMethods(repositoryInterfaceElement);
                TypeSpec typeSpec = null;
                typeSpec = TypeSpec.classBuilder(extractClassName(repositoryInterfaceElement) + "Impl")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethods(methods)
                        .addSuperinterface(repositoryInterfaceElement.asType())
                        .build();
                try {
                    JavaFile.builder("org.gymbrain.orm", typeSpec).build()
                            .writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }

    private List<MethodSpec> getMethods(Element repositoryInterfaceElement) {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        MethodSpec saveMethod = null;
        try {
            saveMethod = MethodSpec.methodBuilder("save")
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter("sad","sad")
                    .addParameter(ParameterSpec.builder(Class.forName(extractClassName(repositoryInterfaceElement),false,new CustomClassLoader()), "entity").build())
                    .addStatement("return 0")
                    .returns(Integer.class)
                    .build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        methodSpecs.add(saveMethod);
        return methodSpecs;
    }

    private String extractClassName(Element element) {
        String completeClassName = element.toString();
        String[] split = completeClassName.split("\\.");
        if (split.length > 0)
            return split[split.length - 1];
        else
            return "";
    }


    private String extractTypeNameFromListGeneric(Element element) {

        String returnType = ((TypeElement) element).getInterfaces().toString();
        String returnClassType = returnType.substring(returnType.indexOf("<") + 1, returnType.indexOf(">"));
        return returnClassType;

    }
}
