/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * from http://octagen.at/2014/10/postgresql-custom-data-types-enum-in-hibernate/
 *
 * @author yerzhan
 * @param <T>
 * @param <E>
 */
public abstract class GenericEnumType<T, E extends Enum<E>> implements UserType {

    private final int sqlType;
    private Class<E> clazz = null;
    private final HashMap<String, E> enumMap;
    private final HashMap<E, String> valueMap;

    public GenericEnumType(Class<E> clazz, E[] enumValues, int sqlType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.clazz = clazz;
        enumMap = new HashMap<>(enumValues.length);
        valueMap = new HashMap<>(enumValues.length);

        for (E e : enumValues) {

            enumMap.put(e.toString(), e);
            valueMap.put(e, e.toString());
        }
        this.sqlType = sqlType;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object obj) throws HibernateException {
        return obj;
    }

    @Override
    public Serializable disassemble(Object obj) throws HibernateException {
        return (Serializable) obj;
    }

    @Override
    public boolean equals(Object obj1, Object obj2) throws HibernateException {
        if (obj1 == obj2) {
            return true;
        }

        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    @Override
    public int hashCode(Object obj) throws HibernateException {
        return obj.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner) throws HibernateException, SQLException {
        String value = rs.getString(names[0]);
        if (!rs.wasNull()) {
            return enumMap.get(value);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object obj, int index, SessionImplementor si) throws HibernateException, SQLException {
        if (obj == null) {
            ps.setNull(index, sqlType);
        } else {
            ps.setObject(index, valueMap.get(obj), sqlType);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @Override
    public Class<E> returnedClass() {
        return clazz;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{sqlType};
    }
}
