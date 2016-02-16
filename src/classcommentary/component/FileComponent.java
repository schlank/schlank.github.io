package classcommentary.component;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.Extensions;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class FileComponent implements ApplicationComponent {

        private final Logger LOG = Logger.getInstance(getClass());
        private final List<NodeDecoration> myNodeDecorations = Lists.newArrayList();

        public static final NotificationGroup NOTIFICATION =
                new NotificationGroup("Commentary ToolBox Messages", NotificationDisplayType.STICKY_BALLOON, true);

        private ExecutorService myExecutor;
        private ScheduledExecutorService myScheduledExecutor;

    public static FileComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(FileComponent.class);
    }

    public Future<?> submit(Runnable task) {
        //return ApplicationManager.getApplication().executeOnPooledThread(task);
        return myExecutor.submit(task);
    }

    public ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit timeUnit) {
        return myScheduledExecutor.schedule(task, delay, timeUnit);
    }

    @Override
    public void initComponent() {
        myExecutor = Executors.newCachedThreadPool(
                new ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat(getComponentName()+"-pool-%s")
                        .setPriority(Thread.NORM_PRIORITY)
                        .build()
        );
        myScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat(getComponentName() + "-scheduled-pool-%s")
                .setPriority(Thread.NORM_PRIORITY)
                .build()
        );
    }

    public NodeDecoration decorationFor(ProjectViewNode node) {
        ApplicationManager.getApplication().assertIsDispatchThread();
        for (NodeDecoration candidate : myNodeDecorations) {
            if (candidate.isForMe(node)) {
                return candidate;
            }
        }
        return EmptyDecoration.INSTANCE;
    }

    @Override
    public void disposeComponent() {
        if (myExecutor != null) {
            myExecutor.shutdownNow();
        }
        myNodeDecorations.clear();
    }

    @NotNull
    @Override
    public String getComponentName() {
        return getClass().getSimpleName();
    }
}
