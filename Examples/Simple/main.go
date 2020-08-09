package main

import (
	"fmt"
	"net"
	"os"

	"github.com/Yukaru-san/JavaBind/Golang_Implementation/javabind"
)

func main() {
	// Start the Server and point towards the .jar file -- true enables Java output
	err := javabind.StartJavaServer("test_Program.jar", true)
	if err != nil {
		fmt.Println("Couldn't start the JavaServer. Is there already a server running?")
		return
	}

	// Setting up a callback to handle errors and client dc's
	javabind.SetDisconnectCallback(func(client *net.UDPAddr, callbackReason string) {

		switch callbackReason {
		case javabind.ClientDisconnected:
			fmt.Println("JavaBind: -- Java client connection lost --")
			break
		case javabind.InvalidJarPath:
			fmt.Println("Couldn't start the JavaServer. Is the given path correct?")
			os.Exit(1)
		}
	})

	// Handles what to do with messages received from java
	javabind.OnMessageReceived(func(msg string) {
		// Check the message's content and react
		fmt.Printf("Received: %s \n", msg)
		javabind.SendMessage("Hi from Golang!")
	})
}
