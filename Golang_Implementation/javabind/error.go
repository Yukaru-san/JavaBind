package javabind

// MessageError handles errors using the message functions
type MessageError struct {
	ErrorType int
}

func (err *MessageError) Error() string {
	switch err.ErrorType {
	case 0:
		return "OnMessageReceived is already running. Only one is possible! You can close one by using StopMessageReceiver"
	case 1:
		return "ReceiveMessage is already running. Only one is possible! You can close one by using StopMessageReceiver"
	default:
		return "An unknown error occured"
	}
}
