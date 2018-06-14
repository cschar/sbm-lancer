import bpy
import random
import glob

scene = bpy.context.scene


# image_names = glob.glob("/Users/myuser/Blender/images/*")
names = ["bob", "sally","alisha","manny"]


for c in names:
    loc = (3*random.random(), 3*random.random(), 3*random.random())

    bpy.ops.object.text_add(view_align=False,
     enter_editmode=False,
      location=loc,
       layers=(True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False))


for ind,ob in enumerate(scene.objects):
    if ob.type == 'FONT' and ob.name.startswith("Text"):
        ob.select = True
        ob.dimensions = (3,3,3)
        # ob.rotation_euler = (90,90,0)
        ob.data.body = names[int(random.random()*len(names))]
        ob.data.extrude = 0.042
        ob.data.size = 1.54