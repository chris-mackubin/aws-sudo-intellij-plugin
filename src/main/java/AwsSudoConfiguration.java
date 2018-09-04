import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExternalizablePath;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.text.StringUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class AwsSudoConfiguration extends LocatableConfigurationBase implements CommonProgramRunConfigurationParameters {

    public String PROGRAM_PARAMETERS = "";
    public String WORKING_DIRECTORY = "";
    private final Map<String, String> myEnvs = new LinkedHashMap<String, String>();
    public boolean PASS_PARENT_ENVS = true;

    protected AwsSudoConfiguration(final Project project, final ConfigurationFactory factory, final String name) {
        super(project, factory, name);
    }

    @Override
    @NotNull
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new AwsSudoConfigurationEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        if (StringUtil.isEmptyOrSpaces(WORKING_DIRECTORY)) {
            RuntimeConfigurationException exception = new RuntimeConfigurationException("No Working Directory specified");
            exception.setQuickFix(new Runnable() {
                @Override
                public void run() {
                    setWorkingDirectory((getProject().getBasePath()));
                }
            });
            throw exception;
        }

        final String error = getError();
        if (error != null) {
            throw new RuntimeConfigurationException(error);
        }
    }


    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        final String error = getError();
        return new CommandLineState(executionEnvironment) {

            @NotNull
            @Override
            protected ProcessHandler startProcess() throws ExecutionException {
                OSProcessHandler handler;
                handler = KillableColoredProcessHandler.create(createCommandLine());
                ProcessTerminatedListener.attach(handler);
                return handler;
            }

            protected GeneralCommandLine createCommandLine() throws ExecutionException {
                final SimpleProgramParameters parameters = getParameters();

                return AwsSudoExecutor.in(getProject(), null)
                        .withPresentableName("aws-sudo")
                        .withWorkDirectory(parameters.getWorkingDirectory())
                        .withParameters(parameters.getProgramParametersList().getParameters())
                        .withPassParentEnvironment(parameters.isPassParentEnvs())
                        .withExtraEnvironment(parameters.getEnv())
                        .showOutputOnError()
                        .createCommandLine();
            }

            protected SimpleProgramParameters getParameters() throws ExecutionException {
                final SimpleProgramParameters params = new SimpleProgramParameters();

                ProgramParametersUtil.configureConfiguration(params, AwsSudoConfiguration.this);
                return params;
            }
        };
    }

    private String getError() {
        if (StringUtil.isEmptyOrSpaces(WORKING_DIRECTORY)) {
            return ("No Working Directory specified!");
        }
        return null;
    }

    @Override
    public void setProgramParameters(String value) {
        PROGRAM_PARAMETERS = value;
    }

    @Override
    public String getProgramParameters() {
        return PROGRAM_PARAMETERS;
    }

    @Override
    public void setWorkingDirectory(String value) {
        WORKING_DIRECTORY = ExternalizablePath.urlValue(value);
    }

    @Override
    public String getWorkingDirectory() {
        return ExternalizablePath.localPathValue(WORKING_DIRECTORY);
    }

    public void setEnvs(@NotNull final Map<String, String> envs) {
        myEnvs.clear();
        myEnvs.putAll(envs);
    }

    @Override
    @NotNull
    public Map<String, String> getEnvs() {
        return myEnvs;
    }

    @Override
    public void setPassParentEnvs(boolean passParentEnvs) {
        PASS_PARENT_ENVS = passParentEnvs;
    }

    @Override
    public boolean isPassParentEnvs() {
        return PASS_PARENT_ENVS;
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        super.writeExternal(element);
        //noinspection deprecation
        DefaultJDOMExternalizer.writeExternal(this, element);
        EnvironmentVariablesComponent.writeExternal(element, getEnvs());
    }

    @Override
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        //noinspection deprecation
        DefaultJDOMExternalizer.readExternal(this, element);
        EnvironmentVariablesComponent.readExternal(element, getEnvs());
    }

}
