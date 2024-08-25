package com.demo.notification.command;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FetchAllNotificationLogsByUserIdCommandTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testFetchAllNotificationLogsByUserIdCommand_Valid() {
        // Arrange
        Long userId = 1L;
        FetchAllNotificationLogsByUserIdCommand command = new FetchAllNotificationLogsByUserIdCommand(userId);

        // Act
        Set<ConstraintViolation<FetchAllNotificationLogsByUserIdCommand>> violations = validator.validate(command);

        // Assert
        assertNotNull(command);
        assertEquals(userId, command.getUserId());
        assertEquals(0, violations.size());  // No violations should occur
    }

    @Test
    void testFetchAllNotificationLogsByUserIdCommand_NullUserId() {
        // Arrange
        FetchAllNotificationLogsByUserIdCommand command = new FetchAllNotificationLogsByUserIdCommand(null);

        // Act
        Set<ConstraintViolation<FetchAllNotificationLogsByUserIdCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<FetchAllNotificationLogsByUserIdCommand> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("userId", violation.getPropertyPath().toString());
    }
}
