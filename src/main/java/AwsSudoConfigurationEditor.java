import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;


public class AwsSudoConfigurationEditor extends SettingsEditor<AwsSudoConfiguration> implements PanelWithAnchor {

    private CommonProgramParametersPanel myCommonProgramParameters;
    private JComponent commonParamAnchor;
    private JPanel panel;
//    private LabeledComponent<ComponentWithBrowseButton> myMainClass;

    public AwsSudoConfigurationEditor() {
        myCommonProgramParameters.setModuleContext((null));
        commonParamAnchor = UIUtil.mergeComponentsWithAnchor(myCommonProgramParameters);
    }

    @Override
    protected void resetEditorFrom(AwsSudoConfiguration configuration) {
        myCommonProgramParameters.reset(configuration);
    }

    @Override
    protected void applyEditorTo(AwsSudoConfiguration configuration) throws ConfigurationException {
        myCommonProgramParameters.applyTo(configuration);
    }

    @Override
    @NotNull
    protected JComponent createEditor() {
//        final JPanel panel = new JPanel(new BorderLayout());
//        panel.add(LabeledComponent.create(new JTextField(), "AWS Profile", BorderLayout.WEST), BorderLayout.NORTH);
//        panel.add(myCommonProgramParameters);
        return panel;
    }

    @Override
    public JComponent getAnchor() {
        return commonParamAnchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        commonParamAnchor = anchor;
        myCommonProgramParameters.setAnchor(anchor);
    }
}
