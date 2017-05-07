######################################################
#
#    Movie.py        Blender 2.50
#
#    Tutorial for using Video.py can be found at
#
#    www.tutorialsforblender3d.com
#
#    Released under the Creative Commons Attribution 3.0 Unported License.	
#
#    If you use this code, please include this information header.
#
######################################################

#import GameLogic
import GameLogic

# get current scene
scene = GameLogic.getCurrentScene()

# get the current controller
controller = GameLogic.getCurrentController()

# get object script is attached to
obj = controller.owner

# check to see video has been added
if "Video" in obj:
	
	# get video 
	video = obj["Video"]
	
	# update the video 
	video.refresh(True)

						
# if video hasn't been added 
else:

	# import VideoTexture module
	import VideoTexture
	
	# get matID for the movie screen	
	matID = VideoTexture.materialID(obj, "MA" + obj['material'])

	# get the texture
	video = VideoTexture.Texture(obj, matID)
	
	movieName = obj['movie']
	
	# get movie path
	movie = GameLogic.expandPath('//' + movieName)
	
	# get movie
	video.source = VideoTexture.VideoFFmpeg(movie)
	
	# set scaling
	video.source.scale = True	
	
	# save mirror as an object variable
	obj["Video"] = video
	
	# check for optional loop property
	if "loop" in obj:
		
		# loop it forever
		if obj['loop'] == True:
			video.source.repeat = -1
	
		# no looping
		else:
			video.source.repeat = 0
	
	# start the video
	video.source.play()
if video.source.status == 3:
    controller.activate("NextScene")