# JavaBind

JavaBind is used to communicate between Golang and Java using UDP Sockets.

## Preparations - Golang

First up you will need the javabind library. You can install it using:<br>
```go get -u github.com/Yukaru-san/JavaBind/Golang_Implementation/javabind```

Import it into your project and you can get started. Examples can be found below.

## Preparations - Java

In Java you will have to either put the library's source code into your project or
set a reference to it's .jar file<br>
In Eclipse the latter can be archived by doing the following:

1. Press Alt + Enter on your project within the Package Explorer
2. Select "Java Build Path"
3. Press "Add External JARs..."
4. Select the library's jar file

When you are done the project will be correctly set up and you can directly start using
JavaBind.

The libary can be found [here](https://github.com/Yukaru-san/JavaBind/blob/master/Java_Implementation/javaBind_lib.jar)
or just look into the <b>Java_Implementation</b> directory
  
## Example - Golang

You can find examples within the <b>Examples</b> directory. Anyway, the simplest one can be found [here](https://github.com/Yukaru-san/JavaBind/blob/master/Examples/Simple/main.go)
<br><br>

<b><i>Importing the project:</b></i><br>
```import "github.com/Yukaru-san/JavaBind/Golang_Implementation/javabind"```<br><br>

<b><i>Starting the Server:</b></i><br>
Use ```javabind.StartJavaServer(string)``` to start the UDP Server. Takes the .jar path as argument<br><br>

<b><i>Receiving Messages:</b></i>
<br>Either use ```ReceiveMessage()``` to get the <b>next</b> incoming message
<br>Or use ```OnMessageReceived(func(string))``` to handle <b>every</b> message directly using the given func<br><br>

<b><i>Stop Receiving Messages:</b></i><br>
Since message reading will block the current thread you can use this function to unblock them:<br>
```StopMessageReceiver()```
<br>New incoming messages won't be read until a new Receive function is called<br><br>

<b><i>Send Messages:</b></i><br>
 ```SendMessage(string)``` sends the given String to the connected Java client<br><br>
 
<b><i>Runtime Callbacks:</b></i><br>
Errors and disconnecting clients will try to give a callback in order to inform the main process of what happened.<br>
It is not mandatory but can be set using ```SetDisconnectCallback(func(*net.UDPAddr, string))```<br>
The string will always be a written out callback reason. Everyone of those can be found as a public variable in javabind
and therefore <b>be changed during runtime</b>

## Example - Java

You can find examples within the <b>Examples</b> directory. Anyway, the simplest one can be found [here](https://github.com/Yukaru-san/JavaBind/blob/master/Examples/Simple/.jar%20src/Main.java)
<br><br>

In order to run correctly your java-side project needs to compiled as a .jar file and <b>contain the main method</b><br>

<b><i>The Client Object:</b></i><br>
The library uses the GolangBinder as an Object that everything resolves around.<br>
Create it using:<br>
```GolangBinder b = new GolangBinder();```
Note that we call it <b>b</b> here.<br><br>

<b><i>Starting the Client:</b></i><br>
Starting up the client is as simple as calling ```b.ConnectToGolangServer()```<br>
However there are to types of this function - one requiring the <b>Bindings</b> interface.<br>
The Bindings interface is currently used as a general message handler and is only required if you want
to use the ```StartMessageReceiver()``` function.<br>
If it is hard to comprehend you should take a look into the Examples directory. (Note: currently TODO!)
<br><br>

<b><i>Receiving Messages:</b></i><br>
Just like in Golang there are two ways to receive messages.<br>
The direct way to receive a single message and continue on to use a MessageReceiver that handles every incoming message.<br>
To simply get the next incoming message, use: ```b.ReceiveNextMessage()```<br><br>
To handle <b>every</b> message however, you will need to set up your Bindings interface.
<br>
If not already done using the constructor, do so by calling ```b.SetMessageBindings(Bindings)```
<br>
The Bindings interface can be found using GolangBinder.<br>
<br>Bindings contain the method ```OnMessageReceived(String)``` which should be overriden, as it is used to handle incoming messages.<br>
Anyway, if you set it up, you can simply call ```b.StartMessageReceiver()```<br>
There is no need to create a thread, as <b>this will create one for you</b>
<br><br><br>
<b><i>Stop Receiving Messages:</b></i><br>
Since message reading will block the current thread you can use this function to unblock them:<br>
```b.CloseConnection()```
<br>New incoming messages won't be read until a new Receive function is called
<br><br>
<b><i>Send Messages:</b></i><br>
 ```b.SendMessage(string)``` sends the given String to the connected Golang server<br><br>
 
<b><i>Keeping the main process alive:</b></i><br>
In order to keep the program and therefore the connection running, you will need to keep the main Thread busy.
If you don't currently need it, you can call ```b.KeepAlive()``` to create a blockade.<br>
To continue running the main Thread you can simply use: ```b.StopKeepAlive()```
