package gameserver;

class KeyGenerator
{
    private static KeyGenerator flag = null;  
    private KeyGenerator()
    {}

    static KeyGenerator getObject()
    {
        if(flag == null)
            flag = new KeyGenerator();

        return flag;
    } 

    //synchronized == 1 thread at a time
    synchronized String getKey()
    {
        StringBuffer sbuff = new StringBuffer();
        sbuff.append(System.currentTimeMillis());
        sbuff.reverse();
        return sbuff.toString();
    }
}

