/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Types;

/**
 *
 * @author yerzhan
 */
public class UserGroupEnumType extends GenericEnumType<String, UsersGroup.Group> {

    public UserGroupEnumType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        super(UsersGroup.Group.class, UsersGroup.Group.values(), Types.OTHER);
    }
}
