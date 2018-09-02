import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;


public class AwsSudoConfigFactory extends ConfigurationFactory {

    private static final String FACTORY_NAME = "aws-sudo configuration factory";

    protected AwsSudoConfigFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        return new AwsSudoConfiguration(project, this, "aws-sudo");
    }

    @Override
    public String getName() {
        return FACTORY_NAME;
    }

}
