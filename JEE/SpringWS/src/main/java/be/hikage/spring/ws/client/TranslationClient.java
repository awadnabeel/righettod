package be.hikage.spring.ws.client;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.security.xwss.XwsSecuritySecurementException;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Hikage
 * Date: 10 nov. 2007
 * Time: 15:47:46
 * To change this template use File | Settings | File Templates.
 */
public class TranslationClient {

    private static final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    private static XWSSProcessor cprocessor;
    private static final ProcessingContext context = new ProcessingContext();


    public static void main(String... args) throws IOException, XWSSecurityException {

        Resource xwssConfig = new ClassPathResource("wss-client-config.xml");
        if (!xwssConfig.exists()) {
            throw new FileNotFoundException();
        }

        XWSSProcessorFactory factory = XWSSProcessorFactory.newInstance();
        cprocessor = factory.createProcessorForSecurityConfiguration(xwssConfig
                .getInputStream(), null);


        Resource resource = new ClassPathResource("be/hikage/spring/ws/client/traductionRequest.xml");
//        webServiceTemplate.setDefaultUri("http://localhost:9090/traductionService/");


        StreamSource source = new StreamSource(resource.getInputStream());

        StreamResult result = new StreamResult(System.out);

        WebServiceMessageCallback wss = new WebServiceMessageCallback() {
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
           /*     SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
                SOAPMessage saajMessage = saajSoapMessage.getSaajMessage();
                try {
                    context.setSOAPMessage(saajMessage);
                    SOAPMessage securedMessage = cprocessor
                            .secureOutboundMessage(context);
                    saajSoapMessage.setSaajMessage(securedMessage);
                }
                catch (XWSSecurityException e) {
                    throw new XwsSecuritySecurementException(e.getMessage());
                }*/
            }
        };

        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:9090/traductionService/", source, result);


    }
}
