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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.util.ObjectContracts;

@SuppressWarnings("deprecation")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Unique(name = "SimpleObject_name_UNQ", members = { "name" })

@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(
            name="findByName", language="JDOQL",
            value="SELECT "
                    + "FROM dom.simple.Pet "
                    + "WHERE name.matches(:name) ")
                   
})
@Bookmarkable
public class Pet implements Comparable<Pet> {

	// region > name (property)

	private String name;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@Title(sequence = "1")
	@MemberOrder(sequence = "1")
	@MaxLength(10)
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	// endregion

	// //////////////////////////////////////
	// speaker (property)
	// //////////////////////////////////////

	private Owner owner;

	@javax.jdo.annotations.Column(allowsNull = "true")
	@MemberOrder(sequence = "2")
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(final Owner owner) {
		this.owner = owner;
	}

	public List<Owner> autoCompleteOwner(String search){
		return owners.findByName(search);
	}

	// region > compareTo

	@Override
	public int compareTo(Pet other) {
		return ObjectContracts.compare(this, other, "name");
	}

	// endregion

	// region > injected services

	@javax.inject.Inject
	@SuppressWarnings("unused")
	private DomainObjectContainer container;

	// endregion
	@javax.inject.Inject
	@SuppressWarnings("unused")
	private Owners owners;

}
