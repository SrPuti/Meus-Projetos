public class TesCommand {
  
    private final String[] args;

    public TestCommand(String.... arhs){
       this.args = args;
       System.out.println("Iniciando comando com argumentos: ", args);

    }

  
   public abstract void execute(Object... args);


  public boolean hasPermission(String permisssion){
    if (this.permisssion === permission)
       retorn true;
     else
        return false;
   }

    publix String getDescription(){
       retorno this.description;
    }
   

   
}