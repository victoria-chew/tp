package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_FIELD;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_ORDER;
import static seedu.address.logic.Messages.MESSAGE_SORT_WRONG_ARGUMENT_COUNT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.PersonSortField;
import seedu.address.model.person.PersonSortOrder;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgsNameAsc_returnsSortCommand() {
        assertParseSuccess(parser, "name asc", new SortCommand(PersonSortField.NAME, PersonSortOrder.ASCENDING));
    }

    @Test
    public void parse_validArgsNameDesc_returnsSortCommand() {
        assertParseSuccess(parser, "name desc", new SortCommand(PersonSortField.NAME, PersonSortOrder.DESCENDING));
    }

    @Test
    public void parse_validArgsRateAsc_returnsSortCommand() {
        assertParseSuccess(parser, "rate asc", new SortCommand(PersonSortField.RATE, PersonSortOrder.ASCENDING));
    }

    @Test
    public void parse_validArgsRateDesc_returnsSortCommand() {
        assertParseSuccess(parser, "rate desc", new SortCommand(PersonSortField.RATE, PersonSortOrder.DESCENDING));
    }

    @Test
    public void parse_caseInsensitive_returnsSortCommand() {
        assertParseSuccess(parser, "NAME ASC", new SortCommand(PersonSortField.NAME, PersonSortOrder.ASCENDING));
        assertParseSuccess(parser, "Rate Desc", new SortCommand(PersonSortField.RATE, PersonSortOrder.DESCENDING));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongTokenCount_throwsParseException() {
        String expected = String.format(MESSAGE_SORT_WRONG_ARGUMENT_COUNT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "name", expected);
        assertParseFailure(parser, "name asc extra", expected);
    }

    @Test
    public void parse_invalidField_throwsParseException() {
        assertParseFailure(parser, "foo asc", String.format(MESSAGE_INVALID_SORT_FIELD, "foo"));
    }

    @Test
    public void parse_invalidOrder_throwsParseException() {
        assertParseFailure(parser, "name ascending", String.format(MESSAGE_INVALID_SORT_ORDER, "ascending"));
    }
}
