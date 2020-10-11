package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;


/**
 * Unmodifiable view of a schedule
  */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
