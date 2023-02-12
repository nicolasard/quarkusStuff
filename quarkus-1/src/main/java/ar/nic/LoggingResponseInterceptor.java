package ar.nic;

import org.jboss.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
public class LoggingResponseInterceptor implements ContainerResponseFilter {

	private static final Logger LOG = Logger.getLogger(LoggingResponseInterceptor.class);

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext context) throws IOException {
		final StringBuilder loggingString = new StringBuilder();
		loggingString.append("Response Body:\n" + context.getEntity().toString());
		LOG.info(loggingString.toString());
	}
}
