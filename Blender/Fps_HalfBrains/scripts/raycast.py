import bge
from bge import render
from mathutils import Vector
#render.showMouse(1)
def main():
    
    scene = bge.logic.getCurrentScene()
    objList = scene.objects
    rayhitobj = objList["rayhitname"]
    cont = bge.logic.getCurrentController()
    own = cont.owner

    force = [300,400,20]
    local = True
    space = 0.003
    
    ray = cont.sensors["ray"] 
    mouse = cont.sensors["mouse"]
    spawn = cont.actuators["spawn"]
    ammo = own["ammo_count"]
    
    if ray.positive:
        pos = ray.hitPosition
        #render.drawLine(own.worldPosition,pos,[1,0,0])  
        pos_vec = Vector(pos)
        normal_vec = Vector(ray.hitNormal)    
        target = ray.hitObject
        own["name"] = target.name
        rayhitobj.text = target.name
        if mouse.positive and ammo>0:
            
            spawn.instantAddObject()
            bullet_hole = spawn.objectLastCreated
            bullet_hole.alignAxisToVect(normal_vec.xyz, 2, 1)
            normal_vec.magnitude = space   
            bullet_hole.worldPosition = (pos_vec + normal_vec).xyz
            bullet_hole.setParent(target,1,0)
            ray.hitObject.applyForce(force,local)
            target['health']-=1
            

main()
