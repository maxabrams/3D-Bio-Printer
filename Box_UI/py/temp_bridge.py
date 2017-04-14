import serial

ser = serial.Serial('/dev/ttyACM0', 9600)

message = ser.readline()
print(message)