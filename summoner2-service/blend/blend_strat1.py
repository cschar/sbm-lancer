#blender --background --python myscript.py
#blender myscene.blend --background --python myscript.py

import bpy
import sys


import bpy
import random
import glob
import math


def run(names=None):
    if not names:
        names = ["hey hello hi ", "hola hola hola "]

    print("Creating text")

    for idx, c in enumerate(names):
        # loc = ( idx, 3*random.random(), 3*random.random())
        loc = ( idx*4, -5, 0)

        bpy.ops.object.text_add(view_align=False,
        enter_editmode=False,
        location=loc,
        layers=(True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False))
        half_pi = 0.5 * math.pi
        bpy.ops.transform.rotate(value=half_pi,  axis=(0, 0, 1))


    print("Modifying text")
    name_indx = 0
    processed = []

    for ind, ob in enumerate(bpy.context.scene.objects):
        if ob.type == 'FONT' and ob.name.startswith("Text"):
            # enumreate evaulates lazy, objects get shuffled
            if ob.name not in processed:
                processed.append(ob.name) 
            else:
                print("skipping " + ob.name)
                continue   
            
            for obj in bpy.data.objects:
                obj.select = False
            ob.select = True
            bpy.context.scene.objects.active = ob

            

            # bpy.ops.object.constraint_add(type='FOLLOW_PATH')
            # ob.constraints["Follow Path"].target = bpy.data.objects["BezierCircle.001"]
            # ob.constraints["Follow Path"].offset = random.random() * 100
            # bpy.ops.object.location_clear(clear_delta=False)

            ob.dimensions = (3,3,3)
            ob.data.extrude = 0.142
            ob.data.size = 1.54
            ob.data.body = names[name_indx]
            print("name_indx: " + str(name_indx) +"\n\n")
            name_indx += 1

            #radians
            
            # bpy.ops.transform.rotate(value=half_pi,  axis=(0, 0, 1))
            

