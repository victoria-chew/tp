package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_FIELD;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_ORDER;
import static seedu.address.logic.Messages.MESSAGE_SORT_WRONG_ARGUMENT_COUNT;

import java.util.Locale;
import java.util.StringTokenizer;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonSortField;
import seedu.address.model.person.PersonSortOrder;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        StringTokenizer tokenizer = new StringTokenizer(trimmed);
        if (tokenizer.countTokens() != 2) {
            throw new ParseException(String.format(MESSAGE_SORT_WRONG_ARGUMENT_COUNT, SortCommand.MESSAGE_USAGE));
        }

        String fieldTokenRaw = tokenizer.nextToken();
        String orderTokenRaw = tokenizer.nextToken();
        String fieldToken = fieldTokenRaw.toLowerCase(Locale.ROOT);
        String orderToken = orderTokenRaw.toLowerCase(Locale.ROOT);

        PersonSortField field;
        switch (fieldToken) {
        case "name":
            field = PersonSortField.NAME;
            break;
        case "rate":
            field = PersonSortField.RATE;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_SORT_FIELD, fieldTokenRaw));
        }

        PersonSortOrder order;
        switch (orderToken) {
        case "asc":
            order = PersonSortOrder.ASCENDING;
            break;
        case "desc":
            order = PersonSortOrder.DESCENDING;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_SORT_ORDER, orderTokenRaw));
        }

        return new SortCommand(field, order);
    }
}
