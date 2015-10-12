# README #

### Informations ###

This is ententy analyzer for generate what you want.
You can see strategie example [Here](https://github.com/Bilgetz/HopelessEmberGeneratorStrategies)

### How to use ###
``
buildscript {
 ...
    dependencies {
        classpath("fr.hopelessworld.plugin:EntityAnalyzerPlugin:0.1")
   }
}

apply plugin: 'fr.hopelessworld.plugin.entity-analyzer'

analyzeEntity {
     entityDirectory = file("/src/main/java/fr/hopelessworld/entity")
    strategies {
        model {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.EmberModelStrategy"
            outputFiles = file("${buildDir}/generated/js/model.js")
        }
        template {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.EmberTemplateStrategy"
            outputFiles = file("${buildDir}/generated/templates")
        }
        controller {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.EmberControllerStrategy"
            outputFiles = file("${buildDir}/generated/js/controllers.js")
        }
        route {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.EmberRouteStrategy"
            outputFiles = file("${buildDir}/generated/js/routes.js")
        }
        routemap {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.EmberRouteMapStrategy"
            outputFiles = file("${buildDir}/generated/js/routesmap.js")
        }
             
    }
}