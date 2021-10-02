import io
import sys
import os
nameRead = open("data/names.csv","r",encoding = "utf-8")
nameWrite = open("data/test.csv","w", encoding = "utf-8")
nameWrite.write("Update")
nameWrite.write("\n")

nameWrite.write("DROP TABLE IF EXISTS names;")
nameWrite.write("\n")

instantiate_string = "CREATE TABLE names (nconst text, primaryName text, birthYear integer, deathYear integer, primaryProfession text, PRIMARY KEY (nconst));"
nameWrite.write(instantiate_string)
nameWrite.write("\n")
nameWrite.write("INSERT INTO names(nconst,primaryName,birthYear,deathYear,primaryProfession) ")
nameWrite.write("Values ")
 
namesdict = {}
iter = 0


for line in nameRead:

    if (iter != 0):
        values = line.split("\t")
       #print(values)
        try:
            namesdict[values[1]] = {} #nconst
            namesdict[values[1]]["primaryName"]  = values[2]
            namesdict[values[1]]["birthYear"]  = values[3]
            namesdict[values[1]]["deathYear"]  = values[4]
            namesdict[values[1]]["primaryProfession"]  = values[5].rstrip()
            lastkey = values[1] #will be the last key
        except:#there are undefined characters
            
            input("char problem?")
            
    iter += 1
def par(string):#add escape to apostrophes
    newstring = ""
    for chara in string:
        if (chara == "'"):
            newstring += "''"
        else:
            newstring += chara
    return newstring
iter = 0
for nconst in namesdict:
    iter += 1
    writestring = ""
    nconststring = par(str(nconst))
    namestring = par(str(namesdict[nconst]["primaryName"]))
    birthyearstring = par(str(namesdict[nconst]["birthYear"]))
    try:
        a = int(birthyearstring)
    except:
        birthyearstring = "000" #if birthyear is missing default int of 000 is added to table. presumably, nobody will think we mean that someone working in the film industry was born in the year 0
    deathyearstring = par(str(namesdict[nconst]["deathYear"]))
    try:
        a = int(deathyearstring)
    except:
        deathyearstring = "000"
    professionstring = par(str(namesdict[nconst]["primaryProfession"]))

    writestring += "(\'" + nconststring + "\'," 
    writestring +=  "\'" + namestring + "\'" + "," + "\'" + birthyearstring + "\'" + "," 
    writestring += "\'" + deathyearstring +"\'" + "," + "\'"+ professionstring + "\'"
    writestring += ")"
    
    if (nconst != lastkey):
        writestring += ","
    
    nameWrite.write(writestring)
nameWrite.write(";")






nameWrite.close()
nameRead.close()
