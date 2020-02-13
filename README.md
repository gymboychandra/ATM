# ATM(Automatic teller machine)
This project contains source code of atm project, just import as a Dynamic web project in eclipse and run on any version of tomcat server recommended(9.0).

Frontend code is in basic HTML5,CSS3, Bootstrap and JSP and JavaScript validations.
Backend is in Oracle Database & SQL,JDBC Concept and Servlet. 


TABLE on Oracle Database:-
1. SQL> desc logindetails;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 LOGINID                                   NOT NULL NUMBER(5)
 PIN                                                NUMBER(4)
 
 2.SQL> desc accdetails;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 LOGINID                                            NUMBER(5)
 PIN                                                NUMBER(4)
 ACCTYPE                                            VARCHAR2(10)
 CURBALANCE                                         NUMBER(10,2)

3.SQL> desc transhistory;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 LOGINID                                            NUMBER(5)
 ACCTYPE                                            VARCHAR2(10)
 TDATE                                              VARCHAR2(35)
 TRANSTYPE                                          VARCHAR2(10)
 TRANSAMT                                           NUMBER(10,2)
