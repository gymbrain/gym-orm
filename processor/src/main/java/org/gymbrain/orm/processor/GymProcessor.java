package org.gymbrain.orm.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import org.gymbrain.orm.common.entity.Table;
import org.gymbrain.orm.common.repository.DefaultMethod;
import org.gymbrain.orm.common.repository.GymRepository;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Map<String, ? extends List<? extends Element>> collect = roundEnv.getElementsAnnotatedWith(Table.class)
                .stream().collect(Collectors.groupingBy(element -> element.asType().toString()));

        for (Element gymRepositoryElement : gymRepositoryElements) {
            List<? extends Element> defaultMethods = gymRepositoryElement.getEnclosedElements()
                    .stream().filter(element -> element.getAnnotation(DefaultMethod.class) != null).collect(Collectors.toList());

            List<? extends Element> notDefaultMethods = gymRepositoryElement.getEnclosedElements()
                    .stream().filter(element -> element.getAnnotation(DefaultMethod.class) == null).collect(Collectors.toList());


            for (Element methodElement : defaultMethods) {
                String methodName = methodElement.getSimpleName().toString();
                String parameterFullName = ((ExecutableElement) methodElement).getParameters().get(0).asType().toString();
                collect.get(parameterFullName)
                        .get(0).getEnclosedElements();
            }
            System.out.println(gymRepositoryElement);
        }
        return true;
    }
}
