import com.intellij.openapi.util.IconLoader;
import javax.swing.*;

public class AwsSudoIcons {

    private static Icon load(String path) {
        return IconLoader.getIcon(path, AwsSudoIcons.class);
    }

    public static final Icon AwsSudo16 = load("/icons/aws-cloud-16.png");
}
