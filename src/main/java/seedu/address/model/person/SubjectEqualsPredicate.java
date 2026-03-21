package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests whether a {@code Person}'s subject matches any of the specified subjects.
 * Matching is performed using prefix matching and is case-insensitive.
 */
public class SubjectEqualsPredicate implements Predicate<Person> {
    private final Set<Subject> subjects;
    private final Set<String> normalizedTargetStrings;

    /**
     * Constructs a {@code SubjectEqualsPredicate} from a set of subjects.
     */
    public SubjectEqualsPredicate(Set<Subject> subjects) {
        requireNonNull(subjects);
        this.subjects = Collections.unmodifiableSet(new HashSet<>(subjects));
        this.normalizedTargetStrings = Collections.unmodifiableSet(normalizeSubjects(this.subjects));
    }

    @Override
    public boolean test(Person person) {
        requireNonNull(person);
        return personMatchesAnyTarget(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SubjectEqualsPredicate)) {
            return false;
        }
        SubjectEqualsPredicate otherPredicate = (SubjectEqualsPredicate) other;
        return Objects.equals(this.normalizedTargetStrings, otherPredicate.normalizedTargetStrings);
    }

    @Override
    public String toString() {
        return subjects.toString();
    }

    // ----------------- Helper methods (single level of abstraction) -----------------

    private Set<String> normalizeSubjects(Set<Subject> subjectsToNormalize) {
        Set<String> normalized = new HashSet<>();
        for (Subject s : subjectsToNormalize) {
            normalized.add(s.toString().toLowerCase());
        }
        return normalized;
    }

    private boolean personMatchesAnyTarget(Person person) {
        for (Subject personSub : person.getSubjects()) {
            if (personSubjectMatchesAnyTarget(personSub.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean personSubjectMatchesAnyTarget(String personSubjectString) {
        for (String target : normalizedTargetStrings) {
            if (StringUtil.containsWordPrefixIgnoreCase(personSubjectString, target)) {
                return true;
            }
        }
        return false;
    }

}
