# README #

### Informations ###

This is strategie for generate EmberJs component for [Hopeless Entity Analyzer](https://github.com/Bilgetz/HopelessEntityAnalyzer)

### How to use ###
``
buildscript {
 ...
    dependencies {
        classpath("fr.hopelessworld.plugin:EntityAnalyzerPlugin:1.0")
        classpath("fr.hopelessworld.plugin:EntityAnalyzerPlugin:1.0")
        classpath("fr.hopelessworld.plugin:EmberGeneratorStrategy:1.0")
   }
}

apply plugin: 'fr.hopelessworld.plugin.entity-analyzer'

analyzeEntity {
     entityDirectory = file("/src/main/java/where/is/my/entity")
    strategies {
        nameOfStrategy {
            strategyClass = "className.of.my.Entity"
            outputFiles = file("${buildDir}/generated/myFileName")
        }
    
    }
}
``