package za.co.no9.jsqldsl.port.jsqldslmojo;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration;

import javax.xml.bind.UnmarshalException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static za.co.no9.jsqldsl.port.jsqldslmojo.JSQLDSLMojo.getConfiguration;

public class ConfigurationTest {
    @Test
    public void should_unmarshall_XML_configuration_into_Java_objects() throws Exception {
        Configuration configuration = getConfiguration(getResource("/valid-jsqldsl.xml"));

        assertEquals("org.h2.Driver", configuration.getSource().getJdbc().getDriver());
        assertEquals("jdbc:h2:mem:", configuration.getSource().getJdbc().getUrl());
        assertEquals("sa", configuration.getSource().getJdbc().getUsername());
        assertNull(configuration.getSource().getJdbc().getPassword());
    }

    @Test(expected = IOException.class)
    public void should_return_an_IOException_if_configuration_file_does_not_exist() throws Exception {
        getConfiguration(getResource("/unknown-jsqldsl.xml"));
    }

    @Test(expected = UnmarshalException.class)
    public void should_fail_with_an_UnmarshalException_if_the_XML_is_not_compliant_with_the_XSD() throws Exception {
        getConfiguration(getResource("/invalid-jsqldsl.xml"));
    }

    private File getResource(String resourceName) throws IOException {
        URL resource = ConfigurationTest.class.getResource(resourceName);

        if (resource == null) {
            throw new IOException("The resource " + resourceName + " could not be located.");
        } else {
            return FileUtils.toFile(resource);
        }
    }
}