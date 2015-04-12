package testing;


/**
 * Test suite to ensure the server operates under expected conditions.
 * 
 * @author Ryan Hammer
 *
 */
public class ServerTests {
//	private Server server;
//
//	/**
//	 * First test the connection of just a basic socket, with no client class.
//	 * @throws IOException 
//	 * @throws InterruptedException 
//	 */
//	/**
//	 * Setup method called before each test. This just creates a new server object.
//	 * @throws UnknownHostException 
//	 * @throws InterruptedException 
//	 */
//	@Before
//	public void setupServer() throws UnknownHostException, InterruptedException {
//		System.out.println("Setting up again!");
//		server = new Server();
//		Thread.sleep(5000);
//	}
//	/**
//	 * Tests that the server will correctly handle a single connection and process it correctly and correctly
//	 * handle a disconnect event.
//	 * @throws IOException Thrown 
//	 * @throws InterruptedException
//	 */
//	@Test
//	public void testSingleConnection() throws IOException, InterruptedException {
//		//Create the single client.
//		Client client = new Client();
//		Thread t = new Thread(client);
//		t.start();
//
//		//Give the server time to process.
//		Thread.sleep(5000);
//		
//
//		//Make sure the clientListener hashMap has a new connection
//		assertEquals(1, server.getNumberConnections());
//
//		//Make sure the broadcaster has a new connection
//		assertEquals(1, server.getBroadcaster().getClientCount());
//		
//		//Make sure that the clientID is set
//		assertEquals(1, client.getClientGameController().getClientID());
//		
//		System.out.println("Before disconnect in test!");
//		//Now test that an expected disconnection is handled correctly:
//		client.disconnect();
//		
//		//Give the server time to process.
//		Thread.sleep(1000);
//		
//		//First do client side testing:
//		//Make sure they aren't connected to the server socket.
//		assertFalse(client.isConnected());
//	
//		//Then make sure the thread is no longer running.
//		assertFalse(client.isRunning());
//		
//		//Now do server side testing.
//		//Make sure that the client's output stream is removed from the broadcaster.
//		assertEquals(0, server.getBroadcaster().getClientCount());
//		
//		//Make sure that the ClientListener was removed from the Server's map
//		assertNull(server.getClientListener(client.getClientGameController().getClientID()));
//	}
//	
//	/**
//	 * Now test having multiple clients connect at once
//	 * @throws InterruptedException 
//	 */
//	@Test
//	public void testLotsaConnections() throws InterruptedException {
//		int testNumber = 1000;
//		ArrayList<Client> clients = new ArrayList<Client>();
//		for(int i = 0; i < testNumber; i++) {
//			//Create the single client.
//			Client client = new Client();
//			Thread t = new Thread(client);
//			t.start();
//			clients.add(client);
//			Thread.sleep(500);
//		}
//		
//		//Give the server time to process.
//		Thread.sleep(1000);
//		
//		//Now go through and test them
//		int id = 1;
//		for(int i = 0; i < testNumber; i++) {
//			//Make sure that the clientID is set
//			assertEquals(id, clients.get(i).getClientGameController().getClientID());
//			id++;
//		}
//		//Make sure the clientListener hashMap has a new connection
//		assertEquals(testNumber, server.getNumberConnections());
//
//		//Make sure the broadcaster has a new connection
//		assertEquals(testNumber, server.getBroadcaster().getClientCount());
//		
//		System.out.println("There are: " + server.getNumberConnections() + " clients connected");
//	}
}

