package org.gymbrain.orm.processor;

import com.google.auto.service.AutoService;
import org.gymbrain.orm.common.entity.Table;
import org.gymbrain.orm.common.repository.DefaultMethod;
import org.gymbrain.orm.common.repository.GymRepository;
import org.gymbrain.orm.generator.clazz.GymClass;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("org.gymbrain.orm.common.repository.GymRepository")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Process.class)
public class GymProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;
    private Elements elements;
    private RepositoryProcessor repositoryProcessor;
    private ProcessorContext processorContext;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        elements = processingEnv.getElementUtils();
        repositoryProcessor = new RepositoryProcessor();
        processorContext = new ProcessorContext();
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processorContext.setRepositoryElements(roundEnv.getElementsAnnotatedWith(GymRepository.class));
        processorContext.setTableElements(roundEnv.getElementsAnnotatedWith(Table.class));
        for (Element repositoryElement : processorContext.getRepositoryElements()) {
            List<? extends Element> defaultMethods = repositoryElement.getEnclosedElements()
                    .stream().filter(element -> element.getAnnotation(DefaultMethod.class) != null).collect(Collectors.toList());

            List<? extends Element> notDefaultMethods = repositoryElement.getEnclosedElements()
                    .stream().filter(element -> element.getAnnotation(DefaultMethod.class) == null).collect(Collectors.toList());

            GymClass aClass = new GymClass();
            repositoryProcessor.processMethods(repositoryElement.asType().toString(), aClass, defaultMethods, processorContext, true);
            repositoryProcessor.processMethods(repositoryElement.asType().toString(), aClass, notDefaultMethods, processorContext, false);
            System.out.println(repositoryElement);
        }
        return true;
    }
}
