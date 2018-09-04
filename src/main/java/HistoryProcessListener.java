
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HistoryProcessListener extends ProcessAdapter {
    private final ConcurrentLinkedQueue<Pair<ProcessEvent, Key>> myHistory = new ConcurrentLinkedQueue<Pair<ProcessEvent, Key>>();

    @Override
    public void onTextAvailable(ProcessEvent event, Key outputType) {
        myHistory.add(Pair.create(event, outputType));
    }

    public void apply(ProcessHandler listener) {
        for (Pair<ProcessEvent, Key> pair : myHistory) {
            listener.notifyTextAvailable(pair.getFirst().getText(), pair.getSecond());
        }
    }
}