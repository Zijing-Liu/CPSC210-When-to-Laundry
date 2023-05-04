package ui.windows;

import model.Event;
import model.EventLog;

import java.io.IOException;

/**
 * Defines behaviours that event log printers must support.
 */
public class LogPrinter {
    /**
     * Prints the log
     * @param el  the event log to be printed
     * @throws LogException when printing fails for any reason
     */

    protected void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.toString());

        }
    }
}
