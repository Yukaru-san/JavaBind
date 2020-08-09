public class Main {
	
	public static void main(String[] args) throws Exception {
		
		GolangBinder b = new GolangBinder();
	
		try {
			b.ConnectToGolangServer(new Main_MessageHandler(b));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Receive the next incoming message
		b.SendMessage("Hello from Java!");
		System.out.println(b.ReceiveNextMessage());
		b.SendMessage("Idk what to send");
		System.out.println(b.ReceiveNextMessage());
		b.SendMessage("Imma DC again!");
		System.out.println(b.ReceiveNextMessage());
		
		b.CloseConnection();
	}	
}
