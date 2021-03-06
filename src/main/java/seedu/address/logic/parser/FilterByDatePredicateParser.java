package seedu.address.logic.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.filter.FilterByDatePredicate;
import seedu.address.model.filter.TaskPredicate;

public class FilterByDatePredicateParser implements Parser<ListCommand>{

	private final Optional<LocalDateTime> referenceDateTime;
    private final DateTimeArgument dateTimeArg = new DateTimeArgument("DATE", "TIME");

    public FilterByDatePredicateParser() {
        this(Optional.empty());
    }

    public FilterByDatePredicateParser(LocalDateTime referenceDateTime) {
        this(Optional.of(referenceDateTime));
    }

    public FilterByDatePredicateParser(Optional<LocalDateTime> referenceDateTime) {
        this.referenceDateTime = referenceDateTime;
    }

	private final CommandLineParser cmdParser = new CommandLineParser().addArgument(dateTimeArg);
	@Override
	public ListCommand parse(String str) throws ParseException{
		cmdParser.parse(str);
        final LocalDateTime now = referenceDateTime.orElse(LocalDateTime.now());

		 final LocalDate startDate = dateTimeArg.getDate().orElse(now.toLocalDate());
	     final LocalTime startTime = dateTimeArg.getTime().orElse(LocalTime.of(23, 59));
	     final LocalDate endDate = dateTimeArg.getDate().orElse(now.toLocalDate());
	     final LocalTime endTime = dateTimeArg.getTime().orElse(LocalTime.of(23, 59));

		final TaskPredicate predicate = new FilterByDatePredicate(LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime) );
		return new ListCommand(predicate);
	}

}
