import os
import sys
import re

template = open("module_template.ts", "r")
content = template.read()

os.chdir("src/app/theme/pages/aside-left-display-disabled")
lsAside = os.listdir()

def upperReplacement(match):
    return match.group(2).upper()

namePattern = r"(\-(\w))+"
nameRegex = re.compile(namePattern)

def parseName(name):
    res = nameRegex.sub(upperReplacement, name)
    res = res[0].upper() + res[1:]
    return res

force = False
if len(sys.argv) > 1 and sys.argv[1] == "--force":
    force = True

for child in lsAside:
    if(os.path.isfile(child)):
        continue
    
    modulePath = "{}/{}.module.ts".format(child, child)
    if(not force and os.path.exists(modulePath)):
        print("File {}.module.ts already exists".format(child))
        continue

    print("Creating {0}/{0}.module.ts".format(child))
    newFile = open(modulePath, "w+")

    newContent = content.replace("{ComponentName}", parseName(child)).replace("{ComponentPath}", child)
    
    newFile.writelines(newContent)
    newFile.close()
    