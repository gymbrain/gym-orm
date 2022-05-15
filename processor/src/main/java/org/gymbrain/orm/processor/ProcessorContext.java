package org.gymbrain.orm.processor;

import javax.lang.model.element.Element;
import java.util.HashSet;
import java.util.Set;

public class ProcessorContext {
    Set<? extends Element> repositoryElements = new HashSet<>();
    Set<? extends Element> tableElements = new HashSet<>();

    public Set<? extends Element> getRepositoryElements() {
        return repositoryElements;
    }

    public void setRepositoryElements(Set<? extends Element> repositoryElements) {
        this.repositoryElements = repositoryElements;
    }

    public Set<? extends Element> getTableElements() {
        return tableElements;
    }

    public void setTableElements(Set<? extends Element> tableElements) {
        this.tableElements = tableElements;
    }
}
