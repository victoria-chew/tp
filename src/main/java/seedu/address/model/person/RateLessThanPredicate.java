package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Rate} is less than the given value.
 */
public class RateLessThanPredicate implements Predicate<Person> {
    private final int upperBound;

    public RateLessThanPredicate(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean test(Person person) {
        int personRate = Integer.parseInt(person.getRate().toString());
        return personRate < upperBound;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RateLessThanPredicate)) {
            return false;
        }

        RateLessThanPredicate otherPredicate = (RateLessThanPredicate) other;
        return upperBound == otherPredicate.upperBound;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("upperBound", upperBound)
                .toString();
    }
}
