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
package dom.simple;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.DateTime;
import org.joda.time.LocalDate;

@DomainService(repositoryFor = Visit.class)
@DomainServiceLayout(menuOrder = "30")
public class Visits {

	// region > listAll (action)

	@SuppressWarnings("deprecation")
	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "1")
	public List<Visit> listAll() {
		return container.allInstances(Visit.class);
	}

	// endregion

	// region > create (action)
	@MemberOrder(sequence = "2")
	public Visit create(
			final @ParameterLayout(named = "Diagnosis") String diagnosis,
			final @ParameterLayout(named = "CheckIn") LocalDate checkin,
			final @ParameterLayout(named = "CheckOut") LocalDate checkout) {
		final Visit obj = container.newTransientInstance(Visit.class);
		obj.setDiagnosis(diagnosis);
		obj.setCheckIn(checkin);
		obj.setCheckOut(checkout);
		container.persistIfNotAlready(obj);
		return obj;
	}
	
	// endregion

	// region > injected services

	@javax.inject.Inject
	DomainObjectContainer container;
	


	// endregion

}
