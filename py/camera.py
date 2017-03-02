import picamera #Pi Camera Library
from time import sleep #Sleep Library

camera = picamera.PiCamera() #New Camera Obj
camera.resolution = (3280, 2464);
camera.start_preview() #Fill screen with camera preview for demo

sleep(5) #Wait 5 seconds

camera.stop_preview() #End preview
cameraPath = '/home/pi/Desktop/8mp.jpg'
camera.capture(cameraPath)
camera.stop_preview() 