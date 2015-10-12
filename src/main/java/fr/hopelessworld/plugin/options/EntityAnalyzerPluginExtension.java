package fr.hopelessworld.plugin.options;

import groovy.lang.Closure;

import java.io.File;

import org.gradle.api.NamedDomainObjectContainer;

/**
 * The Class EntityAnalyzerPluginExtension.
 */
public class EntityAnalyzerPluginExtension {

    /** The report calls. */
    private NamedDomainObjectContainer<StrategyCall> strategies;

    /** The entity directory. */
    private File entityDirectory;

    public EntityAnalyzerPluginExtension(NamedDomainObjectContainer<StrategyCall> strategies) {
        super();
        this.strategies = strategies;
    }

    /**
     * Options.
     *
     * @param closure
     *            the closure
     */
    public void strategies(Closure closure) {
        strategies.configure(closure);
    }

    /**
     * Gets the strategies.
     *
     * @return the strategies
     */
    public NamedDomainObjectContainer<StrategyCall> getStrategies() {
        return strategies;
    }

    /**
     * Sets the strategies.
     *
     * @param strategies
     *            the new strategies
     */
    public void setStrategies(NamedDomainObjectContainer<StrategyCall> strategies) {
        this.strategies = strategies;
    }

    /**
     * Gets the entity directory.
     *
     * @return the entity directory
     */
    public File getEntityDirectory() {
        return entityDirectory;
    }

    /**
     * Sets the entity directory.
     *
     * @param entityDirectory
     *            the new entity directory
     */
    public void setEntityDirectory(File entityDirectory) {
        this.entityDirectory = entityDirectory;
    }

}
