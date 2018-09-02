import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AwsSudoConfigurationType implements ConfigurationType {

    public AwsSudoConfigurationType() {

    }

    public static AwsSudoConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(AwsSudoConfigurationType.class);
    }

    @Override
    public String getDisplayName() {
        return "AWS-sudo";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Runs the aws-sudo command to set proper AWS profile context when combined with another command.";
    }

    @Override
    public Icon getIcon() {
        return AwsSudoIcons.AwsSudo16;
    }

    @Override
    @NotNull
    public String getId() {
        return "AWS-SUDO-CONFIGURATION";
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new AwsSudoConfigFactory(this)};
    }

}
