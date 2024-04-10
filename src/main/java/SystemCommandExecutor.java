public class SystemCommandExecutor {
        public static void main(String[] args) {
            try {
                Runtime.getRuntime().exec("shutdown -s");
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
