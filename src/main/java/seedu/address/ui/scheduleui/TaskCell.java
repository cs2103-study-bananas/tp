package seedu.address.ui.scheduleui;

import static java.util.Objects.requireNonNull;
import static seedu.address.ui.util.ScheduleUiUtil.MARGIN_PER_MINUTE;
import static seedu.address.ui.util.ScheduleUiUtil.checkTimePattern;
import static seedu.address.ui.util.ScheduleUiUtil.getMarginFromDateTime;
import static seedu.address.ui.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;

public class TaskCell extends UiPart<Region> {
    private static final String FXML = "TaskCell.fxml";

    @FXML
    private Label startTime;

    @FXML
    private VBox task;

    @FXML
    private Label title;

    private Task taskObj;

    /**
     * Construct a TaskCell from a {@Code task}
     */
    public TaskCell(Task task) {
        super(FXML);
        requireNonNull(task);
        assert checkTaskValidation(task) : "task must happen today and has duration and a startTime.";
        taskObj = task;

        // Set title and startTime
        title.setText(task.getTitle().title);
        startTime.setText(getTimeFromTask(task));

        // Violation of LoD, may need to improve.
        // Calculate the height of the cell;
        double height = task.getDuration().get().duration * MARGIN_PER_MINUTE;
        this.task.setPrefHeight(height);
    }

    /**
     * Method used to update the startTime.
     * @param startTimeStr must be in the form of hh:mm AM/PM
     */
    public void setStartTime(String startTimeStr) {
        assert checkTimePattern(startTimeStr);
        this.startTime.setText(startTimeStr);
    }

    /**
     * Calculate the margin top by the task for the TimeScale.
     */
    public double marginTop() {
        return getMarginFromDateTime(this.taskObj.getDateTime().get());
    }

    /**
     * Method used to update the title.
     * @param titleStr
     */
    private void setTitle(String titleStr) {
        this.title.setText(titleStr);
    }

    public void update(Task task) {
        this.taskObj = task;
    }

    private boolean checkTaskValidation(Task task) {
        return task.getDateTime().isPresent() && task.getDuration().isPresent() && task.happensToday();
    }

    private String getTimeFromTask(Task task) {
        LocalDateTime dateTime = task.getDateTime().get().dateTime;
        DateTimeFormatter formmater =  DateTimeFormatter.ofPattern("HH:mm");
        return toAmPmTime(formmater.format(dateTime));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof TaskCell)) {
            return false;
        }

        return this.taskObj.equals(((TaskCell) o).taskObj);
    }

}