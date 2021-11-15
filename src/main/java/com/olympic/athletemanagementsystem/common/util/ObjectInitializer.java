package com.olympic.athletemanagementsystem.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectInitializer<T>{
    //generic class will be adopted to the given Object class by the user
    //Ex Apartment, Job, Inquiry etc ...
    private T newObj;
    private T oldObj;

    public ObjectInitializer(T newObj, T oldObj) {
        //new object from user, old object from db using getById function
        this.newObj = newObj;
        this.oldObj = oldObj;
    }

    public T updateObject(){
        //take all the fields of given object
        Field[] fields = oldObj.getClass().getDeclaredFields();
        for(Field f : fields){
            System.out.println(f.getName());
            try {
                if(!f.getName().equals("serialVersionUID")){
                    //f.getName gives property name Ex:- apartmentId, ownerId ...
                    //get getter and setter references using PropertyDescriptor class ...
                    PropertyDescriptor pdOld = new PropertyDescriptor(f.getName(), oldObj.getClass());
                    PropertyDescriptor pdNew = new PropertyDescriptor(f.getName(), newObj.getClass());
                    try {
                        //if not equals to old object value and new obj value is not null not null
                        //pdNew.getReadMethod().invoke(newObj) method to take the value of a object
                        if(pdNew.getReadMethod().invoke(newObj) != null
                                && !pdOld.getReadMethod().invoke(oldObj).equals(pdNew.getReadMethod().invoke(newObj))) {
                            //enable write access to object
                            Method setter = pdOld.getWriteMethod();
                            //set new object value to the old object
                            setter.invoke(oldObj, pdNew.getReadMethod().invoke(newObj));
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        //return altered old object
        return oldObj;
    }

}
