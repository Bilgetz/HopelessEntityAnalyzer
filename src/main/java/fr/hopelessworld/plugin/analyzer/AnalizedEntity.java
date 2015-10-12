package fr.hopelessworld.plugin.analyzer;

import java.util.Collection;


public interface AnalizedEntity {

    String getSimpleName();
    
    Collection<Field> getFields();
}
