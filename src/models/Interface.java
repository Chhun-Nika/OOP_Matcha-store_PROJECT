package models;

import java.time.LocalDate;
import java.util.Date;

public interface Interface {
    boolean login (String identifier , String password);
    boolean register (String Name, String Address, String Phone, String Email, String password, LocalDate dateOfBirth);
      
}