package ar.nic;

import org.jboss.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
public class LoggingRequestInterceptor implements ContainerRequestFilter {

	private static final Logger LOG = Logger.getLogger(LoggingRequestInterceptor.class);

	@Override
	public void filter(ContainerRequestContext context) {
		final StringBuilder loggingString = new StringBuilder();
		loggingString.append(String.format("%s %s \n", context.getMethod(),context.getUriInfo().getPath()));
		try {
			loggingString.append("Request Body:\n" + new String(context.getEntityStream().readAllBytes(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		LOG.info(loggingString.toString());
	}
}