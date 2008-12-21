package org.apache.maven.doxia.sink;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.doxia.logging.Log;
import org.apache.maven.doxia.logging.SystemStreamLog;
import org.apache.maven.doxia.markup.Markup;

/**
 * An abstract base class that defines some convenience methods for sinks.
 *
 * @author ltheussl
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 * @version $Id$
 * @since 1.0-beta-1
 */
public abstract class AbstractSink
    implements Sink, Markup
{
    private Log log;

    /** {@inheritDoc} */
    public void enableLogging( Log log )
    {
        this.log = log;
    }

    /**
     * Returns a logger for this sink.
     * If no logger has been configured, a new SystemStreamLog is returned.
     *
     * @return Log
     */
    protected Log getLog()
    {
        if ( log == null )
        {
            log = new SystemStreamLog();
        }

        return log;
    }

    /**
     * Parses the given String and replaces all occurrences of
     * '\n', '\r' and '\r\n' with the system EOL. All Sinks should
     * make sure that text output is filtered through this method.
     *
     * @param text the text to scan.
     * @return a String that contains only System EOLs.
     */
     protected static String unifyEOLs( String text )
     {
        if ( text == null )
        {
            return null;
        }

        int length = text.length();

        StringBuffer buffer = new StringBuffer( length );

        for ( int i = 0; i < length; i++ )
        {
            if ( text.charAt( i ) == '\r' )
            {
                if ( ( i + 1 ) < length && text.charAt( i + 1 ) == '\n' )
                {
                    i++;
                }

                buffer.append( EOL );
            }
            else if ( text.charAt( i ) == '\n' )
            {
                buffer.append( EOL );
            }
            else
            {
                buffer.append( text.charAt( i ) );
            }
        }

        return buffer.toString();
    }
}