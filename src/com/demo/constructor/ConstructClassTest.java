package com.demo.constructor;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class ConstructClassTest {

    @Test
    public void getClassInstance() throws Exception{
        ConstructClass constructClass = new ConstructClass();
        Creature creature = (Creature) constructClass.getClassInstance("com.demo.constructor.Creature");
        if (!Creature.class.isInstance(creature)) {
            throw new Exception(creature.getClass().getName() + " is not instance of " + Creature.class.getName());
        }
    }

    @Test
    public void getInstanceByConstructor() {
//        ConstructClass constructClass = new ConstructClass();
        Constructor<Creature> creatureConstructor = (Constructor<Creature>) ConstructClass.getInstanceByConstructor("com.demo.constructor.Creature");
        Creature creature = null;
        try {
            creature = creatureConstructor.newInstance("Tom", Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals(null, creature);
    }
}