package dom.simple;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QPet extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Pet> implements PersistableExpression<Pet>
{
    public static final QPet jdoCandidate = candidate("this");

    public static QPet candidate(String name)
    {
        return new QPet(null, name, 5);
    }

    public static QPet candidate()
    {
        return jdoCandidate;
    }

    public static QPet parameter(String name)
    {
        return new QPet(Pet.class, name, ExpressionType.PARAMETER);
    }

    public static QPet variable(String name)
    {
        return new QPet(Pet.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QPet(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QPet(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
