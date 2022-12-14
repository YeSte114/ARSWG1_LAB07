package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class FilterRedundanciesTest {

    @Test
    public void onlyPointRedundanciesTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices gc= ac.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0), new Point(20, 20), new Point(20, 20)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        gc.addNewBlueprint(bp);
        Blueprint as = gc.getBlueprint("john", "thepaint");

        assertEquals("", as.getPoints().size(), 2);
    }

    @Test
    public void notPointRedundanciesTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices gc= ac.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0), new Point(10, 10), new Point(20, 20)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        gc.addNewBlueprint(bp);
        Blueprint as = gc.getBlueprint("john", "thepaint");

        assertEquals("", as.getPoints().size(), 3);
    }

    @Test
    public void allButOnePointRedundanciesTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices gc= ac.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(10, 10), new Point(10, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        gc.addNewBlueprint(bp);
        Blueprint as = gc.getBlueprint("john", "thepaint");

        assertEquals("", as.getPoints().size(), 1);
    }

}