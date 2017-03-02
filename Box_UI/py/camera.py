import picamera #Pi Camera Library
from time import sleep #Sleep Library

camera = picamera.PiCamera() #New Camera Obj
camera.resolution = (3280, 2464);
camera.start_preview() #Fill screen with camera preview for demo

sleep(5) #Wait 5 seconds

camera.stop_preview() #End preview
cameraPath = '/home/pi/Desktop/8mp.jpg'
camera.capture(cameraPath)
<<<<<<< HEAD
camera.stop_preview() 
=======
camera.stop_preview() 
>>>>>>> 6a2fca55634cdbde1f9443f1b5cb6829e498272e
