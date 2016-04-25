package net.anotheria.moskito.webui.tracers.api;

import net.anotheria.anoplass.api.API;
import net.anotheria.anoplass.api.APIException;
import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.moskito.core.stats.TimeUnit;
import org.distributeme.annotation.DistributeMe;
import org.distributeme.annotation.FailBy;
import org.distributeme.annotation.SupportService;
import org.distributeme.core.failing.RetryCallOnce;

import java.util.List;

/**
 * API for tracing. Tracing is ability of a Monitoring Point to trace all calls that passes through. This is useful if you want to find out who is using a method.
 * Tracing is performed by tracers. One tracer represents a monitoring point (producer). A tracer is always referenced by the producer id of its producer.
 *
 * @author lrosenberg
 * @since 05.05.15 00:42
 */
@DistributeMe(agentsSupport=false)
@SupportService
@FailBy(strategyClass=RetryCallOnce.class)
public interface TracerAPI extends API, Service {
	/**
	 * Returns registered tracers.
	 * @return
	 * @throws APIException
	 */
	List<TracerAO> getTracers() throws APIException;

	/**
	 * Returns a tracer by a producer id of the associated producer if such a tracer exists.
	 * @param producerId
	 * @return
	 * @throws APIException
	 */
	TracerAO getTracer(String producerId) throws APIException;

	/**
	 * Returns traces that have been recorded by the tracer associated with the producer id.
	 * @param producerId id of the producer.
	 * @param timeUnit timeunit for the calculation of duration. The duration is measured in nanoseconds and should be recalculated.
	 * @return
	 * @throws APIException
	 */
	List<TraceAO> getTraces(String producerId, TimeUnit timeUnit) throws APIException;

	/**
	 * Creates a new tracer for given producer id. If such a producer doesn't exists yet, no harm will be done. The tracer will be active but won't be able to collect anything. The tracer will
	 * cost no performance.
	 * @param producerId id of the producer the tracer should be associated with.
	 * @throws APIException
	 */
	void createTracer(String producerId) throws APIException;

	/**
	 * Removes a tracer by producer id.
	 * @param producerId id of the producer the tracer is associated with.
	 * @throws APIException
	 */
	void removeTracer(String producerId) throws APIException;

	/**
	 * Temporarly disables a tracer.
	 * @param producerId id of the producer the tracer is associated with.
	 * @throws APIException
	 */
	void disableTracer(String producerId) throws APIException;

	/**
	 * Reenables a previously disabled tracer or creates a new tracer, if there were no tracer for this producer id.
	 * @param producerId id of the producer the tracer is associated with.
	 * @throws APIException
	 */
	void enableTracer(String producerId) throws APIException;

}
