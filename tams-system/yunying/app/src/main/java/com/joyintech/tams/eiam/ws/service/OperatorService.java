package com.joyintech.tams.eiam.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://iam.cebbank.com")
public interface OperatorService {

    @WebMethod
    String findUser(@WebParam(name = "arg0", targetNamespace = "http://iam.cebbank.com") String arg0);

    @WebMethod
    String addUserInfo(@WebParam(name = "arg0", targetNamespace = "http://iam.cebbank.com") String arg0);

    @WebMethod
    String delUser(@WebParam(name = "arg0", targetNamespace = "http://iam.cebbank.com") String arg0);

    @WebMethod
    String modifyUserInfo(@WebParam(name = "arg0", targetNamespace = "http://iam.cebbank.com") String arg0);

    @WebMethod
    String setState(@WebParam(name = "arg0", targetNamespace = "http://iam.cebbank.com") String arg0);

    @WebMethod
    String stateQuery();

}
