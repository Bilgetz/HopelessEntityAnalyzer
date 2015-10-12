package fr.hopelessworld.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.lang3.Validate;
import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.internal.GradleInternal;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.internal.reflect.Instantiator;

import fr.hopelessworld.plugin.analyzer.CodeAnalyzerProcessor;
import fr.hopelessworld.plugin.options.EntityAnalyzerPluginExtension;
import fr.hopelessworld.plugin.options.StrategyCall;
import fr.hopelessworld.plugin.options.StrategyCallFactory;

/**
 * The Class EntityAnalyzerPlugin.
 */
public class EntityAnalyzerPlugin implements Plugin<Project> {

    private static Logger LOG = Logging.getLogger(EntityAnalyzerPlugin.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.gradle.api.Plugin#apply(java.lang.Object)
     */
    public void apply(Project project) {
        Instantiator instantiator = ((GradleInternal) project.getGradle()).getServices().get(Instantiator.class);
        NamedDomainObjectContainer<StrategyCall> reportCalls = project.container(StrategyCall.class, new StrategyCallFactory(instantiator, project));
        project.getExtensions().create("analyzeEntity", EntityAnalyzerPluginExtension.class, reportCalls);

        EntityAnalyzerPlugin me = this;

        project.task("analyze").doFirst(new Action<Task>() {
            @Override
            public void execute(Task arg0) {
                me.analyze(project);
            }
        });
    }

    /**
     * Gets the class path.
     *
     * @return the class path
     */
    private String getJavaClassPath(Project project) {
        Set<Task> javaCompileTasks = project.getTasksByName("compileJava", false);

        StringBuilder buf = new StringBuilder(1000);
        if (javaCompileTasks != null && !javaCompileTasks.isEmpty()) {
            LOG.debug("JavaCompile found! :{}", javaCompileTasks.size());
            for (Task task : javaCompileTasks) {
                JavaCompile javaCompileTask = (JavaCompile) task;
                String separator = System.getProperty("path.separator");
                for (File file : javaCompileTask.getClasspath()) {
                    buf.append(separator).append(file.getAbsolutePath());
                    LOG.debug("classpath added: {}", file.getAbsolutePath());
                }
            }
        }

        return buf.toString();
    }

    /**
     * Analyze.
     *
     * @param project
     *            the project
     */
    protected void analyze(Project project) {
        LOG.lifecycle("starting analyze entity");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        EntityAnalyzerPluginExtension extension = project.getExtensions().getByType(EntityAnalyzerPluginExtension.class);

        // Get the list of java file objects, in this case we have only
        // one file, TestClass.java

        List<File> files = new ArrayList<File>();

        this.getAllClasses(extension.getEntityDirectory(), files);

        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(files);

        // Iterable<? extends JavaFileObject> compilationUnits1 =
        // fileManager.getJavaFileObjects(it);
        // Create the compilation task
        List<String> options = new ArrayList<String>();
        options.add("-classpath");
        options.add(getJavaClassPath(project));

        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits1);

        // Create a list to hold annotation processors
        LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

        // Add an annotation processor to the list
        CodeAnalyzerProcessor codeAnalyzerProcessor = new CodeAnalyzerProcessor(extension.getStrategies());
        processors.add(codeAnalyzerProcessor);

        // Set the annotation processor to the compiler task
        task.setProcessors(processors);

        // Perform the compilation task.
        task.call();

    }

    /**
     * Gets the all classes.
     *
     * @param directoryFile
     *            the directory file
     * @param files
     *            the files
     * @return the all classes
     */
    private void getAllClasses(File directoryFile, List<File> files) {
        Validate.notNull(directoryFile, "Directory of Entity is null");
        for (File it : directoryFile.listFiles()) {
            if (it.isDirectory()) {
                getAllClasses(it, files);
            } else if (it.getName().endsWith(".java")) {
                files.add(it);
            }
        }
    }
}
