'''
    @summary: Gets information from Giger's 5e API and prompts the user for other inputs,
              to build text files. Poorly written. Not meant for public use. 
'''

import requests # requests.get()
import json # json.loads()

def listToString(li):
    listr = ""
    for elem in li:
        listr += str(elem) + "-"
    listr = listr.strip('-')
    listr += "/"
    return listr

# *UPDATE: Fix repetitiveness, write more functions
def getRace(race):

    url = "http://dnd5eapi.co/api/races/" + race
    response = requests.get(url)

    data = response.text
    parsed = json.loads(data)

    racename = parsed["name"]
    print(racename)

    stat_bonuses = parsed["ability_bonuses"]

    traits = []
    for elem in parsed["traits"]:
        traits += [elem["name"]]
    
    print("Enter the male first name options one at a time. Enter 'x' to end.")
    mnames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        mnames += [tempstr]
        tempstr = input("Name: ")

    print("Enter the female first name options one at a time. Enter 'x' to end.")
    fnames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        fnames += [tempstr]
        tempstr = input("Name: ")

    print("Enter the gender neutral first name options one at a time. Enter 'x' to end.")
    nnames = []
    tempstr = input("Names: ")
    while tempstr != 'x' :
        nnames += [tempstr]
        tempstr = input("Names: ")

    mmaxheight = int(input("Enter max height (in inches): "))
    fmaxheight = int(.9 * mmaxheight)
    nmaxheight = int(.95 * mmaxheight)

    fminheight = int(input("Enter min height (in inches): "))
    mminheight = int(1.1 * fminheight)
    nminheight = int(1.05 * fminheight)

    mheight = range(mminheight, mmaxheight, 1)
    fheight = range(fminheight, fmaxheight, 1)
    nheight = range(nminheight, nmaxheight, 1)

    mmaxweight = int(input("Enter max weight (in pounds): "))
    fmaxweight = int(.9 * mmaxweight)
    nmaxweight = int(.95 * mmaxweight)

    fminweight = int(input("Enter min weight (in pounds): "))
    mminweight = int(1.1 * fminweight)
    nminweight = int(1.05 * fminweight)

    mweight = range(mminweight, mmaxweight, 3)
    fweight = range(fminweight, fmaxweight, 3)
    nweight = range(nminweight, nmaxweight, 3)
    
    print("Enter the surname options one at a time. Enter 'x' to end.")
    snames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        snames += [tempstr]
        tempstr = input("Name: ")

    print("Enter the eye color options one at a time. Enter 'x' to end.")
    eyecolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        eyecolors += [tempstr]
        tempstr = input("Color: ")

    print("Enter the hair color options one at a time. Enter 'x' to end.")
    haircolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        haircolors += [tempstr]
        tempstr = input("Color: ")

    print("Enter the skin color options one at a time. Enter 'x' to end.")
    skincolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        skincolors += [tempstr]
        tempstr = input("Color: ")

    maxage = int(input("Enter maximum age: "))
    minage = int(input("Enter minimum age: "))
    ages = range(minage, maxage, int((maxage-minage)/20))

    filestr = ""
    filestr += listToString(mnames) + listToString(mheight) + listToString(mweight)
    filestr += listToString(fnames) + listToString(fheight) + listToString(fweight)
    filestr += listToString(nnames) + listToString(nheight) + listToString(nweight)
    filestr += listToString(ages) + listToString(eyecolors) + listToString(haircolors)
    filestr += listToString(snames) + listToString(stat_bonuses) + listToString(skincolors)
    filestr += listToString(traits)

    filename = racename + ".txt"
    file = open(filename,'w')
    file.write(filestr)

def getClass(classname):
    url = "http://dnd5eapi.co/api/classes/" + classname
    response = requests.get(url)

    data = response.text
    parsed = json.loads(data)

    classname = parsed["name"]
    
    hd = str(parsed["hit_die"])

    skills = []
    for elem in parsed["proficiency_choices"][0]["from"]:
        skills += [elem["name"][7:]]

    level_url = url + "/levels"
    level_data = requests.get(level_url).text
    level_parse = json.loads(level_data)
    abilities = []
    for i in range(0,5):
        thislevel = []
        for fc in level_parse[i]["feature_choices"]:
            thislevel += [fc["name"]]
        for f in level_parse[i]["features"]:
            thislevel += [f["name"]]
        abilities += [thislevel]

    print("Enter the armor options one at a time. Enter 'x' to end.")
    armor = []
    tempstr = input("Armor: ")
    while tempstr != 'x' :
        armor += [tempstr]
        tempstr = input("Armor: ")
    
    print("Enter the weapon options one at a time. Enter 'x' to end.")
    weapons = []
    tempstr = input("Weapon: ")
    while tempstr != 'x' :
        weapons += [tempstr]
        tempstr = input("Weapon: ")

    print("Enter the primary stats:")
    prime = input("Prime: ")
    second = input("Secondary: ")
    primes = [prime, second]

    filename = classname + ".txt"
    file = open(filename,'w')
    
    filestr = "" # Writing the information as one big string to eliminate the presence of newline chars
    
    for level in abilities:
        for ability in level:
            filestr += ability + ", "
        filestr = filestr.strip(' ')
        filestr = filestr.strip(',')
        filestr += "/"

    filestr += listToString(armor)
    filestr += listToString(weapons)
    filestr += listToString(skills)
    filestr += listToString(primes) + hd

    file.write(filestr)
    
