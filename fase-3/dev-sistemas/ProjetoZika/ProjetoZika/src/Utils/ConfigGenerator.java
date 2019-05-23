package Utils;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigGenerator {

	public ConfigGenerator(String path, String login, String pass) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("configConnection");
		doc.appendChild(rootElement);

		// host element
		Element host = doc.createElement("host");
		host.appendChild(doc.createTextNode(path));
		rootElement.appendChild(host);

		// user element
		Element user = doc.createElement("user");
		user.appendChild(doc.createTextNode(login));
		rootElement.appendChild(user);

		// password element
		Element password = doc.createElement("password");
		password.appendChild(doc.createTextNode(pass));
		rootElement.appendChild(password);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("./connection-config.xml"));

		transformer.transform(source, result);

	  } catch (ParserConfigurationException | TransformerException error) {
                Methods.getLogger().error("ConfigGenerator.ConfigGenerator: " + error);
                throw new RuntimeException("ConfigGenerator.ConfigGenerator: " + error);
          }
	}
}