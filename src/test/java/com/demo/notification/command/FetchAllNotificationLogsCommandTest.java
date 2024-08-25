package com.demo.notification.command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FetchAllNotificationLogsCommandTest {

    @Test
    void testFetchAllNotificationLogsCommandCreation() {
        // Act
        FetchAllNotificationLogsCommand command = new FetchAllNotificationLogsCommand();

        // Assert
        assertNotNull(command);  // Verify that the command object is created successfully
    }
}
