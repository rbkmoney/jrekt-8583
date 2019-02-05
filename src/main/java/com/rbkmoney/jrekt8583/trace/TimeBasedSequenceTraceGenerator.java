package com.rbkmoney.jrekt8583.trace;

import com.rbkmoney.jrekt8583.IsoField;
import com.solab.iso8583.TraceNumberGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeBasedSequenceTraceGenerator implements TraceNumberGenerator {

    public static long MAX_SEQUENCE_VALUE = (long) Math.pow(10, IsoField.SYSTEM_TRACE_AUDIT_NUMBER.getLength());

    private final AtomicInteger sequence;

    public TimeBasedSequenceTraceGenerator() {
        this(Math.max(Math.toIntExact(System.currentTimeMillis() % MAX_SEQUENCE_VALUE), 1));
    }

    public TimeBasedSequenceTraceGenerator(int initialValue) {
        if (initialValue < 1 || initialValue >= MAX_SEQUENCE_VALUE) {
            throw new IllegalArgumentException("Initial value must be between 1 and " + MAX_SEQUENCE_VALUE);
        }
        sequence = new AtomicInteger(initialValue);
    }

    @Override
    public int nextTrace() {
        return sequence.accumulateAndGet(1, (prev, acc) -> prev + acc >= MAX_SEQUENCE_VALUE ? 1 : prev + acc);
    }

    @Override
    public int getLastTrace() {
        return sequence.get();
    }

}
