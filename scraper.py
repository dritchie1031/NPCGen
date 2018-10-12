# scraper.py
# author: Daniel Ritchie
#
# This program is meant to scrape the web (specifically Giger's 5e D&D wiki)
# for information on different classes and races, to create text files for
# NPCGen to use.

import requests
import json

def listToString(li):
    listr = ""
    for elem in li:
        listr += str(elem) + "-"
    listr = listr.strip('-')
    listr += "/"
    return listr

def getRace(race):
    # Accessing the Web Page
    url = "http://dnd5eapi.co/api/races/" + race
    response = requests.get(url)

    # Loading the JSON data
    data = response.text
    parsed = json.loads(data)

    # Retrieving the class name (for future file use)
    racename = parsed["name"]
    print(racename)
    # Retrieving the Stat Bonuses
    stat_bonuses = parsed["ability_bonuses"]

    # Retrieving the Traits
    traits = []
    for elem in parsed["traits"]:
        traits += [elem["name"]]
    

    # Manual Input for Male First Names
    print("Enter the male first name options one at a time. Enter 'x' to end.")
    mnames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        mnames += [tempstr]
        tempstr = input("Name: ")

    # Manual Input for Female First Names
    print("Enter the female first name options one at a time. Enter 'x' to end.")
    fnames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        fnames += [tempstr]
        tempstr = input("Name: ")

    # Manual Input for Gender Neutral First Names
    print("Enter the gender neutral first name options one at a time. Enter 'x' to end.")
    nnames = []
    tempstr = input("Names: ")
    while tempstr != 'x' :
        nnames += [tempstr]
        tempstr = input("Names: ")

    # Manual Input for Height Range
    mmaxheight = int(input("Enter max height (in inches): "))
    fmaxheight = int(.9 * mmaxheight)
    nmaxheight = int(.95 * mmaxheight)

    fminheight = int(input("Enter min height (in inches): "))
    mminheight = int(1.1 * fminheight)
    nminheight = int(1.05 * fminheight)

    mheight = range(mminheight, mmaxheight, 1)
    fheight = range(fminheight, fmaxheight, 1)
    nheight = range(nminheight, nmaxheight, 1)

    # Manual Input for Weight Range
    mmaxweight = int(input("Enter max weight (in pounds): "))
    fmaxweight = int(.9 * mmaxweight)
    nmaxweight = int(.95 * mmaxweight)

    fminweight = int(input("Enter min weight (in pounds): "))
    mminweight = int(1.1 * fminweight)
    nminweight = int(1.05 * fminweight)

    mweight = range(mminweight, mmaxweight, 3)
    fweight = range(fminweight, fmaxweight, 3)
    nweight = range(nminweight, nmaxweight, 3)
    
    # Manual Input for Surnames
    print("Enter the surname options one at a time. Enter 'x' to end.")
    snames = []
    tempstr = input("Name: ")
    while tempstr != 'x' :
        snames += [tempstr]
        tempstr = input("Name: ")

    # Manual Input for Eye Colors
    print("Enter the eye color options one at a time. Enter 'x' to end.")
    eyecolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        eyecolors += [tempstr]
        tempstr = input("Color: ")

    # Manual Input for Hair Colors
    print("Enter the hair color options one at a time. Enter 'x' to end.")
    haircolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        haircolors += [tempstr]
        tempstr = input("Color: ")

    # Manual Input for Skin Colors
    print("Enter the skin color options one at a time. Enter 'x' to end.")
    skincolors = []
    tempstr = input("Color: ")
    while tempstr != 'x' :
        skincolors += [tempstr]
        tempstr = input("Color: ")

    # Manual Input for Age
    maxage = int(input("Enter maximum age: "))
    minage = int(input("Enter minimum age: "))
    ages = range(minage, maxage, int((maxage-minage)/20))

    # Writing the data into a text file, in a parsable format for NPCGen
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
    # Accessing the Web Page
    url = "http://dnd5eapi.co/api/classes/" + classname
    response = requests.get(url)

    # Loading the JSON data
    data = response.text
    parsed = json.loads(data)

    # Retrieving the class name (for future file use)
    classname = parsed["name"]
    
    # Retrieving the Hit Die
    hd = str(parsed["hit_die"])

    # Retrieving the possible Skill Proficiencies
    skills = []
    for elem in parsed["proficiency_choices"][0]["from"]:
        skills += [elem["name"][7:]]

    # Retrieving level abilities, with each index + 1 being the level that the class
    # gets those abilities
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

    # Getting manual input for the possible armor
    print("Enter the armor options one at a time. Enter 'x' to end.")
    armor = []
    tempstr = input("Armor: ")
    while tempstr != 'x' :
        armor += [tempstr]
        tempstr = input("Armor: ")
    
    # Getting manual input for the possible weapons
    print("Enter the weapon options one at a time. Enter 'x' to end.")
    weapons = []
    tempstr = input("Weapon: ")
    while tempstr != 'x' :
        weapons += [tempstr]
        tempstr = input("Weapon: ")

    # Getting manual input for the primary stats
    print("Enter the primary stats:")
    prime = input("Prime: ")
    second = input("Secondary: ")
    primes = [prime, second]

    # Writing the data into a text file, in a parsable format for NPCGen
    filename = classname + ".txt"
    file = open(filename,'w')
    filestr = ""
    for level in abilities:
        for ability in level:
            filestr += ability + ", "
        filestr = filestr.strip(' ')
        filestr = filestr.strip(',')
        filestr += "/"

    # Placing the information into a string
    filestr += listToString(armor)
    filestr += listToString(weapons)
    filestr += listToString(skills)
    filestr += listToString(primes) + hd

    # Writing the string to the opened file
    file.write(filestr)

def getClassManual():
    classname = input("What is the name of this class:\n")
    # hit die, skills, abilities, armor, weapons, prime stats

    # Getting manual input for the hit die
    hd = input("Enter the hit die: ")

    # Getting manual input for the abilities
    print("Enter the abilities level by level. Enter 'x' to end.")
    abilities = []
    for i in range(1,6):
        print("Level "+ str(i) +": ")
        tempstr = input("Ability: ")
        level_abs = []
        while tempstr != 'x' :
            level_abs += [tempstr]
            tempstr = input("Ability: ")
        abilities += [level_abs]

    # Getting manual input for the possible skills
    print("Enter the skill options one at a time. Enter 'x' to end.")
    skills = []
    tempstr = input("Skill: ")
    while tempstr != 'x' :
        skills += [tempstr]
        tempstr = input("Skill: ")

    # Getting manual input for the possible armor
    print("Enter the armor options one at a time. Enter 'x' to end.")
    armor = []
    tempstr = input("Armor: ")
    while tempstr != 'x' :
        armor += [tempstr]
        tempstr = input("Armor: ")
    
    # Getting manual input for the possible weapons
    print("Enter the weapon options one at a time. Enter 'x' to end.")
    weapons = []
    tempstr = input("Weapon: ")
    while tempstr != 'x' :
        weapons += [tempstr]
        tempstr = input("Weapon: ")

    # Getting manual input for the primary stats
    print("Enter the primary stats:")
    prime = input("Prime: ")
    second = input("Secondary: ")
    primes = [prime, second]

    # Writing the data into a text file, in a parsable format for NPCGen
    filename = classname + ".txt"
    file = open(filename,'w')
    filestr = ""
    for level in abilities:
        for ability in level:
            filestr += ability + ", "
        filestr = filestr.strip(' ')
        filestr = filestr.strip(',')
        filestr += "/"

    # Placing the information into a string
    filestr += listToString(armor)
    filestr += listToString(weapons)
    filestr += listToString(skills)
    filestr += listToString(primes) + hd

    # Writing the string to the opened file
    file.write(filestr)
    
