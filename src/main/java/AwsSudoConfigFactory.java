import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;


public class AwsSudoConfigFactory extends ConfigurationFactory {

    private static final String FACTORY_NAME = "aws-sudo";

    protected AwsSudoConfigFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        AwsSudoConfiguration configuration = new AwsSudoConfiguration(project, this, "aws-sudo");
        String path = project.getBasePath();
        if (path != null) {
            configuration.setWorkingDirectory(path);
        }
        return configuration;
    }

    @Override
    public String getName() {
        return FACTORY_NAME;
    }

    @Override
    public boolean isConfigurationSingletonByDefault() {
        return true;
    }

}
