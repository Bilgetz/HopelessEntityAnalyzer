package fr.hopelessworld.plugin.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.collections4.CollectionUtils;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

import fr.hopelessworld.plugin.options.StrategyCall;
import fr.hopelessworld.plugin.predicate.AnalizedEntityPredicate;
import fr.hopelessworld.plugin.strategy.GeneratorStrategy;

/**
 * The Class CodeAnalyzerProcessor.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("*")
public class CodeAnalyzerProcessor extends AbstractProcessor {

    private static Logger LOG = Logging.getLogger(CodeAnalyzerProcessor.class);

    /** The strategies. */
    public final Set<StrategyCall> strategies;

    /**
     * Instantiates a new code analyzer processor.
     *
     * @param strategies
     *            the strategies
     */
    public CodeAnalyzerProcessor(Set<StrategyCall> strategies) {
        super();
        this.strategies = strategies;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation.
     * processing.ProcessingEnvironment)
     */
    @Override
    public void init(ProcessingEnvironment pe) {
        super.init(pe);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
     * javax.annotation.processing.RoundEnvironment)
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        Collection<AnalizedEntity> entities = this.getEntities(roundEnvironment.getRootElements());

        if (CollectionUtils.isNotEmpty(entities)) {
            for (StrategyCall strat : strategies) {
                LOG.lifecycle("Execute strategie {}", strat.getName());
                String name = strat.getClass().getSimpleName();
                if (name.endsWith("Strategy")) {
                    name = name.substring(0, name.length() - 8);
                }
                File file = strat.getOutputFiles();
                try {
                    GeneratorStrategy strategy = (GeneratorStrategy) Class.forName(strat.getStrategyClass()).newInstance();
                    strategy.generate(file, entities);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Strategy " + strat.getName() + " File problem", e);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    throw new IllegalArgumentException("Strategy " + strat.getName() + " className problem", e);
                }
            }
        }

        return true;
    }

    /**
     * Gets the entities.
     *
     * @param elements
     *            the elements
     * @return the entities
     */
    private Collection<AnalizedEntity> getEntities(Set<? extends Element> elements) {
        Collection<AnalizedEntity> entities = new ArrayList<AnalizedEntity>();
        AnalizedEntityImpl entity;
        FieldImpl field;
        for (Element e : elements) {
            // pour toute les classes analyse
            // si c'est une entité
            if (e.getAnnotation(Entity.class) != null) {
                entity = new AnalizedEntityImpl();
                entities.add(entity);
                entity.setSimpleName(e.getSimpleName().toString());
                Collection<Field> fields = new ArrayList<Field>();
                entity.setFields(fields);
                List<ExecutableElement> methods = ElementFilter.methodsIn(e.getEnclosedElements());

                // on filtre sur les attribut
                for (VariableElement it : ElementFilter.fieldsIn(e.getEnclosedElements())) {
                    field = new FieldImpl(it);
                    fields.add(field);
                    // TODO: find getter and setter and put it in field
                }
            }
        }
        this.setAnalizedEntityInField(entities);
        return entities;
    }

    private void setAnalizedEntityInField(Collection<AnalizedEntity> entities) {
        for (AnalizedEntity analizedEntity : entities) {
            for (Field field : analizedEntity.getFields()) {
                if (field.getAnnotation(ManyToOne.class) != null) {
                    AnalizedEntity entity = CollectionUtils.find(entities, new AnalizedEntityPredicate(field));
                    ((FieldImpl) field).setAnalizedEntity(entity);
                }
            }
        }
    }
}
