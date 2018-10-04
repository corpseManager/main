package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MED_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddmhCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medicalhistory.Diagnosis;
import seedu.address.model.person.Nric;

//@@ omegafishy
/**
 * Parses input arguments and creates a new AddmhCommand object
 */
public class AddmhCommandParser implements Parser<AddmhCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddmhCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_MED_HISTORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_MED_HISTORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddmhCommand.MESSAGE_USAGE)));
        }

        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Diagnosis diagnosis = ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_MED_HISTORY).get());

        //TODO need to find the right person stored somewhere, then use the person in AddmhCommand
        return new AddmhCommand(patientNric, diagnosis);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
