// Note that you will need to create a reference to the javaBind library!
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// Create GolangBinder Object
		GolangBinder b = new GolangBinder();
	
		// Connect to the server
		b.ConnectToGolangServer();

		// Receive and send messages
		b.SendMessage("Hello from Java!");
		System.out.println(b.ReceiveNextMessage());
		b.SendMessage("Idk what to send");
		System.out.println(b.ReceiveNextMessage());
		b.SendMessage("Imma DC again!");
		System.out.println(b.ReceiveNextMessage());
		
		// Close connection
		b.CloseConnection();
	}	
}
