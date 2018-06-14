#blender --background --python myscript.py

#blender myscene.blend --background --python myscript.py

#blender -b jaran_no_tail.blend --python ~/mdev/notes/blender_script.py -- 4 5
#nohup blender -b /var/tmp/jaran_no_tail_purple.blend --python blender_script.py -- 1 440 &>/dev/null &

import bpy
import sys
argv = sys.argv
argv = argv[argv.index("--") + 1:]  # get all args after "--"

print("received args to script:")
print(argv)  # --> ['example', 'args', '123']
start = argv[0]
end = argv[1]


try:
	bpy.context.area.type = 'VIEW_3D'
	bpy.ops.view3d.clear_render_border()
except Exception:
	print("failed to clear render bordr")





import bpy
import random
import glob
import math

half_pi = 0.5 * math.pi

scene = bpy.context.scene


# image_names = glob.glob("/Users/myuser/Blender/images/*")
if len(argv) > 3:
    names = argv[2:]
else:
    print("no names given")
    names = ["bob", "sally","alisha","manny"]

print("Creating text")

for c in names:
    loc = (3*random.random(), 3*random.random(), 3*random.random())

    bpy.ops.object.text_add(view_align=False,
     enter_editmode=False,
      location=loc,
       layers=(True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False))


print("Modifying text")
for ind,ob in enumerate(scene.objects):
    if ob.type == 'FONT' and ob.name.startswith("Text"):
        ob.select = True
        ob.dimensions = (3,3,3)
        # ob.rotation_euler = (90,90,0)
        ob.data.body = names[int(random.random()*len(names))]
        ob.data.extrude = 0.142
        ob.data.size = 1.54
        
        print("rotating")
        #radians
        bpy.ops.transform.rotate(value=half_pi,  axis=(0, 0, 1))
        
        # if(random.random() < 0.5):
        bpy.ops.transform.rotate(value=half_pi, axis=(1, 0, 0))




print("Rendering text")

for scene in bpy.data.scenes:
    scene.render.resolution_x = 1920
    scene.render.resolution_y = 1080
    scene.render.resolution_percentage = 30
    # scene.render.resolution_percentage = 100
    scene.render.image_settings.file_format = 'JPEG'
    
    # -f command deals with this
    scene.frame_start = int(start)
    scene.frame_end = int(end)

    try:
    	name = bpy.path.basename(bpy.context.blend_data.filepath).split('.blend')[0]
    except Exception:
    	name = 'foo'
    	print("Couldnt find filepath name")

    print("rendering to %s" % name)
    # scene.render.filepath = "/var/tmp/renders/"+name+"/"
    scene.render.filepath = "./renders/"+name+"/"



bpy.ops.render.render(animation=True)