package com.gridnine.testing.exception;

public class RuleException extends RuntimeException{

    public RuleException(RuleCause ruleCause) {
        super(ruleCause.getText());
    }
}
