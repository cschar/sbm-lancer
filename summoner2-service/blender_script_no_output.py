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
filepath = argv[1]



try:
	bpy.context.area.type = 'VIEW_3D'
	bpy.ops.view3d.clear_render_border()
except Exception:
	print("failed to clear render bordr")

import unicodedata
def strip_accents(s):
   return ''.join(c for c in unicodedata.normalize('NFD', s)
                  if unicodedata.category(c) != 'Mn')



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
    challenger_file = glob.glob("./crawls/challenger-*.txt")[-1]
    f = open(challenger_file, 'r')
    lines = f.readlines()
    print(lines)
    names = [strip_accents(name.strip()) for name in lines]
    print(names)
    f.close()
    print("read in file {}, got names {}".format(challenger_file, names))


print("Creating text")

for idx, c in enumerate(names):
    # loc = ( idx, 3*random.random(), 3*random.random())
    loc = ( idx*4, -5, 0)

    bpy.ops.object.text_add(view_align=False,
     enter_editmode=False,
      location=loc,
       layers=(True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False))
    bpy.ops.transform.rotate(value=half_pi,  axis=(0, 0, 1))


print("Modifying text")
name_indx = 0
for ind,ob in enumerate(scene.objects):
    if ob.type == 'FONT' and ob.name.startswith("Text"):
        ob.select = True
        ob.dimensions = (3,3,3)
        # ob.rotation_euler = (90,90,0)
        
        ob.data.body = names[name_indx]
        name_indx += 1
        ob.data.extrude = 0.142
        ob.data.size = 1.54
        
        print("rotating")
        #radians
        
        # bpy.ops.transform.rotate(value=half_pi,  axis=(0, 0, 1))
        # if(random.random() < 0.5):
        # bpy.ops.transform.rotate(value=half_pi, axis=(1, 0, 0))




print("Rendering text")

for scene in bpy.data.scenes:
    scene.render.resolution_x = 1920
    scene.render.resolution_y = 1080
    scene.render.resolution_percentage = 30
    # scene.render.resolution_percentage = 100
    scene.render.image_settings.file_format = 'JPEG'
    
    # -f command deals with this
    scene.frame_start = int(start)
    scene.frame_end = int(start)

    # scene.render.filepath = "/var/tmp/renders/"+name+"/"
    scene.render.filepath = filepath
    print("rendering to" + filepath)


bpy.ops.render.render(write_still=True)