import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;


public class AwsSudoConfigurationEditor extends SettingsEditor<AwsSudoConfiguration> {

    private JPanel panel;

    private LabeledComponent<ComponentWithBrowseButton> myMainClass;

    @Override
    protected void resetEditorFrom(AwsSudoConfiguration awsSudoConfiguration) {}

    @Override
    protected void applyEditorTo(AwsSudoConfiguration awsSudoConfiguration) throws ConfigurationException {}

    @Override
    @NotNull
    protected JComponent createEditor() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(LabeledComponent.create(new JTextField(), "AWS Profile", BorderLayout.WEST), BorderLayout.NORTH);
        return panel;
    }
}
