package net.anotheria.moskito.core.counter;

import net.anotheria.moskito.core.predefined.Constants;
import net.anotheria.moskito.core.producers.AbstractStats;
import net.anotheria.moskito.core.stats.Interval;
import net.anotheria.moskito.core.stats.StatValue;
import net.anotheria.moskito.core.stats.TimeUnit;
import net.anotheria.moskito.core.stats.impl.StatValueFactory;

import java.util.HashMap;
import java.util.Set;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 16.11.12 23:11
 */
public abstract class GenericCounterStats extends AbstractStats{

	private HashMap<String, StatValue> values;


	protected GenericCounterStats(String name, Interval[] intervals, String firstCounter, String ... moreCounters){
		super(name);
		values = new HashMap<String, StatValue>();
		values.put(firstCounter, StatValueFactory.createStatValue(Long.valueOf(0L), firstCounter, intervals));
		if (moreCounters!=null){
			for (String mc : moreCounters){
				values.put(mc, StatValueFactory.createStatValue(Long.valueOf(0L), mc, intervals));
			}
		}

	}

	protected GenericCounterStats(String name, String firstCounter, String ... moreCounters){
		this(name, Constants.getDefaultIntervals(), firstCounter, moreCounters);
	}

	public void inc(String counterName){
		values.get(counterName).increase();
	}

	public void incBy(String counterName, long value){
		values.get(counterName).increaseByLong(value);
	}

	public long get(String counterName, String intervalName){
		return values.get(counterName).getValueAsLong(intervalName);
	}


	@Override
	public String toStatsString(String aIntervalName, TimeUnit unit) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override public String toString(){
		return getName()+" "+values.values();
	}

	public Set<String> getPossibleNames(){
		return values.keySet();
	}

	public abstract String describeForWebUI();

	@Override
	public String getValueByNameAsString(String valueName, String intervalName, TimeUnit timeUnit) {
		System.out.println("Called getValueByNameAsString on "+getClass().getSimpleName()+", with ("+valueName+", "+intervalName+", "+timeUnit+")");
		if (valueName==null || valueName.equals(""))
			throw new AssertionError("Value name can not be empty");
		valueName = valueName.toLowerCase();
		System.out.println("Value "+valueName+" in "+values);
		return ""+values.get(valueName).getValueAsLong(intervalName);
	}
}

