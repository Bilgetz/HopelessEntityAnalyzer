package fr.hopelessworld.plugin.options;

import java.io.File;

/**
 * The Class ReportCall.
 */
public class StrategyCall {

    /** The strategy name. */
    private String name;

    /** The output files. */
    private File outputFiles;

    /** The strategy class. */
    private String strategyClass;

    /**
     * Instantiates a new report call.
     *
     * @param strategyName
     *            the strategy name
     */
    public StrategyCall(String strategyName) {
        super();
        this.name = strategyName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the output files.
     *
     * @return the output files
     */
    public File getOutputFiles() {
        return outputFiles;
    }

    /**
     * Sets the output files.
     *
     * @param outputFiles
     *            the new output files
     */
    public void setOutputFiles(File outputFiles) {
        this.outputFiles = outputFiles;
    }

    /**
     * Gets the strategy class.
     *
     * @return the strategy class
     */
    public String getStrategyClass() {
        return strategyClass;
    }

    /**
     * Sets the strategy class.
     *
     * @param strategyClass
     *            the new strategy class
     */
    public void setStrategyClass(String strategyClass) {
        this.strategyClass = strategyClass;
    }

}
