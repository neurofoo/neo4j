/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.values;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.neo4j.values.Values.booleanValue;
import static org.neo4j.values.Values.byteValue;
import static org.neo4j.values.Values.doubleValue;
import static org.neo4j.values.Values.floatValue;
import static org.neo4j.values.Values.intValue;
import static org.neo4j.values.Values.longValue;
import static org.neo4j.values.Values.shortValue;
import static org.neo4j.values.Values.stringValue;

public class AnyValuesTest
{

    @Test
    public void shouldNotEqualVirtualValue()
    {
        VirtualValue virtual = new MyVirtualValue( 42 );

        assertNotEqual( booleanValue( false ), virtual );
        assertNotEqual( byteValue( (byte)0 ), virtual );
        assertNotEqual( shortValue( (short)0 ), virtual );
        assertNotEqual( intValue( 0 ), virtual );
        assertNotEqual( longValue( 0 ), virtual );
        assertNotEqual( floatValue( 0.0f ), virtual );
        assertNotEqual( doubleValue( 0.0 ), virtual );
        assertNotEqual( stringValue( "" ), virtual );
    }

    private void assertNotEqual( AnyValue a, AnyValue b )
    {
        assertFalse( "should not be equal", a.equals( b ) );
        assertFalse( "should not be equal", b.equals( a ) );
    }
}
