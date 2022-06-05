package com.acme.dbo.txlog.iteration01;

import com.acme.dbo.txlog.Facade;
import com.acme.dbo.txlog.SysoutCaptureAndAssertionAbility;
import com.acme.dbo.txlog.service.LogOperationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertTrue;

public class LoggerExTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test (expected = LogOperationException.class)
    public void shouldGetError() throws LogOperationException {
        //region when
        Facade.log("MyTestException");
        Facade.flush();
        //endregion
    }

    @Test
    public void shouldGetError1() throws LogOperationException {
        //region when
        Exception exception = new Exception();
        try{
            Facade.log("MyTestException");
            Facade.flush();
        }
        catch (LogOperationException e) {
            exception = e;
        }
        finally {
            assertTrue(exception instanceof LogOperationException);
        }
        //endregion
    }
}