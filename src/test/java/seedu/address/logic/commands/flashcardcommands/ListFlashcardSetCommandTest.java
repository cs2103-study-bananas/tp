package seedu.address.logic.commands.flashcardcommands;

import static seedu.address.logic.commands.commandtestutils.FlashcardBankCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcardSets.getTypicalFlashcardBank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.FlashcardModel;
import seedu.address.model.FlashcardModelManager;
import seedu.address.model.flashcard.FlashcardSet;

public class ListFlashcardSetCommandTest {
    private FlashcardModel model;
    private FlashcardModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new FlashcardModelManager(getTypicalFlashcardBank());
        expectedModel = new FlashcardModelManager(model.getFlashcardBank());
    }

    @Test
    public void execute_list_showsEverything() {
        ObservableList<FlashcardSet> flashcardSets = model.getFlashcardSetList();

        StringBuilder details = new StringBuilder();
        details.append("\nThere are ");
        details.append(flashcardSets.size());
        details.append(" sets");
        flashcardSets.forEach(flashcardSet -> details.append("\n" + flashcardSet.toString()));

        String expectedMessage = ListFlashcardSetCommand.MESSAGE_SUCCESS + details;
        assertCommandSuccess(new ListFlashcardSetCommand(), model,
                expectedMessage, expectedModel);
    }
}