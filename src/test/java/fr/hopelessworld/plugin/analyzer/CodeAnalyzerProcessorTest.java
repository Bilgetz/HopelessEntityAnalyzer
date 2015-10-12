package fr.hopelessworld.plugin.analyzer;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.junit.Test;

import fr.hopelessworld.plugin.options.StrategyCall;

public class CodeAnalyzerProcessorTest {

    @Test
    public void test() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // Get the list of java file objects, in this case we have only
        // one file, TestClass.java

        List<File> files = new ArrayList<File>();

        this.getAllClasses(new File("./src/test/java/fr/hopelessworld/entity"), files);

        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(files);

        URL[] javaClassPathUrl = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();

        StringBuilder buf = new StringBuilder(1000);
        String separator = System.getProperty("path.separator");
        for (URL url : javaClassPathUrl) {
            buf.append(separator).append(url.getPath());
        }

        List<String> options = new ArrayList<String>();
        options.add("-classpath");
        options.add(buf.toString());

        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits1);

        // Create a list to hold annotation processors
        LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

        Set<StrategyCall> strategies = new HashSet<StrategyCall>(1);

        StrategyCall strategyCall = new StrategyCall("test");
        strategyCall.setOutputFiles(null);
        strategyCall.setStrategyClass("fr.hopelessworld.plugin.analyzer.TestStrategy");

        strategies.add(strategyCall);

        // Add an annotation processor to the list
        CodeAnalyzerProcessor codeAnalyzerProcessor = new CodeAnalyzerProcessor(strategies);
        processors.add(codeAnalyzerProcessor);

        // Set the annotation processor to the compiler task
        task.setProcessors(processors);

        // Perform the compilation task.
        task.call();
    }

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
