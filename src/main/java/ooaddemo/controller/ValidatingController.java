package ooaddemo.controller;

import ooaddemo.domain.SeverityLevel;
import ooaddemo.message.DecoratingMessage;

public abstract class ValidatingController extends Object {
    public ValidatingController(int i) {
    }

    public Number log(DecoratingMessage message, SeverityLevel severity) {
        if (message == null) throw new IllegalArgumentException("null message");
        return null;
    }

}