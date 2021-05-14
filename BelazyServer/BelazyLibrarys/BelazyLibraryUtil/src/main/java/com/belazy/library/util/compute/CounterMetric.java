package com.belazy.library.util.compute;

/**
 * @author tangcp
 */
import java.util.concurrent.atomic.LongAdder;

public class CounterMetric {
	private final LongAdder counter = new LongAdder();

	public void inc()
	{
		counter.increment();
	}


	public void dec()
	{
		counter.decrement();
	}

	public long count()
	{
		return counter.sum();
	}
}


