package be.rubus.jaxrs.secure.workshop.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignatureInterceptor implements ClientRequestFilter {

    private static final String HEADER_DATE = "Date";
    private static final String HEADER_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        handleDateHeader(requestContext);

        URIInfo uriInfo = new URIInfo(requestContext.getMethod(), requestContext.getUri().getPath());
        requestContext.setProperty(URIInfo.class.getName(), uriInfo);
    }

    private void handleDateHeader(ClientRequestContext requestContext) {
        Date now = new Date();
        String stringNow = new SimpleDateFormat(HEADER_DATE_FORMAT, Locale.US).format(now);

        requestContext.getHeaders().add(HEADER_DATE, stringNow);

    }

}
