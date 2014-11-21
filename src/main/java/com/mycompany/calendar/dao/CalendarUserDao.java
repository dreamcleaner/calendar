package com.mycompany.calendar.dao;

import java.util.List;

import com.mycompany.calendar.domain.CalendarUser;

public interface CalendarUserDao {
    public CalendarUser findUser(int id);

    public CalendarUser findUserByEmail(String email);

    public List<CalendarUser> findUsersByEmail(String partialEmail);

    public int createUser(CalendarUser user);
    
    public List<CalendarUser> findAllusers();
    
    public void deleteAll();
}
