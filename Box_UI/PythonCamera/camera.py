import picamera #Pi Camera Library
from time import sleep #Sleep Library

camera = picamera.PiCamera() #New Camera Obj

camera.start_preview() #Fill screen with camera preview for demo

sleep(5) #Wait 5 seconds

camera.stop_preview() #End preview

camera.capture('pic1.jpg'); #Take one photo and save it to pic1.jpg