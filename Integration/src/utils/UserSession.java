///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package utils;
//
///**
// *
// * @author AmineMissaoui
// */
//public final class UserSession {
//
//    private static UserSession instance;
//    private String username;
//    private Timestamp birthdate;
//    private String userRole;
//
//    private int id;
//    private int active;
//
//    private UserSession(String username) {
//        ServiceUser se = new ServiceUser();
//        try {
//            User u = se.getByUsername(username);
//            this.username = u.getUsername();
//            this.birthdate = u.getBirthdate();
//            this.userRole = u.getRole();
//            this.id=u.getId();
//            this.active = u.getActive();
//        } catch (SQLException ex) {
//            System.out.println("error in constructor" + ex.getMessage());
//        }
//    }
//
//    public static UserSession setInstance(String username) throws SQLException {
//        if (instance == null) {
//            instance = new UserSession(username);
//        }
//        return instance;
//    }
//    
//    public static UserSession getInstance(){
//        return instance;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//    
//    public Timestamp getBirthdate() {
//        return this.birthdate;
//    }
//    
//    public String getRole() {
//        return this.userRole;
//    }
//    public int getActive() {
//        return this.active;
//    }
//        
//    public void clearUserSession() {
//        username = "";
//    }
//
//    @Override
//    public String toString() {
//        return "UserSession{" + "username=" + username + '}';
//    }
//    /**
//     * @return the id
//     */
//    public int getId() {
//        return id;
//    }
//}
