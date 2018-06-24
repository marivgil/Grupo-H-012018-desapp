package tests.model;

import builders.PostBuilder;
import builders.UserBuilder;
import builders.VehicleBuilder;
import model.*;
import model.exceptions.NoCoordsEnoughException;
import model.exceptions.TimeOutOfRangeException;
import model.exceptions.UserBlockedException;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PostTestCase {

    @Test(expected=TimeOutOfRangeException.class)
    public void shouldThrowAnExceptionWhenAPostHasMoreThan5Days(){
        PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MAX).build();
    }

    //TODO: cambiar por un localdate que tenga media hora de diferencia.
    @Test(expected=TimeOutOfRangeException.class)
    public void shouldThrowAnExceptionWhenAPostHasLessThan1Hour(){
        PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MIN).build();
    }

    @Test(expected=UserBlockedException.class)
    public void shouldThrowAnExceptionWhenTheUserIsDisabled(){
        PostBuilder.
                aPost().
                withUserDisabled()
                .build();
    }
    
    @Test(expected=NoCoordsEnoughException.class)
    public void shouldThrowAnExceptionWhenNoSetPickUpCoord(){
    	PostBuilder
    				.aPost()
    				.withPickUpCoord(null)
    				.build();
    }
/*
    @Test(expected=NoCoordsEnoughException.class)
    public void shouldThrowAnExceptionWhenNoSetReturnCoords(){
    	Coord returnCoords= new Coord();
    	PostBuilder
    				.aPost()
    				.withReturnCoords(returnCoords)
    				.build();
    }
*/
    @Test
    public void shouldBeCreateAPost(){
        Vehicle vehicle= VehicleBuilder.aVehicle().build();
        User ownerUser = UserBuilder.anUser().withCredit(100).build();

        Coord coord = new Coord(1,177);
        assertNotNull(ownerUser.post(vehicle, coord,
                coord, LocalDateTime.now(),
                LocalDateTime.now().plusDays(3L), 100));

    }

    @Test
    public void shouldBeCreateAPostInStateAvailable(){
        Vehicle vehicle= VehicleBuilder.aVehicle().build();
        User ownerUser = UserBuilder.anUser().withCredit(100).build();

        Coord coord = new Coord(1,177);
        Post p = ownerUser.post(vehicle, coord,
                coord, LocalDateTime.now(),
                LocalDateTime.now().plusDays(3L), 100);

        assertEquals(p.getPostState().toString(),"available");

    }

    @Test
    public void shouldBeChangeStateToReservedAfterReservationThePost(){
        Vehicle vehicle= VehicleBuilder.aVehicle().build();
        User ownerUser = UserBuilder.anUser().withCredit(100).build();
        User tenantUser = UserBuilder.anUser().withCredit(100).build();
        Coord coord = new Coord(1,177);
        Post p = ownerUser.post(vehicle, coord,
                coord, LocalDateTime.now(),
                LocalDateTime.now().plusDays(3L), 100);

        tenantUser.rent(p, LocalDateTime.now(), LocalDateTime.now().plusDays(3L));

        assertEquals(p.getPostState().toString(),"reserved");

    }
}
