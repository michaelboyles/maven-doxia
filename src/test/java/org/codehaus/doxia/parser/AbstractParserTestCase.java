package org.codehaus.doxia.parser;

import org.codehaus.doxia.sink.Sink;
import org.codehaus.doxia.sink.SinkAdapter;
import org.codehaus.plexus.PlexusTestCase;

import java.io.FileReader;
import java.io.Reader;

/**
 * @author <a href="mailto:evenisse@codehaus.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public abstract class AbstractParserTestCase
    extends PlexusTestCase
{
    protected abstract Parser getParser();

    protected abstract String getDocument();

    public void testParser()
        throws Exception
    {
        Sink sink = new SinkAdapter();

        Reader reader = new FileReader( getTestFile( getBasedir(), getDocument() ) );

        getParser().parse( reader, sink );
    }
}
