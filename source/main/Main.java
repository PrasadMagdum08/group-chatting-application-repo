package main;

import Login.Login;
import java.util.HashMap;

class IDPassword {
    HashMap<String,String> logininfo = new HashMap<String,String>();

    public IDPassword()
    {
        logininfo.put("Kunal","2002");
        logininfo.put("Prasad","5201");
        logininfo.put("Samarjeet","0032");
    }
    protected HashMap getLogininfo()
    {
        return logininfo;
    }
}

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        IDPassword id = new IDPassword();
        
        @SuppressWarnings("unchecked")
        Login login = new Login(id.getLogininfo());
    }
}


