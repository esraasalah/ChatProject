
package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RequestTableOperations {
       
    private static RequestTableOperations requestTableOperations;
    
    public static RequestTableOperations getInstance(){
        
        if(requestTableOperations==null)
            requestTableOperations=new RequestTableOperations();
        
        return requestTableOperations;
    }
    
    public void sendRequest (String senderEmail, String recieverEmail){
         
            //String query;
            String query2;
            int senderId=0;
            int recieverId=0;
           /* query="select "  +  DatabaseContract.UserTableContract.email  + "  " +
                    "from  " +  DatabaseContract.UserTableContract.tableName  + "  " +
                    "where  " +  DatabaseContract.UserTableContract.email + " = " + "'" + recieverEmail + "'" ;
            System.out.println(query);
            ResultSet rs=DatabaseHandler.getInstance().select(query);
            */
           System.out.println(recieverEmail);
           recieverId=UserTableOperations.getInstance().emailToId(recieverEmail);
           System.out.println(recieverId);
            if(recieverId!=-1)
            {
                //System.out.println("yes rs has next");
                senderId=UserTableOperations.getInstance().emailToId(senderEmail);
                //System.out.println(senderId);
                //recieverId=UserTableOperations.getInstance().emailToId(recieverEmail);
                
                query2="insert into " + DatabaseContract.RequesttableContract.tableName + " " +"(" +
                        DatabaseContract.RequesttableContract.senderId + " , " +
                        DatabaseContract.RequesttableContract.recieverId + " "+ ") "
                        + "values (" + senderId + "," + recieverId + ");" ;
                
                System.out.println(query2);
                DatabaseHandler.getInstance().insert(query2);   
            }
            else
                
            {
                System.out.println("not a user");
            }
       }
    
    
    
    public void comfirmRequest(String senderEmail, String recieverEmail)
    {
               String query;
               query="insert into " + DatabaseContract.FriendTableContract.tableName + " "
                       + "values(" + UserTableOperations.getInstance().emailToId(senderEmail) 
                       + ","  +UserTableOperations.getInstance().emailToId(recieverEmail) + 
                           ")";
              System.out.println(query);
              DatabaseHandler.getInstance().insert(query);
      
             deleteRequest(senderEmail, recieverEmail);
             
             
    
    }
    
    public void deleteRequest(String senderEmail, String recieverEmail)
            
    {
                String query;
                query="delete from "+DatabaseContract.RequesttableContract.tableName + " "
                        +"where  "+DatabaseContract.RequesttableContract.senderId   + " = "
                        +UserTableOperations.getInstance().emailToId(senderEmail) + " " +"and " +
                        DatabaseContract.RequesttableContract.recieverId   + " = "
                        +UserTableOperations.getInstance().emailToId(recieverEmail)  ;
        System.out.println(query);
        DatabaseHandler.getInstance().delete(query);
        
    }
}
