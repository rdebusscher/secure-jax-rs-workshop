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

    }

    private void handleDateHeader(ClientRequestContext requestContext) {


    }

}
