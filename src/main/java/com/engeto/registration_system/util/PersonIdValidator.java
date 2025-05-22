package com.engeto.registration_system.util;

import com.engeto.registration_system.exception.ValidationRuntimeException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for validating person ID values loaded from a predefined resource file.
 * <p>
 * The class loads a set of allowed person IDs from a file on the classpath (dataPersonId.txt)
 * and provides a method to validate whether a given ID is in the list.
 * </p>
 * This class is non-instantiable.
 */

@Slf4j
@UtilityClass
public class PersonIdValidator {

    private final String FILE_NAME = "dataPersonId.txt";
    private final Set<String> allowedIds = loadPersonIds();

    /**
     * Validates whether the given person ID is present in the allowed list.
     *
     * @param personId person ID string to validate
     * @return {@code true} if the person ID is valid; {@code false} otherwise
     */
    public boolean isValid(String personId) {
        boolean valid = allowedIds.contains(personId);
        logValidation(personId, valid);
        return valid;
    }

    // === Helper methods ===

    /**
     * Loads person IDs from the resource file and returns them as a Set.
     *
     * @return set of allowed person ID values
     * @throws ValidationRuntimeException if reading the file fails
     */
    private Set<String> loadPersonIds() {
        try (BufferedReader reader = getReader()) {
            Set<String> ids = extractValidLines(reader);
            log.info("Loaded {} allowed personIDs from '{}'", ids.size(), FILE_NAME);
            return ids;
        } catch (Exception e) {
            log.error("Failed to load person ID list from '{}'", FILE_NAME, e);
            throw new ValidationRuntimeException("Failed to load person ID list");
        }
    }

    /**
     * Creates a {@link BufferedReader} for the person ID resource file.
     *
     * @return a BufferedReader for the file
     * @throws NullPointerException if the file is not found
     */
    private BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(
                        PersonIdValidator.class.getClassLoader().getResourceAsStream(FILE_NAME),
                        "Resource file " + FILE_NAME + " not found"
                )));
    }

    /**
     * Extracts non-empty, trimmed lines from the given BufferedReader.
     *
     * @param reader BufferedReader, from which lines are read
     * @return a set of valid (non-empty, trimmed) lines
     */
    private Set<String> extractValidLines(BufferedReader reader) {
        return reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toSet());
    }

    /**
     * Logs the result of a person ID validation attempt.
     *
     * @param personId the person ID that was validated
     * @param valid {@code true} if valid, {@code false} otherwise
     */
    private void logValidation(String personId, boolean valid) {
        if (valid) {
            log.debug("Valid personID '{}'", personId);
        } else {
            log.warn("Invalid personID attempted: '{}'", personId);
        }
    }
}
