/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package integration.tests.smoke;

import dom.simple.Pet;
import dom.simple.Pets;
import fixture.simple.scenario.PetsFixture;
import fixture.simple.PetsTearDownFixture;
import integration.tests.SimpleAppIntegTest;

import javax.inject.Inject;
import org.junit.Test;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleObjectTest extends SimpleAppIntegTest {

    @Inject
    FixtureScripts fixtureScripts;
    @Inject
    Pets pets;

    FixtureScript fixtureScript;

    public static class Name extends SimpleObjectTest {

        @Test
        public void exists() throws Exception {

            // given
            fixtureScript = new PetsFixture();
            fixtureScripts.runFixtureScript(fixtureScript, null);

            final Pet simpleObjectPojo =
                    fixtureScript.lookup("simple-objects-fixture/simple-object-for-foo/item-1", Pet.class);

            // when
            assertThat(simpleObjectPojo, is(not(nullValue())));
            final Pet simpleObjectWrapped = wrap(simpleObjectPojo);

            // then
            assertThat(simpleObjectWrapped.getName(), is("Foo"));
        }

        @Test
        public void doesNotExist() throws Exception {

            // given
            fixtureScript = new PetsTearDownFixture();
            fixtureScripts.runFixtureScript(fixtureScript, null);

            // when
            Pet simpleObjectPojo = fixtureScript.lookup("non-existent", Pet.class);

            // then
            assertThat(simpleObjectPojo, is(nullValue()));

        }
    }

}