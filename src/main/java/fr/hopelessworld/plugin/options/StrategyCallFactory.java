package fr.hopelessworld.plugin.options;

import org.gradle.api.NamedDomainObjectFactory;
import org.gradle.api.Project;
import org.gradle.internal.reflect.Instantiator;

/**
 * A factory for creating ReportCall objects.
 */
public class StrategyCallFactory implements NamedDomainObjectFactory<StrategyCall> {

	/** The project. */
	private Project project;
	
	/** The instantiator. */
	private Instantiator instantiator;
	
	
	
	/**
	 * Instantiates a new report call factory.
	 *
	 * @param project the project
	 * @param instantiator the instantiator
	 */
	public StrategyCallFactory(Instantiator instantiator,Project project) {
		super();
		this.project = project;
		this.instantiator = instantiator;
	}



	/* (non-Javadoc)
	 * @see org.gradle.api.NamedDomainObjectFactory#create(java.lang.String)
	 */
	@Override
	public StrategyCall create(String paramString) {
		return new StrategyCall(paramString);
	}

}
