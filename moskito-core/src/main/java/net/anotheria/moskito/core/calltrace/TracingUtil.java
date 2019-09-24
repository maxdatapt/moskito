package net.anotheria.moskito.core.calltrace;

import net.anotheria.moskito.core.config.MoskitoConfigurationHolder;
import net.anotheria.moskito.core.config.journey.JourneyConfig;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * Tracing Util.
 *
 * @author Alex Osadchy
 */
public final class TracingUtil {

	/**
	 * Private constructor.
	 */
	private TracingUtil() {
	}

	/**
	 * Builds call.
	 *
	 * @param method     source {@link Method}, can't be null
	 * @param parameters method parameters
	 * @return call
	 */
	@Deprecated
	public static String buildCall(final Method method, final Object[] parameters) {
		if (method == null) {
			throw new IllegalArgumentException("Parameter method can't be null");
		}

		return buildCall(method.getDeclaringClass().getSimpleName(), method.getName(), parameters, null).toString();
	}

	public static StringBuilder buildCall(String producerId, String methodName, Object[] parameters, String optionalPrefix) {

		//we have to check if we are building the call for tracing or logging. In case we are building the call for tracing we use the
		//journey config associated with the trace call (for consistency) otherwise we use the general config.
		TracedCall tc = RunningTraceContainer.getCurrentlyTracedCall();
		JourneyConfig journeyConfig =
		        tc instanceof CurrentlyTracedCall ?
				((CurrentlyTracedCall)tc).getJourneyConfig() : MoskitoConfigurationHolder.getConfiguration().getJourneyConfig();


		StringBuilder call = new StringBuilder();
		if (optionalPrefix != null)
			call.append(optionalPrefix).append(' ');

		call.append(producerId).append('.').append(methodName).append('(');

		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {

				call.append(parameter2string(parameters[i], journeyConfig));
				if (i < parameters.length - 1) {
					call.append(", ");
				}
			}
		}

		call.append(')');

		return call;

	}

	private static StringBuilder parameter2string(Object parameter, JourneyConfig journeyConfig){
		if (parameter == null)
			return new StringBuilder("null");
		StringBuilder ret = new StringBuilder();
		boolean handled = false;

		if (!journeyConfig.isToStringCollections() && parameter instanceof Collection){
			handled = true;
			ret.append(parameter.getClass().getSimpleName()).append(" with ").append(((Collection)parameter).size()).append("elements");
		}

		if (!journeyConfig.isToStringMaps() && parameter instanceof Map){
			handled = true;
			ret.append(parameter.getClass().getSimpleName()).append(" with ").append(((Map)parameter).size()).append(" elements");
		}

		if (!handled){
			ret.append(parameter.toString());
		}

		if (ret.length()>journeyConfig.getParameterLengthLimit()){
			ret.delete(journeyConfig.getParameterLengthLimit(), ret.length()).append("...");
		}

		return ret;

	}

	public static StringBuilder parameter2string(Object parameter){
		//we have to check if we are building the call for tracing or logging. In case we are building the call for tracing we use the
		//journey config associated with the trace call (for consistency) otherwise we use the general config.
		TracedCall tc = RunningTraceContainer.getCurrentlyTracedCall();
		JourneyConfig journeyConfig =
				tc instanceof CurrentlyTracedCall ?
						((CurrentlyTracedCall)tc).getJourneyConfig() : MoskitoConfigurationHolder.getConfiguration().getJourneyConfig();

		return parameter2string(parameter, journeyConfig);
	}
}
