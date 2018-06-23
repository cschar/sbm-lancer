
#blender scene3-bezier-text.blend --background --python blender_script_anim.py -- 1 30 bob alice 

import os
import bpy
import sys
import random
import glob
import math
import unicodedata
def strip_accents(s):
   return ''.join(c for c in unicodedata.normalize('NFD', s)
                  if unicodedata.category(c) != 'Mn')


argv = sys.argv
argv = argv[argv.index("--") + 1:]  # get all args after "--"

print("received args to script:")
print(argv)  # --> ['example', 'args', '123']
start = argv[0]
end = argv[1]
filepath = argv[2]


try:
	bpy.context.area.type = 'VIEW_3D'
	bpy.ops.view3d.clear_render_border()
except Exception:
	print("failed to clear render bordr")

if len(argv) > 4:
    names = argv[3:]
else:
    print("no names given in commandline, reading file...")
    challenger_file = sorted(glob.glob("crawls/challenger-*.txt"))[-1]
    f = open(challenger_file, 'r')
    lines = f.readlines()
    print(lines)
    names = [strip_accents(name.strip()) for name in lines]
    print(names)
    f.close()
    print("read in file {}, got names {}".format(challenger_file, names))



dir = os.path.dirname(bpy.data.filepath)
if not dir in sys.path:
    sys.path.append(dir )

from blend_strat2 import run

run(names=names)


for scene in bpy.data.scenes:
    scene.render.resolution_x = 1920
    scene.render.resolution_y = 1080
    scene.render.resolution_percentage = 30
    # scene.render.resolution_percentage = 100
    scene.render.image_settings.file_format = 'JPEG'
    
    # -f command deals with this
    scene.frame_start = int(start)
    scene.frame_end = int(end)

    # scene.render.filepath = "/var/tmp/renders/test/"
    scene.render.filepath = filepath
    print("rendering to" + filepath)


bpy.ops.render.render(animation=True)